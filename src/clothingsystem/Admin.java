/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clothingsystem;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author lenovo
 */
public class Admin extends User {
    static ArrayList<User> user = new ArrayList<>();
    static ArrayList<Product> pp=new ArrayList<>();
    static ArrayList<Supplier> sp= new ArrayList<>();
    User u1=new User("nour","12433","admin");
    
    Scanner s = new Scanner(System.in);
    
    
    public Admin(String username, String password,String usertype) {
        super(username, password,usertype);
    }

    public static ArrayList<User> getUser() { //return all users in list
        return user;
    }

    public static void setUser(ArrayList<User> user) { 
        Admin.user = user;
    }

    public static ArrayList<Product> getPp() { //return all products in,list
        return pp;
    }

    public static void setPp(ArrayList<Product> pp) {
        Admin.pp = pp;
    }
    
public void search(String searchValue, String searchCriteria, TextArea result) {
    boolean found = false;

    for (Product product : pp) {
        String fieldValue = ""; 
        switch (searchCriteria.toLowerCase()) {
            case "product name":
                fieldValue = product.getNameofproduct();
                break;
            case "product id":
                fieldValue = String.valueOf(product.getProductID());
                break;
            case "color":
                fieldValue = product.getColor();
                break;
            case "quantity":
                fieldValue = String.valueOf(product.getQuantity());
                break;
            case "size":
                fieldValue = product.getSize();
                break;
            case "price":
                fieldValue = String.valueOf(product.getPrice());
                break;
            case "category":
                fieldValue = product.getCategory();
                break;
        }

        if (fieldValue.equalsIgnoreCase(searchValue)) {
            found = true;
            result.appendText("Product name: " + product.getNameofproduct() + "\n");
            result.appendText("Product ID: " + product.getProductID() + "\n");
            result.appendText("Product color: " + product.getColor() + "\n");
            result.appendText("Product quantity: " + product.getQuantity() + "\n");
            result.appendText("Product size: " + product.getSize() + "\n");
            result.appendText("Product price: " + product.getPrice() + "\n");
            result.appendText("Product category: " + product.getCategory() + "\n\n");
        }
    }

    if (!found) {
        result.clear();
        result.appendText("No product found with " + searchCriteria + ": " + searchValue);
    }
}

    
     public void addp(Product prod) throws IOException //y check men file id mawgod wla la2 w ba3den y add
    {
         boolean productExists = false;
         loadFromFile("Products.bin");
         Iterator<Product> iter = pp.iterator();
         while (iter.hasNext()) {
            Product curr = iter.next();
            if (curr.getProductID() == prod.getProductID()){
            System.out.println("Product with ID " + prod.getProductID() + " already exists");   
            productExists = true; 
             Alert remove = new Alert(Alert.AlertType.CONFIRMATION);
               remove.setContentText("Product isn't avaiable");
               remove.show();
            break; 
        }  
    }

          if (!productExists) { 
            
          pp.add(prod);
          saveToFile("Products.bin");
          System.out.println("Product added: " + prod);
           Alert addd = new Alert(Alert.AlertType.CONFIRMATION);
               addd.setContentText("Product added succesfully");
               addd.show();
           }    
       
  
    }


