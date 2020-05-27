package com.example.dialogfragmentrecycler;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public  class MutiChoiceAdapter extends RecyclerView.Adapter<MutiChoiceAdapter.MyViewHolder> {


    interface OnItemClikListener {
        void onItemClick(MyViewHolder viewHolder, int position);
    }

    private OnItemClikListener onItemClikListener;

    private SparseArray<Boolean> sparseArray;

    public void setSparseArray(SparseArray sparseArray) {
        this.sparseArray = sparseArray;
    }

    public void setOnItemClikListener(OnItemClikListener onItemClikListener) {
        this.onItemClikListener = onItemClikListener;
    }

    private Context context;

    public MutiChoiceAdapter(Context context) {
        this.context = context;
        sparseArray = new SparseArray<>();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_check;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            text_check = (TextView) itemView.findViewById(R.id.text_check);
            imageView = (ImageView) itemView.findViewById(R.id.image_check);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.dialog_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final int pos = holder.getLayoutPosition();
        holder.text_check.setText("选项" + position);
        if (sparseArray.get(pos) != null) {
            holder.imageView.setSelected(sparseArray.get(pos));
        } else {
            holder.imageView.setSelected(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClikListener != null) {
                    onItemClikListener.onItemClick(holder, pos);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return 15;
    }


}
