package minhtuan.vn.navactionbar;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import minhtuan.vn.navactionbar.fragment.LeftMenuItem1Fragment;
import minhtuan.vn.navactionbar.fragment.LeftMenuItem2Fragment;
import minhtuan.vn.navactionbar.fragment.LeftMenuItem3Fragment;
import minhtuan.vn.navactionbar.fragment.MainFragment;
import minhtuan.vn.navactionbar.fragment.RightMenuItem1Fragment;
import minhtuan.vn.navactionbar.fragment.RightMenuItem2Fragment;
import minhtuan.vn.navactionbar.fragment.RightMenuItem3Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationViewLeft = (NavigationView) findViewById(R.id.nav_view_left);
        navigationViewLeft.setNavigationItemSelectedListener(this);

        NavigationView navigationViewRight = (NavigationView) findViewById(R.id.nav_view_right);
        navigationViewRight.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.contentFrameLayout, new MainFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Home) {
            fm.beginTransaction().replace(R.id.contentFrameLayout, new MainFragment()).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.leftMenuItem1) {
            fm.beginTransaction().replace(R.id.contentFrameLayout, new LeftMenuItem1Fragment()).commit();
        } else if (id == R.id.leftMenuItem2) {
            fm.beginTransaction().replace(R.id.contentFrameLayout, new LeftMenuItem2Fragment()).commit();
        } else if (id == R.id.leftMenuItem3) {
            fm.beginTransaction().replace(R.id.contentFrameLayout, new LeftMenuItem3Fragment()).commit();
        } else if (id == R.id.rightMenuItem1) {
            fm.beginTransaction().replace(R.id.contentFrameLayout, new RightMenuItem1Fragment()).commit();
        } else if (id == R.id.rightMenuItem2) {
            fm.beginTransaction().replace(R.id.contentFrameLayout, new RightMenuItem2Fragment()).commit();
        } else if (id == R.id.rightMenuItem3) {
            fm.beginTransaction().replace(R.id.contentFrameLayout, new RightMenuItem3Fragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}
