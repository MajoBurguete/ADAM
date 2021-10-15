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

//Actividad creada para Desplegar los puntajes mas altos del usuario en cada juego, el perfil con su nombre e icono, y la barra con botones para configuracion y regresar a seleccion de perfiles.

public class UserProfileActivity extends AppCompatActivity {

    //declaracion de todos los componentes necesarios para el funcionamiento del fragmento.
    Toolbar toolbar;
    ImageButton ibBack;
    ImageView ivUsernameProf;
    TextView tvUsernameProf;
    TextView tvScoreR;
    TextView tvScoreC;
    TextView tvScoreF;

    //Instancia de clase User para poder acceder a los datos guardados en Properties
    User user = new User(this);

    //funcion que realiza acciones al crear la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //se carga la informacion de los usuarios
        user.loadProfiles();

        //enlace entre las variables declaradas y los componentes del layout
        setContentView(R.layout.activity_user_profile);
        toolbar = findViewById(R.id.toolbar2);
        ibBack = findViewById(R.id.ibBackFromProf);
        ivUsernameProf = findViewById(R.id.ivUsernameProf);
        tvUsernameProf = findViewById(R.id.tvUsernameProf);
        tvScoreR = findViewById(R.id.tvArcoirisScore);
        tvScoreC = findViewById(R.id.tvTeCuentoUnCuentoScore);
        tvScoreF = findViewById(R.id.tvEnFormitasScore);
        ivUsernameProf.setImageResource(user.getCurrentUserMini());
        tvUsernameProf.setText(user.getCurrentUser());
        tvScoreR.setText(String.valueOf(user.getCurrentUserScoreR()));
        tvScoreC.setText(String.valueOf(user.getCurrentUserScoreC()));
        tvScoreF.setText(String.valueOf(user.getCurrentUserScoreF()));

        //se despliega el toolbar con el menu creado.
        setSupportActionBar(toolbar);
        toolbarActions();

        //Funcion para detectar la interaccion del usuario con el boton de regreso y mandarlo a la pantalla de HomeActivity
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(UserProfileActivity.this, HomeActivity.class);
                startActivity(toHome);
            }
        });
    }

    //Se deshabilita el titulo del toolbar
    private void toolbarActions() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    //funcion para detectar la interaccion del usuario con los botones incorporados en el toolbar para enviarlo a las pantallas de settings o de seleccion de perfil
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
            int value = 2;
            Intent toSettings = new Intent(UserProfileActivity.this, SettingsActivity.class);
            //Elemento que se manda a la siguiente pantalla para poder identificar de que actividad viene para que sepa a donde regresar si es necesario
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