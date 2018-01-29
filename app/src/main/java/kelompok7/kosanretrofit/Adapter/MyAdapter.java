package kelompok7.kosanretrofit.Adapter;

/**
 * Created by zs on 11/28/2017.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kelompok7.kosanretrofit.Data.Penyewa;
import kelompok7.kosanretrofit.Penyewa2Activity;
import kelompok7.kosanretrofit.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Penyewa> mPenyewaList;
    public MyAdapter(List<Penyewa> PenyewaList) {
        mPenyewaList = PenyewaList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_penyewa,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewIdPenyewa.setText("Id Penyewa : "+mPenyewaList.get(position).getid_penyewa());
        holder.mTextViewNamaPenyewa.setText("Nama Penyewa : "+mPenyewaList.get(position).getnama_penyewa());
        holder.mTextViewAlamat.setText("Alamat : "+mPenyewaList.get(position).getalamat());

        holder.mTextViewNoHP.setText("HP : "+String.valueOf(mPenyewaList.get(position).getno_hp()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), Penyewa2Activity.class);
                mIntent.putExtra("id_penyewa",mPenyewaList.get(position).getid_penyewa());
                mIntent.putExtra("nama_penyewa",mPenyewaList.get(position).getnama_penyewa());
                mIntent.putExtra("alamat",mPenyewaList.get(position).getalamat());
                mIntent.putExtra("no_hp",String.valueOf(mPenyewaList.get(position).getno_hp()));
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPenyewaList.size();
    }
    public class MyViewHolder extends ViewHolder {
        public TextView mTextViewIdPenyewa, mTextViewNamaPenyewa,
                mTextViewAlamat,mTextViewNoHP;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdPenyewa = (TextView) itemView.findViewById(R.id.tvIdPenyewa);
            mTextViewNamaPenyewa = (TextView) itemView.findViewById(R.id.tvNamaPenyewa);
            mTextViewAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            mTextViewNoHP = (TextView) itemView.findViewById(R.id.tvNoHp);
        }
    }

    
}
