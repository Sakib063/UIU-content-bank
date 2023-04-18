package com.example.demo;
import java.sql.*;
import java.util.Arrays;

public class Dbcon{
    private String[] ttemp;
    private String[] str=new String[2];
    private String temp,iid,dept;
  private Connection connection;
  private static Dbcon dbcon;

  public static Dbcon getInstance(){
      if(dbcon==null){
          dbcon= new Dbcon();
          System.out.println("Connection successful");
      }
      return dbcon;
  }
    private Dbcon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uiu_content_storage","admin","admin");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void closeConnection(){
        try{
            connection.close();
            System.out.println("Connection closed");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        dbcon=null;
    }
    public void read(){
      try{
          Statement statement=connection.createStatement();
          ResultSet resultSet=statement.executeQuery("SELECT * FROM `content`");
          while(resultSet.next()){
              System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5));
          }
      }
      catch(Exception e){
          throw new RuntimeException(e);
      }
    }
    public void write(String s){
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `content` WHERE name LIKE '"+s+"'");
            while(resultSet.next()){
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5));
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void upload(String[] str1,String url,String course,String code,String type){
       try{
           temp = str1[0];
           ttemp=temp.split(" ");
           iid=ttemp[0];
           dept=ttemp[2];
           System.out.println(Arrays.toString(str1));
           Statement statement=connection.createStatement();
           statement.executeUpdate("INSERT INTO `content`(`course`,`course_code`,`content_type`,`file`,`dept`,`id`)VALUES('"+course+"','"+code+"','"+type+"',LOAD_FILE('"+url+"'),'"+dept+"','"+iid+"')");
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public String[] log(String id,String pass){

      try{
          Statement statement=connection.createStatement();
          ResultSet resultSet=statement.executeQuery("SELECT * FROM `users` WHERE id='"+id+"' AND password='"+pass+"'");
          while(resultSet.next()){
              str[0]=resultSet.getString(1)+" "+resultSet.getString(3)+" "+resultSet.getString(4);
              str[1]=resultSet.getString(2);
          }
      }
      catch(Exception e){
          e.printStackTrace();
      }
      if(str[0]==null){
          System.out.println(str);
          return null;
      }
      else{
          return str;
      }
    }
    public String[] info(){
      return str;
    }
}