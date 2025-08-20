package hw1;

import java.util.Scanner;

/**
 * The <code>PlaylistOperations</code> class tests the methods within the Playlist
 * class. It allows users to perform operations on the class.
 * 
 * @author vashma raja
 *    email: vashma.raja@stonybrook.edu
 *    Stony Brook ID: 115417681
 *    Recitation: CSE214R.30
 */
public class PlaylistOperations {
	private static Playlist playlist = new Playlist();
	private static Scanner scanner = new Scanner(System.in);
	
/**
 * Gives all the options the user can select.
 */
	public static void main(String[] args) {
		while (true) {
			menu();
			System.out.print("\nSelect a menu option: ");
			String options = scanner.nextLine().trim().toUpperCase();
			
			switch (options) {
			case "A": addSong(); break;
			case "B": printSongsByArtist(); break;
			case "G": getSong(); break;
			case "R": removeSong(); break;
			case "P": printAllSongs(playlist, ""); break;
			case "S": System.out.println("\nThere are " + playlist.size() + " song(s) in the current playlist."); break;
			case "Q": System.out.println("\nProgram terminating normally..."); return;
			default: System.out.println("Invalid option");
			}
		}
	}
		
/** 
 * The addSong method asks the user to input information about the song
 * they are adding. 
 */
	public static void addSong() {
		try {
			System.out.print("\nEnter the song title: ");
			String title = scanner.nextLine();
			
			System.out.print("Enter the song artist: ");
			String artist = scanner.nextLine();
			
			System.out.print("Enter the song length (minutes): ");
			int minutes = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Enter the song length (seconds): ");
			int seconds = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Enter the position: ");
			int position = Integer.parseInt(scanner.nextLine());
			
			SongRecord song = new SongRecord();
			song.setSongTitle(title);
			song.setSongArtist(artist);
			try {
				song.setSongMinutes(minutes);
				song.setSongSeconds(seconds);
			} catch (IllegalArgumentException e) {
				System.out.println("\n" + e.getMessage());
				return;
			}
			
			playlist.addSong(song,  position);
			System.out.printf("\nSong Added: %s By %s\n",  title, artist);
		} catch (IllegalArgumentException e) {
			System.out.println("\n" + e.getMessage());
		} catch (FullPlaylistException e) {
			System.out.println("\n" + e.getMessage());
		} catch (Exception e) {
			System.out.println("\nError: Invalid input");
		}
	}
	
/** 
 * Prints the songs inputed by the user by the same artist. 
 */
	public static void printSongsByArtist() {
		System.out.print("\nEnter the artist: ");
		String artist = scanner.nextLine();
		Playlist sameArtist = Playlist.getSongsByArtist(playlist,  artist);
		
		printAllSongs(sameArtist, "");
		
	}
		
/** 
 * Gets the songs when the user enters the position of that song from the play list.
 */
	public static void getSong() {
		try {
			System.out.print("Enter the position: ");
			int position = Integer.parseInt(scanner.nextLine());
			SongRecord song = playlist.getSong(position);
			
			printAllSongs(new Playlist() {{
				try {
					addSong(song, 1);
				} catch (Exception e) {} }}, "");
		} catch (Exception e) {
			System.out.println("\nError: " + e.getMessage());
		}
	}
	
/** 
 * Removes a song from the play list when a user enters the songs
 * position number.
 */
	public static void removeSong() {
		try {
			System.out.print("\nEnter the position: ");
			int position = Integer.parseInt(scanner.nextLine());
			
			playlist.removeSong(position);
			System.out.printf("Song Removed at position %d\n", position);
		} catch (IllegalArgumentException e) {
			System.out.println("\n" + e.getMessage());
		} catch (Exception e) {
			System.out.printf("\nError: Invalid input");
		}
	}
	
/** 
* Prints all the songs in the play list.
*/
	private static void printAllSongs(Playlist pl, String header) {
		System.out.println("\n" + header);
		System.out.println("Song#     Title           Artist          Length"); 
	    System.out.println("------------------------------------------------");
	    
	    if (pl ==null || pl.size() == 0) {
	    	System.out.println();
	    	return;
	    }
	    
	    for (int i = 1; i <= pl.size(); i++) {
	    	SongRecord song = pl.getSong(i);
	    	System.out.printf("%-10d%-17s%-16s%d:%02d\n", i, song.getSongTitle(), song.getSongArtist(),
	    			song.getSongMinutes(), song.getSongSeconds());
	    }
	    System.out.println();
	}
	
/** 
 * Prints the menu options for the user to select.
 */
	public static void menu() {
		System.out.println("\nA) Add Song");
		System.out.println("B) Print Songs by Artist");
		System.out.println("G) Get Song");
		System.out.println("R) Remove Song");
		System.out.println("P) Print All Songs");
		System.out.println("S) Size");
		System.out.println("Q) Quit");	
	}
}