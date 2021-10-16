/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mx.tec.a01730344.adam.R;
import mx.tec.a01730344.adam.Models.User;

//Actividad que se muestra para crear un perfil al establecer un nombre de usuario y una foto de perfil

public class LoginActivity extends AppCompatActivity {

    //Declaración de todos los componentes necesarios para el funcionamiento del fragmento.
    FloatingActionButton fabPickPict;
    ImageView ivProfileImageLogin;
    Button btnEnter;
    ImageButton ibBack;
    EditText etUsername;
    User user = new User(this);
    int image = R.drawable.ic_pp_hex_medium;
    int mini = R.drawable.ic_pp_hex_min;
    int devOptions;

    //Función que realiza acciones al crear la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Se checa si el dispositivo tiene las funciones de desarrollador activadas
        devOptions = Settings.Secure.getInt(this.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);

        //En caso de que las funciones de desarrollador hayan sido encontradas habilitadas, se procede a cerrar la aplicación automáticamente
        if (devOptions == 1) {
            finishAndRemoveTask();
        }

        //Enlace entre las variables declaradas y los componentes del layout
        fabPickPict = findViewById(R.id.fabEditImageLogin);
        btnEnter = findViewById(R.id.btnLogin);
        ibBack = findViewById(R.id.ibBackFromLogin);
        etUsername = findViewById(R.id.etUsername);
        ivProfileImageLogin = findViewById(R.id.ivProfileImageLogin);

        //Proceso para obtener la imagen de perfil el distintos tamaños y desplegarla
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

        //Funciones para validar la longitud del nombre de usuario y activar el botón de Guardar.
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

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
            public void afterTextChanged(Editable editable) { }
        });

        //Funcionalidad del botón de editar foto de perfil
        fabPickPict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 0;
                Intent toProfilePictAct = new Intent(LoginActivity.this, ProfilePictureActivity.class);
                toProfilePictAct.putExtra("screen",value);
                startActivity(toProfilePictAct);
            }
        });

        //Funcionalidad del botón de entrar
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

        //Funcionalidad del botón de regresar
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMainAct = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(toMainAct);
            }
        });

    }
}