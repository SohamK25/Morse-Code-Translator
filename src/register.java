import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class register extends JFrame{
    final private Font mainFont=new Font("Dialog",Font.BOLD,18);
    JTextField tfName;
    JTextField tfUserName;
    JPasswordField tfPassword;
    public void initialize(){
        ImageIcon icon = new ImageIcon("C:\\Users\\soham\\OneDrive\\Documents\\morse code project\\src\\images\\icon.png");
        this.setIconImage(icon.getImage());
        getContentPane().setBackground(Color.decode("#457B9D"));
        JLabel titleLabel = new JLabel("Morse Code Translator");
        JLabel titleLabel1 = new JLabel("Sign In");
        // changes the font size for the label and the font weight
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        titleLabel1.setFont(new Font("Dialog", Font.BOLD, 28));
        // changes the font color of the text to white
        titleLabel.setForeground(Color.WHITE);
        titleLabel1.setForeground(Color.BLACK);
        // centers text (relative to its container's width)
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        // sets the x,y position and the width and height dimensions
        // to make sure that the title aligns to the center of our GUI, we made it the same width
        titleLabel.setBounds(0, 0, 800, 100);
        titleLabel1.setBounds(50, 50, 700, 70);

        JLabel tname=new JLabel("Name");
        tname.setFont(mainFont);
        tname.setForeground(Color.WHITE);

        tfName=new JTextField();
        tfName.setFont(mainFont);

        JLabel tuname=new JLabel("User Name");
        tuname.setFont(mainFont);
        tuname.setForeground(Color.WHITE);

        tfUserName=new JTextField();
        tfUserName.setFont(mainFont);

        JLabel tpassword=new JLabel("Password");
        tpassword.setFont(mainFont);
        tpassword.setForeground(Color.WHITE);

        tfPassword=new JPasswordField();
        tfPassword.setFont(mainFont);

        JPanel userPanel=new JPanel();
        userPanel.setLayout(new GridLayout(0,1,10,10));
        userPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        userPanel.add(titleLabel);
        userPanel.add(titleLabel1);
        userPanel.add(tname);
        userPanel.add(tfName);
        userPanel.add(tuname);
        userPanel.add(tfUserName);
        userPanel.add(tpassword);
        userPanel.add(tfPassword);
        userPanel.setBackground(Color.decode("#457B9D"));

        JButton btnLogin=new JButton("Register");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "password");
            
                    String checkQuery = "SELECT * FROM users WHERE uname = ?";
                    PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
                    checkStatement.setString(1, tfUserName.getText());
            
                    ResultSet resultSet = checkStatement.executeQuery();
            
                    if (resultSet.next()) {
                        // User with the provided username already exists
                        JOptionPane.showMessageDialog(btnLogin, "User Already Exists!");
                    } else {
                        // User doesn't exist, so proceed with registration
                        String insertQuery = "INSERT INTO users(name, uname, password) VALUES (?, ?, ?)";
                        PreparedStatement pr = conn.prepareStatement(insertQuery);
                        pr.setString(1, tfName.getText());
                        pr.setString(2, tfUserName.getText());
                        pr.setString(3, tfPassword.getText());
            
                        int i = pr.executeUpdate();
                        if (i > 0) {
                            JOptionPane.showMessageDialog(btnLogin, "Successfully Registered!!");
                            dispose();
                        }
                        Login l=new Login();
                        l.initialize();
                        dispose();
                        pr.close();
                    }
            
                    checkStatement.close();
                    conn.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            

        });

        
        JButton login=new JButton("Login");
        login.setFont(mainFont);
        login.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
               Login l=new Login();
                    l.initialize();
                    dispose();

            }

        });

        JButton btnCancel=new JButton("Reset");
        btnCancel.setFont(mainFont);
        btnCancel.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
               tfName.setText("");
               tfUserName.setText("");
               tfPassword.setText("");

            }

        });

       JPanel btPanel=new JPanel();
       btPanel.setLayout(new GridLayout(1,2 , 10, 0));
       btPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 100, 50));
       btPanel.add(btnLogin);
       btPanel.add(btnCancel);
       btPanel.setBackground(Color.decode("#457B9D"));
       JLabel msg = new JLabel("Already Registered ?");
        msg.setFont(new Font("Dialog", Font.BOLD, 16));
        msg.setForeground(Color.WHITE);
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        msg.setBounds(0, 0, 700, 10);
        btPanel.add(msg);
        btPanel.add(login);


// form 
        add(userPanel,BorderLayout.NORTH);
        add(btPanel,BorderLayout.SOUTH);



        setTitle("Registration Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800,900);
        setMinimumSize(new DimensionUIResource(350,450));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        register l=new register();
        l.initialize();
    }
    
}
