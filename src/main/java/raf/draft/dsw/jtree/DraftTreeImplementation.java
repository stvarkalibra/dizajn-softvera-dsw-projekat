package raf.draft.dsw.jtree;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ActionManager;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.controller.DraftTreeFactory;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.jtree.view.DraftTreeView;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.io.IOException;
import java.util.Random;

public class DraftTreeImplementation implements DraftTree,ISubscriber{
    private DraftTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public DraftTreeView generateTree(ProjectExplorer projectExplorer) {
        DraftTreeItem root=new DraftTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView =new DraftTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(DraftTreeItem parent) {
        if(parent==null){
            MessageGenerator messageGenerator = new MessageGenerator();
            FileLogger fileLogger = new FileLogger("files/log.txt");
            ConsoleLogger consoleLogger = new ConsoleLogger();

            messageGenerator.addSubscriber(consoleLogger);
            messageGenerator.addSubscriber(fileLogger);

            Message message = new Message("YOU_NEED_TO_SELECT_NODE", MessageType.ERROR);
            messageGenerator.createMessage(message.getContent(), message.getType());
            update(message);
            return;
        }

        if (parent.getDraftNode() instanceof Room){
            MessageGenerator messageGenerator = new MessageGenerator();
            FileLogger fileLogger = new FileLogger("files/log.txt");
            ConsoleLogger consoleLogger = new ConsoleLogger();

            messageGenerator.addSubscriber(consoleLogger);
            messageGenerator.addSubscriber(fileLogger);

            Message message = new Message("NODE_IS_LEAF", MessageType.INFORMATION);
            messageGenerator.createMessage(message.getContent(), message.getType());
            update(message);
            return;
        }

        DraftTreeFactory draftTreeFactory = new DraftTreeFactory();
        DraftNode child = draftTreeFactory.createChild(parent.getDraftNode());

        if (child != null) {
            parent.add(new DraftTreeItem(child));
            ((DraftNodeComposite) parent.getDraftNode()).addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
    }


    @Override
    public void removeChild(DraftTreeItem parent, boolean deleteElement) {
        if(parent==null){
            MessageGenerator messageGenerator = new MessageGenerator();
            FileLogger fileLogger = new FileLogger("files/log.txt");
            ConsoleLogger consoleLogger = new ConsoleLogger();

            messageGenerator.addSubscriber(consoleLogger);
            messageGenerator.addSubscriber(fileLogger);

            Message message = new Message("YOU_NEED_TO_SELECT_NODE", MessageType.ERROR);
            messageGenerator.createMessage(message.getContent(), message.getType());
            update(message);
            return;
        }
        else if((parent.getDraftNode() instanceof ProjectExplorer)){
            MessageGenerator messageGenerator = new MessageGenerator();
            FileLogger fileLogger = new FileLogger("files/log.txt");
            ConsoleLogger consoleLogger = new ConsoleLogger();

            messageGenerator.addSubscriber(consoleLogger);
            messageGenerator.addSubscriber(fileLogger);

            Message message=new Message("NODE_CANNOT_BE_DELETED",MessageType.ERROR);
            messageGenerator.createMessage(message.getContent(),message.getType());
            update(message);
            MainFrame.getInstance().setFlag(true);
            return;
        }
        else{
            if (!(parent.getDraftNode() instanceof DraftNodeComposite) && !(parent.getDraftNode() instanceof ProjectExplorer)){
                ((DraftTreeItem)parent.getParent()).remove(parent);
            }else{
                if(deleteElement==true){

                }else {
                    parent.removeAllChildren();
                    ((DraftTreeItem) parent.getParent()).remove(parent);
                }
            }
        }

        MainFrame.getInstance().setFlag(false);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public DraftTreeItem getSelectedNode() {
        return (DraftTreeItem)treeView.getLastSelectedPathComponent();
    }

    public DraftTreeView getTreeView() {
        return treeView;
    }

    public void setTreeView(DraftTreeView treeView) {
        this.treeView = treeView;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        this.treeModel = treeModel;
    }

    @Override
    public void update(Object o) {
        if(o instanceof Message && ((Message) o).getType().equals(MessageType.ERROR)){
            JOptionPane.showMessageDialog(null,o,((Message) o).getType().toString(),JOptionPane.ERROR_MESSAGE);
        }if(o instanceof Message && ((Message) o).getType().equals(MessageType.INFORMATION)){
            JOptionPane.showMessageDialog(null,o,((Message) o).getType().toString(),JOptionPane.INFORMATION_MESSAGE);
        }if(o instanceof Message && ((Message) o).getType().equals(MessageType.WARNING)){
            JOptionPane.showMessageDialog(null,o,((Message) o).getType().toString(),JOptionPane.WARNING_MESSAGE);
        }
        if(o instanceof DraftNodeComposite){
            treeView.update(o);
        }

    }
}
