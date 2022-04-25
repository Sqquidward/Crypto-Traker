package com.example.test_2.Stock_market;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test_2.R;
import com.example.test_2.Stock_one_card_activity;

import java.util.List;

public class RecyclerAdapter_Stock_Activity extends RecyclerView.Adapter<RecyclerAdapter_Stock_Activity.MyviewHolder> {

    Context context;
    List<Stock_Activity_Init> StockList;

    public RecyclerAdapter_Stock_Activity(Context context, List<Stock_Activity_Init> cryptoList) {
        this.context = context;
        this.StockList = cryptoList;
    }

    public void setMovieList(List<Stock_Activity_Init> cryptoList) {
        this.StockList = cryptoList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter_Stock_Activity.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_stock_acticity,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter_Stock_Activity.MyviewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.symbol.setText(StockList.get(position).getSymbol());
        holder.name.setText(StockList.get(position).getTitle());
        Glide.with(context).load(StockList.get(position)
                .getImage())
                .apply(RequestOptions.centerCropTransform()).into(holder.image);
        holder.price.setText(StockList.get(position).getPrice());
        holder.high_24.setText(StockList.get(position).getHigh_24());
        holder.market_cap_rank.setText(StockList.get(position).getMarket_cap_rank());
        holder.low_24.setText(StockList.get(position).getLow_24());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Ok", Toast.LENGTH_LONG).show();//Вот это работает

                Intent intent = new Intent(context, Stock_one_card_activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", StockList.get(position).getTitle());
                intent.putExtra("photo", StockList.get(position).getImage());
                intent.putExtra("symbol", StockList.get(position).getSymbol());
                intent.putExtra("price",StockList.get(position).getPrice());
                intent.putExtra("image", StockList.get(position).getImage());
                intent.putExtra("high_24", StockList.get(position).getHigh_24());
                intent.putExtra("low_24", StockList.get(position).getLow_24());
                intent.putExtra("atl", StockList.get(position).getAtl());
                intent.putExtra("atl_change_percentage", StockList.get(position).getAtl_change_percentage());
                intent.putExtra("circulating_supply", StockList.get(position).getCirculating_supply());
                intent.putExtra("market_cap_change_24h", StockList.get(position).getMarket_cap_change_24h());
                intent.putExtra("market_cap_rank", StockList.get(position).getMarket_cap_rank());
                intent.putExtra("price_change_24h", StockList.get(position).getPrice_change_24h());
                intent.putExtra("total_volume", StockList.get(position).getTotal_volume());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(StockList != null){
            return StockList.size();
        }
        return 0;

    }


    public class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView symbol;
        TextView name;
        ImageView image;
        TextView price;
        TextView high_24;
        TextView low_24, market_cap_rank;
        //

        public MyviewHolder(View itemView) {
            super(itemView);
            symbol = (TextView)itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            price = (TextView) itemView.findViewById(R.id.price);
            high_24 = (TextView) itemView.findViewById(R.id.high_24);
            low_24 = (TextView) itemView.findViewById(R.id.low_24);
            market_cap_rank = (TextView) itemView.findViewById(R.id.market_cap_rank);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAbsoluteAdapterPosition();
            Toast.makeText(context, "Position "+position, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Stock_one_card_activity.class);
            context.startActivity(intent);
        }
    }
}