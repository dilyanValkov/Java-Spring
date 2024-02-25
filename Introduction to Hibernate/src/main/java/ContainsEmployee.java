import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String fullName = new Scanner(System.in).nextLine();
        String firstName = fullName.split(" ")[0];
        String lastName = fullName.split(" ")[1];
        transaction.begin();
        String result = entityManager.createQuery("SELECT E FROM Employee E" +
                        " WHERE firstName LIKE :f AND lastName LIKE :l", Employee.class)
                .setParameter("f", firstName)
                .setParameter("l", lastName)
                .getResultList()
                .isEmpty() ? "No" : "Yes";
        transaction.commit();
        System.out.println(result);
    }
}
