package raf.draft.dsw.jtabbedpane.model;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class JTabbedPanePublisher implements IPublisher {
    private List<ISubscriber> subscriberList = new ArrayList<>();
    private JTabbedPane jTabbedPane;
    private List<Room> roomList;

    public JTabbedPanePublisher(JTabbedPane jTabbedPane, List<Room> roomList) {
        this.jTabbedPane = jTabbedPane;
        this.roomList = roomList;
    }
    public void Events(){
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = jTabbedPane.getSelectedIndex();
                if (index >= 0 && index < roomList.size()) {
                    Room selectedRoom = roomList.get(index);
                    notifySubscriber(selectedRoom);

                    RoomView roomView = selectedRoom.getRoomView();
                    roomView.getjPanelRoom().revalidate();
                }
            }

        });
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