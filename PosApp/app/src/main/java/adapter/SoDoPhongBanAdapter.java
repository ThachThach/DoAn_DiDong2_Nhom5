package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.posapp.fragment.SoDoTang1Fragment;
import com.example.posapp.fragment.SoDoTang2Fragment;
import com.example.posapp.fragment.SoDoTangTretFragment;

public class SoDoPhongBanAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Tang trệt","Tầng 1","Tầng 2"};
    private SoDoTangTretFragment soDoTangTretFragment;
    private SoDoTang1Fragment soDoTang1Fragment;
    private SoDoTang2Fragment soDoTang2Fragment;
    public SoDoPhongBanAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        soDoTangTretFragment = new SoDoTangTretFragment();
        soDoTang1Fragment = new SoDoTang1Fragment();
        soDoTang2Fragment = new SoDoTang2Fragment();

    }




    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return soDoTangTretFragment;
        }else if (position==1){
            return soDoTang1Fragment;
        }else {
        }
        return soDoTang2Fragment;
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
