package server;

/*
 * Represents one RSS message
 */
public class FeedMessage {

  String title;
  String description;
  String link;
  String author;
  String guid;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  @Override
  public String toString() {
    return "<p align=\"center\" style=\"font-weight:bold\">" + title +"</p>" +
    		"<p style=\"padding-left: 20px; padding-right: 20px\">" + description + "</p>" +
    		"<p align=\"center\"><a href=\"" + link + "\">>>>Go to article </a></p>" +
    		"<hr style=\"width:400px; height:1px; border:2px solid; border-color:#004400;\">";
  }

} 