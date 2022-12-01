package com.project.musicPlayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Song {
     private int ID;
     private String songName;
     private String moviesName;
     private String singerName;
     private String composer;
     private String lyriscist;
     private double length;
	
} 
