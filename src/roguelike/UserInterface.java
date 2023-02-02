package roguelike;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    public void run() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        clearScreen();

        System.out.print("Enter the name of your hero: ");
        String name = scanner.nextLine();

        int maxLength = 40;
        int maxHeight = 40;
        GameEngine engine = new GameEngine(maxLength, maxHeight);

        clearScreen();
        engine.start(name);

        String input;
        do {
            input = scanner.nextLine().toLowerCase();

            while (!checkIfCommandIsLegal(input)){
                System.out.println("No such command!");
                input = scanner.nextLine().toLowerCase();
            }

            clearScreen();
            Code code = engine.run(input);

            if (code == Code.GAME_OVER) {
                clearScreen();
                System.out.println("Game over!");
                return;
            }

            if(code == Code.NEXT_LEVEL){
                clearScreen();
                engine.run(input);
            }

        } while (!input.equals("q"));
        clearScreen();
    }

    private void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkIfCommandIsLegal(String command){
        return command.equals("w") || command.equals("a") || command.equals("s") || command.equals("d") || command.matches("drop+\\s\\d+" ) || command.equals("q") ||
                command.equals("i") || command.equals("j") || command.equals("k") || command.equals("l") || command.matches("break (left|right|up|down)");
    }
}