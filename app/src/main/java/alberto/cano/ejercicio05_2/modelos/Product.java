package alberto.cano.ejercicio05_2.modelos;

import java.io.Serializable;

public class Product implements Serializable {
    private  String name;
    private  int quantity;
    private float price;
    private  float total;

    public Product(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        updateTotal();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateTotal();
    }

    public int getQuantity() {
        return quantity;
    }



    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        updateTotal();
    }

    public float getTotal() {
        return total;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotal();
    }

    private void updateTotal() {
        this.total = this.quantity * this.price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
