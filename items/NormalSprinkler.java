package items;

public class NormalSprinkler extends Sprinkler {
	
	public NormalSprinkler(int x, int y) {
		super(x, y);
		setRadius(1);
	}
	
	public String getType() {return "Normal";}
}
