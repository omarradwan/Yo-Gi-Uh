package eg.edu.guc.yugioh.exceptions;

public class EmptyFieldException extends UnexpectedFormatException {

	
	private int sourceField ; 
	
	public EmptyFieldException(String m , int n , int l){
		super(m,n);
		sourceField = l ;
	}
	
	public int getSourceField() {
		return sourceField;
	}
	public void setSourceField(int sourceField) {
		this.sourceField = sourceField;
	}
}
