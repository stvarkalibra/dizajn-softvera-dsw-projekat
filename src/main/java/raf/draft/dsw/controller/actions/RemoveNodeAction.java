package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Room;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.Action.SMALL_ICON;

public class RemoveNodeAction extends AbstractRoomAction implements IPublisher {
    private List<ISubscriber> subscriberList=new ArrayList<>();
    public RemoveNodeAction(){
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "RemoveNode");
        putValue(SHORT_DESCRIPTION, "Node");
    }
    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().isFlag()==true){
            DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
            MainFrame.getInstance().getDraftTree().removeChild(selected);
            if(selected.getDraftNode() instanceof DraftNodeComposite){
                addSubscriber(MainFrame.getInstance());
                notifySubscriber(selected.getDraftNode());
            }else{
                addSubscriber(MainFrame.getInstance());
                notifySubscriber(selected.getDraftNode());
            }
        }else{
            MainFrame.getInstance().getDraftTree().removeChild(null);
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
        for(ISubscriber subscriber:subscriberList){
            subscriber.update(o);
        }
    }
}
