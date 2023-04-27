package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Download{
    String search_name,search_id;
    Dbcon db=Dbcon.getInstance();
    @FXML
    private TextField name;
    @FXML
    private TextField code;
    @FXML
    private Button search_btn;
    @FXML
    protected void search(){
        search_name=name.getText().toLowerCase();
        search_id=code.getText().toLowerCase();
        System.out.println("searching for: "+search_name+","+search_id);
        db.read(search_name,search_id);
    }
    @FXML
    protected void back_home(ActionEvent event) throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,600,500);
        stage.setTitle("UIU Content Bank");
        stage.setScene(scene);
        stage.show();
    }
}
