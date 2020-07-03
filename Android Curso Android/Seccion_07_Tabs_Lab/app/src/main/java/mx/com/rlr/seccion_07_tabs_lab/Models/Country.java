package mx.com.rlr.seccion_07_tabs_lab.Models;

import java.text.MessageFormat;

public class Country {

    private String name;
    private String countryCode;

    public Country(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFlagURL() {
        return MessageFormat.format("https://www.countryflags.io/{0}/flat/64.png",
                this.getCountryCode());
        //return MessageFormat.format("http://www.geognos.com/api/en/countries/flag/{0}.png", this.getCountryCode());
    }

    // Importante!! Sobreescribimos el método toString de nuestra clase POJO Country
    // Para que cuando el Spinner llame internamente cada objeto, use el name para ser mostrado
    // como único valor posible en la lista desplegable
    @Override
    public String toString() {
        return name;
    }
}
