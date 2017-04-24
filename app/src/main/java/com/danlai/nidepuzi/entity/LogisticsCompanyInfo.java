package com.danlai.nidepuzi.entity;

/**
 * Created by wulei on 2016/1/24.
 */
public class LogisticsCompanyInfo {


  String letter;
  String Name;

  public LogisticsCompanyInfo(String letter, String name) {
    this.letter = letter;
    Name = name;
  }

  public String getLetter() {
    return letter;
  }

  public String getName() {
    return Name;
  }

  public void setLetter(String letter) {
    this.letter = letter;
  }

  public void setName(String name) {
    Name = name;
  }

  @Override public String toString() {
    return "LogisticsCompanyInfo{" +
        "letter='" + letter + '\'' +
        ", Name='" + Name + '\'' +
        '}';
  }
}
