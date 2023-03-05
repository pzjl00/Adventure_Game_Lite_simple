import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //objects
        Scanner input = new Scanner(System.in);
        //Game variables
        String zombie = "zombie";
        int maxZombieHealth = 100;
        //player information
        System.out.println("How old are you?");
        double userAge = input.nextInt();
        //player variables
        int health = 100;
        int numArrows = 4;
        int numMedicine = 3;
        int medicineHeal = 30;
        int chanceMedicine = medicineChance((int) userAge); // chance that the player gets medicine after killing a zombie.

        boolean playing = true;

        System.out.println("Welcome to the zombie apocalypse!");
        GAME:
        while (playing) {
            int zombieHealth = (int) (maxZombieHealth * (userAge % 10));
            System.out.println(zombie + "appeared in your classroom! \n");
            while (zombieHealth > 0) {
                playerStats(health, numArrows, numMedicine);
                System.out.println("What would you like to do? If you would like to view your personal statistics later on in the game, enter the letter s.");
                String action = input.nextLine();
                if (action.equals("s")) {
                    System.out.println(playerStats(health, numArrows, numMedicine));
                }
                else if (action.contains("run")) {
                    System.out.println("You spin around frantically and run away from the zombie.");
                    continue GAME;
                }
                else if (action.contains("attack")) {
                    int damageCaused = (int)userAge + 5;
                    int damageTaken = (int)userAge - 5;

                    zombieHealth -= damageCaused;
                    health -= damageTaken;

                    System.out.println("> You hit the zombie with an arrow.");
                    System.out.println("Damage received = " + damageTaken);

                    if (health <= 0) {
                        System.out.println("Unfortunately, the zombie has fatally injured your before you could kill it.");
                        break;
                    }
                }
                else if ((action.equals("drink healing potion")) || (action.equals("drink potion"))) {
                    if(numMedicine > 0) {
                        System.out.println(medicineEffect(health, medicineHeal));
                        numMedicine --;
                        System.out.println("You drink a healing potion. Almost immediately, your wounds start to heal and colour returns to your face. You feel more energized.\n You now have " + health + "health.\n" + "You have " + numMedicine + "healing potions.");
                    }
                    else {
                        System.out.println("You have no healing potions left! Kill zombies for a chance to collect them!");
                    }
                }
                else {

                }
            }
        }
    }
    public static int medicineChance(int age) {
        return (age/2);
    }
    public static String playerStats (int health, int numArrows, int numMedicine) {
        return "Your health: " + health + "\nYou have " + numMedicine + "healing potions" + "Your weapon is a bow. You currently have " + numArrows + "arrows.";
    }
    public static int medicineEffect(int health, int medicineHeal) {
        return health + medicineHeal;
    }
}