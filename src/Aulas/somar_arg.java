package Aulas;

public class somar_arg {
    public static void main(String[] args) {

        double n1 = Double.parseDouble(args[0]);
        double n2 = Double.parseDouble(args[1]);

        System.out.println(somarArgumentos(n1, n2));
    }

    public static double somarArgumentos(double n1, double n2){
        return n1 + n2;
    }
}