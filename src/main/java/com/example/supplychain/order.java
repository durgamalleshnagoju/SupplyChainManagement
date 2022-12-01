package com.example.supplychain;

public class order {
    public static boolean placeSingleOrder(Product product, String customerEmail){
        String query = String.format("INSERT INTO orders(quantity, customer_id, product_id, status) VALUES (1,(SELECT cid FROM customer WHERE email = '%s'), %s,'ORDERED')",
                customerEmail,product.getId());
        //System.out.println(query);
        databaseConnection dbconn = new databaseConnection();
        if(dbconn.insertData(query)>=1){
            return true;
        }
        return false;
    }
}
