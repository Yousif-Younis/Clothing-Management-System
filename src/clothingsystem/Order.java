/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clothingsystem;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.TextArea;

/**
 *
 * @author lenovo
 */
public class Order implements Serializable {
   private String orderdetails;
  private static ArrayList<Product> products; //arraylist gher arraylist beta3et products 3adya
   private static final long serialVersionUID = -3305505529070801528L;  
  private Cart c;
  private  static int ordernumber=1;
  private int orderid;
  private Date date;
Customer customer;
    Product loadedProduct;
     Cashier cashier ;

    public Date getDate() {
        return date;
    }
  
  
  public Order(){
    orderid=ordernumber;
    products=new ArrayList<>(); 
    ordernumber++;
}

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    
    
  
//  public void getOrderDetails(){
//        System.out.println("Order id:" + orderid);
//        System.out.println("order number: " + ordernumber);
//      for(int i=0;i<products.size();i++){
//            System.out.println("Name of product is : " +products.get(i).getNameofproduct());
//            System.out.println("Quantity : " +products.get(i).getQuantity());
//            System.out.println("Product ID : " +products.get(i).getProductID());
//            System.out.println("total Price of item : " + products.get(i).getPrice());
//           System.out.println("Price : " + products.get(i).gettotalitem());
//        }
//  }
 public void setOrderDetails(Cart cart) {
    List<Product> cartProducts = cart.getProducts();
    ArrayList<Product> loadedProducts = new ArrayList<>();
    for (Product cartProduct : cartProducts) {
        loadedProduct = new Product();
        loadedProduct.setNameofproduct(cartProduct.getNameofproduct());
        loadedProduct.setQuantity(cartProduct.getQuantity());
        loadedProduct.setProductID(cartProduct.getProductID());
        loadedProduct.setPrice(cartProduct.getPrice());
        loadedProducts.add(loadedProduct);
    }
    setProducts(loadedProducts);
    saveToFile("orders.bin");
}

  public String getOrderDetails() {
    StringBuilder orderDetailsString = new StringBuilder();
    orderDetailsString.append("Order id:").append(orderid).append("\n");
    orderDetailsString.append("order number: ").append(ordernumber).append("\n");

    for (int i = 0; i < products.size(); i++) {
        orderDetailsString.append("Name of product is : ").append(products.get(i).getNameofproduct()).append("\n");
        orderDetailsString.append("Quantity : ").append(products.get(i).getQuantity()).append("\n");
        orderDetailsString.append("Product ID : ").append(products.get(i).getProductID()).append("\n");
        orderDetailsString.append("total Price of item : ").append(products.get(i).getPrice()).append("\n");
        orderDetailsString.append("Price : ").append(products.get(i).gettotalitem()).append("\n");
    }
    return orderDetailsString.toString();
}

    void setProducts(ArrayList<Product> arrayList) {
        this.products = products;}

    int getCustomerId() {
       return customer.getCustomerid();
    }

      public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.gettotalitem();
        }
        return totalPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    void setCashierId(String cashierId) {
      
       cashier.getCashierID();
    }

  public static void saveToFile(String fileName) {
    System.out.println("enter the save function");
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(products);
        System.out.println("object written to file");
        System.out.println("Objects saved to " + fileName);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static ArrayList<Product> loadFromFile(String fileName) throws FileNotFoundException, IOException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            products = (ArrayList<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
       return products;
    }
  
  
  
}