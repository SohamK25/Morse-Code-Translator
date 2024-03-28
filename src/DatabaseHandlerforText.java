import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseHandlerforText {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    public void saveTranslationToDatabase(String inputText, String morseCode) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO translation_history(text, morse_code) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputText);
            preparedStatement.setString(2, morseCode);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
