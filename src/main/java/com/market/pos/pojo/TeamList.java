package com.market.pos.pojo;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team_list")
public class TeamList {

  @Id
  private long id;
  private String tId;
  private String tName;
  private String tType;
  private String tTime;
  private String liuyan;
  private String tFrom;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTId() {
    return tId;
  }

  public void setTId(String tId) {
    this.tId = tId;
  }

  public String getTName() {
    return tName;
  }

  public void setTName(String tName) {
    this.tName = tName;
  }

  public String getTType() {
    return tType;
  }

  public void setTType(String tType) {
    this.tType = tType;
  }

  public String getTTime() {
    return tTime;
  }

  public void setTTime(String tTime) {
    this.tTime = tTime;
  }

    public String getLiuyan() {
        return liuyan;
    }

    public void setLiuyan(String liuyan) {
        this.liuyan = liuyan;
    }

  public String gettFrom() {
    return tFrom;
  }

  public void settFrom(String tFrom) {
    this.tFrom = tFrom;
  }
}
