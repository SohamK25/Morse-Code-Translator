import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
public class Login extends JFrame{

    final private Font mainFont=new Font("Dialog",Font.BOLD,18);
    JTextField tfUserName;
    JPasswordField tfPassword;
        public void initialize(){
        ImageIcon icon = new ImageIcon("C:\\Users\\soham\\OneDrive\\Documents\\morse code project\\src\\images\\icon.png");
        this.setIconImage(icon.getImage());
        getContentPane().setBackground(Color.decode("#457B9D"));

        JLabel titleLabel = new JLabel("Morse Code Translator");
        JLabel titleLabel1 = new JLabel("Login");
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

        JLabel username=new JLabel("User Name");
        username.setFont(mainFont);
        username.setForeground(Color.WHITE);

        tfUserName=new JTextField();
        tfUserName.setFont(mainFont);

        JLabel password=new JLabel("Password");
        password.setFont(mainFont);
        password.setForeground(Color.WHITE);

        tfPassword=new JPasswordField();
        tfPassword.setFont(mainFont);
        

        JPanel userPanel=new JPanel();
        userPanel.setLayout(new GridLayout(0,1,5,5));
        userPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        userPanel.add(titleLabel);
        userPanel.add(titleLabel1);
        userPanel.add(username);
        userPanel.add(tfUserName);
        userPanel.add(password);
        userPanel.add(tfPassword);
        userPanel.setPreferredSize(new Dimension(5, 300));
        userPanel.setBackground(Color.decode("#457B9D"));

        JButton btnLogin=new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                String uname=tfUserName.getText();
                String password=String.valueOf(tfPassword.getPassword());
                DashBoards.username=uname;
                user u=getAuthenticateduser(uname,password);

                if(u != null)
                {
                    
                    SwingUtilities.invokeLater(() -> new DashBoards(uname));
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(Login.this,"Invalid Username or Password",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        JButton btnCancel=new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                register r=new register();
                r.initialize();
                dispose();
            }

        });

       JPanel btPanel=new JPanel();
       btPanel.setLayout(new GridLayout(1,2 , 10, 10));
       btPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 100, 50));
       btPanel.add(btnLogin);
       btPanel.add(btnCancel);
       btPanel.setBackground(Color.decode("#457B9D"));

// form 
        add(userPanel,BorderLayout.NORTH);
        add(btPanel,BorderLayout.SOUTH);

        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800,900);
        setMinimumSize(new DimensionUIResource(350,450));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private user getAuthenticateduser(String uname , String password)
    {
        user u=null;

        Connection conn;
	    try
	    {
	    Class.forName("org.postgresql.Driver");
	    conn=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","password");

        
            String s="SELECT * FROM users WHERE uname=? AND password=?";

            PreparedStatement pr=conn.prepareStatement(s);
            pr.setString(1, uname);
            pr.setString(2, password);

            ResultSet rs=pr.executeQuery();

            if(rs.next())
            {
                u=new user();
                u.uname=rs.getString("uname");
                u.password=rs.getString("password");
            }

            pr.close();;
            conn.close();

        }catch(Exception e)
        {
            System.out.println("Database Connection Failed!!");
        }
        
        return u;
    }
    public static void main(String[] args) {
        Login l=new Login();
        l.initialize();
    }
    
}


