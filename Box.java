package Model;

public class Box extends Thing {
	
	public void pushed(Worker w, Direction d) {
		Field nextField = field.getNeighbour(d);
		
		nextField.accept(this, d);
	}
	
	public void pushed(Box b, Direction d) {
		Field nextField = field.getNeighbour(d);
		
		nextField.accept(this, d);
	}

}