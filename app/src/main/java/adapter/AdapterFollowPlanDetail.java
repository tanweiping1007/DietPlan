package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.tarucfyp.dietplan.FollowPlanDetail;
import com.tarucfyp.dietplan.R;

import database.DietPlanPackageRecord;
import network.VolleySingleton;

public class AdapterFollowPlanDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static int TYPE_HEADER = 0;
    private static int TYPE_ITEM = 1;

    private LayoutInflater inflater;
    private Context context;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public AdapterFollowPlanDetail(Context context, FollowPlanDetail followPlanDetail){
        inflater= LayoutInflater.from(context);
        this.context=context;

        volleySingleton=VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();

    }

    public AdapterFollowPlanDetail(Context context) {
        inflater= LayoutInflater.from(context);
        this.context=context;

        volleySingleton=VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER)
        {
            View view = inflater.inflate(R.layout.activity_follow_plan_detail_header,parent,false);
            HeaderViewHolder holder = new HeaderViewHolder(view);
            return holder;
        }
        else
        {
            View view = inflater.inflate(R.layout.activity_follow_plan_detail_item,parent,false);
            ItemViewHolder holder = new ItemViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder)
        {
            HeaderViewHolder ItemHolder = (HeaderViewHolder) holder;
            ItemHolder.followPlanDetailHeader2.setText("Ddfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfay" );
            ItemHolder.followPlanHeaderButton.setText("Ddfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfay" );

        }
        else
        {
            ItemViewHolder ItemHolder = (ItemViewHolder) holder;

                ItemHolder.followPlanDetailDay.setText("Day" + position);
                ItemHolder.followPlanDetailTextView1.setText("Morning : "+"1");
                ItemHolder.followPlanDetailTextView1.setText("Evening : "+"2");
                ItemHolder.followPlanDetailTextView1.setText("Night : "+"3");
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder{
        ImageView followPlanImageViewHeader1;
        TextView followPlanDetailHeader2;
        Button followPlanHeaderButton;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            followPlanImageViewHeader1 = (ImageView)itemView.findViewById(R.id.followPlanImageViewHeader1);
            followPlanDetailHeader2 = (TextView) itemView.findViewById(R.id.followPlanDetailHeader2);
            followPlanHeaderButton = (Button)itemView.findViewById(R.id.followPlanHeaderButton);
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView  followPlanDetailDay,followPlanDetailTextView1,followPlanDetailTextView2,followPlanDetailTextView3;
        public ItemViewHolder(View itemView) {
            super(itemView);
            followPlanDetailDay = (TextView)itemView.findViewById(R.id.followPlanDetailDay);
            followPlanDetailTextView1 = (TextView)itemView.findViewById(R.id.followPlanDetailTextView1);
            followPlanDetailTextView2 = (TextView)itemView.findViewById(R.id.followPlanDetailTextView2);
            followPlanDetailTextView3 = (TextView)itemView.findViewById(R.id.followPlanDetailTextView3);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEADER;
        }
        else {
            return TYPE_ITEM;

        }
    }

}