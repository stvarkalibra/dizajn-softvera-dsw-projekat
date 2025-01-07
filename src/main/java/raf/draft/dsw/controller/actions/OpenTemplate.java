package raf.draft.dsw.controller.actions;

import raf.draft.dsw.elements.*;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.*;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.serializer.Serializer;
import raf.draft.dsw.serializer.TemplateSerializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenTemplate extends AbstractRoomAction{
    private final TemplateSerializer templateSerializer;
    public OpenTemplate(TemplateSerializer templateSerializer) {
        this.templateSerializer=templateSerializer;
        putValue(SMALL_ICON, loadIcon("/images/open_template.png"));
        putValue(NAME, "Open Template");
        putValue(SHORT_DESCRIPTION, "Open Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openTemplate();
    }
    public void openTemplate(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Room Template");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File("src/main/resources/templates"));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));
        Room newRoom=null;
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                newRoom = templateSerializer.loadRoom(selectedFile);
                setParents(newRoom);
                setBoundsForRoomElements(newRoom);
                if (newRoom != null) {
                    System.out.println("Room loaded successfully: " + newRoom.getNodeName());
                } else {
                    System.err.println("Failed to load room.");
                }
            }
        }
        int index= MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Room oldRoom=MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        deleteRoomElementsFromTree(oldRoom);
        oldRoom.getChildrenList().clear();
        oldRoom.getRoomElements().clear();
        oldRoom.getRoomView().getPainterList().clear();

        oldRoom.setWidth(newRoom.getWidth());
        oldRoom.setHeight(newRoom.getHeight());
        oldRoom.setK();
        oldRoom.setChildrenList(newRoom.getChildrenList());
        oldRoom.setRoomElements(newRoom.getRoomElements());
        oldRoom.setNodeName(newRoom.getNodeName());
        oldRoom.getRoomView().setEditStateEntered(true);
        oldRoom.getRoomView().getPainterList().clear();
        MainFrame.getInstance().getActionStateManager().getAddAction().setEnabled(true);
        oldRoom.getRoomView().setK(oldRoom.getK());
        setRoomView(oldRoom);
        setParentForRoomElement(oldRoom);
        addRoomElementsToTree(oldRoom);
        drawAllPainters(oldRoom);
    }
    private void setParents(Room room){
        room.getRoomElements().clear();
        for(DraftNode draftNode: room.getChildrenList()){
            draftNode.setParent(room);
            if(draftNode instanceof RoomElement){
                room.getRoomElements().add((RoomElement) draftNode);
            }
        }
    }
    private void setBoundsForRoomElements(Room room){
        for(RoomElement roomElement:room.getRoomElements()){
            roomElement.setBounds(new Rectangle(roomElement.getLocation().x,roomElement.getLocation().y,
                    roomElement.getDimensionWidth(),roomElement.getDimensionLength()));
        }
    }
    private void setRoomView(Room room){
        room.getRoomView().setK(room.getK());
        room.addSubscriber(room.getRoomView());
        room.notifySubscriber(room);
    }
    private void addRoomElementsToTree(Room room){
        for(RoomElement roomElement:room.getRoomElements()){
            room.getRoomView().addElementToTree(roomElement);
        }
    }private void deleteRoomElementsFromTree(Room room){
        for(RoomElement roomElement:room.getRoomElements()){
            room.getRoomView().deleteElementFromTree(roomElement);
        }
    }
    private void drawAllPainters(Room room){
        for(RoomElement roomElement:room.getRoomElements()){
            roomElement.addSubscriber(room.getRoomView());
            room.getRoomView().getPainterList().add(makePainterForRoomElement(roomElement));
            roomElement.notifySubscriber(roomElement);
        }
        JPanel paintPanel = room.getRoomView().getjPanelRoom();
        Color originalColor = paintPanel.getBackground();
        paintPanel.setLayout(new BorderLayout());
        paintPanel.removeAll();
        paintPanel.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                setBackground(originalColor);
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                for (MyPainter painter : room.getRoomView().getPainterList()) {
                    painter.paint(g2d, painter.getRoomElement().getLocation(), painter.getRoomElement().getDimensionLength() * (int) room.getK(),
                            painter.getRoomElement().getDimensionWidth() * (int) room.getK());
                }
            }
        }, BorderLayout.CENTER);
        paintPanel.revalidate();
        paintPanel.repaint();

    }
    private MyPainter makePainterForRoomElement(RoomElement roomElement){
        MyPainter newPainter = null;
        if (roomElement instanceof Bed){
            newPainter = new BedPainter(roomElement);
        } else if (roomElement instanceof BathTub){
            newPainter = new BathtubPainter(roomElement);
        } else if (roomElement instanceof Boiler){
            newPainter = new BoilerPainter(roomElement);
        } else if (roomElement instanceof Closet){
            newPainter = new ClosetPainter(roomElement);
        } else if (roomElement instanceof Door){
            newPainter = new DoorPainter(roomElement);
        } else if (roomElement instanceof Sink){
            newPainter = new SinkPainter(roomElement);
        }  else if (roomElement instanceof Table){
            newPainter = new TablePainter(roomElement);
        } else if (roomElement instanceof ToiletSeat){
            newPainter = new ToiletPainter(roomElement);
        } else if (roomElement instanceof WashingMachine){
            newPainter = new WashingMachinePainter(roomElement);
        }
        return newPainter;
    }
    private void setParentForRoomElement(Room room){
        for(RoomElement roomElement: room.getRoomElements()){
            roomElement.setParent(room);
        }
    }
}
