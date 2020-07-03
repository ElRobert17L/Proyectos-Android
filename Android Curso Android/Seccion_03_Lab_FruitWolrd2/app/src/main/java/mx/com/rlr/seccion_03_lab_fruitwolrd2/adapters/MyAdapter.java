package mx.com.rlr.seccion_03_lab_fruitwolrd2.adapters;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import mx.com.rlr.seccion_03_lab_fruitwolrd2.R;
import mx.com.rlr.seccion_03_lab_fruitwolrd2.models.Fruit;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener itemClickListener;

    public MyAdapter(List<Fruit> fruits, int layout, Activity activity, OnItemClickListener itemClickListener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflamos el layout y se lo pasamos al constructor del ViewHolder, donde manejaremos
        // toda la lógica como extraer los datos, referencias...
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Llamamos al metodo Bind del ViewHolder pasandole objeto y listener
        holder.bind(fruits.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

        // Elementos UI a rellenar
        public TextView textViewFruit;
        public TextView textViewDescription;
        public TextView textViewQuantity;
        public ImageView imageViewFruit;

        public ViewHolder(View itemView) {
            // Recibe la View completa. La pasa al constructor padre y enlazamos referencias UI
            // con nuestras propiedades ViewHolder declaradas justo arriba.
            super((itemView));
            textViewFruit = (TextView) itemView.findViewById(R.id.textViewName);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewQuantity = (TextView) itemView.findViewById(R.id.textViewQuantity);
            imageViewFruit = (ImageView) itemView.findViewById(R.id.imageViewBackground);
            //Añadimos al view el listener para el context menu, en vez de hacerlo en el
            //activity mediante el metodo registerForContextMenu
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruit fruit, final OnItemClickListener listener) {
            //Procesamos los datos a renderizar
            textViewFruit.setText(fruit.getFruit());
            textViewDescription.setText(fruit.getDescription());
            textViewQuantity.setText(fruit.getQuantity() + "");
            //imageViewFruit.setImageResource(fruit.getFruitImage());
            //Picasso.get().load(fruit.getFruitImage()).into(imageViewFruit);
            Glide.with(activity).load(fruit.getFruitImage()).into(imageViewFruit);
            // Definimos que por cada elemento de nuestro recycler view, tenemos un click listener
            // que se comporta de la siguiente manera...
            this.imageViewFruit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });
        }

        //Sobreescribimos onCreateContextMenu, dentro del ViewHolder, en vez de hacerlo en el activity
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //Recogemos la posicion con el metodo getAdapterPosition
            Fruit fruitSelected = fruits.get(this.getAdapterPosition());
            //Antes de inflar, le añadimo el header con un icono dependiendo del objeto que se pinche
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            contextMenu.setHeaderTitle(fruitSelected.getFruit());
            contextMenu.setHeaderIcon(fruitSelected.getFruitIcon());
            //Inflamos el context menu con nuestro layout inflater
            MenuInflater inflater = activity.getMenuInflater();
            //Inflamos
            inflater.inflate(R.menu.context_menu_fruit, contextMenu);
            //Añadimos el onContetxtItemSelected
            for (int i = 0; i < contextMenu.size(); i++) {
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        //Sobreescribimos onMenuItemClick, dentro del ViewHolder
        //en vez de hacerlo en el activity bajo el nombre de ContextItemSelected
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete_fruit:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset_fruit_quantity:
                    fruits.get(getAdapterPosition()).resetQuantity();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Fruit fruit, int position);
    }
}
