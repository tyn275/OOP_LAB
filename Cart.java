public class Cart {
	public static final int MAX_NUMBER_ORDERED = 20;
	private DigitalVideoDisc itemOrdered[] = 
			new DigitalVideoDisc[MAX_NUMBER_ORDERED];
	
	public int qtyOrdered = 0;
	
	private void displayCart() {
		System.out.println("The cart: \n");
		for (int k = 0; k < qtyOrdered; k++) {
			System.out.println((k+1) + ". " + itemOrdered[k].toString());
		}
	}
	
	private boolean IsFull() {
		return qtyOrdered >= MAX_NUMBER_ORDERED;
	}
	
	public void addDigitalVideoDisc(DigitalVideoDisc disc) {
		if (!IsFull()) {
			itemOrdered[qtyOrdered] = disc;
			qtyOrdered++;
			System.out.println("The disc has been added\n");
		}
		else 
			System.out.println("The cart is almost full\n");
		
		displayCart();
	}
	
	public void addDigitalVideoDisc1(DigitalVideoDisc[] dvdList) {
	    for (DigitalVideoDisc disc : dvdList) {
	        if (!IsFull()) {
	            itemOrdered[qtyOrdered] = disc;
	            qtyOrdered++;
	            System.out.println("The disc " + disc.getTitle() + " has been added\n" );
	        } else {
	            System.out.println("The cart is almost full\\n");
	            break;
	        }
	    }
	    displayCart();
	}

	
	public void addDigitalVideoDisc(DigitalVideoDisc... dvdList) {
	    for (DigitalVideoDisc disc : dvdList) {
	        if (!IsFull()) {
	            itemOrdered[qtyOrdered] = disc;
	            qtyOrdered++;
	            System.out.println("The disc " + disc.getTitle() + " has been added\n" );
	        } else {
	            System.out.println("The cart is almost full\\n");
	        }
	    }
	    displayCart();
	}

	
	public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2){
		
		// Them dvd1
		if (!IsFull()) {
			itemOrdered[qtyOrdered] = dvd1;
			qtyOrdered++;
			System.out.println("The dvd1 has been added\n");
		}else 
			System.out.println("The cart is almost full\n");
		
		//Them dvd2
		if (!IsFull()) {
			itemOrdered[qtyOrdered] = dvd2;
			qtyOrdered++;
			System.out.println("The dvd2 has been added\n");
		}else 
			System.out.println("The cart is almost full\n");
		
		displayCart();
		
	}

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
    	int i, j;
    	boolean found = false;
    	if (qtyOrdered == 0) 
    		System.out.println("The cart is empty\n");
    	for (i = 0; i < qtyOrdered; i++) {
    		if (itemOrdered[i].equals(disc))
    		{
    			found = true;
    			for (j = i; j < qtyOrdered - 1; j++) {
    				itemOrdered[j] = itemOrdered[j + 1];
    			}
    			qtyOrdered--;
    			System.out.println("The disc has been removed\n");
    			break; // Không break thì sẽ tiếp tục xóa đĩa cùng tên, cùng...
    		}
    	}
    	if (!found)
    		System.out.println("The disc is not exist\n");
    	
    	displayCart();
	}
    
    public float totalCost() {
    	float totalCost = 0;
    	if (qtyOrdered == 0) return totalCost;
    	for (int k = 0; k < qtyOrdered; k++) {
    		totalCost += itemOrdered[k].getCost();
    	}
    	return totalCost;
    }
}

