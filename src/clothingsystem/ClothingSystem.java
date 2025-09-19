/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clothingsystem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;
import  javafx.print.*;
import javafx.scene.*;
import javafx.application.Application;
import javafx.stage.*;
import javafx.application.*;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;

/**
 *
 * @author lenovo
 */
public class ClothingSystem extends Application{

private Scene mainScene;
private Scene shopscene;
private Scene welcomeScene;
private Scene hoodiesscene;
Scene ordersScene;
User user= new User("nourhan","1234","customer");
Customer c ; 
Product selectedProduct;
private final Cart cart = new Cart();
Scene cartScene;
Supplier s= new Supplier("randa","1234","supplier") ;
Admin a= new Admin("nour","1234","admin");
Supplier supplier;
Scene adminScene;
Cashier cashieer;
Order order= new Order();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception
    {   
            
         String backgroundImagePath = "file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg";
        

        Label welc = new Label("Welcome to our store");
        welc.setTextFill(Color.BLACK);
        welc.setFont(javafx.scene.text.Font.font("Academy Engraved LET", javafx.scene.text.FontWeight.BOLD, 40)); // Make it bold
        welc.setStyle("-fx-font-style: italic;"); // Make it italic
        welc.setAlignment(Pos.CENTER);

        // Select Label
        Label select = new Label("Select what is your type");
        select.setTextFill(Color.BLACK);
        select.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14)); // Subheading font size
        select.setAlignment(Pos.CENTER);

        Button supp = new Button("Supplier");
        Button admin = new Button("Admin");
        Button customer = new Button("Customer");
        Button cashier = new Button ("Cashier");
        Button ok = new Button("Ok");
        Button cancel = new Button("Cancel");
        Button viewshop = new Button ("View Shop ");
       viewshop.setOnAction(event -> {
        shopW( stage);
    });

        // Set actions for buttons using lambda expressions
        supp.setOnAction(event -> showSignInScreen(stage, "Supplier"));
        admin.setOnAction(event -> showSignInScreen(stage, "Admin"));
        customer.setOnAction(event -> showSignInScreen(stage, "Customer"));
        cashier.setOnAction(event -> showSignInScreen(stage, "Cashier"));

        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.add(welc, 0, 0, 12, 1); // span 12 columns for the header
        pane.add(select, 0, 2, 12, 1); // span 12 columns for the select label
        pane.add(supp, 5, 3);
        pane.add(customer, 6, 3);
        pane.add(cashier, 7, 3);
        pane.add(admin, 8, 3);
        pane.add(ok, 10, 5);
        pane.add(cancel, 11, 5);
        pane.add(viewshop,9,3);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));

        // Set an image as background
        Image backgroundImage = new Image(backgroundImagePath);
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
        pane.setBackground(new Background(background));

        mainScene = new Scene(pane, 600, 400);
        stage.setScene(mainScene);
        stage.setTitle("HER Store");
        stage.setResizable(false);
        stage.show();
    }

   private void showSignInScreen(Stage stage, String userType) {
    // Create sign-in screen components
    Label usernameLabel = new Label("Username:");
    Label passwordLabel = new Label("Password:");
    TextField usernameTextField = new TextField();
    PasswordField passwordTextField = new PasswordField();
    Button goButton = new Button("Go");
    Button cancelButton = new Button("Cancel");

   
    goButton.setOnAction(event -> {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        user = new User(username, password, userType);
        try {
            user.login(username, password, userType);
           
            if ( username.equals("nourhan")&&password.equals("122") &&userType.equals("Customer")){
                 showWelcomeScene(stage);
                System.out.println("welcome "+username);}
            else if (username.equals("randa")&&password.equals("1234")&&userType.equals("Supplier"))
            {showSupplierScreen( stage);
            System.out.println("welcome"+ username);
            }
            else if (username.equals("nourhan")&&password.equals("12345")&&userType.equals("Admin"))
            {showAdminScreen(stage);
            System.out.println("welcome"+username);
            }
            else if (username.equals("shaden")&&password.equals("123")&&userType.equals("Cashier"))
            {
                showCashierScreen(stage);
                System.out.println("Welcome"+user.getUsername());
            }
             else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(" Try Again");
                a.setContentText("Username or Password are incorrect");
                a.show();
                System.out.println("Login failed. Please try again.");}}
         catch (IOException |ClassNotFoundException  e) {
            e.getStackTrace();
        }
    });

    cancelButton.setOnAction(event -> {
      
        System.out.println("Sign-in canceled");
        stage.setScene(mainScene); // Switch back to the main scene
    });

    
    GridPane signInPane = new GridPane();
    signInPane.setVgap(10);
    signInPane.setHgap(10);
    signInPane.add(usernameLabel, 0, 0);
    signInPane.add(usernameTextField, 1, 0);
    signInPane.add(passwordLabel, 0, 1);
    signInPane.add(passwordTextField, 1, 1);
    signInPane.add(goButton, 0, 2);
    signInPane.add(cancelButton, 1, 2);
    signInPane.setAlignment(Pos.CENTER);
    signInPane.setPadding(new Insets(10, 10, 10, 10));

    
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 200, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    signInPane.setBackground(background);

   
    Scene signInScene = new Scene(signInPane, 400, 200);
    stage.setResizable(false);
    stage.setScene(signInScene);
}
   
   private void showCashierScreen(Stage stage)
   {Label welcomeLabel = new Label("Welcome "+user.getUsername()+" ! ");
    welcomeLabel.setTextFill(Color.BLACK);
        welcomeLabel.setFont(javafx.scene.text.Font.font("Braggadocio", javafx.scene.text.FontWeight.BOLD, 14)); 
        welcomeLabel.setAlignment(Pos.CENTER);
    Button checkOrdersButton = new Button("Check past orders");
    Button NeworderButton = new Button("Create new order");
    Button signout = new Button("Sign out");


    checkOrdersButton.setOnAction(e -> {
    System.out.println("Checking past orders...");

    // Assuming you have a Cashier constructor that takes necessary parameters
    Cashier cashier = new Cashier(11, user.getUsername(), user.getPassword());

    // Load past orders from the binary file
    ArrayList<Order> pastOrders = cashier.loadOrdersFromFile("Cashierorders.bin");

    // Create a TextField to display past orders
    TextField ordersTextField = new TextField();
    ordersTextField.setEditable(false);
//    ordersTextField.setPrefRowCount(10);

    // Process the loaded orders
    if (!pastOrders.isEmpty()) {
        StringBuilder ordersText = new StringBuilder();

        for (Order order : pastOrders) {
            ordersText.append("Past Order Details:\n")
                    .append(order.getOrderDetails())
                    .append("\nCashier id: ").append(cashier.getCashierID())
                    .append("\nCustomer id: ").append(order)
                    .append("\n------------\n");
        }

        ordersTextField.setText(ordersText.toString());
    } else {
        ordersTextField.setText("No orders to show.");
    }

    // Create a new stage for displaying orders
    Stage ordersStage = new Stage();
    ordersStage.setTitle("Past Orders");

    // Create a VBox for the layout
    VBox ordersLayout = new VBox(10);
    ordersLayout.setAlignment(Pos.CENTER);
    ordersLayout.setPadding(new Insets(10));

    // Add the orders TextField to the layout
    ordersLayout.getChildren().add(ordersTextField);

    // Create a scene and set it on the stage
     ordersScene = new Scene(ordersLayout, 500, 400);
    ordersStage.setScene(ordersScene);

    // Show the orders stage
    ordersStage.show();
});


    NeworderButton.setOnAction(e -> {
        System.out.println("Cashier is doing new order");
        cashierneworder(stage);
       
    });

    signout.setOnAction(e -> {
        stage.setScene(mainScene);
    });

    VBox welcomeLayout = new VBox(10);
    welcomeLayout.getChildren().addAll(welcomeLabel, checkOrdersButton, NeworderButton,signout);
    welcomeLayout.setAlignment(Pos.CENTER);

    // Use the same background image as main scene
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:/Users/Norhan/Desktop/background.jpeg", 400, 200, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);

    welcomeLayout.setBackground(background);

     welcomeScene = new Scene(welcomeLayout, 400, 200);
    stage.setScene(welcomeScene);
    stage.setResizable(false);
    stage.show();
   }
   private void cashierneworder(Stage stage )
   { this.cashieer = new Cashier(11,user.getUsername(),user.getPassword()); // Assuming Cashier is the class containing the addInCart method

        Label id = new Label("Enter product id");
     Label price = new Label("Enter Product Price");
     Label name = new Label("Enter product name:");
     Label quan = new Label("Enter product quantity");
     Label color = new Label ("Enter product Color");
     Label size = new Label("Enter product Size");
     Label category = new Label("Enter product category");
     id.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     price.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     name.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     quan.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     size.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     color.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     category.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     TextField idname = new TextField();
     TextField pricename = new TextField();
     TextField namee= new TextField();
     TextField quaname = new TextField();
     TextField colorname = new TextField();
     TextField sizename = new TextField();
     TextField categoryname = new TextField();
     
//     String idd = idname.getText();
//        String pricee = pricename.getText();
//        String prodname = namee.getText();
//        String quantityname = quaname.getText();
//        String colorna = colorname.getText();
//        String sizena = sizename.getText();
//        String categoryna = categoryname.getText();
         Button addAnotherProductButton = new Button("Add Another Product");
    Button doneButton = new Button("Done");

    addAnotherProductButton.setOnAction(e -> {
         String idd = idname.getText();
        String pricee = pricename.getText();
        String prodname = namee.getText();
        String quantityname = quaname.getText();
        String colorna = colorname.getText();
        String sizena = sizename.getText();
        String categoryna = categoryname.getText();
       if (idd.isEmpty() || pricee.isEmpty() || prodname.isEmpty() || quantityname.isEmpty() || colorna.isEmpty() || sizena.isEmpty() || categoryna.isEmpty()) {
         
           Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please insert the first product details before adding another one.");
        alert.showAndWait();}
        else{ clearFields(idname, pricename, namee, quaname, colorname, sizename, categoryname);}
    });

doneButton.setOnAction(e -> {
     String idd = idname.getText();
        String pricee = pricename.getText();
        String prodname = namee.getText();
        String quantityname = quaname.getText();
        String colorna = colorname.getText();
        String sizena = sizename.getText();
        String categoryna = categoryname.getText();
    // Add the last product
    if (idd.isEmpty() || pricee.isEmpty() || prodname.isEmpty() || quantityname.isEmpty() || colorna.isEmpty() || sizena.isEmpty() || categoryna.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please insert all the product details.");
        alert.showAndWait();
    } else {
        Product newProduct = new Product(Integer.parseInt(idd), Integer.parseInt(pricee), prodname, Integer.parseInt(quantityname), colorna, sizena, categoryna);
        // Add the new product
        cashieer.addInCart(newProduct);
    }

    // Calculate the total of all product details
    int totalAmount = cashieer.cart.gettotalamount();

    // Display the total amount
    Label totalAmountLabel = new Label("Total Amount: $" + totalAmount);
    totalAmountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    // Create a button to clear the cart
    Button clearCartButton = createButton("return", () -> showCashierScreen(stage));

    // Create a button to show the final result
    Button showResultButton = createButton("Show Final Result", () -> showFinalResult(stage));

    VBox resultLayout = new VBox(10);
    resultLayout.setPadding(new Insets(10));
    resultLayout.getChildren().addAll(totalAmountLabel, clearCartButton, showResultButton);

    // Set background
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:/Users/Norhan/Desktop/background.jpeg", 400, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    resultLayout.setBackground(background);

    // Create a new scene with the result layout
    Scene resultScene = new Scene(resultLayout, 400, 300);

    // Set the scene on the stage
    stage.setScene(resultScene);
    stage.show();
});


     
     
    
     
      GridPane addPane = new GridPane();
        addPane.setVgap(10);
        addPane.setHgap(10);
        addPane.add(id, 0, 0);
        addPane.add(idname, 1, 0);
        addPane.add(price, 0, 1);
        addPane.add(pricename, 1, 1);
        addPane.add(name, 0, 2);
        addPane.add(namee, 1, 2);
        addPane.add(quan, 0, 3);
        addPane.add(quaname, 1, 3);
        addPane.add(color, 0, 4);
        addPane.add(colorname, 1, 4);
        addPane.add(size, 0, 5);
        addPane.add(sizename, 1, 5);
        addPane.add(category, 0, 6);
        addPane.add(categoryname, 1, 6);
      
    addPane.add(addAnotherProductButton, 0, 8);
    addPane.add(doneButton, 1, 8);
        addPane.setAlignment(Pos.CENTER);
        addPane.setPadding(new Insets(10, 10, 10, 10));

         BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:/Users/Norhan/Desktop/background.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        addPane.setBackground(background);
        Scene addItemScene = new Scene(addPane, 350, 350);
        stage.setScene(addItemScene);
   }
   private void showFinalResult(Stage stage) {
    VBox resultLayout = new VBox(10);
    resultLayout.setPadding(new Insets(10));

    // Display order details
    Label orderDetailsLabel = createLabel("Order Details:\n" + cashieer.getOrder().getOrderDetails());
    orderDetailsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    // Display cashier ID
    Label cashierIdLabel = createLabel("Cashier ID: " + cashieer.getCashierID());
    cashierIdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    Label customerIdLabel;
    if (cashieer.c != null) {
        customerIdLabel = createLabel("Customer ID: " + cashieer.c.getCustomerid());
    } else {
        customerIdLabel = createLabel("Customer ID: N/A");
    }
    customerIdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    // Create a button to clear the cart
    Button clearCartButton = createButton("Clear Cart", () -> cashieer.cart.clear());

    resultLayout.getChildren().addAll(orderDetailsLabel, cashierIdLabel, customerIdLabel, clearCartButton);

    // Set background
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:/Users/Norhan/Desktop/background.jpeg", 400, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    resultLayout.setBackground(background);

    // Create a new scene with the result layout
    Scene resultScene = new Scene(resultLayout, 400, 300);

    // Set the scene on the stage
    stage.setScene(resultScene);
    stage.show();
}

