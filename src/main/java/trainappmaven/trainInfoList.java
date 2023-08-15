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
public class trainInfoList {
    public ArrayList<trainInfo> trains=new ArrayList<trainInfo>();
    public long tripId=0;
    
 public trainInfo readTrip(String id){
  Iterator<trainInfo> iter=trains.iterator();
  while(iter.hasNext()){
   trainInfo u=iter.next();
   if(u.id==id) return u;
  }
  
  return null;
 } //read
 
 //create
 public void newTrip(trainInfo u){
  trains.add(u);
  tripId++;
 }
         
 //update User
 public void updateTrip(trainInfo u){
    String id=u.userId;
    boolean cancelled=u.cancelled;
    int trainNum=u.trainNum;
    String trainName=u.trainName;
    String date=u.date;
    String tid=u.id;
  trainInfo u1=readTrip(tid);
  trains.remove(u1);
  trains.add(u);
 }
 
 //delete user
 public void deleteTrip(String id){
  trainInfo u=readTrip(id);
  trains.remove(u);
 }
}
