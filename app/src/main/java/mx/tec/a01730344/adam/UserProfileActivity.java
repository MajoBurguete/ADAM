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
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//Actividad creada para desplegar los puntajes mas altos del usuario en cada juego, el perfil con
//su nombre e ícono, y la barra con botones para configuración y regresar a selección de perfiles.

public class UserProfileActivity extends AppCompatActivity {

    //Declaración de todos los componentes necesarios para el funcionamiento del fragmento.
    Toolbar toolbar;
    ImageButton ibBack;
    ImageView ivUsernameProf;
    TextView tvUsernameProf;
    TextView tvScoreR;
    TextView tvScoreC;
    TextView tvScoreF;

    //Instancia de clase User para poder acceder a los datos guardados en Properties
    User user = new User(this);

    //Función que realiza acciones al crear la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se carga la información de los usuarios
        user.loadProfiles();

        //Snlace entre las variables declaradas y los componentes del layout
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

        //Se despliega el toolbar con el menu creado.
        setSupportActionBar(toolbar);
        toolbarActions();

        //Función para detectar la interacción del usuario con el botón de regreso y mandarlo a la pantalla de HomeActivity
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

    //Función para detectar la interacción del usuario con los botones incorporados en el toolbar para enviarlo a las pantallas de settings o de selección de perfil
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Función para detectar la interacción del usuario con los botones incorporados en el toolbar para enviarlo a las pantallas de settings o de selección de perfil
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