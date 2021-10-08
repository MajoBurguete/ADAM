package mx.tec.a01730344.adam;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ArcoirisFragment extends Fragment {

    Button btnJugarArcoiris;

    public ArcoirisFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arcoiris, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnJugarArcoiris = view.findViewById(R.id.btnJugarArcoiris);

        btnJugarArcoiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRainbowActivityNVL1 = new Intent(getActivity(), RainbowActivityNVL1.class);
                startActivity(toRainbowActivityNVL1);
            }
        });
    }
}