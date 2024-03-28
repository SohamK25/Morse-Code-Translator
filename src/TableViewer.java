import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TableViewer extends JFrame {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    private JTable table;

    public TableViewer(String tableName, String enteredUsername) {
        getContentPane().setBackground(Color.decode("#457B9D"));
        if ("soham kulkarni".equals(enteredUsername)) {
            initialize(tableName);
        } else {
            showInvalidUsernameDialog();
            setBackground(Color.decode("#457B9D"));
            dispose();
        } 
    }
        private void initialize(String tableName) {
        setTitle("Table Viewer");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Setting the icon for the JFrame
        ImageIcon icon = new ImageIcon("C:\\Users\\soham\\OneDrive\\Documents\\morse code project\\src\\images\\icon.png");
        this.setIconImage(icon.getImage());

        // Creating a title label
        JLabel titleLabel = new JLabel("Morse Code Translator");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 800, 100);
        add(titleLabel);

        // Creating another label for "Text to Morse Code"
        JLabel titleLabel1 = new JLabel("Text to Morse Code Translation History");
        titleLabel1.setFont(new Font("Dialog", Font.BOLD, 28));
        titleLabel1.setForeground(Color.BLACK);
        titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel1.setBounds(50, 50, 700, 70);
        add(titleLabel1);

        // Creating a table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("User ID");
        model.addColumn("Name");
        model.addColumn("User Name");
        model.addColumn("Password");

        // Creating a JTable with the model
        table = new JTable(model);
        table.setBackground(Color.decode("#457B9D"));
        //table.header.setBackground(Color.DARK_GRAY);
        // Adding the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 150, 700, 200);
        scrollPane.setPreferredSize(new Dimension(700, (model.getRowCount() + 1) * table.getRowHeight()));
        getContentPane().add(scrollPane);
        scrollPane.setBackground(Color.decode("#457B9D"));
        getContentPane().setBackground(Color.decode("#457B9D"));
        // Populating the table with data from the database
        populateTable();
        getContentPane().setBackground(Color.decode("#457B9D"));

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 20, 80, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //backToDashboard();
                SwingUtilities.invokeLater(() -> new DashBoards(""));
                dispose();
            }
        });
        // Displaying the frame
        add(backButton);
        setResizable(true);
        setLayout(null);// Setting layout to null for absolute positioning
        setVisible(true);
    }

     private void showInvalidUsernameDialog() {
        JDialog dialog = new JDialog(this, "Invalid Username", true);
        dialog.setLayout(new BorderLayout());
        dialog.setBackground(Color.decode("#457B9D"));
        JLabel label = new JLabel("Invalid username. This window will not open.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.RED);
        label.setBackground(Color.decode("#457B9D"));
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
     JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       
        panel.setBackground(Color.decode("#457B9D"));
        dialog.add(label, BorderLayout.CENTER);
        dialog.add(panel, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    private void populateTable() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                String id = resultSet.getString("uid");
                String name = resultSet.getString("name");
                String uname = resultSet.getString("uname");
                String pass = resultSet.getString("password");

                // Adding a new row to the table
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{id, name, uname, pass});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
            new TableViewer("users", enteredUsername);
        });
    }
}

