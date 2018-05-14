package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Model.SwitchHoleField;

public class SwitchHoleFieldView implements Drawable {
	private SwitchHoleField switchHoleField;
	private BufferedImage openedHoleFieldImage;
	private BufferedImage closedHoleFieldImage;
	
	private ThingView thingView;
	
	public SwitchHoleFieldView(SwitchHoleField shf) {
		switchHoleField = shf;
		
		try {
			openedHoleFieldImage = ImageIO.read((SwitchHoleFieldView.class.getResource("/Images/hole.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closedHoleFieldImage = ImageIO.read((SwitchHoleFieldView.class.getResource("/Images/switchHoleField.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void Draw(Graphics g, int x, int y) {
		if (switchHoleField.getState()) {
			g.drawImage(openedHoleFieldImage, x, y, null);
		} else {
			g.drawImage(closedHoleFieldImage, x, y, null);
			FieldView.DrawFrictionImage(switchHoleField, g, x, y);
			if (thingView != null) {
				thingView.Draw(g, x, y);
			}
		}
	}

	@Override
	public void setThingView(ThingView tv) {
		thingView = tv;
	}
}