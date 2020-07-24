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

public class PopularSitesAdapterTwo extends RecyclerView.Adapter<PopularSitesAdapterTwo.ViewHolder> {

    private OnItemClickListener mListener;

    public interface OnItemClickListener{

        void OnItemClick(int position);

    }

    public  void setOnClickListener(OnItemClickListener listener){

        mListener=listener;

    }

    private static final String tag="RecyclerView";
    private Context mContext;
    private ArrayList<PopularNewsSites> popularNewsSitesListTwo;

    public PopularSitesAdapterTwo(Context mContext, ArrayList<PopularNewsSites> popularNewsSitesListTwo) {
        this.mContext = mContext;
        this.popularNewsSitesListTwo = popularNewsSitesListTwo;
    }

    @NonNull
    @Override
    public PopularSitesAdapterTwo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_news_sites_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularSitesAdapterTwo.ViewHolder holder, int position) {
        holder.popularSiteName.setText(popularNewsSitesListTwo.get(position).getName());
        Picasso.get().load(popularNewsSitesListTwo.get(position).getImage()).into(holder.popularImage);
    }

    @Override
    public int getItemCount() {
        return popularNewsSitesListTwo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView popularImage;
        TextView popularSiteName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularImage=itemView.findViewById(R.id.popular_sites_images);
            popularSiteName=itemView.findViewById(R.id.popular_sites_names);

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
