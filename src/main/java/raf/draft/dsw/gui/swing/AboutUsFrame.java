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

    private class BackgroundPanel extends JPanel {
        private Image backGroundImage;

        public BackgroundPanel(Image backGroundImage) {
            this.backGroundImage = backGroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backGroundImage != null) {
                g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public void addElements(){
        ImageIcon backGroundImageIcon = new ImageIcon(getClass().getResource("/images/wall1.jpeg"));
        Image backGroundImage = backGroundImageIcon.getImage();

        BackgroundPanel backGroundPanel = new BackgroundPanel(backGroundImage);
        backGroundPanel.setLayout(new GridBagLayout());

        JTextPane student1Text = new JTextPane();
        student1Text.setText("Mihajlo Marinkovic 127/24 SI");

        JTextPane student2Text = new JTextPane();
        student2Text.setText("Ilija Ivkovic 92/24 SI");

        ImageIcon student1Image = new ImageIcon(getClass().getResource("/images/mihajlo_marinkovic.png"));
        ImageIcon student2Image = new ImageIcon(getClass().getResource("/images/ilija_ivkovic.png"));

        JLabel student1Label = new JLabel(student1Image);
        JLabel student2Label = new JLabel(student2Image);

        GridBagConstraints aboutUsGbc = new GridBagConstraints();
        aboutUsGbc.insets = new Insets(25, 150, 25, 150);

        aboutUsGbc.gridx = 0;
        aboutUsGbc.gridy = 0;
        backGroundPanel.add(student1Text, aboutUsGbc);

        aboutUsGbc.gridy = 1;
        backGroundPanel.add(student1Label, aboutUsGbc);

        aboutUsGbc.gridx = 1;
        aboutUsGbc.gridy = 0;
        backGroundPanel.add(student2Text, aboutUsGbc);

        aboutUsGbc.gridy = 1;
        backGroundPanel.add(student2Label, aboutUsGbc);

        super.setContentPane(backGroundPanel);
    }
}
