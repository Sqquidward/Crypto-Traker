package com.example.test_2.Stock_exchange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class RVAdapter_stock_exchange extends RecyclerView.Adapter<RVAdapter_stock_exchange.MyviewHolder>{

    Context context;
    List<stock_exchange> StockList;

    public RVAdapter_stock_exchange(Context context, List<stock_exchange> cryptoList) {
        this.context = context;
        this.StockList = cryptoList;
    }

    public void setMovieList(List<stock_exchange> cryptoList) {
        this.StockList = cryptoList;
        notifyDataSetChanged();
    }

    @Override
    public RVAdapter_stock_exchange.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_stock_exchange,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVAdapter_stock_exchange.MyviewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(StockList.get(position).getName());
        Glide.with(context).load(StockList.get(position)
                .getImage())
                .apply(RequestOptions.centerCropTransform()).into(holder.image);
        holder.year.setText(StockList.get(position).getYear());
        holder.volume.setText(StockList.get(position).getTrade_volume_24h());
        holder.country.setText(StockList.get(position).getCountry());
        holder.trust_score.setText(StockList.get(position).getTrust_score());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(StockList.get(position).getUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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


    public class MyviewHolder extends RecyclerView.ViewHolder{
        TextView name, year, country, trust_score, volume;
        ImageView image;
        Button button;
        //

        public MyviewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            volume = (TextView) itemView.findViewById(R.id.volume);
            country = (TextView) itemView.findViewById(R.id.Country);
            trust_score = (TextView) itemView.findViewById(R.id.trust_score);
            image = (ImageView) itemView.findViewById(R.id.imageView);
}}}
