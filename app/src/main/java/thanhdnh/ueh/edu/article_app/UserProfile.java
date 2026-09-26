package thanhdnh.ueh.edu.article_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {
  @SerializedName(value = "id", alternate = {"article_id"})
  @Expose
  private int id;

  @SerializedName(value = "name", alternate = {"article_title", "title"})
  @Expose
  private String name;

  @SerializedName("username")
  @Expose
  private String username;

  @SerializedName("email")
  @Expose
  private String email;

  @SerializedName(value = "desc", alternate = {"article_description", "description"})
  @Expose
  private String desc;

  @SerializedName(value = "avatar_url", alternate = {"article_image", "image"})
  @Expose
  private String avatar_url;

  @SerializedName("hobby")
  @Expose
  private String hobby;

  public UserProfile(int id, String name, String username, String email, String desc, String avatar_url, String hobby) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.desc = desc;
    this.avatar_url = avatar_url;
    this.hobby = hobby;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getAvatar_url() {
    return avatar_url;
  }

  public void setAvatar_url(String avatar_url) {
    this.avatar_url = avatar_url;
  }

  public String getHobby() {
    return hobby;
  }

  public void setHobby(String hobby) {
    this.hobby = hobby;
  }

  // Backward compatibility getters/setters
  public int getArticle_id() {
    return id;
  }

  public void setArticle_id(int article_id) {
    this.id = article_id;
  }

  public String getArticle_title() {
    return name;
  }

  public void setArticle_title(String article_title) {
    this.name = article_title;
  }

  public String getArticle_image() {
    return avatar_url;
  }

  public void setArticle_image(String article_image) {
    this.avatar_url = article_image;
  }

  public String getArticle_description() {
    return desc;
  }

  public void setArticle_description(String article_description) {
    this.desc = article_description;
  }
}
