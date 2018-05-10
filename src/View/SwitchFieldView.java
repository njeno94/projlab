package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Model.SwitchField;

public class SwitchFieldView implements Drawable {
	private SwitchField switchField;
	private BufferedImage activeswitchFieldImage;
	private BufferedImage inactiveswitchFieldImage;
	
	private ThingView thingView;
	
	public SwitchFieldView(SwitchField sf) {
		switchField = sf;
		
			try {
				activeswitchFieldImage = ImageIO.read((SwitchFieldView.class.getResource("/Images/activeSwitchField.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			try {
				inactiveswitchFieldImage = ImageIO.read((SwitchFieldView.class.getResource("/Images/inactiveSwitchField.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void Draw(Graphics g, int x, int y) {
		if (switchField.isActive()) {
			g.drawImage(activeswitchFieldImage, x, y, null);
			if (thingView != null) {
				thingView.Draw(g, x, y);
			}
		} else {
			g.drawImage(inactiveswitchFieldImage, x, y, null);
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