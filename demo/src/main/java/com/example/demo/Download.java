package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    private ListView dlist;
    @FXML
    private Label notice;
    @FXML
    protected void search() throws SQLException{
        search_name=name.getText().toLowerCase();
        search_id=code.getText().toLowerCase();
        ResultSet resultSet=db.fetch(search_name,search_id);
        while(resultSet.next()){
            dlist.getItems().add(resultSet.getString(1)+" "+resultSet.getString(2));
        }
    }

    @FXML
    protected void download(){
        search_name=name.getText().toLowerCase();
        search_id=code.getText().toLowerCase();
        db.download(search_name,search_id);
        notice.setText("File downloaded");
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
