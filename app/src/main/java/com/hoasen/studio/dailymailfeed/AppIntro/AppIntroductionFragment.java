package com.hoasen.studio.dailymailfeed.AppIntro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoasen.studio.dailymailfeed.Base.BaseFragment;
import com.hoasen.studio.dailymailfeed.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Harry Nguyen on 29-Feb-16.
 */
public class AppIntroductionFragment extends BaseFragment {

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.textView)
    TextView textView;

    public static AppIntroductionFragment newInstance(Bundle args) {

        AppIntroductionFragment fragment = new AppIntroductionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_intro, container, false);
        ButterKnife.bind(this, view);
        setData();

        return view;
    }

    void setData(){
        Bundle args = getArguments();
        String title = args.getString("intro_title");
        int imageType = args.getInt("intro_image");
        textView.setText(title);
        setImageView(imageType);
    }

    void setImageView(int imageType){
        int imageID = 0;
        switch (imageType){
            case 0:
                imageID = R.mipmap.blog2;
                break;
            case 1:
                imageID = R.mipmap.blog3;
                break;
            case 2:
                imageID = R.mipmap.blog4;
                break;
            case 3:
                imageID = R.mipmap.blog5;
                break;
            default:
                imageID = R.mipmap.blog2;
        }

        imageView.setImageResource(imageID);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
