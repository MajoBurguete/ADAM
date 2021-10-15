package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;


public class HomeActivity extends AppCompatActivity {

    ConstraintLayout clToProfile;
    Toolbar toolbar;
    ImageView ivProfPictHome;
    TextView tvUsernameHome;
    ImageButton ibForwardFragment;
    ImageButton ibBackFragment;
    Fragment arcoirisFragment;
    Fragment formitasFragment;
    Fragment teCuentoFragment;
    int index = 0;
    float x1;
    float x2;
    float y1;
    float y2;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        clToProfile = findViewById(R.id.clToProfile);
        toolbar = findViewById(R.id.toolbar);
        ivProfPictHome = findViewById(R.id.ivProfPictHome);
        tvUsernameHome = findViewById(R.id.tvUsernameHome);

        user.loadProfiles();

        ivProfPictHome.setImageResource(user.getCurrentUserMini());
        tvUsernameHome.setText(user.getCurrentUser());
        ibForwardFragment = findViewById(R.id.ibForwardFragment);
        ibBackFragment = findViewById(R.id.ibBackFragment);
        arcoirisFragment = new ArcoirisFragment();
        formitasFragment = new FormitasFragment();
        teCuentoFragment = new TeCuentoFragment();

        FragmentTransaction fts = fragmentManager.beginTransaction();
        fts.replace(R.id.flGames, arcoirisFragment).commit();

        setSupportActionBar(toolbar);
        toolbarActions();


        clToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 0;
                Intent toProfile = new Intent(HomeActivity.this, UserProfileActivity.class);
                toProfile.putExtra("screen",value);
                startActivity(toProfile);
            }
        });

        ibForwardFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) {
                    index = 1;
                    FragmentTransaction fts = fragmentManager.beginTransaction();
                    fts.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    fts.replace(R.id.flGames, formitasFragment).commit();
                }
                else if (index == 1) {
                    index = 2;
                    FragmentTransaction fts = fragmentManager.beginTransaction();
                    fts.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    fts.replace(R.id.flGames, teCuentoFragment).commit();
                }
                else {
                    index = 0;
                    FragmentTransaction fts = fragmentManager.beginTransaction();
                    fts.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    fts.replace(R.id.flGames, arcoirisFragment).commit();
                }
            }
        });

        ibBackFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 2) {
                    index = 1;
                    FragmentTransaction fts = fragmentManager.beginTransaction();
                    fts.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fts.replace(R.id.flGames, formitasFragment).commit();
                }
                else if (index == 0) {
                    index = 2;
                    FragmentTransaction fts = fragmentManager.beginTransaction();
                    fts.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fts.replace(R.id.flGames, teCuentoFragment).commit();
                }
                else {
                    index = 0;
                    FragmentTransaction fts = fragmentManager.beginTransaction();
                    fts.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fts.replace(R.id.flGames, arcoirisFragment).commit();
                }
            }
        });
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 > x2){
                    if (index == 0) {
                        index = 1;
                        FragmentTransaction fts = fragmentManager.beginTransaction();
                        fts.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fts.replace(R.id.flGames, formitasFragment).commit();
                    }
                    else if (index == 1) {
                        index = 2;
                        FragmentTransaction fts = fragmentManager.beginTransaction();
                        fts.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fts.replace(R.id.flGames, teCuentoFragment).commit();
                    }
                    else {
                        index = 0;
                        FragmentTransaction fts = fragmentManager.beginTransaction();
                        fts.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fts.replace(R.id.flGames, arcoirisFragment).commit();
                    }
            }else if(x1 < x2){
                    if (index == 2) {
                        index = 1;
                        FragmentTransaction fts = fragmentManager.beginTransaction();
                        fts.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                        fts.replace(R.id.flGames, formitasFragment).commit();
                    }
                    else if (index == 0) {
                        index = 2;
                        FragmentTransaction fts = fragmentManager.beginTransaction();
                        fts.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                        fts.replace(R.id.flGames, teCuentoFragment).commit();
                    }
                    else {
                        index = 0;
                        FragmentTransaction fts = fragmentManager.beginTransaction();
                        fts.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                        fts.replace(R.id.flGames, arcoirisFragment).commit();
                    }
            }
            break;
        }
        return false;
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
            int value = 1;
            Intent toSettings = new Intent(HomeActivity.this, SettingsActivity.class);
            toSettings.putExtra("screen",value);
            startActivity(toSettings);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
    }
}