private void clearFields(TextField... fields) {
    for (TextField field : fields) {
        field.clear();
    }
}
   private void showWelcomeScene(Stage stage) {
     
    Label welcomeLabel = new Label("Welcome "+user.getUsername()+" ! ");
    welcomeLabel.setTextFill(Color.BLACK);
        welcomeLabel.setFont(javafx.scene.text.Font.font("Braggadocio", javafx.scene.text.FontWeight.BOLD, 14)); 
        welcomeLabel.setAlignment(Pos.CENTER);
    Button checkOrdersButton = new Button("Check past orders");
    Button goToShopButton = new Button("Go to shop");
    Button signout = new Button("Sign out");
Button ViewCart = new Button ("View my Cart ");
ViewCart.setOnAction(event -> {
    initializeCartScene(stage);
stage.setScene(cartScene);
    stage.show();
       
    });
 // Initialize the Customer object

    checkOrdersButton.setOnAction(e -> {
        System.out.println("Checking past orders...");
        if (c == null) {
            // Assuming you have a Customer constructor that takes necessary parameters
            c = new Customer(1,user.getUsername(),user.getPassword());
        }
        try {
            displayOrderHistory(c,stage);
        } catch (IOException ex) {
            Logger.getLogger(ClothingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    });

    goToShopButton.setOnAction(e -> {
        System.out.println("Going to shop...");
       shop(stage);
    });

    signout.setOnAction(e -> {
        stage.setScene(mainScene);
    });

    VBox welcomeLayout = new VBox(10);
    welcomeLayout.getChildren().addAll(welcomeLabel, checkOrdersButton, goToShopButton,signout,ViewCart);
    welcomeLayout.setAlignment(Pos.CENTER);

    // Use the same background image as main scene
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:/Users/Norhan/Desktop/background.jpeg", 400, 200, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);

    welcomeLayout.setBackground(background);

     welcomeScene = new Scene(welcomeLayout, 400, 200);
    stage.setScene(welcomeScene);
    stage.setResizable(false);
    stage.show();
}

 public void shop(Stage stage) {
    StackPane hoodies = createLabelWithImage("Hoodies", "hoodie.jpeg",stage);
        StackPane jackets = createLabelWithImage("Jackets", "jackets.jpeg",stage);
        StackPane bottoms = createLabelWithImage("Bottoms", "buttom.jpeg",stage);
        Button goback = new Button("previous");
        goback.setOnAction(event -> { 
           stage.setResizable(false);
    stage.setScene(welcomeScene);
    stage.show(); 
});
        Button ViewCart = new Button ("View my Cart ");
ViewCart.setOnAction(event -> {
    initializeCartScene(stage);
stage.setScene(cartScene);
    stage.show();
       
    });
        GridPane shopLayout = new GridPane();
        shopLayout.add(hoodies, 0, 0);
        shopLayout.add(jackets, 1, 0);
        shopLayout.add(bottoms, 2, 0);
        shopLayout.add(goback,1,1);
        shopLayout.add(ViewCart,0,1);
        shopLayout.setPadding(new Insets(20, 30, 30, 20));
        shopLayout.setAlignment(Pos.CENTER);
        shopLayout.setHgap(20);
        shopLayout.setVgap(20);
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    shopLayout.setBackground(background);

    shopscene = new Scene(shopLayout, 400, 400);
    stage.setResizable(false);
        stage.setScene(shopscene);
        stage.show();
}
public void shopW(Stage stage) {
    StackPane hoodies = createLabelWithImage("Hoodies", "hoodie.jpeg",stage);
        StackPane jackets = createLabelWithImage("Jackets", "jackets.jpeg",stage);
        StackPane bottoms = createLabelWithImage("Bottoms", "buttom.jpeg",stage);
        Button goback = new Button("previous");
        goback.setOnAction(event -> { 
           stage.setResizable(false);
    stage.setScene(mainScene);
    stage.show();
});
        GridPane shopLayout = new GridPane();
        shopLayout.add(hoodies, 0, 0);
        shopLayout.add(jackets, 1, 0);
        shopLayout.add(bottoms, 2, 0);
        shopLayout.add(goback,1,1);
        ;
        shopLayout.setPadding(new Insets(20, 30, 30, 20));
        shopLayout.setAlignment(Pos.CENTER);
        shopLayout.setHgap(20);
        shopLayout.setVgap(20);
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 500, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    shopLayout.setBackground(background);

    shopscene = new Scene(shopLayout, 500, 400);
    stage.setResizable(false);
        stage.setScene(shopscene);
        stage.show();
        
}
private StackPane createLabelWithImage(String labelText, String imageName,Stage stage) {
         Label label = new Label(labelText);
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Arial", 14));
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-underline: true;");
label.setOnMouseClicked(event -> {
        openLabelStage(labelText, stage);
        showColorAndSizeOptions(subCategory.valueOf(labelText), stage, labelText); // Pass labelText here
    });

        Image image = new Image("file:/Users/Norhan/Desktop/" + imageName, 100, 100, true, true);
        ImageView imageView = new ImageView(image);

        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(imageView, label);

        label.setOnMouseClicked(event -> openLabelStage(labelText,stage));

        StackPane labelImagePane = new StackPane();
        labelImagePane.setAlignment(Pos.CENTER);
        labelImagePane.getChildren().add(vbox);

        return labelImagePane;
    }


private void openLabelStage(String labelText,Stage stage) {
    // Call the specific method based on labelText
    switch (labelText) {
        case "Hoodies":
            hoodiesStage(stage);
            break;
        case "Jackets":
            jacketsStage(stage);
            break;
        case "Bottoms":
            bottomsStage(stage);
            break;
        default:
            System.out.println("Unhandled label: " + labelText);
         
    } }
private void hoodiesStage(Stage stage) {
    Label label = new Label("Select Hoodies Subcategory");
    label.setAlignment(Pos.CENTER);
label.setTextFill(Color.BLACK);
        label.setFont(Font.font("American Typewriter", FontWeight.SEMI_BOLD, 20));
     
    Button backPrintedButton = createSubcategoryButton(subCategory.BackPrinted, stage);
    Button basicButton = createSubcategoryButton(subCategory.Basic, stage);
    Button oversizedButton = createSubcategoryButton(subCategory.Oversized, stage);
    // Add more buttons for other subcategories

    Button cancel = new Button("Cancel");
    cancel.setOnAction(event -> stage.setScene(shopscene)); // Switch back to the shop scene

    GridPane p = new GridPane();
    p.add(label, 0, 0, 3, 1);
    p.add(backPrintedButton, 0, 1);
    p.add(basicButton, 1, 1);
    p.add(oversizedButton, 2, 1);
    // Add more buttons for other subcategories
    p.add(cancel, 1, 2);
    
    p.setPadding(new Insets(20, 30, 30, 20));
    p.setAlignment(Pos.CENTER);
    p.setHgap(20);
    p.setVgap(20);

    StackPane layout = new StackPane(p);
     hoodiesscene = new Scene(layout, 500, 300);

    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 500, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    layout.setBackground(background);
stage.setResizable(false);
    stage.setScene(hoodiesscene);
    stage.show();
}

private Button createSubcategoryButton(subCategory subcategory, Stage stage) {
    Button button = new Button(subcategory.toString());
    button.setOnAction(event -> {
        System.out.println("Selected Hoodies Subcategory: " + subcategory);
      
        showColorAndSizeOptions(subcategory,stage,"Hoodies");
    });
    return button;
}
private Button createSubcategoryButtonJ(subCategory subcategory, Stage stage) {
    Button button = new Button(subcategory.toString());
    button.setOnAction(event -> {
        System.out.println("Selected Jacket Subcategory: " + subcategory);
      
        showColorAndSizeOptions(subcategory,stage,"Jackets");
    });
    return button;
}
private Button createSubcategoryButtonB(subCategory subcategory, Stage stage) {
    Button button = new Button(subcategory.toString());
    button.setOnAction(event -> {
        System.out.println("Selected Bottoms Subcategory: " + subcategory);
      
        showColorAndSizeOptions(subcategory,stage,"Bottoms");
    });
    return button;
}
private void showColorAndSizeOptions(subCategory subcategory, Stage stage, String labelText) {
 
// else the user will be able eno yedkhul ykhtar el options el 3ayzha
    Label colorLabel = new Label("Select Color:");
    Label sizeLabel = new Label("Select Size:");
    Label quantityLabel = new Label("Quantity:");
Button ViewCart = new Button ("View my Cart ");
ViewCart.setOnAction(event -> {
    initializeCartScene(stage);
stage.setScene(cartScene);
    stage.show();
       
    });
    ComboBox<ProductColor> colorComboBox = new ComboBox<>(FXCollections.observableArrayList(ProductColor.values()));
    ComboBox<ProductSize> sizeComboBox = new ComboBox<>(FXCollections.observableArrayList(ProductSize.values()));
    TextField quantityTextField = new TextField("1"); // Default quantity is 1

    Button cancel = new Button("Cancel");
    cancel.setOnAction(event -> stage.setScene(shopscene));
    
    Button increaseQuantityButton = new Button("+");
    increaseQuantityButton.setOnAction(event -> {
        int currentQuantity = Integer.parseInt(quantityTextField.getText());
        quantityTextField.setText(Integer.toString(currentQuantity + 1));
    });

    Button decreaseQuantityButton = new Button("-");
    decreaseQuantityButton.setOnAction(event -> {
        int currentQuantity = Integer.parseInt(quantityTextField.getText());
        if (currentQuantity > 0) {
            quantityTextField.setText(Integer.toString(currentQuantity - 1));
        }
    });

    Button applyButton = new Button("Add to cart");
    applyButton.setOnAction(event -> {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
        // User is not logged in, show error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please login first.");
        alert.showAndWait();
        stage.setScene(mainScene);
        return;
    } else {
            ProductColor selectedColor = colorComboBox.getValue();
            ProductSize selectedSize = sizeComboBox.getValue();
            int selectedQuantity = Integer.parseInt(quantityTextField.getText());

            String selectedCategory = labelText;
            selectedProduct = new Product(1, 
                    calculateProductPrice(selectedColor, selectedSize, subcategory),
                    subcategory.toString(), selectedQuantity, selectedColor.toString(),
                    selectedSize.toString(), selectedCategory);

            // Display price and total price
            cart.addProduct(selectedProduct);
            updateCartScene(stage,cart);

            // Switch back to the shop scene
            stage.setScene(shopscene);

        }
    });

    GridPane optionsPane = new GridPane();
    optionsPane.add(colorLabel, 0, 0);
    optionsPane.add(colorComboBox, 1, 0);
    optionsPane.add(sizeLabel, 0, 1);
    optionsPane.add(sizeComboBox, 1, 1);
    optionsPane.add(quantityLabel, 0, 2);
    optionsPane.add(quantityTextField, 1, 2);
    optionsPane.add(increaseQuantityButton, 2, 2);
    optionsPane.add(decreaseQuantityButton, 3, 2);
    optionsPane.add(applyButton, 1, 3);
    optionsPane.add(cancel, 2, 3);
    optionsPane.add(ViewCart,3,3);
    optionsPane.setPadding(new Insets(30, 30, 30, 30));
    optionsPane.setAlignment(Pos.CENTER);
    optionsPane.setHgap(10);
    optionsPane.setVgap(10);

    StackPane optionsLayout = new StackPane(optionsPane);
    Scene optionsScene = new Scene(optionsLayout, 500, 200);

    // Set background
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 500, 200, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    optionsLayout.setBackground(background);
stage.setResizable(false);
    stage.setScene(optionsScene);
    stage.show();
}

private void displayOrderHistory(Customer c, Stage stage) throws IOException   {
    VBox orderHistoryLayout = new VBox();
    orderHistoryLayout.setAlignment(Pos.CENTER);
    orderHistoryLayout.setSpacing(10);
    orderHistoryLayout.setPadding(new Insets(10));

    // Load order history from file
//    Order order = Order.loadFromFile("orders.bin");
    //order = new Order();
    String orderDetails = order.getOrderDetails();
    if (orderDetails.isEmpty()) {
        Label emptyLabel = new Label("Order history is empty.");
        emptyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        orderHistoryLayout.getChildren().add(emptyLabel);
    } else {
         //order.loadFromFile("orders.bin");
        TextArea orderHistoryTextArea = new TextArea();
        orderHistoryTextArea.setEditable(false);
        orderHistoryTextArea.appendText(orderDetails);
        
        orderHistoryLayout.getChildren().add(orderHistoryTextArea);
    }

    Button goback = new Button("Previous");
    goback.setOnAction(event -> {
        stage.setScene(welcomeScene);
        stage.show();
    });

    VBox backButtonBox = new VBox(goback);
    backButtonBox.setAlignment(Pos.BOTTOM_CENTER);

    orderHistoryLayout.getChildren().add(backButtonBox);

    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    orderHistoryLayout.setBackground(background);

    Scene orderHistoryScene = new Scene(orderHistoryLayout, 400, 400);
    stage.setScene(orderHistoryScene);
    stage.setResizable(false);
    stage.show();
}

 public void rateOrder(Stage stage) {
        if (c.getOrderRatings().containsKey(order.getOrderid())) {
            // Order has already been rated, you can choose to show a message or handle it accordingly
            System.out.println("You have already rated this order.");
        } else {
            // Create a new stage for order rating
            Stage ratingStage = new Stage();
            ratingStage.setTitle("Rate Order");
            
            // Create a VBox for the rating controls
            VBox ratingLayout = new VBox(10);
            ratingLayout.setAlignment(Pos.CENTER);
            ratingLayout.setPadding(new Insets(10));

            // Add rating components (you can customize this part based on your preferences)
            Label ratingLabel = new Label("Rate the order:");
            ratingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ToggleGroup ratingGroup = new ToggleGroup();
            RadioButton oneStar = new RadioButton("1 Star");
            RadioButton twoStars = new RadioButton("2 Stars");
            RadioButton threeStars = new RadioButton("3 Stars");
            RadioButton fourStars = new RadioButton("4 Stars");
            RadioButton fiveStars = new RadioButton("5 Stars");

            oneStar.setToggleGroup(ratingGroup);
            twoStars.setToggleGroup(ratingGroup);
            threeStars.setToggleGroup(ratingGroup);
            fourStars.setToggleGroup(ratingGroup);
            fiveStars.setToggleGroup(ratingGroup);

            Button rateButton = new Button("Rate");
            rateButton.setOnAction(event -> {
                int selectedRating = getSelectedRating(ratingGroup);
                if (selectedRating > 0) {
                    String orderidd=String.valueOf(order.getOrderid());
                    c.rateOrder(orderidd, selectedRating);
                    System.out.println("Order rated successfully with " + selectedRating + " stars.");
                    ratingStage.close();
                } else {
                    System.out.println("Please select a rating.");
                }
            });

            ratingLayout.getChildren().addAll(ratingLabel, oneStar, twoStars, threeStars, fourStars, fiveStars, rateButton);

            BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    ratingLayout.setBackground(background);

            Scene ratingScene = new Scene(ratingLayout, 300, 200);
            ratingStage.setScene(ratingScene);
            
            // Show the rating stage
            ratingStage.show();
        }
    }

    private int getSelectedRating(ToggleGroup group) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        if (selectedRadioButton != null) {
            return Integer.parseInt(selectedRadioButton.getText().split(" ")[0]);
        }
        return 0;
    }
