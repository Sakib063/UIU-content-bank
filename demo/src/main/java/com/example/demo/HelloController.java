package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController{
    Dbcon db=Dbcon.getInstance();
    String[] str;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label namelabel;
    @FXML
    private Label idlabel;


    @FXML
    protected void search_course(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("pagetwo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,600,500);
        stage.setTitle("Upload Course Contents");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void download_course(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("download.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,600,500);
        stage.setTitle("Download Course Contents");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void exit(){
        db.closeConnection();
        Platform.exit();
    }
    @FXML
    protected void set_credentials(String name,String id){
        System.out.println("Name label set: "+name+" ID label set: "+id);
        namelabel.setText(name.toUpperCase());
        idlabel.setText(id);
    }
}