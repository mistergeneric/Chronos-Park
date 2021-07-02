//andrew ncneill
import locations.Location;
import model.Park;
import model.Preference;
import locations.Ride;
import model.tree.Tree;
import util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static Park park;

    public static void main(String[] args) {


        System.out.println("Welcome to CHRONOS PARK!!!!!! ******");
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Get your recommendations for a ride:");
            System.out.println("2. Get all your for whole park recommendations:");
            System.out.println("3. Generate map of the park:");
            System.out.println("4. Generate a personalised map");


            System.out.println("8. Quit");

            //I'm using a list of all rides because you can do things like filtering and streaming with that, contained within park object (the graph)
            park = Utils.generatePark();
            List<Ride> allRides = park.getAllRides();

            Scanner scanner = new Scanner(System.in);

            String userInput = scanner.nextLine();
            int userInputParsed = 0;
            try {
                userInputParsed = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("You need to insert a valid accepted number");
            }
            switch (userInputParsed) {
                case 1:
                    System.out.println("What ride would you like recommendations for?");
                    while(true) {
                        String answer = scanner.nextLine();
                        List<Ride> filteredRides = allRides.stream().filter(ride -> ride.getName().equals(answer)).collect(Collectors.toList());
                        //there has been a match on the text the user has input for name
                        if(filteredRides.size() > 0) {
                            //there should be only one match
                            getRecommendations(filteredRides.get(0));
                            break;
                        }
                        System.out.println("Not valid name, please try again");
                    }
                    break;
                case 2:
                    getWholePark(allRides);
                    break;
                case 3:
                    getMap(park.getAllLocations(), park);
                    park.primMST();
                    break;
                case 4:
                    System.out.println("Please enter your recommended rides:");
                    List<Location> recommended = getWholePark(allRides);
                    getMap(recommended, park);
                    break;
                case 8:
                    System.out.println("you have chosen to quit. Goodbye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option not recognised, returning to main menu");
                    break;
            }
        }
    }

    private static void getMap(List<Location> allLocations, Park park) {
        for (Location l :
                allLocations) {
            if (l.isEntrance()) {
                park.shortestPathFromEntrance(l, allLocations);
            }
        }
    }

    private static void getRecommendations(Ride ride) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi, what is your first name?");
        String name = scanner.nextLine();
        Tree tree = new Tree(ride);


        Preference preference = tree.question(ride);

        String recommendation = preference.isRideRecommended() ? "The ride is recommended for you!" : "I'm sorry, the ride is not recommended for you";

        System.out.println(recommendation);

        System.out.println("Would you like to print or emaiL? Choose E for email or P for print");

        String answer = scanner.nextLine();

        while((!answer.equals("P")) && (!answer.equals("E"))) {
            System.out.println("please enter valid input");
            answer = scanner.nextLine();
        }
        if(answer.equals("P")) {
            System.out.println(preference.getPreferenceString());
        } else {
            emailCheck(scanner);
        }

    }

    private static List<Location> getWholePark(List<Ride> allRides) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi, what is your first name?");
        String name = scanner.nextLine();
        Tree tree = new Tree();

        List<Ride> recommendedRides = tree.allRidesQuestion(allRides);


        System.out.println("Would you like to print or emaiL? Choose E for email or P for print");

        String answer = scanner.nextLine();

        while((!answer.equals("P")) && (!answer.equals("E"))) {
            System.out.println("please enter valid input");
            answer = scanner.nextLine();
        }
        if(answer.equals("P")) {
            if(recommendedRides.size() > 0) {
                System.out.println("Here are your recommended rides:");
                recommendedRides.forEach(ride -> System.out.println("You have been recommended: " + ride.getName()));
            } else {
                System.out.println("I'm sorry, none of our rides are recommended for you");
            }
        } else {
            emailCheck(scanner);
        }
        List<Location> recommendedLocation = new ArrayList<>();
        park.getAllLocations().forEach(l -> {
            if (l.isEntrance()) {
                recommendedLocation.add(l);
            } else if (recommendedRides.contains(l)) {
                recommendedLocation.add(l);
            }
        });
        return recommendedLocation;
    }

    private static void emailCheck(Scanner scanner) {
        String emailValidation = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(emailValidation);
        while(true) {
            System.out.println("Enter your email");
            String email = scanner.nextLine();
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                System.out.println("Thanks, email has been sent");
                break;
            } else {
                System.out.println("Email incorrect, try again");
            }
        }
    }
}
