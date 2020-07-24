package com.example.mailordeamon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PopularNewsAdapter extends RecyclerView.Adapter<PopularNewsAdapter.ViewHolder> {

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;

    }

    private static final String tag="RecyclerView";
    private Context mContext;
    private ArrayList<PopularNewsSites> popularNewsSitesList;


    public PopularNewsAdapter(Context mContext, ArrayList<PopularNewsSites> popularNewsSitesList) {
        this.mContext = mContext;
        this.popularNewsSitesList = popularNewsSitesList;
    }



    @NonNull
    @Override
    public PopularNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_news_sites_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularNewsAdapter.ViewHolder holder, int position) {
        holder.sitesNames.setText(popularNewsSitesList.get(position).getName());
        Picasso.get().load(popularNewsSitesList.get(position).getImage()).into(holder.sitesImages);
    }

    @Override
    public int getItemCount() {
        return popularNewsSitesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sitesImages;
        TextView sitesNames;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sitesImages=itemView.findViewById(R.id.popular_sites_images);
            sitesNames=itemView.findViewById(R.id.popular_sites_names);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();

                        if(position!=RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }

                    }
                }
            });


        }
    }
}
