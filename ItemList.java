package CSC_202_Project;

public class ItemList {
	 private Item head;
	  private Item tail;

	  //Constructor, sets variables to null values
	  public ItemList() {
	    head = null;
	    tail = null;
	  }

	  //Returns the Item at the input index from this ItemList
	  public Item get(int index) {
	    if(head == null)
	      throw new RuntimeException("no elements in list");
	    if(index < 0)
	      throw new RuntimeException("index can't be negative");

	    Item item = head;
	    for(int i=0; i<index; i++) {
	      if(item.getNext() == null)
	        throw new RuntimeException("no such index: "+index);
	      item = item.getNext();
	    }
	    return item;
	  }

	  //Returns the size of this ItemList
	  public int size() {
	    if(head == null)
	      return 0;
	    int length = 1;
	    Item item = head;
	    while((item = item.getNext()) != null) length++;
	    return length;
	  }

	  //Appends the input Item to the end of this ItemList
	  public Item append(Item item) {
	    if(tail == null)
	      head = item;
	    else
	      tail.setNext(item);
	    return tail = item;
	  }

	  //Appends a new, default item to the end of this ItemList
	  public Item append() {
	    return append(new Item());
	  }

	  //Inserts the input Item into this ItemList at the input position
	  public Item insert(int pos, Item item) {
	    Item old;

	    if((pos == 0 && head == null) || pos == size())
	      return append(item);
	    else if(pos == 0)
	      old = head;
	    else
	      old = get(pos-1);

	    if(pos == 0) {
	      item.setNext(old);
	      head = item;
	    }
	    else {
	      item.setNext(old.getNext());
	      old.setNext(item);
	    }
	    return item;
	  }

	  //Inserts a new, default Item into this ItemList at the input position
	  public Item insert(int pos) {
	    return insert(pos, new Item());
	  }

	  //Removes the Item at the specified index from this ItemList
	  public Item remove(int index) {
	    if(index >= size())
	      throw new RuntimeException("no such index: "+index);
	    if(index < 0)
	      throw new RuntimeException("index can't be negative");

	    Item tmp;
	    if(index == 0) {
	      tmp = head;
	      head = head.getNext();
	      if(head == null)
	        tail = null;
	    }
	    else {
	      Item prev = get(index-1);
	      tmp = prev.getNext();
	      prev.setNext(prev.getNext().getNext());
	    }
	    tmp.setNext(null);
	    return tmp;
	  }

	  
	  //Similar to, but not the same as, pop, removes and returns the head item
	  public Item pop() {
	    return remove(0);
	  }
}
//END OF CLASS
