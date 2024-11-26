package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;

import javax.swing.*;
import java.awt.*;

public class ChangeInfoFrame extends JFrame implements ISubscriber {
    private Button changeButton = new Button("Change Info");
    public ChangeInfoFrame(DraftNode node) {
        initialize();
        addElements(node);
    }

    public void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height/3;
        int screenWidth = screenSize.width/3;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Change Information");
    }

    private String newName;
    private JTextField nameField;

    public void addElements(DraftNode node) {

        nameField = new JTextField(10);

        JLabel nameLabel = new JLabel("Name:");

        changeButton.addActionListener(e -> {
            newName = nameField.getText();
            node.setNodeName(newName);
            dispose();
        });


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(changeButton, gbc);

        setContentPane(panel);
    }

    public Button getChangeButton() {
        return changeButton;
    }

    public void setChangeButton(Button changeButton) {
        this.changeButton = changeButton;
    }

    public String getNewName() {
        return newName;
    }

    @Override
    public void update(Object o) {
        if(o instanceof Project){
            MainFrame.getInstance().getLabelAutor().setText(((Project) o).getProjectAutor());
        }

    }

}
