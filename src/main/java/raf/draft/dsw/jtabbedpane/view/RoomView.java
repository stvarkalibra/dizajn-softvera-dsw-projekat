package raf.draft.dsw.jtabbedpane.view;

import org.w3c.dom.ls.LSOutput;
import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtabbedpane.controller.RoomController;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoomView extends JPanel implements ISubscriber {
    private JPanel jPanelRoom;
    private Room room;
    private RoomController roomController;
    private int width, height;
    public RoomView(Room room){
        this.room=room;
        room.addSubscriber(this);
        roomController=new RoomController(this,room);
        initialize();
    }

    public void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        jPanelRoom=new JPanel();
        jPanelRoom.setPreferredSize(new Dimension((int)kit.getScreenSize().getWidth(),(int)kit.getScreenSize().getHeight()));
        if(room.getParent() instanceof Building){
            jPanelRoom.setBackground(((Building) room.getParent()).getColor());
        }
        if(room.getParent() instanceof Project){
            jPanelRoom.setBackground(((Project) room.getParent()).getColor());
        }
    }
    public void setDimensions(){
                JTextField widthField = new JTextField(10);
                JTextField heightField = new JTextField(10);

                JLabel labelWidth = new JLabel("Width:");
                JLabel labelHeight = new JLabel("Height:");

                JPanel panelDimensions = new JPanel();

                panelDimensions.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                gbc.gridx = 0;
                gbc.gridy = 1;
                panelDimensions.add(labelWidth, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                panelDimensions.add(widthField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                panelDimensions.add(labelHeight, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                panelDimensions.add(heightField, gbc);

                int option = JOptionPane.showConfirmDialog(null, panelDimensions, "Enter Room Dimensions", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String widthText = widthField.getText();
                        String heightText = heightField.getText();
                        width = Integer.parseInt(widthText);
                        height = Integer.parseInt(heightText);
                        roomController.Dimensions(room);

                    } catch (NumberFormatException ex) {
                        MessageGenerator messageGenerator = new MessageGenerator();
                        FileLogger fileLogger = new FileLogger("files/log.txt");
                        ConsoleLogger consoleLogger = new ConsoleLogger();

                        messageGenerator.addSubscriber(consoleLogger);
                        messageGenerator.addSubscriber(fileLogger);

                        Message message = new Message("Enter valid values", MessageType.ERROR);
                        messageGenerator.createMessage(message.getContent(), message.getType());
                        JOptionPane.showMessageDialog(null, message);
                    }
                }
    }

    @Override
    public void update(Object o) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        if(o instanceof Room){
            if(height>=width){
                width=kit.getScreenSize().height/height*width;
                height=kit.getScreenSize().height;
            }else{
                height=kit.getScreenSize().width/width*height;
                width=kit.getScreenSize().width;
            }
            jPanelRoom.setPreferredSize(new Dimension(width,height));
            jPanelRoom.revalidate();
            jPanelRoom.repaint();
        }
    }

    public JPanel getjPanelRoom() {
        return jPanelRoom;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setjPanelRoom(JPanel jPanelRoom) {
        this.jPanelRoom = jPanelRoom;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomController getRoomController() {
        return roomController;
    }

    public void setRoomController(RoomController roomController) {
        this.roomController = roomController;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
