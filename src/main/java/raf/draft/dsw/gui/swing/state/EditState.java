package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.commandPattern.EditCommand;
import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class EditState implements State{
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        for(RoomElement roomElement:roomView.getRoom().getRoomElements()){
            if(roomElement.getBounds().contains(location)){
                int oldWidth= roomElement.getDimensionWidth();
                int oldLength=roomElement.getDimensionLength();
                int oldRotation=roomElement.getRotation();
                int newWidth=oldWidth;
                int newLength=oldLength;
                int newRotation=oldRotation;

                JTextField widthField = new JTextField(10);
                JTextField heightField = new JTextField(10);
                JTextField rotationField = new JTextField(10);

                JLabel labelWidth = new JLabel("Element width:");
                JLabel labelHeight = new JLabel("Element height:");
                JLabel labelRotation = new JLabel("Element Rotation:");

                JPanel panelDimensions = new JPanel();

                panelDimensions.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                gbc.gridx = 0;
                gbc.gridy = 0;
                panelDimensions.add(labelWidth, gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                panelDimensions.add(widthField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                panelDimensions.add(labelHeight, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                panelDimensions.add(heightField, gbc);

                gbc.gridx=0;
                gbc.gridy=2;
                panelDimensions.add(labelRotation,gbc);
                gbc.gridx=1;
                gbc.gridy=2;
                panelDimensions.add(rotationField,gbc);

                int option = JOptionPane.showConfirmDialog(null, panelDimensions, "Change Element", JOptionPane.OK_CANCEL_OPTION);
                roomElement.addSubscriber(roomView);
                if(option==0){
                    try {
                        if (!heightField.getText().isEmpty()) {
                             newLength=Integer.parseInt(heightField.getText());
                            roomElement.setDimensionLength(Integer.parseInt(heightField.getText()),roomView.getK());
                        }
                        if (!widthField.getText().isEmpty()) {
                             newWidth=Integer.parseInt(widthField.getText());
                            roomElement.setDimensionWidth(Integer.parseInt(heightField.getText()), roomView.getK());
                        }
                        if (!rotationField.getText().isEmpty()) {
                             newRotation=Integer.parseInt(rotationField.getText());
                            roomElement.setRotation(Integer.parseInt(rotationField.getText()));
                        }
                    }catch (NumberFormatException ex) {
                        MessageGenerator messageGenerator = new MessageGenerator();
                        FileLogger fileLogger = new FileLogger("files/log.txt");
                        ConsoleLogger consoleLogger = new ConsoleLogger();

                        messageGenerator.addSubscriber(consoleLogger);
                        messageGenerator.addSubscriber(fileLogger);

                        Message message = new Message("Enter valid values", MessageType.ERROR);
                        messageGenerator.createMessage(message.getContent(), message.getType());
                        JOptionPane.showMessageDialog(null, message);
                    }
                    EditCommand editCommand=new EditCommand(roomView,oldLength,oldWidth,oldRotation,newLength,newWidth,newRotation,roomElement);
                    roomView.getCommandManager().addCommand(editCommand);
                    roomView.getjPanelRoom().revalidate();
                    roomView.getjPanelRoom().repaint();
                }
            }
        }
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
    @Override
    public void update(Object o) {

    }
}
