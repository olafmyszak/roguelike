package roguelike;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private final int length;
    private final int height;

    public UserInterface(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public void run() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        playTitleScreen();

        System.out.print("Enter the name of your hero: ");
        String command = scanner.next();

        GameEngine engine = new GameEngine(length, height, command);

        clearScreen();
        engine.start();
        do {
            command = scanner.next();
            clearScreen();
            engine.run(command);
        } while (!command.equalsIgnoreCase("q"));
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

    private void playTitleScreen() throws InterruptedException {
        /*
        System.out.println("""
                ░░░░░▄▄▄▄▀▀▀▀▀▀▀▀▄▄▄▄▄▄░░░░░░░
                ░░░░░█░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░▀▀▄░░░░
                ░░░░█░░░▒▒▒▒▒▒░░░░░░░░▒▒▒░░█░░░
                ░░░█░░░░░░▄██▀▄▄░░░░░▄▄▄░░░░█░░
                ░▄▀▒▄▄▄▒░█▀▀▀▀▄▄█░░░██▄▄█░░░░█░
                █░▒█▒▄░▀▄▄▄▀░░░░░░░░█░░░▒▒▒▒▒░█
                █░▒█░█▀▄▄░░░░░█▀░░░░▀▄░░▄▀▀▀▄▒█
                ░█░▀▄░█▄░█▀▄▄░▀░▀▀░▄▄▀░░░░█░░█░
                ░░█░░░▀▄▀█▄▄░█▀▀▀▄▄▄▄▀▀█▀██░█░░
                ░░░█░░░░██░░▀█▄▄▄█▄▄█▄████░█░░░
                ░░░░█░░░░▀▀▄░█░░░█░█▀██████░█░░
                ░░░░░▀▄░░░░░▀▀▄▄▄█▄█▄█▄█▄▀░░█░░
                ░░░░░░░▀▄▄░▒▒▒▒░░░░░░░░░░▒░░░█░
                ░░░░░░░░░░▀▀▄▄░▒▒▒▒▒▒▒▒▒▒░░░░█░
                ░░░░░░░░░░░░░░▀▄▄▄▄▄░░░░░░░░█░░""");

                    LEGACY VERSION

        scanner.nextLine();
        clearScreen();

         */

        clearScreen();
        System.out.println("""
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣄⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⡿⠟⠉⠉⠉⠉⠉⠉⠉⠀⢀⣀⣠⠤⠶⠶⠶⠶⠦⠤⠤⠄⠈⣉⡉⠉⠉⠉⠛⠛⠻⠿⢿⣿⣿⣶⣶⣶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡿⠋⠀⠀⠀⠀⠀⠀⢀⣤⠖⠛⠉⣁⣠⣤⠤⠴⠶⠶⠶⠤⢤⣤⣀⡀⠀⠀⠀⠀⠉⠉⠉⠉⠉⣀⣀⣀⣤⣍⡙⠻⢿⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⠟⠀⠀⠀⠀⠀⣀⠴⠚⢉⡤⠔⢚⣉⡩⠤⠤⠤⠤⠤⠤⣤⠄⠀⠀⠀⠈⠉⠉⠀⠀⠀⠉⠉⣉⣉⣀⣀⣀⠀⠈⠙⠀⠀⠈⠻⢿⣶⣄⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠏⠀⠀⠀⢀⡴⠊⠁⣠⠞⣉⡤⠞⠋⠀⠀⠀⠀⠀⠀⠀⠀⠈⢳⡀⠀⠀⠀⠀⠀⠀⠀⠾⢛⡍⠉⠀⠀⠀⠉⠛⠦⣄⠀⠀⠀⠀⠀⠙⣿⡇⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⢠⣿⠏⠀⠀⠀⠀⠈⠀⠠⠞⢁⡞⠉⠀⠀⠀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⢰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠘⢇⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⢀⣴⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⣠⣶⣿⣿⣿⣿⣿⣿⠿⠿⠿⣶⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⣀⣀⣤⣤⣤⣤⣄⠀⠈⠀⠀⠀⠀⠀⠸⣿⣆⠀⠀⠀
                ⠀⠀⠀⢀⣴⣿⣿⣿⣖⠒⠦⢄⡀⠀⠀⣤⣤⡖⢀⣾⣿⣭⣿⣿⣿⣿⣿⣿⣤⣄⡀⠈⠙⣿⣷⠀⠀⠀⠀⢀⣀⡀⣠⣴⣿⣿⣿⣿⣯⣭⣿⣿⣷⠄⢤⣄⣀⣀⣀⣀⠈⠻⣷⣤⡀
                ⠀⠀⣴⣿⢟⡿⠁⠀⣀⣤⣶⣶⣾⣶⣤⣈⠀⠚⠉⠉⠉⠉⠁⠀⣀⣴⡆⠉⠉⠛⠿⣿⣶⣿⠿⠀⠀⠀⠀⠘⠿⢿⣿⡿⠿⠛⠋⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⢰⣌⠛⢦⡈⢿⣿
                ⠀⣼⡟⢡⡞⠀⢠⣾⡿⠋⠁⠀⣰⡈⠛⠿⣿⣷⣦⣤⣤⣤⣴⣾⠿⠋⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⣀⡀⠀⠀⣠⣶⣶⣶⣶⣶⣄⢹⡇⠙⡆⣿
                ⢸⣿⠁⢸⡇⠀⣿⡏⠀⠀⠀⢰⣿⣧⣄⡀⠀⠈⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⢀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣷⣄⡀⠀⠀⠀⠻⢿⣶⣿⣿⠋⣰⡄⠀⠙⠛⠈⡇⠀⡇⣿
                ⢸⣿⠀⢸⡇⠀⣿⡄⢠⣤⣶⣿⣿⠛⠻⢿⣷⣤⣄⡀⠀⠀⠀⠀⣀⡤⠤⢤⣤⠾⣴⡿⠿⠿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣦⣄⠀⠀⠀⠀⠀⠀⠀⣿⣇⡀⠀⢀⣠⠇⢀⢧⣿
                ⠘⣿⣇⠈⢇⠀⢿⣷⡈⠉⠁⢹⣿⣄⠀⠀⠀⠙⠻⣿⣿⣶⣤⣀⡀⠀⠀⠀⠀⠀⢿⣧⡀⢶⣶⣾⣶⣶⡄⠀⠀⠀⠀⠀⣴⣿⢿⠿⠳⠦⣀⡀⠀⠀⣠⣿⣿⣧⠀⠈⣁⡤⢾⣿⡿
                ⠀⠙⣿⣆⠘⠦⣌⣙⠃⠀⠀⠘⢿⣿⣷⣤⣀⡀⠀⣿⣇⠉⠙⠛⠻⠿⢷⣶⣤⣤⣌⣻⠗⠈⠁⠀⠀⠀⠀⠀⣶⣦⣴⣾⠟⠁⠀⠀⠀⠀⠀⢉⣠⣾⣿⣿⣿⣿⠀⠉⠀⢀⣾⡟⠀
                ⠀⠀⠈⢻⣷⣦⣤⠉⠁⠀⠀⠀⠀⠹⣿⣟⠻⢿⣷⣿⣿⣦⣀⠀⠀⠀⠀⠈⢙⣿⠿⠿⠿⣷⣶⣶⣤⣤⣤⣀⣈⣉⣉⣁⣀⣀⣀⣀⣤⣤⣶⠿⠻⣿⡏⢻⣿⣿⡆⠀⠀⣾⡟⠀⠀
                ⠀⠀⠀⠀⠙⢿⣷⣄⠀⠀⠀⠀⠀⠀⠙⣿⣦⠀⠀⣿⣿⣿⣿⣿⣶⣶⣤⣀⣾⡿⠀⠀⠀⠀⠀⠈⠉⣿⡏⠉⠛⠛⢻⣿⡟⠛⠋⠉⠉⣿⣧⠀⠀⣿⣇⣸⣿⣿⡇⠀⢸⣿⠁⠀⠀
                ⠀⠀⠀⠀⠀⠀⠹⣿⣇⠀⠀⠀⠀⠀⠀⠈⠻⣷⣤⣼⡿⠀⠈⠙⠛⠿⣿⣿⣿⣷⣶⣶⣤⣤⣀⣀⣀⣿⣇⣀⣀⣀⣀⣿⣇⣀⣀⣤⣤⣼⣿⣶⣾⣿⣿⣿⣿⣿⠇⠀⢸⣿⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⢻⣿⣄⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣅⡀⠀⠀⠀⠀⠀⢸⣿⠛⠛⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⢸⣿⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣦⣀⠀⠀⢠⣿⠏⠀⠀⠀⠀⠀⠈⢹⣿⠛⠿⠿⠿⠿⣿⣿⣿⡿⣿⣿⣿⣿⢿⣿⡿⢿⣿⢟⣿⡟⠀⠀⢸⣿⡆⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣷⣦⡀⢀⣄⡀⠀⣀⣀⠀⠀⠉⠻⢿⣶⣿⡏⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⣾⡟⠀⠀⢀⣿⡟⠀⣼⣿⠁⢼⣿⣾⡿⠁⠀⠀⠈⣿⡇⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣦⣈⠙⠲⢤⣈⠙⠲⢤⣀⠀⠈⠙⠛⠿⢷⣶⣦⣤⣤⣄⣼⣿⣀⣀⣀⣀⣀⣿⣄⣀⣠⣾⣟⣠⣴⣿⣷⣶⠿⠟⠋⠀⠀⠀⠀⠀⢿⡇⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠿⣿⣦⣄⡉⠓⠦⢤⣈⠑⠲⢤⣄⡀⠀⠈⠉⠉⠉⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠋⠉⠉⠉⠀⠀⢀⡆⠀⠀⡀⠀⠀⢸⡇⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣶⣤⣀⡈⠉⠓⠶⢤⣉⡛⠶⢤⣀⣀⠀⠀⠀⠈⠉⠉⠉⠘⠒⠒⠒⠀⠀⠀⠀⠀⠀⢀⣠⠴⠋⠀⠀⠀⡇⠀⠀⢸⣿⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠛⠻⣿⣶⣦⣀⠀⠈⠉⠓⠒⠢⠭⣭⣀⣀⠀⠉⠉⠉⠉⠉⠉⠀⠒⠒⠒⠋⠉⠉⠀⠀⠀⣠⡴⠚⠁⠀⠀⢸⣿⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣿⣶⣄⡀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠑⠒⠒⠒⠒⠒⠒⠒⠒⠒⠒⠒⠋⠁⠀⠀⠀⠀⢀⣾⡿⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⣷⣦⣤⣤⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⡟⠁⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠛⠿⠿⣿⣶⣶⣤⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣶⡿⠟⠁⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠛⠛⠛⠻⠿⠿⠿⠿⠟⠛⢛⢋⠉⡁⠀⠀⠀⠀⠀⠀⠀""");


        Thread.sleep(3000);
        clearScreen();


        System.out.println("""
                ⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⢠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⢸⠀⠀⠀⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⢸⣀⡀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⢠⠏⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⢸⠀⢹⠀⡇⠀⠋⢳⠋⢹⠀⢰⠒⡇⡞⠀⢸⠀⢯⡁⠀⣰⠃⠐⢶⡤⢰⠒⣶⠀⢶⡶⢳⡴⢲⡼⠟⢸⢙⡶⠟⣦⢫⠖⠚⢦⠀
                ⢸⠀⠀⡇⡇⠀⠀⢸⠀⢰⠁⣮⠀⣿⢸⣀⡏⠀⠀⢹⠀⡏⠀⠀⠀⢸⠀⠑⠒⠛⠒⠸⠃⠀⠇⠈⠙⠒⢸⠉⠈⠀⠁⢯⣀⣰⠃
                ⠈⠀⠀⠁⠁⠀⠀⠀⠁⠈⠉⠉⠉⠉⠀⠉⠀⠉⠉⠉⠀⠓⠦⠤⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀""");


        Thread.sleep(3000);
        clearScreen();
    }
}