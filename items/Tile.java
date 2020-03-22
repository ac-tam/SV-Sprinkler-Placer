package items;

public class Tile {
	private int xpos;
	private int ypos;
	private boolean isCovered = false;
	private boolean isWatered = false;
	
	public Tile(int x, int y) {
		setPos(x, y);
	}
	
	public void setPos(int x, int y)  {
		setXpos(x); setYpos(y);
	}

	public String getType() {return "None";}
	
	public int getYpos() {
		return ypos;
	}


	public void setYpos(int ypos) {
		this.ypos = ypos;
	}


	public int getXpos() {
		return xpos;
	}


	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public boolean isCovered() {
		return isCovered;
	}

	public void setCovered(boolean isCovered) {
		this.isCovered = isCovered;
	}

	public boolean isWatered() {
		return isWatered;
	}

	public void setWatered(boolean isWatered) {
		this.isWatered = isWatered;
	}


}
