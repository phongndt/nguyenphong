package fpoly.phongndtph56750.myapplication.fragment.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import fpoly.phongndtph56750.myapplication.activity.ChangePasswordActivity;
import fpoly.phongndtph56750.myapplication.activity.SignInActivity;
import fpoly.phongndtph56750.myapplication.activity.admin.AdminRevenueActivity;
import fpoly.phongndtph56750.myapplication.activity.admin.AdminVoucherActivity;
import fpoly.phongndtph56750.myapplication.constant.GlobalFunction;
import fpoly.phongndtph56750.myapplication.databinding.FragmentAdminManageBinding;
import fpoly.phongndtph56750.myapplication.prefs.DataStoreManager;


public class AdminManageFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAdminManageBinding fragmentAdminManageBinding = FragmentAdminManageBinding.inflate(inflater, container, false);

        fragmentAdminManageBinding.tvEmail.setText(DataStoreManager.getUser().getEmail());

        fragmentAdminManageBinding.layoutReport.setOnClickListener(v -> onClickReport());
        fragmentAdminManageBinding.layoutSignOut.setOnClickListener(v -> onClickSignOut());
        fragmentAdminManageBinding.layoutChangePassword.setOnClickListener(v -> onClickChangePassword());
        fragmentAdminManageBinding.layoutDiscountCode.setOnClickListener(v -> onClickDiscountCode());

        return fragmentAdminManageBinding.getRoot();
    }

    private void onClickReport() {
        GlobalFunction.startActivity(getActivity(), AdminRevenueActivity.class);
    }

    private void onClickChangePassword() {
        GlobalFunction.startActivity(getActivity(), ChangePasswordActivity.class);
    }

    private void onClickDiscountCode() {
        GlobalFunction.startActivity(getActivity(), AdminVoucherActivity.class);
    }

    private void onClickSignOut() {
        if (getActivity() == null) {
            return;
        }
        FirebaseAuth.getInstance().signOut();
        DataStoreManager.setUser(null);
        GlobalFunction.startActivity(getActivity(), SignInActivity.class);
        getActivity().finishAffinity();
    }
}