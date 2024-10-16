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
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("About Us");


    }

    public void addElements(){
        JTextPane student1 = new JTextPane();
        JTextPane student2 = new JTextPane();

        student1.setText("Mihajlo Marinkovic 127/24 SI");
        student2.setText("Ilija Ivkovic 92/24 SI");

        ImageIcon student1Image=new ImageIcon(getClass().getResource("/images/exit.png"));
        ImageIcon student2Image=new ImageIcon(getClass().getResource("/images/exit.png"));

        JLabel student1Label=new JLabel(student1Image);
        JLabel student2Label=new JLabel(student2Image);

        JPanel aboutUsPanel = new JPanel();
        aboutUsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 50));

        aboutUsPanel.add(student1);
        aboutUsPanel.add(student2);
        aboutUsPanel.add(student1Label);
        aboutUsPanel.add(student2Label);

        super.add(aboutUsPanel, BorderLayout.CENTER);
    }
}
