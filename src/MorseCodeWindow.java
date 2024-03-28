import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MorseCodeWindow extends JFrame {

    private JTextArea morseCodeTextArea;

    private static final Map<Character, String> morseCodeMap = new HashMap<>();

    static {
        // Populate Morse code map for A to Z
        morseCodeMap.put('A', ".-");
        morseCodeMap.put('B', "-...");
        morseCodeMap.put('C', "-.-.");
        morseCodeMap.put('D', "-..");
        morseCodeMap.put('E', ".");
        morseCodeMap.put('F', "..-.");
        morseCodeMap.put('G', "--.");
        morseCodeMap.put('H', "....");
        morseCodeMap.put('I', "..");
        morseCodeMap.put('J', ".---");
        morseCodeMap.put('K', "-.-");
        morseCodeMap.put('L', ".-..");
        morseCodeMap.put('M', "--");
        morseCodeMap.put('N', "-.");
        morseCodeMap.put('O', "---");
        morseCodeMap.put('P', ".--.");
        morseCodeMap.put('Q', "--.-");
        morseCodeMap.put('R', ".-.");
        morseCodeMap.put('S', "...");
        morseCodeMap.put('T', "-");
        morseCodeMap.put('U', "..-");
        morseCodeMap.put('V', "...-");
        morseCodeMap.put('W', ".--");
        morseCodeMap.put('X', "-..-");
        morseCodeMap.put('Y', "-.--");
        morseCodeMap.put('Z', "--..");

        morseCodeMap.put('0', "-----");
        morseCodeMap.put('1', ".----");
        morseCodeMap.put('2', "..---");
        morseCodeMap.put('3', "...--");
        morseCodeMap.put('4', "....-");
        morseCodeMap.put('5', ".....");
        morseCodeMap.put('6', "-....");
        morseCodeMap.put('7', "--...");
        morseCodeMap.put('8', "---..");
        morseCodeMap.put('9', "----.");

        morseCodeMap.put(' ', "/");
        morseCodeMap.put(',', "--..--");
        morseCodeMap.put('.', ".-.-.-");
        morseCodeMap.put('?', "..--..");
        morseCodeMap.put(';', "-.-.-.");
        morseCodeMap.put(':', "---...");
        morseCodeMap.put('(', "-.--.");
        morseCodeMap.put(')', "-.--.-");
        morseCodeMap.put('[', "-.--.");
        morseCodeMap.put(']', "-.--.-");
        morseCodeMap.put('{', "-.--.");
        morseCodeMap.put('}', "-.--.-");
        morseCodeMap.put('+', ".-.-.");
        morseCodeMap.put('-', "-....-");
        morseCodeMap.put('_', "..--.-");
        morseCodeMap.put('"', ".-..-.");
        morseCodeMap.put('\'', ".----.");
        morseCodeMap.put('/', "-..-.");
        morseCodeMap.put('\\', "-..-.");
        morseCodeMap.put('@', ".--.-.");
        morseCodeMap.put('=', "-...-");
        morseCodeMap.put('!', "-.-.--");
    }

    private static final int LETTER_FONT_SIZE = 17;
    //private static final int MORSE_CODE_FONT_SIZE = 16;

    public MorseCodeWindow() {
        // Set window title
        ImageIcon icon = new ImageIcon("C:\\Users\\soham\\OneDrive\\Documents\\morse code project\\src\\images\\icon.png");
        this.setIconImage(icon.getImage());
        setBackground(Color.decode("#457B9D"));
        JLabel titleLabel = new JLabel("Morse Code Translator");
        JLabel titleLabel1 = new JLabel("Morse Codes");
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

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 20, 80, 30);
        backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> new DashBoards(""));
            dispose();
        }
        });
        
        // add to GUI
        add(backButton);
        // Initialize components
        morseCodeTextArea = new JTextArea();
        morseCodeTextArea.setEditable(false);

        // Set layout manager
        setLayout(new BorderLayout());

        // Add panels for Letters, Numbers, and Symbols
        addPanels();
        

        // Add text area to the frame
        add(new JScrollPane(morseCodeTextArea), BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 900);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
        
    }

    private void addPanels() {
        // Panel for Letters
        JPanel lettersPanel = createPanel('A', 'Z', "Letters");
        lettersPanel.setBorder(BorderFactory.createMatteBorder(100, 1, 0, 1, Color.decode("#457B9D")));
        lettersPanel.setBackground(Color.decode("#457B9D"));
        // Panel for Numbers
        JPanel numbersPanel = createPanel('0', '9', "Numbers");
        numbersPanel.setBorder(BorderFactory.createMatteBorder(100, 1, 0, 1, Color.decode("#457B9D")));
        numbersPanel.setBackground(Color.decode("#457B9D"));
        // Panel for Symbols
        JPanel symbolsPanel = createSymbolPanel("Symbols");
        symbolsPanel.setBorder(BorderFactory.createMatteBorder(100, 1, 0, 1, Color.decode("#457B9D")));
        symbolsPanel.setBackground(Color.decode("#457B9D"));
        // Add panels to the frame
        JPanel panelsContainer = new JPanel(new GridLayout(1, 3));
        panelsContainer.add(lettersPanel);
        panelsContainer.add(numbersPanel);
        panelsContainer.add(symbolsPanel);
        panelsContainer.setBackground(Color.decode("#457B9D"));
        add(panelsContainer, BorderLayout.NORTH);
        
    }

    private JPanel createPanel(char start, char end, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(Color.decode("#457B9D"));
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, LETTER_FONT_SIZE));
        area.setEditable(false);
        area.setBackground(Color.decode("#457B9D"));
        for (char letter = start; letter <= end; letter++) {
            String morseCode = morseCodeMap.get(letter);
            if (morseCode != null) {
                area.append(letter + ": " + morseCode + "\n");
            }
        }

        panel.add(new JScrollPane(area), BorderLayout.CENTER);

        return panel;
    }
    private JPanel createSymbolPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(Color.decode("#457B9D"));
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, LETTER_FONT_SIZE));
        area.setEditable(false);
        area.setBackground(Color.decode("#457B9D"));
        
        // Print all Morse codes for symbols
        for (Map.Entry<Character, String> entry : morseCodeMap.entrySet()) {
            char symbol = entry.getKey();
            String morseCode = entry.getValue();
            if (!Character.isLetterOrDigit(symbol) && !Character.isWhitespace(symbol)) {
                area.append(symbol + ": " + morseCode + "\n");
            }
        }

        panel.add(new JScrollPane(area), BorderLayout.CENTER);

        return panel;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MorseCodeWindow::new);
    }
}
