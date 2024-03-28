import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashBoards extends JFrame {
    public static String username;
    
    public DashBoards(String username) {
        DashBoards.username=username;
        System.out.print(username);
        // Set up the main frame
        ImageIcon icon = new ImageIcon("C:\\Users\\soham\\OneDrive\\Documents\\morse code project\\src\\images\\icon.png");
        this.setIconImage(icon.getImage());
        setBackground(Color.decode("#457B9D"));

        setTitle("Dashboard");
        setSize(800, 900);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLocationRelativeTo(null);
        

        JLabel titleLabel = new JLabel("Morse Code Translator");
        JLabel titleLabel1 = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        titleLabel1.setFont(new Font("Dialog", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel1.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 800, 100);
        titleLabel1.setBounds(50, 50, 700, 70);
        add(titleLabel);
        add(titleLabel1);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        mainPanel.add(createSidebarPanel(),BorderLayout.WEST); 
        mainPanel.add(SidebarPanel(),BorderLayout.EAST); 
        ImageIcon centerImageIcon = new ImageIcon("C:\\Users\\soham\\OneDrive\\Documents\\morse code project\\src\\images\\mcicon.jpg");
        JLabel centerImageLabel = new JLabel(centerImageIcon);
        centerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(centerImageLabel, BorderLayout.CENTER);
        mainPanel.setBackground(Color.decode("#457B9D"));     
          
        add(mainPanel);
        
        setVisible(true);
        
    }

    

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel,BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.decode("#457B9D"));
        sidebarPanel.setBounds(500, 0,120, 40);
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(100, 1, 1, 1));
        JButton button1 = createButton("Text -> Morse");
        JButton button2 = createButton("Morse -> Text");
        JButton button3 = createButton("History");
        JButton button4 = createButton("Morse Codes");
        JButton button5 = createButton("Admin");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TextToMorse().setVisible(true);
                    }
                    
                });
                dispose();
            }
        });
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new MorseToText().setVisible(true);
                    }
                });
                dispose();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                history l=new history();
                l.initialize(); 
                dispose();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(MorseCodeWindow::new);   
                dispose();
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
                    new TableViewer("users", enteredUsername);
                });
                
            }
        });

        sidebarPanel.add(button1);
        sidebarPanel.add(button2);
        sidebarPanel.add(button3);
        sidebarPanel.add(button4);
        sidebarPanel.add(button5);

        return sidebarPanel;
  

    }

    

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        
        Dimension buttonSize = new Dimension(120, 40);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        return button;
    }

    private JPanel SidebarPanel() {
        JPanel sidebarPanel2 = new JPanel();
        sidebarPanel2.setLayout(new BoxLayout(sidebarPanel2, BoxLayout.Y_AXIS));
        JButton button1 = createButton("Profile");

        
    
        JDialog profileDialog = new JDialog(this, "Profile", true);
        profileDialog.setSize(200, 100);
        profileDialog.setLayout(new BoxLayout(profileDialog.getContentPane(), BoxLayout.Y_AXIS));
        profileDialog.setLocationRelativeTo(this);  
    
        JLabel usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  
    
        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);  
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDialog.dispose();
                new Login().initialize();  
                dispose();
                        
              }
        });
    
        
        profileDialog.add(usernameLabel);
        profileDialog.add(Box.createVerticalStrut(10));  
        profileDialog.add(logoutButton);
    
        profileDialog.getContentPane().setBackground(Color.decode("#457B9D"));
    
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point buttonLocation = button1.getLocationOnScreen();
    
                int potentialX = buttonLocation.x;
                int potentialY = buttonLocation.y + button1.getHeight();
    
                int maxX = getLocationOnScreen().x + getWidth() - profileDialog.getWidth();
                int maxY = getLocationOnScreen().y + getHeight() - profileDialog.getHeight();
    
                int finalX = Math.min(potentialX, maxX);
                int finalY = Math.min(potentialY, maxY);
    
                profileDialog.setLocation(finalX, finalY);
                profileDialog.setVisible(true);
            }
        });
    
        sidebarPanel2.add(button1);
        sidebarPanel2.setBackground(Color.decode("#457B9D"));
        return sidebarPanel2;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashBoards(""));
    }
}