private void initializeCartScene(Stage stage) {
    VBox cartLayout = new VBox(10);

    if (cart.isEmpty()) {
        Label emptyLabel = new Label("Your Cart is Empty.");
        cartLayout.getChildren().add(emptyLabel);
    } else {
        Label cartLabel = new Label("Shopping Cart:");
        cartLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        cartLayout.getChildren().add(cartLabel);

        for (Product product : cart.getProducts()) {
            Label productLabel = new Label(product.toString());  // Update this based on your Product class
            cartLayout.getChildren().add(productLabel);
        }

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(event -> checkout(stage,cart));  // Implement your checkout logic
        cartLayout.getChildren().add(checkoutButton);
    }

    Button backToShopButton = new Button("Back to Shop");
    backToShopButton.setOnAction(event -> stage.setScene(shopscene)); // Switch back to the shop scene
    cartLayout.getChildren().add(backToShopButton);

    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 700, 700, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);

    cartLayout.setBackground(background);

    cartScene = new Scene(cartLayout, 700, 700);
}
private int calculateProductPrice(ProductColor color, ProductSize size, subCategory subcategory) {
    // Replace this logic with your actual price calculations
    int basePrice = 220; // Replace with the base price of the product
    int colorPrice = color == ProductColor.Black ? 20 : 0; // Example color-based price
    int sizePrice = size == ProductSize.XL ? 25: 0; // Example size-based price
    int subcategoryPrice = subcategory == subCategory.BackPrinted ? 10 : 0; // Example subcategory-based price

    return basePrice + colorPrice + sizePrice + subcategoryPrice;
}
private void updateCartScene(Stage stage, Cart cart) {
    // Update the cart scene based on the contents of the shopping cart
    VBox cartLayout = new VBox(10);

    if (cart.isEmpty()) {
        Label emptyLabel = new Label("Your Cart is Empty.");
        cartLayout.getChildren().add(emptyLabel);
    } else {
        Label cartLabel = new Label("Shopping Cart:");
        cartLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        cartLayout.getChildren().add(cartLabel);

        for (Product product : cart.getProducts()) {
            Label productLabel = new Label(product.toString());  // Update this based on your Product class
            cartLayout.getChildren().add(productLabel);
        }

        Button checkoutButton = new Button("Checkout");
checkoutButton.setOnAction(event -> {
   // Set order details before checkout
//    checkout(stage, cart);
//    System.out.println("all order details are saved in orders.bin");
});
cartLayout.getChildren().add(checkoutButton);

    }

    Button backToShopButton = new Button("Back to Shop");
    backToShopButton.setOnAction(event -> stage.setScene(shopscene)); // Switch back to the shop scene
    cartLayout.getChildren().add(backToShopButton);

    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    cartLayout.setBackground(background);

    cartScene = new Scene(cartLayout, 400, 400);
    stage.setScene(cartScene);
    stage.show();
}
public void checkout(Stage stage, Cart cart) {
    // Example: Initialize a Customer object (replace with your actual initialization logic)
    c = new Customer(1, user.getUsername(), user.getPassword());
    cashieer = new Cashier(11, user.getUsername(), user.getPassword());

    if (cart.isEmpty()) {
        System.out.println("Error: Your Cart is Empty. Please add items before checkout.");
        return;
    }     

    // Create a new Order object
    order = new Order();
    
    // Set the order details from the cart
    order.setOrderDetails(cart); //save to file

    // Check if cashieer is initialized
    if (cashieer != null) {
        // Proceed with the payment method
        paymentMethod(stage);
    } else {
        System.out.println("Error: Cashier object is not initialized.");
    }
}

