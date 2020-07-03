package mx.com.rlr.seccion_04_realm_lab.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import mx.com.rlr.seccion_04_realm_lab.app.MyApplication;

public class City extends RealmObject {

    @PrimaryKey
    private int idCity;
    @Required
    private String cityTitle;
    @Required
    private String cityDescription;
    @Required
    private String cityImage;
    private float cityScore;

    public City() {
    }

    public City(String cityTitle, String cityDescription, String cityImage, float cityScore) {
        this.idCity = MyApplication.CityID.incrementAndGet();
        this.cityTitle = cityTitle;
        this.cityDescription = cityDescription;
        this.cityImage = cityImage;
        this.cityScore = cityScore;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    public String getCityImage() {
        return cityImage;
    }

    public void setCityImage(String cityImage) {
        this.cityImage = cityImage;
    }

    public float getCityScore() {
        return cityScore;
    }

    public void setCityScore(float cityScore) {
        this.cityScore = cityScore;
    }
}
