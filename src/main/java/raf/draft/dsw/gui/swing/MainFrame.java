package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ActionManager;
import raf.draft.dsw.jtabbedpane.JTabbedPaneImplementation;
import raf.draft.dsw.jtabbedpane.view.JTabbedPaneRightPanel;
import raf.draft.dsw.jtabbedpane.view.JTabbedPaneView;
import raf.draft.dsw.jtree.DraftTree;
import raf.draft.dsw.jtree.DraftTreeImplementation;
import raf.draft.dsw.jtabbedpane.model.JTabbedPanePublisher;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements ISubscriber{

    private ActionManager actionManager = new ActionManager();
    private static MainFrame mainFrameInstance;
    private DraftTree draftTree;
    private JTree jTree;
    private JTabbedPane jTabbedPane = new JTabbedPane();
    private java.util.List<Room> roomList = new ArrayList<Room>();
    private Label labelBuilding = new Label("Building");
    private Label labelProject = new Label("Project");

    private Label labelAutor = new Label("Autor");
    private JTabbedPanePublisher jTabbedPanePublisher;
    private JTabbedPaneView jTabbedPaneView;
    private JTabbedPaneImplementation jTabbedPaneImplementation;
    private boolean flagIsSelected=false;
    ;
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

        //JTREE
        draftTree = new DraftTreeImplementation();
        ProjectExplorer projectExplorer = new ProjectExplorer("ProjectExplorer");
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
        add(jTabbedPaneView.getjTabbedPane(),BorderLayout.CENTER);
        jTabbedPaneImplementation=new JTabbedPaneImplementation(jTabbedPaneView.getjTabbedPane(),jTree,roomList);
        jTabbedPaneImplementation.generate();
        jTabbedPanePublisher=new JTabbedPanePublisher(jTabbedPaneView.getjTabbedPane(),jTabbedPaneImplementation.getRoomList());

        //RIGHTSIDE
        JTabbedPaneRightPanel jTabbedPaneRightPanel = new JTabbedPaneRightPanel(labelProject, labelBuilding, labelAutor,jTabbedPanePublisher);
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

    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
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

        if(o instanceof Project) {

            for (DraftNode draftNode : ((Project) o).getChildrenList()) {
                if (draftNode instanceof Room) {
                    jTabbedPaneView.getjTabbedPane().remove(jTabbedPaneImplementation.getRoomList().indexOf(draftNode));
                    ((Project) o).getChildrenList().remove(draftNode);
                    jTabbedPaneImplementation.getRoomList().remove(draftNode);
                    jTabbedPaneImplementation.setIndexOfTab(jTabbedPaneImplementation.getIndexOfTab()-1);
                }

                else if (draftNode instanceof Building) {
                    for (DraftNode draftNodeBuilding : ((Building) draftNode).getChildrenList()) {
                        if (draftNodeBuilding instanceof Room) {
                            jTabbedPaneView.getjTabbedPane().remove(jTabbedPaneImplementation.getRoomList().indexOf(draftNodeBuilding));
                            ((Project) o).getChildrenList().remove(draftNodeBuilding);
                            jTabbedPaneImplementation.getRoomList().remove(draftNodeBuilding);
                            jTabbedPaneImplementation.setIndexOfTab(jTabbedPaneImplementation.getIndexOfTab()-1);
                        }
                    }
                }
            }
        }

        else if(o instanceof Building){
            for (DraftNode draftNode : ((Building) o).getChildrenList()) {
                System.out.println(((Building) o).getChildrenList());
                if (draftNode instanceof Room) {
                    jTabbedPaneView.getjTabbedPane().remove(jTabbedPaneImplementation.getRoomList().indexOf(draftNode));
                    ((Building) o).getChildrenList().remove(draftNode);
                    jTabbedPaneImplementation.getRoomList().remove(draftNode);
                    jTabbedPaneImplementation.setIndexOfTab(jTabbedPaneImplementation.getIndexOfTab()-1);
                }
            }
        }

        else if(o instanceof Room){
            if(((Room) o).getParent() instanceof Project){
                ((Project) ((Room) o).getParent()).getChildrenList().remove(o);
            }

            else if(((Room) o).getParent() instanceof Building){
                ((Building) ((Room) o).getParent()).getChildrenList().remove(o);
            }
            jTabbedPaneView.getjTabbedPane().remove(jTabbedPaneImplementation.getRoomList().indexOf(o));
            jTabbedPaneImplementation.getRoomList().remove(o);
            jTabbedPaneImplementation.setIndexOfTab(jTabbedPaneImplementation.getIndexOfTab()-1);
        }
    }
}