     public void removep(int id) throws FileNotFoundException, IOException{
    boolean productExists = false;

    loadFromFile("Products.bin");

    for (Product curr : pp) {
        if (curr.getProductID() == id) {
            pp.remove(curr);
            saveToFile("Products.bin");
            productExists = true; 
             Alert removeee = new Alert(Alert.AlertType.CONFIRMATION);
               removeee.setContentText("Product removed succesfully");
               removeee.show();
            break;
        }
    }

    if (!productExists) {
        System.out.println("Product doesn't exist");
        Alert not = new Alert(Alert.AlertType.CONFIRMATION);
        not.setContentText("Product not available");
        not.show();
    }
}
 
//     private void updateFile() throws IOException {
//        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Products.bin"))) {
//                os.writeObject(pp);
//            
//            //saveToFile("Products.bin");
//        }
//     }
    public void listp(TextArea t1){
        if(pp.isEmpty()){
            System.out.println("No Products were added ");
        }
        else{
    for(Product a:pp){
     t1.appendText("Product name : " + a.getNameofproduct() );
     t1.appendText("Product id : " + a.getProductID() );
     t1.appendText("Product color : " + a.getColor() );
     t1.appendText("Product quantity : " + a.getQuantity());
     t1.appendText("Product size : " + a.getSize() );
     t1.appendText("Product price : " + a.getPrice() );
     t1.appendText("Product category : " + a.getCategory());
            }
    }
        }
    

  
     public void editp(TextField pricefield,TextField id){
   if (pp.isEmpty()) {
        System.out.println("No Products were added ...");
   }
   else{
        int selectedId = Integer.parseInt(id.getText());
        Product selectedProd = pp.stream().filter(p -> p.getProductID() == selectedId).findFirst().orElse(null);

        if (selectedProd != null) {
        System.out.println("Choose what would you like to edit : ");
        System.out.println("Product ID "); //drop down list
        System.out.println( "Price ");
        
            System.out.println("Enter the new Price : ");
            int price=Integer.parseInt(pricefield.getText());
           selectedProd.setPrice(price);
            System.out.println(" Price updated Successfully .");
        
        saveToFile("Products.bin");
    }
        else{
        System.out.println("Invalid index. Please choose a valid index.");
        }
   }
}
     public void searchUser(String searchString, String searchCriteria, TextArea result) {
    boolean found = false;

    for (User a : user) {
        if (("username".equalsIgnoreCase(searchCriteria) && a.getUsername().equalsIgnoreCase(searchString))
                || ("password".equalsIgnoreCase(searchCriteria) && a.getPassword().equalsIgnoreCase(searchString))
                || ("usertype".equalsIgnoreCase(searchCriteria) && a.getUserType().equalsIgnoreCase(searchString))) {

            found = true;
            result.appendText("Username: " + a.getUsername() + "\n");
            result.appendText("Password: " + a.getPassword() + "\n");
            result.appendText("UserType: " + a.getUserType() + "\n");
        }
    }

    if (!found) {
        result.clear();
        result.appendText("No user found with " + searchCriteria + ": " + searchString);

      
    }
   
}


    
 
     public void listu(TextArea tuser) throws   IOException, ClassNotFoundException, ClassNotFoundException{
        if(user.isEmpty()){
            tuser.appendText("No Users were added ");
        }
        else{
            tuser.clear();
   for (User a : user) {
    tuser.appendText("Username: " + a.getUsername() + "\n");
    tuser.appendText("Password: " + a.getPassword() + "\n\n");
    tuser.appendText("UserType: " + a.getUserType() + "\n\n\n");
}

  }
}
     
    public void addu(User userr) throws IOException, ClassNotFoundException {
    boolean userExists = false;
    
    Iterator<User> iter = user.iterator();
    while (iter.hasNext()) {
        User curr = iter.next();
        if (curr.getUsername().equals(userr.getUsername())) {
            System.out.println("User with username " + userr.getUsername() + " already exists");
            userExists = true;
            Alert remove = new Alert(Alert.AlertType.CONFIRMATION);
            remove.setContentText("User isn't available");
            remove.show();
            break;
        }
    }

    if (!userExists) {
        user.add(userr);
        saveToFilee("Users.bin");
        System.out.println("User added: " + userr);
        Alert addd = new Alert(Alert.AlertType.CONFIRMATION);
        addd.setContentText("User added successfully");
        addd.show();
    }
}

     public void remove(String u) throws FileNotFoundException, IOException, ClassNotFoundException 
    {
    //LoadFromFileuser("Users.bin");
    Iterator<User> iterator = user.iterator();
    while (iterator.hasNext()) {
        User curr = iterator.next();
        if (curr.getUsername().contains(u)) {
            iterator.remove();
            saveToFilee("Users.bin");
            Alert removeee = new Alert(Alert.AlertType.CONFIRMATION);
            removeee.setContentText("User removed successfully");
            removeee.show();
            return; 
        }
    }

    System.out.println("User doesn't exist");
    Alert not = new Alert(Alert.AlertType.CONFIRMATION);
    not.setContentText("User not available");
    not.show();
}
    
