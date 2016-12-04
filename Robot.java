package CSC_202_Project;

public class Robot {
	//Creates an Item and Station object for the robot
	  private Item item;
	  private Station  currentStation;

	  //Default constructor, sets objects to null
	  public Robot() {
	    this.item           = null;
	    this.currentStation = null;
	  }

	  //Alternate constructor, sets currentStation to the input Station
	  public Robot(Station station) {
	    item           = null;
	    currentStation = station;
	  }

	  //Moves the robot to the Station from the argument
	  public void moveToStation(Station station) {
	    int current_num;
	    int end_num;
	    if(currentStation.getName().equalsIgnoreCase("Refrigeration Container"))
	      current_num = -2;
	    else if(currentStation.getName().equalsIgnoreCase("Pickup"))
	      current_num = -1;
	    else {
	      String num = currentStation.getName().replaceAll("[^0-9]", "");
	      current_num = Integer.valueOf(num).intValue();
	    }

	    if(station.getName().equalsIgnoreCase("Refrigeration Container"))
	      end_num = -2;
	    else if(station.getName().equalsIgnoreCase("Pickup"))
	      end_num = -1;
	    else {
	      String num = station.getName().replaceAll("[^0-9]", "");
	      end_num = Integer.valueOf(num).intValue();
	    }

	    if(current_num%2==0 && current_num >= 0 && current_num != 8)
	      System.out.println("Robot moving Left");
	    else if((current_num%2==1 && current_num > 0) || current_num == 8)
	      System.out.println("Robot moving Right");
	    else if(current_num == -2) {
	      System.out.println("Robot moving Backward");
	      current_num = 6;
	    }
	    else if(current_num == -1)
	      System.out.println("Robot moving Forward");

	    if(current_num%2 == 1)
	      current_num -= 1;
	    if(current_num == 8)
	      current_num = -1;

	    int end_num_b = end_num;
	    if(end_num_b == 8)
	      end_num_b = -1;
	    if(end_num_b == -2)
	      end_num_b = 6;
	    end_num_b = (end_num_b%2==0 && end_num_b>0 )? end_num_b : end_num_b-1;

	    while(current_num > end_num_b) {
	      System.out.println("Robot moving Backward");
	      current_num--;
	    }
	    while(current_num < end_num_b) {
	      System.out.println("Robot moving Forward");
	      current_num++;
	    }

	    if(end_num%2==0 && end_num >= 0 && end_num != 8)
	      System.out.println("Robot moving Right");
	    else if((end_num%2==1 && end_num > 0) || end_num == 8)
	      System.out.println("Robot moving Left");
	    else if(end_num == -2) {
	      System.out.println("Robot moving Forward");
	    }
	    else if(end_num == -1)
	      System.out.println("Robot moving Backward");

	    currentStation = station;
	  }

	  //Calls the getItem method of Station to pick up an item from the station
	  public boolean pickItem() {
	    if(currentStation == null || this.item != null) {
	      return false;
	    }
	    item = currentStation.getItem();
	    if(item == null)
	      System.out.printf("Unable to retrieve item from %s\n",
	        currentStation.getName());
	    else
	      System.out.printf("Retrieved item from %s: {id:%s}\n",
	        currentStation.getName(),
	        item.getStrID());
	    return item != null;
	  }

	  //Calls the addItem method of Station and sets the robot's item to null
	  public boolean putItem() {
	    if(currentStation == null || this.item == null) {
	      System.out.printf("%s: unable to palce item\n",
	        currentStation.getName());
	      return false;
	    }
	    if(currentStation.maxedOut()) {
	      System.out.printf("%s: unable to palce item\n",
	        currentStation.getName());
	      return false;
	    }
	    if(currentStation.addItem(this.item)) {
	      System.out.printf("%s: placed item: {id:%s}\n",
	        currentStation.getName(),
	        item.getStrID());
	      item = null;
	      return true;
	    }
	    System.out.printf("%s: unable to palce item\n",
	    currentStation.getName());
	    return false;
	  }

	  //Returns the robot's current Item
	  public Item getItem() {
	    return item;
	  }

	  //Returns the robot's current Station
	  public Station getStation() {
	    return currentStation;
	  }
	}//END OF CLASS