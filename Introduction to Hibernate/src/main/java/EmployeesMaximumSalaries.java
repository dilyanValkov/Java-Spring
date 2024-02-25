import javax.persistence.EntityManager;
public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
         entityManager.createQuery("SELECT Department.name, MAX(salary) FROM Employee " +
                        "GROUP BY Department.name" +
                        " HAVING MAX(salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                        .getResultList()
                        .forEach(file-> System.out.println(file[0] + " " + file[1]));
    }
}
