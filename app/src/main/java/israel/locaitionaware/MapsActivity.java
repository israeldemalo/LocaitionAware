package israel.locaitionaware;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.SupportMapFragment;

public class MapsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        SupportMapFragment mapFragment = new SupportMapFragment();

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame1, mapFragment).
                commit();
    }


}
