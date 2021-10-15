/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//ACtividad para editar la información del usuario (foto de perfil y nombre de usuario)

public class SettingsActivity extends AppCompatActivity {

    //Declaración de todos los componentes necesarios para el funcionamiento del fragmento.
    FloatingActionButton fabEditPictSett;
    Button btnDeleteSett;
    ImageView ivProfilePictsett;
    ImageButton ibBack;
    EditText etUsernameSett;
    Button btnSaveSett;
    User user = new User(this);
    int image;
    int mini;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Instancia de clase User para poder acceder a los datos guardados en Properties
        user.loadProfiles();

        //Enlace entre las variables declaradas y los componentes del layout
        image = user.getCurrentUserImage();mini = user.getCurrentUserMini();
        fabEditPictSett = findViewById(R.id.fabEditPictSett);
        btnDeleteSett = findViewById(R.id.btnDeleteSett);
        ibBack = findViewById(R.id.ibBackFromSett);
        ivProfilePictsett = findViewById(R.id.ivProfilePictSett);
        etUsernameSett = findViewById(R.id.etUsernameSett);
        btnSaveSett = findViewById(R.id.btnSaveSett);
        ivProfilePictsett.setImageResource(user.getCurrentUserImage());
        etUsernameSett.setText(user.getCurrentUser());

        //Funcionalidad para desplegar la información modificada en la pantalla anterior
        int previousScreen = getIntent().getExtras().getInt("screen");
        if (previousScreen == 0){
            int normalIm = getIntent().getExtras().getInt("image");
            image = getIntent().getExtras().getInt("medium");
            mini = getIntent().getExtras().getInt("mini");
            ivProfilePictsett.setImageResource(normalIm);
        }

        //Funciones para validar la longitud del nombre de usuario y activar el botón de Guardar.
        etUsernameSett.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etUsernameSett.getText().length() <= 10){
                    btnSaveSett.setEnabled(true);
                }
                else{
                    btnSaveSett.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        //Funcionalidad del botón para guardar los cambios
        btnSaveSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsernameSett.getText().toString();
                String currUserNumber = user.getCurrentUserNumber();
                Log.i("currUserNumber", currUserNumber);
                user.setCurrentUser(username, image, mini, currUserNumber,user.getCurrentUserScoreR(),user.getCurrentUserScoreC(),user.getCurrentUserScoreF());
                user.modifyUser(currUserNumber,username, image, mini);
                toPreviousScreen(previousScreen);
            }
        });

        //Botón para regresar
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPreviousScreen(previousScreen);
            }
        });

        //Funcionalidad del botón para editar la foto de perfil
        fabEditPictSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 1;
                Intent toProfilePict = new Intent(SettingsActivity.this, ProfilePictureActivity.class);
                toProfilePict.putExtra("screen",value);
                startActivity(toProfilePict);
            }
        });

        //Funcionlidad del botón para eliminar a un usuario
        btnDeleteSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.deleteUser(user.getCurrentUser());
                user.minusCount();
                Intent toMain = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });
    }

    //Función para volver a la pantalla de donde vino
    private void toPreviousScreen(int screen) {
        // screen = 1 is settings, 0 is login, 2 is UserProfile
         if (screen == 2){
            Intent toUserProfile = new Intent(SettingsActivity.this, UserProfileActivity.class);
            startActivity(toUserProfile);
        }
        else {
            Intent toHome = new Intent(SettingsActivity.this, HomeActivity.class);
            startActivity(toHome);
        }
    }
}