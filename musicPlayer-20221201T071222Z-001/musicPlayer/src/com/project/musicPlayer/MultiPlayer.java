package com.project.musicPlayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MultiPlayer {
	static songOperation songOperation = new songOperation();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.err.println("WELCOME TO MULTIPLAYER....!!");
		DateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date now=new Date();
		System.out.print("DATE :");
		System.out.println(sdf.format(now));
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
				songOperation.removeSong();
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
}
