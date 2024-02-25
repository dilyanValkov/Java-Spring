import entities.Address;

import javax.persistence.EntityManager;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();
        entityManager.createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(Address::printInfo);
    }
}
