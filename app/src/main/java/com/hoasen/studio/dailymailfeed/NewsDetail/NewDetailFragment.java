package com.hoasen.studio.dailymailfeed.NewsDetail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hoasen.studio.dailymailfeed.Base.BaseFragment;
import com.hoasen.studio.dailymailfeed.NewsDetail.Presenter.INewsDetailPresenter;
import com.hoasen.studio.dailymailfeed.NewsDetail.Presenter.NewDetailPresenterImpl;
import com.hoasen.studio.dailymailfeed.NewsDetail.View.INewsDetailView;
import com.hoasen.studio.dailymailfeed.R;
import com.hoasen.studio.dailymailfeed.Utilities.ConstantValue;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewDetailFragment extends BaseFragment implements INewsDetailView{


    @Bind(R.id.ivShared)
    ImageView ivShared;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvDescription)
    TextView tvDescription;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    INewsDetailPresenter iNewsDetailPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_new_detail, container, false);
        ButterKnife.bind(this, mainView);

        return mainView;
    }

    @Override
    public void setupView(){
        iNewsDetailPresenter = new NewDetailPresenterImpl();
        iNewsDetailPresenter.setView(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNewsDetailPresenter.showSnackBar();
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iNewsDetailPresenter.updateData();
    }

    @Override
    public void updateData() {
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
    public void showSnackBar() {
        Snackbar.make(getView(), "Here's a Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
