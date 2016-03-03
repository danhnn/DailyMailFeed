package com.hoasen.studio.dailymailfeed.NewsDetail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoasen.studio.dailymailfeed.Base.BaseFragment;
import com.hoasen.studio.dailymailfeed.R;
import com.hoasen.studio.dailymailfeed.Utilities.ConstantValue;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewDetailFragment extends BaseFragment {


    @Bind(R.id.ivShared)
    ImageView ivShared;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvDescription)
    TextView tvDescription;
    @Bind(R.id.btViewFull)
    Button btViewFull;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = mainView == null ? inflater.inflate(R.layout.fragment_new_detail, container, false) : mainView;
        ButterKnife.bind(this, mainView);
        btViewFull.setOnClickListener(v -> {

        });

        return mainView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateData();
    }

    void updateData(){
        Bundle data = getData();
        String url = data.getString(ConstantValue.URL_KEY);
        String title = data.getString(ConstantValue.TITLE_KEY);
        String body = data.getString(ConstantValue.BODY_KEY);
        Bitmap bm = (Bitmap) data.getParcelable(ConstantValue.BITMAP_KEY);

        tvTitle.setText(title);
        tvDescription.setText(body);
        ivShared.setImageBitmap(bm);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
