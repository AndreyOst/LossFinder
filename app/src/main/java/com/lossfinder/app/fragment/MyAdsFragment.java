package com.lossfinder.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lossfinder.app.R;
import com.lossfinder.app.constant.Constant;

public class MyAdsFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(Constant.MY_ADS_FRAGMENT_LAYOUT, container, false);
    }
}
