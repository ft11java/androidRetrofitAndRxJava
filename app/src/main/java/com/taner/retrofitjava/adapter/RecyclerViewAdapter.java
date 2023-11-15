package com.taner.retrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taner.retrofitjava.R;
import com.taner.retrofitjava.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<CryptoModel> cryptoList;
    private String[] colors={"#00FFFF","#0000FF","#00008B","#7FFFD4","#C0C0C0","#808000"};

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
            holder.bind(cryptoList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(CryptoModel cryptoModel,String[] color,Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 6]));
            textName = itemView.findViewById(R.id.text_name);
            textPrice = itemView.findViewById((R.id.text_price));
            textName.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price);

        }
    }
}
