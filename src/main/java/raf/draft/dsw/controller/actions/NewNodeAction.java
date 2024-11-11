package raf.draft.dsw.controller.actions;

import com.sun.tools.javac.Main;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class NewNodeAction extends AbstractRoomAction implements IPublisher {
    private List<ISubscriber> subscriberList=new ArrayList<>();
    public NewNodeAction(){
        putValue(SMALL_ICON, loadIcon("/images/addNode.png"));
        putValue(NAME, "AddNode");
        putValue(SHORT_DESCRIPTION, "Add New Node");
    }
    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().isFlag()==true){
            DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
            MainFrame.getInstance().getDraftTree().addChild(selected);
            if(selected.getDraftNode() instanceof DraftNodeComposite){
                if(((DraftNodeComposite) selected.getDraftNode()).getChildrenList().getLast() instanceof Room){
                    addSubscriber(MainFrame.getInstance().getjTabbedPaneImplementation());
                    notifySubscriber(((DraftNodeComposite) selected.getDraftNode()).getChildrenList().getLast());
                }
            }
        }else{
            MainFrame.getInstance().getDraftTree().addChild(null);
        }
    }

    @Override
    public void addSubscriber(ISubscriber s) {
        if(!subscriberList.contains(s)){
            subscriberList.add(s);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber s) {
        if(subscriberList.contains(s)){
            subscriberList.remove(s);
        }
    }

    @Override
    public void notifySubscriber(Object o) {
        for(ISubscriber subscriber: subscriberList){
            subscriber.update(o);
        }
    }
}
