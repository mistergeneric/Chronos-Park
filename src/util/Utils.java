package util;

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
            if (answer.equals("Y") || answer.equals("y")) {
                return answer;
            } else if (answer.equals("N") || answer.equals("n")) {
                return answer;
            } else {
                System.out.println("Input not understood, please try again");
                answer = scanner.nextLine();
            }
        }
    }
}
