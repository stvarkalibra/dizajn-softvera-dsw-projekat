package raf.draft.dsw.gui.swing;

import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import java.awt.*;

public class ChangeInfoFrame extends JFrame {
    public ChangeInfoFrame(Project project) {
        initialize();
        addElements(project);
    }

    public void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height/3;
        int screenWidth = screenSize.width/3;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Change Project Information");
    }

    public void addElements(Project project) {
        JTextField authorField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField pathField = new JTextField(10);
        Button changeButton = new Button("Change Info");

        JLabel authorLabel = new JLabel("Author:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel pathLabel = new JLabel("Path:");

        changeButton.addActionListener(e -> {
            project.setProjectAutor(authorField.getText());
            project.setProjectName(nameField.getText());
            project.setPathToFolder(pathField.getText());
            JOptionPane.showMessageDialog(this, "Author changed to: " + authorField.getText() + " Name changed to: " + nameField.getText() + " Path changed to: " + pathField.getText());
            dispose();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(authorLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(pathLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(pathField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(changeButton, gbc);

        setContentPane(panel);
    }
}
