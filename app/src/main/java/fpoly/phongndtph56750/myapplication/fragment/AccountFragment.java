package fpoly.phongndtph56750.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import fpoly.phongndtph56750.myapplication.activity.ChangePasswordActivity;
import fpoly.phongndtph56750.myapplication.activity.SignInActivity;
import fpoly.phongndtph56750.myapplication.constant.GlobalFunction;
import fpoly.phongndtph56750.myapplication.databinding.FragmentAccountBinding;
import fpoly.phongndtph56750.myapplication.prefs.DataStoreManager;


public class AccountFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentAccountBinding fragmentAccountBinding = FragmentAccountBinding.inflate(inflater, container, false);

        fragmentAccountBinding.tvEmail.setText(DataStoreManager.getUser().getEmail());

        fragmentAccountBinding.layoutSignOut.setOnClickListener(l -> onClickSignOut());
        fragmentAccountBinding.layoutChangePassword.setOnClickListener(l -> onClickChangePassword());

        return fragmentAccountBinding.getRoot();
    }

    private void onClickChangePassword() {
        GlobalFunction.startActivity(getActivity(), ChangePasswordActivity.class);
    }

    private void onClickSignOut(){
        if (getActivity()== null){
            return;
        }
        FirebaseAuth.getInstance().signOut();
        DataStoreManager.setUser(null);
        GlobalFunction.startActivity(getActivity(), SignInActivity.class);
        getActivity().finishAffinity();
    }
}