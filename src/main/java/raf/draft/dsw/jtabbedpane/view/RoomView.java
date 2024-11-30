package raf.draft.dsw.jtabbedpane.view;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoomView extends JPanel {
    JPanel jPanelRoom;
    Room room;
    int width, height;
    Label nesto = new Label("aaa");
    public RoomView(JPanel jPanelRoom) {
        this.jPanelRoom = jPanelRoom;
        jPanelRoom.add(nesto);


        jPanelRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

                        System.out.println("Width: " + width + ", Height: " + height);
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
        });
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
}
