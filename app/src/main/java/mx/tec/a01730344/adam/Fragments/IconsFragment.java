/* Integración de seguridad informática en redes y sistemas de software (TC2007B.1)
   ADAM: Aplicación para el Desarrollo de Atención y Memoria
   Fecha: 17/10/2021
   Creado por: María José Burguete Euán
               Aarón Cortés García
               Marco Flamenco Andrade
               Daniela Hernández y Hernández
*/

package mx.tec.a01730344.adam.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.tec.a01730344.adam.R;

//Fragmento creado para desplegar la informacion de como conseguir los iconos bloqueados
public class IconsFragment extends Fragment {

    //Declaración de todos los componentes necesarios para el funcionamiento del fragmento.
    ConstraintLayout clIcon;
    TextView tvIconDescription;

    //Funciones necesarias para que el fragmento se ejecute de manera correcta.
    public IconsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_icons, container, false);
    }

    //Función añadida para realizar acciones al crear el fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Declaración del Bundle para poder recibir información de la pantalla de donde viene
        Bundle bundle = this.getArguments();
        int achievement = bundle.getInt("achievement");
        //enlace entre las variables declaradas y los componentes del layout
        clIcon = view.findViewById(R.id.clIcon);
        tvIconDescription = view.findViewById(R.id.tvIconDescription);
        //Dependiendo del valor enviado de la actividad, se despliega un texto diferente que corresponde a las instrucciones para desbloquear el icono con el que se interactuo.
        if (achievement == 1){
            tvIconDescription.setText("¡Consigue 2,700 puntos en 'Te cuento un cuento' para obtener este ícono!");
        } else if (achievement == 2) {
            tvIconDescription.setText("¡Consigue 10,000 puntos en 'Arcoiris' para obtener este ícono!");
        } else {
            tvIconDescription.setText("¡Consigue 2,500 puntos en todos los juegos para obtener este ícono!");
        }

        //Declaración para detectar la interacción del usuario con el layout y asi poder eliminar el fragmento
        clIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(IconsFragment.this).commit();
                clIcon.setClickable(false);
            }
        });


    }


}