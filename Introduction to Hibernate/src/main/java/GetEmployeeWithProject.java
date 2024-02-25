import entities.Employee;
import javax.persistence.EntityManager;
import java.util.Scanner;
public class GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        int input = new Scanner(System.in).nextInt();
        entityManager.createQuery("FROM Employee WHERE id = :id", Employee.class)
                .setParameter("id", input)
                .getSingleResult()
                .printEmployees();

    }
}
