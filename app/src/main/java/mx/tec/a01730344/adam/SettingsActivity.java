package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {

    FloatingActionButton fabEditPictSett;
    Button btnDeleteSett;
    ImageButton ibBack;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        user.loadProfiles();

        fabEditPictSett = findViewById(R.id.fabEditPictSett);
        btnDeleteSett = findViewById(R.id.btnDeleteSett);
        ibBack = findViewById(R.id.ibBackFromSett);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(SettingsActivity.this, HomeActivity.class);
                startActivity(home);
            }
        });

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
                user.deleteUser(user.getCurrentUserNumber());
                user.minusCount();
                Intent toMain = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });

    }
}