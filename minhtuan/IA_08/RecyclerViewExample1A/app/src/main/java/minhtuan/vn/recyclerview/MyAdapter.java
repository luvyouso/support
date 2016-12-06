package minhtuan.vn.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mDataset;
    private TextView txtMsg;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Integer vitri = position;
        holder.txtData.setText(mDataset[position]);
        holder.txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = " Position: " + vitri + "  " + mDataset[vitri];
                txtMsg.setText(text);
                notifyItemChanged(vitri);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public MyAdapter(String[] myDataset, TextView txtmsg) {
        mDataset = myDataset;
        txtMsg = txtmsg;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtData;

        public ViewHolder(View itemView) {
            super(itemView);
            txtData=(TextView)itemView.findViewById(android.R.id.text1);
        }
    }
}
