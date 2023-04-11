/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author thien
 */
public class ProductDTO {

    private int id;
    private String name;
    private String color;
    private float price;
    private String imaga_link;
    private int quantity;
    private float weight;
    private String category;
    private String origin;
    private String description;

    public ProductDTO(int id, String name, String description, String color, float price, String imaga_link, int quantity, float weight, String category, String origin) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.imaga_link = imaga_link;
        this.quantity = quantity;
        this.weight = weight;
        this.category = category;
        this.origin = origin;
        this.description = description;

    }
    
    public ProductDTO( String name, String description, String color, float price, String imaga_link, int quantity, float weight, String category, String origin) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.imaga_link = imaga_link;
        this.quantity = quantity;
        this.weight = weight;
        this.category = category;
        this.origin = origin;
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImaga_link() {
        return imaga_link;
    }

    public void setImaga_link(String imaga_link) {
        this.imaga_link = imaga_link;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
