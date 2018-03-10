package Model;

public class SwitchHoleField extends Field {
	
	private boolean state;
	
	public SwitchHoleField() {
		state = false;
	}
	
	@Override
	public boolean accept(Worker w, Direction d) {
		
		if (!state) {
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
		else {
			w.disappear();
			return false;
		}
	}
	
	@Override
	public void accept(Box b, Direction d) {
		if (!state) {
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
		else {
			b.disappear();
		}
	}
	
	public void setState(boolean s) {
		state = s;
	}
	
	public void changeState() {
		if (state && thing!=null) {
			thing.disappear();
		}
	}
}
