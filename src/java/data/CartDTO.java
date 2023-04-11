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
public class CartDTO {

    private int customerID;
    private int productID;
    private int quantity;
    private String p_image_link;
    private String p_description;
    private float p_price;
    private int p_quantity;
    private String p_name;

    public CartDTO(int customerID, int productID, String p_name, int quantity, String p_image_link, String p_description, float p_price, int p_quantity) {
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
        this.p_image_link = p_image_link;
        this.p_description = p_description;
        this.p_price = p_price;
        this.p_quantity = p_quantity;
        this.p_name = p_name;
        
    }

    public CartDTO(int customerID, int productID, int quantity) {
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public CartDTO(int customerID, int productID) {
        this.customerID = customerID;
        this.productID = productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getP_image_link() {
        return p_image_link;
    }

    public void setP_image_link(String p_image_link) {
        this.p_image_link = p_image_link;
    }

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public float getP_price() {
        return p_price;
    }

    public void setP_price(float p_price) {
        this.p_price = p_price;
    }

    public int getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(int p_quantity) {
        this.p_quantity = p_quantity;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

}
