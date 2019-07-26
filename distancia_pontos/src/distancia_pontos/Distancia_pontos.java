/*
    Programa desenvolvido por Raphael Oliveira Melo e Felipe Andrey Nunes
                       Tecnologia em jogos digitais II        
                       Centro Universitário Senac 2018
 */
package distancia_pontos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Distancia_pontos {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Iniciando variável da classe FileReader
        FileReader leituraPontos;
        // Abre um arquivo e atribui ao objeto de leitura
        leituraPontos = new FileReader("pontos.txt");
        // Para fazer a leitura bufferizada será utilizada a classe 
        // BufferedReader
        BufferedReader pontosBufferizados;
        pontosBufferizados = new BufferedReader(leituraPontos);
        // Inicializando scanner do programa
        Scanner entrada = new Scanner(System.in);
        // - A varaiavel line será utilizada para ler as linhas do arquivos;
        // - A variavel numPontos receberá a String referente à quantidade de 
        // linhas (pontos) que o arquivo contém;
        // - xPonto e yPonto é onde as coordenadas x e y serão convertidas e
        // guardadas;
        // - A variável P será usada para criar os Pontos e os alocar no vetor de
        // pontos mais tarde;
        String line, numPontos;
        int xPonto, yPonto;
        Ponto P;
        // Line recebaerá o que o objeto bufferizado ler com a função readline()
        line = pontosBufferizados.readLine();
        // A primeira linha lida será a quantidade de linhas a serem lidas,
        // logo, será atribuida a uma variável que será utilizada posteriormente
        // para ler o resto das linhas do arquivo
        numPontos = line;
        // Após a atribuir a uma variável, converteremos essa variável em um
        // interiro para fazer a contagem das linhas
        int numPontosConvertido = Integer.parseInt(numPontos);
        // Temos aqui inicializados os vetores utilizadoos no programa
        // - vetorString será o responsável por armazenar as Strings retiradas
        // do documento de texto
        // - vetorPontos será o responsável por armazenar os pontos
        // identificados, seu tamanho é dado pelo número de pontos encontrado
        // - vetorProximos é o vetor que armazena as distâncias calculadas entre
        // os pontos
        String vetorString[];
        Ponto vetorPontos[] = new Ponto[numPontosConvertido];
        double vetorProximos[] = new double[numPontosConvertido];
        // No laço abaixo os seguintes processos ocorrem:
        // - line lê uma linha
        // - vetorString recebe as Strings após cortar os espaços da linha
        // - xPonto recebe a primeira coordenada em String e a converte em int
        // - yPonto recebe a segunda coordenada em String e a converte em int
        // - Com os pontos designados, a variável P cria um ponto com os
        // parâmetros xPonto e yPonto
        // - O ponto P é adicionado ao vetor de pontos
        // - x é incrementado e o mesmo processo ocorre na linha seguinte
        for (int x = 0; x < numPontosConvertido; x++) {
            line = pontosBufferizados.readLine();
            vetorString = line.split(" ");
            xPonto = Integer.parseInt(vetorString[0]);
            yPonto = Integer.parseInt(vetorString[1]);
            P = new Ponto(xPonto, yPonto);
            vetorPontos[x] = P;
        }
        // Agora que não existe mais a necessidade de usar o arquivo, o programa
        // fecha o mesmo
        pontosBufferizados.close();
        // Agora é inicializada a variavél utilizada no loop seguinte que irá
        // andar pelo vetor dos pontos e printar para o usuaario os pontos
        // disponíveis no arquivo, que a gora fazem parte do vetor de pontos
        int cont = 0;
        while (cont < numPontosConvertido) {
            System.out.println("Ponto " + "[" + cont + "]:" + vetorPontos[cont]);
            cont++;
        }
        // É então perguntado ao usuário qual ponto ele deseja usar de
        // referência para conferir os pontos mais próximos
        System.out.println("\nQual a coordendada X do ponto que deseja conferir?");
        int XRef = entrada.nextInt();
        System.out.println("\nQual a coordendada Y do ponto que deseja conferir?");
        int YRef = entrada.nextInt();
        Ponto PontoUsuario = new Ponto(XRef, YRef);    
        // E então é perguntado ao mesmo quantos pontos próximos ele deseja
        // conferir
        System.out.println("Quantos pontos próximos deseja conferir?");
        int quantRef = entrada.nextInt();
        // Após obter as informações necessárias, temos o loop que ira calcular
        // a distância entre o ponto que deseja ser verificado e cada um dos 
        // pontos do vetor de pontos, após obter o retorno, ele o armazena em 
        // uma das posições do vetor de distâncias
        for (int x = 0; x < vetorPontos.length; x++) {
            double result = PontoUsuario.distancia(vetorPontos[x]);
            vetorProximos[x] = result;   
        }
        // Após realizar os cálculos, chama a função Bolha ensinada em sala para
        // organizar os vetores
        Bolha(vetorProximos, vetorPontos);
        // Reiniciando novamente o contador para utilizar no loop, que dessa
        // vez irá imprimir para o usário os pontos mais próximos encontrados
        // juntamente com sua respectiva distância. Para isso ele anda no vetor
        // que agora se encontra ordenado ignorando apenas o primeiro número,
        // pois é a comparação do ponto com ele mesmo 
        cont = 0;
        System.out.println("Os pontos mais próximos são:");
        while (cont < quantRef + 1) {
            System.out.printf(cont+1 + "º: " + vetorPontos[cont]
                    + " com a distância de: %.2f %n", vetorProximos[cont]);
            cont++;
        }
    }

    // Função Bolha ensinada em sala, que consiste em "borbulhar" o maior
    // elemento para o final do vetor. Aqui ela está adaptada para receber um 
    // vetor com valores em double e um vetor de pontos. É importante frizar que
    // o vetor de pontos é dependente do vetor de distância, ou seja, só anda
    // no vetor se o vetor de distancia também andar, assim garantindo que as 
    // distancias andem junto com o respectivo vetor
    public static void Bolha(double V[], Ponto Vp[]) {
        for (int x = 1; x < V.length; x++) {
            for (int y = 0; y < V.length - x; y++) {
                if (V[y] > V[y + 1]) {
                    double temp = V[y];
                    Ponto tempP = Vp[y];
                    V[y] = V[y + 1];
                    Vp[y] = Vp[y + 1];
                    V[y + 1] = temp;
                    Vp[y + 1] = tempP;

                }
            }
        }

    }

}
