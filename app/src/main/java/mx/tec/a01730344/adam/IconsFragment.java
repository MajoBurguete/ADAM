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


public class IconsFragment extends Fragment {

    ConstraintLayout clIcon;
    TextView tvIconDescription;


    public IconsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_icons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clIcon = view.findViewById(R.id.clIcon);
        tvIconDescription = view.findViewById(R.id.tvIconDescription);
        tvIconDescription.setText("¡Consigue 2700 puntos en 'Te cuento un cuento' Para obtener este Ícono!");

        clIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(IconsFragment.this).commit();
                clIcon.setClickable(false);
            }
        });


    }


}