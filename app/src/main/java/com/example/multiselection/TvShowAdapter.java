package com.example.multiselection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends  RecyclerView.Adapter<TvShowAdapter.ViewHolder> {


    private List<TvShow> tvShows;
    private TvShowsListener tvShowsListener;
    private Context context;


    public TvShowAdapter(List<TvShow> tvShows, TvShowsListener tvShowsListener,Context context) {
        this.tvShows = tvShows;
        this.tvShowsListener = tvShowsListener;
        this.context=context;

    }

    public List<TvShow> getSelectedTvShows(){
        List<TvShow> selectedTvShows=new ArrayList<>();
        for(TvShow tvShow:tvShows){
            if(tvShow.isSelected()){
                selectedTvShows.add(tvShow);
            }
        }
        return selectedTvShows;
    }
    public List<TvShow> clear(){

        TvShow.clear=true;
        List<TvShow> selectedTvShows=new ArrayList<>();
        for(TvShow tvShow:tvShows){
            if(tvShow.isSelected()){
                tvShow.setSelected(false);
                selectedTvShows.remove(tvShow);
            }

        }
        notifyDataSetChanged();
        return selectedTvShows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTvShow(tvShows.get(position));

    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layoutTvShow;
        View viewBackground;
        RoundedImageView imageView;
        TextView text_name,textCreatedBy,textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutTvShow=itemView.findViewById(R.id.layout_cons);
            viewBackground=itemView.findViewById(R.id.viewBackground);
            imageView=itemView.findViewById(R.id.imageview);
            text_name=itemView.findViewById(R.id.text_view);
            textCreatedBy=itemView.findViewById(R.id.textCreatedBy);
            textStory=itemView.findViewById(R.id.textStory);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            imageSelected=itemView.findViewById(R.id.image_selected);
        }
        void bindTvShow(TvShow tvShow){
            Glide.with(context).load(tvShow.getImage()).into(imageView);
            text_name.setText(tvShow.getName());
            textCreatedBy.setText(tvShow.getCreatedBy());
            textStory.setText(tvShow.getStory());
            ratingBar.setRating(tvShow.getRating());
            if(tvShow.isSelected()&& !TvShow.clear ){
                viewBackground.setBackgroundResource(R.drawable.tv_show_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            }else{
                viewBackground.setBackgroundResource(R.drawable.tv_show_bacground);
                imageSelected.setVisibility(View.GONE);
            }
            layoutTvShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tvShow.isSelected() ){
                        viewBackground.setBackgroundResource(R.drawable.tv_show_bacground);
                        imageSelected.setVisibility(View.GONE);
                        tvShow.setSelected(false);
                        if(getSelectedTvShows().size()==0){
                            tvShowsListener.onTvShowAction(false);
                        }
                    }else{
                        viewBackground.setBackgroundResource(R.drawable.tv_show_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        tvShow.setSelected(true);
                        tvShowsListener.onTvShowAction(true);
                    }
                }
            });

        }
    }
}
