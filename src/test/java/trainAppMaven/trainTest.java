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
import trainappmaven.trainInfoList;
import trainappmaven.trainInfo;
/**
 *
 * @author scott
 */
public class trainTest {
   final String INPUTVECTNAME="testTrainVectors.txt";
    public trainTest() {
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
        String [] infos= new String[6];
        infos=reader.nextLine().split(" ");
        
        //parse string into values
        String uid=infos[0];
        boolean cancelled=false;
        if(infos[1]=="1") cancelled=true;
        int trainNum=Integer.parseInt(infos[2]);
        String trainName=infos[3];
        String date=infos[4];
        String id=infos[5];
        
        trainInfo u=new trainInfo(uid,cancelled,trainNum,trainName,date,id);
        
        //test new user and read user
        testNewTrain(u);
        
        //test delete user
        testDeleteTrain(u);
        
        //test update user
        
       }
       }catch(FileNotFoundException E){
        System.out.println("file not found for testing trains.");
        return;
    }
    }
    
      public void testNewTrain(trainInfo u) {
        System.out.println("newUser");
        trainInfoList instance = new trainInfoList();
        instance.newTrip(u);
        // TODO review the generated test code and remove the default call to fail.
        assertNotEquals(instance.readTrip(u.id),null);
        if(instance.readTrip(u.id)!=null) System.out.println("passed testing new user.");
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
       
       public void testDeleteTrain(trainInfo u) {
        System.out.println("deleteUser");
        String id = u.id;
        trainInfoList instance = new trainInfoList();
        instance.newTrip(u);
        instance.deleteTrip(id);
        // TODO review the generated test code and remove the default call to fail.
        if(instance.readTrip(id)==null) System.out.println("user test for delete passed.");
        else System.out.println("user test for delete failed.");
    } 
   
}
