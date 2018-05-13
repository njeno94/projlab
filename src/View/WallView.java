package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Model.Wall;

public class WallView implements Drawable {
	private Wall wall;
	private BufferedImage wallImage;
	
	private ThingView thingView;
	
	public WallView(Wall w) {
		thingView = null;
		wall = w;
		try {
			wallImage = ImageIO.read((WallView.class.getResource("/Images/wall.png")));
		} catch (IOException e) {
			System.out.println("Nincs meg a fal képe!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void Draw(Graphics g, int x, int y) {
		g.drawImage(wallImage, x, y, null);
	}

	@Override
	public void setThingView(ThingView tv) {
		// TODO
	}
}
