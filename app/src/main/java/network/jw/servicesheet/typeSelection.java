package network.jw.servicesheet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class typeSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);
        findViewById(R.id.button5).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup rg = findViewById(R.id.radioGroup1);
                final Integer radioSel = rg.getCheckedRadioButtonId();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("type", ((Button)findViewById(radioSel)).getText());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
