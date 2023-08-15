/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainAppMaven;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import trainappmaven.Users;
import trainappmaven.userList;
/**
 *
 * @author scott
 */
public class userTest {
    final String INPUTVECTNAME="testUserVectors.txt";
    public userTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testUserFuncs(){
      try{
       File myInput = new File(INPUTVECTNAME);
       Scanner reader = new Scanner(myInput);
       while(reader.hasNextLine()){
        //test all four functions
        String [] infos= new String[3];
        infos=reader.nextLine().split(" ");
        Users u=new Users(infos[0],infos[1],infos[2]);
        
        //test new user and read user
        testNewUser(u);
        
        //test delete user
        testDeleteUser(u);
        
        //test update user
        
       }
       }catch(FileNotFoundException E){
        System.out.println("file not found for testing.");
        return;
    }
    }
    
      public void testNewUser(Users u) {
        System.out.println("newUser");
        userList instance = new userList();
        instance.newUser(u);
        // TODO review the generated test code and remove the default call to fail.
        assertNotEquals(instance.readUser(u.id),null);
        if(instance.readUser(u.id)!=null) System.out.println("passed testing new user.");
        else System.out.println("new user test failed.");
    }
      
       public void testUpdateUser(Users u) {
        System.out.println("updateUser");
        userList instance = new userList();
        instance.newUser(u);
        Users u1=u;
        u1.userName+="_";
        instance.updateUser(u1);
        // TODO review the generated test code and remove the default call to fail.
        Users u2=instance.readUser(u1.id);
        if(u2.userName==u1.userName+"_") System.out.println("update users passed.");
        else System.out.println("update users failed.");
    }
       
       public void testDeleteUser(Users u) {
        System.out.println("deleteUser");
        String id = u.id;
        userList instance = new userList();
        instance.newUser(u);
        instance.deleteUser(id);
        // TODO review the generated test code and remove the default call to fail.
        if(instance.readUser(id)==null) System.out.println("user test for delete passed.");
        else System.out.println("user test for delete failed.");
    }
}
