package thanhdnh.ueh.edu.article_app;

import android.app.Activity;
import android.content.Context;
import android.widget.GridView;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArticleData {
  public static UserList data;
  private Context context;
  private GridView gridview;
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  private static final String DEFAULT_STUDENT_JSON = "{\n" +
      "  \"articles\": [\n" +
      "    {\n" +
      "      \"id\": 1,\n" +
      "      \"name\": \"Nguyễn Văn An\",\n" +
      "      \"username\": \"an.nguyen\",\n" +
      "      \"email\": \"an.nguyen@ueh.edu.vn\",\n" +
      "      \"desc\": \"Sinh viên năm 3 khoa CNTT, đam mê lập trình di động và AI. Luôn sẵn sàng học hỏi công nghệ mới.\",\n" +
      "      \"avatar_url\": \"https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=300\",\n" +
      "      \"hobby\": \"Coding, Chơi guitar, Đọc sách công nghệ\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"id\": 2,\n" +
      "      \"name\": \"Trần Thị Bình\",\n" +
      "      \"username\": \"binh.tran\",\n" +
      "      \"email\": \"binh.tran@ueh.edu.vn\",\n" +
      "      \"desc\": \"Sinh viên năng động, thích tham gia các hoạt động ngoại khóa, CLB tình nguyện và sự kiện trường.\",\n" +
      "      \"avatar_url\": \"https://images.unsplash.com/photo-1517841905240-472988babdf9?w=300\",\n" +
      "      \"hobby\": \"Chụp ảnh, Nấu ăn, Du lịch\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"id\": 3,\n" +
      "      \"name\": \"Lê Hoàng Cường\",\n" +
      "      \"username\": \"cuong.le\",\n" +
      "      \"email\": \"cuong.le@ueh.edu.vn\",\n" +
      "      \"desc\": \"Yêu thích thiết kế UI/UX, phát triển web frontend và sáng tạo nội dung số.\",\n" +
      "      \"avatar_url\": \"https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=300\",\n" +
      "      \"hobby\": \"Thiết kế đồ họa, Xem phim sci-fi, Chơi game\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"id\": 4,\n" +
      "      \"name\": \"Phạm Thị Dung\",\n" +
      "      \"username\": \"dung.pham\",\n" +
      "      \"email\": \"dung.pham@ueh.edu.vn\",\n" +
      "      \"desc\": \"Chăm chỉ học tập, đạt học bổng xuất sắc các kỳ học và có kỹ năng làm việc nhóm tốt.\",\n" +
      "      \"avatar_url\": \"https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=300\",\n" +
      "      \"hobby\": \"Nghe nhạc acoustic, Viết blog, Trồng cây\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"id\": 5,\n" +
      "      \"name\": \"Hoàng Văn Em\",\n" +
      "      \"username\": \"em.hoang\",\n" +
      "      \"email\": \"em.hoang@ueh.edu.vn\",\n" +
      "      \"desc\": \"Thành viên tích cực trong các dự án nguồn mở, hackathon và nghiên cứu khoa học.\",\n" +
      "      \"avatar_url\": \"https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=300\",\n" +
      "      \"hobby\": \"Đá bóng, Chơi cờ vua, Lập trình C++\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"id\": 6,\n" +
      "      \"name\": \"Vũ Thị Phương\",\n" +
      "      \"username\": \"phuong.vu\",\n" +
      "      \"email\": \"phuong.vu@ueh.edu.vn\",\n" +
      "      \"desc\": \"Cán bộ lớp gương mẫu, nhiệt tình trong công tác phong trào và học tập xuất sắc.\",\n" +
      "      \"avatar_url\": \"https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=300\",\n" +
      "      \"hobby\": \"Học ngoại ngữ (Nhật/Anh), Đọc tiểu thuyết, Xem kịch\"\n" +
      "    }\n" +
      "  ]\n" +
      "}";

  public ArticleData(Context context, GridView gridview) {
    this.context = context;
    this.gridview = gridview;
  }

  public static UserProfile getPhotoFromId(int id) {
    if (data == null || data.getArticles() == null) return null;
    for (int i = 0; i < data.getArticles().size(); i++)
      if (data.getArticles().get(i).getId() == id)
        return data.getArticles().get(i);
    return null;
  }

  public void loadData(String url, Activity activity){
      executor.execute(()->{
          // Use student JSON directly instead of downloading the old fruit products json from github
          String jsonString = DEFAULT_STUDENT_JSON;
          activity.runOnUiThread(()->{
            Gson gson = new Gson();
            data = gson.fromJson(jsonString, (Type) UserList.class);
            if (data != null && data.getArticles() != null) {
              ArticleAdapter adapter = new ArticleAdapter(data.getArticles(), context);
              gridview.setAdapter(adapter);
            }
          });
        });
  }

  public String readText(File file){
    BufferedReader reader = null;
    try {
      InputStream stream = new FileInputStream(file);
      reader = new BufferedReader(new InputStreamReader(stream));
      StringBuffer buffer = new StringBuffer();
      String line = "";
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      return buffer.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
    return reader != null ? reader.toString() : "";
  }
}