public void paymentMethod(Stage stage) {
    VBox paymentLayout = new VBox(10);
    paymentLayout.setPadding(new Insets(10));

    // Display total amount
    paymentLayout.getChildren().add(createLabel("Total: $" + cart.gettotalamount()));

    // Create buttons for payment options
    Button cashButton = createButton("Cash", () -> {
    try { 
        processCashPayment(stage);
        
    } catch (IOException ex) {
        Logger.getLogger(ClothingSystem.class.getName()).log(Level.SEVERE, null, ex);
        // Handle the IOException or rethrow it if needed
    }
});
    Button visaButton = createButton("Visa", () -> displayVisaPayment(stage));

    paymentLayout.getChildren().addAll(cashButton, visaButton);

    // Set background
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    paymentLayout.setBackground(background);

    Scene paymentScene = new Scene(paymentLayout, 300, 200);
    stage.setScene(paymentScene);
    stage.show();
}

private void processCashPayment(Stage stage) throws IOException {
    System.out.println("Processing Cash Payment...");


    Label transferLabel = createLabel("Done transferring $" + cart.gettotalamount() +
            " to the store by Cashier " + cashieer.getUsername());
    transferLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    
    VBox paymentResultLayout = new VBox(10);
    paymentResultLayout.getChildren().addAll(transferLabel, createButton("OK",
            () -> clearShoppingCart(stage)),createButton("Rate order ",()->rateOrder(stage)));

    // Set background
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    paymentResultLayout.setBackground(background);

    // Create a new scene with the payment result layout
    Scene paymentResultScene = new Scene(paymentResultLayout, 400, 300);

    // Set the scene on the stage
    stage.setScene(paymentResultScene);
    stage.show();
}

