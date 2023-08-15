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
import trainappmaven.trainAppMaven;
import trainappmaven.trainInfo;
/**
 *
 * @author scott
 */
public class mainTest {
    final String TESTVECTNAME = "mainTestVectors.txt";
    trainAppMaven tam=new trainAppMaven();
    public mainTest() {
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
    public void testAll(){
        try{
     File input = new File(TESTVECTNAME);
     Scanner reader = new Scanner(input);
     while(reader.hasNextLine()){
      String line=reader.nextLine();
      String [] tokens=line.split(" ");
      String un = tokens[0];
      String pw = tokens[1];
      String trainNum = tokens[2];
      String trainName = tokens[3];
      String date = tokens[4];
      
      //register a user
     assertTrue(tam.register(un,pw));
     tam.printAllUSers();
      //login a user
      String id=tam.login(un,pw);
      System.out.println("id of user is: "+id);
      assertNotNull(id);
      
      //add a train
      int trainNumInt = Integer.parseInt(trainNum);
      String tripId=tam.insertTrip(id, trainName, trainNumInt, date);
      trainInfo ti=tam.til.readTrip(tripId);
      assertNotNull(ti);
      tam.printAllTrains();
     }
        }catch(FileNotFoundException fe){
         System.out.println("could not open main file for testing.");
         return;
        }
    }
}
