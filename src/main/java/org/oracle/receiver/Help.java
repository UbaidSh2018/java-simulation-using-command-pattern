package org.oracle.receiver;

/**
 * Help Manual displaying available commands with description.
 */
class Help {

    static void simulationHelp() {

        System.out.printf("%n %15s %n%n", "-------------------------------------Help Manual------------------------------------------");

        System.out.printf("%-20s", "No. Command");
        System.out.printf("%-15s %n", "Info");

        System.out.printf("%-20s", "1. Advance <n>");
        System.out.printf("%-15s %n", "Moves the Bulldozer one unit forward in facing direction.");
        System.out.printf("%-20s", "");
        System.out.printf("%-35s %n", "<n> - Positive integer to define the number of squares the bulldozer should move forwards.");

        System.out.printf("%-20s", "2. Left");
        System.out.printf("%-15s", "Rotates the Bulldozer 90 degrees to the left of the direction it is facing.");
        System.out.printf("%-15s %n", "");

        System.out.printf("%-20s", "3. Right");
        System.out.printf("%-15s", "Rotates the Bulldozer 90 degrees to the right of the direction it is facing.");
        System.out.printf("%-15s %n", "");

        System.out.printf("%-20s", "4. Quit");
        System.out.printf("%-15s", "Terminates Simulation.");
        System.out.printf("%-15s %n", "");

        System.out.printf("%-15s %n", "Note: ");
        System.out.printf("%-15s %n", "- The simulation only accepts one command per line.");
        System.out.printf("%-15s %n", "- Help command does not incur any communication costs.");
        System.out.printf("%-15s ", "- Commands are not case-sensitive.");

        System.out.printf("%n %15s %n%n", "-------------------------------------------------------------------------------------------");


    }

}
