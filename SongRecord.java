package hw1;

/**
 * The <code>SongRecord</code> class contains information about an audio file. 
 * There are accessor and mutator methods for each variable in the SongRecord.
 * This includes the song's title, artist, minute length, and second length.
 * 
 * @author vashma raja
 *    email: vashma.raja@stonybrook.edu
 *    Stony Brook ID: 115417681
 *    Recitation: CSE214R.30
 */
public class SongRecord {
	
	private String songTitle; 
	private String songArtist;
	private int songMinutes;
	private int songSeconds;
	
/**
 * Gets the song title.
 * @return The song title as a String
 */
	public String getSongTitle() {
		return songTitle;
	}
	
/**
 * Gets the song artist.
 * @return The song artist as a String.
 */
	public String getSongArtist() {
		return songArtist;
	}
	
/**
 * Gets the song minutes.
 * @return The song minutes as an int.
 */
	public int getSongMinutes() {
		return songMinutes;
	}
	
/**
 * Gets the song seconds.
 * @return The song seconds as an int.
 */
	public int getSongSeconds() {
		return songSeconds;
	}
	
/** 
 * Sets the song title.
 * @param title The new song title (can't be null)
 */
	public void setSongTitle(String title) {
		this.songTitle = title;	
	}
	
/**
 * Sets the song artist.
 * @param artist The new song artist (can't be null)
 */
	public void setSongArtist(String artist) {
		this.songArtist = artist;
	}
	
/**
 * Sets the song's minutes length.
 * @param minutes The new songs minute's value.
 * @throws IllegalArgumentException if the minutes value is negative.
 */
	public void setSongMinutes(int minutes) {
		if (minutes < 0) {
			throw new IllegalArgumentException("Invalid song length.");
		}
		this.songMinutes = minutes; 
	}
	
/**
 * Sets the songs seconds length.
 * @param seconds The new song's seconds value 
 * @throws IllegalArgumentException if seconds value is not between 0 and 59.
 */
	public void setSongSeconds(int seconds) {
		if (seconds < 0 || seconds > 59) {
			throw new IllegalArgumentException("Invalid song length");
		}
		this.songSeconds = seconds;
	}
	
/** 
 * Creates a new SongRecord with default values.
 * There are empty Strings for the title and artist. 
 * The song minutes and seconds are default at 0.
 */
	public SongRecord() {
		this.songTitle = "";
		this.songArtist = "";
		this.songMinutes = 0;
		this.songSeconds = 0; 
	}
	
/** 
 * Returns a String representation of the new song in a specific format.
 * @return String in the format: "Song Added: [Title] by [Artist]".
 */
	public String toString() {
		return "Song Added: " + songTitle + " by " + songArtist;
	}
}