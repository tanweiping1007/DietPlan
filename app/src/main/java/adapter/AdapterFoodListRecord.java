package adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.tarucfyp.dietplan.R;

import java.util.ArrayList;

import data.Information;
import database.DietPlanPackageDataSource;
import database.DietPlanPackageRecord;
import network.VolleySingleton;

/**
 * Created by STUDIO on 26/12/2015.
 */

public class AdapterFoodListRecord extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public View viewer;
    private LayoutInflater layoutInflater;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context context;

    DietPlanPackageDataSource dietPlanPackageDataSource;

    private ArrayList<DietPlanPackageRecord> dietPlanArrayList = new ArrayList<>();
    private int previousPosition =0;

    private static int TYPE_HEADER = 0;
    private static int TYPE_ITEM = 1;

    public AdapterFoodListRecord(Context context){
        volleySingleton= VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();
        layoutInflater=LayoutInflater.from(context);
        this.context = context;
    }

    // pass record at here
    public void setDietPlanArrayList(ArrayList<DietPlanPackageRecord> dietPlanArrayList){
        this.dietPlanArrayList = dietPlanArrayList;
        notifyItemRangeChanged(0,dietPlanArrayList.size());
    }

    //create a view to let user look
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==TYPE_HEADER)
        {
            View viewer = layoutInflater.inflate(R.layout.activity_food_list_header,parent,false);
            HeaderViewHolder holder = new HeaderViewHolder(viewer);
            return holder;
        }
        else
        {
            View viewer = layoutInflater.inflate(R.layout.activity_food_list_item,parent,false);
            ItemViewHolder holder = new ItemViewHolder(viewer);
            return holder;
        }


    }

    //bind the dat into the view
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if(holder instanceof HeaderViewHolder)
        {

        }
        else
        {
            ItemViewHolder ItemHolder = (ItemViewHolder) holder;
          //  Information current = data.get(position-1);
            ItemHolder.foodListTextView1.setText("xx");
            ItemHolder.foodListTextView2.setText("xx");
        }

        /*String urlThumbnail = dietPlanArrayList.get(position).getPackage_thumbnail();
        if(urlThumbnail!=null){
            imageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.fragmentZeroImageView.setImageBitmap(response.getBitmap());

                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }*/


        if(position > previousPosition)
        {
            animate(holder,true);
        }
        else
        {
            animate(holder,false);
        }
        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    //define the control view
    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        ImageView FoodListHeaderImageView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            FoodListHeaderImageView = (ImageView)itemView.findViewById(R.id.FoodListHeaderImageView);

        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView foodListTextView1,foodListTextView2;
        ImageView foodListImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            foodListTextView1 = (TextView) itemView.findViewById(R.id.foodListTextView1);
            foodListTextView2 = (TextView) itemView.findViewById(R.id.foodListTextView2);
            foodListImageView = (ImageView)itemView.findViewById(R.id.foodListImageView);

        }
    }
        // make list got animation
    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView,"translationY",goesDown==true?300:-300,0);
        ObjectAnimator animatorTranslateX =ObjectAnimator.ofFloat(holder.itemView,"translationX",300,0);
        animatorTranslateY.setDuration(1000);
        animatorTranslateX.setDuration(1000);
        animatorSet.playTogether(animatorTranslateY,animatorTranslateX);
        animatorSet.playTogether(animatorTranslateY);

        animatorSet.start();
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

