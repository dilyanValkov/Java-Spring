import entities.Employee;
import javax.persistence.EntityManager;
public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        entityManager.createQuery("SELECT E FROM Employee E " +
                " WHERE department.name LIKE :dname" , Employee.class)
                .setParameter("dname" , "Research and Development")
                .getResultList()
                .forEach(employee ->
                        System.out.println(employee.getFirstName() + employee.getLastName()
                        + " from " + employee.getDepartment()
                        + " - $" + employee.getSalary()));
    }
}
