package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    ImageButton ibProfileIcon1;
    ImageButton ibProfileIcon2;
    ImageButton ibAddProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibProfileIcon1 = findViewById(R.id.ibProfileIcon1);
        ibProfileIcon2 = findViewById(R.id.ibProfileIcon2);
        ibAddProfile = findViewById(R.id.ibAddProfile);

        ibAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        });
        ibProfileIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToHome();
            }
        });
        ibProfileIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToHome();
            }
        });
    }

    private void ToHome(){
        Intent toHome = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(toHome);
    }


}