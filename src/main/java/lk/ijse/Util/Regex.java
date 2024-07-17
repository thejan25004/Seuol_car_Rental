package lk.ijse.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

     public static boolean isTextFieldValid(TexetField texetField,String text){
         String filed = "";

         switch (texetField){
             case ID:
                 filed = "^([A-Z][0-9]{3})$";
                 break;
             case NAME:
                 filed = "^[A-z|\\\\s]{3,}$";
                 break;
             case CONTACT:
                 filed = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                 break;
             case EMAIL:
                 filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
         }

         Pattern pattern = Pattern.compile(filed);

         if (text != null){
             if (text.trim().isEmpty()){
                 return false;
             }
         }else {
             return false;
         }

         Matcher matcher = pattern.matcher(text);

         if (matcher.matches()){
             return true;
         }
         return false;
     }

    public static boolean setTextColor(TexetField location, javafx.scene.control.TextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-focus-color: #00ff00");
            return true;
        }else {
            textField.setStyle("-fx-border-color: red;-fx-border-radius:5;-fx-border-width: 3");
            return false;
        }
    }
}
