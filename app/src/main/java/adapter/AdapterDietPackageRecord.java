package adapter;

// this adapter is use to load the record list from web


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.tarucfyp.dietplan.R;

import java.util.ArrayList;
import java.util.List;

import database.DietPlanPackageDataSource;
import database.DietPlanPackageRecord;
import fragment.GetDietPackageRecord;
import network.VolleySingleton;


public class AdapterDietPackageRecord extends RecyclerView.Adapter<AdapterDietPackageRecord.DietPackageViewHolder> {

    public View viewer;
    private LayoutInflater layoutInflater;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context context;

    DietPlanPackageDataSource dietPlanPackageDataSource;

    private ArrayList<DietPlanPackageRecord> dietPlanArrayList = new ArrayList<>();
    private int previousPosition =0;

    public AdapterDietPackageRecord(Context context){
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
    public DietPackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_get_diet_package_record,parent,false);
        DietPackageViewHolder viewHolder = new DietPackageViewHolder(view);
        this.viewer = view;
        return viewHolder;
    }

    //bind the dat into the view
    @Override
    public void onBindViewHolder(final DietPackageViewHolder holder, int position) {
        //Toast.makeText(viewer.getContext(),dietPlanArrayList.get(position).getPackage_description_day1_night(),Toast.LENGTH_LONG).show();
        holder.fragmentZeroTextView1.setText(dietPlanArrayList.get(position).getPackage_suitable());
        holder.fragmentZeroTextView2.setText(dietPlanArrayList.get(position).getPackage_description_day1_morning());

        String urlThumbnail = dietPlanArrayList.get(position).getPackage_thumbnail();
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
        }


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
        return dietPlanArrayList.size();
    }

    //define the control view
    public class DietPackageViewHolder extends RecyclerView.ViewHolder{

        TextView fragmentZeroTextView1,fragmentZeroTextView2;
        ImageView fragmentZeroImageView;

        public DietPackageViewHolder(View itemView) {
            super(itemView);
            fragmentZeroTextView1 = (TextView) itemView.findViewById(R.id.fragmentZeroTextView1);
            fragmentZeroTextView2 = (TextView) itemView.findViewById(R.id.fragmentZeroTextView2);
            fragmentZeroImageView = (ImageView)itemView.findViewById(R.id.fragmentZeroImageView);

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

}

