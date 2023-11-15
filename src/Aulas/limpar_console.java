package Aulas;

//
//import java.io.IOException;
//
//public class limpar_console {
//    public static void main(String[] args) throws IOException {
//        System.out.println("teste de limpeza de tela");
//        try {
//            Runtime.getRuntime().exec("cls");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("teste de novo");
//    }
//    public final static void clearConsole()
//    {
//        try
//        {
//            final String os = System.getProperty("os.name");
//
//            if (os.contains("Windows"))
//            {
//                Runtime.getRuntime().exec("cls");
//            }
//            else
//            {
//                Runtime.getRuntime().exec("clear");
//            }
//        }
//        catch (final Exception e)
//        {
//            //  Handle any exceptions.
//        }
//    }
//}
import java.io.IOException;
import java.util.Scanner;

public class limpar_console {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Teste");
        String texto = scanner.next();

        //Limpa a tela no windows, no linux e no MacOS
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");

    }
}