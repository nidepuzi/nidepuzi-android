package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by itxuye on 2016/8/5.
 */
public class MamaSelfListBean {


  /**
   * count : 1
   * next : null
   * previous : null
   * results : [{"id":1,"title":"一元创业大赛排名展示","content_link":"http://m.xiaolumeimei.com/mall/activity/20160729/rank","content":"","dest":"","status":1,"read":true,"created":"2016-08-02T21:11:38","creator":"686011"}]
   * unread_cnt : 0
   */

  private int count;
  private String next;
  private String previous;
  private int unread_cnt;
  /**
   * id : 1
   * title : 一元创业大赛排名展示
   * content_link : http://m.xiaolumeimei.com/mall/activity/20160729/rank
   * content :
   * dest :
   * status : 1
   * read : true
   * created : 2016-08-02T21:11:38
   * creator : 686011
   */

  private List<ResultsBean> results;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public int getUnread_cnt() {
    return unread_cnt;
  }

  public void setUnread_cnt(int unread_cnt) {
    this.unread_cnt = unread_cnt;
  }

  public List<ResultsBean> getResults() {
    return results;
  }

  public void setResults(List<ResultsBean> results) {
    this.results = results;
  }

  public static class ResultsBean {
    private int id;
    private String title;
    private String content_link;
    private String content;
    private String dest;
    private int status;
    private boolean read;
    private String created;
    private String creator;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getContent_link() {
      return content_link;
    }

    public void setContent_link(String content_link) {
      this.content_link = content_link;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public String getDest() {
      return dest;
    }

    public void setDest(String dest) {
      this.dest = dest;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public boolean isRead() {
      return read;
    }

    public void setRead(boolean read) {
      this.read = read;
    }

    public String getCreated() {
      return created;
    }

    public void setCreated(String created) {
      this.created = created;
    }

    public String getCreator() {
      return creator;
    }

    public void setCreator(String creator) {
      this.creator = creator;
    }
  }
}
