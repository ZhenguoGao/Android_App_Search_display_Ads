package com.example.zhenguo.my_example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int position=0;
    String[] k={"Legislator","Bills","Committees","favorites","about"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setToolbarTitle();
    }
    private void setToolbarTitle(){getSupportActionBar().setTitle(k[position]);}

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            position=0;
            setToolbarTitle();
            LegislatorFragment legislatorFragment= new LegislatorFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().setCustomAnimations(R.anim.anim_slide_in_from_left,R.anim.anim_slide_our_from_left).replace(R.id.relativelayout_for_fragment,legislatorFragment, legislatorFragment.getTag()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            position=1;
            setToolbarTitle();
            BillsFragment billsFragment= BillsFragment.newInstance("some1","some2");
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,billsFragment, billsFragment.getTag()).commit();


        } else if (id == R.id.nav_slideshow) {
            position=2;
            setToolbarTitle();
            CommitteesFragment committeesFragment= new CommitteesFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,committeesFragment, committeesFragment.getTag()).commit();

        } else if (id == R.id.nav_manage) {
            position=3;
            setToolbarTitle();
            FavoriteFragment favoriteFragment= new FavoriteFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,favoriteFragment, favoriteFragment.getTag()).commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            position=4;
            setToolbarTitle();
            aboutFragment aboutfragment= new aboutFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,aboutfragment, aboutfragment.getTag()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
