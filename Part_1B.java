package CSC_202_Project;
/*Write a program to stimulate the operation of a simple robot. 
 * The robot moves forward, backwards, left and right. There are 10 stations plus the pick up station. 
 * Pick up station in is the initial start where the robot picks up the items.
 * 8 items can be placed on each station.The items are marked with identification or serial numbers.
 * The items with the even numbers go to the left and the odd numbers goes to the right.
 * The last slot, number 7 is reserved for items which are less than 50kg. 
 * MSB digit 5 indicates that the product must be placed in the fifth station which is keeping the product at 22 degrees F. 
 * There are two other station marked as 12 and 14 which each consists of 4 slots. 
 * The station 12 is designated for items which are for immediate delivery and station 14 is for items that require 5 days storage. 
 * Station 9 and 10 are used for special delivery.
 * */
//PROBLEM STATEMENT
//the robot should:
	  //  1) go to the pickup station if it is not already there
	  //  2) the robot should pick up items
	  //  3) after picking up items, it should figure out what station to go to
	  //  4) the robot will then procede to the correct station
	  //  5) the robot will then place the items at the station
	//  6) finally, the robot will return to pickup station
	  //     - if an item was not placed, the robot will pick a new station and repeat
import java.io.*; 
public class Part_1B {
	

	 public static void main(String[] args) throws IOException {
		    //Creates a map in which the robot and the stations are located
		 Map_of_store storeRoom = new Map_of_store();
		    //Creates an input reader object
		    BufferedReader input
		            = new BufferedReader(new InputStreamReader(System.in));
		    String input_line;

		    while((input_line = input.readLine()).length() > 0) {
		      Item item = new Item();
		      String itemdata[] = input_line.split("\\s+");
		      item.setID(Integer.valueOf(itemdata[0]).intValue());
		      item.setMass(Integer.valueOf(itemdata[1]).intValue());
		      item.setTemp(Integer.valueOf(itemdata[2]).intValue());
		      storeRoom.addItem(item);
		    }

		    while(storeRoom.canStart()) {
		      System.out.print("\nHow many items until unload: ");
		      String s1 = input.readLine();
		      int numItems = Integer.valueOf(s1).intValue();
		      for(int i=0; i<numItems; i++){
		        if(storeRoom.canStart())
		          storeRoom.start();
		        else
		          break;
		      }
		      System.out.print("\nUnload now [y/n]:");
		      s1 = input.readLine();
		      if(s1.matches("(?i)y.*"))
		        storeRoom.unload();
		    }//Closes stream
		    input.close();
		    System.exit(0);
		  }	//End of main
		}//End of class