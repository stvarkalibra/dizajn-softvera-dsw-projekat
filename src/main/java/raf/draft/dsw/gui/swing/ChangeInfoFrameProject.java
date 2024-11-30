package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.structures.Project;

import javax.swing.*;
import java.awt.*;

public class ChangeInfoFrameProject extends JFrame{
    private Button changeButton = new Button("Change Info");
    private String autorName;
    private String projectName;
    private String pathToFolder;
    public ChangeInfoFrameProject(Project project) {
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


        JLabel authorLabel = new JLabel("Author:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel pathLabel = new JLabel("Path:");

        changeButton.addActionListener(e -> {
            autorName=authorField.getText();
            projectName=nameField.getText();
            pathToFolder=pathField.getText();
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

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPathToFolder() {
        return pathToFolder;
    }

    public void setPathToFolder(String pathToFolder) {
        this.pathToFolder = pathToFolder;
    }

    public Button getChangeButton() {
        return changeButton;
    }
    public void setChangeButton(Button changeButton) {
        this.changeButton = changeButton;
    }
}
