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

//Actividad creada para Desplegar el carrusel de juegos a elegir, el perfil con su nombre e icono, y la barra con botones para configuracion y regresar a seleccion de perfiles.
public class HomeActivity extends AppCompatActivity {

    //declaracion de todos los componentes necesarios para el funcionamiento del fragmento.
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
    //Instancia de clase User para poder acceder a los datos guardados en Properties
    User user = new User(this);

    //funcion que realiza acciones al crear la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //se carga la informacion de los usuarios
        user.loadProfiles();
        //enlace entre las variables declaradas y los componentes del layout
        setContentView(R.layout.activity_home);
        clToProfile = findViewById(R.id.clToProfile);
        toolbar = findViewById(R.id.toolbar);
        ivProfPictHome = findViewById(R.id.ivProfPictHome);
        tvUsernameHome = findViewById(R.id.tvUsernameHome);
        ivProfPictHome.setImageResource(user.getCurrentUserMini());
        tvUsernameHome.setText(user.getCurrentUser());
        ibForwardFragment = findViewById(R.id.ibForwardFragment);
        ibBackFragment = findViewById(R.id.ibBackFragment);
        //se crean los fragmentos que se desplegaran en el carrusel
        arcoirisFragment = new ArcoirisFragment();
        formitasFragment = new FormitasFragment();
        teCuentoFragment = new TeCuentoFragment();

        //se despliega el fragmento del juego arcoiris de manera inicial
        FragmentTransaction fts = fragmentManager.beginTransaction();
        fts.replace(R.id.flGames, arcoirisFragment).commit();

        //se despliega el toolbar con el menu creado.
        setSupportActionBar(toolbar);
        toolbarActions();

        //Funcion para detectar la interaccion del usuario con el boton para regresar a la pantalla de el perfil del usuario
        clToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 0;
                Intent toProfile = new Intent(HomeActivity.this, UserProfileActivity.class);
                //Elemento que se manda a la siguiente pantalla para poder identificar de que actividad viene para que sepa a donde regresar si es necesario
                toProfile.putExtra("screen",value);
                startActivity(toProfile);
            }
        });
        //Funcion para detectar la interaccion del usuario con los botones flecha para cambiar el fragmento del juego actual al siguiente
        // index: 1 = En Formitas, 2 = Te Cuento Un Cuento, 0 = Arcoiris
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
        //Funcion para detectar la interaccion del usuario con los botones flecha para cambiar el fragmento del juego actual al anterior
        // index: 1 = En Formitas, 2 = Te Cuento Un Cuento, 0 = Arcoiris
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
    //Funcion que detecta si el usuario realizo gestos tactiles (deslizar a la derecha/izquierda) para cambiar el fragmento del juego actual al siguiente o anterior
    // index: 1 = En Formitas, 2 = Te Cuento Un Cuento, 0 = Arcoiris
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                //condicional para moverse adelante
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
                //condicional para moverse atras
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
    //Se deshabilita el titulo del toolbar
    private void toolbarActions(){
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    //funcion necesaria para el uso del toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //funcion para detectar la interaccion del usuario con los botones incorporados en el toolbar para enviarlo a las pantallas de settings o de seleccion de perfil
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btnSettings){
            int value = 1;
            Intent toSettings = new Intent(HomeActivity.this, SettingsActivity.class);
            //Elemento que se manda a la siguiente pantalla para poder identificar de que actividad viene para que sepa a donde regresar si es necesario
            toSettings.putExtra("screen",value);
            startActivity(toSettings);
        }
        if(item.getItemId() == R.id.btnBackProfile){
            Intent toMain = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(toMain);
        }
        return super.onOptionsItemSelected(item);
    }

    //Funcion que deshabilita el boton de regreso integrado en los celulares android
    @Override
    public void onBackPressed() {
    }
}