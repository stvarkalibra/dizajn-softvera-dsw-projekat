package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.ChangeInfoFrame;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChangeInfoAction extends AbstractRoomAction implements IPublisher{
    private List<ISubscriber> subscriberList=new ArrayList<>();
    public ChangeInfoAction() {
        putValue(SMALL_ICON, loadIcon("/images/editProject.png"));
        putValue(NAME, "ChangeProjectInfo");
        putValue(SHORT_DESCRIPTION, "Change project info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        if (selected.getDraftNode() instanceof Project){
            ChangeInfoFrame changeInfoFrame = new ChangeInfoFrame((Project) selected.getDraftNode());
            addSubscriber(changeInfoFrame);
            changeInfoFrame.addElements((Project) selected.getDraftNode());
            changeInfoFrame.setVisible(true);
            changeInfoFrame.getChangeButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    notifySubscriber(selected.getDraftNode());
                }
            });
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
