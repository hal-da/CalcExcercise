import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String inputString;
        System.out.println("Welcome to the calculator. Type 'quit' for quitting, 'help' for help.");

        while (true){

            System.out.print("Input: ");
            inputString = sc.nextLine().trim().replaceAll("\\s+", " ");

            if (inputString.equals("quit"))break;

            String[] inpArr = inputString.split(" ");

            if(inpArr.length == 2 || inpArr.length == 3){
                Calculator.calculate(inpArr);
                continue;
            }

            if(inputString.equals("help")) {
                Calculator.printHelp();
            } else {
                //could not do anything, got to be wrong input
                Calculator.printWrongInput(inputString);
            }

        }

        System.out.println("goodbye");
        sc.close();
    }
}
