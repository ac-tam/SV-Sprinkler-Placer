package window;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import items.*;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		super("Stardew");
		add(new MainPanel());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		setBounds(400, 400, 240, 300);
		setResizable(false);
		setVisible(true);
	}
		
	public MainFrame(Tile[][] farm) {
			super("Stardew");
			MainPanel myPanel = new MainPanel(farm);
			
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
			
			JScrollPane scr = new JScrollPane(myPanel);
			scr.setPreferredSize(new Dimension(750, 750));
			scr.getVerticalScrollBar().setUnitIncrement(100);
			scr.getHorizontalScrollBar().setUnitIncrement(100);

			KeyAdapter key = new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					switch (e.getKeyChar()) {
					case 'w':
						scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getValue() - 200);
						System.out.println('w');
						break; 
					case 'd':
						scr.getHorizontalScrollBar().setValue(scr.getHorizontalScrollBar().getValue() + 200);
						System.out.println('d');
						break;
					case 'c':
						scr.getHorizontalScrollBar().setValue(scr.getHorizontalScrollBar().getValue() + 200);
						scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getValue() + 200);
						System.out.println('c');
						break;
					case 'q':
						scr.getHorizontalScrollBar().setValue(scr.getHorizontalScrollBar().getValue() - 200);
						scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getValue() - 200);
						System.out.println('q');
						break;
					case 'e':
						scr.getHorizontalScrollBar().setValue(scr.getHorizontalScrollBar().getValue() + 200);
						scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getValue() - 200);
						System.out.println('e');
						break;
					case 'z':
						scr.getHorizontalScrollBar().setValue(scr.getHorizontalScrollBar().getValue() - 200);
						scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getValue() + 200);
						System.out.println('z');
						break;
					case 's':
						scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getValue() + 200);
						System.out.println('s');

						break;
					case 'a':
						scr.getHorizontalScrollBar().setValue(scr.getHorizontalScrollBar().getValue() - 200);
						System.out.println('a');

						break;
					case 'x':
						scr.getVerticalScrollBar().setValue(0);
						scr.getHorizontalScrollBar().setValue(0);
						break;
					}
				}
			};
			addKeyListener(key);
			add(scr);
			setBounds(700, 100 , 700, 750);
			setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
		
	}
}
