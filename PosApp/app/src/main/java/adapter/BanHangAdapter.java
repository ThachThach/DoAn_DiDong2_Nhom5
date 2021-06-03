package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.posapp.fragment.Tang1Fragment;
import com.example.posapp.fragment.Tang2Fragment;
import com.example.posapp.fragment.TangTretFragment;

public class BanHangAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Tang trệt","Tầng 1","Tầng 2"};
    private TangTretFragment tangTretFragment;
    private Tang1Fragment tang1Fragment;
    private Tang2Fragment tang2Fragment;
    public BanHangAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        tangTretFragment = new TangTretFragment();
        tang1Fragment = new Tang1Fragment();
        tang2Fragment = new Tang2Fragment();

    }




    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return tangTretFragment;
        }else if (position==1){
            return tang1Fragment;
        }else {
        }
        return tang2Fragment;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }
    public CharSequence getPageTitle(int potision)
    {
        return listTab[potision];
    }
}
