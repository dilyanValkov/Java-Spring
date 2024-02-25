import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ChangeCasing {
    private static final String UPDATE_TOWN = "";

    public static void main(String[] args) {

        EntityManager entityManager = utils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createQuery("UPDATE Town SET name = UPPER(name) " +
                "WHERE CHAR_LENGTH(name) > 5").executeUpdate();
        transaction.commit();
    }
}
