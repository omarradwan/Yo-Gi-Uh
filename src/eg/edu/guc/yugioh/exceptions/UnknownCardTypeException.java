package eg.edu.guc.yugioh.exceptions;

public class UnknownCardTypeException extends UnexpectedFormatException {
	
	private String unknownType ;
	public UnknownCardTypeException(String m , int n , String s ){
		super(m,n);
		unknownType = s ;
	}
	public String getUnknownType() {
		return unknownType;
	}
	public void setUnknownType(String unknownType) {
		this.unknownType = unknownType;
	}
}
