package com.hoasen.studio.dailymailfeed.Base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Harry Nguyen on 29-Feb-16.
 */
public class BaseFragment extends Fragment {
    Bundle data = null;
    protected View mainView = null;

    public void setData(Bundle data){
        this.data = data;
    }

    public Bundle getData(){
        return data;
    }

    boolean islogicHappenOnece = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupView();

        if(islogicHappenOnece == false){
            handleLogicForOnceTime();
            islogicHappenOnece = true;
        }
    }

    protected void setupView(){

    }

    protected void handleLogicForOnceTime(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
