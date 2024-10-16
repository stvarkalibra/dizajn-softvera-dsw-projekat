package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    public AboutUsFrame(){
        initialize();
        addElements();
    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2, screenHeight/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("About Us");


    }

    public void addElements(){
        JTextPane student1Text = new JTextPane();
        student1Text.setText("Mihajlo Marinkovic 127/24 SI");

        JTextPane student2Text = new JTextPane();
        student2Text.setText("Ilija Ivkovic 92/24 SI");

        ImageIcon student1Image=new ImageIcon(getClass().getResource("/images/exit.png"));
        ImageIcon student2Image=new ImageIcon(getClass().getResource("/images/exit.png"));

        JLabel student1Label=new JLabel(student1Image);
        JLabel student2Label=new JLabel(student2Image);

        JPanel aboutUsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 150, 25, 150);  // Add some padding

        // Place student1 and its image in the grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        aboutUsPanel.add(student1Text, gbc);

        gbc.gridy = 1;  // Move to next row
        aboutUsPanel.add(student1Label, gbc);

        // Place student2 and its image in the grid
        gbc.gridx = 1;
        gbc.gridy=0;// Move to next row
        aboutUsPanel.add(student2Text, gbc);

        gbc.gridy = 1;  // Move to next row
        aboutUsPanel.add(student2Label, gbc);

        // Add the panel to the frame
        super.add(aboutUsPanel, BorderLayout.NORTH);
    }
}
