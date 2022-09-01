package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import ui.UsefulClasses.Bounds;
import ui.ds.RoundedBorder;

public class UI {

	private static JFrame frame;
	private static JPanel activePanel;
	private static JPanel innerPanel;

	private static Insets in;
	private static Bounds b;
	private static JButton optionsButton;
	private static JButton resizeButton;

	private static int innerPanelHeight;
	private static int innerPanelWidth;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void createUI() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame = new JFrame();
				b = Variables.getFrameSize();
				frame.setBounds(b.x, b.y, b.width, b.height);
				in = frame.getInsets();
				innerPanelHeight = b.height - in.top - in.bottom - Variables.getExtraHeight();
				innerPanelWidth = b.width - in.left - in.right;

				JPanel panel = new JPanel() {
					private static final long serialVersionUID = 1L;

					@Override
					public void paint(Graphics g) {
						super.paint(g);
						resizeButton.setBounds(getWidth() - 80, 0, 55, getHeight());
					}
				};
				panel.setBackground(Variables.getScreenAndBackgroundColor());
				panel.setLayout(null);

				innerPanel = Variables.getDefaultOptionPanel();

				optionsButton = new JButton("Options");
				optionsButton.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
				optionsButton.setSize(Variables.getMenuButtonSize());
				optionsButton.setBorder(new RoundedBorder(Variables.getScreenAndBackgroundColor(), 1, 8));
				optionsButton.setBackground(Variables.getButtonColor());
				addMouseListenerTo(optionsButton);
				addPopup(optionsButton, getPopupMenu());

				panel.add(optionsButton);

				activePanel = new JPanel() {

					private static final long serialVersionUID = 1L;

					@Override
					public void paint(Graphics g) {
						super.paint(g);
						innerPanel.setSize(getSize());
					}
				};
				activePanel.setLayout(null);

				activePanel.add(innerPanel);

				GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
				groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, b.width, Short.MAX_VALUE)
						.addComponent(activePanel, GroupLayout.DEFAULT_SIZE, innerPanelWidth, Short.MAX_VALUE));
				groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, Variables.getExtraHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(activePanel, GroupLayout.DEFAULT_SIZE, innerPanelHeight,
										Short.MAX_VALUE)));

				resizeButton = new JButton("↖↘");
				resizeButton.setFont(new Font(Font.DIALOG, Font.BOLD, 11));
				resizeButton.setBackground(Variables.getButtonColor());
				addMouseListenerTo(resizeButton);
				addResizeListener(resizeButton);
				panel.add(resizeButton);

				frame.getContentPane().setLayout(groupLayout);
				initialize();
			}

		});
	}

	private static JPopupMenu getPopupMenu() {
		JPopupMenu popmenu = new JPopupMenu();
		popmenu.setPopupSize(Variables.getPopupMenuSize());
		popmenu.add(getStandardMenuItem());
		popmenu.add(getScientificMenuItem());
		popmenu.add(getCalculusMenuItem());
		popmenu.add(getGraphMenuItem());
		popmenu.add(getConverterMenuItem());
		return popmenu;
	}

	private static JMenuItem getConverterMenuItem() {
		JMenuItem converter = new JMenuItem("Converter");
		converter.setFont(new Font("Microsoft JhengHei", Font.BOLD, 23));
		converter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				optionsButton.setText("Converter");
				// TODO
				// TOD
				// TODO
			}
		});

		return converter;
	}

	private static JMenuItem getStandardMenuItem() {
		JMenuItem standard = new JMenuItem("Standard");
		standard.setFont(new Font("Microsoft JhengHei", Font.BOLD, 23));
		standard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optionsButton.setText("Standard");
				updateView(getStandardPanel());
			}

		});

		return standard;
	}

	private static JMenuItem getScientificMenuItem() {
		JMenuItem scientific = new JMenuItem("Scientific");
		scientific.setFont(new Font("Microsoft JhengHei", Font.BOLD, 23));
		scientific.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optionsButton.setText("Scientific");
				updateView(getScientificPanel());
			}

		});

		return scientific;
	}

	private static JMenuItem getCalculusMenuItem() {
		JMenuItem calc = new JMenuItem("Calculus");
		calc.setFont(new Font("Microsoft JhengHei", Font.BOLD, 23));
		calc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optionsButton.setText("Calculus");
				updateView(getCalculusPanel());
			}

		});

		return calc;
	}

	private static JMenuItem getGraphMenuItem() {
		JMenuItem graph = new JMenuItem("Graphing");
		graph.setFont(new Font("Microsoft JhengHei", Font.BOLD, 23));
		graph.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optionsButton.setText("Graphing");
				updateView(getGraphPanel());
			}

		});

		return graph;
	}

	private static void updateView(JPanel panel) {
		innerPanel.setVisible(false);
		activePanel.remove(innerPanel);
		innerPanel = panel;
		activePanel.add(innerPanel);
		innerPanel.setVisible(true);
	}

	protected static JPanel getStandardPanel() {
		JFormattedTextField board1 = new JFormattedTextField();
		board1.setBorder(BorderFactory.createEmptyBorder());
		board1.setBackground(Variables.getScreenAndBackgroundColor());
		board1.setForeground(Color.RED);
		board1.setHorizontalAlignment(JTextField.RIGHT);
		board1.setFont(new Font("Microsoft JhengHei", Font.BOLD, 30));

		JFormattedTextField board2 = new JFormattedTextField();
		board2.setBackground(Variables.getScreenAndBackgroundColor());
		board2.setBorder(BorderFactory.createEmptyBorder());
		board2.setForeground(Color.BLACK);
		board2.setHorizontalAlignment(JTextField.RIGHT);
		board2.setFont(new Font("Microsoft JhengHei", Font.BOLD, 30));
		board2.getCaret().setBlinkRate(500);
		board2.setCaretPosition(0);
		board2.getCaret().setVisible(true);
		board2.setCaretColor(Color.RED);
		board2.putClientProperty("caretWidth", 3);
		addCaretListenerTo(board2);

		JPanel panel = new StandardPanel(board2);
		panel.setBackground(Variables.getScreenAndBackgroundColor());

		JPanel standard = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				board1.setBounds(0, 3, getWidth(), getHeight() / 6);
				board2.setBounds(0, 3 + (getHeight() / 6), getWidth(), getHeight() / 6);

				panel.setBounds(3, 6 + (getHeight() / 3), getWidth() - 6, (2 * getHeight() - 27) / 3);
			}
		};

		standard.setLayout(null);
		standard.setBackground(Variables.getScreenAndBackgroundColor());
		standard.add(board1);
		standard.add(board2);
		standard.add(panel);

		standard.setSize(innerPanelWidth, innerPanelHeight);
		return standard;
	}

	protected static JPanel getScientificPanel() {
		JPanel panel = new JPanel();
		panel.setSize(b.width, innerPanelHeight);
		panel.setBackground(Color.gray);
		return panel;
	}

	protected static JPanel getGraphPanel() {
		JPanel panel = new JPanel();
		panel.setSize(b.width, innerPanelHeight);
		panel.setBackground(Color.GREEN);
		return panel;
	}

	protected static JPanel getCalculusPanel() {
		JPanel panel = new JPanel();
		panel.setSize(b.width, innerPanelHeight);
		return panel;
	}

	private static void initialize() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private static void addPopup(JButton button, final JPopupMenu popup) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popup.show(button, 0, 0);
			}
		});
	}

	private static void addMouseListenerTo(JButton button) {
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(Variables.getButtonColor());
			}

		});

	}

	private static void addCaretListenerTo(JFormattedTextField board) {
		board.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				board.getCaret().setVisible(true);
			}

		});
	}

	private static void addResizeListener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(frame.getX(), frame.getY(), b.width, b.height);
			}
		});
	}
}
