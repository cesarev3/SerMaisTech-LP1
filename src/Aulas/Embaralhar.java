package Aulas;

public class Embaralhar {
    public static void main(String[] args) {
        int[] teste = {1, 5, 3, 17, 28, 53};
        gerarEembaralharArray(teste);
        for (int i = 0; i < teste.length; i++) {
            System.out.print(teste[i] + ",");
        }
    }
        private static void gerarEembaralharArray(int[] array) {
//            for (int i = 0; i < 60; i++) {
//                array[i] = i + 1;
//            }
            for (int i = array.length - 1; i > 0; i--) {
                int j = (int) (Math.random() * (i + 1));
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

}
