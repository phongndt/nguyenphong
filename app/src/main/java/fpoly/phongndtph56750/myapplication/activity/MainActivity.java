package fpoly.phongndtph56750.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.greenrobot.eventbus.EventBus;

import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.adapter.admin.AdminViewPagerAdapter;
import fpoly.phongndtph56750.myapplication.databinding.ActivityAdminMainBinding;
import fpoly.phongndtph56750.myapplication.event.ResultQrCodeEvent;


@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminMainBinding activityAdminMainBinding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(activityAdminMainBinding.getRoot());

        AdminViewPagerAdapter adminViewPagerAdapter = new AdminViewPagerAdapter(this);
        activityAdminMainBinding.viewpager2.setAdapter(adminViewPagerAdapter);
        activityAdminMainBinding.viewpager2.setUserInputEnabled(false);

        activityAdminMainBinding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        activityAdminMainBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_category).setChecked(true);
                        activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_category));
                        break;

                    case 1:
                        activityAdminMainBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_food_drink).setChecked(true);
                        activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_food_drink));
                        break;

                    case 2:
                        activityAdminMainBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_movie).setChecked(true);
                        activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_movie));
                        break;

                    case 3:
                        activityAdminMainBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_booking).setChecked(true);
                        activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_booking));
                        break;

                    case 4:
                        activityAdminMainBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_manage).setChecked(true);
                        activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_manage));
                        break;
                }
            }
        });

        activityAdminMainBinding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_admin_category) {
                activityAdminMainBinding.viewpager2.setCurrentItem(0);
                activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_category));
            } else if (id == R.id.nav_admin_food_drink) {
                activityAdminMainBinding.viewpager2.setCurrentItem(1);
                activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_food_drink));
            } else if (id == R.id.nav_admin_movie) {
                activityAdminMainBinding.viewpager2.setCurrentItem(2);
                activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_movie));
            } else if (id == R.id.nav_admin_booking) {
                activityAdminMainBinding.viewpager2.setCurrentItem(3);
                activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_booking));
            } else if (id == R.id.nav_admin_manage) {
                activityAdminMainBinding.viewpager2.setCurrentItem(4);
                activityAdminMainBinding.tvTitle.setText(getString(R.string.nav_admin_manage));
            }
            return true;
        });
    }

    private void showDialogLogout() {
        new MaterialDialog.Builder(this)
                .title(getString(R.string.app_name))
                .content(getString(R.string.msg_confirm_login_another_device))
                .positiveText(getString(R.string.action_ok))
                .negativeText(getString(R.string.action_cancel))
                .onPositive((dialog, which) -> {
                    dialog.dismiss();
                    finishAffinity();
                })
                .onNegative((dialog, which) -> dialog.dismiss())
                .cancelable(true)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null && intentResult.getContents() != null) {
            EventBus.getDefault().post(new ResultQrCodeEvent(intentResult.getContents()));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        showDialogLogout();
    }
}