     public void editu(TextField pass,TextField us,TextField oldusername){
    if (user.isEmpty()) {
        System.out.println("No Users were added ...");
    } else {
        String oldUsername = oldusername.getText();
        User selectedUser = user.stream().filter(u -> u.getUsername().equals(oldUsername)).findFirst().orElse(null);

        if (selectedUser != null) {
            System.out.println("Enter the new Username: ");
            String newUsername = us.getText();
            System.out.println("Enter the new Password: ");
            String newPassword = pass.getText();
            selectedUser.setUsername(newUsername);
            selectedUser.setPassword(newPassword);
            System.out.println("Username and Password updated successfully.");
            saveToFile("Users.bin");
        } else {
            System.out.println("User not found. Please enter a valid username.");
        }
    }
}

     
      public void viewreportscash(Cashier u) throws IOException
    {
               ArrayList<Order> corder= u.loadOrdersFromFile("Orders.bin");
                boolean foundCashier = false;
               for(int i=0;i<corder.size();i++){
                    foundCashier = true;
                   System.out.println("Order Details : "+ corder.get(i).getOrderDetails());
                   System.out.println("-------------------");
               }
    
      if (!foundCashier) {
            System.out.println("No orders found for the specified cashier.");
        }
    }

//              public void viewreportssupp(Supplier u)
//    {
//               ArrayList<Order> corder=u.getOrders();
//               for(int i=0;i<corder.size();i++){
//                   System.out.println("Order ID : "+ corder.get(i).getOrderid());
//                   System.out.println("Order Price : "+ corder.get(i).gettotal());
//                   System.out.println("-------------------");
//
//               }
//    }
//                public void viewreportscust(Customer uu) throws IOException, FileNotFoundException, ClassNotFoundException
//    {
//               ArrayList<Order> corderr=uu.loadOrdersFromFile("Order.bin");
//                boolean foundCust = false;
//               for(int i=0;i<corderr.size();i++){
//                   foundCust = true;
//                   System.out.println("Order ID : "+ corderr.get(i).getOrderDetails());
//                   System.out.println("-------------------");
//
//               }
//               if (!foundCust) {
//            System.out.println("No orders found for the specified cashier.");
//        }
//    } 
      public void getcustomer(){
          Customer cust= null;
          loadOrderHistoryFromFilecust("Order.bin",cust.getCustomerid());
        for(User a:user){
            if(a instanceof Customer){
                System.out.println("Customer ID : "+((Customer) a).getCustomerid()+"Number of orders " + ((Customer) a).getNooforders());
            }
        }
    }
       public void getcashier(){ 
           Cashier c= null;
           loadOrderHistoryFromFilecash("Order.bin",c.getCashierID());
        for(User a:user){
            if(a instanceof Cashier){
                System.out.println("Cashier ID : "+((Cashier) a).getCashierID()+"Number of orders " + ((Cashier) a).getNooforders());
            }
        }
    }
           public void getsupplier(){ 
               Supplier s = null;
               loadOrderHistoryFromFile("Supplier.bin",s.getSupplierid());
        for(User a:user){
            if(a instanceof Supplier){
                System.out.println("Supplier ID : "+((Supplier) a).getSupplierid()+"Number of orders " + ((Supplier) a).getNooforders());
            }
        }
    }
        public void getmaxorderscush(){ 
            Cashier cash= null;
            loadOrderHistoryFromFilecash("Order.bin",cash.getCashierID());
        int num=0;
        int cashierid = 0; 
        for(User a:user){
            if(a instanceof Cashier){
              if(num<((Cashier) a).getNooforders()){
                  num=((Cashier) a).getNooforders();
                  cashierid=((Cashier) a).getCashierID();
              }
            }
        }
        System.out.println("Cashier's ID with the highest number of orders is : "+cashierid+" with "+num+ " Orders");
    }
    public void getmaxordercust(){ 
        Customer c1= null;
         loadOrderHistoryFromFilecust("Order.bin",c1.getCustomerid());
        int num=0;
        int customid=0;
        for(User a: user){
           if(a instanceof Customer){
           if(num<((Customer) a).getNooforders()){
           num=((Customer) a).getNooforders();
           customid=((Customer) a).getCustomerid();
}
            }
           
        } System.out.println("Customer id with the highest number of orders is: " + customid+" with " +num);
    }
    public void getmaxordersupp(){ 
        Supplier supp = null;
        loadOrderHistoryFromFile("Supplier.bin",supp.getSupplierid());
    int num=0;
        int Supplierid = 0;
        for(User a: user){
            if(a instanceof Supplier){
            if(num<((Supplier) a).getNooforders()){
            num=((Supplier) a).getNooforders();
            Supplierid=((Supplier) a).getSupplierid();
              
            }
        }
        }
        System.out.println("Supplier's ID with the highest number of orders is : "+Supplierid+" with "+num+ " Orders");
    
    }
     public void getmaxrevenuesupp(){
         Supplier supp1 = null;
        loadOrderHistoryFromFile("Supplier.bin",supp1.getSupplierid());
        int revenue=0;
        int Supplierid = 0;
        for(User a: user){
            if(a instanceof Supplier){
            if(revenue<((Supplier) a).getRevenue()){
            revenue=((Supplier) a).getRevenue();
            Supplierid=((Supplier) a).getSupplierid();
              
            }
        }
        }
        System.out.println("Supplier's ID with the highest revenue is : "+Supplierid+" with "+revenue);
    
    }
       public void getmaxrevenuecust(){
          Customer c2= null;
         loadOrderHistoryFromFilecust("Order.bin",c2.getCustomerid());
       int revenue=0;
       int Customerid = 0;
       for(User a: user){
         if(a instanceof Customer){
         if(revenue<((Customer) a).getrevenue()){
            revenue=((Customer) a).getrevenue();
            Customerid=((Customer) a).getCustomerid();
              
            }
        }
        }
        System.out.println("Customer's ID with the highest revenue is : "+revenue+" with "+Customerid);
    
    }
      public void getmaxrevenuecash(){ 
          Cashier cash1= null;
            loadOrderHistoryFromFilecash("Order.bin",cash1.getCashierID());
      int revenue=0;
      int casherid = 0;
      for(User a: user){
        if(a instanceof Cashier){
        if(revenue<((Cashier) a).getRevenue()){
           revenue=((Cashier) a).getRevenue();
           casherid=((Cashier) a).getCashierID();
              
            }
        }
        }
        System.out.println("Cashier's ID with the highest revenue is : "+revenue+" with "+casherid);
    }
       public void getlistofsupp(){ //yerag3 kol asamy suppliers ely 3andy
          for(Supplier ss:sp){
              System.out.println("Supplier id: "+ss.getSupplierid() );
              System.out.println("Supplier id: "+ss.getRevenue());//total price
              System.out.println("Info: ");
          }
      }
       public void getMaxProductOverTime(Date startDate, Date endDate) throws FileNotFoundException, IOException, ClassNotFoundException { //Best  seller count
         int maxQuantity = 0;
        String productName = "";

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Orders.bin"))) {
            List<Order> orderHistory = (List<Order>) in.readObject();

        for (Order order : orderHistory) {
                Date orderDate = order.getDate();

        if (orderDate.after(startDate) && orderDate.before(endDate)) {
               int orderTotalAmount = 0;
                 String maxProductName = "";

                    for (Product product : order.getProducts()) {
                        orderTotalAmount += product.getQuantity();
                        
                        if (product.getQuantity() > maxQuantity) {
                            maxQuantity = product.getQuantity();
                            maxProductName = product.getNameofproduct();
                        }
                    }

                    if (orderTotalAmount > maxQuantity) {
                        maxQuantity = orderTotalAmount;
                        productName = maxProductName;
                    }
                }
            }
        }

