package com.project.musicPlayer.jdbc;

import java.util.Scanner;

import com.project.musicPlayer.songOperation;

public class MultiPlayerJDBC {
	static SongOperationJdbc songOperation = new SongOperationJdbc();
	static Scanner sc = new Scanner(System.in);

	public static void menu() {
		
		
		boolean loop = true;

		while (loop) {

			System.out.println(
					"press choice\n1.To play song \n2.Add song\n3.Remove song\n4.Update Song\n5.Display All song \n6.Exit");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				songOperation.chooseSongToPlay();
				break;

			case 2:
				songOperation.addSong();
				break;
			case 3:
				songOperation.removeSongs();
				break;
			case 4:
				songOperation.updateSong();
				break;
			case 5:
				songOperation.displayAllSong();
				break;
			case 6:
				loop = false;
				System.out.println("Thank You");
				break;
			default:
				System.err.println("enter the valid number");
			}

		}
	}
	public static void main(String[] args) {
		System.err.println("WELCOME TO MULTIPLAYER....!!");
		    menu();
	}

}
