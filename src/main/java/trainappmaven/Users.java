/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainappmaven;

/**
 *
 * @author scott
 */
public class Users {
      public String userName;
    public String passWord;
    public String id;
    public Users(String un, String pw, String id1){
     userName=un;
     passWord=pw;
     id=id1;
    }
    
    public String toString(){
     String s="user element: un="+userName+",password: "+passWord+", id="+id+"\n";
     return s;
    }
}
