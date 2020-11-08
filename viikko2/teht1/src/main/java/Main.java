import java.util.*;
import ohtu.Multiplier;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Multiplier m = new Multiplier(5);
    System.out.println("Gimme number: ");
    int number = scanner.nextInt();
    System.out.println("That times 5 is: " + m.multipliedBy(number));
  }
}