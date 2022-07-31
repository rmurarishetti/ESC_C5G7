package com.example.ecs_c5g7.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecs_c5g7.R;
import com.example.ecs_c5g7.model.PostModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private List<PostModel> mData;
    private Context context;
    private int position;


    public RecordAdapter(Context context, ArrayList<PostModel> data) {
        this.context = context;
        mData = data == null ? new ArrayList<>() : data;
    }

    public void setData(List<PostModel> e, int position) {
        mData = e;
        notifyItemChanged(position);
    }

    public void setData(List<PostModel> data) {
        mData = data == null ? new ArrayList<>() : data;
        //  Collections.sort(mData);
        notifyDataSetChanged();
    }

    public List<PostModel> getData() {
        return mData;
    }

    public void setData(List<PostModel> data, List<Integer> members) {
        mData = data == null ? new ArrayList<>() : data;

        notifyDataSetChanged();
    }


    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record, parent, false);
        return new RecordAdapter.ViewHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RecordAdapter.ViewHolder holder, final int position) {
        PostModel reportModel = mData.get(position);
        String extension="";
        if(reportModel.getFileName().contains(".")) {
            extension = reportModel.getFileName().substring(reportModel.getFileName().lastIndexOf(".")+1);
        }
        if(extension.equalsIgnoreCase("png")|| extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("jpeg")){
            Glide.with(context).load(reportModel.getPostImage()).placeholder(R.drawable.jpg_icon).into(holder.imageView);
        }else{
            if (extension.equalsIgnoreCase("doc")||extension.equalsIgnoreCase("docx")) {
                holder.imageView.setImageResource(R.drawable.doc_icon);
            }

            else if (extension.equalsIgnoreCase("pdf")) {
                holder.imageView.setImageResource(R.drawable.pdf_icon);
            }
            else if (extension.equalsIgnoreCase("txt")) {
                holder.imageView.setImageResource(R.drawable.text_icon);
            }

            else if (extension.equalsIgnoreCase("ppt")) {
                holder.imageView.setImageResource(R.drawable.ppt_icon);
            }

            else if (extension.equalsIgnoreCase("xls")||extension.equalsIgnoreCase("xlsx")) {
                holder.imageView.setImageResource(R.drawable.xls_icon);
            }
        }

        holder.tv_document.setText(reportModel.getFileName());
        holder.tv_status.setText("" + reportModel.offerStatusString());
        holder.tv_status.setBackgroundResource(reportModel.offerStatusColor());
        holder.tv_price.setText("Price: " + reportModel.getPrice());
        holder.tv_date.setText("" + convert_time(reportModel.getPostTime()));
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

        ImageView imageView;
        TextView tv_status, tv_document, tv_price, tv_date;
        View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            imageView = v.findViewById(R.id.img_thumbnail);
            tv_document = v.findViewById(R.id.textView141);
            tv_status = v.findViewById(R.id.textView16);
            tv_price = v.findViewById(R.id.textView15);
            tv_date = v.findViewById(R.id.textView13);
        }
    }

    public String convert_time(String timestamp) {
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sfd.format(new Date(timestamp));
    }

}
