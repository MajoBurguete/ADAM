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
    int image = R.drawable.ic_pp_hex;
    int medium = R.drawable.ic_pp_hex_medium;
    int mini = R.drawable.ic_pp_hex_min;

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
                image = R.drawable.ic_pp_cuadradito;
                medium = R.drawable.ic_pp_cuadradito_medium;
                mini = R.drawable.ic_pp_cuadradito_mini;
                toLogin();
            }
        });

        ibIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = R.drawable.ic_pp_triangulito;
                medium = R.drawable.ic_pp_triangulito_medium;
                mini = R.drawable.ic_pp_triangulito_mini;
                toLogin();
            }
        });

        ibIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = R.drawable.ic_pp_circulito;
                medium = R.drawable.ic_pp_circulito_medium;
                mini = R.drawable.ic_pp_circulito_mini;
                toLogin();
            }
        });

        ibIcon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = R.drawable.ic_pp_onditas;
                medium = R.drawable.ic_pp_onditas_medium;
                mini = R.drawable.ic_pp_onditas_mini;

                toLogin();
            }
        });

        ibIcon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = R.drawable.ic_pp_estrellita;
                medium = R.drawable.ic_pp_estrellita_medium;
                mini = R.drawable.ic_pp_estrellita_mini;
                toLogin();
            }
        });
    }

    private void toLogin() {
        Intent toLogin = new Intent(ProfilePictureActivity.this, LoginActivity.class);
        toLogin.putExtra("image",image);
        toLogin.putExtra("medium", medium);
        toLogin.putExtra("mini", mini);
        startActivity(toLogin);
    }
}