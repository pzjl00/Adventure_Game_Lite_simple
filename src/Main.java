import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //objects
        Scanner input = new Scanner(System.in);
        //Game variables
        int maxZombieHealth = 100;
        //player information
        System.out.println("Enter a random number that is a positive integer");
        double userAge = input.nextInt();
        //player variables
        int health = 100;
        int numArrows = 4;
        int numMedicine = 3;
        int medicineHeal = 30;
        int chanceMedicine = medicineChance((int) userAge); // chance that the player gets medicine after killing a zombie.

        boolean playing = true;

        System.out.println("Welcome to the zombie apocalypse!");
        System.out.println("You were having a self study session alone when a zombie appeared in your classroom!");
        System.out.println("If you would like to view your personal statistics later on in the game, enter the letter s. Please also note that all commands in this game contain one word only.");
        GAME:
        while (playing) {
            int zombieHealth = (int) (maxZombieHealth * (userAge/100));
            while (!(zombieDead(zombieHealth))) {
                playerStats(health, numArrows, numMedicine);
                System.out.println("What would you like to do?");
                String action = input.next();
                if (action.equals("s")) {
                    System.out.println(playerStats(health, numArrows, numMedicine));
                }
                else if (action.contains("run")) {
                    System.out.println("You spin around frantically and run away from the zombie. It chases after you.");
                    continue GAME;
                }
                else if (action.contains("attack") || action.contains("shoot")) {
                    int damageCaused = (int)damageCaused(userAge);
                    int damageTaken = (int)userAge - 5;

                    zombieHealth = zombieHealth(zombieHealth, damageCaused);
                    health = health(health, damageTaken);
                    numArrows = numArrows(numArrows);

                    System.out.println("You shoot the zombie with an arrow.");
                    System.out.println("Damage received = " + damageTaken);
                    System.out.println("Damage caused = " + damageCaused);
                    System.out.println("Current zombie health = " + zombieHealth);

                    if (health <= 0) {
                        System.out.println("Unfortunately, the zombie has fatally injured your before you could kill it.");
                        break;
                    }
                    if ((noArrows(numArrows))) {
                        System.out.println("Oh no, you have run out of arrows! You watch in horror as the zombie pounces at you.");
                        health = -1;
                        break;
                    }
                }
                else if (action.equals("drink")) {
                    if(numMedicine > 0) {
                        health = (medicineEffect(health, medicineHeal));
                        numMedicine --;
                        System.out.println("You drink a healing potion. Almost immediately, your wounds start to heal and colour returns to your face. You feel more energized.\nYou now have " + health + " health.\n" + "You now have " + numMedicine + " healing potions.");
                    }
                    else {
                        System.out.println("You have no healing potions left! Kill zombies for a chance to collect them!");
                    }
                }
                else {
                    System.out.println("Sorry, I don't understand what you're saying.");
                }
            }
            if (health < 1) {
                System.out.println("You are barely able to stay conscious as you stumble towards the door, unable to fight for any longer. \nGAME OVER");
                break;
            }
            System.out.println("The zombie has been defeated. You win!");
            System.out.println("Your remaining health: " + health);
            if (chanceMedicine >= 20) {
                medicine(numMedicine);
                System.out.println("You now have " + numMedicine + " healing potion(s)");
            }
            System.out.println("Would you like to fight or leave?");
            String choice = input.next();
            if (!choice.contains("fight") && !choice.contains("kill") && !choice.contains("leave") && !choice.contains("exit")) {
                System.out.println("Sorry, I don't understand what you're saying.");
                choice = input.next();
            }

            if (choice.contains("fight") || choice.contains("kill")) {
                System.out.println("You eagerly go to the corridor to find more zombies. Soon, another zombie appears in the classroom you enter.");
                zombieHealth = 100;
            }
            else if(choice.contains("leave") || choice.contains("exit")) {
                System.out.println("You exit the school victorious from combat.");
                break;
            }
        }
        input.close();
    }
    public static int medicineChance(int age) {
        if (age % 2!= 0) {
            return (age+1)/2;
        }
        return (age/2);
    }
    public static String playerStats (int health, int numArrows, int numMedicine) {
        return "Your health: " + health + "\nYou have " + numMedicine + " healing potions." + " Your weapon is a bow. You currently have " + numArrows + " arrows.";
    }
    public static boolean zombieDead(int zombieHealth) {
        return zombieHealth < 0;
    }
    public static int medicineEffect(int health, int medicineHeal) {
        return health + medicineHeal;
    }
    public static int medicine(int numMedicine) {
        System.out.println("After the zombie drops dead, a bottle of healing potion appears at your feet.");
        return numMedicine + 1;
    }
    public static double damageCaused(double userAge) {
        return (userAge - 10) * 2;
    }
    public static int numArrows(int numArrows) {
        return numArrows - 1;
    }
    public static boolean noArrows(int numArrows) {
        boolean noArrows = false;
        return numArrows < 0;
    }
    public static int zombieHealth(int zombieHealth, int damageCaused) {
        return zombieHealth - damageCaused;
    }
    public static int health(int health, int damageTaken) {
        return health - damageTaken;
    }
}