package com.example.multiselection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TvShowsListener{
    RecyclerView tvShowRecyclerView;
    List<TvShow> tvShows;
    private Button buttonAddToWatchList;
    private TvShowAdapter tvShowAdapter;

    //https://picsum.photos/seed/picsum/200/300
    //https://picsum.photos/1000?random
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShowRecyclerView=findViewById(R.id.tvShowsRecyclerView);
        buttonAddToWatchList=findViewById(R.id.buttonAddToWatchList);
        createList();
        tvShowRecyclerView.setHasFixedSize(true);
        tvShowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvShowAdapter=new TvShowAdapter(tvShows,this, MainActivity.this);
        tvShowRecyclerView.setAdapter(tvShowAdapter);
        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<TvShow> selectedTvShows=tvShowAdapter.getSelectedTvShows();
                StringBuilder tvShowNames=new StringBuilder();
                for (int i=0;i<selectedTvShows.size();i++){
                    if(i==0){
                        tvShowNames.append(selectedTvShows.get(i).getName());
                    }else{
                        tvShowNames.append("\n").append(selectedTvShows.get(i).getName());
                    }
                }
                Toast.makeText(MainActivity.this, tvShowNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void  createList(){
        tvShows=new ArrayList<>();
        for(int i=0;i<5;i++){
            TvShow tvShow=new TvShow("name"+i,"createdBy"+i,"story"+i,"https://picsum.photos/1000?random"+i,5f);
            tvShows.add(tvShow);
        }

    }

    @Override
    public void onTvShowAction(boolean selected) {
        if(selected){
            buttonAddToWatchList.setVisibility(View.VISIBLE);
        }else{
            buttonAddToWatchList.setVisibility(View.GONE);
        }
    }
}