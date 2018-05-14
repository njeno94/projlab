package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Model.GoalField;

public class GoalFieldView implements Drawable {
	private GoalField goalField;
	private BufferedImage goalFieldImage;
	
	private ThingView thingView;
	
	public GoalFieldView(GoalField g) {
		goalField = g;
		thingView = null;
		try {
			goalFieldImage = ImageIO.read((GoalFieldView.class.getResource("/Images/goalField.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void setThingView(ThingView t) {
		thingView = t;
	}
	
	@Override
	public void Draw(Graphics g, int x, int y) {
		g.drawImage(goalFieldImage, x, y, null);
		FieldView.DrawFrictionImage(goalField, g, x, y);
		if (thingView != null) {
			thingView.Draw(g, x, y);
		}	
	}
}
