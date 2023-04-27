package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PageTwo{
    Dbcon db=Dbcon.getInstance();
    @FXML
    String url,temp,iid,dept,ttype,file_name;
    String[] str,ttemp;
    @FXML
    private Button select;
    @FXML
    private Button select_multi;
    @FXML
    private ListView list;
    @FXML
    private TextField cname;
    @FXML
    private TextField ccid;
    @FXML
    private TextField oi;
    @FXML
    private RadioButton books,pdf,questions,others;
    @FXML
    protected void select_file(ActionEvent e){
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(null);
        if(file!=null){
            file_name=file.getName();
            list.getItems().add(file_name);
            url=file.getAbsolutePath();
        }
        else{
            System.out.println("error file select");
        }
    }
    public void get_type(ActionEvent event){
        if(books.isSelected()){
            ttype="books";
        }
        else if(pdf.isSelected()){
            ttype="pdf";
        }
        else if(questions.isSelected()){
            ttype="questions";
        }
        else if(others.isSelected()){
            ttype=oi.getText();
        }
        System.out.println(ttype);
    }
    @FXML
    protected void upload(ActionEvent e){
        str=db.info();
        url=url.replace("\\","/");
        System.out.println(url);
        db.upload(str,url,cname.getText().toLowerCase(),ccid.getText().toLowerCase(),ttype,file_name);
        url=null;
        ttype=null;
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
