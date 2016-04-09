package eg.edu.guc.yugioh.exceptions;

public class UnknownSpellCardException extends UnexpectedFormatException {

	private String unknownSpell ;
	
	public UnknownSpellCardException(String m , int n , String s){
		super(m,n);
		unknownSpell = s ;
	}

	public String getUnknownSpell() {
		return unknownSpell;
	}

	public void setUnknownSpell(String unknownSpell) {
		this.unknownSpell = unknownSpell;
	}
}
