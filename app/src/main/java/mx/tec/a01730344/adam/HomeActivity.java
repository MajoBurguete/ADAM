package mx.tec.a01730344.adam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HomeActivity extends AppCompatActivity {

    ConstraintLayout clToProfile;
    Toolbar toolbar;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        clToProfile = findViewById(R.id.clToProfile);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbarActions();

        clToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfile = new Intent(HomeActivity.this, UserProfileActivity.class);
                startActivity(toProfile);
            }
        });

        displayGames();
    }

    private void displayGames() {
        Fragment fragment = new TeCuentoFragment();
        FragmentTransaction fts = fragmentManager.beginTransaction();
        fts.replace(R.id.flGames, fragment).commit();
    }

    private void toolbarActions(){
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
            Intent settings = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(settings);
        }
        return super.onOptionsItemSelected(item);
    }
}