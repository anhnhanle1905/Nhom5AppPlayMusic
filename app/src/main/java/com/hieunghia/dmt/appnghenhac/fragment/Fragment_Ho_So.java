package com.hieunghia.dmt.appnghenhac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hieunghia.dmt.appnghenhac.Activity.LoginActivity;
import com.hieunghia.dmt.appnghenhac.Activity.MainActivity;
import com.hieunghia.dmt.appnghenhac.R;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class Fragment_Ho_So extends Fragment {
    View view;
    TextView txtName,txtPhone,txtEmail;
    CircularProgressButton crpbLogout;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ho_so, container, false);
        mainActivity = (MainActivity) getActivity();

        init();
        upDateValue();

        return view;
    }

    private void init() {
        txtName = view.findViewById(R.id.txtusername);
        txtEmail = view.findViewById(R.id.textviewemail);
        txtPhone = view.findViewById(R.id.textviewphone);
        crpbLogout = view.findViewById(R.id.cirProfileLogoutButton);
    }

    private void upDateValue() {
        txtEmail.setText(mainActivity.getGetEmail());
        txtPhone.setText(mainActivity.getGetPhone());
        txtName.setText(mainActivity.getGetName());
    }
}
