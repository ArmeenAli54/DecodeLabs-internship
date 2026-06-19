import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    private static final int MAX_NUMBER  = 100;
    private static final int MAX_ATTEMPTS = 5;

    public static void main(String[] args) {

        Scanner sc     = new Scanner(System.in);
        Random  random = new Random();

        int  score        = 0;
        int  roundsPlayed = 0;
        char playAgain;

        printHeader("WELCOME TO THE NUMBER GUESSING GAME");

        do {
            roundsPlayed++;
            boolean guessed = playRound(sc, random, roundsPlayed);

            if (guessed) {
                score++;
            }

            System.out.println("\n  Current Score: " + score + " win(s) out of " + roundsPlayed + " round(s)");
            System.out.print("\n  Play again? (Y/N): ");
            playAgain = sc.next().charAt(0);

        } while (playAgain == 'Y' || playAgain == 'y');

        printHeader("GAME OVER");
        System.out.println("  Final Score : " + score + " win(s) out of " + roundsPlayed + " round(s)");
        System.out.printf ("  Win Rate    : %.1f%%%n", (score * 100.0 / roundsPlayed));
        System.out.println("\n  Thanks for playing. Goodbye!");
        sc.close();
    }

    private static boolean playRound(Scanner sc, Random random, int roundNumber) {

        int     secretNumber = random.nextInt(MAX_NUMBER) + 1;
        int     attemptsLeft = MAX_ATTEMPTS;
        boolean guessed      = false;

        System.out.println("\n----------------------------------");
        System.out.println("  ROUND " + roundNumber);
        System.out.println("  Guess a number between 1 and " + MAX_NUMBER);
        System.out.println("  You have " + MAX_ATTEMPTS + " attempts.");
        System.out.println("----------------------------------");

        while (attemptsLeft > 0) {

            System.out.print("\n  [" + attemptsLeft + " attempt(s) left] Enter guess: ");
            int guess = readIntSafely(sc);

            if (guess < 1 || guess > MAX_NUMBER) {
                System.out.println("  WARNING: Please enter a number between 1 and " + MAX_NUMBER + ".");
                continue;
            }

            if (guess == secretNumber) {
                int taken = MAX_ATTEMPTS - attemptsLeft + 1;
                System.out.println("\n  CORRECT! The number was " + secretNumber + ".");
                System.out.println("  You got it in " + taken + " attempt(s)!");
                guessed = true;
                break;
            }

            attemptsLeft--;

            if (guess > secretNumber) {
                System.out.print("  Too High!  ");
            } else {
                System.out.print("  Too Low!   ");
            }

            int diff = Math.abs(guess - secretNumber);
            if      (diff <= 5)  System.out.println("(Very close!)");
            else if (diff <= 15) System.out.println("(Getting warm...)");
            else                 System.out.println("(Way off.)");
        }

        if (!guessed) {
            System.out.println("\n  GAME OVER! Out of attempts.");
            System.out.println("  The correct number was: " + secretNumber);
        }

        return guessed;
    }

    private static int readIntSafely(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("  Invalid input! Enter a whole number: ");
            }
        }
    }

    private static void printHeader(String title) {
        System.out.println("\n==========================================");
        System.out.println("  " + title);
        System.out.println("==========================================");
    }
}