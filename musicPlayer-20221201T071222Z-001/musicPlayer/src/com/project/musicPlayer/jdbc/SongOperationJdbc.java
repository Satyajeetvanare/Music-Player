package com.project.musicPlayer.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class SongOperationJdbc {
	static Connection connection;
	static PreparedStatement preparedStatement;
	static ResultSet resultSet;
	static int resultInt;
	static FileReader fileReader;
	static Scanner sc = new Scanner(System.in);
	static Properties properties = new Properties();
	static String query;
	static String filepath = "D:\\musicPlayer\\resources\\\\db_info.properties";

	public static void openConnection() {
		try {
			fileReader = new FileReader(filepath);
			properties.load(fileReader);
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(properties.getProperty("dbPath"), properties);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("connection not closed ");
			}

		}
	}

	public void chooseSongToPlay() {
		System.out.println("Your Available Song List");
		showAllSongs();
		System.out.println("\nEnter the choice \n1.To Play Specific Song\n2.To Play All Song\n3.To Play Random Song");
		int select = sc.nextInt();
		switch (select) {
		case 1: {
			System.out.print("enter the id:");
			int id = sc.nextInt();
			openConnection();
			query = "select * from song where id=" + id;
			try {
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				System.out.println(resultSet.getString(2) + " Now Playing...");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 2:
			playAllSong();
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
			break;
		case 3:
			playRandomSong();
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
			break;
		default:
			System.out.println("envalid choice");
			MultiPlayerJDBC.menu();
			break;
		}
	}

	private void playRandomSong() {
		System.out.println("Playing Random Songs....");
		openConnection();
		query = "select * from song order by rand() limit 10";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(2) + " song from " + resultSet.getString(3)
						+ "  Movies is playing now..\n");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("connection not closed");
				}
			}
		}
		System.out.println("You want Continue...\n[1.Yes/2.No]");
		switch (sc.nextInt()) {
		case 1:
			playRandomSong();
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
			break;
		case 2:
			System.out.println("moving back to main menu");
			MultiPlayerJDBC.menu();
			break;
		default:
			System.out.println("invalid choice....Lets go to main menu");
			MultiPlayerJDBC.menu();
			break;
		}
	}

	private void showAllSongs() {
		openConnection();
		query = "select * from song";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			int count = 0;
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "|" + resultSet.getString(2) + "|" + resultSet.getString(3)
						+ "|" + resultSet.getDouble(4));
				count++;
			}
			if (count == 0) {
				System.out.println("Oops..No Song are available please add Song\n[1.add song / 2.back]");
				switch (sc.nextInt()) {
				case 1:
					addSong();
					System.out.println(
							"-----------------------------------------------------------------------------------------------------");
					break;
				case 2:
					MultiPlayerJDBC.menu();
					System.out.println(
							"-----------------------------------------------------------------------------------------------------");
					break;
				case 3:
					System.out.println("Invalid choice...");
					System.out.println(
							"-----------------------------------------------------------------------------------------------------");
					break;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();

	}

	public void addSong() {
		System.out.println("How many Song you want to add");
		int n = sc.nextInt();
		int count = 0;
		while (0 < n) {
			openConnection();
			int resultInt;
			query = "insert into song values(?,?,?,?)";
			try {
				preparedStatement = connection.prepareStatement(query);
				System.out.println("enter song id");
				preparedStatement.setInt(1, sc.nextInt());
				sc.nextLine();
				System.out.println("enter song name");
				preparedStatement.setString(2, sc.nextLine());
				sc.nextLine();
				System.out.println("Enter movies name");
				preparedStatement.setString(3, sc.nextLine());
				sc.nextLine();
				System.out.println("enter duration");
				preparedStatement.setDouble(4, sc.nextDouble());
				resultInt = preparedStatement.executeUpdate();
				count = count + resultInt;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception t) {
				System.out.println("plzz give valid input");

			}
			n--;

		}
		System.out.println(count + " songs added successfully...\n Your new Song list");
		showAllSongs();
		System.out.println("Do you want to play song one by one [1.Yes / 2.No] ");
		switch (sc.nextInt()) {
		case 1:
			System.out.println("okay...Lets Enjoy the Song");
			playAllSong();
			break;
		case 2:
			System.out.println("We are moving back to main menu");
			MultiPlayerJDBC.menu();
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
		default:
			System.out.println("Invalid input..Lets go to main menu");
			MultiPlayerJDBC.menu();
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
		}

	}

	public void removeSongs() {
		showAllSongs();
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
		openConnection();
		System.out.println("Want to remove all Song\n[1.Yes / 2.No");
		switch (sc.nextInt()) {
		case 1:
			query = "truncate table song";
			try {
				preparedStatement = connection.prepareStatement(query);
				resultInt = preparedStatement.executeUpdate();
				System.out.println("All songs deleted successfully");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("Want to remove specific song...\n[1.Yes /2. No");
			switch (sc.nextInt()) {
			case 1:
				removeSong();
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				break;
			case 2:
				System.out.println("Go to main menu");
				MultiPlayerJDBC.menu();
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				break;
			default:
				System.out.println(" Invalid choice...lets go to main menu");
				MultiPlayerJDBC.menu();
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				break;
			}
			break;

		default:
			System.out.println(" Invalid choice...lets go to main menu");
			MultiPlayerJDBC.menu();
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
			break;
		}
		closeConnection();
	}

	public void updateSong() {
		System.out.println("How many Song you want update");
		int n = sc.nextInt();
		while (0 < n) {
			System.out.println("enter choice\n [1.For update ID / 2.For update Songname / 3.For update Songmovies ]");
			switch (sc.nextInt()) {
			case 1:
				updateId();
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				break;
			case 2:
				updateSongName();
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				break;
			case 3:
				updateMoviesName();
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				break;
			default:
				System.out.println("Invalid choice");
				updateSong();
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				break;
			}
			n--;
		}

	}

	public void displayAllSong() {
		showAllSongs();
		System.out.println(
				"------------------------------------------------------------------------------------------------------");

	}

	public void playAllSong() {
		openConnection();
		query = "select * from song";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(
						resultSet.getString(2) + " song from " + resultSet.getString(3) + "  Movies is playing On..\n");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("Do you want play again\n[1.Yes]\n[2.No");
		int in = sc.nextInt();
		switch (in) {
		case 1:
			playAllSong();
			break;
		case 2:
			System.out.println("We are moving back to main menu");
			MultiPlayerJDBC.menu();
			break;
		default:
			System.out.println("invalid choice");
			MultiPlayerJDBC.menu();
			break;
		}

	}

	public void removeSong() {
		System.out.println("Enter the id of Song");
		query = "delete from song where id=" + sc.nextInt();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultInt = preparedStatement.executeUpdate();
			System.out.println(resultInt + " song deleted successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateId() {
		openConnection();
		query = "update song set id= ? where id =?";
		try {
			System.out.print("Enter new id:");
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, sc.nextInt());
			System.out.print("Enter old id:");
			preparedStatement.setInt(2, sc.nextInt());
			resultInt = preparedStatement.executeUpdate();
			System.out.println(resultInt + " Song updated Successfully...!");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}

	private void updateSongName() {
		openConnection();
		query = "update song set songName= ? where id =?";
		try {
			System.out.print("Enter new songName:");
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, sc.nextLine());
			System.out.print("Enter song id:");
			preparedStatement.setInt(2, sc.nextInt());
			resultInt = preparedStatement.executeUpdate();
			System.out.println(resultInt + " Song updated Successfully...!");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}

	private void updateMoviesName() {
		openConnection();
		query = "update song set moviesName= ? where id =?";
		try {
			System.out.print("Enter new songName:");
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, sc.nextLine());
			System.out.print("Enter song id:");
			preparedStatement.setInt(2, sc.nextInt());
			resultInt = preparedStatement.executeUpdate();
			System.out.println(resultInt + " Song updated Successfully...!");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}

}
