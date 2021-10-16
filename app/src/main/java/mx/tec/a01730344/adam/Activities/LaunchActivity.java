package mx.tec.a01730344.adam.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    int devOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se checa si el dispositivo tiene las funciones de desarrollador activadas
        devOptions = Settings.Secure.getInt(this.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);

        //En caso de que las funciones de desarrollador hayan sido encontradas habilitadas, se procede a cerrar la aplicación automáticamente
        if (devOptions == 1) {
            finishAndRemoveTask();
        }

        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
        finish();
    }
}
