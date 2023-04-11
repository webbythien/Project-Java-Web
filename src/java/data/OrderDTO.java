package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thien
 */
public class OrderDTO {
    private int id;
    private int user_id;
    private String date;
    private int status;
    private int quantity;
    private int product_id;
    private float totalPrice;
    private String details;

    public OrderDTO(int id, int user_id, String date, int status, int quantity, int product_id, float totalPrice, String details) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.status = status;
        this.quantity = quantity;
        this.product_id = product_id;
        this.totalPrice = totalPrice;
        this.details = details;
    }

    
    
    public OrderDTO(int id, String date, int status, int product_id) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.product_id = product_id;
    }

    public OrderDTO(int id, int quantity, int product_id, float totalPrice, String details) {
        this.id = id;
        this.quantity = quantity;
        this.product_id = product_id;
        this.totalPrice = totalPrice;
        this.details = details;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    
}
