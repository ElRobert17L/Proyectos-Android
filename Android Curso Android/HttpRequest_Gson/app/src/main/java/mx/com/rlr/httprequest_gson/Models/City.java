package mx.com.rlr.httprequest_gson.Models;

public class City {

    public int id;
    public String name;
    private String country;
    private String icon;
    private String description;
    private int temperature;

    public City() {
    }

    public City(int id, String name, String country, String icon, String description, int temperature) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.icon = icon;
        this.description = description;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    /*
    public static Temperature parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Temperature temp = gson.fromJson(response, Temperature.class);
        return temp;
    } */
}
