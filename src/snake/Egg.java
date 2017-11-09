package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	private int row;
	private int col;
	static Random r = new Random();
	Color eggColor = Color.green;

	Egg() {

		this(r.nextInt(Yard.ROW - 3) + 3, r.nextInt(Yard.COL + 1) - 1);

	}

	Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public void reApprear() {
		System.out.print("gogog");
		this.row = r.nextInt(Yard.ROW - 3) + 3;
		this.col = r.nextInt(Yard.COL + 1) - 1;
	}

	public Rectangle getRect() {
		System.out.println("Egg:" + this.row + ":" + this.col);
		return new Rectangle(row * Yard.BLOCK_SIZE, col * Yard.BLOCK_SIZE, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(eggColor);
		g.fillOval(row * Yard.BLOCK_SIZE, col * Yard.BLOCK_SIZE, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		g.setColor(c);
		if (eggColor == Color.green) {
			eggColor = Color.red;
		} else {
			eggColor = Color.green;
		}

	}

}
