package ui;

import java.awt.Color;

import javax.swing.JPanel;

import ui.UsefulClasses.Bounds;

public class Variables {

	public static Bounds getFrameSize() {
		return new UsefulClasses.Bounds(30, 30, 420, 600);
	}

	public static Color getFrameColor() {
		return Color.BLUE;
	}

	public static int getActivePanelWidth() {
		return 450;
	}

	public static Color getScreenAndBackgroundColor() {
		return new Color(220, 220, 220);
	}

	public static Bounds getPopupMenuSize() {
		return new UsefulClasses.Bounds(200, 400);
	}

	public static Bounds getMenuButtonSize() {
		return new UsefulClasses.Bounds(150, getExtraHeight());
	}

	public static Color getStandardModeNumberButtons() {
		return new Color(245, 240, 255);
	}

	public static int getExtraHeight() {
		return 25;
	}

	public static JPanel getDefaultOptionPanel() {
		return UI.getStandardPanel();
	}

	public static Color getButtonColor() {
		return Color.LIGHT_GRAY;
	}

	public static Color getBackgroundColor() {
		return new Color(200, 220, 255);
	}
}