private void displayVisaPayment(Stage stage) {
    VBox visaLayout = new VBox(10);
    visaLayout.setPadding(new Insets(10));

    // Create input fields for Visa payment details
    visaLayout.getChildren().addAll(
            createLabel("Enter Visa Details:"),
            createTextField("Card Number"),
            createTextField("Expiry Date"),
            createTextField("CVV"),
            createButton("OK", () -> {
        try {
            processVisaPayment(stage);
        } catch (IOException ex) {
            Logger.getLogger(ClothingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }),
            createButton("Cancel", stage::close)
    );

    Scene visaScene = new Scene(visaLayout, 300, 200);
    stage.setScene(visaScene);
    stage.show();
}

private void processVisaPayment(Stage stage) throws IOException {
    // Implement Visa payment processing logic
    System.out.println("Processing Visa Payment...");

    // Display a message indicating successful payment
    System.out.println("Done transferring $" + cart.gettotalamount() + " by Cashier " + cashieer.getUsername()+order.getOrderDetails());
 String transferMessage = "Done transferring $" + cart.gettotalamount() +
            " to the store by Cashier " + cashieer.getUsername();
 
  Label transferLabel = createLabel(transferMessage);
    transferLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
 VBox paymentResultLayout = new VBox(10);
    paymentResultLayout.getChildren().addAll(transferLabel, createButton("Clear Cart",
            () -> clearShoppingCart(stage)));

    updateCartScene(stage,cart);
       BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    paymentResultLayout.setBackground(background);

    Scene paymentResultScene = new Scene(paymentResultLayout, 400, 300);

    stage.setScene(paymentResultScene);
    stage.show();
}

private javafx.scene.control.Label createLabel(String text) {
    return new javafx.scene.control.Label(text);
}

private javafx.scene.control.TextField createTextField(String promptText) {
    javafx.scene.control.TextField textField = new javafx.scene.control.TextField();
    textField.setPromptText(promptText);
    return textField;
}

private Button createButton(String text, Runnable action) {
    Button button = new Button(text);
    button.setOnAction(event -> Platform.runLater(action));
    return button;
}


private void clearShoppingCart(Stage stage)  {
     order.setOrderDetails(cart);
     System.out.println("all order details are saved in orders.bin");
    cart.clear();
    System.out.println("Shopping Cart Cleared");

    // Update the cart scene after clearing the cart
    updateCartScene(stage,cart);
}

    public enum ProductColor {
        Black, White, Green, Blue, Grey;
    }

    public enum ProductSize {
        S, M, L, XL;
    }

private void jacketsStage(Stage stage) {
    Label label = new Label("Select Jackets Subcategory");
    label.setAlignment(Pos.CENTER);
label.setTextFill(Color.BLACK);
        label.setFont(Font.font("American Typewriter", FontWeight.SEMI_BOLD, 20));
    Button Leathern = createSubcategoryButtonJ(subCategory.Leather, stage);
    Button Pump = createSubcategoryButtonJ(subCategory.Pump, stage);
    Button WaterProof = createSubcategoryButtonJ(subCategory.WaterProof, stage);
    // Set background
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 300, 200, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Button cancel = new Button("Cancel");
    cancel.setOnAction(event -> stage.setScene(shopscene)); // Switch back to the shop scene

    GridPane p = new GridPane();
    p.add(label, 0, 0, 3, 1);
    p.add(Leathern, 0, 1);
    p.add(Pump, 1, 1);
    p.add(WaterProof, 2, 1);
    // Add more buttons for other subcategories
    p.add(cancel, 1, 2);
    
    p.setPadding(new Insets(20, 30, 30, 20));
    p.setAlignment(Pos.CENTER);
    p.setHgap(20);
    p.setVgap(20);
    StackPane layout = new StackPane(p);
    Scene Jscene = new Scene(layout, 500, 300);

    BackgroundImage backgroundImage2 = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 500, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage2);
    layout.setBackground(background);
   
stage.setResizable(false);
    stage.setScene(Jscene);
    stage.show();
}

private void bottomsStage(Stage stage) {
    // Implement the Bottoms stage content here
    Label label = new Label("This is the Bottoms stage.");
    label.setAlignment(Pos.CENTER);
label.setTextFill(Color.BLACK);
        label.setFont(Font.font("American Typewriter", FontWeight.SEMI_BOLD, 20)); 
    label.setAlignment(Pos.CENTER);
           Button Cargo = createSubcategoryButtonB(subCategory.Cargo, stage);
    Button Jeans = createSubcategoryButtonB(subCategory.Jeans, stage);
    Button SweetPants  = createSubcategoryButtonB(subCategory.SweetPants, stage);
     Button Skirt  = createSubcategoryButtonB(subCategory.Skirt, stage);
Image Jeanss = new Image("file:/Users/Norhan/Desktop/Jeans.jpeg");
Image Cargoss= new Image("file:/Users/Norhan/Desktop/Cargo.jpeg");
Image SweetPantss=new Image("file:/Users/Norhan/Desktop/Sweatpants.jpeg");
Image Skirtt= new Image ("file:/Users/Norhan/Desktop/Skirt.jpeg");
        ImageView Jeansview = new ImageView(Jeanss);
         ImageView Cargosview = new ImageView(Cargoss);
          ImageView Skirtview = new ImageView(Skirtt);
          ImageView Sweatpantsview=new ImageView(Skirtt);
            Jeans.setGraphic(Jeansview);
            Jeans.setGraphic(Cargosview);
            Jeans.setGraphic(Skirtview);
            Jeans.setGraphic(Sweatpantsview);
    Button cancel = new Button("Cancel");
    cancel.setOnAction(event -> stage.setScene(shopscene)); // Switch back to the shop scene

    GridPane p = new GridPane();
        p.add(label, 0, 0, 4, 1);
        p.add(Cargo, 0, 1);
        p.add(Jeans, 1, 1);
        p.add(SweetPants, 2, 1);
        p.add(Skirt, 3, 1);
        p.add(cancel, 1, 2);
        p.getChildren().addAll(Jeansview,Cargosview,Skirtview,Sweatpantsview);
 p.setPadding(new Insets(20, 30, 30, 20));
    p.setAlignment(Pos.CENTER);
    p.setHgap(20);
    p.setVgap(20);

   StackPane layout = new StackPane(p);
        Scene scene = new Scene(layout, 600, 400);


    // Set background
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 600, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    layout.setBackground(background);
stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
}

private void showSupplierScreen(Stage stage){
       Button addprod = new Button("Add Products");
       Button history = new Button("View History");
       Label l1 = new Label("Choose What do you want to do");
      l1.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14)); // Subheading font size
      l1.setAlignment(Pos.CENTER);
      
      addprod.setOnAction(e->{
         addproduct(stage);
          
      });
      
    GridPane suppPane = new GridPane();
    suppPane.setVgap(10);
    suppPane.setHgap(10);
    suppPane.add(l1, 0, 0);
    suppPane.add(addprod, 0, 1);
    suppPane.add(history, 1, 1);
    suppPane.setAlignment(Pos.CENTER);
    suppPane.setPadding(new Insets(10, 10, 10, 10));
    
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 500, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    suppPane.setBackground(background);
    
    Scene suppScene = new Scene(suppPane, 500, 300);
    stage.setScene(suppScene);
       
   }
