package com.example.ecs_c5g7.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecs_c5g7.HomeActivity;
import com.example.ecs_c5g7.R;
import com.example.ecs_c5g7.RecordActivity;
import com.example.ecs_c5g7.model.PostModel;
import com.example.ecs_c5g7.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private List<UserModel> mData;
    private Context context;
    private int position;


    public CustomerAdapter(Context context, ArrayList<UserModel> data) {
        this.context = context;
        mData = data == null ? new ArrayList<>() : data;
    }

    public void setData(List<UserModel> e, int position) {
        mData = e;
        notifyItemChanged(position);
    }

    public void setData(List<UserModel> data) {
        mData = data == null ? new ArrayList<>() : data;
        //  Collections.sort(mData);
        notifyDataSetChanged();
    }

    public List<UserModel> getData() {
        return mData;
    }

    public void setData(List<UserModel> data, List<Integer> members) {
        mData = data == null ? new ArrayList<>() : data;

        notifyDataSetChanged();
    }


    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer, parent, false);
        return new CustomerAdapter.ViewHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final CustomerAdapter.ViewHolder holder, final int position) {
        UserModel reportModel = mData.get(position);

        holder.tv_name.setText(reportModel.getName());
        holder.tv_email.setText("" + reportModel.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, RecordActivity.class);
                i.putExtra("userId",reportModel.getUserId());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mData != null && !mData.isEmpty()) {
            return mData.size();
        }
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_email;
        View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            tv_name = v.findViewById(R.id.textView141);
            tv_email = v.findViewById(R.id.textView16);
        }
    }



}
