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

        JFrame aboutUsFrame=new JFrame();

        JTextPane student1Text = new JTextPane();
        student1Text.setText("Mihajlo Marinkovic 127/24 SI");

        JTextPane student2Text = new JTextPane();
        student2Text.setText("Ilija Ivkovic 92/24 SI");

        ImageIcon student1Image=new ImageIcon(getClass().getResource("/images/exit.png"));
        ImageIcon student2Image=new ImageIcon(getClass().getResource("/images/exit.png"));

        JLabel student1Label=new JLabel(student1Image);
        JLabel student2Label=new JLabel(student2Image);

        JPanel aboutUsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints aboutUsGbc = new GridBagConstraints();
        aboutUsGbc.insets = new Insets(25, 150, 25, 150);

        aboutUsGbc.gridx = 0;
        aboutUsGbc.gridy = 0;
        aboutUsPanel.add(student1Text, aboutUsGbc);

        aboutUsGbc.gridy = 1;
        aboutUsPanel.add(student1Label, aboutUsGbc);

        aboutUsGbc.gridx = 1;
        aboutUsGbc.gridy=0;
        aboutUsPanel.add(student2Text, aboutUsGbc);

        aboutUsGbc.gridy = 1;
        aboutUsPanel.add(student2Label, aboutUsGbc);

        aboutUsFrame.add(aboutUsPanel);

        super.add(aboutUsPanel, BorderLayout.NORTH);
    }
}
