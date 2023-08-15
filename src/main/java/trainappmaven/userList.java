/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainappmaven;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author scott
 */
public class userList {
 public ArrayList<Users> users=new ArrayList<Users>();
 
 public Users readUser(String id){
  Iterator<Users> iter=users.iterator();
  while(iter.hasNext()){
   Users u=iter.next();
   if(u.id==id) return u;
  }
  
  return null;
 } //read
 
 //create
 public void newUser(Users u){
  users.add(u);
 }
         
 //update User
 public void updateUser(Users u){
  String id=u.id;
  String un=u.userName;
  String pw=u.passWord;
  Users u1=readUser(id);
  users.remove(u1);
  users.add(u);
 }
 
 //delete user
 public void deleteUser(String id){
  Users u=readUser(id);
  users.remove(u);
 }
 
 //check if a user with a given password exists
 public boolean hasPassword(String pw){
  Iterator<Users> iter=users.iterator();
  while(iter.hasNext()){
   Users u=iter.next();
   if(u.passWord==pw) return true;
  }
  
  return false;
 }
 
 //check if a user with a given username exists
 public boolean hasUserName(String un){
  Iterator<Users> iter=users.iterator();
  while(iter.hasNext()){
   Users u=iter.next();
   if(u.userName==un) return true;
  }
  
  return false;
 }
 
 //find a user with a given username
 public Users findUserName(String un){
  Iterator<Users> iter=users.iterator();
  while(iter.hasNext()){
   Users u=iter.next();
   if(u.userName==un) return u;
  }
  
  return null;
 }
 
}

