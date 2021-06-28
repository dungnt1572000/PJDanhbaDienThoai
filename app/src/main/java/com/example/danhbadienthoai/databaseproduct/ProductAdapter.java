package com.example.danhbadienthoai.databaseproduct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.danhbadienthoai.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    List<Product> list;
    ChitietProduct chitietProduct;

    public ProductAdapter(List<Product> list, ChitietProduct chitietProduct) {
        this.list = list;
        this.chitietProduct = chitietProduct;
    }
    public void setData(List<Product> list){
        this.list = list;
        notifyDataSetChanged();
    }
    // fragment produc t dau
    // la sao ? ten fragment FragmentProduct
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent,false);
        return new ProductViewHolder(view,chitietProduct) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txtid.setText(String.valueOf(list.get(position).getId()));
        holder.txtname.setText(list.get(position).getName());
        Glide.with(holder.img.getContext()).load(list.get(position).getUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    // co 1 van de, la luc framgnet product chua khoi tao thi no k nhan su kien
    // t tuong chay MainActy la no khoi tao san r chu k , viewpager chi khoi tao 1 fragment dau tien the tuc la minh vao fragment1 no ko khoi tao fragment 2 a
    // ? the t ms bao check xẻm da khoi tao chua

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtname,txtid;
        CardView cardView;
        public ProductViewHolder(@NonNull View itemView, ChitietProduct chitietProduct) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.product_item_img);
            txtname = (TextView) itemView.findViewById(R.id.product_ten);
            txtid = (TextView) itemView.findViewById(R.id.product_id);
            cardView = (CardView) itemView.findViewById(R.id.itemproduct_cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        chitietProduct.Clicktosee(list.get(getAdapterPosition()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
