/*
 * @author David Sawma
 * CMSC 204 23393
 */
public class QueueUnderflowException extends Exception{
	public QueueUnderflowException() {
		super("Empty Queue");
	}

}
