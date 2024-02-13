import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class SelectFromEmployeesBySalary {
    public static void main(String[] args) throws SQLException {
        final String FIND_EMPLOYEE_BY_SALARY = "SELECT first_name, middle_name, last_name  FROM employees WHERE salary > ?";
        Scanner scn = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "MyNewPass");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni",props);
        PreparedStatement statement =
                connection.prepareStatement(FIND_EMPLOYEE_BY_SALARY);
        String salary = scn.nextLine();
        statement.setDouble(1, Double.parseDouble(salary));
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("middle_name") +
                    " "   + resultSet.getString("last_name"));
        }
    }
}

