package fr.sylvainjanet.test.backend.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * The EntityManager is a core interface of JPA that provides an interface to
 * interact with entities and the database.
 * 
 * @author Sylvain
 *
 */
@Repository
public class EntityManagerRepository {

  /**
   * entity manager.
   */
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * truncate table.
   * 
   * @param tableName table name
   */
  @Transactional
  public void truncateTable(final String tableName) {
    String sql = "UPDATE `hibernate_sequence` SET `next_val`='1' WHERE 1";
    Query query = entityManager.createNativeQuery(sql);
    query.executeUpdate();
  }
}
