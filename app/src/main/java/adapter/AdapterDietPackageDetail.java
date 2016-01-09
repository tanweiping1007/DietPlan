package adapter;

//this adapter is use to get the record tht user selected
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.tarucfyp.dietplan.R;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import data.Information;
import database.DietPlanPackageRecord;
import fragment.GetDietPackageRecord;
import network.VolleySingleton;

public class AdapterDietPackageDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static int TYPE_HEADER = 0;
    private static int TYPE_ITEM = 1;

    private LayoutInflater inflater;
    private DietPlanPackageRecord dietPlanPackageRecord ;
    private Context context;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public AdapterDietPackageDetail(Context context, DietPlanPackageRecord  dietPlanPackageRecord){
        inflater= LayoutInflater.from(context);
        this.dietPlanPackageRecord = dietPlanPackageRecord;
        this.context=context;

        volleySingleton=VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER)
        {
            View view = inflater.inflate(R.layout.fragment_diet_package_detail_header,parent,false);
            HeaderViewHolder holder = new HeaderViewHolder(view);
            return holder;
        }
        else
        {
            View view = inflater.inflate(R.layout.fragment_diet_package_detail_item,parent,false);
            ItemViewHolder holder = new ItemViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder)
        {

            final HeaderViewHolder HeaderHolder = (HeaderViewHolder) holder;
            //set image
            imageLoader.get(dietPlanPackageRecord.getPackage_thumbnail(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    HeaderHolder.dietPackageDetailHeader1.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

        }
        else
        {
            ItemViewHolder ItemHolder = (ItemViewHolder) holder;
            if (position == 1)
            {
                ItemHolder.dietPackageDetailDay.setText("Day" + position);
                ItemHolder.dietPackageDetailTextView1.setText("Morning : "+dietPlanPackageRecord.getPackage_description_day1_morning());
                ItemHolder.dietPackageDetailTextView2.setText("Evening : "+dietPlanPackageRecord.getPackage_description_day1_evening());
                ItemHolder.dietPackageDetailTextView3.setText("Night : "+dietPlanPackageRecord.getPackage_description_day1_night());
            }
            if (position == 2)
            {
                ItemHolder.dietPackageDetailDay.setText("Day" + position);
                ItemHolder.dietPackageDetailTextView1.setText("Morning : "+dietPlanPackageRecord.getPackage_description_day2_morning());
                ItemHolder.dietPackageDetailTextView2.setText("Evening : "+dietPlanPackageRecord.getPackage_description_day2_evening());
                ItemHolder.dietPackageDetailTextView3.setText("Night : "+dietPlanPackageRecord.getPackage_description_day2_night());
            }
            if (position == 3)
            {
                ItemHolder.dietPackageDetailDay.setText("Day" + position);
                ItemHolder.dietPackageDetailTextView1.setText("Morning : "+dietPlanPackageRecord.getPackage_description_day3_morning());
                ItemHolder.dietPackageDetailTextView2.setText("Evening : "+dietPlanPackageRecord.getPackage_description_day3_evening());
                ItemHolder.dietPackageDetailTextView3.setText("Night : "+dietPlanPackageRecord.getPackage_description_day3_night());
            }
            if (position == 4)
            {
                ItemHolder.dietPackageDetailDay.setText("Day" + position);
                ItemHolder.dietPackageDetailTextView1.setText("Morning : "+dietPlanPackageRecord.getPackage_description_day4_morning());
                ItemHolder.dietPackageDetailTextView2.setText("Evening : "+dietPlanPackageRecord.getPackage_description_day4_evening());
                ItemHolder.dietPackageDetailTextView3.setText("Night : "+dietPlanPackageRecord.getPackage_description_day4_night());
            }
            if (position == 5)
            {
                ItemHolder.dietPackageDetailDay.setText("Day" + position);
                ItemHolder.dietPackageDetailTextView1.setText("Morning : "+dietPlanPackageRecord.getPackage_description_day5_morning());
                ItemHolder.dietPackageDetailTextView2.setText("Evening : "+dietPlanPackageRecord.getPackage_description_day5_evening());
                ItemHolder.dietPackageDetailTextView3.setText("Night : "+dietPlanPackageRecord.getPackage_description_day5_night());
            }
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder{
        ImageView dietPackageDetailHeader1;
        TextView dietPackageDetailHeader2;
        Button dietPackageDetailButton;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            dietPackageDetailHeader1 = (ImageView)itemView.findViewById(R.id.dietPackageDetailHeader1);
            dietPackageDetailHeader2 = (TextView)itemView.findViewById(R.id.dietPackageDetailHeader2);
            dietPackageDetailButton = (Button)itemView.findViewById(R.id.dietPackageDetailHeaderButton);
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView  dietPackageDetailDay,dietPackageDetailTextView1,dietPackageDetailTextView2,dietPackageDetailTextView3;
        public ItemViewHolder(View itemView) {
            super(itemView);
            dietPackageDetailDay = (TextView)itemView.findViewById(R.id.dietPackageDetailDay);
            dietPackageDetailTextView1 = (TextView)itemView.findViewById(R.id.dietPackageDetailTextView1);
            dietPackageDetailTextView2 = (TextView)itemView.findViewById(R.id.dietPackageDetailTextView2);
            dietPackageDetailTextView3 = (TextView)itemView.findViewById(R.id.dietPackageDetailTextView3);
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
