package thanhdnh.ueh.edu.article_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends BaseAdapter {
  private ArrayList<UserProfile> userProfile_list;
  private Context context;

  public ArticleAdapter(ArrayList<UserProfile> userProfile_list, Context context) {
    this.userProfile_list = userProfile_list;
    this.context = context;
  }

  @Override
  public int getCount() {
    return userProfile_list.size();
  }

  @Override
  public Object getItem(int position) {
    return userProfile_list.get(position);
  }

  @Override
  public long getItemId(int position) {
    return userProfile_list.get(position).getId();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final MyView dataitem;
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (convertView == null) {
      dataitem = new MyView();
      convertView = inflater.inflate(R.layout.article_disp_tpl, null);
      dataitem.iv_photo = convertView.findViewById(R.id.imv_photo);
      dataitem.tv_caption = convertView.findViewById(R.id.tv_title);
      convertView.setTag(dataitem);
    } else {
      dataitem = (MyView) convertView.getTag();
    }

    UserProfile profile = userProfile_list.get(position);
    String avatarUrl = profile.getAvatar_url();
    if (avatarUrl == null || avatarUrl.isEmpty()) {
      avatarUrl = profile.getArticle_image();
    }

    Picasso.get().load(avatarUrl).resize(300, 300).centerCrop().into(dataitem.iv_photo);
    
    String name = profile.getName();
    if (name == null || name.isEmpty()) {
      name = profile.getArticle_title();
    }
    dataitem.tv_caption.setText(name);
    return convertView;
  }

  private static class MyView {
    ImageView iv_photo;
    TextView tv_caption;
  }
}
