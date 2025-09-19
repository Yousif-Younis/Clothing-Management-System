/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clothingsystem;


import static clothingsystem.Admin.pp;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

/**
 *
 * @author lenovo
 */
public class Supplier extends User implements Serializable {
    private int supplierid;
    int nooforders;
    private static ArrayList<Product> suppprod;
    private static final long serialVersionUID = 123456789L;
    int revenue;
    TextArea t1;
    Admin a= new Admin("nour","1234","admin");
    
    public Supplier(String username, String password,String userType) {
        super(username, password,userType);
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        int totalamount=0;
        for(int i=0;i<suppprod.size();i++){
        totalamount+=suppprod.get(i).gettotalitem();
    }
        System.out.println(totalamount);
    
        
    }

    public static ArrayList<Product> getSuppprod() {
        return suppprod;
    }

    public static void setSuppprod(ArrayList<Product> suppprod) {
        Supplier.suppprod = suppprod;
    }
    
    public int getNooforders() {
        return nooforders;
    }

    public void setNooforders(int nooforders) {
        this.nooforders = nooforders;
    }
    
    
    
    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }
    
     public void addProduct(int productID, int price, String nameofproduct, int quantity, String Color, String Size, String category) {
         suppprod = a.getPp();
         Product newProduct = new Product(productID, price,nameofproduct, quantity, Color, Size,  category);
        suppprod.add(newProduct);
        System.out.println("Product added: " + newProduct.getNameofproduct() + " - Price: $" + newProduct.getPrice());
        saveToFile("Products.bin",suppprod);
        
    }

    public void saveToFile(String fileName,List<Product> products) {
         suppprod= a.getPp();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(products);
            System.out.println("Object saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Product> loadFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        suppprod= a.getPp();
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Object loadedObject = in.readObject();
            suppprod.addAll((ArrayList<Product>) loadedObject);
            System.out.println("Object loaded from " + fileName);
            return suppprod;
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " not found. Creating a new one...");
            
        }
        return null;
    }
}

