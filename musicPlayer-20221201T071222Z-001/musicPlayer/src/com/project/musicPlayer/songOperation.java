package com.project.musicPlayer;

import java.util.ArrayList;
import java.util.Scanner;

public class songOperation {
	static ArrayList<Song> arrayList = new ArrayList<Song>();
	static Scanner sc = new Scanner(System.in);
	static Song song;

	public void chooseSongToPlay() {
    	if (arrayList.isEmpty()) {
    		System.out.println("Song are not available..please Add Song First");
    	}
    	else {
    		System.out.println("press the choice\n 1. play Specific song \n 2 play All song\n 3 play Random Song");
    		int choice =sc.nextInt();
    		switch(choice) {
    		case 1: showAllSong();
    		System.out.println("enter the id of the song to play");
    		 int input=sc.nextInt();
    		 for(int j=0;j<arrayList.size();j++) {
    			 if(arrayList.get(j).getID()==input) {
    				 
    				 System.err.println( arrayList.get(j).getSongName()+" is now playing ");
    			 }
    		 }
    		 break;
    		case 2:
    	    System.out.println("Following Song are plying now");
    		   for(int p=0;p<arrayList.size();p++) {
    			   System.out.println(arrayList.get(p).getSongName()+" is now playing\n\n");
    		   }
    		   break;
    		case 3:
    			int i=(int) Math.random()+1;
    			for(int r=0;r<arrayList.size();r++) {
    				if(arrayList.get(i).getID()==i) {
    					System.out.println(arrayList.get(i).getSongName()+ "is now playing...(randomly");
    				}
    			}
    			break;
    		 default:{
    		 System.out.println("Oops... envalid choice, please enter valid choice");}
    			
    	   System.out.println("-----------------------------------------------------------------------------------------");
    		}
    		
    				
    	}
    }

	public void showAllSong() {
		if (arrayList.isEmpty()) {
			System.out.println("There is no song to play....please add song first");
		} else {
			System.out.println("The following Song are present inside the list...");
			for (int k = 0; k < arrayList.size(); k++) {
				System.out.println(arrayList.get(k));
			}
		}
		System.out.println("\n\n-----------------------------------------------------------------------------");

	}

	public void addSong() {
		System.out.println("please enter the number of song you wish to add");
		int no = sc.nextInt();
		for (int i = 0; i < no; i++) {
			song = new Song();
			System.out.println("enter the song id");
			song.setID(sc.nextInt());
			System.out.println("enter the song name");
			song.setSongName(sc.next());
			System.out.println("enter the movies name");
			song.setMoviesName(sc.next());
			System.out.println("enter the Singer name");
			song.setSingerName(sc.next());
			System.out.println("enter the composer");
			song.setComposer(sc.next());
			System.out.println("enter the lyriscist");
			song.setLyriscist(sc.next());
			System.out.println("enter the length");
			song.setLength(sc.nextDouble());
			arrayList.add(song);
			System.out.println("Song added Successfully....");
			System.out.println("--------------------------------------------------------------------------------------------");
		}
	}

	public void removeSong() {
		showAllSong();
		System.out.println("enter the id of song you want to delete");
		int ids=sc.nextInt();
		for(int i=0;i<arrayList.size();i++) {
			if(arrayList.get(i).getID()==ids) {
				arrayList.remove(i);
				System.err.println("song remove successfully...!!");
			}
		}
		
		
	}

	public void updateSong() {
		song=new Song();
		showAllSong();
		System.out.println("enter the id of song wish you want to update");
		int ids=sc.nextInt();
		for(int i=0;i<arrayList.size();i++) {
			if(arrayList.get(i).getID()==ids) {
			 System.out.print("enter new id :");
			 arrayList.get(i).setID(sc.nextInt());
			 System.out.print("Enter song name :");
			 arrayList.get(i).setSongName(sc.next());
			 System.out.print("Enter Singer name : ");
			 arrayList.get(i).setSingerName(sc.next());
			 System.out.print("Enter Movies name :");
			 arrayList.get(i).setMoviesName(sc.next());
			 System.out.print("enter composer name :");
			 arrayList.get(i).setComposer(sc.next());
			 System.out.print("Enter lyricit name :");
			 arrayList.get(i).setLyriscist(sc.next());
			 System.out.print("Enter the duration");
			 arrayList.get(i).setLength(sc.nextDouble());
			 System.err.println("song updated successfully");
			}
		}
		
	}

	public void displayAllSong() {
		if(arrayList.isEmpty()) {
			System.out.println("No Song are Available....!!\n-------------------------------------------------------------------------------");
		}
		else
		for(int i=0;i<arrayList.size();i++) {
			System.out.println(arrayList.get(i));  
		}
		
	}
}
