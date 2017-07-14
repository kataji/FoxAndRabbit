package field;

import java.util.ArrayList;

import cell.Cell;

public class Field {
	//这个Field只管数据
	
	private Cell[][] field;
	private int width;
	private int height;
	
	public Field(int width, int height){
		field = new Cell[height][width];
		this.width = width;
		this.height = height;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Cell place(int row, int col, Cell cell){
		field[row][col] = cell;
		return field[row][col];
	}
	
	public Cell get(int row, int col){
		return field[row][col];
	}

	public Cell[] getNeighbors(int row, int col){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		for(int i = -1; i < 2; i++)
			for(int j = -1; j < 2; j++){
				int r = row+i;
				int c = col+j;
				if(r>-1 && r<height && c>-1 && c<width && (r!=row || c!=col)){
					//上次错在了这最后一个条件
					neighbors.add(field[r][c]);
				}
			}
		return neighbors.toArray(new Cell[neighbors.size()]);
		//这个toArray()里面的参数设置了出来的Array的类型
	}
	
	public void clear(){
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++){
				field[i][j] = null;
			}
	}
}
