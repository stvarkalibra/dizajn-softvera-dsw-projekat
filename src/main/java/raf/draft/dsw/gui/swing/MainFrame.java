package raf.draft.dsw.gui.swing;

import com.sun.tools.javac.Main;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ActionManager;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.jtree.DraftTree;
import raf.draft.dsw.jtree.DraftTreeImplementation;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDateTime;

public class MainFrame extends JFrame implements ISubscriber{

    private ActionManager actionManager = new ActionManager();
    private static MainFrame mainFrameInstance;
    private DraftTree draftTree;
    private JTree jTree;

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
        jTabbedPane.setPreferredSize(new Dimension(400,200));
        add(jTabbedPane,BorderLayout.CENTER);

        draftTree=new DraftTreeImplementation();
        ProjectExplorer projectExplorer=new ProjectExplorer("ProjectExplorer");
        jTree = draftTree.generateTree(projectExplorer);

        JPanel desktop = new JPanel();

        JScrollPane scroll=new JScrollPane(jTree);
        scroll.setMinimumSize(new Dimension(400,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,desktop);
        getContentPane().add(split,BorderLayout.WEST);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    for(DraftNode draftNode:projectExplorer.getChildrenList()){

                        Label label1=new Label();
                        jTabbedPane.addTab("A",label1);
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

    @Override
    public void update(Object o) {
        JOptionPane.showMessageDialog(null, o, "ERROR",JOptionPane.INFORMATION_MESSAGE);
    }
}
