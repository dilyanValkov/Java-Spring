import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;

public class FindLatest10Projects {
    public static void main(String[] args) {
        EntityManager entityManager = utils.entityManager();

        entityManager.createQuery("FROM Project ORDER BY startDate DESC, name", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(Project::printProject);
    }
}
