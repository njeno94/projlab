package Model;

import java.util.HashMap;
import java.util.Map;

public class Field {
	protected Thing thing;
	private Map<Direction, Field> fields;
	
	public Field() {
		thing = null;
		fields = new HashMap<Direction, Field>();
	}
	
	public void setNeighbour(Direction d, Field f) {
		fields.put(d, f);
	}
	
	public Field getNeighbour(Direction d) {
		return fields.get(d);
	}
	
	public boolean accept(Worker w, Direction d) {
		if (thing == null) {
			w.removeFromField();
			addThing(w);
			w.addField(this);
			return true;
		}
		else {
			thing.pushed(w, d);
			if (thing == null) {
				w.removeFromField();
				addThing(w);
				w.addField(this);
				return true;
			}
		}
		return false;
	}
	
	public void accept(Box b, Direction d) {
		if (thing == null) {
			b.removeFromField();
			addThing(b);
			b.addField(this);
		}
		else {
			thing.pushed(b, d);
			if (thing == null) {
				b.removeFromField();
				addThing(b);
				b.addField(this);
			}
		}
	}
	
	public void addPointToThing(Direction d) {
		if (thing != null) {
			thing.addPoint(d);			
		}
	}
	
	public void addThing(Thing t) {
		thing = t;
	}
	
	public void removeThing() {
		thing = null;
	}
	
	public Direction convertDir(Direction d) {
		Direction opp = null;
		switch(d) {
		case RIGHT:
			opp = Direction.LEFT;
			break;
		case LEFT:
			opp = Direction.RIGHT;
			break;
		case UP:
			opp = Direction.DOWN;
			break;
		case DOWN:
			opp = Direction.UP;
			break;
		}
		return opp;
	}
}
