/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainappmaven;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author scott
 */
public class trainAppMaven {
    final int RANDMAX = 30000;
    final String DATABASEURL="jdbc:ucanaccess://tripDB.accdb";
     public userList ul=new userList();
     public trainInfoList til=new trainInfoList();
    /**
     * @param args the command line arguments
     */
    
    //register
    public boolean register(String userName,String password){
     //if username and password are not in system, generate an MD5 hash for id, insert it
     if(ul.hasUserName(userName) || ul.hasPassword(password)) {
      System.out.println("user is already in the system.");
      return false;
     }
     else{
         try{
             long rawInt=(long)(Math.random()*RANDMAX+Calendar.MILLISECOND);
      String raw=""+rawInt; //random
      MessageDigest md = MessageDigest.getInstance("MD5");
       md.update(raw.getBytes());  
        byte[] digest = md.digest();
       BigInteger bi= new BigInteger(1,digest);
        String ID = bi.toString(16);
            while (ID.length() < 32) {
                ID = "0" + ID;
            }
            System.out.println("in register, ID="+ID);
      Users u=new Users(userName,password, ID);
      ul.newUser(u);
      addUserToDb(u);
         }catch(Exception e){
         System.out.println("Exception in hashing user id.");
        }
         }
     System.out.println("User "+userName+" has been registered.");
     return true;
    }    
    
    //login
    public String login(String userName, String passWord){
     if(ul.hasUserName(userName) && ul.hasPassword(passWord)) {
      System.out.println("user "+userName+" has been found. logging in...");
      Users user=ul.findUserName(userName);
      return user.id;
     }
     else {
      System.out.println("could not find user with name of: "+userName+", and password: "+passWord);
      return null;
     }
    }
    
    //insert a train trip
    public String insertTrip(String userId,String trainName, int trainNum, String date){
     String ID=""+til.tripId;
     trainInfo tf = new trainInfo(userId,false,trainNum,trainName,date,ID);
     til.newTrip(tf);
     addTripToDb(tf);
     System.out.println("trip with ID: "+ID+" added.");
     return ID;
    }
    
    //print all elements of til
    public void printAllTrains(){
     Iterator<trainInfo> it=til.trains.iterator();
     System.out.println("printing trains: ");
     while(it.hasNext()){
      trainInfo ti=it.next();
      System.out.println(ti.toString());
     }
    }
    
    //print all users
    public void printAllUSers(){
     Iterator<Users> it=ul.users.iterator();
     System.out.println("printing users: ");
     while(it.hasNext()){
      Users u=it.next();
      System.out.println(u.toString());
     }
    }
    
    //get trains for a given user id
    public ArrayList<trainInfo> getTrainsForUserId(String uid){
     Iterator<trainInfo> iter = til.trains.iterator();
     ArrayList<trainInfo> list = new ArrayList<trainInfo>();
     while(iter.hasNext()){
      trainInfo t=iter.next();
      if(t.userId.equals(uid)) list.add(t);
     }
     
     return list;
    }
    
    //retrieve password for a given username
    public String getPassWord(String un){
     Iterator<Users> iter = ul.users.iterator();
     while(iter.hasNext()){
      Users u=iter.next();
      if(u.userName.equals(un)) return u.passWord;
     }
     
     return null;
    }
    
    //get trip id for a given index and user id
    public String getTripId(String ui, int index){
     Iterator<trainInfo> iter = til.trains.iterator();
     int count=0;
     while(iter.hasNext()){
      trainInfo u=iter.next();
      if(u.userId.equals(ui)) {
          if(count==index){
           return u.id;
          }
          count++;
      }
     }
     
     return null;
    }
    
    //---------------------------database stuff-------------------------------
    public void addUserToDb(Users u){
     String databaseURL = DATABASEURL;
         
          try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
             
            String sql = "INSERT INTO userList (userName, userId, passWord) VALUES (?, ?, ?)";
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, u.userName);
            preparedStatement.setString(2, u.id);
            preparedStatement.setString(3, u.passWord);
             
            int row = preparedStatement.executeUpdate();
             
            if (row > 0) {
                System.out.println("A row has been inserted successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addTripToDb(trainInfo tu){
    String databaseURL = DATABASEURL;
         
          try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
             
            String sql = "INSERT INTO tripList (userId,cancelled, trainNum,trainName,dateNum,tripId) VALUES (?, ?, ?,?,?,?)";
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tu.userId);
            preparedStatement.setBoolean(2, tu.cancelled);
            preparedStatement.setInt(3, tu.trainNum);
            preparedStatement.setString(4, tu.trainName);
            preparedStatement.setString(5, tu.date);
            preparedStatement.setString(6, tu.id);
             
            int row = preparedStatement.executeUpdate();
             
            if (row > 0) {
                System.out.println("A row has been inserted successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteTripFromDb(trainInfo tu){
      String databaseURL = DATABASEURL;
      String id=tu.id;
         
          try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
             
            String sql = "DELETE * FROM tripList WHERE id="+id;
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int row = preparedStatement.executeUpdate();
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void readFromDb(){
        
        //clear lists
        ul.users.clear();
        til.trains.clear();
        
    // TODO code application logic here
         String databaseURL = DATABASEURL;
         
          try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
            String sql = "SELECT * FROM userList";
             
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                String un=result.getString("userName");
                String pw=result.getString("passWord");
                String id=result.getString("userId");
                Users u1=new Users(un,pw,id);
                ul.users.add(u1);
            }
            
            //trips read
            sql = "SELECT * FROM tripList";
             
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
             
            while (result.next()) {
                String uid=result.getString("userId");
                int cancelledInt =result.getInt("cancelled");
                boolean cancelled=false;
                if(cancelledInt>0)cancelled=true;
                else cancelled=false;
                int trainNum=result.getInt("trainNum");
                String trainName=result.getString("trainName");
                String date=result.getString("dateNum");
                String id=result.getString("tripId");
                trainInfo tInfo=new trainInfo(uid,cancelled,trainNum,trainName,date,id);
                til.newTrip(tInfo);
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
