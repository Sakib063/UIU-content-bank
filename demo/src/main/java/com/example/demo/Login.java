package com.example.demo;

import com.mysql.cj.util.TestUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.EventObject;

public class Login{
    private String[] str=new String[2];
    private String[] ttemp;
    protected static String name,iid,dept,designation,temp;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label warning;
    @FXML
    private TextField id;
    @FXML
    private TextField pass;
    @FXML
    protected void login(ActionEvent event) throws Exception{
        Dbcon db=Dbcon.getInstance();
        str=db.log(id.getText(),pass.getText());

        if(str!=null){
            temp=str[0];
            ttemp=temp.split(" ");
            iid=ttemp[0];
            designation=ttemp[1];
            dept=ttemp[2];
            name=str[1];
            FXMLLoader loader=new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root=loader.load();
            HelloController controller=loader.getController();
            controller.set_credentials(name,iid);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root,600,500);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();
        }
        else{
            warning.setText("Invalid Credentials!");
        }
    }
}
