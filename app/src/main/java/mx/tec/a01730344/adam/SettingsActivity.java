package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {

    FloatingActionButton fabEditPictSett;
    Button btnDeleteSett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fabEditPictSett = findViewById(R.id.fabEditPictSett);
        btnDeleteSett = findViewById(R.id.btnDeleteSett);

        fabEditPictSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfilePict = new Intent(SettingsActivity.this, ProfilePictureActivity.class);
                startActivity(toProfilePict);
            }
        });

        btnDeleteSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMain = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });

    }
}