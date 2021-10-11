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

public class TeCuentoFragment extends Fragment {

    Button btnJugarTeCuento;

    public TeCuentoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_te_cuento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnJugarTeCuento = view.findViewById(R.id.btnJugarTeCuento);

        btnJugarTeCuento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toStoryActivity = new Intent(getActivity(), StoryActivity.class);
                startActivity(toStoryActivity);
            }
        });
    }
}