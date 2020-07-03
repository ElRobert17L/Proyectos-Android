package mx.com.rlr.seccion_04_realm_lab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import mx.com.rlr.seccion_04_realm_lab.R;
import mx.com.rlr.seccion_04_realm_lab.models.City;

public class NewCity extends AppCompatActivity {

    private int cityId;
    private boolean isCreation;

    private City city;
    private Realm realm;

    private EditText editTextCityName;
    private EditText editTextCityDescription;
    private EditText editTextCityLink;
    private ImageView cityImage;
    private Button btnPreview;
    private FloatingActionButton fab;
    private RatingBar ratingBarCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_city);

        realm = Realm.getDefaultInstance();
        bindUIReferences();

        // Comprobar si va a ser una acción para editar o para creación
        if (getIntent().getExtras() != null) {
            cityId = getIntent().getExtras().getInt("idCity");
            isCreation = false;
        } else {
            isCreation = true;
        }

        setActivityTitle();

        if (!isCreation) {
            city = getCityById(cityId);
            bindDataToFields();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditNewCity();
            }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = editTextCityLink.getText().toString();
                if (link.length() > 0) {
                    loadImageLinkForPreview(editTextCityLink.getText().toString());
                }
            }
        });
    }

    private void bindUIReferences() {
        editTextCityName = (EditText) findViewById(R.id.editTextCityName);
        editTextCityDescription = (EditText) findViewById(R.id.editTextCityDescription);
        editTextCityLink = (EditText) findViewById(R.id.editTextCityImage);
        cityImage = (ImageView) findViewById(R.id.imageViewPreview);
        btnPreview = (Button) findViewById(R.id.buttonPreview);
        fab = (FloatingActionButton) findViewById(R.id.FABEditCity);
        ratingBarCity = (RatingBar) findViewById(R.id.ratingBarCity);
    }

    private void setActivityTitle() {
        String title = "Edit City";
        if (isCreation) {
            title = "Create New City";
            setTitle(title);
        }
    }

    private City getCityById(int cityId) {
        return realm.where(City.class).equalTo("idCity", cityId).findFirst();
    }

    private void bindDataToFields() {
        editTextCityName.setText(city.getCityTitle());
        editTextCityDescription.setText(city.getCityDescription());
        editTextCityLink.setText(city.getCityImage());
        loadImageLinkForPreview(city.getCityImage());
        ratingBarCity.setRating(city.getCityScore());
    }

    private void loadImageLinkForPreview(String link) {
        Glide.with(this).load(link).fitCenter().into(cityImage);
    }

    private void addEditNewCity() {
        if (isValidDataForNewCity()) {
            String name = editTextCityName.getText().toString();
            String description = editTextCityDescription.getText().toString();
            String link = editTextCityLink.getText().toString();
            float stars = ratingBarCity.getRating();

            City city = new City(name, description, link, stars);
            // En caso de que sea una edición en vez de creación pasamos el ID
            if (!isCreation) {
                city.setIdCity(cityId);
            }

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(city);
            realm.commitTransaction();

            goToMainActivity();
        } else {
            Toast.makeText(this, "The data is not valid, please check the fields again", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidDataForNewCity() {
        if (editTextCityName.getText().toString().length() > 0 &&
            editTextCityDescription.getText().toString().length() > 0 &&
            editTextCityLink.getText().toString().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(NewCity.this, MainActivity.class);
        startActivity(intent);
    }
}