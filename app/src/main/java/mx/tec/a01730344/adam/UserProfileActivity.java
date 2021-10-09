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
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = findViewById(R.id.toolbar2);
        ibBack = findViewById(R.id.ibBackFromProf);
        ivUsernameProf = findViewById(R.id.ivUsernameProf);
        tvUsernameProf = findViewById(R.id.tvUsernameProf);

        user.loadProfiles();

        ivUsernameProf.setImageResource(user.getCurrentUserMini());
        tvUsernameProf.setText(user.getCurrentUser());

        setSupportActionBar(toolbar);
        toolbarActions();

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(UserProfileActivity.this, HomeActivity.class);
                startActivity(home);
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
            Intent settings = new Intent(UserProfileActivity.this, SettingsActivity.class);
            startActivity(settings);
        }
        return super.onOptionsItemSelected(item);
    }
}