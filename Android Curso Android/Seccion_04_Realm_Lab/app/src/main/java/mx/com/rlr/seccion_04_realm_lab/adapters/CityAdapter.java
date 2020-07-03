package mx.com.rlr.seccion_04_realm_lab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import mx.com.rlr.seccion_04_realm_lab.R;
import mx.com.rlr.seccion_04_realm_lab.models.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;
    private List<City> cities;
    private int layout;
    private OnItemClickListener itemClickListener;
    private OnButtonClickListener btnClickListener;

    // Pasamos el activity en vez del context, ya que nos hará falta para poder inflar en context menu
    public CityAdapter(List<City> cities, int layout, OnItemClickListener itemClickListener, OnButtonClickListener btnClickListener) {
        this.cities = cities;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
        this.btnClickListener = btnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(cities.get(position), itemClickListener, btnClickListener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    // Implementamos las interfaces OnCreateContextMenuListener y OnMenuItemClickListener
    // para hacer uso del context menu en RecyclerView, y sobreescribimos los métodos

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityTitle;
        public TextView cityDescription;
        public ImageView cityImage;
        public TextView cityScore;
        public Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            cityTitle = (TextView) itemView.findViewById(R.id.textViewCityName);
            cityDescription = (TextView) itemView.findViewById(R.id.textViewCityDescription);
            cityImage = (ImageView) itemView.findViewById(R.id.imageViewCity);
            cityScore = (TextView) itemView.findViewById(R.id.textViewScore);
            btnDelete = (Button) itemView.findViewById(R.id.buttonDeleteCity);
        }

        public void bind(final City city, final OnItemClickListener itemListener, final OnButtonClickListener btnListener) {
            cityTitle.setText(city.getCityTitle());
            cityDescription.setText(city.getCityDescription());
            cityScore.setText(city.getCityScore() + "");
            // Cargamos la imagen con Glide
            Glide.with(context).load(city.getCityImage()).fitCenter().into(cityImage);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnListener.onButtonClick(city,getAdapterPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onItemClick(city, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(City city, int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick(City city, int position);
    }
}
