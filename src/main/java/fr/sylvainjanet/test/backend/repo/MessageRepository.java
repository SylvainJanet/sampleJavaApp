package fr.sylvainjanet.test.backend.repo;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import fr.sylvainjanet.test.backend.entities.Message;

/**
 * Repository.
 * 
 * @author Sylvain
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
  /**
   * Truncate table.
   */
  @Modifying
  @Transactional
  @Query(value = "TRUNCATE TABLE message", nativeQuery = true)
  void truncateTable();

}
