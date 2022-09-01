package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ui.ds.Vec;

public class StandardPanel extends JPanel {

	private class MyButton extends JButton {
		private static final long serialVersionUID = 1L;

		public MyButton(String args) {
			super(args);
			this.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
			this.setBackground(Color.WHITE);
		}

		public MyButton(String args, int fontSize, Color color) {
			super(args);
			this.setFont(new Font(Font.DIALOG, Font.BOLD, fontSize));
			this.setBackground(color);
		}

		public MyButton(int num) {
			super(String.valueOf(num));
			this.setBackground(Variables.getStandardModeNumberButtons());
			this.setFont(new Font("Microsoft JhengHei", Font.BOLD, 30));
		}

		public MyButton(String args, Color color) {
			super(args);
			this.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
			this.setBackground(color);

		}

		public MyButton(String args, int fontSize) {
			super(args);
			this.setFont(new Font(Font.DIALOG, Font.BOLD, fontSize));
			this.setBackground(Color.WHITE);
		}
	}

	public static class Status {
		String text;
		int caretPosition;

		public Status(String text, int pos) {
			this.text = text;
			this.caretPosition = pos;
		}

		@Override
		public String toString() {
			return "[" + text + ", " + caretPosition + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(text);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || obj.getClass() != getClass())
				return false;
			Status other = (Status) obj;
			return other.text.equals(text);
		}
	}

	private static final long serialVersionUID = 1L;

	private JFormattedTextField board;
	private JButton undo, redo, left, right, clear, neg, inv, sqr, sqrt, backspace;
	private JButton seven, eight, nine, minus, abs, four, five, six, plus, times;
	private JButton one, two, three, divide, mod, zero, dot, open, close, equals;

	private JButton[] buttonArray;

	private int caretPosition = 0;

	private Vec<Status> myVector = new Vec<>(350);

	public StandardPanel(JFormattedTextField board) {
		super();
		this.board = board;
		myVector.push(new StandardPanel.Status("", 0));
		this.setLayout(null);
		initializeButtons();
		implementListeners();
	}

	private void initializeButtons() {
		undo = new MyButton("↻", 27, Color.LIGHT_GRAY);
		redo = new MyButton("↺", 27, Color.LIGHT_GRAY);
		left = new MyButton("←");
		right = new MyButton("→");
		clear = new MyButton("CE", Color.LIGHT_GRAY);
		neg = new MyButton("-x");
		inv = new MyButton("inv", 18);
		sqr = new MyButton("x²");
		sqrt = new MyButton("√", 27);
		backspace = new MyButton("⌫", Color.LIGHT_GRAY);
		seven = new MyButton(7);
		eight = new MyButton(8);
		nine = new MyButton(9);
		minus = new MyButton("-");
		abs = new MyButton("||");
		four = new MyButton(4);
		five = new MyButton(5);
		six = new MyButton(6);
		plus = new MyButton("+");
		times = new MyButton("×");
		one = new MyButton(1);
		two = new MyButton(2);
		three = new MyButton(3);
		divide = new MyButton("÷");
		mod = new MyButton("mod", 18);
		zero = new MyButton(0);
		dot = new MyButton(".", 30);
		open = new MyButton("(");
		close = new MyButton(")");
		equals = new MyButton("=", Color.LIGHT_GRAY);

		JButton[] myButtonArray = { undo, redo, left, right, clear, neg, inv, sqr, sqrt, backspace, seven, eight, nine,
				minus, abs, four, five, six, plus, times, one, two, three, divide, mod, zero, dot, open, close,
				equals };
		this.buttonArray = myButtonArray;

		resizeButtons(getWidth(), getHeight());

		addAllToThis();
	}

	private void implementListeners() {
		addKeyListenersTo(new JButton[] { one, two, three, four, five, six, seven, eight, nine, zero, dot, times, plus,
				minus, divide, open, close });
		addBackListenerTo(left, false);
		addBackListenerTo(right, true);
		addDeletionListenerTo(clear, true);
		addDeletionListenerTo(backspace, false);
		addUndoRedoListener(undo, true);
		addUndoRedoListener(redo, false);
		addDocumentListenerTo(board);
	}

	private void addKeyListenersTo(JButton[] buttons) {
		for (JButton button : buttons) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					caretPosition = board.getCaretPosition() + 1;
					board.setText(
							new StringBuilder(board.getText()).insert(caretPosition - 1, button.getText()).toString());
					board.setCaretPosition(caretPosition);
				}
			});
		}
	}

	private void resizeButtons(int width, int height) {
		int x = 0;
		int y = 0;

		int bWidth = (width - 12) / 5;
		int bHeight = (height - 15) / 6;

		makeButtonsInvisible();
		removeAllFromThis();

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

		addAllToThis();
		makeButtonsVisible();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		resizeButtons(getWidth(), getHeight());
	}

	private void addBackListenerTo(JButton button, boolean forward) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.setCaretPosition(forward ? Math.min(board.getText().length(), board.getCaretPosition() + 1)
						: Math.max(0, board.getCaretPosition() - 1));
			}

		});
	}

	private void addDeletionListenerTo(JButton button, boolean clear) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (clear) {
					board.setText("");
					board.getCaret().setVisible(true);
				} else {
					caretPosition = board.getCaretPosition();
					StringBuilder s = new StringBuilder(board.getText());
					if (caretPosition - 1 > -1 && caretPosition > 0) {
						s.deleteCharAt(caretPosition - 1);
						caretPosition -= 1;
						board.setText(s.toString());
						board.setCaretPosition(caretPosition);
					}
				}
			}

		});
	}

	private void addUndoRedoListener(JButton button, boolean undo) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (undo) {
					myVector.undo();
				} else {
					myVector.redo();
				}

				if (myVector.len() == 0) {
					board.setText("");
				} else {
					board.setText(myVector.peek().text);
					board.setCaretPosition(myVector.peek().caretPosition);
				}

			}

		});
	}

	private void addDocumentListenerTo(JFormattedTextField board2) {
		board.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				myVector.querry(new Status(board.getText(), caretPosition));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

		});
	}

	private void makeButtonsInvisible() {
		for (JButton button : buttonArray) {
			button.setVisible(false);
		}
	}

	private void makeButtonsVisible() {
		for (JButton button : buttonArray) {
			button.setVisible(true);
		}
	}

	private void removeAllFromThis() {
		for (JButton button : buttonArray) {
			this.remove(button);
		}
	}

	private void addAllToThis() {
		for (JButton button : buttonArray) {
			this.add(button);
		}
	}

}
