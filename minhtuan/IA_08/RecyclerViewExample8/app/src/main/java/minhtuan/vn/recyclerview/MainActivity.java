package minhtuan.vn.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class MainActivity extends Activity {

    public TextView txtMsg;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String[] myDataset = { "Data-1", "Data-2", "Data-3", "Data-4",
            "Data-5", "Data-6", "Data-7", "Data-8", "Data-9", "Data-10",
            "Data-11", "Data-12", "Data-13", "Data-14", "Data-15"};

    Integer[] thumbnails = { R.drawable.pic01_small, R.drawable.pic02_small, R.drawable.pic03_small,
            R.drawable.pic04_small, R.drawable.pic05_small, R.drawable.pic06_small, R.drawable.pic07_small,
            R.drawable.pic08_small, R.drawable.pic09_small, R.drawable.pic10_small, R.drawable.pic11_small,
            R.drawable.pic12_small, R.drawable.pic13_small, R.drawable.pic14_small, R.drawable.pic15_small};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1. Show layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. Find view id
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //3. use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        //4. use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //5. specify an adapter
        mAdapter = new MyAdapter(myDataset, thumbnails,txtMsg);
        mRecyclerView.setAdapter(mAdapter);
    }
}
