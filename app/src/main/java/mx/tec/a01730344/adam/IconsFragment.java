package mx.tec.a01730344.adam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

//fragmento creado para desplegar la informacion de como conseguir los iconos bloqueados
public class IconsFragment extends Fragment {

    //declaracion de todos los componentes necesarios para el funcionamiento del fragmento.
    ConstraintLayout clIcon;
    TextView tvIconDescription;

    //funcion necesaria para que el fragmento se ejecute de manera correcta.
    public IconsFragment() {
    }

    //funcion necesaria para que el fragmento se ejecute de manera correcta.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_icons, container, false);
    }

    //funcion añadida para realizar acciones al crear el fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //declaracion del Bundle para poder recibir informacion de la pantalla de donde viene
        Bundle bundle = this.getArguments();
        int achievement = bundle.getInt("achievement");
        //enlace entre las variables declaradas y los componentes del layout
        clIcon = view.findViewById(R.id.clIcon);
        tvIconDescription = view.findViewById(R.id.tvIconDescription);
        //Dependiendo del valor enviado de la actividad, se despliega un texto diferente que corresponde a las instrucciones para desbloquear el icono con el que se interactuo.
        if (achievement == 1){
            tvIconDescription.setText("¡Consigue 2,700 puntos en 'Te cuento un cuento' para obtener este ícono!");
        }
        else if (achievement == 2){
            tvIconDescription.setText("¡Consigue 10,000 puntos en 'Arcoiris' para obtener este ícono!");
        }
        else {
            tvIconDescription.setText("¡Consigue 2,500 puntos en todos los juegos para obtener este ícono!");
        }
        //Funcion para detectar la interaccion del usuario con el layout y asi poder eliminar el fragmento
        clIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(IconsFragment.this).commit();
                clIcon.setClickable(false);
            }
        });


    }


}