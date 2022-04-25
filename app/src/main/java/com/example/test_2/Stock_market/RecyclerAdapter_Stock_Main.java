package com.example.test_2.Stock_market;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test_2.R;

import java.util.List;

public class RecyclerAdapter_Stock_Main extends RecyclerView.Adapter<RecyclerAdapter_Stock_Main.MyviewHolder> {

    Context context;
    List<Stock_Main_Init> StockList;

    public RecyclerAdapter_Stock_Main(Context context, List<Stock_Main_Init> movieList) {
        this.context = context;
        this.StockList = movieList;
    }

    public void setMovieList(List<Stock_Main_Init> movieList) {
        this.StockList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter_Stock_Main.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_stock,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter_Stock_Main.MyviewHolder holder, int position) {
        holder.name.setText(StockList.get(position).getTitle());
        holder.price.setText(StockList.get(position).getPrice());
        holder.High24.setText(StockList.get(position).getHigh_24());

    }

    @Override
    public int getItemCount() {
        if(StockList != null){
            return StockList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView High24;
        //

        public MyviewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.textView_1);
            price = (TextView) itemView.findViewById(R.id.textView_2);
            High24 = (TextView) itemView.findViewById(R.id.textView_3);

        }
    }
}