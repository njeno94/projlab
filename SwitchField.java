package Model;

public class SwitchField extends Field {
	
	private SwitchHoleField hole;
	
	@Override
	public void accept(Box b, Direction d) {
		if (thing == null) {
			b.removeFromField();
			addThing(b);
			b.addField(this);
			hole.setState(true);
			hole.changeState();
		}
		else {
			thing.pushed(b, d);
			if (thing == null) {
				b.removeFromField();
				addThing(b);
				b.addField(this);
				hole.setState(true);
				hole.changeState();
			}
		}
	}
	
	public void setSwitchHoleField(SwitchHoleField sh) {
		hole = (SwitchHoleField)sh;
	}
	
	@Override
	public void removeThing() {
		thing = null;
		hole.setState(false);
	}

}