import model.Preference;
import model.Ride;
import model.tree.Tree;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to CHRONOS PARK!!!!!! ******");
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Get your recommendations:");
            System.out.println("2. Quit");
        /*
        For step 1, we will use The Iron Jaws as the test case
         */
            //for the max heights and max group size, because the ride does not have a level of max height or max group size, set it to max possible number
            Ride ironJaws = new Ride("The Iron Jaws", 1, Integer.MAX_VALUE, false, 1, Integer.MAX_VALUE, true, false, false, false);


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
                    //this takes a parameter because it will do different rides in future
                    getRecommendations(ironJaws);
                    break;
                case 2:
                    System.out.println("you have chosen to quit. Goodbye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option not recognised, returning to main menu");
                    break;
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
