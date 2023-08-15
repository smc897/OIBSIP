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
public class trainInfo {
    public String userId;
    public boolean cancelled;
    public int trainNum;
    public String trainName;
    public String date;
    public String id;
    public trainInfo(String uid, boolean cancelled1, int trainNum1,String trainName1,String date1,String id1){
     userId=uid;
     cancelled=cancelled1;
     trainNum=trainNum1;
     trainName=trainName1;
     date=date1;
     id=id1;
    }
    
    //toString
    public String toString(){
        String canc="";
        if(cancelled) canc="true";
        else canc="false";
     String s="trainInfo element: "+userId+","+canc+","+trainNum+","+trainName+","+date+","+id+"\n";
     return s;
    }
}
