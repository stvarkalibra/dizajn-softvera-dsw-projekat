package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.elements.*;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAction extends AbstractRoomAction {

    public AddAction() {
        putValue(SMALL_ICON, loadIcon("/images/editProject.png"));
        putValue(NAME, "ChangeProjectInfo");
        putValue(SHORT_DESCRIPTION, "Change project info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Room room = MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        room.getRoomView().startAdd();
        JPopupMenu popupMenu = new JPopupMenu();

        String[] options = {
                "Vrata",
                "Krevet",
                "Sto",
                "Ormar",
                "Kada/Tuš kabina",
                "Veš mašina",
                "Bojler",
                "Lavabo",
                "WC šolja"
        };

        for (String option : options) {
            JMenuItem menuItem = new JMenuItem(option);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        handleOptionSelected(option);
                    }
            });
            popupMenu.add(menuItem);
        }
        popupMenu.show(MainFrame.getInstance(), 250, 100);
    }
    public void handleOptionSelected(String option) {
        Point point;
        System.out.println(option + " selected.");
        int index = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Room room = MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        switch (option) {
            case "Vrata":
                point=new Point(0,0);
                Door door=new Door(null,room,point,0,0,0);
                if(room.getPendingElement()==null){
                    room.setPendingElement(door);
                }
                break;
                case "Krevet":
                    point=new Point(0,0);
                    Bed bed=new Bed(null,room,point,0,0,0);
                    if(room.getPendingElement()==null){
                        room.setPendingElement(bed);
                    }
                    break;
                case "Sto":
                    point=new Point(0,0);
                    Table table=new Table(null,room,point,0,0,0);
                    if(room.getPendingElement()==null){
                        room.setPendingElement(table);
                    }
                    break;
                case "Ormar":
                    point=new Point(0,0);
                    Closet closet=new Closet(null,room,point,0,0,0);
                    if(room.getPendingElement()==null){
                        room.setPendingElement(closet);
                    }
                    break;
                case "Kada/Tuš kabina":
                    point=new Point(0,0);
                    BathTub bathTub=new BathTub(null,room,point,0,0,0);
                    if(room.getPendingElement()==null){
                        room.setPendingElement(bathTub);
                    }
                    break;
                case "Veš mašina":
                    point=new Point(0,0);
                    WashingMachine washingMachine=new WashingMachine(null,room,point,0,0,0);
                    if(room.getPendingElement()==null){
                        room.setPendingElement(washingMachine);
                    }
                    break;
                case "Bojler":
                    point=new Point(0,0);
                    Boiler boiler=new Boiler(null,room,point,0,0,0);
                    if(room.getPendingElement()==null){
                        room.setPendingElement(boiler);
                    }
                    break;
                case "Lavabo":
                    point=new Point(0,0);
                    Sink sink=new Sink(null,room,point,0,0,0);
                    if(room.getPendingElement()==null){
                        room.setPendingElement(sink);
                    }
                    break;
                case "WC šolja":
                    point=new Point(0,0);
                    ToiletSeat toiletSeat=new ToiletSeat(null,room,point,0,0,0);
                    if(room.getPendingElement()==null){
                        room.setPendingElement(toiletSeat);
                    }
                    break;
                default:
                    break;
        }
    }
}
