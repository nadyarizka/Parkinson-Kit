package papb.coba.parkinsonkit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Amalina Kurniasari on 26/11/2015.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onResume(){
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }

    public void OnClick(View view){
        switch(view.getId()) {
            case R.id.Informasi:
                Intent inf_intent = new Intent(this, InformationActivity.class);
                startActivity(inf_intent);
                break;

            case R.id.Tes:
                Intent idn_intent = new Intent(this, TremorIdentificationActivity.class);
                startActivity(idn_intent);
                break;

            case R.id.Training:
                Intent trn_intent = new Intent(this, TrainingActivity.class);
                startActivity(trn_intent);
                break;
        }

    }
}

