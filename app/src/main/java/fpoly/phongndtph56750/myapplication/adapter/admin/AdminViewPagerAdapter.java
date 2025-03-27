package fpoly.phongndtph56750.myapplication.adapter.admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fpoly.phongndtph56750.myapplication.fragment.admin.AdminBookingFragment;
import fpoly.phongndtph56750.myapplication.fragment.admin.AdminCategoryFragment;
import fpoly.phongndtph56750.myapplication.fragment.admin.AdminFoodFragment;
import fpoly.phongndtph56750.myapplication.fragment.admin.AdminHomeFragment;
import fpoly.phongndtph56750.myapplication.fragment.admin.AdminManageFragment;


public class AdminViewPagerAdapter extends FragmentStateAdapter {

    public AdminViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new AdminFoodFragment();
            case 2:
                return new AdminHomeFragment();
            case 3:
                return new AdminBookingFragment();
            case 4:
                return new AdminManageFragment();
            default:
                return new AdminCategoryFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
