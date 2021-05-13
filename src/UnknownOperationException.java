public class UnknownOperationException extends Exception{
    public UnknownOperationException(String inp) {
        System.out.println("Wrong input, Can not process. " + inp);
    }
}
