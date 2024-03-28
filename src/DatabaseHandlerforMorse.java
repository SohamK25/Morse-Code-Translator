import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseHandlerforMorse {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    public void saveTranslation(String inputMorse,String text) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            String query = "INSERT INTO translation_historyMorse(morse_code,text) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputMorse);
            preparedStatement.setString(2, text);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
