package raf.draft.dsw.gui.swing;

import com.sun.tools.javac.Main;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ActionManager;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.jtree.DraftTree;
import raf.draft.dsw.jtree.DraftTreeImplementation;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainFrame extends JFrame{

    private ActionManager actionManager = new ActionManager();
    private static MainFrame mainFrameInstance;
    private DraftTree draftTree;
    private JTree jTree;
    private java.util.List<Room> roomList=new ArrayList<Room>();

    private MainFrame(){
        initialize();
    }

    public static MainFrame getInstance(){
        if(mainFrameInstance==null){
            mainFrameInstance=new MainFrame();
        }
        return mainFrameInstance;
    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar(actionManager);
        add(toolBar, BorderLayout.NORTH);

        JTabbedPane jTabbedPane=new JTabbedPane();
        jTabbedPane.setPreferredSize(new Dimension(600,screenHeight));


        draftTree=new DraftTreeImplementation();
        ProjectExplorer projectExplorer=new ProjectExplorer("ProjectExplorer");
        jTree = draftTree.generateTree(projectExplorer);

        JPanel desktop = new JPanel();

        JScrollPane scroll=new JScrollPane(jTree);
        scroll.setPreferredSize(new Dimension(200,screenHeight));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,desktop);
        add(split,BorderLayout.WEST);
        add(jTabbedPane,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);


        JPanel rightPanel=new JPanel();
        Label labelProject=new Label("Project");
        rightPanel.add(labelProject);
        rightPanel.setPreferredSize(new Dimension(200,screenHeight));
        add(rightPanel,BorderLayout.EAST);
        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            int indexOfTab=0;
            @Override
            public void mouseClicked(MouseEvent evt) {
                TreePath path=jTree.getPathForLocation(evt.getX(), evt.getY());

                if(path==null){
                    return;
                }
                DraftTreeItem selectedNode=(DraftTreeItem) path.getLastPathComponent();


                if (evt.getClickCount() == 2 && selectedNode.getDraftNode() instanceof Project) {

                    Project project = (Project) selectedNode.getDraftNode();
                    for(DraftNode draftNode:project.getChildrenList()){
                        if(draftNode instanceof Room && !roomList.contains((Room) draftNode)){
                            Label labelRoom=new Label();
                            jTabbedPane.addTab(draftNode.getNodeName(),labelRoom);
                            jTabbedPane.setBackgroundAt(indexOfTab,project.getColor());
                            roomList.add((Room) draftNode);
                            indexOfTab++;
                        }
                        else if(draftNode instanceof Building){
                            for(DraftNode draftNodeBuilding: ((Building) draftNode).getChildrenList()){
                                if(draftNodeBuilding instanceof Room && !roomList.contains((Room)draftNodeBuilding)){
                                    Label labelRoom=new Label();
                                    jTabbedPane.addTab(draftNodeBuilding.getNodeName(),labelRoom);
                                    jTabbedPane.setBackgroundAt(indexOfTab,((Building) draftNode).getColor());
                                    roomList.add((Room) draftNodeBuilding);
                                    indexOfTab++;
                                }

                            }
                        }

                    }

                }
            }
        });

    }
    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public JTree getjTree() {
        return jTree;
    }

    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }

    public DraftTree getDraftTree() {
        return draftTree;
    }

    public void setDraftTree(DraftTree draftTree) {
        this.draftTree = draftTree;
    }
}
