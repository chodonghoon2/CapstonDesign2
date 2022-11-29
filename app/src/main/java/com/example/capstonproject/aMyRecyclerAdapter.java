package com.example.capstonproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class aMyRecyclerAdapter extends RecyclerView.Adapter<aMyRecyclerAdapter.ViewHolder> {

    private ArrayList<aFriendItem> mFriendList;
    LinearLayout itemLayout;
    String match_number;

    @NonNull
    @Override
    public aMyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_itemrecyclerview, parent, false);
        itemLayout = (LinearLayout) view.findViewById(R.id.itemlayout);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull aMyRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mFriendList.get(position));
    }

    public void setFriendList(ArrayList<aFriendItem> list){
        this.mFriendList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        String match_number;
        ImageView profile;
        TextView name;
        TextView message;
        LinearLayout itemlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemlayout = itemView.findViewById(R.id.itemlayout);
            profile =  itemView.findViewById(R.id.profile);
            name =  itemView.findViewById(R.id.name);
            message =  itemView.findViewById(R.id.message);
        }

        void onBind(aFriendItem item){
            match_number = item.getNumber();
            profile.setImageResource(item.getResourceId());
            name.setText(item.getName());
            message.setText(item.getMessage());

            itemlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //matching_deatail 로 넘어가는 설정

                    Context context = v.getContext();

                    Log.e("adapter custom", ""+match_number);
                    Intent intent = new Intent(context, cmatching_detail.class);
                    intent.putExtra("match_number", ""+match_number);
                    context.startActivity(intent);
                }
            });

        }
    }
}
