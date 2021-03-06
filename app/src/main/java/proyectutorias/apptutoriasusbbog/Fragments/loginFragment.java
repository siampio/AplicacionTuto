package proyectutorias.apptutoriasusbbog.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import proyectutorias.apptutoriasusbbog.R;

/**
 * Created by LOPEZPALACIOS on 22/09/2017.
 */

public class loginFragment extends Fragment{
    Switch perfil;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        perfil = (Switch)rootView.findViewById(R.id.switchPerfil);
        perfil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    perfil.setText("Tutor");
                }else {
                    perfil.setText("Estudiante");
                }
            }
        });

        return rootView;
    }
}
