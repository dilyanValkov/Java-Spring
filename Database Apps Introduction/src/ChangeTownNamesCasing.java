import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    private static final String SELECT_TOWNS_BY_COUNTRY_NAME = "SELECT name FROM minions_db.towns\n" +
            "WHERE country = ?;";
    private static final String NO_TOWNS_MESSAGE = "No town names were affected.";
    private static final String AFFECTED_TOWNS = "%d town names were affected.%n";

    public static void main(String[] args) throws SQLException {
        Scanner scn = new Scanner(System.in);
        Connection connection = Utils.getSqlConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_TOWNS_BY_COUNTRY_NAME);
        String countryName = scn.nextLine();
        statement.setString(1,countryName);
        ResultSet resultSet = statement.executeQuery();
        List <String> towns = new ArrayList<>();
            while (resultSet.next()) {
                String townName = resultSet.getString("name").toUpperCase();
                towns.add(townName);
            }
        if (towns.isEmpty()){
        System.out.println(NO_TOWNS_MESSAGE);
        connection.close();
        return;
        }
        System.out.printf(AFFECTED_TOWNS,towns.size());
        System.out.println(towns);
        connection.close();
    }
}
