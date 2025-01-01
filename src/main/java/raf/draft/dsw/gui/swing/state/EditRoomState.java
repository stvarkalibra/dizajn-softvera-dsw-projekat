package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class EditRoomState implements State{
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
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
                roomView.getRoom().setHeight(Integer.parseInt(heightText));
                roomView.getRoom().setWidth(Integer.parseInt(widthText));
                roomView.getRoom().addSubscriber(roomView);
                roomView.getRoom().notifySubscriber(roomView.getRoom());
                roomView.setEditStateEntered(true);
                MainFrame.getInstance().getActionStateManager().getAddAction().setEnabled(true);
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
