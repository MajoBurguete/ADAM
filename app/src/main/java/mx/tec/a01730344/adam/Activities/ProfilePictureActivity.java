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
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import mx.tec.a01730344.adam.Fragments.IconsFragment;
import mx.tec.a01730344.adam.R;
import mx.tec.a01730344.adam.Models.User;

//Actividad que despliega las opciones de imagen de perfil disponibles y permite que el usuario elija una.

public class ProfilePictureActivity extends AppCompatActivity {

    //Declaración de todos los componentes necesarios para el funcionamiento del fragmento.
    ImageButton ibBackProfPic;
    ImageButton ibIcon1;
    ImageButton ibIcon2;
    ImageButton ibIcon3;
    ImageButton ibIcon4;
    ImageButton ibIcon5;
    ImageButton ibIcon6;
    boolean achievement1 = true;
    boolean achievement2 = true;
    boolean achievement3 = true;
    int image = R.drawable.ic_pp_hex;
    int medium = R.drawable.ic_pp_hex_medium;
    int mini = R.drawable.ic_pp_hex_min;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture_layout);

        //Instancia de clase User para poder acceder a los datos guardados en Properties
        user.loadProfiles();

        //Enlace entre las variables declaradas y los componentes del layout
        ibBackProfPic = findViewById(R.id.ibBackProfPic);
        ibIcon1 = findViewById(R.id.ibIcon1);
        ibIcon2 = findViewById(R.id.ibIcon2);
        ibIcon3 = findViewById(R.id.ibIcon3);
        ibIcon4 = findViewById(R.id.ibIcon4);
        ibIcon5 = findViewById(R.id.ibIcon5);
        ibIcon6 = findViewById(R.id.ibIcon6);

        int value = getIntent().getExtras().getInt("screen");

        //Proceso de validación para desplegar imágenes de perfil bloqueadas
        if (user.getCurrentUserScoreC() >= 2700 && value != 0){
            ibIcon4.setImageResource(R.drawable.ic_pp_circulito_medium);
            achievement1 = false;
        }
        if (user.getCurrentUserScoreR() >= 10000 && value != 0){
            ibIcon5.setImageResource(R.drawable.ic_pp_onditas_medium);
            achievement2 = false;
        }
        if (user.getCurrentUserScoreF() >= 2500 && user.getCurrentUserScoreR() >= 2500 && user.getCurrentUserScoreC() >= 2500 && value != 0){
            ibIcon6.setImageResource(R.drawable.ic_pp_estrellita_medium);
            achievement3 = false;
        }

        //Funcionalidad de los botones de navegación
        ibBackProfPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPreviousScreen(value);
            }
        });

        //Funcionalidad de selección de la imagen de perfil, junto con las variantes de tamaño
        ibIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPreviousScreen(value);
            }
        });

        ibIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = R.drawable.ic_pp_cuadradito;
                medium = R.drawable.ic_pp_cuadradito_medium;
                mini = R.drawable.ic_pp_cuadradito_mini;
                toPreviousScreen(value);
            }
        });

        ibIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = R.drawable.ic_pp_triangulito;
                medium = R.drawable.ic_pp_triangulito_medium;
                mini = R.drawable.ic_pp_triangulito_mini;
                toPreviousScreen(value);
            }
        });

        ibIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (achievement1){
                    Bundle bundle = new Bundle();
                    bundle.putInt("achievement", 1);
                    IconsFragment fragment = new IconsFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flProfilePicture, fragment).commit();
                }
                else {
                    image = R.drawable.ic_pp_circulito;
                    medium = R.drawable.ic_pp_circulito_medium;
                    mini = R.drawable.ic_pp_circulito_mini;
                    toPreviousScreen(value);
                }
            }
        });

        ibIcon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (achievement2){
                    Bundle bundle = new Bundle();
                    bundle.putInt("achievement", 2);
                    IconsFragment fragment = new IconsFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flProfilePicture, fragment).commit();
                }
                else {
                    image = R.drawable.ic_pp_onditas;
                    medium = R.drawable.ic_pp_onditas_medium;
                    mini = R.drawable.ic_pp_onditas_mini;
                    toPreviousScreen(value);
                }
            }
        });

        ibIcon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (achievement3){
                    Bundle bundle = new Bundle();
                    bundle.putInt("achievement", 3);
                    IconsFragment fragment = new IconsFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flProfilePicture, fragment).commit();
                }
                else {
                    image = R.drawable.ic_pp_estrellita;
                    medium = R.drawable.ic_pp_estrellita_medium;
                    mini = R.drawable.ic_pp_estrellita_mini;
                    toPreviousScreen(value);
                }
            }
        });
    }



    //Función para regresar a la pantalla desde donde vino el usuario.
    private void toPreviousScreen(int screen) {
        // screen = 1 is settings, else is login
        if (screen == 1){
            int value = 0;
            Intent toSettings = new Intent(ProfilePictureActivity.this, SettingsActivity.class);
            toSettings.putExtra("screen",value);
            toSettings.putExtra("image",image);
            toSettings.putExtra("medium", medium);
            toSettings.putExtra("mini", mini);
            startActivity(toSettings);
        } else {
            Intent toLogin = new Intent(ProfilePictureActivity.this, LoginActivity.class);
            toLogin.putExtra("image",image);
            toLogin.putExtra("medium", medium);
            toLogin.putExtra("mini", mini);
            startActivity(toLogin);
        }

    }
}