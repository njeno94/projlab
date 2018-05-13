package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Hole;
import Model.Thing;
import Model.Worker;

public class HoleView implements Drawable {
	private Hole hole;
	private BufferedImage holeImage;
	
	private ThingView thingView = null;
	
	public HoleView(Hole h) {
		hole = h;
		try {
			holeImage = ImageIO.read(HoleView.class.getResource("/Images/hole.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void Draw(Graphics g, int x, int y) {
		g.drawImage(holeImage, x, y, null);
	}

	@Override
	public void setThingView(ThingView tv) {
		//It does not matter....
	}
}
