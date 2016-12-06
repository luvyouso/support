package minhtuan.vn.slidingpanellayout;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import minhtuan.vn.slidingpanellayout.fragment.LeftMenuItem1Fragment;
import minhtuan.vn.slidingpanellayout.fragment.LeftMenuItem2Fragment;
import minhtuan.vn.slidingpanellayout.fragment.LeftMenuItem3Fragment;
import minhtuan.vn.slidingpanellayout.fragment.MainFragment;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnLeftMenuItem1;
    private Button btnLeftMenuItem2;
    private Button btnLeftMenuItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeftMenuItem1=(Button)findViewById(R.id.btnLeftMenuItem1);
        btnLeftMenuItem2=(Button)findViewById(R.id.btnLeftMenuItem2);
        btnLeftMenuItem3=(Button)findViewById(R.id.btnLeftMenuItem3);

        btnLeftMenuItem1.setOnClickListener(this);
        btnLeftMenuItem2.setOnClickListener(this);
        btnLeftMenuItem3.setOnClickListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.contentFrameLayout, new MainFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        if(v == btnLeftMenuItem1)
            fm.beginTransaction().replace(R.id.contentFrameLayout, new LeftMenuItem1Fragment()).commit();
        else if(v == btnLeftMenuItem2)
            fm.beginTransaction().replace(R.id.contentFrameLayout, new LeftMenuItem2Fragment()).commit();
        else if(v == btnLeftMenuItem3)
            fm.beginTransaction().replace(R.id.contentFrameLayout, new LeftMenuItem3Fragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.action_Home) {
            fm.beginTransaction().replace(R.id.contentFrameLayout, new MainFragment()).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
