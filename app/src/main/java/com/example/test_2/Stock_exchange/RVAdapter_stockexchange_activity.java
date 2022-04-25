package com.example.test_2.Stock_exchange;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test_2.R;

import java.util.List;

public class RVAdapter_stockexchange_activity extends RecyclerView.Adapter<RVAdapter_stockexchange_activity.MyviewHolder> {

    Context context;
    List<stock_exchange> StockList;

    public RVAdapter_stockexchange_activity(Context context, List<stock_exchange> cryptoList) {
        this.context = context;
        this.StockList = cryptoList;
    }

    public void setMovieList(List<stock_exchange> cryptoList) {
        this.StockList = cryptoList;
        notifyDataSetChanged();
    }

    @Override
    public RVAdapter_stockexchange_activity.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_stockexchange_activity,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVAdapter_stockexchange_activity.MyviewHolder holder, int position) {
        holder.name.setText(StockList.get(position).getName());
        holder.year.setText(StockList.get(position).getYear());

    }

    @Override
    public int getItemCount() {
        if(StockList != null){
            return StockList.size();
        }
        return 0;

    }


    public class MyviewHolder extends RecyclerView.ViewHolder{
        TextView name, year;
        //

        public MyviewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            year = (TextView) itemView.findViewById(R.id.country);

        }
    }
}
