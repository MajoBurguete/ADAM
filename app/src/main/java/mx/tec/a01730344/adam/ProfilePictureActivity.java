package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProfilePictureActivity extends AppCompatActivity {

    ImageButton ibBackProfPic;
    ImageButton ibIcon1;
    ImageButton ibIcon2;
    ImageButton ibIcon3;
    ImageButton ibIcon4;
    ImageButton ibIcon5;
    ImageButton ibIcon6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture_layout);

        ibBackProfPic = findViewById(R.id.ibBackProfPic);
        ibIcon1 = findViewById(R.id.ibIcon1);
        ibIcon2 = findViewById(R.id.ibIcon2);
        ibIcon3 = findViewById(R.id.ibIcon3);
        ibIcon4 = findViewById(R.id.ibIcon4);
        ibIcon5 = findViewById(R.id.ibIcon5);
        ibIcon6 = findViewById(R.id.ibIcon6);

        ibBackProfPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        ibIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        ibIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        ibIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        ibIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        ibIcon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        ibIcon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });
    }

    private void toLogin() {
        Intent toLogin = new Intent(ProfilePictureActivity.this, LoginActivity.class);
        startActivity(toLogin);
    }
}