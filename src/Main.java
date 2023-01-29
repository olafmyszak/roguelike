import roguelike.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        try {
            ui.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}