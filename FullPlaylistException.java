package hw1;

/**
 * The <code>FullPlaylistException</code> handles the error when the user
 * tried to add a song to the playlist when the playlist has reached 
 * maximum capacity.
 * 
 * @author vashma raja
 *    email: vashma.raja@stonybrook.edu
 *    Stony Brook ID: 115417681
 *    Recitation: CSE214R.30
 */
public class FullPlaylistException extends Exception {
	public FullPlaylistException() {
		super();
	}
	
	public FullPlaylistException(String message) {
		super(message);
	}
}