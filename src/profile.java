import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class profile extends JFrame {

    public profile() {
        setTitle("Profile Example");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileDialog();
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(profileButton, BorderLayout.NORTH);

        add(mainPanel);

        setVisible(true);
    }

    private void showProfileDialog() {
        JDialog profileDialog = new JDialog(this, "Profile Details", true);
        profileDialog.setSize(300, 200);
        profileDialog.setLocationRelativeTo(null);

        // Add profile details to the dialog
        JPanel profilePanel = new JPanel(new GridLayout(2, 2));
        profilePanel.add(new JLabel("Name: "));
        profilePanel.add(new JTextField(10)); // You can replace this with the actual name
        profilePanel.add(new JLabel("Age: "));
        profilePanel.add(new JTextField(10)); // You can replace this with the actual age

        profileDialog.add(profilePanel, BorderLayout.EAST);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDialog.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        profileDialog.add(buttonPanel, BorderLayout.SOUTH);

        profileDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(profile::new);
    }
}
