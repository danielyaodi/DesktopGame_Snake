package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	private Node head=null;
	private Node tail=null;
	private int len;
	private Yard y;
	private Node n = new Node(20, 20, Dir.R);

	Snake(Yard y) {
		this.y = y;
		head = n;
		tail = n;
		len = 1;
	}

	public void addToTail() {
		Node node = null;
		switch (tail.dir) {
		case L:
			node = new Node(tail.row, tail.col + 1, tail.dir);
			break;
		case R:
			node = new Node(tail.row, tail.col - 1, tail.dir);
			break;
		case U:
			node = new Node(tail.row + 1, tail.col, tail.dir);
			break;
		case D:
			node = new Node(tail.row - 1, tail.col, tail.dir);
			break;
		}
		tail.next = node;
		node.prev = tail;
		tail = node;
		len++;

	}

	private void addToHead() {
		Node node = null;
		switch (head.dir) {
		case L:
			node = new Node(head.row, head.col - 1, head.dir);
			break;
		case R:
			node = new Node(head.row, head.col + 1, head.dir);
			break;
		case U:
			node = new Node(head.row - 1, head.col, head.dir);
			break;
		case D:
			node = new Node(head.row + 1, head.col, head.dir);
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
		len++;

	}

	private void move() {
		addToHead();
		deleteFromTail();
		checkDead();
	}

	private void deleteFromTail() {
		if (len == 0) {
			return;
		}
		tail = tail.prev;
		tail.next = null;

	}

	public void draw(Graphics g) {

		if (len <= 0) {
			return;
		}

		move();
		for (Node n = head; n != null; n = n.next) {
			n.draw(g);
		}

	}

	private Rectangle getRect() {
		System.out.println("Head:"+head.row+":"+head.col);
		return new Rectangle(head.col * Yard.BLOCK_SIZE, head.row * Yard.BLOCK_SIZE, head.w, head.h);
	}

	public void eat(Egg egg) {
		if (this.getRect().intersects(egg.getRect())) {
			
			egg.reApprear();
			this.addToHead();
			y.score++;
			y.level=y.level-100;
		}
		
	}

	public void checkDead() {
		if (head.row < 0 || head.col < 0 || head.row > Yard.ROW || head.col > Yard.COL) {
			y.stop();;
		}

	}

	private class Node {
		int w = Yard.BLOCK_SIZE;
		int h = Yard.BLOCK_SIZE;
		Node next;
		Node prev;
		int row;
		int col;
		Dir dir;

		Node(int row, int col, Dir dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;

		}

		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.black);
			g.fillRect(col * Yard.BLOCK_SIZE, row * Yard.BLOCK_SIZE, w, h);
			g.setColor(c);
		}

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			if (head.dir != Dir.R) {
				head.dir = Dir.L;
				System.out.println(key);

			}
			break;
		case KeyEvent.VK_RIGHT:
			if (head.dir != Dir.L) {
				head.dir = Dir.R;
			}
			break;
		case KeyEvent.VK_UP:
			if (head.dir != Dir.D) {
				head.dir = Dir.U;
			}
			break;

		case KeyEvent.VK_DOWN:
			if (head.dir != Dir.U) {

				head.dir = Dir.D;
			}
			break;

		}

	}

}
