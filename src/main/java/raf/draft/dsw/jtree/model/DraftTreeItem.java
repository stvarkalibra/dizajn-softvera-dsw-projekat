package raf.draft.dsw.jtree.model;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

public class DraftTreeItem extends DefaultMutableTreeNode implements ISubscriber {
    private DraftNode draftNode;
    public DraftTreeItem(DraftNode node){
        this.draftNode=node;
    }
    public List<DraftNode> A(DraftNodeComposite node){
        return node.getChildrenList();
    }

    @Override
    public String toString() {
        return draftNode.getNodeName();
    }
    public void setName(String name){
        if(name.equals("")){
            MessageGenerator messageGenerator = new MessageGenerator();
            FileLogger fileLogger = new FileLogger("files/log.txt");
            ConsoleLogger consoleLogger = new ConsoleLogger();

            messageGenerator.addSubscriber(consoleLogger);
            messageGenerator.addSubscriber(fileLogger);

            Message message=new Message("NAME_CANNOT_BE_EMPTY", MessageType.ERROR);
            messageGenerator.createMessage(message.getContent(),message.getType());
            update(message);
        }else {
            this.draftNode.setNodeName(name);
        }
    }
    public void addChild(DraftNode child) {
        if (this.draftNode instanceof DraftNodeComposite) {
            ((DraftNodeComposite) this.draftNode).addChild(child); // Dodavanje u logičku strukturu
        }
    }public void removeChild(DraftNode child){
        if(this.draftNode instanceof DraftNodeComposite){
            ((DraftNodeComposite) this.draftNode).removeChild(child);
        }
    }

    public DraftNode getDraftNode() {
        return draftNode;
    }

    public void setDraftNode(DraftNode draftNode) {
        this.draftNode = draftNode;
    }

    @Override
    public void update(Object o) {
        if(o instanceof Message && ((Message) o).getType().equals(MessageType.ERROR)){
            JOptionPane.showMessageDialog(null,o,((Message) o).getType().toString(),JOptionPane.ERROR_MESSAGE);
        }
    }
}
