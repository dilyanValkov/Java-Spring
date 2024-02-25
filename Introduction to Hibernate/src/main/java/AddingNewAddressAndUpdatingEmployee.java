import entities.Address;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        EntityTransaction transaction = entityManager.getTransaction();
        Address address = new Address();
        address.setText("Vitoshka 11");
        transaction.begin();
        entityManager.persist(address);
        entityManager.createQuery("UPDATE Employee SET address = :a WHERE lastName= :lName")
                .setParameter("a", address)
                .setParameter("lName", input).executeUpdate();
        entityManager.flush();
        transaction.commit();

    }
}
