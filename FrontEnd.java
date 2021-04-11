import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class FrontEnd {
    public static Boolean exit_status = true;
    public static char code = ' ';
    public static final double CAR_SPEED = 100.0;
    public static final double TRAIN_SPEED = 300.0;
    private static String start, end, current;
    private static int distance,cost;

    /**
     * Switch into different actions by different codes.
     *
     * code == S -> Shortest mode;
     * code == M -> Money mode;
     * code == R -> Reachable mode;
     * code == A -> Add path mode;
     * code == D -> Delete path mode;
     * code == X -> if exit_status is true, exit the whole programme, else back to base mode.
     *
     * @param backend backend used
     * @param code command code
     */
    public static void run(Backend backend, char code){
        switch (code){
            case 'S':
                ShortestMode(backend);
                break;
            case 'M':
                MoneyMode(backend);
                break;
            case 'R':
                ReachableMode(backend);
                break;
            case 'A':
                AddMode(backend);
                break;
            case 'D':
                DeleteMode(backend);
                break;
            case 'X':
                ExitJudge();
                BaseMode(backend);
                break;
            default:
                System.out.println("-----ERROR-----");
                System.out.println("-----Command cannot find. Make sure your command in uppercase-----");
                System.out.println("Please try again.\n\n");
                BaseMode(backend);
                break;
        }
    }

    /**
     * Base mode. Provide user commands.
     * @param backend backend used
     */
    public static void BaseMode(Backend backend) {
        System.out.println("===========================================");
        System.out.println("-----Welcome to Traffic Navigation Map-----\n");
        System.out.println("|------------------------------------------");
        System.out.println("|- Press “S”, which stands for “Shortest”, will return the shortest path between two cities, and calculate time.\n" +
                "|- Press “M”, which stands for “Money”, will return the most affordable path between two cities.\n" +
                "|- Press “R”, which stands for “Reachable”, will return directly reachable cities of the target city.\n" +
                "|- Press “A”, which stands for  “ADD”, will add a new path between two cities.\n" +
                "|- Press “D”, which stands for “Delete”, will delete a path between two cities.\n" +
                "|- Press “X” to exit");
        System.out.println("\n|-------------City List-----------------");
        int n = 1;
        for (CityDataInterface city: backend.cityList) {
            System.out.print(city.GetName()+" ");
            n = n + 1;
            if(n % 4 == 0){
                System.out.println("");
            }
        }
        System.out.println("\n|---------------------------------------");
        System.out.println("\n==> CURRENT MODE: Base Mode<==");
        System.out.printf("\nNow you input:");
        code = new Scanner(System.in).next().charAt(0);
        exit_status = true;
        run(backend, code);
    }

    /**
     * Shortest Path mode. This method will pass the params. into backend and retrieve data to
     * the user.
     * @param backend the backend used
     */
    private static void ShortestMode(Backend backend) {
        boardInfo("Shortest Path",backend);
        List<CityDataInterface> shortestPath =null;
        try{
            shortestPath = backend.getShortestPath(start, end);
            System.out.printf("\nThe shortest path from %s to %s is: ", start, end);
            StringBuilder info = new StringBuilder();

            for (int i = 0; i < shortestPath.size() - 1; i++) {
                info.append(shortestPath.get(i).GetName()).append(" -> ");
            }
            System.out.println("\n"+ info.toString() + shortestPath.get(shortestPath.size() - 1).GetName());
            System.out.printf("\nWhich will take you %.2f hours by car and %.2f by train",(backend.getShortestDistance(start,end) / TRAIN_SPEED),(backend.getShortestDistance(start,end) / CAR_SPEED));
        }catch (NoSuchElementException e){
            System.out.println("NO PATH BETWEEN TWO CITIES");
        }

        innerExit(backend);
    }

    /**
     * Money Mode. This method will pass the params. into backend and retrieve data to
     * the user.
     * @param backend the backend used
     */
    private static void MoneyMode(Backend backend) {
        boardInfo("Money",backend);
        List<CityDataInterface> cheapestPath =null;
        try{
            cheapestPath = backend.getShortestPath(start, end);
            System.out.printf("\nThe cheapest path from %s to %s is: ", start, end);
            StringBuilder info = new StringBuilder();
            for (int i = 0; i < cheapestPath.size() - 1; i++) {
                info.append(cheapestPath.get(i).GetName()).append(" -> ");
            }
            System.out.println("\n"+ info.toString() + cheapestPath.get(cheapestPath.size() - 1).GetName());
            System.out.println("And the cheapest cost is: " + backend.getCheapestCost(start,end));
        }catch (NoSuchElementException e){
            System.out.println("NO PATH BETWEEN TWO CITIES");
        }
        innerExit(backend);
    }

    /**
     * Reachable Mode.This method will pass the params. into backend and retrieve data to
     * the user.
     * @param backend the backend used
     */
    private static void ReachableMode(Backend backend){
        boardInfo("Direct Reachable",backend);
        List<CityDataInterface> directPath =null;
            directPath = backend.getDirectReach(current);
            if(directPath.size() == 0){
                System.out.printf("\nNO DIRECTLY REACHABLE CITIES FROM %s",current);
            }else{
                System.out.printf("\nAll the directly reachable cities from %s are: ", current);
                StringBuilder info = new StringBuilder();
                for (int i = 0; i < directPath.size() - 1; i++) {
                    info.append(directPath.get(i).GetName()).append(" , ");
                }
                System.out.println("\n"+ info.toString() + directPath.get(directPath.size() - 1).GetName());

            }
        innerExit(backend);
    }
    /**
     * Add path mode. This method will retrieve the input collect by board_info and pass it into
     * the backend, then show to the users.
     * @param backend the backend used
     */
    private static void AddMode(Backend backend) {
        boardInfo("Add Path",backend);
        backend.addPath(start,end,distance,cost);
        System.out.printf("\nPath from %s to %s has been added successfully: ", start, end);
        innerExit(backend);
    }

    /**
     * Delete path mode. This method will retrieve the input collect by board_info and pass it into
     * the backend, then show to the users.
     * @param backend the backend used
     */
    private static void DeleteMode(Backend backend) {
        boardInfo("Delete Path",backend);
        System.out.printf("\nPath from %s to %s has been added successfully: ", start, end);
        try{
            backend.deletePath(start,end);
        }catch (NoSuchElementException e){
            System.out.println("NO PATH FOUND");
        }
        innerExit(backend);

    }

    /**
     * Tool method. To judge if exit the whole program. or just back to base mode.
     */
    private static void ExitJudge() {
        if (exit_status){
            System.out.println("-----Thanks for using Traffic Navigation Map-----\n");
            System.exit(-1);
        }
    }

    /**
     * Tool method. To finish function back to base mode
     * @param backend the backend used
     */
    private static void innerExit(Backend backend){
        while(true){
            System.out.println("\nPress X to back to base mode");
            if(new Scanner(System.in).next().charAt(0) == 'X'){
                exit_status = false;
                run(backend,'X');
                break;
            }
        }
    }

    /**
     * Tool method. Welcome information and instructions
     * @param mode_name the name of the mode
     * @param backend backend used
     */
    private static void boardInfo(String mode_name,Backend backend){
        System.out.println("\n|------------------------------------------");
        System.out.println("|-----Welcome to " + mode_name + " Mode------");
        if(mode_name.equals("Direct Reachable")){
            while(true){
                System.out.println("Please input your desired city's name: ");
                Scanner scanner = new Scanner(System.in);
                current  = scanner.next();
                if(!cityNameValidation(backend,current)) {
                    System.out.println("Make sure you input the right city's name. Try again.");
                    continue;
                }
                break;
            }
        }else{
            while(true){
                System.out.println("Please input your starting city's name: ");
                Scanner scanner = new Scanner(System.in);
                start  = scanner.next();
                if(!cityNameValidation(backend,start)) {
                    System.out.println("Make sure you input the right city's name. Try again.");
                    continue;
                }
                System.out.println("Please input your end city's name: ");
                end  = scanner.next();
                if(!cityNameValidation(backend,end)){
                    System.out.println("Make sure you input the right city's name. Try again.");
                    continue;
                }
                if(mode_name.equals("Add Path")){
                    System.out.println("Please input the distance between two cities: ");
                    distance = scanner.nextInt();
                    System.out.println("Please input the cost between two cities: ");
                    cost = scanner.nextInt();
                }
                break;
            }
        }

    }

    /**
     * Valid if data could be found in the city data set
     * @param backend backend used
     * @param city_name city's name needed to be validate
     * @return true if data is valid; false if not valid
     */
    private static boolean cityNameValidation(Backend backend,String city_name){
        boolean status = false;
        for (CityDataInterface city: backend.cityList) {
            if(city.GetName().equals(city_name)){
                status = true;
                break;
            }
        }
        return status;
    }
    public static void main(String[] args) {
        Backend backend;
        try {
            FileReader city_data = new FileReader("G:\\AAWISC-Spring 2021\\CS400_git\\CS400-Project-One\\CityDataSet.csv");
            FileReader path_data = new FileReader("G:\\AAWISC-Spring 2021\\CS400_git\\CS400-Project-One\\PathDataSet.csv");
            backend = new Backend(city_data,path_data);
            BaseMode(backend);
        } catch (FileNotFoundException e) {
            System.out.println("NO SUCH FILE");
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        } catch (DataFormatException e) {
            System.out.println("WRONG DATA FORMAT");
        }

    }

}
