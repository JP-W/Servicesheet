package network.jw.servicesheet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        try {
            wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.finish();
    }
}
