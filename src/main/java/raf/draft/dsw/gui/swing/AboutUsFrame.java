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
        ImageIcon backGroundImageIcon = new ImageIcon(getClass().getResource("/images/wall.jpeg"));
        Image backGroundImage = backGroundImageIcon.getImage();

        BackgroundPanel backGroundPanel = new BackgroundPanel(backGroundImage);
        backGroundPanel.setLayout(new GridBagLayout());

        JLabel student1NameLabel = new JLabel();
        student1NameLabel.setText("Mihajlo Marinkovic 127/24 SI");

        JLabel student2NameLabel = new JLabel();
        student2NameLabel.setText("Ilija Ivkovic 92/24 SI");

        ImageIcon student1Image = new ImageIcon(getClass().getResource("/images/mihajlo_marinkovic.png"));
        ImageIcon student2Image = new ImageIcon(getClass().getResource("/images/ilija_ivkovic.png"));

        JLabel student1IconLabel = new JLabel(student1Image);
        JLabel student2IconLabel = new JLabel(student2Image);

        Font font=new Font("Courier", Font.BOLD,20);

        student1NameLabel.setOpaque(false);
        student1NameLabel.setForeground(Color.PINK);
        student1NameLabel.setFont(font);


        student2NameLabel.setOpaque(false);
        student2NameLabel.setForeground(Color.PINK);
        student2NameLabel.setFont(font);

        GridBagConstraints aboutUsGbc = new GridBagConstraints();
        aboutUsGbc.insets = new Insets(25, 150, 25, 150);

        aboutUsGbc.gridx = 0;
        aboutUsGbc.gridy = 0;
        backGroundPanel.add(student1NameLabel, aboutUsGbc);

        aboutUsGbc.gridy = 1;
        backGroundPanel.add(student1IconLabel, aboutUsGbc);

        aboutUsGbc.gridx = 1;
        aboutUsGbc.gridy = 0;
        backGroundPanel.add(student2NameLabel, aboutUsGbc);

        aboutUsGbc.gridy = 1;
        backGroundPanel.add(student2IconLabel, aboutUsGbc);

        super.setContentPane(backGroundPanel);
    }
}
