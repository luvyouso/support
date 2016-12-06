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

    String[] myDataset = { "Line1", "Line2", "Line3", "Line4",
            "Line5", "Line6", "Line7", "Line8", "Line9", "Line10",
            "Line11", "Line12", "Line13", "Line14", "Line15" };

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
        mAdapter = new MyAdapter(myDataset, txtMsg);
        mRecyclerView.setAdapter(mAdapter);
    }
}
