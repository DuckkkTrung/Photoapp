package thanhdnh.ueh.edu.article_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserList {

  @SerializedName(value = "articles", alternate = {"students", "users"})
  @Expose
  private ArrayList<UserProfile> userProfiles;

  public UserList(ArrayList<UserProfile> userProfiles) {
    this.setArticles(userProfiles);
  }

  public ArrayList<UserProfile> getArticles() {
    return userProfiles;
  }

  public void setArticles(ArrayList<UserProfile> userProfiles) {
    this.userProfiles = userProfiles;
  }
}
