package tdc.edu.vn.doan.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.R;


public class CustomAdapterTX extends RecyclerView.Adapter<CustomAdapterTX.MyViewHolder> {
    private int layoutID;
    private ArrayList<TaiXe> data;
    private Listener listener;
    public static interface Listener{
        public void onClick(int position);
    }

    public CustomAdapterTX(int layoutID, ArrayList<TaiXe> data) {
        this.layoutID = layoutID;
        this.data = data;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView info_avatar;
        private TextView info_maTX;
        private TextView info_tenTX;
        private  ImageView info_signature;
        private CardView cardView;

        public MyViewHolder(@NonNull CardView v) {
            super(v);
            info_avatar=v.findViewById(R.id.avatar);
            info_maTX=v.findViewById(R.id.info_maTX);
            info_tenTX=v.findViewById(R.id.info_tenTX);
            info_signature=v.findViewById(R.id.info_signature);
            cardView=v;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        CardView cardView= (CardView) inflater.inflate(layoutID,viewGroup,false);
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final CardView cardView=myViewHolder.cardView;
        TaiXe taiXe =data.get(i);

        myViewHolder.info_maTX.setText("Mã Tài Xế:"+ taiXe.getMaTX());
        myViewHolder.info_tenTX.setText("Tên Tài Xế:"+ taiXe.getTenTX());

        if(taiXe.getImgSignature()!=null) {
            Bitmap bitmapToImage = BitmapFactory.decodeByteArray(taiXe.getImgSignature(), 0, taiXe.getImgSignature().length);
            myViewHolder.info_signature.setImageBitmap(bitmapToImage);
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null)
                    listener.onClick(myViewHolder.getAdapterPosition());
            }
        });

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
