package com.example.android56_day6.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android56_day6.R;
import com.example.android56_day6.interfaces.ProductClickListener;
import com.example.android56_day6.models.Product;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static final String TAG = "ProductAdapter";
    private ArrayList<Product> mListProduct;
    private Context mContext;
    private ProductClickListener mProductClickListener;

    public ProductAdapter(ArrayList<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    public void setmProductClickListener(ProductClickListener mProductClickListener) {
        this.mProductClickListener = mProductClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Product product = mListProduct.get(position);
        String imageProduct = product.getProductThump();
        String productName = product.getProductName();
        String productOwner = "Tran huy";

        Glide.with(mContext).load(imageProduct).into(holder.imgProduct);
        holder.tvProductName.setText(productName);
        holder.tvProductOwner.setText(productOwner);

    }

    @Override
    public int getItemCount() {
        return mListProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgProduct;
        TextView tvProductName;
        ImageView imgProductOwner;
        TextView tvProductOwner;
        ImageView imgDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            imgProductOwner = itemView.findViewById(R.id.imgProductOwner);
            tvProductOwner = itemView.findViewById(R.id.tvProductOwner);
            imgDetails = itemView.findViewById(R.id.imgDetails);

            imgDetails.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.imgDetails) {
                int position = getAdapterPosition();
                if (mProductClickListener != null) {
                    mProductClickListener.onItemClickListener(position, mListProduct.get(getAdapterPosition()));
                }
            }
        }
    }
}
