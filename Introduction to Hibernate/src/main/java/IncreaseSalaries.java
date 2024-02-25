import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.List;
public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<entities.Employee> result= entityManager.createQuery("SELECT E FROM Employee E" +
                        " WHERE department.id IN (1, 2, 4, 11)", entities.Employee.class)
                .getResultList();

        result.forEach(employee ->
                employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12))));
        entityManager.flush();
        transaction.commit();
        entityManager.close();

        result.forEach(employee ->
                System.out.printf("%s %s ($%.2f)%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary()));
    }
}
