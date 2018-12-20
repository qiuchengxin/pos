package com.market.pos.pojo;


public class TimePrice {

  private String money;
  private String time;


  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public TimePrice(String money, String time) {
    this.money = money;
    this.time = time;
  }
}
