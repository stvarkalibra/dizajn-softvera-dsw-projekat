package raf.draft.dsw.controller.actions;

import raf.draft.dsw.elements.*;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.*;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.serializer.JacksonSerializer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class OpenProjectAction extends AbstractRoomAction{
    private final JacksonSerializer serializer;
    public OpenProjectAction(JacksonSerializer serializer){
        this.serializer = serializer;
        putValue(SMALL_ICON, loadIcon("/images/open_project.png"));
        putValue(NAME, "OpenProject");
        putValue(SHORT_DESCRIPTION, "Open Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open();
    }
    public void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Project");

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Project project = serializer.loadProject(selectedFile);

            String uniqueName = generateUniqueProjectName(project.getProjectName());
            project.setProjectName(uniqueName);
            project.setNodeName(uniqueName);
            project.setParent(MainFrame.getInstance().getProjectExplorer());
            setParent(project.getChildrenList(), project);

            updateTreeWithProject(project);
            setBoundsForRoomElements(project);
            addTabToJTabbedPane(project);
            setRoomViewDimensions(project);
            setAllPainters(project);
            drawAllPainters(project);
            enableAddButton(project);

            System.out.println("Project loaded successfully.");
        } else {
            System.out.println("Open operation canceled.");
        }
    }
    public void setParent(List<DraftNode> childrenList, DraftNodeComposite parent) {
        for (DraftNode draftNode : childrenList) {
            draftNode.setParent(parent);

            if (draftNode instanceof DraftNodeComposite) {
                DraftNodeComposite compositeNode = (DraftNodeComposite) draftNode;
                setParent(compositeNode.getChildrenList(), compositeNode);
            }

        }
    }
    private void updateTreeWithProject(Project project) {
        DefaultTreeModel model = (DefaultTreeModel) MainFrame.getInstance().getjTree().getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) model.getRoot();
        DraftTreeItem projectNode = new DraftTreeItem(project);
        addChildrenToNode(projectNode, project);
        rootNode.add(projectNode);
        model.reload(rootNode);
    }

    private void addChildrenToNode(DraftTreeItem parentNode, DraftNode parentNodeData) {
        if (parentNodeData instanceof RoomElement) {
            System.out.println(parentNode);
            return;
        }
        if (parentNodeData instanceof DraftNodeComposite) {
            DraftNodeComposite compositeNode = (DraftNodeComposite) parentNodeData;
            for (DraftNode child : compositeNode.getChildrenList()) {
                DraftTreeItem childNode = new DraftTreeItem(child);
                parentNode.add(childNode);
                addChildrenToNode(childNode, child);
            }
        }
    }
    private String generateUniqueProjectName(String baseName) {
        ProjectExplorer projectExplorer = MainFrame.getInstance().getProjectExplorer();
        List<DraftNode> existingProjects = projectExplorer.getChildrenList();

        String uniqueName = baseName;
        int counter = 1;

        for (DraftNode node : existingProjects) {
            while (node.getNodeName().equals(uniqueName)) {
                uniqueName = baseName + "(" + counter + ")";
                counter++;
            }
        }
        return uniqueName;
    }
    private void setBoundsForRoomElements(DraftNode parentNode) {
        if (parentNode instanceof RoomElement) {
            if(parentNode.getParent() instanceof Room){
                ((Room) parentNode.getParent()).getRoomElements().get(((Room) parentNode.getParent()).getChildrenList().indexOf(parentNode)).updateBounds(((Room) parentNode.getParent()).getK());
            }
        }
        if (parentNode instanceof DraftNodeComposite) {
            DraftNodeComposite compositeNode = (DraftNodeComposite) parentNode;
            for (DraftNode childNode : compositeNode.getChildrenList()) {
                setBoundsForRoomElements(childNode);
            }
        }
    }
    private void addTabToJTabbedPane(DraftNode draftNode){
        if(draftNode instanceof Room){
            MainFrame.getInstance().getjTabbedPaneImplementation().setPrikazi(true);
            MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().add((Room) draftNode);
            JPanel jPanel=new JPanel();
            RoomView roomView=new RoomView((Room) draftNode);
            ((Room) draftNode).setRoomView(roomView);
            jPanel.add(roomView.getjPanelRoom());

            MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().addTab(draftNode.getNodeName(),jPanel);
            if(draftNode.getParent() instanceof Building){
                MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().setBackgroundAt(MainFrame.getInstance().getjTabbedPaneImplementation().getIndexOfTab(),((Building)(draftNode.getParent())).getColor());
            }if(draftNode.getParent() instanceof Project){
                MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().setBackgroundAt(MainFrame.getInstance().getjTabbedPaneImplementation().getIndexOfTab(),((Project)(draftNode.getParent())).getColor());
            }

            MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().setIconAt(MainFrame.getInstance().getjTabbedPaneImplementation().getIndexOfTab(),MainFrame.getInstance().getjTabbedPaneImplementation().getRoomIcon());
            MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().setSelectedIndex(MainFrame.getInstance().getjTabbedPaneImplementation().getIndexOfTab());
            MainFrame.getInstance().getjTabbedPaneImplementation().setIndexOfTab(MainFrame.getInstance().getjTabbedPaneImplementation().getIndexOfTab()+1);
        }
        if(draftNode instanceof DraftNodeComposite){
            DraftNodeComposite compositeNode=(DraftNodeComposite) draftNode;
            for(DraftNode childNode:compositeNode.getChildrenList()){
                addTabToJTabbedPane(childNode);
            }
        }
    }
    private void setRoomViewDimensions(DraftNode draftNode){
        if(draftNode instanceof Room){
            if(((Room) draftNode).getHeight()!=0){
                ((Room) draftNode).getRoomView().setK(((Room) draftNode).getK());
                ((Room) draftNode).addSubscriber(((Room) draftNode).getRoomView());
                ((Room) draftNode).notifySubscriber(draftNode);
            }
        }
        if(draftNode instanceof DraftNodeComposite){
            DraftNodeComposite compositeNode=(DraftNodeComposite) draftNode;
            for(DraftNode childNode:compositeNode.getChildrenList()){
                setRoomViewDimensions(childNode);
            }
        }
    }
    private void setAllPainters(DraftNode draftNode){
        if(draftNode instanceof RoomElement){
            MyPainter newPainter = null;
            if (draftNode instanceof Bed){
                newPainter = new BedPainter((RoomElement) draftNode);
            } else if (draftNode instanceof BathTub){
                newPainter = new BathtubPainter((RoomElement) draftNode);
            } else if (draftNode instanceof Boiler){
                newPainter = new BoilerPainter((RoomElement) draftNode);
            } else if (draftNode instanceof Closet){
                newPainter = new ClosetPainter((RoomElement) draftNode);
            } else if (draftNode instanceof Door){
                newPainter = new DoorPainter((RoomElement) draftNode);
            } else if (draftNode instanceof Sink){
                newPainter = new SinkPainter((RoomElement) draftNode);
            }  else if (draftNode instanceof Table){
                newPainter = new TablePainter((RoomElement) draftNode);
            } else if (draftNode instanceof ToiletSeat){
                newPainter = new ToiletPainter((RoomElement) draftNode);
            } else if (draftNode instanceof WashingMachine){
                newPainter = new WashingMachinePainter((RoomElement) draftNode);
            }
            if(draftNode.getParent() instanceof Room){
                ((Room) draftNode.getParent()).getRoomView().getPainterList().add(newPainter);
            }
        }
        if(draftNode instanceof DraftNodeComposite){
            DraftNodeComposite compositeNode=(DraftNodeComposite) draftNode;
            for(DraftNode childNode:compositeNode.getChildrenList()){
                setAllPainters(childNode);
            }
        }
    }
    private void drawAllPainters(DraftNode draftNode){
        if(draftNode instanceof Room){
            Room room = (Room) draftNode;
            RoomView roomView = room.getRoomView();
            JPanel paintPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;

                    for (MyPainter painter : roomView.getPainterList()) {
                        painter.paint(g2d, painter.getRoomElement().getLocation(), painter.getRoomElement().getDimensionLength() * (int) room.getK(),
                                painter.getRoomElement().getDimensionWidth() * (int) room.getK());
                    }
                }
            };

            paintPanel.setOpaque(false);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(paintPanel, BorderLayout.CENTER);
            roomView.getjPanelRoom().revalidate();
            roomView.getjPanelRoom().repaint();
        }
        if(draftNode instanceof DraftNodeComposite){
            DraftNodeComposite compositeNode=(DraftNodeComposite) draftNode;
            for(DraftNode childNode:compositeNode.getChildrenList()){
                drawAllPainters(childNode);
            }
        }
    }
    private void enableAddButton(DraftNode draftNode){
        if(draftNode instanceof Room){
            if (((Room) draftNode).getWidth() != 0 && ((Room) draftNode).getHeight() != 0) {
                ((Room) draftNode).getRoomView().setEditStateEntered(true);
                MainFrame.getInstance().getActionStateManager().getAddAction().setEnabled(true);
            }
        }
        if(draftNode instanceof DraftNodeComposite){
            DraftNodeComposite compositeNode=(DraftNodeComposite) draftNode;
            for(DraftNode childNode:compositeNode.getChildrenList()){
                enableAddButton(childNode);
            }
        }
    }
    private void setParentForRoomElement(DraftNode draftNode){

    }
}


