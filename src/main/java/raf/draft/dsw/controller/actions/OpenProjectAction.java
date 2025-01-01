package raf.draft.dsw.controller.actions;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.serializer.Gson;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class OpenProjectAction extends AbstractRoomAction{
    private final Gson serializer;
    public OpenProjectAction(Gson serializer){
        this.serializer = serializer;
        putValue(SMALL_ICON, loadIcon("/images/addNode.png"));
        putValue(NAME, "AddNode");
        putValue(SHORT_DESCRIPTION, "Add New Node");
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
            RoomElement roomElement = (RoomElement) parentNode;
            if(roomElement.getParent() instanceof Room){
                roomElement.updateBounds(((Room) roomElement.getParent()).getK());
                System.out.println(((Room) roomElement.getParent()).getRoomElements().contains(roomElement));
            }
        }
        if (parentNode instanceof DraftNodeComposite) {
            DraftNodeComposite compositeNode = (DraftNodeComposite) parentNode;
            for (DraftNode childNode : compositeNode.getChildrenList()) {
                setBoundsForRoomElements(childNode);

            }
        }
    }
}


