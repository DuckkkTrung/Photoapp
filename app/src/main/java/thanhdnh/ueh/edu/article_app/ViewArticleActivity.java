package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ViewArticleActivity extends AppCompatActivity {
  ImageView iv_detail;
  TextView tv_detail_name, tv_detail_username, tv_detail_email, tv_detail_hobby, tv_detail_description;
  Button btn_back;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_article);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }

    iv_detail = findViewById(R.id.iv_detail);
    tv_detail_name = findViewById(R.id.tv_detail_name);
    tv_detail_username = findViewById(R.id.tv_detail_username);
    tv_detail_email = findViewById(R.id.tv_detail_email);
    tv_detail_hobby = findViewById(R.id.tv_detail_hobby);
    tv_detail_description = findViewById(R.id.tv_detail_description);
    btn_back = findViewById(R.id.btn_back);

    btn_back.setOnClickListener(v -> finish());

    int id = (int) getIntent().getLongExtra("id", 0);
    UserProfile profile = ArticleData.getPhotoFromId(id);

    if (profile != null) {
      String avatarUrl = profile.getAvatar_url();
      if (avatarUrl == null || avatarUrl.isEmpty()) {
        avatarUrl = profile.getArticle_image();
      }

      Picasso.get().load(avatarUrl).resize(400, 400).centerCrop().into(iv_detail);

      String name = profile.getName();
      if (name == null || name.isEmpty()) {
        name = profile.getArticle_title();
      }
      tv_detail_name.setText(name);
      tv_detail_username.setText("Username: " + profile.getUsername());
      tv_detail_email.setText("Email: " + profile.getEmail());
      tv_detail_hobby.setText("Sở thích: " + profile.getHobby());
      tv_detail_description.setText("Mô tả: " + profile.getDesc());
    }
  }
}
