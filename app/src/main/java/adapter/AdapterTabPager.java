package adapter;

//tab generater adaptor, how many tab will be generate in UI
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tarucfyp.dietplan.R;

import fragment.FragmentOne;
import fragment.FragmentTwo;
import fragment.FragmentZero;
import fragment.FragmentDashBoard;

public class AdapterTabPager extends FragmentPagerAdapter {


    public static final int TAB_0 = 0;
    public static final int TAB_1 =1;
    public static final int TAB_2 =2;
    Context context;

    int icon[] = {R.drawable.ic_tab1,R.drawable.ic_tab2,R.drawable.ic_tab3} ;
    String[] tabs = {"tab1","tab2","tab3"};

    public AdapterTabPager(FragmentManager fm, Context applicationContext) {
        super(fm);
        context = applicationContext;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case TAB_0:
                fragment= FragmentDashBoard.newInstance("", "");
                break;
            case TAB_1:
                fragment= FragmentZero.newInstance("", "");
                break;
            case TAB_2:
                fragment= FragmentOne.newInstance("", "");
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        return tabs[position];
    }

    public Drawable getIcon(int position){
        return context.getResources().getDrawable(icon[position]);
    }
}