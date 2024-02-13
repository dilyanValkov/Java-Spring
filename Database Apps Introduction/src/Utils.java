import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public enum Utils {
    ;
    static Connection getSqlConnection() throws SQLException {
        final Properties properties = new Properties();
        properties.setProperty(Constants.USER, Constants.USER_NAME);
        properties.setProperty(Constants.PASSWORD, Constants.PASSWORD_VALUE);
        return DriverManager.getConnection(Constants.URL,properties);
    }
}
