package minhtuan.vn.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private String[] mDataset;
    Integer[] thumbnails;
    private TextView txtMsg;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_icon_label,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Integer vitri = position;
        holder.icon.setImageResource(thumbnails[position]);
        holder.label.setText(mDataset[position]);
        holder.label.setOnClickListener(new View.OnClickListener() {
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

    public MyAdapter(String[] myDataset, Integer[] thumbNails, TextView txtmsg) {
        mDataset = myDataset;
        thumbnails = thumbNails;
        txtMsg = txtmsg;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon;
        private TextView label;

        public ViewHolder(View itemView) {
            super(itemView);
            icon=(ImageView)itemView.findViewById(R.id.icon);
            label=(TextView)itemView.findViewById(R.id.label);
        }
    }
}
