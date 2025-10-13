/**
 * A program that takes two names as command line arguments and prints
 * a hello message to both names and a goodbye message with names in reverse order.
 * <p>
 * How to run:
 * 1. Navigate to project root:
 * cd data-structures-algorithms
 * 2. Compile:
 * javac -d build src/0.GettingStarted/HelloGoodbye.java
 * (Compiles and puts .class file in build folder)
 * 3. Run:
 * java -cp .\build HelloGoodbye <firstname> <secondname>
 * Eg: java -cp .\build HelloGoodbye Alice Bob
 * (Runs the program by looking for class in build folder)
 */
public class HelloGoodbye {
    public static void main(String[] args) {
        // Check if exactly two arguments are provided
        if (args.length != 2) {
            System.out.println("Please provide exactly two names as arguments");
            return;
        }

        // Store the command line arguments in variables
        String name1 = args[0];
        String name2 = args[1];

        // Print hello message with names in original order
        System.out.printf("Hello %s and %s.\n", name1, name2);
        // Print goodbye message with names in reverse order
        System.out.printf("Goodbye %s and %s.\n", name2, name1);
    }
}
