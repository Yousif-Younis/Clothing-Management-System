/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clothingsystem;
import java.util.*;

import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class Cart {
      private int totalamount;
    private ArrayList<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public int gettotalamount() {
        for (int i = 0; i < products.size(); i++) {
            totalamount += products.get(i).gettotalitem();
        }
        return totalamount;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
    public Order createOrder(Order order) {
         order = new Order();
        order.setProducts(new ArrayList<>(products));
        return order;
    }
}