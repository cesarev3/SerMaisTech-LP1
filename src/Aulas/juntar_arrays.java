package Aulas;

public class juntar_arrays {
    public static void main(String[] args) {
//        String[] vogaisp1 = {"a","e"}; // 0 - 1
//        String[] vogaisp2 = {"i","o","u"}; // 0 - 2
//        int dim = vogaisp1.length + vogaisp2.length;
//        String[] vogais = new String[dim];
//
//        vogais = vogaisp1 + vogaisp2;
//        // 0   1   2   3   4
//        // a - e - i - o - u - NOVO ARRAY
//        // solucao 1 - percorrendo 5 indices
//        for (int i = 0; i < dim; i++) {
//            if (i > 1) {
//                vogais[i] = vogaisp2[i-2];
//            } else {
//                vogais[i] = vogaisp1[i];
//            }
//        }
        String[] vogaisp1 = {"a", "e"};
        String[] vogaisp2 = {"i", "o", "u"};

        int dim = vogaisp1.length + vogaisp2.length;
        String[] vogais = new String[dim];

// Preenche os elementos do novo array com as vogais do primeiro array
        for (int i = 0; i < vogaisp1.length; i++) {
            vogais[i] = vogaisp1[i];
        }

// Preenche os elementos restantes do novo array com as vogais do segundo array
        for (int i = 0; i < vogaisp2.length; i++) {
            vogais[vogaisp1.length + i] = vogaisp2[i];
        }

// Agora, o array 'vogais' c
        //System.out.println(vogais.toString(vogais));
    }
}
