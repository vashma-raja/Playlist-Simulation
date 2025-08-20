package hw1;

import java.util.Objects;

/**
 * The <code>Playlist</code> class contains and ADT for a play list of audio files.
 * It supports common operations on such lists of audio files.
 * 
 * @author vashma raja
 *    email: vashma.raja@stonybrook.edu
 *    Stony Brook ID: 115417681
 *    Recitation: CSE214R.30
 */
public class Playlist implements Cloneable {
	public static final int MAX_SONGS = 50;
	private SongRecord[] songs;
	private int size = 0;
	
/**
 * Counts the number of SongRecords currently in this Playlist.
 * @return The number of SongRecords in this Playlist.
 */
	public int size() {
		return this.size;
	}
	
/** 
 * Creates an instance of the Playlist class.
 * Playlist is initialized to an empty list.
 */
	public Playlist() {
		songs = new SongRecord[MAX_SONGS];
		size = 0;
	}
	
/**
 * Creates a copy of this Playlist.
 * @return A copy of this Playlist such that it does not affect 
 * the original version.
 */
	@Override
	public Object clone() {
		Playlist playlistClone = new Playlist();
		playlistClone.size = this.size;
		for (int i = 0; i < this.size; i++) {
			SongRecord original = this.songs[i];
			SongRecord copy = new SongRecord();
			copy.setSongTitle(original.getSongTitle());
			copy.setSongArtist(original.getSongArtist());
			copy.setSongMinutes(original.getSongMinutes());
			copy.setSongSeconds(original.getSongSeconds());
			playlistClone.songs[i] = copy;
		}
	return playlistClone;
	}
	
/**
 * Compares the Playlist to another object.
 * @param obj is an object the Playlist is compared to.
 * Checks to see if the songs are in the same sequence.
 * Compares the objects of the SongRecord to make sure title, artist, minutes,
 * and seconds match.
 */
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Playlist other = (Playlist) obj;
		if (size != other.size) return false;
		for (int i = 0; i < size; i++) {
			SongRecord s1 = songs[i];
			SongRecord s2 = other.songs[i];
			if (!s1.getSongTitle().equals(s2.getSongTitle()) ||
			   !s1.getSongArtist().equals(s2.getSongArtist()) ||
			   s1.getSongMinutes() != s2.getSongMinutes() || 
			   s1.getSongSeconds() != s2.getSongSeconds()) {
			   return false;
			}
		}
		return true;
	}
	
/**
 * 
 * @param song is the new SongRecord object to add.
 * @param position is where in the playlist the song will be added.
 * @throws IllegalArgumentException if the position is not within the valid range,
 * @throws FullPlaylistException if there is no more room inside the playlist
 * to add another SongRecord object.
 */
	public void addSong(SongRecord song, int position) 
			throws IllegalArgumentException, FullPlaylistException {
		
		if (position < 1 || position > size + 1) {
			throw new IllegalArgumentException("Invalid position for adding the new song. ");
		}
		if (size >= MAX_SONGS) {
			throw new FullPlaylistException("No more room inside of the Playlist. ");
		}
		
		for (int i = size; i >= position; i--) {
			songs[i] = songs[i - 1];
		}
		songs[position - 1] = song;
		size++;
	}
	
/**
 * Removes the song at a specific position in the playlist.
 * @param position is the position where the song will be removed from.
 * @throws IllegalArgumentException if the position is not within the valid range.
 * When a song is removed, all the songs after that removed song move up one position,
 * for example if we have songs in positions 1-5, and the song in position 3 is removed,
 * then song in position 4 goes to position 3, and song in position 5 got to postion 4.
 */
	public void removeSong(int position) 
		throws IllegalArgumentException {
		if (position < 1 ||  position > size) {
            throw new IllegalArgumentException("No song at position " + position + " to remove. ");
        }

        for (int i = position - 1; i < size - 1; i++) {
            songs[i] = songs[i + 1];
        }
        songs[size - 1] = null;
        size--;
    }

