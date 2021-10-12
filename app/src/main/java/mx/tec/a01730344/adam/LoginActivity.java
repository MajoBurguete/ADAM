package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton fabPickPict;
    ImageView ivProfileImageLogin;
    Button btnEnter;
    ImageButton ibBack;
    EditText etUsername;
    User user = new User(this);
    int image = R.drawable.ic_pp_hex_medium;
    int mini = R.drawable.ic_pp_hex_min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fabPickPict = findViewById(R.id.fabEditImageLogin);
        btnEnter = findViewById(R.id.btnLogin);
        ibBack = findViewById(R.id.ibBackFromLogin);
        etUsername = findViewById(R.id.etUsername);
        ivProfileImageLogin = findViewById(R.id.ivProfileImageLogin);

        int normalIm = getIntent().getExtras().getInt("image");

        if (normalIm > 0){
            image = getIntent().getExtras().getInt("medium");
            mini = getIntent().getExtras().getInt("mini");
            ivProfileImageLogin.setImageResource(normalIm);
        }
        else if (normalIm == -1){
            ibBack.setVisibility(View.VISIBLE);
        }
        else {
            ibBack.setVisibility(View.GONE);
        }

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etUsername.getText().length() <= 10){
                    btnEnter.setEnabled(true);
                }
                else{
                    btnEnter.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fabPickPict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 0;
                Intent toProfilePictAct = new Intent(LoginActivity.this, ProfilePictureActivity.class);
                toProfilePictAct.putExtra("screen",value);
                startActivity(toProfilePictAct);
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                user.saveUser(username, image, mini);
                String newUser = "user" + String.valueOf(user.getCount());
                user.setCurrentUser(username, image, mini, newUser,0,0,0);
                Intent toHomeAct = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(toHomeAct);
            }
        });

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMainAct = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(toMainAct);
            }
        });

    }
}