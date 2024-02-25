import entities.Employee;
import javax.persistence.EntityManager;

public class EmployeesSalaryOver50k {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        entityManager.createQuery("SELECT E FROM Employee E" +
                        " WHERE salary > 50000", Employee.class)
                .getResultList()
                .forEach(employee -> System.out.println(employee.getFirstName()));
    }
}