private void addproduct(Stage stage){
     Label id = new Label("Enter product id");
     Label price = new Label("Enter Product Price");
     Label name = new Label("Enter product name:");
     Label quan = new Label("Enter product quantity");
     Label color = new Label ("Enter product Color");
     Label size = new Label("Enter product Size");
     Label category = new Label("Enter product category");
     id.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     price.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     name.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     quan.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     size.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     color.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     category.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     TextField idname = new TextField();
     TextField pricename = new TextField();
     TextField namee= new TextField();
     TextField quaname = new TextField();
     TextField colorname = new TextField();
     TextField sizename = new TextField();
     TextField categoryname = new TextField();
         
     Button submit= new Button("Submit");
     
     submit.setOnAction(e->{
        String idd = idname.getText();
        String pricee = pricename.getText();
        String prodname = namee.getText();
        String quantityname = quaname.getText();
        String colorna = colorname.getText();
        String sizena=sizename.getText();
        String categoryna = categoryname.getText();
        
        //Product newProduct = new Product(Integer.parseInt(idd), Integer.parseInt(pricee), prodname,Integer.parseInt(quantityname), colorna, sizena, categoryna);
        s.addProduct(Integer.parseInt(idd), Integer.parseInt(pricee), prodname,Integer.parseInt(quantityname), colorna, sizena, categoryna);
        
       
   });
      GridPane addPane = new GridPane();
        addPane.setVgap(10);
        addPane.setHgap(10);
        addPane.add(id, 0, 0);
        addPane.add(idname, 1, 0);
        addPane.add(price, 0, 1);
        addPane.add(pricename, 1, 1);
        addPane.add(name, 0, 2);
        addPane.add(namee, 1, 2);
        addPane.add(quan, 0, 3);
        addPane.add(quaname, 1, 3);
        addPane.add(color, 0, 4);
        addPane.add(colorname, 1, 4);
        addPane.add(size, 0, 5);
        addPane.add(sizename, 1, 5);
        addPane.add(category, 0, 6);
        addPane.add(categoryname, 1, 6);
        addPane.add(submit, 1, 7);
        addPane.setAlignment(Pos.CENTER);
        addPane.setPadding(new Insets(10, 10, 10, 10));
     BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 500, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    addPane.setBackground(background);
    
    Scene addScene = new Scene(addPane, 500, 300);
    stage.setScene(addScene);
 }
private void showAdminScreen(Stage stage2){
     Button add = new Button("Add Product");
     Button remove = new Button("Remove Product");
     Button edit = new Button("Edit Product");
     Button list = new Button("List Products");
     Button search = new Button("Search for a product");
     Button adduser = new Button("Add User");
     Button removeuser = new Button("Remove User");
     Button edituser = new Button("Edit User");
     Button searchuser = new Button("Search User");
     Button listuser = new Button("List User");
     Label l1 = new Label("Choose What do you want to do");
     l1.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14)); // Subheading font size
     l1.setAlignment(Pos.CENTER);
   
     add.setOnAction(e->{
      AddItems(stage2);
     });
     list.setOnAction(e->{
       listItems(stage2);
     });
     remove.setOnAction(e->{
         RemoveItems(stage2);
     });
     edit.setOnAction(e->{
         editItems(stage2);
     });
     search.setOnAction(e->{
         searchItems(stage2);
     });
     listuser.setOnAction(e->{
         listUser(stage2);
     });
     
     adduser.setOnAction(e->{
         addUser(stage2);
     });
     removeuser.setOnAction(e->{
         removeUser(stage2);
     });
     edituser.setOnAction(e->{
         editUser(stage2);
     });
     searchuser.setOnAction(e->{
         searchUser(stage2);
     });
     
    GridPane AdminPane = new GridPane();
    AdminPane.setVgap(10);
    AdminPane.setHgap(10);
    AdminPane.add(l1, 0, 0);
    AdminPane.add(add, 0, 1);
    AdminPane.add(remove, 1, 1);
    AdminPane.add(edit, 0, 2);
    AdminPane.add(list, 1, 2);
    AdminPane.add(search, 0, 3);
    AdminPane.add(adduser, 1, 3);
    AdminPane.add(removeuser, 0, 4);
    AdminPane.add(edituser, 1, 4);
    AdminPane.add(listuser, 0, 5);
    AdminPane.add(searchuser, 1, 5);
    AdminPane.setAlignment(Pos.CENTER);
    AdminPane.setPadding(new Insets(10, 10, 10, 10));
    
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 500, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    AdminPane.setBackground(background);
    
    adminScene = new Scene(AdminPane, 500, 300);
    stage2.setScene(adminScene);
    
   }
