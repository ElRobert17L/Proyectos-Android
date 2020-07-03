package mx.com.rlr.seccion_07_tabs_lab.Interfaces;

import mx.com.rlr.seccion_07_tabs_lab.Models.Person;

// Interfaz definida en fichero aparte para la comunicación entre el FormFragment
// y el ListFragment, a través del MainAcitivty
public interface OnPersonCreated {
    void createPerson(Person person);
}

