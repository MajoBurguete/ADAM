package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton ibBack;
    ImageView ivUsernameProf;
    TextView tvUsernameProf;
    TextView tvScoreR;
    TextView tvScoreC;
    TextView tvScoreF;

    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = findViewById(R.id.toolbar2);
        ibBack = findViewById(R.id.ibBackFromProf);
        ivUsernameProf = findViewById(R.id.ivUsernameProf);
        tvUsernameProf = findViewById(R.id.tvUsernameProf);
        tvScoreR = findViewById(R.id.tvArcoirisScore);
        tvScoreC = findViewById(R.id.tvTeCuentoUnCuentoScore);
        tvScoreF = findViewById(R.id.tvEnFormitasScore);


        user.loadProfiles();

        ivUsernameProf.setImageResource(user.getCurrentUserMini());
        tvUsernameProf.setText(user.getCurrentUser());
        tvScoreR.setText(String.valueOf(user.getCurrentUserScoreR()));
        tvScoreC.setText(String.valueOf(user.getCurrentUserScoreC()));
        tvScoreF.setText(String.valueOf(user.getCurrentUserScoreF()));

        setSupportActionBar(toolbar);
        toolbarActions();

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(UserProfileActivity.this, HomeActivity.class);
                startActivity(toHome);
            }
        });
    }

    private void toolbarActions() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btnSettings){
            int value = 2;
            Intent toSettings = new Intent(UserProfileActivity.this, SettingsActivity.class);
            toSettings.putExtra("screen",value);
            startActivity(toSettings);
        }
        else if(item.getItemId() == R.id.btnBackProfile){
            Intent toMain = new Intent(UserProfileActivity.this, MainActivity.class);
            startActivity(toMain);
        }
        return super.onOptionsItemSelected(item);
    }


}