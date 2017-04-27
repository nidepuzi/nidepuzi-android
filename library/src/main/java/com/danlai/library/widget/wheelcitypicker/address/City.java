package com.danlai.library.widget.wheelcitypicker.address;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class City {

  @SerializedName("childs")private ArrayList<County> counties;
  @SerializedName("id") private String id;
  @SerializedName("name") private String name;
  /**
   * grade : 3
   * parent_id : 2
   * zipcode :
   */

  @SerializedName("grade") private int grade;
  @SerializedName("parent_id") private int parentId;
  @SerializedName("zipcode") private String zipcode;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<County> getCounties() {
    return counties;
  }

  public void setCounties(ArrayList<County> counties) {
    this.counties = counties;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
}
