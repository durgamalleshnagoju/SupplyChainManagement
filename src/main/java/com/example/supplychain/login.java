package com.example.supplychain;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class login {
    databaseConnection dbconn = new databaseConnection();

    public static byte[] getSHA(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    String getEncryptedPassword (String password){

        String encryptedPassword = "";
        BigInteger number = new BigInteger(1,getSHA(password));
        StringBuilder hexString = new StringBuilder(number.toString(16));
        encryptedPassword = hexString.toString();

        return encryptedPassword;
    }

    public boolean customerLogin(String email, String password){
        try{
            String query = String.format("SELECT * FROM customer WHERE email = '%s' and password = '%s'",email,getEncryptedPassword(password));
            ResultSet rs = dbconn.getQueryTable(query);
            if(rs.next()){
                return true;
            }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


//    public static void main(String[] args) {
//        login log = new login();
//        System.out.println(log.getEncryptedPassword("abc@7013"));
//
//    }
}
