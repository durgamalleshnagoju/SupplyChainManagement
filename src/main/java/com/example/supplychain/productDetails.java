package com.example.supplychain;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class productDetails {

    public TableView<Product> productTable;

    public Pane getAllProducts(){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        final ObservableList<Product> data = FXCollections.observableArrayList();
        data.add(new Product(1,"Realme7Pro",20000));
        data.add(new Product(2,"Realme8Pro",21000));

        ObservableList<Product> items = Product.getAllProducts();

        productTable = new TableView<>();
        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id, name, price);
        productTable.setMinSize(supplyChain.width,supplyChain.height-200);

        Pane tablePane = new Pane();
        //tablePane.setMinSize(supplyChain.width,supplyChain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;

    }

    public GridPane getProductsByName(String searchName){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        ObservableList<Product> items = Product.getProductsByName(searchName);

        productTable = new TableView<>();
        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.setMinSize(supplyChain.width,supplyChain.height-200);

        productTable.getColumns().addAll(id, name, price);

        GridPane tablePane = new GridPane();
        tablePane.setAlignment(Pos.CENTER);
        tablePane.getChildren().add(productTable);

        return tablePane;

    }

    public Product getSelectedProduct(){
        if(productTable==null){
            //System.out.println("Table is Empty :(");
            return null;
        }
        if(productTable.getSelectionModel().getSelectedIndex()!=-1){
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
//            System.out.println("Id :"+selectedProduct.getId()+
//                            " Name : "+ selectedProduct.getName()+
//                            " Price : "+selectedProduct.getPrice()
//                            );
            return selectedProduct;
        }
        //System.out.println("Nothing is selected");
        return null;
    }

}