/**
 * Gets the SongRecord at the given position in this playlist.
 * @param position is the position we want to retrieve.
 * @return the SongRecord at the specified position.
 * @throws IllegalArgumentException if position is not within valid range.
 */
    public SongRecord getSong(int position) 
    	throws IllegalArgumentException {
        if (position < 1 || position > size) {
            throw new IllegalArgumentException("Position is not within the valid range.");
        }
        return songs[position - 1];
    }

/**
 * Prints a formatted table of each SongRecord
 * Format is shown in the sample output.
 */
    public void printAllSongs() {
    	// Finds maximum lengths for the columns
    	int maxTitle = "Title".length();
    	int maxArtist = "Artist".length();
    	for (int i = 0; i < size; i++) {
    	    maxTitle = Math.max(maxTitle, songs[i].getSongTitle().length());
    	    maxArtist = Math.max(maxArtist, songs[i].getSongArtist().length());
    	    }

    	// Creating the header 
    	System.out.printf("%-6s %-"+maxTitle+"s %-"+maxArtist+"s %s%n", 
    	"Song#", "Title", "Artist", "Length");
    	System.out.println(new String(new char[13+maxTitle+maxArtist]).replace('\0', '-'));

    	for (int i = 0; i < size; i++) {
    	SongRecord s = songs[i];
    	System.out.printf("%-6d %-"+maxTitle+"s %-"+maxArtist+"s %d:%02d%n",
    	i+1, s.getSongTitle(), s.getSongArtist(), s.getSongMinutes(), s.getSongSeconds());
    	}
    }

/**
 * Creates a new Playlist containing all SongRecords in the original Playlist
 * from the same artist.
 * @param artist is the name of the artist.
 * @return A new Playlist object containing all matching SongRecords.
 */
    public static Playlist getSongsByArtist(Playlist originalList, String artist) {
        if (originalList == null || artist == null) {
            return null;
        }

        Playlist result = new Playlist();
        
        for (int i = 1; i <= originalList.size(); i++) {
            SongRecord currentSong = originalList.getSong(i);
            
        if (artist.equals(currentSong.getSongArtist())) {
        	try { 
        		SongRecord copy = new SongRecord();
        		copy.setSongTitle(currentSong.getSongTitle());
        		copy.setSongArtist(currentSong.getSongArtist());
        		copy.setSongMinutes(currentSong.getSongMinutes());
        		copy.setSongSeconds(currentSong.getSongSeconds());
        		result.addSong(copy,  result.size() + 1);
        	} catch (FullPlaylistException e) {
        		break;
        	}	
         }
      }
      
        return result;
    }

/**
 * Gets the String representation of this Playlist object.
 * @return the String representation of this Playlist object.
 */
    public String toString() {
        if (size == 0) return "Playlist is empty. ";
        
      //Adjusted column width to match sample output
      int maxTitle = "Title".length();
      int maxArtist = "Artist".length();
      for (int i = 0; i < size; i++) {
    	  maxTitle = Math.max(maxTitle, songs[i].getSongTitle().length());
    	  maxArtist = Math.max(maxArtist, songs[i].getSongArtist().length());
      }
      
      String header = String.format("%-6s %-" +maxTitle+ "s %-"+maxArtist+"s %s",
    		  "Song#", "Title", "Artist", "Length");        
       String divider = new String(new char[13 + maxTitle + maxArtist]).replace('\0', '-');
       
       StringBuilder sb = new StringBuilder();
       sb.append(header).append("\n").append(divider).append("\n");
       for (int i = 0; i < size; i ++) {
    	   SongRecord s = songs[i];
    	   sb.append(String.format("%-6d %-"+maxTitle+"s %-"+maxArtist+"s %d:%02d\n",
    			   i+1, s.getSongTitle(), s.getSongArtist(),
    			   s.getSongMinutes(), s.getSongSeconds()));
        }
        return sb.toString();
    } 
}