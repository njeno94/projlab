package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Model.Field;

public class FieldView implements Drawable {
	private Field field;
	private BufferedImage fieldImage;
	public static BufferedImage oilImage;
	public static BufferedImage honeyImage;
	
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
			oilImage = ImageIO.read((FieldView.class.getResource("/Images/oil.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			honeyImage = ImageIO.read((FieldView.class.getResource("/Images/honey.png")));
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
		g.drawImage(fieldImage, x, y, null);
		DrawFrictionImage(field, g, x, y);
		if (thingView != null) {
			thingView.Draw(g, x, y);
		}		
	}
	
	public static void DrawFrictionImage(Field f, Graphics g, int x, int y) {
		if (f.getFriction() < Field.frictionAtStart ){;
			g.drawImage(oilImage, x, y, null);
		
		} else if (f.getFriction() > Field.frictionAtStart ){
			g.drawImage(honeyImage, x, y, null);	
		}
	}
}