        System.out.println("Product with max Count: " + maxQuantity);
        System.out.println("Product name with max Count: " + productName);
    }


      public void getmaximumrevenue(Date startDate, Date endDate) throws FileNotFoundException, IOException, ClassNotFoundException { //Best seller b price
        int maxRevenue = 0;
        String bestSellerProductName = "";

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Orders.bin"))) {
            List<Order> orderHistory = (List<Order>) in.readObject();

            for (Order order : orderHistory) {
                Date orderDate = order.getDate();

                if (orderDate.after(startDate) && orderDate.before(endDate)) {
                    int orderTotalRevenue = 0;
                    String maxRevenueProductName = "";

                    for (Product product : order.getProducts()) {
                        int productRevenue = product.getQuantity() * product.getPrice();
                        orderTotalRevenue += productRevenue;

                        if (productRevenue > maxRevenue) {
                            maxRevenue = productRevenue;
                            maxRevenueProductName = product.getNameofproduct();
                        }
                    }

                    if (orderTotalRevenue > maxRevenue) {
                        maxRevenue = orderTotalRevenue;
                        bestSellerProductName = maxRevenueProductName;
                    }
                }
            }
        }

        System.out.println("Best seller product revenue: " + maxRevenue);
        System.out.println("Product name with max revenue: " + bestSellerProductName);
    }
        public void generateAndStoreSpecificCredentials() throws IOException {

        pp.clear();
       System.out.println("saving data");
       Product p1 = new Product(1234,200,"Basic Hoodie",2,"Black","M","Hoodies");
       Product p2 = new Product(1240,300,"BackPrinted Hoodie",2,"Black","M","Hoodies");
       Product p3 = new Product(2023,400,"Cargo pants",3,"White","L","Buttoms");
       Product p4 = new Product(2030,600,"Pump Jacket ",4,"White","xl","Jackets");
       Product p5 = new Product(3020,550,"Skirt",5,"Black","S","Buttoms");
       
      pp.add(p1);
      pp.add(p2);
      pp.add(p3);
      pp.add(p4);
      pp.add(p5);
       
        saveToFile("Products.bin");
    }
        public void displayLoadedProducts(String fileName) { 
    for (Product product : pp) {
        System.out.println(product);
    }
}




      
public static void saveToFile(String fileName) {
    System.out.println("enter the save function");
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(pp);
        System.out.println("object written to file");
        System.out.println("Objects saved to " + fileName);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
        public static void saveToFilee( String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(user);
            System.out.println("Object saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void loadFromFile(String fileName) throws FileNotFoundException, IOException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            pp = (ArrayList<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayProducts() {
        Iterator<Product> iter = pp.iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            System.out.println(product.getNameofproduct());
        }
    }


// public ArrayList<Product> loadFromFile(String fileName) {
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
//            Object loadedObject = in.readObject();//type object 3ashan a3raf a read content kolaha
//            System.out.println("Object loaded from " + fileName);
//             ArrayList<Product> loadedProducts = (ArrayList<Product>) loadedObject;
//
//            System.out.println("Loaded Products:");
//            for (Product product : loadedProducts) {
//                System.out.println(product);
//                System.out.println("-------------------");
//            }
//
//            return loadedProducts;
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
 
 private ArrayList<Supplier> loadOrderHistoryFromFile(String fileName, int supplierId) {
    ArrayList<Supplier> supporders = new ArrayList<>();

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
        Object loadedObject;
        while ((loadedObject = in.readObject()) != null) {
            if (loadedObject instanceof Order && ((Supplier) loadedObject).getSupplierid() == supplierId) {
                supporders.add((Supplier) loadedObject);
            }
        }
    } catch (EOFException e) {
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

    return supporders;
}
 private ArrayList<Order> loadOrderHistoryFromFilecust(String fileName, int custID) {
    ArrayList<Order> orders = new ArrayList<>();

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
        Object loadedObject;
        while ((loadedObject = in.readObject()) != null) {
            if (loadedObject instanceof Order && ((Customer) loadedObject).getCustomerid() == custID) {
                orders.add((Order) loadedObject);
            }
        }
    } catch (EOFException e) {
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

    return orders;
}
 private ArrayList<Order> loadOrderHistoryFromFilecash(String fileName, int cashID) {
    ArrayList<Order> orders = new ArrayList<>();

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
        Object loadedObject;
        while ((loadedObject = in.readObject()) != null) {
            if (loadedObject instanceof Order && ((Cashier) loadedObject).getCashierID() == cashID) {
                orders.add((Order) loadedObject);
            }
        }
    } catch (EOFException e) {
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

    return orders;
}

      
}
