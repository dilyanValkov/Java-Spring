import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AddMinion {
    private static final String ADD_TOWN = "INSERT INTO minions_db.towns (name)\n" +
            "VALUES (?);" ;
    private static final String ADD_VILLAIN = "INSERT INTO minions_db.`villains`(name, evilness_factor)\n" +
            "VALUES (?, ?)" ;
    private static final String GET_TOWN_BY_ID = "SELECT v.name FROM towns AS t WHERE t.id = ?";
    private static final String GET_VILLAIN_BY_ID = "SELECT v.name FROM towns AS t WHERE t.id = ?";

    private static final String SUCCESSFULLY_ADDED_TOWN = "Town %s was added to the database.";
    private static final String IS_VILLAIN_PRESENT = "SELECT v.id FROM villains AS v WHERE v.name = ?";
    private static final String ID = "id";
    private static final String EVILNESS_FACTOR = "evil" ;

    public static void main(String[] args) throws SQLException {
        Scanner scn = new Scanner(System.in);
        Connection connection = Utils.getSqlConnection();

        String[] minionInfo = scn.nextLine().split(" ");
        String townName = minionInfo[3];
        String minionName = minionInfo[1];
        int age = Integer.parseInt(minionInfo[2]);
        String villainName = scn.nextLine().split(" ")[1];

        final int townId = getID(connection,
                List.of(townName), ADD_TOWN, GET_TOWN_BY_ID, SUCCESSFULLY_ADDED_TOWN);
        final int villainId = getID(connection,
                List.of(villainName, EVILNESS_FACTOR), ADD_VILLAIN, GET_TOWN_BY_ID, SUCCESSFULLY_ADDED_TOWN);


        PreparedStatement presentVillainStmt = connection.prepareStatement(IS_VILLAIN_PRESENT);
        presentVillainStmt.setString(1,villainName);
        ResultSet resultSet = presentVillainStmt.executeQuery();
        if (!resultSet.next()) {

        }

    }

    private static int getID(Connection connection,
                             List<String> args,
                             String insertQuery,
                             String selectQuery,
                             String printFormat) throws SQLException {
        String name = args.getFirst();
        final PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
        selectStmt.setString(1, name);
        final ResultSet resultSet = selectStmt.executeQuery();

        if (!resultSet.next()){
            final PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            for (int i = 1; i <= args.size() ; i++) {
                insertStmt.setString(i, args.get(i-1));
            }

            insertStmt.executeUpdate();
            final ResultSet resultSet1 = selectStmt.executeQuery();
            System.out.printf(printFormat, name);
            return resultSet1.getInt(ID);
        }
       return resultSet.getInt(ID);
    }
}
