package forum.post;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tarucfyp.dietplan.MyApplication;
import com.tarucfyp.dietplan.R;

import java.util.List;

/**
 * Created by weiping-tan on 10/1/2016.
 */
public class CustomListView_Post extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Post> threadItems;
    ImageLoader imageLoader = MyApplication.getsInstance().getImageLoader();

    public CustomListView_Post(Activity activity, List<Post> threadItems) {
        this.activity = activity;
        this.threadItems = threadItems;
    }

    @Override
    public int getCount() {
        return threadItems.size();
    }

    @Override
    public Object getItem(int location) {
        return threadItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_listview_post_layout, null);

        if (imageLoader == null)
            imageLoader = MyApplication.getsInstance().getImageLoader();
        NetworkImageView thumbnail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail_post);
        TextView title = (TextView) convertView.findViewById(R.id.title_post);
        TextView username = (TextView) convertView.findViewById(R.id.username_post);
        TextView timestamp = (TextView) convertView.findViewById(R.id.timestamp_post);
        TextView message = (TextView) convertView.findViewById(R.id.message_post);



        // getting movie data for the row
        Post m  = threadItems.get(position);

        // thumbnail image
        thumbnail.setImageUrl(m.getThumbnail(), imageLoader);
        // title
        if(m.getTitle() != null)
        {
            title.setText(m.getTitle());
            title.setVisibility(View.VISIBLE);
        }
        else
        {
            title.setVisibility(View.GONE);
        }
        message.setText(m.getMessage());
        // rating
        username.setText(m.getUsername());
        timestamp.setText(m.getCreated_at());
        //      CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
        //              Long.parseLong(m.getCreated_at()),
        //              System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        //    timestamp.setText(timeAgo);

        // genre
//        String genreStr = "";
//        for (String str : m.getGenre()) {
//            genreStr += str + ", ";
//        }
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//                genreStr.length() - 2) : genreStr;
//        genre.setText(genreStr);

        // release year


        return convertView;
    }
}
