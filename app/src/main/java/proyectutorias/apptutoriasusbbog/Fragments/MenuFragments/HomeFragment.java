package proyectutorias.apptutoriasusbbog.Fragments.MenuFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import proyectutorias.apptutoriasusbbog.Adaptadores.UserAdapter;
import proyectutorias.apptutoriasusbbog.ClasesDatabase.Users;
import proyectutorias.apptutoriasusbbog.R;
import proyectutorias.apptutoriasusbbog.WebService.Constantes;
import proyectutorias.apptutoriasusbbog.WebService.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private static final String TAG = HomeFragment.class.getSimpleName();
    /*
    Adaptador del recycler view
     */
    private UserAdapter adapter;

    /*
    Instancia global del recycler view
     */
    private RecyclerView lista;

    /*
    instancia global del administrador
     */
    private RecyclerView.LayoutManager lManager;

    /*
    Instancia global del FAB
     */

    private Gson gson = new Gson();


    public HomeFragment() {
        // Required empty public constructor
    }

    //ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        setHasOptionsMenu(true);

        lista = (RecyclerView) v.findViewById(R.id.reciclador);
        lista.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        lista.setLayoutManager(lManager);

        // Cargar datos en el adaptador
        cargarAdaptador();



//        final ListView listView = (ListView)v.findViewById(R.id.listview_prueba);
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.array_country)));
//
//        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(adapter);
//        listView.setTextFilterEnabled(true);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item =  getResources().getStringArray(R.array.array_country)[i];
//                Intent intent = new Intent(getActivity(), TutorProfile.class);
//                intent.putExtra("NAME",item);
//                startActivity(intent);
//            }
//        });


        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_search:
                return false;
            default:
                break;
        }
        return false;
    }

    public void cargarAdaptador() {
        // Petición GET
        VolleySingleton.
                getInstance(getActivity()).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                Constantes.GET_TUTOR,
                                null,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Procesar la respuesta Json
                                        procesarRespuesta(response);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(TAG, "Error Volley: " + error.toString());
                                    }
                                }

                        )
                );
    }

    /**
     * Interpreta los resultados de la respuesta y así
     * realizar las operaciones correspondientes
     *
     * @param response Objeto Json con la respuesta
     */
    private void procesarRespuesta(JSONObject response) {
        try {
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "1": // EXITO
                    // Obtener array "user" Json
                    JSONArray mensaje = response.getJSONArray("user");
                    // Parsear con Gson
                    Users[] post = gson.fromJson(mensaje.toString(), Users[].class);
                    // Inicializar adaptador
                    adapter = new UserAdapter(Arrays.asList(post), getActivity());
                    // Setear adaptador a la lista
                    lista.setAdapter(adapter);
                    break;
                case "2": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }

    }
}
