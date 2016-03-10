package com.hoasen.studio.dailymailfeed.MainNews;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoasen.studio.dailymailfeed.MainNews.Model.VnreviewItem;
import com.hoasen.studio.dailymailfeed.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public class DailyMailAdapter extends RecyclerView.Adapter<DailyMailAdapter.DailyMailViewHolder> {

    protected IDailyMailViewHolderClicks dailyMailViewHolderClicks;
    private List<VnreviewItem> dailyMailModelList;
    Context context;

    public DailyMailAdapter(Context context){
        this.context = context;
    }

    public void setData(List<VnreviewItem> dailyMailModelList){
        this.dailyMailModelList = dailyMailModelList;
    }

    public void setListener(IDailyMailViewHolderClicks listener){
        this.dailyMailViewHolderClicks = listener;
    }

    @Override
    public DailyMailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view_daily_mail, parent, false);
        return new DailyMailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DailyMailViewHolder holder, int position) {
        VnreviewItem model = dailyMailModelList.get(position);
        holder.tvTitle.setText(model.title);
        holder.tvDesc.setText(model.description);
        holder.url = model.link;
        Picasso.with(context).load(model.guid).placeholder(R.mipmap.blog4).into(holder.ivNews);

        ViewCompat.setTransitionName(holder.ivNews, String.valueOf(position) + "_image");
    }

    @Override
    public int getItemCount() {
        return dailyMailModelList == null ? 0 : dailyMailModelList.size();
    }


    public class DailyMailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvTitle;
        protected TextView tvDesc;
        protected ImageView ivNews;
        protected String url;

        public DailyMailViewHolder(View v) {
            super(v);
            tvTitle =  (TextView) v.findViewById(R.id.tvTitle);
            tvDesc = (TextView)  v.findViewById(R.id.tvDescription);
            ivNews = (ImageView)  v.findViewById(R.id.ivShared);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(dailyMailViewHolderClicks != null) {
                dailyMailViewHolderClicks.onDailyMailClick(this);
            }
        }
    }

    public  interface IDailyMailViewHolderClicks {
        void onDailyMailClick(DailyMailViewHolder viewHolder);
    }
}
