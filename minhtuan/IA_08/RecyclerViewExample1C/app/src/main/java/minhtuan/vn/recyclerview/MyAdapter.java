package minhtuan.vn.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private String[] mDataset;
    private TextView txtMsg;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_custom_line,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Integer vitri = position;
        holder.my_custom_textview.setText(mDataset[position]);
        holder.my_custom_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = " XLine selected= " + vitri + " " + mDataset[vitri];
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

        private TextView my_custom_textview;

        public ViewHolder(View itemView) {
            super(itemView);
            my_custom_textview=(TextView)itemView.findViewById(R.id.my_custom_textview);
        }
    }
}
