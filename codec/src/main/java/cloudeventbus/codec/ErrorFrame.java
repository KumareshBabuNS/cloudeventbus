package cloudeventbus.codec;

/**
 * @author Mike Heath <elcapo@gmail.com>
 */
public class ErrorFrame {

	private final String message;

	public ErrorFrame() {
		this(null);
	}

	public ErrorFrame(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}