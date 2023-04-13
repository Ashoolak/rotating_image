// GUI_Panel.java
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class GUI_Panel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;

    Image myImage;
    Timer myTimer;
    int xVelocity = 14;
    int yVelocity = 37;
    int imageWidth;
    int imageHeight;
    double angle = 0;
    double angleStep = 90;
    AffineTransform identity = new AffineTransform();
    Font titleFont = new Font("Arial", Font.BOLD, 24);

    int x = 0, y = 0;

    public GUI_Panel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);

        myImage = new ImageIcon("src/images/Me.jpg").getImage();

        int scaledWidth = (int) Math.floor(myImage.getWidth(this) * 0.05); // Set the desired width
        imageWidth = scaledWidth;
        int scaledHeight = (int) Math.floor(myImage.getHeight(this) * 0.05); // Set the desired height
        imageHeight = scaledHeight;

        Image scaledImage = myImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        myImage = scaledImage;

        myTimer = new Timer(100, this);
        myTimer.restart();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D) g;

        // Draw title
        graphics.setFont(titleFont);
        graphics.setColor(Color.white);

        // Draw image
        AffineTransform trans = graphics.getTransform();
        graphics.translate(x, y);
        graphics.translate(imageWidth / 2, imageHeight / 2);
        graphics.rotate(Math.toRadians(angle));
        graphics.drawImage(myImage, -imageWidth / 2, -imageHeight / 2, this);
        graphics.setTransform(trans);
    }

    public void updateRotation() {
        angle += angleStep;
        repaint();
    }

    public void updateAngle(double deg) {
        angleStep = deg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (x >= PANEL_WIDTH - imageWidth || x < 0) {
            xVelocity *= -1;
        }
        if (y >= PANEL_HEIGHT - imageHeight || y < 0) {
            yVelocity *= -1;
        }
        x += xVelocity;
        y += yVelocity;

        repaint();
    }
}
