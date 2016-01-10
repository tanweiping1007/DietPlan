package forum.post;

/**
 * Created by weiping-tan on 9/1/2016.
 */
public class Post {
    private int threadID , categoryID;
    private String title,message,username,created_at,update_at,thumbnail;

    public Post(){

    }
    public Post(int threadID, int categoryID, String message, String title, String username, String created_at, String update_at, String thumbnail) {
        this.threadID = threadID;
        this.categoryID = categoryID;
        this.message = message;
        this.title = title;
        this.username = username;
        this.created_at = created_at;
        this.update_at = update_at;
        this.thumbnail = thumbnail;
    }

    public int getThreadID() {
        return threadID;
    }

    public void setThreadID(int threadID) {
        this.threadID = threadID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
