package mx.com.rlr.seccion_03_lab_fruitwolrd2.models;

public class Fruit {

    private String fruit;
    private String description;
    private int quantity;
    private int fruitImage;
    private int fruitIcon;

    //Valores accesibles estaticamente
    public static  final int LIMIT_QUANTITY = 10;
    public static  final int RESET_VALUE_QUANTITY = 0;

    public Fruit() {
    }

    public Fruit(String fruit, String description, int quantity, int fruitImage, int fruitIcon) {
        this.fruit = fruit;
        this.description = description;
        this.quantity = quantity;
        this.fruitImage = fruitImage;
        this.fruitIcon = fruitIcon;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFruitImage() {
        return fruitImage;
    }

    public void setFruitImage(int fruitImage) {
        this.fruitImage = fruitImage;
    }

    public int getFruitIcon() {
        return fruitIcon;
    }

    public void setFruitIcon(int fruitIcon) {
        this.fruitIcon = fruitIcon;
    }

    //Añadir cantidad
    public void addQuantity(int quantity) {
        if (this.quantity < LIMIT_QUANTITY) {
            this.quantity += quantity; //quantity = quantity + 1;
        }
    }

    //Reset cantidad
    public void resetQuantity() {
        this.quantity = RESET_VALUE_QUANTITY;
    }
}
