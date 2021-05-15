import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Calculator {
    public Calculator() {
    }

    public static void calculate(String[] inpArr){

        if(inpArr.length == 2) calcUnaryOperation(inpArr);
        else calcBinaryOperation(inpArr);

    }

    public static void calcUnaryOperation(String[] inpArr){
        try {
            UnaryOperator<Double> operation = getUnaryOperation(inpArr[0]);
            double operator = Double.parseDouble(inpArr[1]);
            System.out.println(inpArr[0]+"(" + inpArr[1] + ")" + " = " + operation.apply(operator));
        } catch (UnknownOperationException e) {
            printHelp();
        } catch (NumberFormatException e){
            System.out.println("Input was not a number. ");
            printHelp();
        }
    }

    public static void calcBinaryOperation(String[] inpArr){
        try {
            double a = Double.parseDouble(inpArr[0]);
            BinaryOperator<Double> operation = getBinaryOperation(inpArr[1]);
            double b = Double.parseDouble(inpArr[2]);
            System.out.println(String.join(" ",inpArr) + " = " + operation.apply(a,b));
        } catch (NumberFormatException e){
            System.out.println("Input was not a number. ");
            printHelp();
        } catch (UnknownOperationException e){
            printHelp();
        }
    }

    public static UnaryOperator<Double> getUnaryOperation(String s) throws UnknownOperationException {
        switch (s){
            case "sin":  return Math::sin;
            case "cos":  return Math::cos;
            case "tan":  return a -> Math.sin(a) / Math.cos(a);
            case "cotan":  return a -> 1 / (Math.sin(a) / Math.cos(a));
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
    public static void printHelp(){
        System.out.println("Allowed operations: a + b, a - b, a * b, a / b, sin x, cos x, tan x, cotan x");
    }


    public static void printWrongInput(String inputString){
        System.out.println("Wring input. Can not compute '" + inputString + "' at all. Type 'quit' for quitting, 'help' for help");
    }
}
