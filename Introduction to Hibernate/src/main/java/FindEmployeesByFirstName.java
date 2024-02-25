import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();

        Query query = entityManager.createQuery("FROM Employee WHERE firstName LIKE (:fname)", Employee.class);
        query.setParameter("fname",input + "%");
        List <Employee> employees = query.getResultList();
        employees.forEach(employee ->
                System.out.printf("%s %s - %s - ($%.2f)%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getJobTitle(),
                        employee.getSalary()));

    }
}
