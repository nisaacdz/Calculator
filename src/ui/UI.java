package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import ui.UsefulClasses.Bounds;

public class UI {

	private static JFrame frame;
	private static JPanel activePanel;
	private static JPanel innerPanel;

	private static Insets in;
	private static Bounds b;
	private static JButton button;

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

				System.out.println(frame.getContentPane().getSize());

				JPanel panel = new JPanel();
				panel.setBackground(Color.CYAN);
				panel.setLayout(null);

				innerPanel = Variables.getDefaultOptionPanel();

				button = new JButton("Options");
				button.setSize(100, Variables.getExtraHeight());
				addPopup(button, getPopupMenu());

				panel.add(button);

				activePanel = new JPanel() {

					private static final long serialVersionUID = 1L;

					@Override
					public void paint(Graphics g) {
						super.paint(g);

						innerPanel.setSize(getSize());
					}
				};
				activePanel.setLayout(null);
				activePanel.setBackground(Color.PINK);

				activePanel.add(innerPanel);

				GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
				groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, b.width, Short.MAX_VALUE)
						.addComponent(activePanel, GroupLayout.DEFAULT_SIZE, b.width, Short.MAX_VALUE));
				groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, Variables.getExtraHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(activePanel, GroupLayout.DEFAULT_SIZE,
										b.height - in.top - in.bottom - Variables.getExtraHeight(), Short.MAX_VALUE)));
				frame.getContentPane().setLayout(groupLayout);
				initialize();
			}

		});
	}

	private static JPopupMenu getPopupMenu() {
		JPopupMenu popmenu = new JPopupMenu();
		popmenu.setPopupSize(Variables.getPopupMenuSize());
		popmenu.add(getBasicMenuItem());
		popmenu.add(getBodmasMenuItem());
		popmenu.add(getEqnsMenuItem());
		popmenu.add(getScnMenuItem());
		return popmenu;
	}

	private static JMenuItem getBasicMenuItem() {
		JMenuItem basic = new JMenuItem("Basic");
		basic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button.setText("Basic");
				updateView(getBasicPanel());
			}

		});

		return basic;
	}

	private static JMenuItem getBodmasMenuItem() {
		JMenuItem basic = new JMenuItem("Bodmas");
		basic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button.setText("Bodmas");
				updateView(getBodmasPanel());
			}

		});

		return basic;
	}

	private static JMenuItem getEqnsMenuItem() {
		JMenuItem basic = new JMenuItem("Equations");
		basic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button.setText("Equations");
				updateView(getEqnsPanel());
			}

		});

		return basic;
	}

	private static JMenuItem getScnMenuItem() {
		JMenuItem basic = new JMenuItem("Scientific");
		basic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button.setText("Scientific");
				updateView(getScnPanel());
			}

		});

		return basic;
	}

	private static void updateView(JPanel panel) {
		innerPanel.setVisible(false);
		activePanel.remove(innerPanel);
		innerPanel = panel;
		activePanel.add(innerPanel);
		innerPanel.setVisible(true);
	}

	protected static JPanel getBasicPanel() {
		JPanel panel = new JPanel();
		panel.setSize(b.width, b.height - in.bottom - in.top - Variables.getExtraHeight());
		panel.setBackground(Color.BLUE);
		return panel;
	}

	protected static JPanel getBodmasPanel() {
		JPanel panel = new JPanel();
		panel.setSize(b.width, b.height - in.bottom - in.top - Variables.getExtraHeight());
		panel.setBackground(Color.gray);
		return panel;
	}

	protected static JPanel getScnPanel() {
		JPanel panel = new JPanel();
		panel.setSize(b.width, b.height - in.bottom - in.top - Variables.getExtraHeight());
		panel.setBackground(Color.GREEN);
		return panel;
	}

	protected static JPanel getEqnsPanel() {
		JPanel panel = new JPanel();
		panel.setSize(b.width, b.height - in.bottom - in.top - Variables.getExtraHeight());
		return panel;
	}

	private static void initialize() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private static void addPopup(JButton button, final JPopupMenu popup) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popup.show(button, 0, 0);
			}
		});
	}
}
