package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Box;
import Model.Thing;
import Model.Worker;

public class ThingView implements Drawable {
	private Thing thing;
	private BufferedImage thingImage;
	
	public ThingView(Worker w, String name) {
		thing = w;
		
		if (name.equals("w2")) {
			try {
				thingImage = ImageIO.read((ThingView.class.getResource("/Images/w2.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} else {
			try {
				thingImage = ImageIO.read((ThingView.class.getResource("/Images/w1.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ThingView(Box b) {
		thing = b;
		
		try {
			thingImage = ImageIO.read((ThingView.class.getResource("/Images/box.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void Draw(Graphics g, int x, int y) {
		//Konstans eltolás, mert kisebb a thingImage, hogy jobban látszódjon, hogy hol van a thing
		g.drawImage(thingImage, x + 10, y + 10, null);
	}

	@Override
	public void setThingView(ThingView tv) {
		//Not possible
	}
}
