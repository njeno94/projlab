package Model;

public abstract class Thing {
	protected Field field;

	public Thing() {
		field = null;
	}

	public abstract void pushed(Worker w, Direction d);
	
	public abstract void pushed(Box b, Direction d);

	public void addField(Field f) {
		field = f;
	}
	
	public void addPoint(Direction d) {
			Field neighbour = field.getNeighbour(d);
			
			if (neighbour != null) {
				neighbour.addPointToThing(d);
			}
				}

	public void removeFromField() {
		if (field != null) {		
			field.removeThing();
		}
	}

	public void disappear() {
		removeFromField();
		field = null;
	}

}
