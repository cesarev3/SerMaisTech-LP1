package Desafios;

public class aula_5_hw1 {

//    Maria está olhando o mercado de automóveis para comprar um carro novo.
//    Ela identificou que o preço final do veiculo tem um percentual relacionado
//    ao distribuidor e também um percentual de impostos. Ela deseja identificar
//    qual veículo possui os menores percentuais de imposto e do distribuidor,
//    para fazer a escolha de seu carro novo.
//
//    Para ajudar Maria com a sua busca, crie uma função que receba um array com
//    o preço de final de fábrica, o valor do custo com o distribuidor e o valor
//    do preço dos impostos. Ao final, a função deve retornar um array com o
//    percentual do custo do distribuidor e o percentual do custo com os impostos,
//    seguindo essa ordem. Realize o arredondamento para duas casas decimais em
//    relação aos dados de retorno.
    public static void main(String[] args) {
        double input[] = {100000.00, 12000.00, 20000.00};
        double resultado[] = custosCarro(input);
        System.out.println(resultado[0]);
        System.out.println(resultado[1]);
    }

    public static double[] custosCarro( double[] input) {
        double percentualCustoDistribuidor = (input[1] / input[0]) * 100.00;
        double percentualCustoImpostos = (input[2] / input[0]) * 100.00;
        percentualCustoDistribuidor = Math.round(percentualCustoDistribuidor * 100.00) / 100.00;
        percentualCustoImpostos = Math.round(percentualCustoImpostos * 100.00) /100.00;
        double[] percentuais = {percentualCustoDistribuidor, percentualCustoImpostos};
        return percentuais;
    }
}
