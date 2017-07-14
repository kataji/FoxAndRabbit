package cellmachine;

import javax.swing.JFrame;

import cell.Cell;
import field.Field;
import field.View;

public class CellMachine {
	private Field field;
	private View view;
	
	public CellMachine(int size){
		field = new Field(size, size);
		for(int i=0; i<field.getHeight(); i++){
			for(int j=0; j<field.getWidth(); j++){
				field.place(i, j, new Cell());
			}
		}
		
		for(int i=0; i<field.getHeight(); i++){
			for(int j=0; j<field.getWidth(); j++){
				if(Math.random() < 0.2){
					field.get(i, j).reborn();
				}
			}
		}
		
		view = new View(field);
		JFrame frame = new JFrame();
		frame.setTitle("CELL MACHINE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(view);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void start(int steps){
		for(int i=0; i<steps; i++){
			step();			
			System.out.println("UPDATE " + i);
			view.repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void step(){
		for(int i=0; i<field.getHeight(); i++){
			for(int j=0; j<field.getWidth(); j++){
				Cell cell = field.get(i, j);
				int aliveCount = countAliveNeighbors(field.getNeighbors(i, j));
				
				System.out.print("["+i+"]["+j+"]: ");
				System.out.print(cell.isAlive()? "alive" : "dead");
				System.out.print(". " + aliveCount + " ==> ");
				
				if(cell.isAlive()){
					if(aliveCount<2 || aliveCount>3){
						cell.die();
						System.out.print("die");
					}
				}
				else if(aliveCount == 3){
					cell.reborn();
					System.out.print("reborn");
				}
				System.out.println();
			}
		}
	}
	
	private int countAliveNeighbors(Cell[] neighbors){
		int count = 0;
		for(Cell c : neighbors){
			if(c.isAlive()){
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		CellMachine cm = new CellMachine(30);
		cm.start(1000);
	}

}