private void editUser(Stage stage){
       Label edit = new Label ("Edit User");
       Label olduser = new Label("write old username");
       Label pass = new Label("Write password:");
       Label user = new Label("Edit username");
       edit.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       pass.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       user.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       olduser.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       Button editt = new Button("edit");
       Button back = new Button("Go Back");
       TextField passedit = new TextField();
       TextField useredit = new TextField();
       TextField oldedit = new TextField();

       editt.setOnAction(e->{
           try {
               a.editu(passedit,useredit,oldedit);
           } catch (Exception ex) {
               Logger.getLogger(ClothingSystem.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
       });
       
       back.setOnAction(e->{
          stage.setScene(adminScene);
          stage.show();
       });
     
        GridPane addPane = new GridPane();
        addPane.setVgap(10);
        addPane.setHgap(10);
        addPane.add(edit, 1, 0);
        addPane.add(olduser, 1, 1);
        addPane.add(oldedit,2,1);
        addPane.add(user, 1, 2);
        addPane.add(useredit, 2, 2);
        addPane.add(pass, 1,3 );
        addPane.add(passedit, 2, 3);
        addPane.add(editt, 4, 4);
        addPane.add(back, 3, 4);
        
        addPane.setAlignment(Pos.CENTER);
        addPane.setPadding(new Insets(10, 10, 10, 10));
       
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        addPane.setBackground(background);
    
        // Create a new scene for the add item window
        Scene editItemScene = new Scene(addPane, 350, 350);
        stage.setScene(editItemScene);
        
        
      
}
   private void removeUser(Stage stage){
       Label userr = new Label("Enter username");
       userr.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       TextField userrname = new TextField();
       Button remove= new Button("Remove");
       Button back = new Button("Go Back");
       remove.setOnAction(e->{
           
           try {
                 //a.LoadFromFileuser("Users.bin");
               a.remove(userrname.getText());
              
           } catch (IOException ex) {
              ex.getStackTrace();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(ClothingSystem.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       });
        back.setOnAction(e->{
          stage.setScene(adminScene);
          stage.show();
       });
       GridPane removePane = new GridPane();
        removePane.setVgap(10);
        removePane.setHgap(10);
        removePane.add(userr, 0, 0);
        removePane.add(userrname, 1, 0);
        removePane.add(remove, 0, 1);
        removePane.add(back,0,2);
        removePane.setAlignment(Pos.CENTER);
        removePane.setPadding(new Insets(10, 10, 10, 10));
       BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        removePane.setBackground(background);
    
        // Create a new scene for the add item window
        Scene removeItemScene = new Scene(removePane, 350, 350);
        stage.setScene(removeItemScene);
   }
   
  private void addUser(Stage stage){
     Label user = new Label("Enter your username");
     Label pass = new Label("Enter your password");
     Label usertype = new Label("Enter your type");
     user.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     pass.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     usertype.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     Button submit= new Button("Submit");
     Button back = new Button("Go Back");
     TextField username=new TextField();
     TextField password = new TextField();
     TextField userTypee = new TextField();
     
     submit.setOnAction(e->{
        
        User us= new User(username.getText(),password.getText(),userTypee.getText());
         try {
             a.addu(us);
         } catch (IOException ex) {
                        ex.getStackTrace()
;
         } catch (ClassNotFoundException ex) {
           ex.getStackTrace();
         }
   });
     
     back.setOnAction(e->{
           stage.setScene(adminScene);
          stage.show();
     });
     GridPane addPane = new GridPane();
        addPane.setVgap(10);
        addPane.setHgap(10);
        addPane.add(user, 0, 0);
        addPane.add(username, 1, 0);
        addPane.add(usertype, 0, 1);
        addPane.add(userTypee, 1, 1);
        addPane.add(pass, 1, 2);
        addPane.add(password, 0, 2);
        addPane.add(submit, 0, 3);
        addPane.add(back, 1, 3);
        addPane.setAlignment(Pos.CENTER);
        addPane.setPadding(new Insets(10, 10, 10, 10));
        
         BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 500, 300, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    addPane.setBackground(background);
    
    Scene addScene = new Scene(addPane, 500, 300);
    stage.setScene(addScene);
   }
   
   private void searchUser(Stage stage){
       Label user= new Label("Chose UserType");
       TextField enter = new TextField();
       TextArea result = new TextArea();
       Button found = new Button("Search");
       Button backk = new Button("Go back");
       user.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       ComboBox<String> Dropdown = new ComboBox<>();
       Dropdown.getItems().addAll("username", "password", "usertype");
       Dropdown.setValue("username"); // Default selection
       
       found.setOnAction(e->{
            String searchValue = enter.getText();
        String searchCriteria = Dropdown.getValue();
           try {
               //a.LoadFromFileuser("Users.bin");
               a.searchUser(searchValue,searchCriteria,result);
           } catch (Exception ex) {
             ex.getMessage();
           }
           
       });
        backk.setOnAction(e->{
          stage.setScene(adminScene);
          stage.show();
       });
        
         GridPane searchPane = new GridPane();
        searchPane.setVgap(10);
        searchPane.setHgap(10);
        searchPane.add(user, 0, 0);
        searchPane.add(enter, 0, 1);
        searchPane.add(Dropdown, 1, 1);
        searchPane.add(result, 0, 2);
        searchPane.add(found, 0, 4);
        searchPane.add(backk, 0, 3);
        searchPane.setAlignment(Pos.CENTER);
        searchPane.setPadding(new Insets(10, 10, 10, 10));
        
       BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        searchPane.setBackground(background);
    
        // Create a new scene for the add item window
        Scene searchItemScene = new Scene(searchPane, 350, 350);
        stage.setScene(searchItemScene);
       
       
   }
   
   private void listUser(Stage stage){
      Label user = new Label("Avaible Users:");
      user.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
      TextArea userlist = new TextArea();
      Button viewdata = new Button("Press here");
      Button goback = new Button("Go Back");
      userlist.setEditable(false);
      viewdata.setOnAction(e -> {
        try {
            //a.generateAndStoreSpecificCredentials("Users.bin");
            a.listu(userlist);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    });
      
      goback.setOnAction(e->{
          stage.setScene(adminScene);
          stage.show();
      });
      
      GridPane listPane = new GridPane();
        listPane.setVgap(10);
        listPane.setHgap(10);
        listPane.add(user, 0, 0);
        listPane.add(userlist, 0, 1);
        listPane.add(viewdata, 0, 2);
        listPane.add(goback, 0, 3);
        listPane.setAlignment(Pos.CENTER);
        listPane.setPadding(new Insets(10, 10, 10, 10));
      
       BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        listPane.setBackground(background);
    
        // Create a new scene for the add item window
        Scene listItemScene = new Scene(listPane, 350, 350);
        stage.setScene(listItemScene);
   }
   private void searchItems(Stage stage) {
    Label idd = new Label("Enter name");
    TextField enter = new TextField();
    TextArea result = new TextArea();
    Button found = new Button("Search");
    Button backk = new Button("Go back");
    idd.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));

    ComboBox<String> criteriaDropdown = new ComboBox<>();
    criteriaDropdown.getItems().addAll("Product Name", "Product ID", "Color", "Quantity", "Size", "Price", "Category");
    criteriaDropdown.setValue("Product Name"); // Default selection

    found.setOnAction(e -> {
        String namee = enter.getText();
        String criteria = criteriaDropdown.getValue();
        try {
            // Load items data from file and perform search
            a.loadFromFile("Products.bin");
            a.search(namee, criteria, result);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    });

    backk.setOnAction(e -> {
        stage.setScene(adminScene);
        stage.show();
    });

    GridPane searchPane = new GridPane();
    searchPane.setVgap(10);
    searchPane.setHgap(10);
    searchPane.add(idd, 0, 0);
    searchPane.add(enter, 0, 1);
    searchPane.add(criteriaDropdown, 1, 1); // Add dropdown list
    searchPane.add(result, 0, 2);
    searchPane.add(found, 0, 4);
    searchPane.add(backk, 0, 3);
    searchPane.setAlignment(Pos.CENTER);
    searchPane.setPadding(new Insets(10, 10, 10, 10));

    BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
    Background background = new Background(backgroundImage);
    searchPane.setBackground(background);

    Scene searchItemScene = new Scene(searchPane, 350, 350);
    stage.setScene(searchItemScene);
}

   private void listItems(Stage stage){
      Label items = new Label("The availbe Products:");
      items.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
      TextArea proditems = new TextArea();
      Button viewdata = new Button("Press here");
      Button goback = new Button("Go Back");
      proditems.setEditable(false);
      proditems.setWrapText(true);
      viewdata.setOnAction(e->{
          try {
              //a.generateAndStoreSpecificCredentials();
              a.loadFromFile("Products.bin");
              a.listp(proditems);
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          
      });
      
      goback.setOnAction(e->{
          stage.setScene(adminScene);
          stage.show();
      });
      
      GridPane listPane = new GridPane();
        listPane.setVgap(10);
        listPane.setHgap(10);
        listPane.add(items, 0, 0);
        listPane.add(proditems, 0, 1);
        listPane.add(viewdata, 0, 2);
        listPane.add(goback, 0, 3);
        listPane.setAlignment(Pos.CENTER);
        listPane.setPadding(new Insets(10, 10, 10, 10));
      
       BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        listPane.setBackground(background);
    
        // Create a new scene for the add item window
        Scene listItemScene = new Scene(listPane, 350, 350);
        stage.setScene(listItemScene);
        
   }
   private void RemoveItems(Stage stage){
       Label id = new Label("Enter product id");
       id.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       TextField idname = new TextField();
       Button remove= new Button("Remove");
       Button back = new Button("Go Back");
       remove.setOnAction(e->{
           
           try {
               String idd = idname.getText();
               int productId = Integer.parseInt(idd);
               a.removep(productId);
              
           } catch (IOException ex) {
               ex.getStackTrace();
           }
       });
        back.setOnAction(e->{
          stage.setScene(adminScene);
          stage.show();
       });
       GridPane removePane = new GridPane();
        removePane.setVgap(10);
        removePane.setHgap(10);
        removePane.add(id, 0, 0);
        removePane.add(idname, 1, 0);
        removePane.add(remove, 0, 1);
       BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        removePane.setBackground(background);
    
        // Create a new scene for the add item window
        Scene removeItemScene = new Scene(removePane, 350, 350);
        stage.setScene(removeItemScene);
   }
   private void AddItems(Stage stage){  
     Label id = new Label("Enter product id");
     Label price = new Label("Enter Product Price");
     Label name = new Label("Enter product name:");
     Label quan = new Label("Enter product quantity");
     Label color = new Label ("Enter product Color");
     Label size = new Label("Enter product Size");
     Label category = new Label("Enter product category");
     id.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     price.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     name.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     quan.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     size.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     color.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     category.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
     TextField idname = new TextField();
     TextField pricename = new TextField();
     TextField namee= new TextField();
     TextField quaname = new TextField();
     TextField colorname = new TextField();
     TextField sizename = new TextField();
     TextField categoryname = new TextField();
         
     Button submit= new Button("Submit");
     
     submit.setOnAction(e->{
        String idd = idname.getText();
        String pricee = pricename.getText();
        String prodname = namee.getText();
        String quantityname = quaname.getText();
        String colorna = colorname.getText();
        String sizena=sizename.getText();
        String categoryna = categoryname.getText();
        
        Product newProduct = new Product(Integer.parseInt(idd), Integer.parseInt(pricee), prodname,Integer.parseInt(quantityname), colorna, sizena, categoryna);
        try {
            // Add the new product

            a.addp(newProduct);
        }
       catch (IOException ee) {
            ee.printStackTrace(); // Handle the exception appropriately
        }
         
     });
     
      GridPane addPane = new GridPane();
        addPane.setVgap(10);
        addPane.setHgap(10);
        addPane.add(id, 0, 0);
        addPane.add(idname, 1, 0);
        addPane.add(price, 0, 1);
        addPane.add(pricename, 1, 1);
        addPane.add(name, 0, 2);
        addPane.add(namee, 1, 2);
        addPane.add(quan, 0, 3);
        addPane.add(quaname, 1, 3);
        addPane.add(color, 0, 4);
        addPane.add(colorname, 1, 4);
        addPane.add(size, 0, 5);
        addPane.add(sizename, 1, 5);
        addPane.add(category, 0, 6);
        addPane.add(categoryname, 1, 6);
        addPane.add(submit, 1, 7);
        addPane.setAlignment(Pos.CENTER);
        addPane.setPadding(new Insets(10, 10, 10, 10));

         BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        addPane.setBackground(background);
    
        // Create a new scene for the add item window
        Scene addItemScene = new Scene(addPane, 350, 350);
        stage.setScene(addItemScene);
        
        
    }
   
   private void editItems(Stage stage){
       Label edit = new Label ("Edit Product");
       Label id = new Label("Choose id:");
       Label price = new Label("Edit Price");
       edit.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       id.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       price.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
       Button editprod = new Button("edit");
       Button back = new Button("Go Back");
       TextField priceedit = new TextField();
       TextField idedit = new TextField();

       editprod.setOnAction(e->{
           try {
               a.loadFromFile("Products.bin");
               a.editp(priceedit,idedit);
           } catch (IOException ex) {
              ex.getStackTrace();
           }
           
           
       });
       
       back.setOnAction(e->{
          stage.setScene(adminScene);
          stage.show();
       });
     
        GridPane addPane = new GridPane();
        addPane.setVgap(10);
        addPane.setHgap(10);
        addPane.add(edit, 0, 0);
        addPane.add(id, 0, 1);
        addPane.add(idedit, 1, 1);
        addPane.add(price, 0, 2);
        addPane.add(priceedit, 1, 2);
        addPane.add(editprod, 3, 3);
        addPane.add(back, 2, 3);
        
        addPane.setAlignment(Pos.CENTER);
        addPane.setPadding(new Insets(10, 10, 10, 10));
       
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:C:/Users/lenovo/Documents/NetBeansProjects/ClothingManagmentSystem/cloth.jpeg", 400, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
    );
        Background background = new Background(backgroundImage);
        addPane.setBackground(background);
    
        // Create a new scene for the add item window
        Scene editItemScene = new Scene(addPane, 350, 350);
        stage.setScene(editItemScene);
        
        
   }
     
}

