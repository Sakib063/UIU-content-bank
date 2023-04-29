package com.example.demo;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
    public void download(String name,String code){
      int BUFFER_SIZE=10000;
      try{
          Statement statement=connection.createStatement();
          ResultSet resultSet=statement.executeQuery("SELECT filename,file FROM `content` WHERE course='"+name+"' AND course_code='"+code+"'");
          while(resultSet.next()){
              String file=resultSet.getString(1);
              String path="C:/Users/Public/Downloads/"+file;
              System.out.println(path);
              Blob blob=resultSet.getBlob("file");
              InputStream inputStream=blob.getBinaryStream();
              OutputStream outputStream=new FileOutputStream(path);
              int bytesRead=-1;
              byte[] buffer=new byte[BUFFER_SIZE];
              while((bytesRead=inputStream.read(buffer))!=-1){
                  outputStream.write(buffer,0,bytesRead);
              }
              inputStream.close();
              outputStream.close();
              System.out.println("File saved");
          }
      }
      catch(Exception e){
          throw new RuntimeException(e);
      }
    }
    public ResultSet fetch(String name,String code){
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT content_type,filename FROM `content` WHERE course='"+name+"' AND course_code='"+code+"'");
//            while(resultSet.next()){
//                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
//            }
            return resultSet;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void upload(String[] str1,String url,String course,String code,String type,String filename){
       try{
           temp = str1[0];
           ttemp=temp.split(" ");
           iid=ttemp[0];
           dept=ttemp[2];
           System.out.println(Arrays.toString(str1));
           Statement statement=connection.createStatement();
           statement.executeUpdate("INSERT INTO `content`(`course`,`course_code`,`content_type`,`file`,`dept`,`uid`,`filename`)VALUES('"+course+"','"+code+"','"+type+"',LOAD_FILE('"+url+"'),'"+dept+"','"+iid+"','"+filename+"')");
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
      System.out.println(Arrays.toString(str));
      return str;
    }
}