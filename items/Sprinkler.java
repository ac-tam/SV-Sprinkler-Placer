package items;
public class Sprinkler extends Tile {
		private int radius;
		
		public int getRadius() {
			return radius;
		}
		public void setRadius(int radius) {
			this.radius = radius;
		}
		
		public Sprinkler(int x, int y) {
			super(x, y);
		}
		public String getType() {return "None";}

}
