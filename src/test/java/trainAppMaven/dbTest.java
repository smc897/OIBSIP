package trainAppMaven;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import trainappmaven.trainAppMaven;
import trainappmaven.Users;
import trainappmaven.trainInfo;

/**
 *
 * @author scott
 */
public class dbTest {
    final String TESTVECTNAME="dbVectors.txt";
    trainAppMaven tam=new trainAppMaven();
    public dbTest() {
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
    public void testDB(){
     int userCount,tripCount;
     String line="";
     userCount=0;
     tripCount=0;
     try{
      File input = new File(TESTVECTNAME);
      Scanner reader = new Scanner(input);
      
      //users add
      do{
       line=reader.nextLine();
       if(!line.substring(0,1).equals("//")){
        //process line
        String[] tokens=line.split(" ");
        String un=tokens[0];
        String pw=tokens[1];
        String uid=tokens[2];
        Users u=new Users(un,pw,uid);
        tam.addUserToDb(u);
        userCount++;
       }
      }while(line.length()>0);
      
      //trips add
      do{
       line=reader.nextLine();
       if(!line.substring(0,1).equals("//")){
        //process line
        String[] tokens=line.split(" ");
        String uid=tokens[0];
        String cancStr=tokens[1];
        String trainNumStr=tokens[2];
        String trainName=tokens[3];
        String date=tokens[4];
        String id=tokens[5];
        boolean cancelled=false;
        if(cancStr.equals("1")) cancelled=true;
        int trainNum=Integer.parseInt(trainNumStr);
        trainInfo ti=new trainInfo(uid,cancelled,trainNum,trainName,date,id);
        tam.addTripToDb(ti);
        tripCount++;
       }
      }while(line.length()>0);
      
      tam.readFromDb(); //read all
      assertEquals(tam.ul.users.size(),userCount);
      assertEquals(tam.til.trains.size(),tripCount);
      
      //delete trips
      do{
       line=reader.nextLine();
       String tripId=line;
       trainInfo tu=tam.til.readTrip(tripId);
       tam.deleteTripFromDb(tu);
       tripCount--;
      }while(reader.hasNextLine());
      
      tam.readFromDb();
       assertEquals(tam.til.trains.size(),tripCount);
       assertEquals(tam.ul.users.size(),userCount);
       
     }catch(Exception e){System.out.println("could not open db test file.");
     
     }
    }
}
