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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable { 
      private String username;
    private String password;
      private String userType;
      Admin a;
      private static final long serialVersionUID = 6411481920480496025L;

    //static ArrayList<User> user = new ArrayList<>();

    public User(String username, String password,String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" + "username=" + username + ", password=" + password + ", userType=" + userType + '}';
    }
 public void generateAndStoreSpecificCredentials(String fileName) throws IOException {
        ArrayList<User> users = a.getUser();
        users.clear();
        // Add specific usernames and passwords for each user type
        if (users.isEmpty()) {
        User u1 = new User("nourhan", "122", "Customer");
        User u2 = new User("shaden", "123", "Cashier");
        User u3 = new User("randa", "1234", "Supplier");
        User u4 = new User("nourhan", "12345", "Admin");
        
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);

        saveToFilee("Users.bin");
    }
 }
 private void saveToFilee(String fileName) throws IOException {
        ArrayList<User> users=a.getUser();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(users);
            System.out.println("Object saved to " + fileName);
        }
    }
   public void login(String username, String password, String userType) throws IOException, ClassNotFoundException {
        ArrayList<User> users = LoadFromFileuser("Users.bin");
        if (users != null) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    System.out.println("User: " + username + ", Password: " + password + " logged in as " + userType);
                    return;
                }
            }
        }
        System.out.println("Incorrect username or password for " + userType);
    }

            
    
    public void Signup() throws IOException, ClassNotFoundException{
         ArrayList<User> users = LoadFromFileuser(userType + "Users.bin");
            if (users != null) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)){
                System.out.println("User already exists");
                }
        else{
                Scanner s = new Scanner(System.in);
                System.out.println("Enter username:");
                String username= s.nextLine();
                System.out.println("Enter password");
                String password=s.nextLine();
                System.out.println("Your new Username is : " + username + "and Password is: " + password);
                }
            }
    }
    }
    public void createAndStoreBinaryFile() {
        try {
            generateAndStoreSpecificCredentials(getUserType() + "Users.bin");
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }
    public ArrayList<User> LoadFromFileuser(String fileName) throws IOException, ClassNotFoundException {
         ArrayList<User> users=a.getUser();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Object loadedObject = in.readObject();
            users.addAll((ArrayList<User>) loadedObject);
            System.out.println("Object loaded from " + fileName);
            return users;
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " not found. Creating a new one...");
            createAndStoreBinaryFile();
        }
        return null;
    }
}
