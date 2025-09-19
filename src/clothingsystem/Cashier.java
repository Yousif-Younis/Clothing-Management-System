/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clothingsystem;

import java.io.*;
import java.util.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class Cashier extends User implements Serializable {
    ArrayList<Order> order; //tezhar bel id bas mesh lazem history
    private int cashierID;
    public int nooforders;
    Cart cart;
    Order o=new Order();
    Customer c;
    int revenue;

    public Order getOrder() {
        return o;
    }
    

    public Cashier(int id,String username, String password) {
        super(username, password,"Cashier");
        cashierID=id;
        nooforders=0;
        cart = new Cart();
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
         revenue+=cartcost();
    }
    
     public void setOrder(Order order) {
        this.o = order;
    }

    
 public void FinalResult() {
    String orderDetails = o.getOrderDetails();
    System.out.println("Order Details:\n" + orderDetails);
    System.out.println("Cashier id: " + getCashierID());
    System.out.println("Customer id: " + c.getCustomerid());
    String dataToSave = "Order Details:\n" + orderDetails +
                        "\nCashier id: " + getCashierID() +
                        "\nCustomer id: " + c.getCustomerid();
    order.add(o);
    saveToFile(order, "Cashierorders.bin");
}


    
public void addInCart(Product e) {
    if (order == null) {
        order = new ArrayList<>(); 
    }

    if (order.contains(e)) {
        int newquantity = e.getQuantity() + 1;
        e.setQuantity(newquantity);
    } else {
        cart.addProduct(e);
        order.add(o); 
    }
}

    public void removeFromCart(Product e){ 
        if(order.contains(e) && e.getQuantity()>0){
            cart.removeProduct(e);
            int newquantity = e.getQuantity()-1;
            System.out.println(e.getNameofproduct()+" product removed succesfully");
            System.out.println("the new quantity is: " + newquantity);
        }
        else {
        System.out.println(e.getNameofproduct() + " product not found in the order");
    }
    }
     public int getNooforders() {
        return nooforders;
    }
      public int getCashierID() {
        return cashierID;
    }

    public void setCashierID(int cashierID) {
        this.cashierID = cashierID;
    }
    public int cartcost(){ //calculate payement
    return cart.gettotalamount();
   }
    public void cancelorder(Order m){
       if(order.contains(m)){
           order.remove(m);
           System.out.println("order removed successfully");
       }
       else
           System.out.println("order isn't found");
   }
   
    //function bet calculate total revenue men get total amount f cart 
    public void paymentmethod (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("total : "+cart.gettotalamount());
      System.out.println("1.cancel ");
      System.out.println("2.complete to payment method");
      int choice;
      
      choice=scanner.nextInt();
      scanner.nextLine();
      if ("1".equals(choice))
      {
       System.out.println("done successfully");
     cart.clear();
     }
      else if("2".equals(choice))
      {
          System.out.println("choose your payment method :");
          System.out.println("1. pay cash  ");
          System.out.println("2. visa ");
           int y = scanner.nextInt();
           scanner.nextLine();
          if ("1".equals(y))
          {  System.out.println("order created successfully ! ");
             //yed5lo 3ala order ely 3amlo then y save it f file getdetails then save object gowa file
             FinalResult();
             for(Order p:order){
                 
             }
             
          }
          else 
          {
              System.out.println("payment details"); //nekamelha
          }
        
      }
    }public void saveToFile(ArrayList<Order> data, String fileName) {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
        out.writeObject(data);
        System.out.println("Data saved to " + fileName);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public ArrayList<Order> loadOrdersFromFile(String fileName) {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
        Object loadedObject = in.readObject();

        if (loadedObject instanceof ArrayList) {
            return (ArrayList<Order>) loadedObject;
        }

        System.out.println("Invalid data format in " + fileName);
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return new ArrayList<>();
}
  
}
