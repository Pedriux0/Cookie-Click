package userInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Cookie extends JFrame {
    private JTextField counterField;

    public Cookie() {
        super("Cookie Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 800));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        SpringLayout springLayout = new SpringLayout();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(springLayout);

        // 1. Cookie Image Label
        JLabel cookieLabel = ImageLabel("assests/cookieImage.jpg");
        if (cookieLabel != null) {
            cookieLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int counter = Integer.parseInt(counterField.getText());
                    counterField.setText(Integer.toString(++counter));
                }
            });

            jPanel.add(cookieLabel);
            springLayout.putConstraint(SpringLayout.WEST, cookieLabel, 65, SpringLayout.WEST, jPanel);
            springLayout.putConstraint(SpringLayout.NORTH, cookieLabel, 35, SpringLayout.NORTH, jPanel);
        }

        // 4. Counter Field
        counterField = new JTextField(10);
        if (counterField != null) {
            counterField.setFont(new Font("Dialog", Font.BOLD, 26));
            counterField.setHorizontalAlignment(SwingConstants.RIGHT);
            counterField.setText("0");

            jPanel.add(counterField);
        }

        // 3. Counter
        JLabel counterLabel = new JLabel("Cookies + 1");
        if (counterLabel != null) {
            counterLabel.setFont(new Font("Dialog", Font.BOLD, 56));
            jPanel.add(counterLabel);
            springLayout.putConstraint(SpringLayout.WEST, counterLabel, 115, SpringLayout.WEST, jPanel);
            springLayout.putConstraint(SpringLayout.NORTH, counterLabel, 176, SpringLayout.NORTH, jPanel);
        }

        if (jPanel != null) {
            this.getContentPane().add(jPanel);
        }
    }

    private JLabel ImageLabel(String fileName) {
        JLabel label;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            Image image = ImageIO.read(inputStream);
            label = new JLabel(new ImageIcon(image));
            return label;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    private BufferedImage resizeImage(BufferedImage image, int Width, int Height) {
        BufferedImage newImage = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, Width, Height, null);
        graphics2D.dispose();
        return newImage;
    }
}
