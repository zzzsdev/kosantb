package kelompok7.kosanretrofit.Adapter;

/**
 * Created by zs on 12/1/2017.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kelompok7.kosanretrofit.Data.Sewa;
import kelompok7.kosanretrofit.R;
import kelompok7.kosanretrofit.Sewa2Activity;

public class MyAdapterSewa extends RecyclerView.Adapter<MyAdapterSewa.MyViewHolder> {
    List<Sewa> mSewaList;
    public MyAdapterSewa(List<Sewa> sewaList) {
        mSewaList = sewaList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sewa,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewIdSewa.setText("Id Sewa : "+mSewaList.get(position).getid_sewa());
        holder.mTextViewTglMulai.setText("Tanggal Mulai : "+mSewaList.get(position).gettgl_mulai());
        holder.mTextViewTglSewa.setText("Tanggal Selesai : "+mSewaList.get(position).gettgl_sewa());
        holder.mTextViewIdKamar.setText("Id Kamar : "+mSewaList.get(position).getid_kamar());
        holder.mTextViewIdPenyewa.setText("Id Penyewa : "+String.valueOf(mSewaList.get(position).getid_penyewa()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), Sewa2Activity.class);
                mIntent.putExtra("id_sewa",mSewaList.get(position).getid_sewa());
                mIntent.putExtra("tgl_mulai",mSewaList.get(position).gettgl_mulai());
                mIntent.putExtra("tgl_sewa",mSewaList.get(position).gettgl_sewa());
                mIntent.putExtra("id_kamar",mSewaList.get(position).getid_kamar());
                mIntent.putExtra("id_penyewa",mSewaList.get(position).getid_penyewa());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSewaList.size();
    }
    public class MyViewHolder extends ViewHolder {
        public TextView mTextViewIdSewa, mTextViewTglMulai,
                mTextViewTglSewa,mTextViewIdKamar,mTextViewIdPenyewa;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdSewa = (TextView) itemView.findViewById(R.id.tvIdSewa);
            mTextViewTglMulai = (TextView) itemView.findViewById(R.id.tvTglMulai);
            mTextViewTglSewa = (TextView) itemView.findViewById(R.id.tvTglSewa);
            mTextViewIdKamar = (TextView) itemView.findViewById(R.id.tvIdKamars);
            mTextViewIdPenyewa = (TextView) itemView.findViewById(R.id.tvIdPenyewas);
        }
    }
}
