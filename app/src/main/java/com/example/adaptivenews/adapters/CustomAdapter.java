package com.example.adaptivenews.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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

        if (headlines.get(position).getUrlToImage()!=null) {
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);
        }

        if (access.equals("Deuteranopia")) {
            holder.text_source.setTextColor(Color.parseColor("#74676B"));
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(DEUTERANOPIA);
            holder.img_headline.setColorFilter(filter);
        } else if (access.equals("Monochromacy")) {
            holder.text_source.setTextColor(Color.parseColor("#6A6A6A"));
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(ACROMATOPSIA);
            holder.img_headline.setColorFilter(filter);
        } else if (access.equals("Deuteranomaly")) {
            holder.text_source.setTextColor(Color.parseColor("#71686A"));
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(Deuteranomaly);
            holder.img_headline.setColorFilter(filter);
        } else if (access.equals("LowVision")) {
            holder.text_title.setTextSize(18);
            holder.text_source.setTextSize(18);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(NORMAL);
            holder.img_headline.setColorFilter(filter);
        } else {
            holder.text_source.setTextColor(Color.parseColor("#757575"));
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(NORMAL);
            holder.img_headline.setColorFilter(filter);
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




    private static final float[] Deuteranomaly = {
            0.8f ,0.2f ,0,0,0,
            0.258f,0.742f ,0,0,0,
            0,0.142f,0.858f,0,0,
            0,0,0,1,0,
            0,0,0,0
    };

    private static final float[] ACROMATOPSIA = {
            0.299f,0.587f,0.114f,0,0,
            0.299f,0.587f,0.114f,0,0,
            0.299f,0.587f,0.114f,0,0,
            0,0,0,1,0,
            0,0,0,0,1
    };

    private static final float[] DEUTERANOPIA = {
            0.625f,0.375f,0,0,0,
            0.7f,0.3f,0,0,0,
            0,0.3f,0.7f,0,0,
            0,0,0,1,0,
            0,0,0,0,1
    };

    private static final float[] NORMAL = {
            1,0,0,0,0,
            0,1,0,0,0,
            0,0,1,0,0,
            0,0,0,1,0,
            0,0,0,0,1
    };
}
