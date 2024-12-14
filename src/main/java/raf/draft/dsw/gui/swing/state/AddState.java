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
import java.awt.event.MouseWheelEvent;

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
    public void myMouseReleasedElement(RoomView roomView){
        addStateManager.getCurrentState().myMouseReleased(roomView);
    }
    public void myMouseScrolledElement(RoomView roomView,MouseWheelEvent e){
        addStateManager.getCurrentState().myMouseScrolled(roomView,e);
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
                    handleOptionSelected(option, roomView,location);
                }
            });
            popupMenu.add(menuItem);
        }
        popupMenu.show(MainFrame.getInstance(), 250, 100);
    }

    @Override
    public void myMouseScrolled(RoomView roomView, MouseWheelEvent event) {
    }

    @Override
    public void myMouseReleased(RoomView roomView) {

    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent mouseEvent) {

    }
    public void handleOptionSelected(String option, RoomView roomView,Point location) {

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
        ElementDimensionFrame elementDimensionFrame = new ElementDimensionFrame();
        if(elementDimensionFrame.getWidth()>room.getWidth() || elementDimensionFrame.getLenght()>room.getHeight()){
            JOptionPane.showMessageDialog(null,"ELEMENT_CANT_FIT_IN_THE_ROOM","ELEMENT_2_BIG",JOptionPane.ERROR_MESSAGE);
            return;
        }
        ElementsController elementsController = new ElementsController(roomView.getK());
        if (elementDimensionFrame.getLenght() != 0 && elementDimensionFrame.getWidth() != 0) {
            elementsController.setLength(elementDimensionFrame.getLenght());
            elementsController.setWidth(elementDimensionFrame.getWidth());

            elementsController.dimensions(roomElement);
            elementsController.location(roomElement, location);

            room.setPendingElement(roomElement);
            room.notifySubscriber(this);
        } else{
            JOptionPane.showMessageDialog(null,"YOU_MUST_FILL_ALL_FIELDS","EMPTY FIElDS",JOptionPane.ERROR_MESSAGE);
        }
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