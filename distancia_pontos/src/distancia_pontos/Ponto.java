/*
    Esta é a classe pontos, onde receberá como parâmetro um X e um Y e irá
    os atribuir ao objeto ponto
 */
package distancia_pontos;

public class Ponto {

    // Definindo os dados do ponto, que é um TAD
    private int x;
    private int y;

    // Definindo o construtor da classe Ponto. O construtor inicialiaz os
    // atributos da classe (x e y) com os valores informados pela aplicação 
    // cliente (xCliente, YCliente)
    public Ponto(int xCliente, int yCliente) {
        x = xCliente;
        y = yCliente;
    }

    // Método override, que permitirá a leitura do objeto ao invés da leitura
    // do endereço de memórtia do mesmo
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    // Método distancia, que receberá um ponto e efetuará o cálculo para
    // descobri a distância entre o ponto que chamou a função e o ponto que está
    // sendo passado de forma explícita
    public double distancia(Ponto outro) {
        // A distância entre dois ponto é dada por
        // raiz quadrada de (Xp1 - Xp2)² + (Yp1 - Yp2)²
        // Tendo esta fórmula em mente, executamos conforme a fórmula
        double refp, refp2, distancia, parenteses1, parenteses2, somaparenteses;
        // Realizando a subtração do primeiro parêntese
        refp = (outro.x - this.x);
        // Elevento o primeiro parêntese à dois
        parenteses1 = Math.pow(refp, 2);
        // Realizando a subtração do segundo parêntese
        refp2 = (outro.y - this.y);
        // Elevento o segundo parêntese à dois
        parenteses2 = Math.pow(refp2, 2);
        // Somando os dois parênteses
        somaparenteses = parenteses1 + parenteses2;
        // Tirando a raiz da soma 
        distancia = Math.sqrt(somaparenteses);
        // Retornando a distancia entre os dois pontos
        return distancia;
    }
}
