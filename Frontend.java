
// --== CS400 File Header Information ==--
// Name: Yuanqing Cai
// Email: cai92@wisc.edu
// Team: AF red
// Role: Frontend Developer
// TA: Mu
// Lecturer: Florian
// Notes to Grader: none
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.DataFormatException;
public class Frontend {
	public static Boolean FirstTimeChangeMode=true;//to check whether the mode change for the first time
	/**
	 * Run the whole system with the given Backend back, print the Welcome message and enter Base Mode.
	 * @param back
	 */
	public static void run(Backend back) {
		System.out.println("Welcome!!!");
		System.out.println("This is a movies selection app!!!");
		BaseMode(back);//enter the base Mode and pass the back
		System.out.println("ByeBye!!!");
		
		
	}
	/**
	 * The Rating Mode Allow user to select the Rate of the movies they want. Also they can diselect. They can enter x to go back base Mode.
	 * @param back
	 * @param scan
	 */
	private static void RatingMode(Backend back, Scanner scan) {
		if(FirstTimeChangeMode) {
			for(int i=0;i<=10;i++) back.removeAvgRating(String.valueOf(i));
			FirstTimeChangeMode=false;
			//if user enter the Rating mode for the first time, clear all the selected Rating
		}
		System.out.println("This is Rating Mode!");
		System.out.println("You can select any Rating by typing the number of Rating in select column and enter and type the number of Rating"
				+ " you want to diselect in diselect column and enter! if you don't want to select or diselect, type null !!"
				+ " enter x if you want to go back to base mode!");//the Introduction message and tell user how to select and exit.
		while(true) {//loop for user to select for multiple times
			System.out.println("The list of Rating :");
			for(int i=0;i<=10;i++) {
				System.out.print(i+"  ");
				String Rating=String.valueOf(i);
				if(back.getAvgRatings().contains(Rating)) System.out.println("Selected!");
				else System.out.println("Diselected!");
			}//list All the Rating with clear marked selected or diselected	
			System.out.println("type x if you want to go back to base mode! type no if you want to stay");
			//user can enter x to go back to base mode, enter no to select.
			String next=scan.next();// get input
			if(next.equals("x")) break;//go out the loop and go back to base mode.
			if(next.equals("no")) {//select
				System.out.println("Rating you want to select: (type null if no Rating you want select)");
				String select= scan.next();
				if(!select.equals("null")) {
					try {
						int selectnum=Integer.parseInt(select);
					}catch(Exception e) {
						System.out.println("Please enter number of Rating!!!");
						continue;
					}//check whether the user input is valid
					try {
						back.addAvgRating(select);
					}catch(Exception e) {
						System.out.println("Please enter number of Rating!!!");
						continue;//check whether the input is valid
					}
					
					System.out.println("Successfully add Rating "+select);
				}
				//diselect
				System.out.println("Rating you want to diselect: (type null if no Rating you want diselect)");
				String diselect= scan.next();	
				if(!diselect.equals("null")) {
					try {// check the input is valid
						int diselectnum=Integer.parseInt(diselect);
					}catch(Exception e) {
						System.out.println("Please enter number of Genre!!!");
						continue;
					}
					try {
						back.removeAvgRating(diselect);
					}catch(Exception e) {
						System.out.println("Please enter the number of Rating!!!");
						continue;//check whether the input is valid;
					}
					
					System.out.println("Successfully remove Rating "+ diselect);
				
				}
			}else {
				System.out.println("type x if you want to go back to base mode! type no if you want to stay");
				continue;
			}
		}
	}
	/**
	 * The Genre Mode allow the user to select Genre and diselect it. Also the User can type x to exit the Genre Mode and go back the Base Mode.
	 * @param back
	 * @param scan
	 */
	private static void GenreMode(Backend back, Scanner scan) {
		System.out.println("This is Genre Mode!");
		System.out.println("You can select any Genre by typing the number of Genre in select column and enter and type the number of Genre"
				+ " you want to diselect in diselect column and enter! if you don't want to select or diselect, type null !!"
				+ " enter x if you want to go back to base mode!");//information for Genre mode and how to use it.
		while(true) {//loop for user to select multiple time
			System.out.println("The list of Genre :");
			for(int i=0;i<back.getAllGenres().size();i++) {
				System.out.print(i+1+"      ");
				System.out.print(back.getAllGenres().get(i));
				if(back.getGenres().contains(back.getAllGenres().get(i))) System.out.println("          selected!        ");
				else System.out.println("           not selected!        ");
				//list all the Genre with the number represent the Genre and the marked whether it is selected or not.
			}
			
			System.out.println("type x if you want to go back to base mode! type no if you want to stay");
			//check whether go back to base mode
			String next=scan.next();
			if(next.equals("x")) break;
			if(next.equals("no")) {
				System.out.println("Genre you want to select: (type null if no Genre you want select)");
				//select the Genre with number.
				String select= scan.next();
				if(!select.equals("null")) {
					try {
						int selectnum=Integer.parseInt(select);
					}catch(Exception e) {
						System.out.println("Please enter number of Genre!!!");
						continue;//check the input is valid
					}
					int selectnum= Integer.parseInt(select);
					try {
						back.addGenre(back.getAllGenres().get(selectnum-1));
					}catch(Exception e) {
						System.out.println("Please enter number of Genre!!!");
						continue;//check the input is valid.
					}
					System.out.println("Successfully add "+back.getAllGenres().get(selectnum-1));
				}
				
				System.out.println("Genre you want to diselect: (type null if no Genre you want diselect)");
				String diselect= scan.next();	
				if(!diselect.equals("null")) {
					try {
						int diselectnum=Integer.parseInt(diselect);
					}catch(Exception e) {
						System.out.println("Please enter number of Genre!!!");
						continue;//check the input is valid;
					}
					int diselectnum= Integer.parseInt(diselect);
					try {
						back.removeGenre(back.getAllGenres().get(diselectnum-1));
					}catch(Exception e) {
						System.out.println("Please enter number of Genre!!!");
						continue;//check the input is valid.
					}
					
					System.out.println("Successfully remove "+back.getAllGenres().get(diselectnum-1));
				
				}
			}else {
				System.out.println("type x if you want to go back to base mode! type no if you want to stay");
			}
		}
	}
	/**
	 * The base mode show the movies user selected with all the movies initial. user can type the number of movies and g to enter G mode, r to enter Rating Mode and x to exit.
	 * @param back
	 */
	private static void BaseMode(Backend back) {
		Boolean exit=false;//check whether exit.
		for(int i=0;i<=10;i++) {
			String AvgRating=Integer.toString(i);
			back.addAvgRating(AvgRating);// add all the Rating at first
		}
		while(!exit) {//loop for user to make action multiple times
			System.out.println("This is the Base Mode!!!!");
			int num=back.getNumberOfMovies();
			Scanner scan=new Scanner(System.in);// construct a Scanner
			System.out.println("You can type the list number of the movie you want to see! The number of movies is "+num+"!");
			System.out.println("Or you can type g to enter Genre Mode!");
			System.out.println("Or you can type r to enter Rating Mode!");// welcome messages and tell user how to use it.
			System.out.print("The Genre selected: ");
			System.out.println(back.getGenres());// print out the Genre selected. with null initial
			System.out.print("The Rating selected: ");
			if(back.getAvgRatings().size()==0) System.out.println("[ All ]");
			else System.out.println(back.getAvgRatings());//print out the Rating selected. With all initial
			System.out.println("----------------------------");
			System.out.println(num+" movies are selected! The Top Three Rating movies are list below!");
			for(int i=0;i<3&&i<num;i++) {
				String title = back.getThreeMovies(0).get(i).getTitle();
				String director = back.getThreeMovies(0).get(i).getDirector();
				String description = back.getThreeMovies(0).get(i).getDescription();
				String year = String.valueOf(back.getThreeMovies(0).get(i).getYear());
				String genres = back.getThreeMovies(0).get(i).getGenres().toString();
				Float avgVote = back.getThreeMovies(0).get(i).getAvgVote();
				String info = String.format("Title: %s, Director: %s, Description: %s, Year: %s, Genres: %s, Average Vote: %.1f",title,director,description,year,genres,avgVote);
				System.out.println(info);
				//list the top three movies of the selected. initial 
			}

			while(true) {//loop for user to make multiple action
				String typeIn=scan.next();	
				if(typeIn.equals("x")) {
					scan.close();
					exit=true;//renew exit
					break;
					//check x to exit.
				}
				if(typeIn.equals("g")) {
					GenreMode(back,scan);
					break;
					//check g to enter G mode
				}
				if(typeIn.equals("r")) {
					RatingMode(back,scan);
					break;
					//enter r to enter R mode
				}
				try {
					int chooseNum=Integer.parseInt(typeIn);
				}catch(Exception e) {
				System.out.println("You have to type g, r, x, or number!");
				continue;
				//check the valid of input
				}
				int chooseNum=Integer.parseInt(typeIn);
				if(chooseNum<=0||chooseNum>num) System.out.println("Please enter number between 1 and "+num+"!");
				//check valid of input
				else {
					for(int i=0;i<3&&chooseNum+i<=num;i++) {
						String title = back.getThreeMovies(chooseNum-1).get(i).getTitle();
						String director = back.getThreeMovies(chooseNum-1).get(i).getDirector();
						String description = back.getThreeMovies(chooseNum-1).get(i).getDescription();
						String year = String.valueOf(back.getThreeMovies(chooseNum-1).get(i).getYear());
						String genres = back.getThreeMovies(chooseNum-1).get(i).getGenres().toString();
						Float avgVote = back.getThreeMovies(chooseNum-1).get(i).getAvgVote();
						String info = String.format("Title: %s, Director: %s, Description: %s, Year: %s, Genres: %s, Average Vote: %.1f",title,director,description,year,genres,avgVote);
						System.out.println(info);
						// print out the information of the selected three movies.
					}
								
				}
			}
		}
		
	}
	/**
	 * Main methods to initialize and instantiating the Backend back with File Reader and then run the whole system.
	 * @param args
	 */
	public static void main(String[] args) {
		Backend back;//new Backend
		try {
			FileReader file= new FileReader("./movies.csv");//the file Reader
			back = new Backend(file);
		    run(back);// run the whole System
		
		} catch (IOException e) {
			System.out.println("There is an IOException!");
			e.printStackTrace();//check the exception
		} catch (DataFormatException e) {
			System.out.println("There is a DataFormatException!");
			e.printStackTrace();//check the Exception.
		} 
		
		
	}

}
