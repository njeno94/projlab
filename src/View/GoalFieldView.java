package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Field;
import Model.GoalField;

public class GoalFieldView implements Drawable {
	private GoalField goalField;
	private BufferedImage goalFieldImage;
	private BufferedImage fieldWithOilImage;
	private BufferedImage fieldWithHoneyImage;
	
	private ThingView thingView;
	
	public GoalFieldView(GoalField g) {
		goalField = g;
		thingView = null;
		try {
			goalFieldImage = ImageIO.read((FieldView.class.getResource("/Images/goalField.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fieldWithOilImage = ImageIO.read((FieldView.class.getResource("/Images/fieldWithOil.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fieldWithHoneyImage = ImageIO.read((FieldView.class.getResource("/Images/fieldWithHoney.png")));
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
		if (goalField.getFriction() == Field.frictionAtStart ){
			g.drawImage(goalFieldImage, x, y, null);
			if (thingView != null) {
				thingView.Draw(g, x, y);
			}
		} else if (goalField.getFriction() < Field.frictionAtStart ){
			g.drawImage(fieldWithOilImage, x, y, null);
			if (thingView != null) {
				thingView.Draw(g, x, y);
			}			
		} else if (goalField.getFriction() > Field.frictionAtStart ){
			g.drawImage(fieldWithHoneyImage, x, y, null);
			if (thingView != null) {
				thingView.Draw(g, x, y);
			}		
		}
	}
}
