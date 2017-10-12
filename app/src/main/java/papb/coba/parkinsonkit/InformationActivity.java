package papb.coba.parkinsonkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class InformationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.menu_information, menu);
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

        if (id == R.id.nav_information) {
            Intent information_intent = new Intent(this, InformationActivity.class);
            startActivity(information_intent);
        } else if (id == R.id.nav_identification) {
            Intent identification_intent = new Intent(this, TremorIdentificationActivity.class);
            startActivity(identification_intent);
        } else if (id == R.id.nav_training) {
            Intent training_intent = new Intent(this, MainActivity.class);
            startActivity(training_intent);
        } else if (id == R.id.nav_community) {

        } else if (id == R.id.nav_share) {
            Intent komunitas_intent = new Intent(this, InformationKomunitas.class);
            startActivity(komunitas_intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void OnClick(View view)
    {
        switch(view.getId()) {
            case R.id.tentangparkinson:
                Intent tentang_intent = new Intent(this, InformationTentang.class);
                startActivity(tentang_intent);
                break;

            case R.id.penyebab:
                Intent penyebab_intent = new Intent(this, InformationPenyebab.class);
                startActivity(penyebab_intent);
                break;

            case R.id.penanganan:
                Intent penanganan_intent = new Intent(this, InformationPenanganan.class);
                startActivity(penanganan_intent);
                break;

            case R.id.gejala:
                Intent gejala_intent = new Intent(this, InformationGejala.class);
                startActivity(gejala_intent);
                break;

            case R.id.komunitas:
                Intent komunitas_intent = new Intent(this, InformationKomunitas.class);
                startActivity(komunitas_intent);
                break;
        }
    }
}
