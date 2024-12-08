package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.*;
import raf.draft.dsw.elements.contoller.ElementsController;
import raf.draft.dsw.gui.swing.ElementDimensionFrame;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.gui.swing.state.addstate.AddStateManager;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddState implements State {
    AddStateManager addStateManager=new AddStateManager();
    private RoomElement roomElement;

    public void startBathtub(){
        addStateManager.setBathTubState();
    }public void startBed(){
        addStateManager.setBedState();
    }public void startBoiler(){
        addStateManager.setBoilerState();
    }public void startCloset(){
        addStateManager.setClosetState();
    }public void startDoor(){
        addStateManager.setDoorState();
    }public void startSink(){
        addStateManager.setSinkState();
    }public void startTable(){
        addStateManager.setTableState();
    }public void startToilet(){
        addStateManager.setToiletState();
    }public void startWashingMachine(){
        addStateManager.setWashingMachineState();
    }


    public void myMouseClickedElement(RoomView roomView,Point location){
        addStateManager.getCurrentState().myMouseClicked(roomView, location);
    }
    public void myMouseReleasedElement(){
        addStateManager.getCurrentState().myMouseReleased();
    }
    public void myMouseScrolledElement(){
        addStateManager.getCurrentState().myMouseScroll();
    }
    public void myMouseDraggedElement(){
        addStateManager.getCurrentState().myMouseDragged();
    }
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomElement=new Bed(null,null,null,0,0,0);
        roomView.getRoom().setPendingElement(roomElement);

        ElementDimensionFrame elementDimensionFrame=new ElementDimensionFrame();
        ElementsController elementsController=new ElementsController();

        elementsController.setLenght(elementDimensionFrame.getLenght());
        elementsController.setWidht(elementDimensionFrame.getWidth());
        elementsController.dimensions(roomView.getRoom().getPendingElement());
        elementsController.location(roomElement,roomView.getMouseLocation());

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

    @Override
    public void myMouseScroll() {

    }

    @Override
    public void myMouseReleased() {

    }

    @Override
    public void myMouseDragged() {

    }
    public void handleOptionSelected(String option) {
        int index = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Room room = MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        switch (option) {
            case "Vrata":
                startDoor();
                roomElement=new Door("Door",room,room.getPendingElement().getLocation(),room.getPendingElement().getDimensionWidth(),room.getPendingElement().getDimensionLenght(),0);
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            case "Krevet":
                startBed();
                roomElement=new Bed("Bed",room,room.getPendingElement().getLocation(),room.getPendingElement().getDimensionWidth(),room.getPendingElement().getDimensionLenght(),0);
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            case "Sto":
                startTable();
                roomElement=new Table("Table",room,room.getPendingElement().getLocation(),room.getPendingElement().getDimensionWidth(),room.getPendingElement().getDimensionLenght(),0);
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            case "Ormar":
                startCloset();
                roomElement=new Closet("Closet",room,room.getPendingElement().getLocation(),room.getPendingElement().getDimensionWidth(),room.getPendingElement().getDimensionLenght(),0);
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            case "Kada/Tuš kabina":
                startBathtub();
                roomElement=new BathTub("BathTub",room,room.getPendingElement().getLocation(),room.getPendingElement().getDimensionWidth(),room.getPendingElement().getDimensionLenght(),0);
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            case "Veš mašina":
                startWashingMachine();
                roomElement=new WashingMachine("WashingMachine",room,room.getPendingElement().getLocation(),room.getPendingElement().getDimensionWidth(),room.getPendingElement().getDimensionLenght(),0);
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            case "Bojler":
                startBoiler();
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            case "Lavabo":
                startSink();
                roomElement=new Sink("Sink",room,room.getPendingElement().getLocation(),room.getPendingElement().getDimensionWidth(),room.getPendingElement().getDimensionLenght(),0);
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            case "WC šolja":
                startToilet();
                roomElement=new ToiletSeat("ToiledSeat",room,room.getPendingElement().getLocation(),room.getPendingElement().getDimensionWidth(),room.getPendingElement().getDimensionLenght(),0);
                addStateManager.getCurrentState().myMouseClicked(room.getRoomView(),room.getRoomView().getMouseLocation());
                break;
            default:
                break;
        }
        room.setPendingElement(roomElement);
    }
    @Override
    public void update(Object o) {

    }
}
