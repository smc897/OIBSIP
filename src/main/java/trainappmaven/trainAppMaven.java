/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainappmaven;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author scott
 */
public class trainAppMaven {
    final int RANDMAX = 30000;
     public userList ul=new userList();
     public trainInfoList til=new trainInfoList();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    //register
    public boolean register(String userName,String password){
     //if username and password are not in system, generate an MD5 hash for id, insert it
     if(ul.hasUserName(userName) || ul.hasPassword(password)) {
      System.out.println("user is already in the system.");
      return false;
     }
     else{
         try{
             long rawInt=(int)(Math.random()*RANDMAX+Calendar.MILLISECOND);
      String raw=""+rawInt; //random
      MessageDigest md = MessageDigest.getInstance("MD5");
       md.update(password.getBytes());  
        byte[] digest = md.digest();
       BigInteger bi= new BigInteger(1,digest);
        String ID = bi.toString(16);
            while (ID.length() < 32) {
                ID = "0" + ID;
            }
            System.out.println("in register, ID="+ID);
      Users u=new Users(userName,password, ID);
      ul.newUser(u);
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
    //print all elements of til
    public void printAllUSers(){
     Iterator<Users> it=ul.users.iterator();
     System.out.println("printing users: ");
     while(it.hasNext()){
      Users u=it.next();
      System.out.println(u.toString());
     }
    }
}
