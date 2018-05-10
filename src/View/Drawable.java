package View;

import java.awt.Graphics;

public interface Drawable {
	public void Draw(Graphics g, int x, int y);
	public void setThingView(ThingView tv);
}