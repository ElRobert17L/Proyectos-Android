package mx.com.rlr.seccion_02_lab_fruitworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //ListView, GridView y Adapters
    private ListView listView;
    private GridView gridView;
    private MyAdapter myAdapterListView;
    private MyAdapter myAdapterGridView;

    //Lista de nuestro modelo, fruta
    private List<Fruit> fruits;

    //Items en el option menu
    private MenuItem itemListView;
    private MenuItem itemGridView;

    //Variables
    private int counter = 0;
    private static int SWITCH_TO_LIST_VIEW = 0;
    private static int SWITCH_TO_GRID_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mostrar icono
        this.enforceIconBar();

        this.fruits = getAllFruits();

        //Instanciamos
        listView = (ListView) findViewById(R.id.listView);
        gridView = (GridView) findViewById(R.id.gridView);

        //Adnjuntamos el mismo metodo, click par ambos
        this.listView.setOnItemClickListener(this);
        this.gridView.setOnItemClickListener(this);

        //Enlazamos con nuestro adaptador personalizado
        this.myAdapterListView = new MyAdapter(this, R.layout.list_item, fruits);
        this.myAdapterGridView = new MyAdapter(this, R.layout.grid_item, fruits);

        this.listView.setAdapter(myAdapterListView);
        this.gridView.setAdapter(myAdapterGridView);

        //Registrar el context menu para ambos
        registerForContextMenu(this.listView);
        registerForContextMenu(this.gridView);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.clickFruit(fruits.get(position));
    }

    private void clickFruit(Fruit fruit) {
        //Diferenciamos entre las frutas conocidas y desconocidas
        if (fruit.getOrigin().equals("Unknown")) {
            Toast.makeText(this, "Sorry, we don't have many info about " + fruit.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "The best fruit from " + fruit.getOrigin() + " is " + fruit.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo CRUD
    private List<Fruit> getAllFruits() {
        //Datos a mostrar
        List<Fruit> list = new ArrayList<Fruit>() {{
            add(new Fruit ("Banana", R.mipmap.ic_banana, "Gran Canaria"));
            add(new Fruit ("Strawberry", R.mipmap.ic_strawberry, "Huelva"));
            add(new Fruit ("Orange", R.mipmap.ic_orange, "Sevilla"));
            add(new Fruit ("Apple", R.mipmap.ic_apple, "Madrid"));
            add(new Fruit ("Cherry", R.mipmap.ic_cherry, "Galicia"));
            add(new Fruit ("Pear", R.mipmap.ic_pear, "Zaragoza"));
            add(new Fruit ("Raspberry", R.mipmap.ic_raspberry, "Barcelona"));
        }};
        return list;
    }

    //Añadir una Fruta
    private void addFruit(Fruit fruit) {
        this.fruits.add(fruit);
        //Avisamos del cambio en ambos adapters
        this.myAdapterListView.notifyDataSetChanged();
        this.myAdapterGridView.notifyDataSetChanged();
    }

    //Borramos una fruta
    private void deleteFruit(int position) {
        this.fruits.remove(position);
        //Avisamos del cambio en ambos adaptadores
        this.myAdapterListView.notifyDataSetChanged();
        this.myAdapterGridView.notifyDataSetChanged();
    }

    //Metodo Icono
    private void enforceIconBar() {
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //Inflamos el layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        //Despues de inflar, recogemos las referencias a los botones que nos interesan
        this.itemListView = menu.findItem(R.id.lis_view);
        this.itemGridView = menu.findItem(R.id.grid_view);
        return true;
    }

    //Manejamos eventos, click en el menu de opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                this.addFruit(new Fruit("Added n° " + (++counter), R.mipmap.ic_new_fruit, "Unknown"));
                return true;
            case R.id.lis_view:
                this.switchListGridView(this.SWITCH_TO_LIST_VIEW);
                return true;    
            case R.id.grid_view:
                this.switchListGridView(this.SWITCH_TO_GRID_VIEW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Metodo para cambiar entre Grid/List view
    private void switchListGridView(int option) {
        if (option == SWITCH_TO_LIST_VIEW) {
            //Si queremos cambiar a list view, y el list view esta en modo invisible
            if (this.listView.getVisibility() == View.INVISIBLE) {
                //escondemos el grid view, y enseñamos su boton en el menu de opciones
                this.gridView.setVisibility(View.INVISIBLE);
                this.itemGridView.setVisible(true);
                //no olvidamos enseñar el list view, y esconder su boton en el menu de opciones
                this.listView.setVisibility(View.VISIBLE);
                this.itemListView.setVisible(false);
            } else if (option == SWITCH_TO_GRID_VIEW) {
                //Si queremos cambiar a grid view, y el list view esta en modo invisible
                if (this.gridView.getVisibility() == View.INVISIBLE) {
                    //escondemos el list view, y enseñamos su boton en el menu de opciones
                    this.listView.setVisibility(View.INVISIBLE);
                    this.itemListView.setVisible(true);
                    //no olvidamos enseñar el grid view, y esconder su boton en el menu de opciones
                    this.gridView.setVisibility(View.VISIBLE);
                    this.itemGridView.setVisible(false);
                }
            }
        }
    }

    //Inflamos el layout del context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Inflamos el context menu con nuestro layout
        MenuInflater inflater = getMenuInflater();
        //Antes de inflar, le añadimos el header dependiendo del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        //Inflamos
        inflater.inflate(R.menu.context_menu, menu);
    }

    //Manejamos eventos click en el context menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_item:
                deleteFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}