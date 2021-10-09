package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ImageButton ibProfileIcon1;
    TextView tvprofileName1;
    TextView tvprofileName2;
    TextView tvprofileName3;
    ImageButton ibProfileIcon2;
    ImageButton ibProfileIcon3;
    ImageButton ibAddProfile;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user.loadProfiles();

        if (user.getCount() == 0){
            Intent toLogin = new Intent(this, LoginActivity.class);
            toLogin.putExtra("image", 0);
            startActivity(toLogin);
        }

        ibProfileIcon1 = findViewById(R.id.ibProfileIcon1);
        tvprofileName1 = findViewById(R.id.tvprofileName1);
        ibProfileIcon2 = findViewById(R.id.ibProfileIcon2);
        tvprofileName2 = findViewById(R.id.tvprofileName2);
        ibProfileIcon3 = findViewById(R.id.ibProfileIcon3);
        tvprofileName3 = findViewById(R.id.tvprofileName3);
        ibAddProfile = findViewById(R.id.ibAddProfile);

        int count = user.getCount();
        Log.d("MAIN", "onCreate: " + count);

        switch (count){
            case 1:
                ibProfileIcon1.setImageResource(user.getImage("user1image"));
                tvprofileName1.setText(user.getUsername("user1"));
                ibProfileIcon2.setVisibility(View.GONE);
                tvprofileName2.setVisibility(View.GONE);
                ibProfileIcon3.setVisibility(View.GONE);
                tvprofileName3.setVisibility(View.GONE);
                break;
            case 2:
                ibProfileIcon1.setImageResource(user.getImage("user1image"));
                tvprofileName1.setText(user.getUsername("user1"));
                ibProfileIcon2.setImageResource(user.getImage("user2image"));
                tvprofileName2.setText(user.getUsername("user2"));
                ibProfileIcon2.setVisibility(View.VISIBLE);
                tvprofileName2.setVisibility(View.VISIBLE);
                ibProfileIcon3.setVisibility(View.GONE);
                tvprofileName3.setVisibility(View.GONE);
                break;
            case 3:
                ibProfileIcon1.setImageResource(user.getImage("user1image"));
                tvprofileName1.setText(user.getUsername("user1"));
                ibProfileIcon2.setImageResource(user.getImage("user2image"));
                tvprofileName2.setText(user.getUsername("user2"));
                ibProfileIcon3.setImageResource(user.getImage("user3image"));
                tvprofileName3.setText(user.getUsername("user3"));
                ibProfileIcon2.setVisibility(View.VISIBLE);
                tvprofileName2.setVisibility(View.VISIBLE);
                ibProfileIcon3.setVisibility(View.VISIBLE);
                tvprofileName3.setVisibility(View.VISIBLE);
                ibAddProfile.setVisibility(View.GONE);
                break;
            default:
                ibProfileIcon1.setVisibility(View.GONE);
                ibProfileIcon2.setVisibility(View.GONE);
                ibProfileIcon3.setVisibility(View.GONE);
                break;
        }

        ibAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(MainActivity.this, LoginActivity.class);
                toLogin.putExtra("image", -1);
                startActivity(toLogin);
            }
        });
        ibProfileIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setCurrentUser(user.getUsername("user3"), user.getImage("user3image"), user.getImage("user3mini"), "user3");
                ToHome();
            }
        });
        ibProfileIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setCurrentUser(user.getUsername("user2"), user.getImage("user2image"), user.getImage("user2mini"), "user2");
                ToHome();
            }
        });
        ibProfileIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setCurrentUser(user.getUsername("user1"), user.getImage("user1image"), user.getImage("user1mini"), "user1");
                ToHome();
            }
        });
    }

    private void ToHome(){
        Intent toHome = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(toHome);
    }


}