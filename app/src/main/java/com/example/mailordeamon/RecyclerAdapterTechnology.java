package com.example.mailordeamon;


import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterTechnology extends RecyclerView.Adapter<RecyclerAdapterTechnology.ViewHolder> {

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener=listener;

    }


    private static final String tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Message> messageListtechnology;

    public RecyclerAdapterTechnology(Context mContext, ArrayList<Message> messageListtechnology) {
        this.mContext = mContext;
        this.messageListtechnology = messageListtechnology;
    }

    @NonNull
    @Override
    public RecyclerAdapterTechnology.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTechnology.ViewHolder holder, int position) {
        holder.textView1.setText(messageListtechnology.get(position).getText1());
        holder.textView2.setText(messageListtechnology.get(position).getText2());
        Picasso.get().load(messageListtechnology.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return messageListtechnology.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
            imageView = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();

                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
