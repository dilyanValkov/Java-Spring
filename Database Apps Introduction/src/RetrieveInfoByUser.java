import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class RetrieveInfoByUser {
    private static final String FIND_INFO_BY_USER = """
            SELECT users.first_name, users.last_name,COUNT(ug.user_id) AS 'count' FROM users
            JOIN diablo.users_games ug on users.id = ug.user_id
            WHERE last_name = ?
            GROUP BY ug.user_id;""";
    private static final String NO_SUCH_USER_STATEMENT = "No such user exist";
    private static final String PRINT_USER_NAME = "User: %s%n";
    private static final String PRINT_RESULT = "%s %s has played %d games";

    public static void main(String[] args) throws SQLException {
        Scanner scn = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "MyNewPass");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);
        PreparedStatement statement =
                connection.prepareStatement(FIND_INFO_BY_USER);
        String name = scn.nextLine();
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            System.out.printf(PRINT_USER_NAME, name);
            System.out.printf(PRINT_RESULT, resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("count"));
        } else {
            System.out.println(NO_SUCH_USER_STATEMENT);
        }
    }
}
