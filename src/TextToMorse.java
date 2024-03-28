import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextToMorse extends JFrame implements KeyListener {
    private TTMController morseCodeController;
    private DatabaseHandlerforText databaseHandler;

    private JTextArea textInputArea, morseCodeArea;

    public TextToMorse() {
        super("Morse Code Translator");
        setSize(new Dimension(800, 900));
        setResizable(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#457B9D"));
        setLocationRelativeTo(null);

        morseCodeController = new TTMController();
        databaseHandler = new DatabaseHandlerforText();
        addGuiComponents();
    }

    private void addGuiComponents() {
        ImageIcon icon = new ImageIcon("C:\\Users\\soham\\OneDrive\\Documents\\morse code project\\src\\images\\icon.png");
        this.setIconImage(icon.getImage());
        JLabel titleLabel = new JLabel("Morse Code Translator");
        JLabel titleLabel1 = new JLabel("Text to Morse Code");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        titleLabel1.setFont(new Font("Dialog", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel1.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 800, 100);
        titleLabel1.setBounds(50, 50, 700, 70);

        JLabel textInputLabel = new JLabel("Text:");
        textInputLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        textInputLabel.setForeground(Color.WHITE);
        textInputLabel.setBounds(20, 100, 200, 30);

        textInputArea = new JTextArea();
        textInputArea.setFont(new Font("Dialog", Font.PLAIN, 18));
        textInputArea.addKeyListener(this);
        textInputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textInputArea.setLineWrap(true);
        textInputArea.setWrapStyleWord(true);
        JScrollPane textInputScroll = new JScrollPane(textInputArea);
        textInputScroll.setBounds(20, 132, 744, 236);

        JLabel morseCodeInputLabel = new JLabel("Morse Code:");
        morseCodeInputLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        morseCodeInputLabel.setForeground(Color.WHITE);
        morseCodeInputLabel.setBounds(20, 390, 200, 30);

        morseCodeArea = new JTextArea();
        morseCodeArea.setFont(new Font("Dialog", Font.PLAIN, 18));
        morseCodeArea.setEditable(false);
        morseCodeArea.setLineWrap(true);
        morseCodeArea.setWrapStyleWord(true);
        morseCodeArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane morseCodeScroll = new JScrollPane(morseCodeArea);
        morseCodeScroll.setBounds(20, 430, 744, 236);

        JButton playSoundButton = new JButton("Play Sound");
        playSoundButton.setBounds(335, 680, 100, 30);
        playSoundButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                playSound();
            }
        });

        JButton btnTranslate = new JButton("Translate");
        btnTranslate.setBounds(150, 680, 100, 30);
        btnTranslate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                translateAndSave();
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(520, 680, 100, 30);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

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
        
        add(backButton);
        add(titleLabel);
        add(titleLabel1);
        add(textInputLabel);
        add(textInputScroll);
        add(morseCodeInputLabel);
        add(morseCodeScroll);
        add(playSoundButton);
        add(btnTranslate);
        add(btnReset);
    }

    private void playSound() {
        try {
            String[] morseCodeMessage = morseCodeArea.getText().split(" ");
            morseCodeController.playSound(morseCodeMessage);
        } catch (LineUnavailableException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private void translateAndSave() {
        String inputText = textInputArea.getText();
        String morseCode = morseCodeController.translateToMorse(inputText);
        morseCodeArea.setText(morseCode);
        databaseHandler.saveTranslationToDatabase(inputText, morseCode);
    }

    private void reset() {
        textInputArea.setText("");
        morseCodeArea.setText("");
    }

    // private void backToDashboard() {
    //     SwingUtilities.invokeLater(() -> new DashBoards(""));
    //     dispose();
    // }

    @Override
    public void keyTyped(KeyEvent e) {
        // ...
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // ...
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_SHIFT) {
            //translateAndSave();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextToMorse());
    }
}
