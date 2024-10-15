package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    public AboutUsFrame(){
        initialize();
        addElemnts();
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

    private void addElemnts(){
        JTextPane student1 = new JTextPane();
        JTextPane student2 = new JTextPane();

        student1.setText("Mihajlo Marinkovic 127/24 SI");
        student2.setText("Ilija Ivkovic 92/24 SI");

        JPanel aboutUsPanel = new JPanel();
        aboutUsPanel.setLayout(new BorderLayout());

        aboutUsPanel.add(student1, BorderLayout.NORTH);
        aboutUsPanel.add(student2, BorderLayout.SOUTH);
    }
}
