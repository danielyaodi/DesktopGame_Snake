package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Yard extends JFrame {

	public static final int ROW = 50;
	public static final int COL = 50;
	public static final int BLOCK_SIZE = 10;
	Snake s = new Snake(this);
	Egg egg = new Egg();
	PaintThread paintThread = new PaintThread();
	Image offScreenImage;
	boolean running = true;
	int score;
	int level=1000;

	public void launch() {
		this.setTitle("Happy Snake");
		this.setBounds(200, 200, ROW * BLOCK_SIZE, COL * BLOCK_SIZE);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
		this.addKeyListener(new KeyMonitor());
		new Thread(paintThread).start();
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, COL * BLOCK_SIZE, ROW * BLOCK_SIZE);
		g.setColor(Color.BLACK);
		for (int i = 1; i < ROW; i++) {
			g.drawRect(0, BLOCK_SIZE * i, COL * BLOCK_SIZE, BLOCK_SIZE * i);
		}
		for (int i = 1; i < COL; i++) {
			g.drawRect(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, ROW * BLOCK_SIZE);
		}
		g.setColor(Color.yellow);
		g.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
		g.drawString("You ate: "+ score +" eggs", 50, 50);
		g.drawString("Level "+score, 400, 50);
		g.setColor(c);
		s.eat(egg);
		egg.draw(g);
		s.draw(g);
		

	}

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(COL * BLOCK_SIZE, ROW * BLOCK_SIZE);
			Graphics gOFF = offScreenImage.getGraphics();
			paint(gOFF);
			g.drawImage(offScreenImage, 0, 0, null);
		}
	}

	public class PaintThread implements Runnable {

		@Override
		public void run() {
			while (running) {
				repaint();
				try {
					Thread.sleep(level);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			// int key = e.getKeyCode();
			s.keyPressed(e);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Yard().launch();
	}

	public void stop() {
		running = false;

	}

}
