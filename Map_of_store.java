package CSC_202_Project;
//On this class, we have different assumptions : 
//Place holder
// this will be the station id that the robot goes to
//        with the default # of station this should be in range [0-8]
// In the loop - if this value is not -1, that means that the
//        number that is currently being stored, is the id of a station
//        in which the robot COULD NOT place an item in, therefore the
//        robot should chose a DIFFENT number station
import java.io.*; 
public class Map_of_store {
	private Robot            robot;
	  private Station          stations[];
	  private Station          pickup;
	  private Station          unload;
	  private static final int NUM_STATIONS = 9; //should be 1+actual number
	                                             //  this way station 0 is included

	  public Map_of_store() {
	    stations = new Station[NUM_STATIONS]; //Create normal stations
	    pickup   = new Station();//create pickup/refrigeration stations
	    unload   = new Station();
	    robot    = new Robot(pickup); //Robot starts at pickup

	    for(int i=0; i<NUM_STATIONS; i++) {
	      stations[i] = new Station();
	    }
	    

	    //pickup, refrigeration container, and station 8, can hold infinite items
	    pickup.setLimit(-1);
	    unload.setLimit(-1);
	    stations[8].setLimit(-1);

	    //these methods set names to pickup and refrigeration container
	    pickup.setName("Pickup");
	    unload.setName("Refrigeration Container");
	    for(int i=0; i<NUM_STATIONS; i++) {
	      stations[i].setName(String.format("Station %02d", i));
	    }
	  }

	
      //will be looped in actual main
	  public boolean canStart() {
	    //Go to station if not there
	    if(robot.getStation() != pickup)
	      robot.moveToStation(pickup);

	    //will return false when no items left in pickup station
	    //true if the robot picked up an item
	    //Can't do much is robot does not have an item
	    return robot.getItem() != null || robot.pickItem();
	  }

	  public void start() {
	    int station_num = -1;

	    //just in case the caller of start had not already called this method
	    canStart();

	    do {
	      //get the atributes of the item that the robot is holding
	      int     item_id     = robot.getItem().getID();
	      int     item_msd    = robot.getItem().getMSD();
	      double  item_temp   = robot.getItem().getTemp();
	      double  item_mass   = robot.getItem().getMass();

	      if(item_temp <= 20.001) {   //Cold items
	        if(station_num == -1) {
	          station_num = 5; //cold items go in station 5
	        }
	        else {
	          station_num = -3;   //if station 5 is full
	          unload(5, -2); //perform emergency dump
	        }
	      }
	      else if(item_mass <= 50) {  //light items
	        if(station_num == -1) {
	          station_num = 7; //light items go in station 7
	        }
	        else {
	          station_num = -3;   //if station 7 is full
	          unload(7, 8); //perform emergency dump
	        }
	      }
	      else {                      //All other items
	        if(station_num == -1) {   //  go into the same station as their MSD
	          station_num = item_msd%2;
	        }
	        else {                   //If that station is full, they go into
	          station_num += 2;      //  the next station(perserving even/odd-ness)
	        }
	        if(station_num >= 8) {//If all even/odd stations are full
	          for(int i=item_msd%2; i<8; i++) {//perform emergency dump
	            unload(i, 8);
	          }
	          station_num = -3;
	        }
	      }

	      //Go to station
	      if(station_num != -3)
	        robot.moveToStation(stations[station_num]);
	    } while(!robot.putItem() && station_num != -3);

	    //Go the the pickup station, this is the robot's 'base'
	    //  this way, at the end of the day the robot is in his 'base'
	    robot.moveToStation(pickup);
	  }

	  //Adds item to pickup 'station'
	  public void addItem(Item item) {
	    if(!pickup.addItem(item))
	      throw new RuntimeException("pickup does not want to accept more items");
	  }

	  //Sends unload message to robot
	  public void unload() {
	    unload(5, -2);//default unload station is 5
	  }

	  public void unload(int station) {
	    unload(station, -2);//default unload station is 5
	  }

	  public void unload(int from, int to) {
	    if(from < -2 || from >= stations.length
	      || to < -2 || to >= stations.length)
	        throw new ArrayIndexOutOfBoundsException(
	          String.format("invalid stations: (%d, %d) should be in range [-2, %d]",
	            from,
	            to,
	            stations.length-1));
	    if(from == to)
	      throw new RuntimeException("cannot unload to and from the same station");

	    System.out.println("Unloading");
	    if(robot.getItem() == null) {
	      robot.moveToStation(stations[from]);
	      robot.pickItem();
	    }
	    while(robot.getItem() != null) {
	      //move to unload station
	      if(to == -2) // be it refrigeration container
	        robot.moveToStation(unload);
	      else if(to == -1) // pickup
	        robot.moveToStation(pickup);
	      else // or just some other station
	        robot.moveToStation(stations[to]);

	      //unload the item
	      if(!robot.putItem())
	        throw new RuntimeException("robot could not unload item");

	      //move to station to unload(pick) from
	      if(from == -2) // be it refrigeration container
	        robot.moveToStation(unload);
	      else if(from == -1) // pickup
	        robot.moveToStation(pickup);
	      else // or just some other station
	        robot.moveToStation(stations[from]);

	      //Pick up an item from said station
	      robot.pickItem();
	    }
	    System.out.println("Done Unloading");
	 
	  }
}
//END OF CLASS
