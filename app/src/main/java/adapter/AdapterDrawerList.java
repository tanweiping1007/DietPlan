package adapter;

// side menu bar
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarucfyp.dietplan.R;

import java.util.Collections;
import java.util.List;

import data.Information;

public class AdapterDrawerList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_HEADER = 0;
    private static int TYPE_ITEM = 1;

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
    private Context context;
    private ClickListener clickListener;

    public AdapterDrawerList(Context context, List<Information> data){
        inflater=LayoutInflater.from(context);
        this.data = data;
        this.context=context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER)
        {
            View view = inflater.inflate(R.layout.fragment_navigation_header,parent,false);
            HeaderViewHolder holder = new HeaderViewHolder(view);
            return holder;
        }
        else
        {
            View view = inflater.inflate(R.layout.fragment_navigation_item,parent,false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
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

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder)
        {

        }
        else
        {
            MyViewHolder ItemHolder = (MyViewHolder) holder;
            Information current = data.get(position-1);
            ItemHolder.title.setText(current.title);
            ItemHolder.icon.setImageResource(current.iconId);
        }

    }

    @Override
    public int getItemCount() {
        return data.size()+1;
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon= (ImageView) itemView.findViewById(R.id.listIcon);
            title.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(clickListener!=null)
            {
                clickListener.itemClicked(v,getPosition());
            } }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

   // public void delete(int position){
   //     data.remove(position);
   //     notifyItemRemoved(position);
   // }
}
