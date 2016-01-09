package adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.tarucfyp.dietplan.R;

import network.VolleySingleton;

/**
 * Created by STUDIO on 28/12/2015.
 */
public class AdapterFollowPlan extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public View viewer;
    private LayoutInflater layoutInflater;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context context;


    private int previousPosition =0;

    private static int TYPE_HEADER = 0;
    private static int TYPE_ITEM = 1;

    public AdapterFollowPlan(Context context){
        volleySingleton= VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();
        layoutInflater=LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==TYPE_HEADER)
        {
            View viewer = layoutInflater.inflate(R.layout.activity_follow_plan_header,parent,false);
            HeaderViewHolder holder = new HeaderViewHolder(viewer);
            return holder;
        }
        else
        {
            View viewer = layoutInflater.inflate(R.layout.activity_follow_plan_item,parent,false);
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
        return 10;
    }

    //define the control view
    public class HeaderViewHolder extends RecyclerView.ViewHolder{


        public HeaderViewHolder(View itemView) {
            super(itemView);

        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{



        public ItemViewHolder(View itemView) {
            super(itemView);


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

