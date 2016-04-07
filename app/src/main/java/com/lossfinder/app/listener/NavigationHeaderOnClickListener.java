package com.lossfinder.app.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.lossfinder.app.activity.LoginActivity;

public class NavigationHeaderOnClickListener implements View.OnClickListener {

    private Context context;

    public NavigationHeaderOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
