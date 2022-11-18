import java.util.Scanner;

public class Temperaturas {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        int linhas, colunas;
        read.nextLine(); // ler a primeira linha
        linhas = read.nextInt();
        colunas = read.nextInt();
        String[][] mapaDeAlertas = new String[linhas][colunas]; //Matriz para guardar os Alertas c)
        int[][] mapaDeTemperatura = lerTemperaturas(linhas, colunas); // mapa de temperatura (matriz) .a)
        System.out.println("b)");
        mostrarTemperaturas(mapaDeTemperatura, linhas, colunas); // mostrar as temperaturas da matriz b)
        System.out.println("c)");
        mostrarMapaDeAlertas(mapaDeTemperatura, linhas, colunas, mapaDeAlertas); // matriz com alertas c)
        System.out.println("e)");
        percentagemDeAlertas(mapaDeAlertas, linhas, colunas); // mostrar a percentagem de alertas por temperaturas e)


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

    //c
    public static void mostrarMapaDeAlertas(int[][] mapaDeTemperaturas, int linhas, int colunas, String[][] mapaDeAlertas) {
        int i, x;
        for (i = 0; i < linhas; i++) {
            for (x = 0; x < colunas; x++) {
                if (mapaDeTemperaturas[i][x] < 20) {
                    System.out.print("M");
                    mapaDeAlertas[i][x] = "M";
                } else if (mapaDeTemperaturas[i][x] < 30) {
                    System.out.print("H");
                    mapaDeAlertas[i][x] = "H";
                } else if (mapaDeTemperaturas[i][x] < 40) {
                    System.out.print("E");
                    mapaDeAlertas[i][x] = "E";
                } else {
                    System.out.print("C");
                    mapaDeAlertas[i][x] = "C";
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    //e)
    public static void percentagemDeAlertas(String[][] mapaDeAlertas, int linhas, int colunas) {
        int nBlocos = linhas * colunas;
        int i, x;
        double mediaModerate = 0, mediaHigh = 0, mediaExtreme = 0, mediaCatastrophic = 0;
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
        mediaModerate = mediaModerate / nBlocos * 100;
        mediaExtreme = mediaExtreme / nBlocos * 100;
        mediaHigh = mediaHigh / nBlocos * 100;
        mediaCatastrophic = mediaCatastrophic / nBlocos * 100;
        System.out.printf("MODERATE     : %.2f%%%n", mediaModerate);
        System.out.printf("HIGH         : %.2f%%%n", mediaHigh);
        System.out.printf("EXTREME      : %.2f%%%n", mediaExtreme);
        System.out.printf("CATASTROPHIC : %.2f%%%n", mediaCatastrophic);
    }

    public static void bolas(){

        System.out.println("bolas");

    }


}

