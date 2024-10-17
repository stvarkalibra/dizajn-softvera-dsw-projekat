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
        private Image bgImage;

        public BackgroundPanel(Image bgImage) {
            this.bgImage = bgImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bgImage != null) {
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public void addElements(){
        ImageIcon bgImageIcon = new ImageIcon(getClass().getResource("/images/knezevac.png"));
        Image bgImage = bgImageIcon.getImage();

        BackgroundPanel bgPanel = new BackgroundPanel(bgImage);
        bgPanel.setLayout(new GridBagLayout());

        JTextPane student1Text = new JTextPane();
        student1Text.setText("Mihajlo Marinkovic 127/24 SI");

        JTextPane student2Text = new JTextPane();
        student2Text.setText("Ilija Ivkovic 92/24 SI");

        ImageIcon student1Image = new ImageIcon(getClass().getResource("/images/exit.png"));
        ImageIcon student2Image = new ImageIcon(getClass().getResource("/images/exit.png"));

        JLabel student1Label = new JLabel(student1Image);
        JLabel student2Label = new JLabel(student2Image);

        GridBagConstraints aboutUsGbc = new GridBagConstraints();
        aboutUsGbc.insets = new Insets(25, 150, 25, 150);

        aboutUsGbc.gridx = 0;
        aboutUsGbc.gridy = 0;
        bgPanel.add(student1Text, aboutUsGbc);

        aboutUsGbc.gridy = 1;
        bgPanel.add(student1Label, aboutUsGbc);

        aboutUsGbc.gridx = 1;
        aboutUsGbc.gridy = 0;
        bgPanel.add(student2Text, aboutUsGbc);

        aboutUsGbc.gridy = 1;
        bgPanel.add(student2Label, aboutUsGbc);

        super.setContentPane(bgPanel);
    }
}
