package mx.com.rlr.seccion_03_lab_fruitwolrd2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.com.rlr.seccion_03_lab_fruitwolrd2.R;
import mx.com.rlr.seccion_03_lab_fruitwolrd2.adapters.MyAdapter;
import mx.com.rlr.seccion_03_lab_fruitwolrd2.models.Fruit;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits;

    private RecyclerView mRecyclerView;
    // Puede ser declarado como 'RecyclerView.Adapter' o como nuetra clase adaptador 'MyAdapter'
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = this.getAllFruits();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

        // Implementamos nuestro OnItemClickListener propio, sobreescribiendo el método que nosotros
        // definimos en el adaptador, y recibiendo los parámetros que necesitamos
        mAdapter = new MyAdapter(fruits, R.layout.recycler_view_item, this, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                fruit.addQuantity(1);
                mAdapter.notifyItemChanged(position);
            }
        });

        // Lo usamos en caso de que sepamos que el layout no va a cambiar de tamaño, mejorando la performance
        mRecyclerView.setHasFixedSize(true);

        // Enlazamos el layout manager y adaptor directamente al recycler view
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fruit:
                int position = fruits.size();
                this.addFruit(position);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Fruit> getAllFruits() {
        return new ArrayList<Fruit>() {{
            add((new Fruit("Apple", "Apple description", 0, R.drawable.apple_bg, R.mipmap.ic_apple)));
            add((new Fruit("Banana", "Banana description", 0, R.drawable.banana_bg, R.mipmap.ic_banana)));
            add((new Fruit("Cherry", "Cheery description", 0, R.drawable.cherry_bg, R.mipmap.ic_cherry)));
            add((new Fruit("Orange", "Orange description", 0, R.drawable.orange_bg, R.mipmap.ic_orange)));
            add((new Fruit("Pear", "Pear description", 0, R.drawable.pear_bg, R.mipmap.ic_pear)));
            add((new Fruit("Raspberry", "Raspbery description", 0, R.drawable.raspberry_bg, R.mipmap.ic_raspberry)));
            add((new Fruit("Strawberry", "Strawberry description", 0, R.drawable.strawberry_bg, R.mipmap.ic_strawberry)));
        }};
    }

    private void addFruit(int position) {
        fruits.add(position, new Fruit("Plum " + (++count), "Fruit added by the user", 0, R.drawable.plum_bg, R.mipmap.ic_plum));
        // Notificamos de un nuevo item insertado en nuestra colección
        mAdapter.notifyItemInserted(position);
        // Hacemos scroll hacia lo posición donde el nuevo elemento se aloja
        mLayoutManager.scrollToPosition(position);
    }

}