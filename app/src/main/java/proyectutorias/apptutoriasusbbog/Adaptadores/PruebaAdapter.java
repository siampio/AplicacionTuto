package proyectutorias.apptutoriasusbbog.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import proyectutorias.apptutoriasusbbog.ClasesDatabase.Users;
import proyectutorias.apptutoriasusbbog.R;

/**
 * Created by LOPEZPALACIOS on 25/09/2017.
 */

public class PruebaAdapter extends RecyclerView.Adapter<PruebaAdapter.UserViewHolder>
        implements ItemClickListener, Filterable {

    /**
     * Lista de objetos {@link Users} que representan la fuente de datos
     * de inflado
     */
    private List<Users> items;
    private List<Users> originalList;

    /*
    Contexto donde actua el recycler view
     */
    private Context context;


    public PruebaAdapter(List<Users> items, Context context) {
        this.context = context;
        this.items = items;
        this.originalList = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PruebaAdapter.UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_tutor, viewGroup, false);
        return new PruebaAdapter.UserViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(PruebaAdapter.UserViewHolder viewHolder, int i) {

        viewHolder.nombre.setText(items.get(i).getNombre());

    }

    class UserViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView nombre;
        public ItemClickListener listener;

        public UserViewHolder(View v, ItemClickListener listener) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.txtNameTutorList);

            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }

    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(context, "Item: "+items.get(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items = (List<Users>) results.values;
            PruebaAdapter.this.notifyDataSetChanged();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Users> filteredResults = null;
            if (constraint.length() == 0) {
                filteredResults = originalList;
            } else {
                filteredResults = getFilteredResults(constraint.toString().toLowerCase());
            }

            FilterResults results = new FilterResults();
            results.values = filteredResults;

            return results;
        }
    };
}

    protected List<Users> getFilteredResults(String constraint) {
        List<Users> results = new ArrayList<>();

        for (Users item : originalList) {
            if (item.getNombre().toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }


}






