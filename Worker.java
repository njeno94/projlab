package Model;

public class Worker extends Thing {
	private int points;
	
	public Worker() {
		points = 0;
	}
	
	public void step(Direction d) {
		Field nextField = field.getNeighbour(d);
		
		nextField.accept(this, d);
		
	}
	
	@Override
	public void pushed(Worker w, Direction d) {
		//
	}
	
	@Override
	public void pushed(Box b, Direction d) {
		Field nextField = field.getNeighbour(d);
		
		if (nextField.accept(this, d)) {
			//When it can go...
		}
		else {
			this.disappear();
		}
	}
	
	@Override
	public void addPoint(Direction d) {
		Field neighbour = field.getNeighbour(d);
		if (neighbour != null) {
			neighbour.addPointToThing(d);
		}
		else {
			points++;
		}
	}
	
	public int getPoint() {
		return points;
	}
	
}