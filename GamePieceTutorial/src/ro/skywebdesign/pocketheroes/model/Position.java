package ro.skywebdesign.pocketheroes.model;

public class Position {

	private int col;
	private int row;
	
	public Position(int row, int col) {
		super();
		this.col = col;
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}

	public static Position clone(Position oldPosition) {
		return new Position(oldPosition.getRow(), oldPosition.getCol());
	}

	@Override
	public String toString() {
		return "Position [col=" + col + ", row=" + row + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	
}
