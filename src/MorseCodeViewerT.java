import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MorseCodeViewerT extends JFrame {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    private JTable table;

    public MorseCodeViewerT() {
        setTitle("Morse Code Viewer");
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
        model.addColumn("Text");
        model.addColumn("Morse Code");

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
                SwingUtilities.invokeLater(() -> new history());
                dispose();
            }
        });
        // Displaying the frame
        add(backButton);
        setResizable(true);
        setLayout(null);// Setting layout to null for absolute positioning
        setVisible(true);
    }

    private void populateTable() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT text, morse_code FROM translation_history");

            while (resultSet.next()) {
                String text = resultSet.getString("text");
                String morseCode = resultSet.getString("morse_code");

                // Adding a new row to the table
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{text, morseCode});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MorseCodeViewerT());
    }
}
