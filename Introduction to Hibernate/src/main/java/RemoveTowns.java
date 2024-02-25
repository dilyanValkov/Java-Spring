import entities.Address;
import entities.Town;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Scanner scn = new Scanner(System.in);
        String townName = scn.nextLine();
        transaction.begin();
        Town townToDelete = entityManager.createQuery("FROM Town WHERE name LIKE :tname", Town.class)
                .setParameter("tname", townName)
                .getSingleResult();

        List<Address> addressesToDelete = entityManager.createQuery("FROM Address WHERE id = :id", Address.class)
                .setParameter("id", townToDelete.getId())
                .getResultList();

        addressesToDelete.forEach(td -> td.getEmployees()
                .forEach(employee -> employee.setAddress(null)));
        addressesToDelete.forEach(entityManager::remove);
        entityManager.remove(townToDelete);

        int countDeletedAddresses = addressesToDelete.size();

        System.out.printf("%d address%s in %s deleted", countDeletedAddresses,
                countDeletedAddresses == 1 ? "" : "es",
                townName);
        transaction.commit();
    }
}
