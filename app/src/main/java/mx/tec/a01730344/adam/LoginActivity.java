package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton fabPickPict;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fabPickPict = findViewById(R.id.fabEditImageLogin);
        btnEnter = findViewById(R.id.btnLogin);

        fabPickPict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfilePictAct = new Intent(LoginActivity.this, ProfilePictureActivity.class);
                startActivity(toProfilePictAct);
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomeAct = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(toHomeAct);
            }
        });
        }
    }