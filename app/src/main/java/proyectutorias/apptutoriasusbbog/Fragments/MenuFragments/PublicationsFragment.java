package proyectutorias.apptutoriasusbbog.Fragments.MenuFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import proyectutorias.apptutoriasusbbog.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicationsFragment extends Fragment {


    public PublicationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_publications,container,false);

        Button btn_publications = (Button)v.findViewById(R.id.btn_publications);
        btn_publications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Button publications",Toast.LENGTH_SHORT).show();
            }
        });
        return v;

    }

}
