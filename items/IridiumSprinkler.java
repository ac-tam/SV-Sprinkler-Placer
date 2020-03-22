package items;

public class IridiumSprinkler extends Sprinkler {

	public IridiumSprinkler(int x, int y) {
		super(x, y);
		setRadius(3);
		}
	@Override
	public String getType() {return "Iridium";}

	
}
