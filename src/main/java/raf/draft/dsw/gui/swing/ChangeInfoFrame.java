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
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2, screenHeight/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Change Project Information");
    }

    public void addElements(Project project) {
        JFrame frame = new JFrame("Change Project Information");

        JTextField authorField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField pathField = new JTextField(20);
        Button changeButton = new Button("Change Info");

        JLabel authorLabel = new JLabel("Author:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel pathLabel = new JLabel("Path:");


        changeButton.addActionListener(e -> {
            project.setProjectAutor(authorField.getText());
            project.setProjectName(nameField.getText());
            project.setPathToFolder(pathField.getText());

            JOptionPane.showMessageDialog(frame, "Author changed to: " + authorField.getText() + " Name changed to: " + nameField.getText() + " Path changed to: " + pathField.getText());
            //System.out.println(project.getProjectAutor() + " " + project.getProjectName() + " " + project.getPathToFolder());
        });
        JPanel panel = new JPanel();

        GridBagConstraints changeInfoGBC = new GridBagConstraints();
        changeInfoGBC.insets = new Insets(5, 5, 5, 5);
        changeInfoGBC.gridx = 0;
        changeInfoGBC.gridy = 0;
        panel.add(authorLabel, changeInfoGBC);
        changeInfoGBC.gridx = 1;
        panel.add(authorField, changeInfoGBC);
        changeInfoGBC.gridy = 1;
        changeInfoGBC.gridx = 0;
        panel.add(nameLabel, changeInfoGBC);
        changeInfoGBC.gridx = 1;
        panel.add(nameField, changeInfoGBC);
        changeInfoGBC.gridy = 2;
        changeInfoGBC.gridx = 0;
        panel.add(pathLabel, changeInfoGBC);
        changeInfoGBC.gridx = 1;
        panel.add(pathField, changeInfoGBC);
        changeInfoGBC.gridy = 3;
        changeInfoGBC.gridx = 0;
        panel.add(changeButton, changeInfoGBC);


        super.setContentPane(panel);

    }
}
