package gr.dit.project1.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request_type")
public class RequestType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "request_id", referencedColumnName = "id")
  private Request requestId;

  public RequestType() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Request getRequestId() {
    return requestId;
  }

  public void setRequestId(Request requestId) {
    this.requestId = requestId;
  }

}
