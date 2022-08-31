package ui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

public class StandardPanel extends JPanel {

	private class MyButton extends JButton {
		private static final long serialVersionUID = 1L;

		public MyButton(String args) {
			super(args);
			this.setFont(new Font("Microsoft JhengHei", Font.BOLD, 23));
		}
	}

	private static final long serialVersionUID = 1L;

	JFormattedTextField board;
	private JButton undo, redo, left, right, clear, neg, inv, sqr, sqrt, backspace;
	private JButton seven, eight, nine, minus, abs, four, five, six, plus, times;
	private JButton one, two, three, divide, mod, zero, dot, open, close, equals;

	public StandardPanel(JFormattedTextField board) {
		super();
		this.board = board;

		initializeButtons();
	}

	private void initializeButtons() {
		undo = new MyButton("↺");
		redo = new MyButton("↻");
		left = new MyButton("←");
		right = new MyButton("→");
		clear = new MyButton("CE");
		neg = new MyButton("-x");
		inv = new MyButton("inv");
		sqr = new MyButton("x²");
		sqrt = new MyButton("√");
		backspace = new MyButton("⌫");
		seven = new MyButton("7");
		eight = new MyButton("8");
		nine = new MyButton("9");
		minus = new MyButton("-");
		abs = new MyButton("||");
		four = new MyButton("4");
		five = new MyButton("5");
		six = new MyButton("6");
		plus = new MyButton("+");
		times = new MyButton("×");
		one = new MyButton("1");
		two = new MyButton("2");
		three = new MyButton("3");
		divide = new MyButton("÷");
		mod = new MyButton("mod");
		zero = new MyButton("0");
		dot = new MyButton(".");
		open = new MyButton("(");
		close = new MyButton(")");
		equals = new MyButton("=");

		this.add(undo);
		this.add(redo);
		this.add(left);
		this.add(right);
		this.add(clear);
		this.add(neg);
		this.add(inv);
		this.add(sqr);
		this.add(sqrt);
		this.add(backspace);
		this.add(seven);
		this.add(eight);
		this.add(nine);
		this.add(minus);
		this.add(abs);
		this.add(four);
		this.add(five);
		this.add(six);
		this.add(plus);
		this.add(times);
		this.add(one);
		this.add(two);
		this.add(three);
		this.add(divide);
		this.add(mod);
		this.add(zero);
		this.add(dot);
		this.add(open);
		this.add(close);
		this.add(equals);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		int x = 0;
		int y = 0;

		int bWidth = (getWidth() - 12) / 5;
		int bHeight = (getHeight() - 15) / 6;

		undo.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		redo.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		left.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		right.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		clear.setBounds(x, y, bWidth, bHeight);

		x = 0;
		y += bHeight + 3;

		neg.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		inv.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		sqr.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		sqrt.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		backspace.setBounds(x, y, bWidth, bHeight);

		x = 0;
		y += bHeight + 3;

		seven.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		eight.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		nine.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		minus.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		abs.setBounds(x, y, bWidth, bHeight);

		x = 0;
		y += bHeight + 3;

		four.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		five.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		six.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		plus.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		times.setBounds(x, y, bWidth, bHeight);

		x = 0;
		y += bHeight + 3;

		one.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		two.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		three.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		divide.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		mod.setBounds(x, y, bWidth, bHeight);

		x = 0;
		y += bHeight + 3;

		zero.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		dot.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		open.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		close.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		equals.setBounds(x, y, bWidth, bHeight);
	}

}
