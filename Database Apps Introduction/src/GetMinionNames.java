import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class GetMinionNames {
    private static final String GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID ="SELECT villains.name as vname,m.name as mname,age FROM minions_db.villains\n" +
            "JOIN minions_db.minions_villains mv on villains.id = mv.villain_id\n" +
            "JOIN minions_db.minions m on m.id = mv.minion_id\n" +
            "WHERE villains.id = ?;" ;
    private static final String VILLAIN_NAME = "Villain: %s%n" ;
    private static final String NO_SUCH_VILLAIN = "No villain with ID %d exists in the database.";
    private static int COUNT = 1;
    private static final String MINIONS_NAME_AGE ="%d. %s %d%n" ;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = Utils.getSqlConnection();
        PreparedStatement statement = connection.prepareStatement(GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID);
        int villainId = sc.nextInt();
        statement.setInt(1,villainId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            System.out.printf(VILLAIN_NAME,resultSet.getString("vname"));
            while (resultSet.next()){
                System.out.printf(MINIONS_NAME_AGE,COUNT,resultSet.getString("mname"),
                        resultSet.getInt("age"));
                COUNT++;
            }
        }else {
            System.out.printf(NO_SUCH_VILLAIN,villainId);
            connection.close();
        }
    }
}
