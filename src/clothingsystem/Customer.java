/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clothingsystem;

import java.io.*;
import java.util.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lenovo
 */

public class Customer extends User implements Serializable {
    private int numberofordercustomer;
    private String orderdetailscustomer;
    private ArrayList<Order> OrderHistory;
    private int customerid;
    int nooforders;
    Cashier c;
    int revenue;
    int quantity;
    Order o;
    private HashMap<String,Integer> orderrate;
    public Customer(int id,String username, String password) {
        super(username, password,"Customer");
        this.customerid = id;
        this.orderrate=new HashMap<>();
        this.OrderHistory = new ArrayList<>(); 
    }
    
    public ArrayList<Order> getOrderHistory() {
        return OrderHistory;
    }

   public void addorderhistory(Order order, String cashierId) throws IOException {
        this.OrderHistory.add(order);
        nooforders++;
      order.setCashierId(cashierId);
        o.calculateTotalPrice(); 
       OrderHistory.add(order);
        revenue += order.calculateTotalPrice();
        saveToFile(order, "customerorder.bin", getCustomerid(), cashierId);
    }
    public int getNooforders() {
        return nooforders;
    }

    public void setNooforders(int nooforders) {
        this.nooforders = nooforders;
    }
    

    public void setRevenue(int revenue) {
        revenue+=c.cartcost();
        
    }
    public int getrevenue()
    { return revenue;
    }
    
    public Map<String, Integer> getOrderRatings() {
        return orderrate;
    }
    public void rateOrder(String orderId, int stars) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Stars should be between 1 and 5");
        }
        orderrate.put(orderId, stars);
    }
    
    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

private void saveToFile(Order order, String fileName, int customerId, String cashierId) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName, true))) {
            out.writeInt(customerId);
            out.writeObject(cashierId);
            out.writeObject(order.getOrderDetails());
            out.writeObject(order.getDate());
            System.out.println("Order details saved to " + fileName + " for customer ID " + customerId);
            out.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 public static ArrayList<Customer> loadCustomersFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<Customer> loadedCustomers = new ArrayList<>();

            while (true) {
                try {
                    Object loadedObject = in.readObject();

                    if (loadedObject instanceof Integer) {
                        int customerId = (int) loadedObject;
                        String cashierId = (String) in.readObject();
                        String orderDetails = (String) in.readObject();
                        Date orderDate = (Date) in.readObject();
                        Customer customer = new Customer(customerId, "username", "password");
                        Order order = new Order();
                      
                        order.setDate(orderDate);
                        customer.addorderhistory(order, cashierId);
                        loadedCustomers.add(customer);
                    } else {
                        break;
                    }
                } catch (EOFException e) {
                    break; 
                }
            }

            System.out.println("Customers loaded successfully from " + fileName);
            return loadedCustomers;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}

