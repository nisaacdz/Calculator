package ui;

import java.awt.Dimension;

public class UsefulClasses {

	public static class Bounds extends Dimension {

		private static final long serialVersionUID = 1L;
		int x = 0, y = 0, width, height;

		public Bounds(int a, int b, int c, int d) {
			super(c, d);
			this.x = a;
			this.y = b;
			this.width = c;
			this.height = d;
		}

		public Bounds(int c, int d) {
			super(c, d);
			this.width = c;
			this.height = d;
		}

	}

}
