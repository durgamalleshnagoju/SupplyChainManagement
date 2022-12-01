package com.example.supplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class supplyChain extends Application {

    public static final int height = 600;
    public static final int width = 700;
    public static final int upperLine = 50;

    Pane bodyPane = new Pane();
    login Login = new login();
    productDetails productDetails= new productDetails();

    public boolean loggedInn = false;

    Label loginLabel;
    Button orderButton;
    Label statusLabel = new Label();
    Button exitButton = new Button(" EXIT ");

    BackgroundFill background_fill = new BackgroundFill(Color.PINK,
            CornerRadii.EMPTY, Insets.EMPTY);

    // create Background
    Background background = new Background(background_fill);

    private GridPane footerBar(){
        GridPane gridPane = new GridPane();
        orderButton = new Button("BUY NOW");
        //Label statusLabel = new Label();
        gridPane.add(statusLabel,0,0);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //productDetails.getSelectedProduct();
                if(!loggedInn){
                    statusLabel.setText("Please Login");
//                    bodyPane.getChildren().clear();
//                    bodyPane.getChildren().add(loginPage());
                }
                else{
                    Product product = productDetails.getSelectedProduct();
                    if(product!=null){
                        String email = loginLabel.getText();
                        email = email.substring(10);
                        //System.out.println(email);
                        if(order.placeSingleOrder(product,email)){
                            statusLabel.setText("Order Placed!"+" "+product.getName());
                            //System.out.println("order placed");
                            //System.out.println(product.getName());
                        }
                        else{
                            statusLabel.setText("Order failed :(");
                            //System.out.println("order failed");
                        }
                    }else{
                        statusLabel.setText("Please select products!");
                        //System.out.println("Please select products!");
                    }
                }
            }
        });


        //orderButton.setMinSize(50,50);
        //orderButton.setMinHeight(50);
        gridPane.add(orderButton,0,1);
        gridPane.add(exitButton,2,1);
        gridPane.setHgap(10);
        gridPane.setVgap(15);
        gridPane.setMinWidth(width);
        gridPane.setTranslateY(height-50);
        gridPane.setAlignment(Pos.CENTER);

        return  gridPane;
    }

    private GridPane headerBar(){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(width,upperLine-5);

        loginLabel = new Label(" Please Login!");

        TextField searchText = new TextField();
        searchText.setMinWidth(250);
        searchText.setPromptText("Please Search Here");


        Button loginButton = new Button("Login");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!loggedInn){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                    //loginButton.setText("Logout");
                }
            }
        });

        Button searchButton = new Button("Search");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                //bodyPane.getChildren().add(productDetails.getAllProducts());
                String search = searchText.getText();
                bodyPane.getChildren().add(productDetails.getProductsByName(search));

            }
        });

        gridPane.add(searchText,0,0);
        gridPane.add(searchButton, 1,0);
        gridPane.add(loginLabel, 2,0);
        gridPane.add(loginButton,3,0);

        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        return gridPane;
    }



    private GridPane loginPage(){

        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label messageLabel = new Label();

        TextField emailField = new TextField();
        emailField.setPromptText("Please Enter Email ID");
        PasswordField passwordfield = new PasswordField();
        passwordfield.setPromptText("Please Enter Password");

        Button loginButton = new Button("Login");

        loginButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {

                String email = emailField.getText();
                String password = passwordfield.getText();
                if(Login.customerLogin(email,password)){
                    loginLabel.setText("Welcome : " + email);
                    messageLabel.setText("Login Successful");
                    statusLabel.setText("Place an order!");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());

                    loggedInn = true;

                }else{
                    messageLabel.setText("Invalid User");
                }


            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());

        gridPane.add(emailLabel,0,0);
        gridPane.add(emailField,1,0);

        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordfield,1,1);
        gridPane.add(messageLabel,1,2);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(loginButton,0,2);

        return gridPane;

    }
    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+upperLine);
        //bodyPane.setBackground(background);
        bodyPane.setTranslateY(upperLine);
        bodyPane.setMinSize(width,height);

        bodyPane.getChildren().add(productDetails.getAllProducts());
        root.getChildren().addAll(
                headerBar(),
                bodyPane,
                footerBar()
        );
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}