// MyFrame.java
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    GUI_Panel panel;
    JButton rotateButton;
    JLabel angleLabel;
    JTextField angleField;
    JButton angleButton;

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Rotating Image Demo");
        this.setLocationRelativeTo(null);

        panel = new GUI_Panel();
        JPanel sidePanel = new JPanel();

        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.black);

        sidePanel.add(Box.createRigidArea(new Dimension(0, 5)));

        angleLabel = new JLabel("Rotation Angle");
        angleLabel.setForeground(Color.WHITE);
        angleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        angleField = new JTextField();
        angleField.setFont(new Font("Arial", Font.BOLD, 14));
        angleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

        sidePanel.add(angleLabel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 2)));
        sidePanel.add(angleField);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));

        angleButton = new JButton("Enter");
        angleButton.setFont(new Font("Arial", Font.BOLD, 14));
        angleButton.setFocusPainted(false);
        angleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double angle = Double.parseDouble((angleField.getText().trim()));
                panel.updateAngle(angle);
            }
        });

        sidePanel.add(angleButton);

        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        rotateButton = new JButton("Rotate");
        rotateButton.setFont(new Font("Arial", Font.BOLD, 14));
        rotateButton.setFocusPainted(false);
        rotateButton.addActionListener(e -> panel.updateRotation());

        sidePanel.add(rotateButton);

        JPanel separatorPanel = new JPanel();
        separatorPanel.setBackground(Color.WHITE);
        separatorPanel.setMaximumSize(new Dimension(1, Integer.MAX_VALUE));

        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.WEST);
        container.add(separatorPanel, BorderLayout.CENTER); // Add the separator panel between panel and sidePanel
        container.add(sidePanel, BorderLayout.EAST);

        this.add(container);
        this.pack();
        this.setVisible(true);
    }
}
