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
		 
		this(r.nextInt(Yard.ROW-1)+1,r.nextInt(Yard.COL));
		
		 
	}
	Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public void reApprear() {
		row = r.nextInt(Yard.ROW-1) + 1;
		col = r.nextInt(Yard.COL);
	}
	public Rectangle getRec(){
		return   new Rectangle(col*Yard.BLOCK_SIZE,row*Yard.BLOCK_SIZE,Yard.BLOCK_SIZE,Yard.BLOCK_SIZE);
		
	}
	
	
	
	
	 
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(eggColor);
		g.fillOval(row*Yard.BLOCK_SIZE,col*Yard.BLOCK_SIZE, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		g.setColor(c);
		if(eggColor==Color.green){
			eggColor=Color.red;
		}else{
			eggColor= Color.green;
		}
		
	}

}
