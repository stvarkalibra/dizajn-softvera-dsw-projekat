package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ActionManager;
import raf.draft.dsw.core.ActionStateManager;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.gui.swing.state.StateManager;
import raf.draft.dsw.jtabbedpane.JTabbedPaneImplementation;
import raf.draft.dsw.jtabbedpane.view.JTabbedPaneRightPanel;
import raf.draft.dsw.jtabbedpane.view.JTabbedPaneView;
import raf.draft.dsw.jtree.DraftTree;
import raf.draft.dsw.jtree.DraftTreeImplementation;
import raf.draft.dsw.jtabbedpane.model.JTabbedPanePublisher;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MainFrame extends JFrame implements ISubscriber{
    private JTabbedPaneRightPanel jTabbedPaneRightPanel;
    private ActionManager actionManager = new ActionManager();
    private ActionStateManager actionStateManager = new ActionStateManager();
    private static MainFrame mainFrameInstance;
    private DraftTree draftTree;
    private JTree jTree;
    private ProjectExplorer projectExplorer;
    private java.util.List<Room> roomList = new ArrayList<Room>();
    private Label labelBuilding = new Label("Building");
    private Label labelProject = new Label("Project");
    private Label labelAutor = new Label("Autor");
    private JTabbedPanePublisher jTabbedPanePublisher;
    private JTabbedPaneView jTabbedPaneView;
    private JTabbedPaneImplementation jTabbedPaneImplementation;
    private boolean flagIsSelected=false;
    private List<ISubscriber> listSubscriber = new ArrayList<>();

    private MainFrame() {
        initialize();
    }

    public static MainFrame getInstance() {
        if (mainFrameInstance == null) {
            mainFrameInstance = new MainFrame();
        }
        return mainFrameInstance;
    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        //MENUBAR & TOOLBAR
        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar(actionManager);
        add(toolBar, BorderLayout.NORTH);

        StateToolBar stateToolBar=new StateToolBar(actionStateManager);

        JPanel stateJPanel=new JPanel(new BorderLayout());
        add(stateJPanel,BorderLayout.CENTER);
        stateJPanel.add(stateToolBar, BorderLayout.NORTH);

        //JTREE
        draftTree = new DraftTreeImplementation();
        projectExplorer = new ProjectExplorer("ProjectExplorer");
        jTree = draftTree.generateTree(projectExplorer);
        JPanel desktop = new JPanel();
        JScrollPane scroll = new JScrollPane(jTree);

        scroll.setPreferredSize(new Dimension(200, screenHeight));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, desktop);
        add(split, BorderLayout.WEST);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        //JTABBEDPANE
        jTabbedPaneView=new JTabbedPaneView();
        stateJPanel.add(jTabbedPaneView.getjTabbedPane(), BorderLayout.CENTER);
        jTabbedPaneImplementation=new JTabbedPaneImplementation(jTabbedPaneView.getjTabbedPane(),jTree,roomList);
        jTabbedPaneImplementation.generate();
        jTabbedPanePublisher=new JTabbedPanePublisher(jTabbedPaneView.getjTabbedPane(),jTabbedPaneImplementation.getRoomList());

        //RIGHTSIDE
        jTabbedPaneRightPanel = new JTabbedPaneRightPanel(labelProject, labelBuilding, labelAutor,jTabbedPanePublisher);
        jTabbedPanePublisher.addSubscriber(jTabbedPaneRightPanel);
        JPanel rightPanel = jTabbedPaneRightPanel.getRightPanel();
        add(rightPanel, BorderLayout.EAST);
    }
    /*--------------------------------------------------------------------------------*/
    //GETTERS && SETTERS

    public DraftTree getDraftTree() {
        return draftTree;
    }

    public boolean isFlag() {
        return flagIsSelected;
    }

    public void setFlag(boolean flag) {
        this.flagIsSelected = flag;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Label getLabelProject() {
        return labelProject;
    }

    public void setLabelProject(Label labelProject) {
        this.labelProject = labelProject;
    }

    public Label getLabelAutor() {
        return labelAutor;
    }

    public void setLabelAutor(Label labelAutor) {
        this.labelAutor = labelAutor;
    }

    public JTabbedPanePublisher getjTabbedPanePublisher() {
        return jTabbedPanePublisher;
    }


    public void setjTabbedPanePublisher(JTabbedPanePublisher jTabbedPanePublisher) {
        this.jTabbedPanePublisher = jTabbedPanePublisher;
    }

    public JTabbedPaneRightPanel getjTabbedPaneRightPanel() {
        return jTabbedPaneRightPanel;
    }

    public void setjTabbedPaneRightPanel(JTabbedPaneRightPanel jTabbedPaneRightPanel) {
        this.jTabbedPaneRightPanel = jTabbedPaneRightPanel;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public static MainFrame getMainFrameInstance() {
        return mainFrameInstance;
    }

    public static void setMainFrameInstance(MainFrame mainFrameInstance) {
        MainFrame.mainFrameInstance = mainFrameInstance;
    }

    public void setDraftTree(DraftTree draftTree) {
        this.draftTree = draftTree;
    }

    public JTree getjTree() {
        return jTree;
    }

    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }

    public Label getLabelBuilding() {
        return labelBuilding;
    }

    public void setLabelBuilding(Label labelBuilding) {
        this.labelBuilding = labelBuilding;
    }

    public boolean isFlagIsSelected() {
        return flagIsSelected;
    }

    public void setFlagIsSelected(boolean flagIsSelected) {
        this.flagIsSelected = flagIsSelected;
    }

    public List<ISubscriber> getListSubscriber() {
        return listSubscriber;
    }

    public void setListSubscriber(List<ISubscriber> listSubscriber) {
        this.listSubscriber = listSubscriber;
    }

    public JTabbedPaneView getjTabbedPaneView() {
        return jTabbedPaneView;
    }

    public void setjTabbedPaneView(JTabbedPaneView jTabbedPaneView) {
        this.jTabbedPaneView = jTabbedPaneView;
    }

    public JTabbedPaneImplementation getjTabbedPaneImplementation() {
        return jTabbedPaneImplementation;
    }

    public void setjTabbedPaneImplementation(JTabbedPaneImplementation jTabbedPaneImplementation) {
        this.jTabbedPaneImplementation = jTabbedPaneImplementation;
    }
    /*--------------------------------------------------------------------------------*/

    @Override
    public void update(Object o) {
        if (o instanceof Project) {
            Project project = (Project) o;
            List<DraftNode> projectChildren = new ArrayList<>(project.getChildrenList());

            for (DraftNode draftNode : projectChildren) {
                if (draftNode instanceof Room) {
                    removeTabAndRoom((Room) draftNode);
                    project.getChildrenList().remove(draftNode);
                } else if (draftNode instanceof Building) {
                    for (DraftNode buildingChild : ((Building) draftNode).getChildrenList()) {
                        if (buildingChild instanceof Room) {
                            removeTabAndRoom((Room) buildingChild);
                        }
                    }
                    ((Building) draftNode).getChildrenList().clear();
                }
            }
        }
        else if (o instanceof Building) {
            Building building = (Building) o;
            List<DraftNode> buildingChildren = new ArrayList<>(building.getChildrenList());

            for (DraftNode draftNode : buildingChildren) {
                if (draftNode instanceof Room) {
                    removeTabAndRoom((Room) draftNode);
                }
            }
            building.getChildrenList().clear();
        }
        // Removing a single Room tab
        else if (o instanceof Room) {
            removeTabAndRoom((Room) o);
        }else if(o instanceof RoomElement){
            if(((RoomElement) o).getParent() instanceof Room){

            }

        }
        jTabbedPaneImplementation.setIndexOfTab(jTabbedPaneView.getjTabbedPane().getTabCount());

    }
    private void removeTabAndRoom(Room room) {
        int tabIndex = jTabbedPaneImplementation.getRoomList().indexOf(room);
        if (tabIndex != -1) {
            jTabbedPaneView.getjTabbedPane().remove(tabIndex);
            jTabbedPaneImplementation.getRoomList().remove(room);
        }
        if(jTabbedPaneImplementation.getRoomList().size()==0){
            jTabbedPaneImplementation.setPrikazi(false);
        }
    }

}


