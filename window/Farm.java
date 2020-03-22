package window;
import items.*;

public class Farm {
	private Tile[][] layout;
	public Tile[][] getLayout() {return layout;}
	public Farm(int x, int y) {
		layout = new Tile[x][y];
		for (int i = 0; i < layout.length; i++)
			for (int j = 0; j < layout[0].length; j++)
				layout[i][j] = new Tile(i, j);
	}
	
	public void scarecrow(boolean deluxeScarecows, int y, int x) {
		try {
			layout[y][x] = new Scarecrow(y, x);
			
			for (int i = -6; i < 7; i++)
				for (int j = -6; j < 7; j++)
					try {
					layout[y + i][x + j].setCovered(true);
					} catch (ArrayIndexOutOfBoundsException e) {;}
			
			for (int i = 7; i < 9; i++)
				for (int j = -4; j < 5; j++)
						try {
							layout[y + i][x + j].setCovered(true);
							} catch (ArrayIndexOutOfBoundsException e) {;}
			for (int i = -8; i < -6; i++)
				for (int j = -4; j < 5; j++)
						try {
							layout[y + i][x + j].setCovered(true);
							} catch (ArrayIndexOutOfBoundsException e) {;}
			
			for (int i = -4; i < 5; i++)
				for (int j = -8; j < -6; j++)
						try {
							layout[y + i][x + j].setCovered(true);
							} catch (ArrayIndexOutOfBoundsException e) {;}
			for (int i = -4; i < 5; i++)
				for (int j = 7; j < 9; j++)
						try {
							layout[y + i][x + j].setCovered(true);
							} catch (ArrayIndexOutOfBoundsException e) {;}
			
			try {
				layout[y + 5][x + 7].setCovered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
			try {
				layout[y + 7][x + 5].setCovered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
			try {
				layout[y - 5][x - 7].setCovered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
			try {
				layout[y - 7][x - 5].setCovered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
			
			try {
				layout[y + 5][x - 7].setCovered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
			try {
				layout[y + 7][x - 5].setCovered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
			try {
				layout[y - 5][x + 7].setCovered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
			try {
				layout[y - 7][x + 5].setCovered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
		
		} 
		catch (ArrayIndexOutOfBoundsException e) {;}
	}
	public void water(Sprinkler sprinklerType, int y, int x) {
		
		switch(sprinklerType.getType()) {
			case "Iridium":
				try {
				layout[y][x] = new IridiumSprinkler(y, x);
				} catch (ArrayIndexOutOfBoundsException e) {break;}
				
				for (int i = -2; i < 3; i++)
					for (int j = -2; j < 3; j++)
						try {
						layout[y + i][x + j].setWatered(true);
						} catch (ArrayIndexOutOfBoundsException e) {;}
				break;
				
			case "Quality":
				try {
				layout[y][x] = new QualitySprinkler(y, x);
				} catch (ArrayIndexOutOfBoundsException e) {break;}
				for (int i =-1; i < 2; i++)
					for (int j =-1; j < 2; j++)
						try {
						layout[y + i][x + j].setWatered(true);
						} catch (ArrayIndexOutOfBoundsException e) {;}
				break;
				
			case "Normal":
				try {
				layout[y][x] = new NormalSprinkler(y, x);				
				} catch (ArrayIndexOutOfBoundsException e) {break;}
				try {
				layout[y - 1][x].setWatered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
				try {
				layout[y][x + 1].setWatered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
				try {
				layout[y][x - 1].setWatered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
				try {
				layout[y + 1][x].setWatered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
				try {
					layout[y][x].setWatered(true);
				} catch (ArrayIndexOutOfBoundsException e) {;}
				break;
		}
	}

	public int[] lookForNormalSprinkler() {
		for (int y = 1; y < layout.length - 1; y++) 
		{
			for (int x = 1; x < layout[0].length - 1; x++) 
			{
				if (layout[y][x-1].getType() == "None" &&
					layout[y + 1][x].getType() == "None" &&
					layout[y - 1][x].getType() == "None" &&
					layout[y][x + 1].getType() == "None" &&
					
					layout[y][x - 1].isCovered() &&
					layout[y + 1][x].isCovered() &&
					layout[y - 1][x].isCovered() &&
					layout[y][x + 1].isCovered() &&
					
					!layout[y][x - 1].isWatered() &&
					!layout[y + 1][x].isWatered() &&
					!layout[y - 1][x].isWatered() &&
					!layout[y][x + 1].isWatered()
				   )
				return new int[] {y, x};
			}
		
		}
		return new int[] {};
	}

	public int[] lookForQualitySprinkler() {
	for (int y = 1; y < layout.length - 1; y++) 
		for (int x = 1; x < layout[0].length - 1; x++) {
			if (
				layout[y][x].getType() == "None"  &&
					
				!layout[y + 1][x + 1].isWatered() &&
				layout[y + 1][x + 1].isCovered() &&
				layout[y + 1][x + 1].getType() == "None" && 
				
				!layout[y][x + 1].isWatered() &&
				layout[y][x + 1].isCovered() &&
				layout[y][x + 1].getType() == "None" && 
				
				!layout[y + 1][x].isWatered() &&
				layout[y + 1][x].isCovered() &&
				layout[y + 1][x].getType() == "None" && 
				
				!layout[y - 1][x - 1].isWatered() &&
				layout[y - 1][x - 1].isCovered() &&
				layout[y - 1][x - 1].getType() == "None" && 
				
				!layout[y][x - 1].isWatered() &&
				layout[y][x - 1].isCovered() &&
				layout[y][x - 1].getType() == "None" && 
				
				!layout[y - 1][x].isWatered() &&
				layout[y - 1][x].isCovered() &&
				layout[y - 1][x].getType() == "None" && 
				
				!layout[y - 1][x + 1].isWatered() &&
				layout[y - 1][x + 1].isCovered() &&
				layout[y - 1][x + 1].getType() == "None" && 
				
				!layout[y + 1][x - 1].isWatered() &&
				layout[y + 1][x - 1].isCovered() &&
				layout[y + 1][x - 1].getType() == "None"
				)return new int[] {y, x};
				
		}
		return new int[] {};			
	}
	
	public int[] lookForIridiumSprinkler() {
		for (int y = 2; y < layout.length - 2; y++) 
			for (int x = 2; x < layout[0].length - 2; x++) {
				if (
					layout[y][x].getType() == "None"  &&
						!layout[y -2][x -2].isWatered() && layout[y -2][x -2].isCovered() && layout[y -2][x -2].getType() == "None" && 
						!layout[y -2][x -1].isWatered() && layout[y -2][x -1].isCovered() && layout[y -2][x -1].getType() == "None" && 
						!layout[y -2][x].isWatered() && layout[y -2][x].isCovered() && layout[y -2][x].getType() == "None" && 
						!layout[y -2][x + 1].isWatered() && layout[y -2][x + 1].isCovered() && layout[y -2][x + 1].getType() == "None" && 
						!layout[y -2][x + 2].isWatered() && layout[y -2][x + 2].isCovered() && layout[y -2][x + 2].getType() == "None" && 
						!layout[y -1][x -2].isWatered() && layout[y -1][x -2].isCovered() && layout[y -1][x -2].getType() == "None" && 
						!layout[y -1][x -1].isWatered() && layout[y -1][x -1].isCovered() && layout[y -1][x -1].getType() == "None" && 
						!layout[y -1][x].isWatered() && layout[y -1][x].isCovered() && layout[y -1][x].getType() == "None" && 
						!layout[y -1][x + 1].isWatered() && layout[y -1][x + 1].isCovered() && layout[y -1][x + 1].getType() == "None" && 
						!layout[y -1][x + 2].isWatered() && layout[y -1][x + 2].isCovered() && layout[y -1][x + 2].getType() == "None" && 
						!layout[y][x -2].isWatered() && layout[y][x -2].isCovered() && layout[y][x -2].getType() == "None" && 
						!layout[y][x -1].isWatered() && layout[y][x -1].isCovered() && layout[y][x -1].getType() == "None" && 
						!layout[y][x].isWatered() && layout[y][x].isCovered() && layout[y][x].getType() == "None" && 
						!layout[y][x + 1].isWatered() && layout[y][x + 1].isCovered() && layout[y][x + 1].getType() == "None" && 
						!layout[y][x + 2].isWatered() && layout[y][x + 2].isCovered() && layout[y][x + 2].getType() == "None" && 
						!layout[y + 1][x -2].isWatered() && layout[y + 1][x -2].isCovered() && layout[y + 1][x -2].getType() == "None" && 
						!layout[y + 1][x -1].isWatered() && layout[y + 1][x -1].isCovered() && layout[y + 1][x -1].getType() == "None" && 
						!layout[y + 1][x].isWatered() && layout[y + 1][x].isCovered() && layout[y + 1][x].getType() == "None" && 
						!layout[y + 1][x + 1].isWatered() && layout[y + 1][x + 1].isCovered() && layout[y + 1][x + 1].getType() == "None" && 
						!layout[y + 1][x + 2].isWatered() && layout[y + 1][x + 2].isCovered() && layout[y + 1][x + 2].getType() == "None" && 
						!layout[y + 2][x -2].isWatered() && layout[y + 2][x -2].isCovered() && layout[y + 2][x -2].getType() == "None" && 
						!layout[y + 2][x -1].isWatered() && layout[y + 2][x -1].isCovered() && layout[y + 2][x -1].getType() == "None" && 
						!layout[y + 2][x].isWatered() && layout[y + 2][x].isCovered() && layout[y + 2][x].getType() == "None" && 
						!layout[y + 2][x + 1].isWatered() && layout[y + 2][x + 1].isCovered() && layout[y + 2][x + 1].getType() == "None" && 
						!layout[y + 2][x + 2].isWatered() && layout[y + 2][x + 2].isCovered() && layout[y + 2][x + 2].getType() == "None"

					)
					return new int[] {y, x};
			}
			return new int[] {};			
		}
		
	public void printMatrix() {
		System.out.print("  ");
		for (int i = 0; i < layout[0].length; i++)
			System.out.print(i % 10 + " ");
		System.out.println();
		
		for (int y = 0; y < layout.length; y++) {
			System.out.print(y % 10 + " ");

			for (int x = 0; x < layout[0].length; x++) {
				
				switch(layout[y][x].getType()) {
					case "Iridium":
						System.out.print("X ");
						break;
					case "Quality":
						System.out.print("X ");
						break;
					case "Normal":
						System.out.print("X ");
						break;
					
					case "Scarecrow":
						System.out.print("S ");
						break;

					case "None":
					if (layout[y][x].isWatered() && layout[y][x].isCovered())
						System.out.print("# ");
					else if (layout[y][x].isWatered())
						System.out.print("- ");
					else if (layout[y][x].isCovered())
						System.out.print("- ");
					else
						System.out.print("  ");
					
						break;
				}	
			}
			System.out.println();
		}	
	}
	
}
