package CSC_202_Project;

public class Station {

	//Creates an ItemList, an integer limit, and a String name for the object
	  private ItemList  items;
	  private int       limit;
	  private String    name;

	  //Constructor, sets default values for class variables
	  public Station() {
	    items = new ItemList();
	    limit = 8;
	    name  = "";
	  }

	  //Adds the input Item to the station if there is room
	  public boolean addItem(Item item) {
	    if(!maxedOut()) {
	      items.append(item);
	      return true;
	    }
	    return false;
	  }

	  //Returns and removes the item at the top of the ItemList
	  public Item getItem() {
	    if(items.size() == 0) {
	      return null;
	    }
	    return items.pop();
	  }

	  //Returns true if there is no more space in the station
	  public boolean maxedOut() {
	    return items.size() >= limit && limit != -1;
	  }
	  

	  //Sets the limit for the stations capacity
	  public void setLimit(int limit) {
	    limit = limit;
	  }

	  //Returns the current capacity limit
	  public int getLimit() {
	    return limit;
	  }

	  //Sets the name of the station
	  public void setName(String name) {
	    name = name;
	  }

	  //Returns the name of the station
	  public String getName() {
	    return name;
}
}
//END OF CLASS