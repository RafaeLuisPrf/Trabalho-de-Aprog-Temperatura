import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Temperaturas {



    public static final String INPUT="Entrada";

    public static final int VARIACAO_TEMP1 = -10;
    public static final int VARIACAO_TEMP2 = 10;
    public static final int MODERATE = 20;
    public static final int HIGH = 30;
    public static final int EXTREME = 40;
    public static final int CATASTROPHIC = 40;
    public static final int FIRE = 50;

    public static void main(String[] args)throws FileNotFoundException  {




        //a
        int[][] mapaDeTemperatura = lerTemperaturas(); // mapa de temperatura (matriz) .a)
        int linhas= mapaDeTemperatura.length, colunas=mapaDeTemperatura[0].length;

        //b
        System.out.println("b)");
        mostrarTemperaturas(mapaDeTemperatura, linhas, colunas); // mostrar as temperaturas da matriz b)

        //c
        System.out.println("c)");
        char[][] mapaDeAlertas = new char[linhas][colunas]; //Matriz para guardar os Alertas c)
        criarMapaDeAlertas(mapaDeTemperatura, linhas, colunas, mapaDeAlertas);//criar a matriz alertas
        mostrarMapaDeAlertas(linhas, colunas, mapaDeAlertas); // matriz com alertas


        //d
        System.out.println("d)");
        alterarMT(mapaDeTemperatura, linhas, colunas, VARIACAO_TEMP1);
        mostrarTemperaturas(mapaDeTemperatura, linhas, colunas);
        criarMapaDeAlertas(mapaDeTemperatura, linhas, colunas, mapaDeAlertas);
        mostrarMapaDeAlertas(linhas, colunas, mapaDeAlertas);

        //e
        System.out.println("e)");
        percentagemDeAlertas(mapaDeAlertas, linhas, colunas); // mostrar a percentagem de alertas por temperaturas e)

        //f
        System.out.println("f)");
        necessarioParaCatastrophic(linhas, colunas, mapaDeTemperatura);

        //g
        System.out.println("g)");
        variacaoDosNiveisAlerta(mapaDeAlertas, linhas, colunas, mapaDeTemperatura);

        //h
        System.out.println("h)");
        criarMapaDeAlertas(mapaDeTemperatura, linhas, colunas, mapaDeAlertas);
        alterarMAPeloVento(linhas, colunas, mapaDeAlertas);

        //i
        System.out.println("i)");
        mostrarTemperaturas(mapaDeTemperatura, linhas, colunas);
        encontrarIncendio(mapaDeTemperatura, linhas, colunas);

        //j
        System.out.println("j)");
        criarMapaDeAlertas(mapaDeTemperatura, linhas, colunas, mapaDeAlertas);
        colunasSafe(mapaDeAlertas, linhas, colunas);
    }

    //a
    public static int[][] lerTemperaturas() throws FileNotFoundException {

        Scanner in = new Scanner(new File(INPUT));
        String dataEHoras = in.nextLine();
        int linhas = in.nextInt(),colunas=in.nextInt();


        int[][] mapaDeTemperatura = new int[linhas][colunas]; // colocar os valores na matriz

        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {
                mapaDeTemperatura[i][x] = in.nextInt();
            }
        }
        in.close();

        return mapaDeTemperatura;
    }

    //b
    public static void mostrarTemperaturas(int[][] mapaDeTemperaturas, int linhas, int colunas) {

        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {


                System.out.printf("%3d ", mapaDeTemperaturas[i][x]);


            }
            System.out.println();
        }
        System.out.println();
    }

    public static void criarMapaDeAlertas(int[][] mapaDeTemperaturas, int linhas, int colunas, char[][] mapaDeAlertas) {

        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {

                if (mapaDeTemperaturas[i][x] < MODERATE) {
                    mapaDeAlertas[i][x] = 'M';
                } else if (mapaDeTemperaturas[i][x] < HIGH) {
                    mapaDeAlertas[i][x] = 'H';
                } else if (mapaDeTemperaturas[i][x] < EXTREME) {
                    mapaDeAlertas[i][x] = 'E';
                } else {
                    mapaDeAlertas[i][x] = 'C';//CATASTRPHE
                }
            }
        }
    }

    //c
    public static void mostrarMapaDeAlertas(int linhas, int colunas, char[][] mapaDeAlertas) {

        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {
                System.out.print(mapaDeAlertas[i][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //d
    public static void alterarMT(int[][] mapaDeTemperaturas, int linhas, int colunas, int deltaT) {

        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {

                mapaDeTemperaturas[i][x] += deltaT;

            }
        }
    }


    //e)
    public static void percentagemDeAlertas(char[][] mapaDeAlertas, int linhas, int colunas) {

        int nBlocos = linhas * colunas;
        float mediaModerate = 0, mediaHigh = 0, mediaExtreme = 0, mediaCatastrophic = 0;

        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {

                if (mapaDeAlertas[i][x] == 'M') {
                    mediaModerate++;
                }
                if (mapaDeAlertas[i][x] == 'H') {
                    mediaHigh++;
                }
                if (mapaDeAlertas[i][x] == 'E') {
                    mediaExtreme++;
                }
                if (mapaDeAlertas[i][x] == ('C')) {
                    mediaCatastrophic++;
                }
            }
        }
        mediaModerate = (mediaModerate / nBlocos) * 100;
        mediaExtreme = (mediaExtreme / nBlocos) * 100;
        mediaHigh = (mediaHigh / nBlocos) * 100;
        mediaCatastrophic = (mediaCatastrophic / nBlocos) * 100;

        System.out.printf("MODERATE     :  %6.2f%%%n", mediaModerate);
        System.out.printf("HIGH         :  %6.2f%%%n", mediaHigh);
        System.out.printf("EXTREME      :  %6.2f%%%n", mediaExtreme);
        System.out.printf("CATASTROPHIC :  %6.2f%%%n", mediaCatastrophic);
        System.out.println();
    }

    //f
    public static void encontrarIncendio(int[][] mapaDeTemperaturas, int linhas, int colunas) {

        int x = 0, y = 0, nFogosMax = 0, nFogos = 0;

        boolean fogoEncontrado = false;



        if (linhas > 2 && colunas > 2 ) {


            for (int i = 1; i < linhas - 1; i++) {
                for (int j = 1; j < colunas - 1; j++) {
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (mapaDeTemperaturas[k][l] > FIRE) {
                                nFogos++;
                                fogoEncontrado = true;
                            }
                        }
                    }
                    if (nFogosMax < nFogos) {

                        nFogosMax = nFogos;
                        x = j;
                        y = i;
                    }
                    nFogos = 0;
                }
            }
            if (fogoEncontrado == true) {

                System.out.printf("drop water at (%d , %d)%n", y, x);
            } else {

                System.out.println("no fire");

            }
            System.out.println();

        } else {

            System.out.println("Não é possível colocar o balde");
        }
        }

    public static void necessarioParaCatastrophic(int linhas, int colunas, int[][] mapaDeTemperatura) {

        int menor = mapaDeTemperatura[0][0], tmpAtual, tmpNecessariaPC;

        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {
                tmpAtual = mapaDeTemperatura[i][x];
                if (tmpAtual < menor) {
                    menor = tmpAtual;
                }
            }
        }
        tmpNecessariaPC = CATASTROPHIC - menor;
        System.out.println("To get all terrain on CATASTROPHIC alert, the temperature has to rise : " + tmpNecessariaPC + " ºC");
        System.out.println();
    }

    public static void variacaoDosNiveisAlerta(char[][] mapaDeAlertas, int linhas, int colunas, int[][] mapaDeTemperatura) {

        int i, x, nBlocos = linhas * colunas;
        int contagemMudanca = 0;
        float media;

        char[][] mapaDeAlertasVariacao = new char[linhas][colunas];

        alterarMT(mapaDeTemperatura, linhas, colunas, VARIACAO_TEMP2);
        criarMapaDeAlertas(mapaDeTemperatura, linhas, colunas, mapaDeAlertasVariacao);
        mostrarMapaDeAlertas(linhas, colunas, mapaDeAlertasVariacao);

        for (i = 0; i < linhas; i++) {
            for (x = 0; x < colunas; x++) {
                if (mapaDeAlertasVariacao[i][x] != (mapaDeAlertas[i][x])) {
                    contagemMudanca++;
                }
            }
        }

        media = ((float) contagemMudanca / nBlocos) * 100;
        System.out.printf("Alert Levels changes due to temperature variations by %dºC :%.2f%%%n", VARIACAO_TEMP2, media);
        System.out.println();
    }

    public static void copiarMatriz(char[][] matrizOriginal, char[][] matrizCopia, int linhas, int colunas) {

        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {
                matrizCopia[i][x] = matrizOriginal[i][x];
            }
        }
    }


    public static void alterarMAPeloVento(int linhas, int colunas, char[][] mapaDeAlertas) {

        char[][] copiaMapaDeAlertasVaricao = new char[linhas][colunas];
        copiarMatriz(mapaDeAlertas, copiaMapaDeAlertasVaricao, linhas, colunas);

        for (int i = 1; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {
                if (mapaDeAlertas[i - 1][x] == 'C') {
                    copiaMapaDeAlertasVaricao[i][x] = 'C';
                }
            }
        }
        mostrarMapaDeAlertas(linhas, colunas, copiaMapaDeAlertasVaricao);
    }
    //i)

    public static void colunasSafe(char[][] mapaDeAlertas, int linhas, int colunas) {

        boolean safe = true;
        boolean encontrada = false;

        for (int x = colunas - 1; x > -1 && encontrada == false; x--) {
            safe = true;
            for (int i = 0; i < linhas && safe == true; i++) {
                if (mapaDeAlertas[i][x] == 'C') {
                    safe = false;
                }
            }
            if (safe == true) {
                System.out.println("safe column = (" + x + ")");
                encontrada = true;

            }
        }
        if (safe == false) {
            System.out.println("safe column = NONE");
        }
    }
}