import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String inp = "";


        while (!inp.equals("quit")){

            System.out.print("Input: ");
            inp = sc.nextLine().trim();
            String[] inpArr = inp.split(" ");

            if(inpArr.length == 2){
                try {
                    UnaryOperator<Double> operation = getUnaryOperation(inpArr[0]);
                    double operator = Double.parseDouble(inpArr[1]);
                    System.out.println(inpArr[0]+"(" + inpArr[1] + ")" + " = " + operation.apply(operator));
                } catch (UnknownOperationException e) {
                    inp = "rules";
                } catch (NumberFormatException e){
                    System.out.println("Number for operation has to be a double");
                }
            } else if (inpArr.length == 3){
                try {
                    double a = Double.parseDouble(inpArr[0]);
                    BinaryOperator<Double> operation = getBinaryOperation(inpArr[1]);
                    double b = Double.parseDouble(inpArr[2]);
                    System.out.println(inp + " = " + operation.apply(a,b));
                } catch (NumberFormatException e){
                    System.out.println("Number for operation has to be a double");
                } catch (UnknownOperationException e){
                    inp = "rules";
                }
            } else if(inp.equals("rules")) {
                System.out.println("Allowed operations: a + b, a - b, a * b, a / b, sin x, cos x, tan x, cotan x");
            } else if(!inp.equals("quit"))System.out.println("there seems to be a typo (" + inp + "). Type 'quit' for quitting, 'rules' for rules");


        }

        System.out.println("goodbye");
        sc.close();
    }


    public static UnaryOperator<Double> getUnaryOperation(String s) throws UnknownOperationException {
        switch (s){
            case "sin":  return Math::sin;
            case "cos":  return Math::cos;
            case "tan":  return a -> Math.sin(a) / Math.cos(a);
            case "cotan":  return a ->1/ (Math.sin(a) / Math.cos(a));
            default: throw new UnknownOperationException(s);
        }
    }

    public static BinaryOperator<Double> getBinaryOperation(String s) throws UnknownOperationException {
        switch (s){
            case "+":  return Double::sum;
            case "-":  return (a,b) -> a-b;
            case "*":  return (a,b) -> a*b;
            case "/":  return (a,b) -> a/b;
            default: throw new UnknownOperationException(s);
        }
    }
}
