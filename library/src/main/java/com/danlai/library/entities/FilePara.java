package com.danlai.library.entities;

/**
 * @author wulei
 * @date 2016年02月04日 下午3:16
 */
public class FilePara {
  String filePath;
  int width;
  int height;

  public String getFilePath() {
    return filePath;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override public String toString() {
    return "FilePara{" +
        "filePath='" + filePath + '\'' +
        ", width=" + width +
        ", height=" + height +
        '}';
  }
}
