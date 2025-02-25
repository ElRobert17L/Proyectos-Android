package mx.com.rlr.seccion_07_tabs_lab.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import mx.com.rlr.seccion_07_tabs_lab.Interfaces.OnPersonCreated;
import mx.com.rlr.seccion_07_tabs_lab.Models.Country;
import mx.com.rlr.seccion_07_tabs_lab.Models.Person;
import mx.com.rlr.seccion_07_tabs_lab.R;
import mx.com.rlr.seccion_07_tabs_lab.Utils.Util;

public class PersonFormFragment extends Fragment {

    private EditText editTextName;
    private Spinner spinnerCountry;
    private Button btnCreate;

    private List<Country> countries;

    private OnPersonCreated onPersonCreated;

    public PersonFormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_form, container, false);

        editTextName = (EditText) view.findViewById(R.id.editTextName);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinnerCountry);
        btnCreate = (Button) view.findViewById(R.id.buttonCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewPerson();
            }
        });
        // Obtenemos todos los países
        countries = Util.getCountries();

        // Creamos un ArrayAdapter para ser pasado a nuestro Spinner/DropDown/Lista despegable
        // Con un layout integrado en Android para su uso directo, llamado
        // simple_spinner_dropdown_item
        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        spinnerCountry.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    private void createNewPerson() {
        if (!editTextName.getText().toString().isEmpty()) {
            // Recogemos el país seleccionado de la siguiente manera
            Country country = (Country) spinnerCountry.getSelectedItem();
            Person person = new Person(editTextName.getText().toString(), country);
            // Usamos la interfaz para comunicarnos con el MainActivity y éste con el otro fragment
            onPersonCreated.createPerson(person);
        }
    }

    // Eventos para enlazar el listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPersonCreated) {
            onPersonCreated = (OnPersonCreated) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnPersonCreated");
        }
    }
    // Eventos para desenlazar el listener
    @Override
    public void onDetach() {
        super.onDetach();
        onPersonCreated = null;
    }
}