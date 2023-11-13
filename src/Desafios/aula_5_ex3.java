package Desafios;

public class aula_5_ex3 {
    public static void main(String[] args) {
        // String fornecida
        String palavra01 = " Leão-Marinho ";

        // 01. Obter o tamanho do texto da variável palavra01
        int tamanho = palavra01.length();
        System.out.println("01. Tamanho do texto: " + tamanho);

        // 02. Trocar o caractere com acento por um equivalente sem acento
        String semAcento = palavra01.replace("ã", "a").replace("é", "e");
        System.out.println("02. Texto sem acento: " + semAcento);

        // 03. Separar em duas strings por hífen e imprimi-las separadamente
        String[] partes = palavra01.split("-");
        System.out.println("03. Parte 1: " + partes[0].trim());
        System.out.println("    Parte 2: " + partes[1].trim());

        // 04. Obter a posição do caractere 'i'
        int posicaoI = palavra01.indexOf('i');
        System.out.println("04. Posição do 'i': " + posicaoI);

        // 05. Obter a string do item 02 e retornar se são iguais
        boolean saoIguais = semAcento.equals(partes[0].trim());
        System.out.println("05. São iguais? " + saoIguais);

        // 06. Pegar as duas strings separadas no item 03 e juntar com método concat
        String juntas = partes[0].trim().concat(partes[1].trim());
        System.out.println("06. Juntas: " + juntas);

        // 07. Colocar tudo em minúsculo
        String minusculo = juntas.toLowerCase();
        System.out.println("07. Minúsculo: " + minusculo);

        // 08. Remover os espaços em branco nas laterais
        String semEspacos = minusculo.trim();
        System.out.println("08. Sem espaços laterais: " + semEspacos);

        // 09. Após item 08, verificar se a variável termina com 'nho' e indicar se sim ou não
        boolean terminaComNho = semEspacos.endsWith("nho");
        System.out.println("09. Termina com 'nho'? " + terminaComNho);

        // 10. Verificar se palavra01 é nula ou vazia (em branco)
        boolean nulaOuVazia = palavra01 == null || palavra01.trim().isEmpty();
        System.out.println("10. É nula ou vazia? " + nulaOuVazia);
    }
}
