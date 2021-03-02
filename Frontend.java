// --== CS400 File Header Information ==--
// Name: Yuanqing Cai
// Email: cai92@wisc.edu
// Team: AF red
// Role: Data Wrangler
// TA: Mu
// Lecturer: Florian
// Notes to Grader: none
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.DataFormatException;
public class Frontend {
	public static Boolean FirstTimeChangeMode=true;
	public static void run(Backend back) {
		System.out.println("Welcome!!!");
		System.out.println("This is a movies selection app!!!");
		BaseMode(back);
		System.out.println("ByeBye!!!");
		
		
	}
	private static void RatingMode(Backend back, Scanner scan) {
		if(FirstTimeChangeMode) {
			back.clear();
			FirstTimeChangeMode=false;
		}
		System.out.println("This is Rating Mode!");
		System.out.println("You can select any Rating by typing the number of Rating in select column and enter and type the number of Rating"
				+ " you want to diselect in diselect column and enter! if you don't want to select or diselect, type null !!"
				+ " enter x if you want to go back to base mode!");
		while(true) {
			System.out.println("The list of Rating :");
			for(int i=0;i<=10;i++) {
				System.out.print(i+"  ");
				String Rating=String.valueOf(i);
				if(back.getAvgRatings().contains(Rating)) System.out.println("Selected!");
				else System.out.println("Diselected!");
			}
			
			
			System.out.println("type x if you want to go back to base mode! type no if you want to stay");
			String next=scan.next();
			if(next.equals("x")) break;
			if(next.equals("no")) {
				System.out.println("Rating you want to select: (type null if no Rating you want select)");
				String select= scan.next();
				if(!select.equals("null")) {
					try {
						int selectnum=Integer.parseInt(select);
					}catch(Exception e) {
						System.out.println("Please enter number of Rating!!!");
						continue;
					}
					back.addAvgRating(select);
					System.out.println("Successfully add Rating "+select);
				}
				
				System.out.println("Rating you want to diselect: (type null if no Rating you want diselect)");
				String diselect= scan.next();	
				if(!diselect.equals("null")) {
					try {
						int diselectnum=Integer.parseInt(diselect);
					}catch(Exception e) {
						System.out.println("Please enter number of Genre!!!");
						continue;
					}
					back.removeAvgRating(diselect);
					System.out.println("Successfully remove Rating "+ diselect);
				
				}
			}else {
				System.out.println("type x if you want to go back to base mode! type no if you want to stay");
			}
		}
	}
	private static void GenreMode(Backend back, Scanner scan) {
		if(FirstTimeChangeMode) {
			back.clear();
			FirstTimeChangeMode=false;
		}
		System.out.println("This is Genre Mode!");
		System.out.println("You can select any Genre by typing the number of Genre in select column and enter and type the number of Genre"
				+ " you want to diselect in diselect column and enter! if you don't want to select or diselect, type null !!"
				+ " enter x if you want to go back to base mode!");
		while(true) {
			System.out.println("The list of Genre :");
			for(int i=0;i<back.getAllGenres().size();i++) {
				System.out.print(i+1+"      ");
				System.out.print(back.getAllGenres().get(i));
				if(back.getGenres().contains(back.getAllGenres().get(i))) System.out.println("          selected!        ");
				else System.out.println("           not selected!        ");
				
			}
			
			System.out.println("type x if you want to go back to base mode! type no if you want to stay");
			String next=scan.next();
			if(next.equals("x")) break;
			if(next.equals("no")) {
				System.out.println("Genre you want to select: (type null if no Genre you want select)");
				String select= scan.next();
				if(!select.equals("null")) {
					try {
						int selectnum=Integer.parseInt(select);
					}catch(Exception e) {
						System.out.println("Please enter number of Genre!!!");
						continue;
					}
					int selectnum= Integer.parseInt(select);
					back.addGenre(back.getAllGenres().get(selectnum-1));
					System.out.println("Successfully add "+back.getAllGenres().get(selectnum-1));
				}
				
				System.out.println("Genre you want to diselect: (type null if no Genre you want diselect)");
				String diselect= scan.next();	
				if(!diselect.equals("null")) {
					try {
						int diselectnum=Integer.parseInt(diselect);
					}catch(Exception e) {
						System.out.println("Please enter number of Genre!!!");
						continue;
					}
					int diselectnum= Integer.parseInt(diselect);
					back.removeGenre(back.getAllGenres().get(diselectnum-1));
					System.out.println("Successfully remove "+back.getAllGenres().get(diselectnum-1));
				
				}
			}else {
				System.out.println("type x if you want to go back to base mode! type no if you want to stay");
			}
			
			
		}
		
	}
	private static void BaseMode(Backend back) {
		Boolean exit=false;
		for(int i=0;i<=10;i++) {
			String AvgRating=Integer.toString(i);
			back.addAvgRating(AvgRating);
		}
		while(!exit) {
			System.out.println("This is the Base Mode!!!!");
			int num=back.getNumberOfMovies();
			System.out.println("You can type the list number of the movie you want to see! The number of movies is "+num+"!");
			System.out.println("Or you can type g to enter Genre Mode!");
			System.out.println("Or you can type r to enter Rating Mode!");
			System.out.print("The Genre selected: ");
			System.out.println(back.getGenres());
			System.out.print("The Rating selected: ");
			if(back.getAvgRatings().size()==0) System.out.println("[ All ]");
			else System.out.println(back.getAvgRatings());
			System.out.println("----------------------------");
			System.out.println(num+" movies are selected! The Top Three Rating movies are list below!");
			for(int i=0;i<3&&i<num;i++) {
				String title = back.getThreeMovies(0).get(i).getTitle();
				String director = back.getThreeMovies(0).get(i).getDirector();
				String description = back.getThreeMovies(0).get(i).getDescription();
				String genres = back.getThreeMovies(0).get(i).getGenres().toString();
				Float avgVote = back.getThreeMovies(0).get(i).getAvgVote();

				String info = String.format("Title: %s, Director: %s, Description: %s, Genres: %s, Average Vote: %.1f",title,director,description,genres,avgVote);
				System.out.println(info);
			}
			Scanner scan=new Scanner(System.in);
			while(true) {
				String typeIn=scan.next();
				if(typeIn.equals("x")) {
					scan.close();
					exit=true;
					break;
				}
				if(typeIn.equals("g")) {
					GenreMode(back,scan);
					break;
				}
				if(typeIn.equals("r")) {
					RatingMode(back,scan);
					break;
				}
				try {
					int chooseNum=Integer.parseInt(typeIn);
				}catch(Exception e) {
				System.out.println("You have to type g, r, x, or number!");
				continue;
				}
				int chooseNum=Integer.parseInt(typeIn);
				if(chooseNum<=0||chooseNum>num) System.out.println("Please enter number between 1 and "+num+"!");
				else {
					for(int i=0;i<3&&chooseNum+i<=num;i++) {
						String title = back.getThreeMovies(chooseNum-1).get(i).getTitle();
						String director = back.getThreeMovies(chooseNum-1).get(i).getDirector();
						String description = back.getThreeMovies(chooseNum-1).get(i).getDescription();
						String genres = back.getThreeMovies(chooseNum-1).get(i).getGenres().toString();
						Float avgVote = back.getThreeMovies(chooseNum-1).get(i).getAvgVote();

						String info = String.format("Title: %s, Director: %s, Description: %s, Genres: %s, Average Vote: %.1f",title,director,description,genres,avgVote);
						System.out.println(info);
					}
								
				}
			}
		}
		
	}
	public static void main(String[] args) {
		Backend back;
		try {
			FileReader file= new FileReader("./movies.csv");
			back = new Backend(file);
		    run(back);
		
		} catch (IOException e) {
			System.out.println("There is an IOException!");
			e.printStackTrace();
		} catch (DataFormatException e) {
			System.out.println("There is a DataFormatException!");
			e.printStackTrace();
		} 
		
		
	}

}
