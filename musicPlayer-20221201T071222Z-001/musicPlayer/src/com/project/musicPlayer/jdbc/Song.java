package com.project.musicPlayer.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Song {
	private int id;
	private String songName;
	private String moviesName;
	private double duration;
	
	

}
