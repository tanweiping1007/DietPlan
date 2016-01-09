package adapter;
////////////////////ADAPTER HERE LAST MODIFY///////////////////////////////////////////////////////////////
// this adapter is use to load the record list from web


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.tarucfyp.dietplan.R;

import java.util.ArrayList;

import database.DietPlanPackageDataSource;
import database.DietPlanPackageRecord;
import network.VolleySingleton;


public class AdapterDashBoard extends RecyclerView.Adapter<AdapterDashBoard.DashBoardViewHolder> {

    public View viewer;
    private LayoutInflater layoutInflater;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context context;

    private ArrayList<DietPlanPackageRecord> dietPlanArrayList = new ArrayList<>();
    private int previousPosition =0;

    public AdapterDashBoard(Context context){
        volleySingleton= VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();
        layoutInflater=LayoutInflater.from(context);
        this.context = context;
    }

    //create a view to let user look
    @Override
    public DashBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_get_dashboard_record,parent,false);
        DashBoardViewHolder viewHolder = new DashBoardViewHolder(view);
        this.viewer = view;
        return viewHolder;
    }

    //bind the dat into the view
    @Override
    public void onBindViewHolder(final DashBoardViewHolder holder, int position) {

        if(position > previousPosition)
        {
            animate(holder,true);
        }
        else
        {
            animate(holder,false);
        }


        if(position == 0)
        {
            holder.fragmentDashBoardTextView1.setText("User Profile");
        }
        else if(position == 1)
        {
            holder.fragmentDashBoardTextView1.setText("Food Knowledge");
        }
        else if(position == 2)
        {
            holder.fragmentDashBoardTextView1.setText("Follow a plan, start a healthly lifestyle !");
        }
        else if (position == 3)
        {
            holder.fragmentDashBoardTextView1.setText("Let Discuss with friend!");
        }

        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    //define the control view
    public class DashBoardViewHolder extends RecyclerView.ViewHolder{

        ImageView fragmentDashBoardImageButton;
        TextView fragmentDashBoardTextView1;

        public DashBoardViewHolder(View itemView) {
            super(itemView);
            fragmentDashBoardImageButton = (ImageButton)itemView.findViewById(R.id.fragmentDashBoardImageButton);
            fragmentDashBoardTextView1 = (TextView)itemView.findViewById(R.id.fragmentDashBoardTextView1);
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

