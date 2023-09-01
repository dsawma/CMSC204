
public class SocSecException extends Exception {
	public String error;
	
	public SocSecException(String error) {
		super( error);
		this.error =  "Invalid the social security number," + error;
	}
}
