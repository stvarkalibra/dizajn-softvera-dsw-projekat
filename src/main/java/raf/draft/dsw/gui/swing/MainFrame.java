package raf.draft.dsw.gui.swing;

import com.sun.tools.javac.Main;
import org.w3c.dom.ls.LSOutput;
import raf.draft.dsw.controller.observer.IPublisher;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements ISubscriber, IPublisher {

    private ActionManager actionManager = new ActionManager();
    private static MainFrame mainFrameInstance;
    private DraftTree draftTree;
    private JTree jTree;
    private JTabbedPane jTabbedPane = new JTabbedPane();
    private java.util.List<Room> roomList = new ArrayList<Room>();
    private Label labelBuilding = new Label("Building");
    private Label labelProject = new Label("Project");
    private Label labelAutor = new Label("Autor");
    private boolean flagIsSelected=false;
    ;
    private List<ISubscriber> listSubscriber = new ArrayList<>();
    private final Icon roomIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/room.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

    private MainFrame() {
        initialize();
        addSubscriber(this);
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

        //RIGHTSIDE
        JPanel rightPanel = new JPanel();
        rightPanel.add(labelProject);
        rightPanel.add(labelBuilding);
        rightPanel.add(labelAutor);
        rightPanel.setPreferredSize(new Dimension(200, screenHeight));
        add(rightPanel, BorderLayout.EAST);

        //JTABBEDPANE
        add(jTabbedPane, BorderLayout.CENTER);
        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            int indexOfTab = 0;

            @Override
            public void mouseClicked(MouseEvent evt) {
                TreePath path = jTree.getPathForLocation(evt.getX(), evt.getY());

                if (path == null) {
                    return;
                }
                DraftTreeItem selectedNode = (DraftTreeItem) path.getLastPathComponent();

                if (evt.getClickCount() == 2 && selectedNode.getDraftNode() instanceof Project) {

                    Project project = (Project) selectedNode.getDraftNode();
                    for (DraftNode draftNode : project.getChildrenList()) {
                        if (draftNode instanceof Room && !roomList.contains((Room) draftNode)) {
                            Label labelRoom = new Label();
                            roomList.add((Room) draftNode);
                            jTabbedPane.addTab(draftNode.getNodeName(), labelRoom);
                            jTabbedPane.setBackgroundAt(indexOfTab, project.getColor());

                            jTabbedPane.setIconAt(indexOfTab, roomIcon);
                            indexOfTab++;
                        } else if (draftNode instanceof Building) {
                            for (DraftNode draftNodeBuilding : ((Building) draftNode).getChildrenList()) {
                                if (draftNodeBuilding instanceof Room && !roomList.contains((Room) draftNodeBuilding)) {
                                    Label labelRoom = new Label();
                                    roomList.add((Room) draftNodeBuilding);
                                    jTabbedPane.addTab(draftNodeBuilding.getNodeName(), labelRoom);
                                    jTabbedPane.setBackgroundAt(indexOfTab, ((Building) draftNode).getColor());
                                    jTabbedPane.setIconAt(indexOfTab, roomIcon);
                                    indexOfTab++;
                                }
                            }
                        }
                    }
                }
            }
        });
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index=jTabbedPane.getSelectedIndex();
                notifySubscriber(roomList.get(index));

            }

        });
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

    /*--------------------------------------------------------------------------------*/
    //OBSERVER PATTERN
    @Override
    public void addSubscriber(ISubscriber s) {
        if (!listSubscriber.contains(s)) {
            listSubscriber.add(s);
        }
    }
    @Override
    public void removeSubscriber(ISubscriber s) {
        if (listSubscriber.contains(s)) {
            listSubscriber.remove(s);
        }
    }
    @Override
    public void notifySubscriber(Object o) {
        for (ISubscriber subscriber : listSubscriber) {
            subscriber.update(o);
        }
    }
    @Override
    public void update(Object o) {
        if (o instanceof Room) {
            if(((Room) o).getParent() instanceof Project){
                labelProject.setText(((Room) o).getParent().getNodeName());
                labelAutor.setText(((Project) ((Room) o).getParent()).getProjectAutor());
                labelBuilding.setText("/");
            }else{
                if(((Room) o).getParent().getParent() instanceof Project){
                    labelAutor.setText(((Project) ((Room) o).getParent().getParent()).getProjectAutor());
                }
                labelBuilding.setText(((Room) o).getParent().getNodeName());
                labelProject.setText(((Room) o).getParent().getParent().getNodeName());
            }
        }
    }
}

