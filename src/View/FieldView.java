package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Field;

public class FieldView implements Drawable {
	private Field field;
	private BufferedImage fieldImage;
	private BufferedImage fieldWithOilImage;
	private BufferedImage fieldWithHoneyImage;
	
	private ThingView thingView;
	
	public FieldView(Field f) {
		field = f;
		thingView = null;
		try {
			fieldImage = ImageIO.read((FieldView.class.getResource("/Images/field.png")));
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
	
	public void setThingView(ThingView tv) {
		thingView = tv;
	}
	
	@Override
	public void Draw(Graphics g, int x, int y) {
		if (field.getFriction() == Field.frictionAtStart ){
			g.drawImage(fieldImage, x, y, null);
			if (thingView != null) {
				thingView.Draw(g, x, y);
			}		
		} else if (field.getFriction() < Field.frictionAtStart ){
			g.drawImage(fieldWithHoneyImage, x, y, null);
			if (thingView != null) {
				thingView.Draw(g, x, y);
			}			
		} else if (field.getFriction() > Field.frictionAtStart ){
			g.drawImage(fieldWithOilImage, x, y, null);
			if (thingView != null) {
				thingView.Draw(g, x, y);
			}		
		}
	}
}
