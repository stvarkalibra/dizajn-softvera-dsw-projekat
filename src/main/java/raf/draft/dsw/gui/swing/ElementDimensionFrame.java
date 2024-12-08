package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;

import javax.swing.*;
import java.awt.*;

public class ElementDimensionFrame extends JFrame {

    Button changeButton=new Button("Set");

    private int lenght;
    private int width;
    public ElementDimensionFrame(){
        addElements();
    }
    public void addElements() {
        JTextField widthField = new JTextField(10);
        JTextField lenghtField = new JTextField(10);

        JLabel labelWidth = new JLabel("Width:");
        JLabel labelLenght = new JLabel("Height:");

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
        panelDimensions.add(labelLenght, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelDimensions.add(lenghtField, gbc);

        int option = JOptionPane.showConfirmDialog(null, panelDimensions, "Enter Element Dimensions", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String widthText = widthField.getText();
                String lenghtText = lenghtField.getText();
                width=Integer.parseInt(widthText);
                lenght=Integer.parseInt(lenghtText);

                dispose();
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

    public Button getChangeButton() {
        return changeButton;
    }

    public void setChangeButton(Button changeButton) {
        this.changeButton = changeButton;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
