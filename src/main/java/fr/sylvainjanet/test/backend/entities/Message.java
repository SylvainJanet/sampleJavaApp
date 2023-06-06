package fr.sylvainjanet.test.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.sylvainjanet.test.backend.config.ConfigurationParams;

/**
 * Message. https://www.baeldung.com/jpa-entities
 * 
 * @author Sylvain
 *
 */
// @Entity(name = "someThing") => this name will be used to name Entity
// @Table(name = "someThing") => this name will be used to name a table in DB
// Entity is object-oriented and table is relation-oriented.
// You can only use the entity name in the HQL (Hibernate Query Language)
// to query objects, and the table
// name in the native SQL. 
@Entity(name = "message")
@Table(name = "message")
public class Message {

  /**
   * Constructor.
   * 
   * @param content the content.
   */
  public Message(final String content) {
    super();
    this.content = content;
  }

  /**
   * This id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * The content.
   */
  @Column(name = "message_content", length = ConfigurationParams.MAX_STR_SIZE,
      nullable = false, unique = false)
  private String content;

  /**
   * Get content.
   *
   * @return the content
   */
  public String getContent() {
    return this.content;
  }

  /**
   * Get id.
   *
   * @return the id
   */
  public Long getId() {
    return this.id;
  }

  /**
   * Set content.
   *
   * @param content the content to set
   */
  public void setContent(final String content) {
    this.content = content;
  }

  /**
   * Set id.
   *
   * @param id the id to set
   */
  public void setId(final Long id) {
    this.id = id;
  }

}
