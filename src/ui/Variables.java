package ui;

import java.awt.Color;

import javax.swing.JPanel;

import ui.UsefulClasses.Bounds;

public class Variables {

	public static Bounds getFrameSize() {
		return new UsefulClasses.Bounds(200, 150, 400, 600);
	}

	public static Color getFrameColor() {
		return Color.BLUE;
	}

	public static int getActivePanelWidth() {
		return 400;
	}

	public static Bounds getPopupMenuSize() {
		return new UsefulClasses.Bounds(150, 200);
	}

	public static Bounds getMenuButtonSize() {
		return new UsefulClasses.Bounds(150, getExtraHeight());
	}

	public static int getExtraHeight() {
		return 25;
	}

	public static JPanel getDefaultOptionPanel() {
		return UI.getBasicPanel();
	}
}
