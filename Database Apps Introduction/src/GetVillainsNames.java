import java.sql.*;
import java.util.Scanner;

public class GetVillainsNames {
    private static final String FIND_VILLAIN_BY_NAME = "SELECT name, count(distinct minion_id) AS count from villains\n" +
            "JOIN minions_db.minions_villains mv on villains.id = mv.villain_id\n" +
            "group by villain_id\n" +
            "HAVING count > 15\n" +
            "ORDER BY count;";
    private static final String PRINT_RESULT = "%s %d";


    public static void main(String[] args) throws SQLException {
        Scanner scn = new Scanner(System.in);
        Connection connection = Utils.getSqlConnection();
        PreparedStatement statement = connection.prepareStatement(FIND_VILLAIN_BY_NAME);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.printf(PRINT_RESULT,resultSet.getString("name"),
                    resultSet.getInt("count"));
        }
    }
}
