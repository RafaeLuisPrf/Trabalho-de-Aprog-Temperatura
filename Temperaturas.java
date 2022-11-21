import java.util.Scanner;

public class Temperaturas {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        int linhas, colunas;
        read.nextLine(); // ler a primeira linha
        linhas = read.nextInt();
        colunas = read.nextInt();
        String[][] mapaDeAlertas = new String[linhas][colunas]; //Matriz para guardar os Alertas c)

        //a
        int[][] mapaDeTemperatura = lerTemperaturas(linhas, colunas); // mapa de temperatura (matriz) .a)

        //b
        System.out.println("b)");
        mostrarTemperaturas(mapaDeTemperatura, linhas, colunas); // mostrar as temperaturas da matriz b)

        //c
        System.out.println("c)");
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
        alterarMAPeloVento(linhas, colunas, mapaDeTemperatura);
    }

    //a
    public static int[][] lerTemperaturas(int linhas, int colunas) {
        int i, x;
        int[][] mapaDeTemperatura = new int[linhas][colunas]; // colocar os valores na matriz
        for (i = 0; i < linhas; i++) {
            for (x = 0; x < colunas; x++) {
                mapaDeTemperatura[i][x] = read.nextInt();
            }
        }
        return mapaDeTemperatura;
    }

    //b
    public static void mostrarTemperaturas(int[][] mapaDeTemperaturas, int linhas, int colunas) {
        int i, x;
        for (i = 0; i < linhas; i++) {
            for (x = 0; x < colunas; x++) {
                if (mapaDeTemperaturas[i][x] < 0) { // caso seja negativo o volar, coloco 1 casa decimal a traz
                    System.out.print(" " + mapaDeTemperaturas[i][x]);
                } else {
                    System.out.print("  " + mapaDeTemperaturas[i][x]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void criarMapaDeAlertas(int[][] mapaDeTemperaturas, int linhas, int colunas, String[][] mapaDeAlertas) {
        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {
                    if (mapaDeTemperaturas[i][x] < 20) {
                        mapaDeAlertas[i][x] = "M";
                    } else if (mapaDeTemperaturas[i][x] < 30) {
                        mapaDeAlertas[i][x] = "H";
                    } else if (mapaDeTemperaturas[i][x] < 40) {
                        mapaDeAlertas[i][x] = "E";
                    } else {
                        mapaDeAlertas[i][x] = "C";
                    }
                }
            }
        }
    //c
    public static void mostrarMapaDeAlertas(int linhas, int colunas, String[][] mapaDeAlertas) {
        int i, x;
        for (i = 0; i < linhas; i++) {
            for (x = 0; x < colunas; x++) {
                System.out.print(mapaDeAlertas[i][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //e)
    public static void percentagemDeAlertas(String[][] mapaDeAlertas, int linhas, int colunas) {
        int nBlocos = linhas * colunas;
        int i, x;
        float mediaModerate = 0, mediaHigh = 0, mediaExtreme = 0, mediaCatastrophic = 0;
        for (i = 0; i < linhas; i++) {
            for (x = 0; x < colunas; x++) {
                if (mapaDeAlertas[i][x].equals("M")) {
                    mediaModerate++;
                }
                if (mapaDeAlertas[i][x].equals("H")) {
                    mediaHigh++;
                }
                if (mapaDeAlertas[i][x].equals("E")) {
                    mediaExtreme++;
                }
                if (mapaDeAlertas[i][x].equals("C")) {
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
    public static void necessarioParaCatastrophic(int linhas, int colunas, int[][] mapaDeTemperatura) {
        int i, x, menor = mapaDeTemperatura[0][0], tmpAtual, tmpNecessariaPC;
        final int CATASTROPHIC = 40;
        for (i = 0; i < linhas; i++) {
            for (x = 0; x < colunas; x++) {
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

    public static void variacaoDosNiveisAlerta(String[][] mapaDeAlertas, int linhas, int colunas, int[][] mapaDeTemperatura) {
        int i, x, nBlocos = linhas * colunas;
        int contagemMudanca = 0;
        float media;

        String[][] mapaDeAlertasVariacao = new String[linhas][colunas];
        alterarMT(mapaDeTemperatura, linhas, colunas, VARIACAO_TEMP2);
        criarMapaDeAlertas(mapaDeTemperatura, linhas, colunas, mapaDeAlertasVariacao);
        mostrarMapaDeAlertas(linhas, colunas, mapaDeAlertasVariacao);

        for (i = 0; i < linhas; i++) {
            for (x = 0; x < colunas; x++) {
                if (!mapaDeAlertasVariacao[i][x].equals(mapaDeAlertas[i][x])) {
                    contagemMudanca++;
                }
            }
        }
        media = ((float) contagemMudanca / nBlocos) * 100;
        System.out.printf("Alert Levels changes due to temperature variations by %dºC :%.2f%%%n", VARIACAO_TEMP2, media);
        System.out.println();
    }

    public static void copiarMatriz(String[][] mapaDeAlertasVariacao, String[][] copiaMapaDeALertasVariacao, int linhas, int colunas) {
        for (int i = 0; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {
                copiaMapaDeALertasVariacao[i][x] = mapaDeAlertasVariacao[i][x];
            }
        }
    }


    public static void alterarMAPeloVento(int linhas, int colunas, int[][] mapaDeTemperatura) {
        String[][] mapaDeAlertasVariacao = new String[linhas][colunas];
        criarMapaDeAlertas(mapaDeTemperatura, linhas, colunas, mapaDeAlertasVariacao);
        String[][] copiaMapaDeAlertasVaricao = new String[linhas][colunas];
        copiarMatriz(mapaDeAlertasVariacao, copiaMapaDeAlertasVaricao, linhas, colunas);

        for (int i = 1; i < linhas; i++) {
            for (int x = 0; x < colunas; x++) {
                if (mapaDeAlertasVariacao[i - 1][x].equals("C")) {
                    copiaMapaDeAlertasVaricao[i][x] = "C";
                }
            }
        }

        /*for (int i = 0; i < colunas; i++) {
            for (int x = 1; x < linhas; x++) {
                if (mapaDeAlertasVariacao[x - 1][i].equals("C")) {
                    mapaDeAlertasVariacao[x][i] = "C";
                    i++;
                }
            }
*/
        //System.out.println(Arrays.deepToString(copiaMapaDeALertasVariacao));
        mostrarMapaDeAlertas(linhas, colunas, copiaMapaDeAlertasVaricao);
    }
}

