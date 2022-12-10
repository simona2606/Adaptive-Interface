package com.example.adaptivenews.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adaptivenews.api.models.NewsHeadlines;
import com.example.adaptivenews.R;
import com.example.adaptivenews.api.SelectListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    private SelectListener listener;
    private String access;

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener,String accessibility) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
        this.access = accessibility;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getName());

        if (access.equals("Deuteranopia")){
            holder.text_source.setTextColor(Color.parseColor("#636161"));
        }else if (access.equals("Dichromasy")){
            holder.text_source.setTextColor(Color.parseColor("#636161"));
        }else if (access.equals("Deuteranomaly")){
            holder.text_source.setTextColor(Color.parseColor("#636161"));
        }else if (access.equals("Low vision")){
            holder.text_title.setTextSize(18);
            holder.text_source.setTextSize(18);
        }
        if (headlines.get(position).getUrlToImage()!=null) {
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(headlines.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
