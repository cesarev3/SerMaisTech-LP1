package Aulas;

public class while_doWhile_for {
    public static void main(String[] args) {

        boolean condicao = true;
        int idade = 20;

        while( idade-- > 18 ) {
            System.out.println( idade + " while");

        }

        System.out.println( idade + " parada");

        do {
            System.out.println( idade + " do-while");
        } while( --idade > 16);

//        int i = 0;
//
//        System.out.println("0 == " + i);
//        System.out.println(i++ == 1 ? "1 == " + i : "1 != " + i);
//        System.out.println(++i == 2 ? "2 == " + i : "2 != " + i);

        int[] cartela = {1,2,3,4,5};
        for (int i = 0; i < cartela.length; i++) { // i = i + 1
            int valor = cartela[i];
            if ( valor % 2 == 0 ) {
                System.out.println( "par = " + cartela[i] );
            }
        }
    }
}
