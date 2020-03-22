package window;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import items.IridiumSprinkler;
import items.NormalSprinkler;
import items.QualitySprinkler;
import items.Tile;

public class MainPanel extends JPanel{
	private BufferedImage iridium;
	private BufferedImage normal;
	private BufferedImage quality;
	private BufferedImage tile;
	private BufferedImage scarecrow;
	private BufferedImage watered;
	private BufferedImage scarecrowed;
	private BufferedImage both;

	private int sideLength = 0;
	private JLabel pic;
	
	public BufferedImage loadTheImage(String path) throws IOException {
		return ImageIO.read(getClass().getResourceAsStream(path)); 
	}
	
	public void loadSmallImages() {
		sideLength = 12;
		try { 
			File im = new File("C:/Users/atam2/eclipse-workspace/Stardew Placer/images/small_iridium.png");
			iridium = ImageIO.read(im); 
			im = new File("C:/Users/atam2/eclipse-workspace/Stardew Placer/images/small_tile.png");
			tile = ImageIO.read(im); 
			im = new File("C:/Users/atam2/eclipse-workspace/Stardew Placer/images/small_ scarecrow.png");
			scarecrow = ImageIO.read(im); 
			im = new File("C:/Users/atam2/eclipse-workspace/Stardew Placer/images/small_normal.png");
			normal = ImageIO.read(im); 
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	public void loadImages() {
		sideLength = 24;
		try { 
			File im = new File("C:/Users/atam2/eclipse-workspace/Stardew Placer/images/iridium.png");
			iridium = ImageIO.read(im); 
			im = new File("C:/Users/atam2/eclipse-workspace/Stardew Placer/images/tile.png");
			tile = ImageIO.read(im); 
			im = new File("C:/Users/atam2/eclipse-workspace/Stardew Placer/images/Scarecrow.png");
			scarecrow = ImageIO.read(im); 
			im = new File("C:/Users/atam2/eclipse-workspace/Stardew Placer/images/normal.png");
			normal = ImageIO.read(im); 
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		}	
	}
	public void loadBigImages() { 
		sideLength = 48;
		try { 
			iridium = loadTheImage("iridium.png"); 
			scarecrow = loadTheImage("scarecrow.png"); 
			watered = loadTheImage("watered.png"); 
			scarecrowed = loadTheImage("scarecrowed.png"); 
			both = loadTheImage("both.png"); 
			normal = loadTheImage("normal.png"); 
			quality = loadTheImage("quality.png");
			tile = loadTheImage("tile.png"); 
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	public int getSideLength() {return sideLength;}
	public void addImage(int y, int x, BufferedImage what) {
		pic = new JLabel(new ImageIcon(what));
		pic.setBounds(x, y, sideLength, sideLength);
		add(pic);
	}
	
	public MainPanel(Tile[][] farm) { 	
		setLayout(null);
		//if (farm.length > 80 || farm[0].length > 80)
			//loadSmallImages();
		//else if (farm.length > 20 || farm[0].length > 20)
			//loadImages();
		//else
			loadBigImages();
		setPreferredSize(new Dimension(farm.length * sideLength, farm[0].length * sideLength));
		//System.out.println(new Dimension(farm.length * sideLength, farm[0].length * sideLength));
		for (int y = 0; y < farm.length; y++)
			for (int x = 0; x < farm[0].length; x++)
				switch (farm[y][x].getType()) {
					case "Iridium":
						//System.out.println(farm.length);
						addImage(y * sideLength , x * sideLength, iridium);
						break;
					case "Normal":
						//System.out.println(farm.length);
						addImage(y * sideLength , x * sideLength, normal);
						break;
					case "Quality":
						addImage(y * sideLength , x * sideLength, quality);
						break;
					case "Scarecrow":
						addImage(y * sideLength , x * sideLength, scarecrow);
						break;
					case "None":
						if (farm[y][x].isWatered() && farm[y][x].isCovered())
							addImage(y * sideLength , x * sideLength, both);	
						else if (farm[y][x].isCovered())
							addImage(y * sideLength , x * sideLength, scarecrowed);	
						else if (farm[y][x].isWatered())
							addImage(y * sideLength , x * sideLength, watered);	
						else 	
							addImage(y * sideLength , x * sideLength, tile);	
						break;
				}
	}
	JSpinner iridiumIO, qualityIO, normalIO;
	public MainPanel() {
		setLayout(null);
		
		JButton generate = new JButton("Generate");
		generate.setBounds(67, 232, 100, 23);
		generate.setBackground(Color.WHITE);
		generate.setForeground(Color.BLACK);
		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Farm river = new Farm(29, 29);
				int[] a;		
				int iridium, quality, normal;
				iridium = (int) iridiumIO.getValue();
				quality = (int) qualityIO.getValue();
				normal =  (int) normalIO.getValue();
				river.scarecrow(false, 7, 7);				
				river.scarecrow(false, 21, 7);
				river.scarecrow(false, 7, 21);
				river.scarecrow(false, 21, 21);

				for (int j = 1; j < iridium + 1; j++) {
					a = river.lookForIridiumSprinkler();
					try {
					river.water(new IridiumSprinkler(1, 2),  a[0], a[1]);
					} catch (ArrayIndexOutOfBoundsException e1) {;}
				}
				
				for (int j = 1; j < quality + 1; j++) {
					a = river.lookForQualitySprinkler();
					try {
					river.water(new QualitySprinkler(1, 2),  a[0], a[1]);
					} catch (ArrayIndexOutOfBoundsException e1) {;}
				}
				
				for (int j = 1; j < normal + 1; j++) {
					a = river.lookForNormalSprinkler();
					try {
					river.water(new NormalSprinkler(1, 2),  a[0], a[1]);
					} catch (ArrayIndexOutOfBoundsException e1) {;}
				}
				new MainFrame(river.getLayout());
			}
		}  );
		add(generate);

		iridiumIO = new JSpinner(); 
		qualityIO = new JSpinner(); 
		normalIO = new JSpinner();
		
		iridiumIO.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if ((int) iridiumIO.getValue() < 0 || (int) iridiumIO.getValue() > 1000) 
                	iridiumIO.setValue(0);
            }
        });
		qualityIO.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if ((int) qualityIO.getValue() < 0 || (int) qualityIO.getValue() > 10000) 
                	qualityIO.setValue(0);
            }
        });
		normalIO.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if ((int) normalIO.getValue() < 0 || (int) normalIO.getValue() > 10000) 
                	normalIO.setValue(0);
            }
        });
		
		normalIO.setBounds(8, 174, 50, 20);
		qualityIO.setBounds(87, 174, 50, 20);
		iridiumIO.setBounds(166, 174, 50, 20);

		add(iridiumIO);
		add(qualityIO);
		add(normalIO);
		
		JLabel ir, qu, ns, logo;
		try {
			ir = new JLabel(new ImageIcon(loadTheImage("Iridium_Sprinkler.png")));
			ir.setBounds(166, 113, 48, 48);

			add(ir);
		} catch (IOException e) { e.printStackTrace();}
		try {
			qu = new JLabel(new ImageIcon(loadTheImage("Quality_Sprinkler.png")));
			qu.setBounds(89, 115, 48, 48);
			add(qu);
		} catch (IOException e) { e.printStackTrace(); }
		try {
			ns = new JLabel(new ImageIcon(loadTheImage("Sprinkler.png")));
			
			ns.setBounds(10, 112, 48, 48);add(ns);
		} catch (IOException e) { e.printStackTrace(); }
		try {
			logo = new JLabel(new ImageIcon(loadTheImage("stardew.png")));

			logo.setBounds(59, 23, 114, 75);
			add(logo);
		} catch (IOException e) { e.printStackTrace(); }
	}
}