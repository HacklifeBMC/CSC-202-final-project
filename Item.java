package CSC_202_Project;

public class Item {
	
	//This object will be part of a single linked list
	private Item   next; 
	  private int    id;
	  private double temp;
	  private double mass;

	  public Item() {
	    next = null;
	    id   = 0;
	    temp = 0;
	    mass = 0;
	  }

	  public Item(int id) {
	    next = null;
	    id = id;
	    temp = 0;
	    mass = 0;
	  }

	  //Accessor/Mutator methods
	  public void setNext(Item next) {
	    next = next;
	  }

	  public Item getNext() {
	    return next;
	  }

	  public void setID(int id) {
	    id = id;
	  }

	  public int getID() {
	    return id;
	  }

	  public String getStrID() {
	  	return String.format("%05d", id);
	  }

	  public int getMSD() {
	  	return getStrID().charAt(0) - '0';
	  }

	  public void setTemp(double temp) {
	    temp = temp;
	  }

	  public double getTemp() {
	    return temp;
	  }

	  public void setMass(double mass) {
	    mass = mass;
	  }

	  public double getMass() {
	    return mass;

}
}
//END OF CLASS
