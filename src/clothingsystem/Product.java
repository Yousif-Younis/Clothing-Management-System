/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clothingsystem;

import java.io.Serializable;

import java.io.Serializable;

/**
 *
 * @author lenovo
 */
 enum Categories{
        Hoodies,Jackets,Buttoms;
        
    public static Categories getHoodies() {
        return Hoodies;
    }

    public static Categories getJackets() {
        return Jackets;
    }

    public static Categories getButtoms() {
        return Buttoms;
    }
}
    enum subCategory{
        BackPrinted,Basic,Oversized,Leather,Pump,WaterProof,Cargo,Jeans,SweetPants,Skirt;
        
    public static subCategory getBackPrinted() {
        return BackPrinted;
    }

    public static subCategory getBasic() {
        return Basic;
    }

    public static subCategory getOversized() {
        return Oversized;
    }

    public static subCategory getLeather() {
        return Leather;
    }

    public static subCategory getPump() {
        return Pump;
    }

    public static subCategory getWaterProof() {
        return WaterProof;
    }

    public static subCategory getCargo() {
        return Cargo;
    }

    public static subCategory getJeans() {
        return Jeans;
    }

    public static subCategory getSweetPants() {
        return SweetPants;
    }

    public static subCategory getSkirt() {
        return Skirt;
    }
}
    enum Color{
        Black,White,Green,Blue,Grey;
        
    public static Color getBlack() {
        return Black;
    }

    public static Color getWhite() {
        return White;
    }

    public static Color getGreen() {
        return Green;
    }

    public static Color getBlue() {
        return Blue;
    }

    public static Color getGrey() {
        return Grey;
    }
}
    enum Size{
        S,M,L,Xl;
        
    public static Size getS() {
        return S;
    }

    public static Size getM() {
        return M;
    }

    public static Size getL() {
        return L;
    }

    public static Size getXl() {
        return Xl;
    }
}


public class Product implements Serializable {
 private int productID;
 private int price;
 private String nameofproduct;
 private int quantity;
 private String Color;
 private String Size;
 private String category; 
 public int count=0;
public Product()
{
}
    public Product(int productID, int price, String nameofproduct, int quantity, String Color, String Size, String category) {
        this.productID = productID;
        this.price = price;
        this.nameofproduct = nameofproduct;
        this.quantity = quantity;
        this.Color = Color;
        this.Size = Size;
        this.category = category;
        this.count=count;
    }
    
    public int gettotalitem()
    {
    return price*quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNameofproduct() {
        return nameofproduct;
    }

    public void setNameofproduct(String nameofproduct) {
        this.nameofproduct = nameofproduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", price=" + price + ", nameofproduct=" + nameofproduct + ", quantity=" + quantity + ", Color=" + Color + ", Size=" + Size + ", category=" + category + '}';
    }

    


}