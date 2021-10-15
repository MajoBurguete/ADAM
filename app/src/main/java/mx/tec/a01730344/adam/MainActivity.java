/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

//Actividad creada para desplegar los perfiles creados para que el usuario escoja, así como la opción de crear uno nuevo.
public class MainActivity extends AppCompatActivity {

    //Declaración de todos los componentes necesarios para el funcionamiento del fragmento.
    ImageButton ibProfileIcon1;
    TextView tvprofileName1;
    TextView tvprofileName2;
    TextView tvprofileName3;
    ImageButton ibProfileIcon2;
    ImageButton ibProfileIcon3;
    ImageButton ibAddProfile;
    //Instancia de clase User para poder acceder a los datos guardados en Properties
    User user = new User(this);
    int devOptions;

    //Función que realiza acciones al crear la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Se checa si el dispositivo tiene las funciones de desarrollador activadas
        devOptions = Settings.Secure.getInt(this.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);

        //En caso de que las funciones de desarrollador hayan sido encontradas habilitadas, se procede a cerrar la aplicación automáticamente
        if (devOptions == 1) {
            finishAndRemoveTask();
        }
  
        //Se carga la información de los usuarios
        user.loadProfiles();
        //Condicional que checa si no hay usuarios, para mandarlos de manera directa a la actividad de LoginActivity paras crear un perfil
        if (user.getCount() == 0){
            Intent toLogin = new Intent(this, LoginActivity.class);
            //se manda un dato para indicar que no existia un perfil antes.
            toLogin.putExtra("image", 0);
            startActivity(toLogin);
        }
        //Enlace entre las variables declaradas y los componentes del layout
        ibProfileIcon1 = findViewById(R.id.ibProfileIcon1);
        tvprofileName1 = findViewById(R.id.tvprofileName1);
        ibProfileIcon2 = findViewById(R.id.ibProfileIcon2);
        tvprofileName2 = findViewById(R.id.tvprofileName2);
        ibProfileIcon3 = findViewById(R.id.ibProfileIcon3);
        tvprofileName3 = findViewById(R.id.tvprofileName3);
        ibAddProfile = findViewById(R.id.ibAddProfile);

        //Se obtiene el numero de usuarios actuales
        int count = user.getCount();

        //Condicionales que despliegan componentes dependiendo de la cantidad de usuarios y la información que se tiene sobre ellos
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

        //Función para detectar la interacción del usuario con el botón para crear un usuario, mandándolo a la pantalla de LoginActivity
        ibAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(MainActivity.this, LoginActivity.class);
                //se manda un dato para indicar que se quiere crear un nuevo perfil pero que ya existian otros previamente.
                toLogin.putExtra("image", -1);
                startActivity(toLogin);
            }
        });
        //Función para detectar la interacción del usuario con el botón para escoger su perfil y mandarlo a la pantalla de homeActivity
        ibProfileIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se guarda en Properties el usuario en sesión con toda su información necesaria.
                user.setCurrentUser(user.getUsername("user3"), user.getImage("user3image"), user.getImage("user3mini"), "user3",user.getScoreR("user3scoreR"),user.getScoreC("user3scoreC"),user.getScoreF("user3scoreF"));
                ToHome();
            }
        });
        //Función para detectar la interacción del usuario con el botón para escoger su perfil y mandarlo a la pantalla de homeActivity
        ibProfileIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se guarda en Properties el usuario en sesión con toda su información necesaria.
                user.setCurrentUser(user.getUsername("user2"), user.getImage("user2image"), user.getImage("user2mini"), "user2",user.getScoreR("user2scoreR"),user.getScoreC("user2scoreC"),user.getScoreF("user2scoreF"));
                ToHome();
            }
        });
        //Función para detectar la interacción del usuario con el botón para escoger su perfil y mandarlo a la pantalla de homeActivity
        ibProfileIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se guarda en Properties el usuario en sesión con toda su información necesaria.
                user.setCurrentUser(user.getUsername("user1"), user.getImage("user1image"), user.getImage("user1mini"), "user1",user.getScoreR("user1scoreR"),user.getScoreC("user1scoreC"),user.getScoreF("user1scoreF"));
                ToHome();
            }
        });
    }
    //Función para mandar al usuario a la pantalla de HomeActvity
    private void ToHome(){
        Intent toHome = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(toHome);
    }
}