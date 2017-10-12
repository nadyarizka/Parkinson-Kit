package papb.coba.parkinsonkit;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TremorIdentificationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SensorEventListener, View.OnClickListener {

    private float mLastX, mLastY,mLastZ;
    private boolean mInitialized;
    private final float NOISE = (float)0.1;
    private long lastUpdate = 0;
    private int indexUpdate;
    long startTime;
    float[] delta = new float[1000];



    Sensor accelerometer;
    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tremor_identification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(this);

        mInitialized=false;
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

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
        getMenuInflater().inflate(R.menu.tremor_identification, menu);
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

    public void onResume(){
        super.onResume();
        sm.unregisterListener(this);
    }

    public void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    public void onSensorChanged(SensorEvent event)
    {

        TextView show_txt = (TextView)findViewById(R.id.show);

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        indexUpdate++;

        if (!mInitialized)
        {
            mLastX = x;
            mLastY = y;
            mLastZ = z;

            mInitialized = true;
            delta[indexUpdate] = (float)0.0;
        }
        else
        {
            float deltaX = Math.abs(mLastX - x);
            float deltaY = Math.abs(mLastY - y);
            float deltaZ = Math.abs(mLastZ - z);


            if(deltaX < NOISE) deltaX = (float)0.0;
            if(deltaX < NOISE) deltaY = (float)0.0;
            if(deltaX < NOISE) deltaZ = (float)0.0;

            mLastX = x;
            mLastY = y;
            mLastZ = z;

            delta[indexUpdate]= (deltaX + deltaY + deltaZ);

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 10) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                if ((curTime - startTime) > 3100 )
                {
                    sm.unregisterListener(this);

                    float total = 0;

                    for (int i=0; i<indexUpdate; i++)
                    {
                        total = total + delta[i];
                    }

                    double d = 0.5 * total * 3100;
                    float a = total/indexUpdate;

                    double freq = Math.sqrt((9.80665 * a)/(2*(22/7)*(22/7)*d));
                    show_txt.setText(Double.toString(a));
                }
                else
                if ((curTime - startTime) > 3000 )
                {
                    show_txt.setText("3");
                }
                else
                if ((curTime - startTime) > 2000 )
                {
                    show_txt.setText("2");
                }
                else
                if ((curTime - startTime) > 1000 )
                {
                    show_txt.setText("1");
                }

            }
        }
    }

    @Override
    public void onClick(View view) {
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        startTime = System.currentTimeMillis();
        indexUpdate = 0 ;
        TextView show_txt = (TextView)findViewById(R.id.show);
        show_txt.setText("0");
    }
}
