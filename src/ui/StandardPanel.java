package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import ui.ds.Vec;

public class StandardPanel extends JPanel {

	private class MyButton extends JButton {
		private static final long serialVersionUID = 1L;

		public Color realColor;
		public Color hoverColor;

		public MyButton(String args) {
			super(args);
			realColor = Variables.getOtherButtonsColor();
			hoverColor = Variables.getButtonHoverColor2();
			this.setBackground(realColor);
			this.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		}

		public MyButton(int num) {
			super(String.valueOf(num));
			realColor = Variables.getStandardModeNumberButtons();
			hoverColor = Variables.getButtonHoverColor();
			this.setBackground(realColor);
			this.setFont(new Font("Microsoft JhengHei", Font.BOLD, 30));
		}

		public MyButton(String args, int fontSize) {
			super(args);
			realColor = Variables.getOtherButtonsColor();
			hoverColor = Variables.getButtonHoverColor2();
			this.setBackground(realColor);
			this.setFont(new Font(Font.DIALOG, Font.BOLD, fontSize));
		}

		public MyButton(String string, int i, int bold) {
			super(string);
			realColor = Variables.getOtherButtonsColor();
			hoverColor = Variables.getButtonHoverColor2();
			this.setBackground(realColor);
			this.setFont(new Font(Font.DIALOG, bold, i));
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
			return "[\"" + text + "\", " + caretPosition + "]";
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
	private MyButton undo, redo, left, right, clear, home, inv, sqr, sqrt, backspace;
	private MyButton seven, eight, nine, minus, abs, four, five, six, plus, times;
	private MyButton one, two, three, divide, mod, dot, zer, open, close, equals;

	private MyButton[] buttonArray;
	private Status peeker;

	private Vec<Status> myVector = new Vec<>(350);

	public StandardPanel(JFormattedTextField board) {
		super();
		this.board = board;
		myVector.push(new Status("", 0));
		this.setLayout(null);
		initializeButtons();
		implementListeners();
	}

	private void initializeButtons() {
		undo = new MyButton("↻", 27);
		redo = new MyButton("↺", 27);
		left = new MyButton("←", 27, Font.BOLD);
		right = new MyButton("→", 27, Font.BOLD);
		clear = new MyButton("CE");
		home = new MyButton("H");
		inv = new MyButton("inv", 18);
		sqr = new MyButton("a^b");
		sqrt = new MyButton("√", 27);
		backspace = new MyButton("⌫");
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
		zer = new MyButton(0);
		dot = new MyButton(".", 30);
		open = new MyButton("(");
		close = new MyButton(")");
		equals = new MyButton("=");

		MyButton[] myButtonArray = { undo, redo, left, right, clear, home, inv, sqr, sqrt, backspace, seven, eight,
				nine, minus, abs, four, five, six, plus, times, one, two, three, divide, mod, dot, zer, open, close,
				equals };
		this.buttonArray = myButtonArray;

		resizeButtons(getWidth(), getHeight());

		addAllToThis();
	}

	private void implementListeners() {
		addKeyListenersTo(new JButton[] { one, two, three, four, five, six, seven, eight, nine, dot, zer, times, plus,
				minus, divide, open, close });
		addBackListenerTo(left, false);
		addBackListenerTo(right, true);
		addDeletionListenerTo(clear, true);
		addDeletionListenerTo(backspace, false);
		addUndoRedoListener(undo, true);
		addUndoRedoListener(redo, false);
		addDocumentListenerTo(board);
		addMouseListenersTo(buttonArray);
		addModInsertListenerTo(mod);
		addSqrtInsertListenerTo(sqrt);
		addInverseInsertListenerTo(inv);
		addAbsInsertListenerTo(abs);
		addHomeListener(home);
	}

	private void resizeButtons(int width, int height) {
		int x = 0;
		int y = 0;

		int bWidth = (width - 12) / 5;
		int bHeight = (height - 15) / 6;

		makeButtonsInvisible();

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

		home.setBounds(x, y, bWidth, bHeight);
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

		dot.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		zer.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		open.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		close.setBounds(x, y, bWidth, bHeight);
		x += bWidth + 3;
		equals.setBounds(x, y, bWidth, bHeight);

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
				try {
					if (clear) {
						board.getDocument().remove(0, board.getText().length());
					} else {
						int i = board.getCaretPosition();
						if (i > 0)
							board.getDocument().remove(--i, 1);
					}
				} catch (BadLocationException a) {
					a.printStackTrace();
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

				peeker = myVector.peek();
				System.out.println(peeker);
				board.setText(peeker.text);
				board.setCaretPosition(peeker.caretPosition);
			}

		});
	}

	private void addDocumentListenerTo(JFormattedTextField board2) {
		board.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				myVector.querry(new Status(board.getText(), UI.caretPos + 1));
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

	private void addAllToThis() {
		for (JButton button : buttonArray) {
			this.add(button);
		}
	}

	private void addMouseListenersTo(MyButton[] buttons) {
		for (MyButton button : buttons) {
			button.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					button.setBackground(button.hoverColor);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							button.setBackground(button.realColor);
						}

					}).start();
				}

			});
		}
	}

	private void addKeyListenersTo(JButton[] buttons) {
		for (JButton button : buttons) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						board.getDocument().insertString(board.getCaretPosition(), button.getText(), null);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	}

	private void addModInsertListenerTo(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					board.getDocument().insertString(board.getCaretPosition(), "mod", null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void addSqrtInsertListenerTo(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					board.getDocument().insertString(board.getCaretPosition(), button.getText() + "()", null);
					board.setCaretPosition(board.getCaretPosition() - 1);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void addInverseInsertListenerTo(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					board.getDocument().insertString(board.getCaretPosition(), "inv()", null);
					board.setCaretPosition(board.getCaretPosition() - 1);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void addAbsInsertListenerTo(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					board.getDocument().insertString(board.getCaretPosition(), "||", null);
					board.setCaretPosition(board.getCaretPosition() - 1);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void addHomeListener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.setCaretPosition(board.getText().length());
			}
		});
	}

}
