import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String[] arquivos = {
                "aleatorio_100.csv",
                "aleatorio_1000.csv",
                "aleatorio_10000.csv",
                "crescente_100.csv",
                "crescente_1000.csv",
                "crescente_10000.csv",
                "decrescente_100.csv",
                "decrescente_1000.csv",
                "decrescente_10000.csv"
        };

        System.out.println("Arquivo;Bubble Sort (ns);Insertion Sort (ns);Quick Sort (ns)");

        for (int i = 0; i < arquivos.length; i++) {
            int[] original = lerCSV(arquivos[i]);

            if (original.length == 0) {
                System.out.println(arquivos[i] + ";ERRO;ERRO;ERRO");
                continue;
            }

            int[] vetorBubble = copiarArray(original);
            int[] vetorInsertion = copiarArray(original);
            int[] vetorQuick = copiarArray(original);

            long inicioBubble = System.nanoTime();
            bubbleSort(vetorBubble);
            long fimBubble = System.nanoTime();
            long tempoBubble = fimBubble - inicioBubble;

            long inicioInsertion = System.nanoTime();
            insertionSort(vetorInsertion);
            long fimInsertion = System.nanoTime();
            long tempoInsertion = fimInsertion - inicioInsertion;

            long inicioQuick = System.nanoTime();
            quickSort(vetorQuick, 0, vetorQuick.length - 1);
            long fimQuick = System.nanoTime();
            long tempoQuick = fimQuick - inicioQuick;

            System.out.println(
                    arquivos[i] + ";" +
                            tempoBubble + ";" +
                            tempoInsertion + ";" +
                            tempoQuick
            );
        }
    }

    public static int[] lerCSV(String nomeArquivo) {
        int quantidade = contarNumeros(nomeArquivo);
        int[] numeros = new int[quantidade];

        try {
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));

            leitor.readLine();

            String linha;
            int indice = 0;

            while ((linha = leitor.readLine()) != null) {
                String numeroTexto = "";

                for (int i = 0; i < linha.length(); i++) {
                    char caractere = linha.charAt(i);

                    if (caractere == ',' || caractere == ';') {
                        if (numeroTexto.length() > 0) {
                            numeros[indice] = Integer.parseInt(numeroTexto.trim());
                            indice++;
                            numeroTexto = "";
                        }
                    } else {
                        numeroTexto = numeroTexto + caractere;
                    }
                }

                if (numeroTexto.length() > 0) {
                    numeros[indice] = Integer.parseInt(numeroTexto.trim());
                    indice++;
                }
            }

            leitor.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + nomeArquivo);
        }

        return numeros;
    }

    public static int contarNumeros(String nomeArquivo) {
        int contador = 0;

        try {
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));

            leitor.readLine();

            String linha;

            while ((linha = leitor.readLine()) != null) {
                String numeroTexto = "";

                for (int i = 0; i < linha.length(); i++) {
                    char caractere = linha.charAt(i);

                    if (caractere == ',' || caractere == ';') {
                        if (numeroTexto.length() > 0) {
                            contador++;
                            numeroTexto = "";
                        }
                    } else {
                        numeroTexto = numeroTexto + caractere;
                    }
                }

                if (numeroTexto.length() > 0) {
                    contador++;
                }
            }

            leitor.close();

        } catch (IOException e) {
            System.out.println("Erro ao contar números do arquivo: " + nomeArquivo);
        }

        return contador;
    }

    public static int[] copiarArray(int[] original) {
        int[] copia = new int[original.length];

        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }

        return copia;
    }

    public static void bubbleSort(int[] vetor) {
        int tamanho = vetor.length;

        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temporario = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temporario;
                }
            }
        }
    }

    public static void insertionSort(int[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            int chave = vetor[i];
            int j = i - 1;

            while (j >= 0 && vetor[j] > chave) {
                vetor[j + 1] = vetor[j];
                j--;
            }

            vetor[j + 1] = chave;
        }
    }

    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = particionar(vetor, inicio, fim);

            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    public static int particionar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;

                int temporario = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temporario;
            }
        }

        int temporario = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = temporario;

        return i + 1;
    }
}