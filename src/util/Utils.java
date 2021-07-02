//andrew mcneill
package util;

import locations.Location;
import model.Park;
import locations.Ride;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//static util class keeps things tidy
public class Utils {
    public static int getNumber() {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        int numberParsed = 0;

        while (true) {
            try {
                numberParsed = Integer.parseInt(number);
                if(numberParsed >= 1) {
                    break;
                } else {
                    System.out.println("Minimum number can be 1");
                }
            } catch (NumberFormatException e) {
                System.out.println("You need to insert a valid accepted number, please re-enter");
                number = scanner.nextLine();
            }
        }
        return numberParsed;
    }

    public static float getFloat() {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        float numberParsed = 0;

        while (true) {
            try {
                numberParsed = Float.parseFloat(number);
                break;
            } catch (NumberFormatException e) {
                System.out.println("You need to insert a valid accepted number, please re-enter");
                number = scanner.nextLine();
            }
        }
        return numberParsed;
    }

    public static String yesOrNo() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        while (true) {
            if (answer.equalsIgnoreCase("Y")) {
                return answer;
            } else if (answer.equalsIgnoreCase("N")) {
                return answer;
            } else {
                System.out.println("Input not understood, please try again");
                answer = scanner.nextLine();
            }
        }
    }
    public static Park generatePark() {
        Park park = new Park();
        List<Ride> allRides = new ArrayList<>();
        //one list including entrance
        List<Location> allLocations = new ArrayList<>();

        Ride ironJaws = new Ride("The Iron Jaws", 1, Integer.MAX_VALUE, false, 1, Integer.MAX_VALUE, true, false, false, "Medieval", false);
        allRides.add(ironJaws);
        Ride towerTerror = new Ride("Tower of Terror", 0, Integer.MAX_VALUE, true, 1, Integer.MAX_VALUE, false, false, true, "Medieval", true);
        allRides.add(towerTerror);
        Ride cloister = new Ride("Cloister of Cruelty", 0, Integer.MAX_VALUE, true, 5, 6, true, false, true, "Medieval", true);
        allRides.add(cloister);
        Ride pony = new Ride("Pony Jousts", 0.8f, 1.2f, false, 1, Integer.MAX_VALUE, true, false, true, "Medieval", false);
        allRides.add(pony);
        Ride mystic = new Ride("Mystic Fortunes", 0, Integer.MAX_VALUE, true, 1, 1, true, false, true, "Medieval", false);
        allRides.add(mystic);

        Ride crisis = new Ride("Crisis at Farpoint", 0, Integer.MAX_VALUE, true, 4, 8, true, true, false, "Futuristic", true);
        allRides.add(crisis);
        Ride build = new Ride("Build a Bot", 0, Integer.MAX_VALUE, true, 1, Integer.MAX_VALUE, false, true, false, "Futuristic", false);
        allRides.add(build);
        Ride laser = new Ride("Laser Lock", 1.1f, Integer.MAX_VALUE, false, 2, 6, true, false, true, "Futuristic", false);
        allRides.add(laser);
        Ride trench = new Ride("Trench Chase", 0, 1.6f, true, 1, Integer.MAX_VALUE, true, true, false, "Futuristic", false);
        allRides.add(trench);
        Ride robot = new Ride("Robot Conflicts", 0, Integer.MAX_VALUE, true, 1, Integer.MAX_VALUE, true, true, true, "Futuristic", false);
        allRides.add(robot);

        Ride rex = new Ride("Rex Rampage", 0, Integer.MAX_VALUE, false, 2, 2, true, false, true, "Jurassic", true);
        allRides.add(rex);
        Ride pet = new Ride("Pet a Saur", 0, 1, true, 1, Integer.MAX_VALUE, false, true, false, "Jurassic", false);
        allRides.add(pet);
        Ride sauro = new Ride("SauroPods", 1, Integer.MAX_VALUE, true, 1, Integer.MAX_VALUE, true, false, false, "Jurassic", true);
        allRides.add(sauro);
        Ride raptor = new Ride("Raptor Races", 0, Integer.MAX_VALUE, true, 2, 2, true, true, false, "Jurassic", false);
        allRides.add(raptor);
        Ride hatchling = new Ride("Hatchling Nest", 0, 1, true, 1, Integer.MAX_VALUE, false, true, false, "Jurassic", false);
        allRides.add(hatchling);

        Ride mirrors = new Ride("Hall of Mirrors", 0, Integer.MAX_VALUE, true, 1, Integer.MAX_VALUE, true, false, false, "Industrial", false);
        allRides.add(mirrors);

        Ride high = new Ride("High Noon", 0, Integer.MAX_VALUE, true, 2, 2, true, true, false, "Industrial", false);
        allRides.add(high);

        Ride steampunk = new Ride("Steampunk Cups", 0, Integer.MAX_VALUE, false, 3, 6, true, true, true, "Industrial", false);
        allRides.add(steampunk);
        Ride pain = new Ride("The Pain Train", 1, Integer.MAX_VALUE, true, 2, 8, true, false, true, "Industrial", true);
        allRides.add(pain);
        Ride descent = new Ride("The Descent", 0, Integer.MAX_VALUE, false, 1, Integer.MAX_VALUE, true, true, true, "Industrial", true);
        allRides.add(descent);

        Location entrance = new Location("Entrance");
        allLocations.add(entrance);

        allLocations.addAll(allRides);

        park.setAllRides(allRides);
        park.setAllLocations(allLocations);

        park.setAdjacencylist(new LinkedList[allLocations.size()]);

        park.addRoute(entrance, towerTerror, 55);
        park.addRoute(entrance, ironJaws, 60);
        park.addRoute(cloister, entrance, 50);
        park.addRoute(towerTerror, cloister, 60);
        park.addRoute(towerTerror, mystic, 80);
        park.addRoute(towerTerror, pony, 50);
        park.addRoute(ironJaws, towerTerror, 30);
        park.addRoute(mystic, pony, 100);
        park.addRoute(mystic, hatchling, 40);
        park.addRoute(pony, crisis, 90);
        park.addRoute(pony, ironJaws, 20);

        park.addRoute(trench, laser, 50);
        park.addRoute(trench, crisis, 50);
        park.addRoute(trench, raptor, 130);
        park.addRoute(trench, steampunk, 90);
        park.addRoute(trench, descent,110);
        park.addRoute(crisis, laser, 45);
        park.addRoute(crisis, build, 30);
        park.addRoute(build, ironJaws, 80);
        park.addRoute(build, laser, 45);
        park.addRoute(laser, robot, 45);
        park.addRoute(robot, trench, 55);

        park.addRoute(pet, high, 200);
        park.addRoute(high, mirrors, 30);

        park.addRoute(pain, trench, 55);
        park.addRoute(pain, descent, 45);
        park.addRoute(descent, mirrors, 30);
         park.addRoute(mirrors, high, 30);
        park.addRoute(high, steampunk, 30);

        park.addRoute(steampunk, pain, 55);

        park.addRoute(pet, rex, 30);
        park.addRoute(pet, raptor, 40);
        park.addRoute(rex, hatchling, 30);
        park.addRoute(hatchling, pet, 60);
        park.addRoute(hatchling, sauro, 40);
        park.addRoute(sauro, hatchling, 40);
        park.addRoute(raptor, sauro, 40);
        park.addRoute(raptor, steampunk, 90);



        return park;

    }
}
