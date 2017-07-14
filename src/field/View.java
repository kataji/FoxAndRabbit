package field;

import javax.swing.JPanel;

import cell.Cell;

import java.awt.Dimension;
import java.awt.Graphics;


public class View extends JPanel {
	//这个View只管表现
	
	private static final long serialVersionUID = -4210050542073980171L;
	//这个UID可以在其他东西写完后再搞
	//按那个warning消息，选自动生成
	
	private Field field;
	private static final int GRID_SIZE = 16;
	
	public View(Field field){
		this.field = field;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int i=0; i<field.getHeight(); i++){
			for(int j=0; j<field.getWidth(); j++){
				Cell cell = field.get(i, j);
				if(cell != null){
					cell.draw(g, j*GRID_SIZE, i*GRID_SIZE, GRID_SIZE);
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(field.getWidth()*GRID_SIZE+1, field.getHeight()*GRID_SIZE+1);
	}
	
	
}
