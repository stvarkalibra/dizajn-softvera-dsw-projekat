package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.*;
import raf.draft.dsw.elements.contoller.ElementsController;
import raf.draft.dsw.gui.swing.ElementDimensionFrame;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.state.addstate.AddStateManager;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

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
        addStateManager.getCurrentState().myMouseScrolled();
    }
    public void myMouseDraggedElement(){
        addStateManager.getCurrentState().myMouseDragged(null,null);
    }
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {


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
                    handleOptionSelected(option, roomView);
                }
            });
            popupMenu.add(menuItem);
        }
        popupMenu.show(MainFrame.getInstance(), 250, 100);
    }

    @Override
    public void myMouseScrolled() {

    }

    @Override
    public void myMouseReleased() {

    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent mouseEvent) {

    }
    public void handleOptionSelected(String option, RoomView roomView) {

        int index = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Room room = MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        switch (option) {
            case "Vrata":
                startDoor();
                roomElement=new Door("Door",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            case "Krevet":
                startBed();
                roomElement=new Bed("Bed",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            case "Sto":
                startTable();
                roomElement=new Table("Table",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            case "Ormar":
                startCloset();
                roomElement=new Closet("Closet",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            case "Kada/Tuš kabina":
                startBathtub();
                roomElement=new BathTub("Bath Tub",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            case "Veš mašina":
                startWashingMachine();
                roomElement=new WashingMachine("Washing Machine",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            case "Bojler":
                startBoiler();
                roomElement=new Boiler("Boiler",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            case "Lavabo":
                startSink();
                roomElement=new Sink("Sink",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            case "WC šolja":
                startToilet();
                roomElement=new ToiletSeat("Toilet",room,room.getRoomView().getMouseLocation(),0,0,0);
                break;
            default:
                break;
        }

        ElementDimensionFrame elementDimensionFrame=new ElementDimensionFrame();
        ElementsController elementsController=new ElementsController();

        elementsController.setLength(elementDimensionFrame.getLenght());
        elementsController.setWidth(elementDimensionFrame.getWidth());
        elementsController.dimensions(roomElement);
        elementsController.location(roomElement,roomView.getMouseLocation());

        room.setPendingElement(roomElement);
        room.notifySubscriber(this);
    }
    @Override
    public void update(Object o) {
    }

    public AddStateManager getAddStateManager() {
        return addStateManager;
    }

    public void setAddStateManager(AddStateManager addStateManager) {
        this.addStateManager = addStateManager;
    }
}