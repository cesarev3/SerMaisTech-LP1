package Aulas;

import java.math.BigDecimal;

public class variaveis {
    public static void main(String[] args) {
        String texto0 = new String();
        String texto1 = "";
        String texto2 = new String("texto2");
        String texto3 = "hello " + "world";
        String texto4 = texto3 + texto2;

        System.out.println(texto0);
        System.out.println(texto1);
        System.out.println(texto2);
        System.out.println(texto3);
        System.out.println(texto4);

        // wrappers - tipos comuns - imutáveis
        // 2 bytes - UTF-16 (unicod transform format)
        String texto = "a";
        // 1 byte = 8 bits (binaria = 0/1)

        int a = Integer.valueOf(args[0]); // boxing - autoboxing
        Integer c = Integer.parseInt(args[0]);
        Integer b = 0;

        String abc = "12345";
        String data = "05/06/1963";
        System.out.println(data.split("/"));

//        Integer a1 = null;
//        int a2 = a1;
//        if ( a1 != null) {
//            a1.intValue();
//        }
//        System.out.println(a2);

        char x = 'a';

        BigDecimal big = BigDecimal.ONE;
        Double pi = Math.PI;

        byte b1 = -128;
        byte b2 = +2;
        System.out.println((byte)(b1 + b2));

        // # representacoes
        Long max = Long.MAX_VALUE;
        long creditCardNumber = 1234_5678_9012_3456L;
        long socialSecurityNumber = 999_99_9999L;
        long hexBytes = 0xFF_EC_DE_5E; // base 16
        long hexWords = 0xCAFE_BABE;
        long maxLong = 0x7fff_ffff_ffff_ffffL;
        byte nybbles = 0b0010_0101;
        long bytes = 0b11010010_01101001_10010100_10010010;

        System.out.println(creditCardNumber);

        // operador ternário
        int n = 35;
        String s = (n % 2 == 0) ? "par" : "impar";
        System.out.println(s);



        // byte = -128 a 127 -> overflow - underflow
        // decima 2 = 001

    }
}
