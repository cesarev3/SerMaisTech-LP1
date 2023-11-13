package Aulas;

public class notas_aula_variaveis {
    public static void main(String[] args) {
        tipoString();
        tipoCharacter();
        tipoNumerico();
        operadorBitwise();
        operadorTernario();
    }

    private static void operadorTernario() {
        // ternario
        byte idade = 17;
        String resultado = // identacao
                ( idade >= 18 )
                        ? "maior" : "menor";

    }

    private static void tipoString() {
        // string possui 2 bytes sob codificação UTF-16
        // UTF-16 permite representar caracteres para muitas línguas e símbolos

        String t1;
        String t2 = "texto";
        String t3 = new String("texto");
        String t4 = "hello" + " " + "world"; // 4 strings
        // mostrar funcoes e entrar dentro do codigo

        String name = "Roberto Carlos";
        System.out.printf("Olá, %s.", name);

        int age = 25;
        System.out.printf("Sua idade é %d", age);

        double altura = 1.75;
        System.out.printf("Sua altura é %.2f metros", altura);

        char nota = 'B';
        System.out.printf("Sua nota foi %c", nota);

        int percentual = 75;
        System.out.printf("Você acertou %d%% do teste", percentual);

        // para nova linha = /n .. tab = /t
    }

    private static void tipoCharacter() {
        // Usando o tipo primitivo char
        char myChar = 'A';
        System.out.println("Caractere: " + myChar);

        // Convertendo um char em uma String
        String charAsString = Character.toString(myChar);
        System.out.println("Char convertido para String: " + charAsString);

        // Usando a classe Character
        Character myCharacter = 'B';

        // Verificando se um caractere é uma letra
        if (Character.isLetter(myCharacter)) {
            System.out.println(myCharacter + " é uma letra.");
        }

        // Convertendo um caractere em maiúsculo
        char upperCaseChar = Character.toUpperCase(myCharacter);
        System.out.println("Em maiúsculo: " + upperCaseChar);

        // Convertendo um caractere em minúsculo
        char lowerCaseChar = Character.toLowerCase(myCharacter);
        System.out.println("Em minúsculo: " + lowerCaseChar);
    }

    private static void tipoNumerico() {
        // short (2 bytes): -32.768 a 32.767
        // int (4 bytes): -2.147.483.648 a 2.147.483.647
        // long (8 bytes): -9.223.372.036.854.775.808 a 9...

        // ponto flutuante .. `unidade lógica arquitetura`
        // float (4 bytes): aprox. +- 3.40282347E+38F
        //      (6-7 dígitos decimais significativos)
        //double (8 bytes): aprox. +- 1.797691313486231570E+308
        //      (15 dígitos decimais significativos)
        //      8 bytes = 2^3 * 8 = 64 bits

        // representacoes
        long maximoLong = Long.MAX_VALUE;
        long creditCardNumber = 1234_5678_9012_3456L;
        long socialSecurityNumber = 999_99_9999L;
        float pi =  3.14_15F;
        long hexBytes = 0xFF_EC_DE_5E;
        long hexWords = 0xCAFE_BABE;
        long maxLong = 0x7fff_ffff_ffff_ffffL;
        byte nybbles = 0b0010_0101;
        long bytes = 0b11010010_01101001_10010100_10010010;

        // constantes .. convenção maiúscula e lembrar do final
        final int QUANTIDADE_TENTATIVAS = 3;
        final double PI = 3.14159265358979323846; // já existe o Math.PI

        // unverflow e overflow
        byte a = -1;
        byte x = (byte) (Byte.parseByte("-128") + a);
        int y = Byte.parseByte("-128") + a;

        System.out.println( x );
        System.out.println( y );
        // qual resultado de x e y? .. qual motivo?
    }

    private static void operadorBitwise() {
        // operador ~ TIL o não bitwise - inverte os bits
        int a = 45;
        int b = ~a;

        System.out.println(b); // -46
        System.out.println( Integer.toBinaryString(a) ); // ..00.101101
        System.out.println( Integer.toBinaryString(b) ); // ..11.010010 - mudou bits incluindo sinal

        // convertendo de binario para inteiro novamente
        String binaryString = Integer.toBinaryString(b);
        int decimal = Integer.parseUnsignedInt(binaryString, 2); // parse binario sem sinal
        System.out.println( decimal); // -46

        // para conferir
        // https://www.rapidtables.com/convert/number/binary-to-ascii.html
    }

}