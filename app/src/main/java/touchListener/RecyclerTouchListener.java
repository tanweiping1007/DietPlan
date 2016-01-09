package touchListener;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import Communicator.ClickListener;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

    private GestureDetector gestureDetector;
    private ClickListener clickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
        this.clickListener=clickListener;
        gestureDetector=new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //return super.onSingleTapUp(e);
                View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && clickListener!=null)
                {
                    clickListener.onClick(child,recyclerView.getChildPosition(child));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && clickListener!=null)
                {
                    clickListener.onLongClick(child,recyclerView.getChildPosition(child));
                }
                super.onLongPress(e);
            }
        });
    }

    //first will call this function when touch the navigation view pager
    //then call the gestureDectector
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }
}