import roguelike.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter the name of your hero: ");
        String name = new Scanner(System.in).next();
        UserInterface ui = new UserInterface(15,15, name);
        ui.run();
    }
}