# Documentação do Código – Algoritmos de Ordenação

## 1. Objetivo

Este programa tem como objetivo comparar o desempenho de três algoritmos de ordenação:

- Bubble Sort
- Insertion Sort
- Quick Sort

A comparação é feita utilizando arquivos CSV com diferentes quantidades de dados e diferentes tipos de ordenação inicial: aleatória, crescente e decrescente.

O tempo de execução de cada algoritmo é medido com `System.nanoTime()`.

---

## 2. Estrutura Geral do Programa

O código foi desenvolvido em uma única classe chamada `Main`.

A classe contém métodos responsáveis por:

- Ler os arquivos CSV.
- Contar a quantidade de números em cada arquivo.
- Copiar os dados para novos vetores.
- Executar os três algoritmos de ordenação.
- Medir o tempo de execução de cada algoritmo.
- Exibir os resultados no terminal.

---

## 3. Arquivos Utilizados

O programa utiliza os seguintes arquivos CSV:

```text
aleatorio_100.csv
aleatorio_1000.csv
aleatorio_10000.csv
crescente_100.csv
crescente_1000.csv
crescente_10000.csv
decrescente_100.csv
decrescente_1000.csv
decrescente_10000.csv
```

Esses arquivos devem estar na mesma pasta do arquivo `Main.java` ou na pasta raiz do projeto.

---

## 4. Funcionamento do Método `main`

O método `main` é o ponto inicial do programa.

Ele cria um vetor com os nomes dos arquivos CSV e percorre cada arquivo utilizando um laço `for`.

Para cada arquivo:

1. O conteúdo é lido e armazenado em um vetor de inteiros.
2. São criadas três cópias do vetor original.
3. Cada cópia é ordenada por um algoritmo diferente.
4. O tempo de execução é medido separadamente.
5. Os resultados são exibidos no terminal em formato de tabela separada por ponto e vírgula.

Exemplo de saída:

```text
Arquivo;Bubble Sort (ns);Insertion Sort (ns);Quick Sort (ns)
aleatorio_100.csv;12345;6789;4321
```

---

## 5. Método `lerCSV`

O método `lerCSV` é responsável por ler os números presentes em um arquivo CSV e armazená-los em um vetor `int[]`.

Antes de ler os valores, ele chama o método `contarNumeros` para descobrir quantos números existem no arquivo. Isso é necessário para criar um vetor com o tamanho correto.

O método aceita arquivos separados por vírgula ou ponto e vírgula.

---

## 6. Método `contarNumeros`

O método `contarNumeros` percorre o arquivo CSV e conta quantos números existem nele.

Essa contagem é usada para criar o vetor de inteiros com o tamanho exato necessário.

Esse método evita o uso de estruturas prontas do Java, como `ArrayList`.

---

## 7. Método `copiarArray`

O método `copiarArray` cria uma cópia manual do vetor original.

Isso é importante porque cada algoritmo precisa receber os mesmos dados iniciais. Se todos usassem o mesmo vetor, o primeiro algoritmo deixaria os dados ordenados e prejudicaria a comparação com os outros.

A cópia é feita manualmente com um laço `for`, sem utilizar `Arrays.copyOf` ou `System.arraycopy`.

---

## 8. Bubble Sort

O Bubble Sort compara elementos vizinhos e troca suas posições quando estão fora de ordem.

Ele percorre o vetor várias vezes até que os maiores valores sejam levados para o final.

Apesar de simples, é um algoritmo pouco eficiente para grandes quantidades de dados.

### Características

- Fácil de implementar.
- Realiza muitas comparações.
- Costuma ser lento em vetores grandes.
- Desempenho médio e pior caso: O(n²).

---

## 9. Insertion Sort

O Insertion Sort ordena o vetor como se estivesse organizando cartas na mão.

Ele pega um elemento por vez e o insere na posição correta em relação aos elementos anteriores.

Esse algoritmo costuma ter bom desempenho quando os dados já estão quase ordenados.

### Características

- Bom desempenho em vetores pequenos.
- Muito eficiente em dados já ordenados ou quase ordenados.
- Pode ser lento em dados decrescentes.
- Melhor caso: O(n).
- Pior caso: O(n²).

---

## 10. Quick Sort

O Quick Sort é um algoritmo de ordenação baseado na técnica de divisão e conquista.

No código, o pivô utilizado é sempre o último elemento do vetor, conforme solicitado no enunciado do trabalho.

O algoritmo particiona o vetor em duas partes:

- Valores menores ou iguais ao pivô.
- Valores maiores que o pivô.

Depois disso, aplica o mesmo processo recursivamente nas duas partes.

### Características

- Muito eficiente em dados aleatórios.
- Usa recursão.
- Último elemento do array é usado como pivô.
- Caso médio: O(n log n).
- Pior caso: O(n²), especialmente quando o vetor já está ordenado ou em ordem decrescente usando o último elemento como pivô.

---

## 11. Método `particionar`

O método `particionar` é utilizado pelo Quick Sort.

Ele escolhe o último elemento do intervalo como pivô e reorganiza os elementos de forma que:

- Os menores ou iguais ao pivô fiquem à esquerda.
- Os maiores fiquem à direita.

Ao final, o pivô é colocado em sua posição correta e essa posição é retornada para o método `quickSort`.

---

## 12. Medição de Tempo

A medição de tempo é feita com `System.nanoTime()`.

Antes de executar cada algoritmo, o programa armazena o tempo inicial. Depois da execução, armazena o tempo final.

O tempo total é calculado pela diferença:

```java
long tempo = fim - inicio;
```

O resultado é exibido em nanossegundos.

---

## 13. Restrições Atendidas

O código atende às principais restrições do trabalho:

- Implementa Bubble Sort.
- Implementa Insertion Sort.
- Implementa Quick Sort.
- Usa o último elemento do array como pivô no Quick Sort.
- Lê arquivos CSV.
- Mede o tempo com `System.nanoTime()`.
- Não utiliza `ArrayList`.
- Não utiliza `Collections.sort()`.
- Não utiliza `Arrays.sort()`.
- Não utiliza estruturas prontas para ordenar os dados.

---

## 14. Análise Esperada dos Resultados

Espera-se que o Bubble Sort apresente os maiores tempos de execução, principalmente nos arquivos com 10.000 elementos.

O Insertion Sort deve apresentar bons resultados em arquivos já ordenados de forma crescente, pois nesse caso realiza poucas movimentações.

O Quick Sort tende a ser mais rápido em arquivos aleatórios. Porém, como o pivô escolhido é sempre o último elemento, seu desempenho pode piorar em arquivos já ordenados ou em ordem decrescente.

Isso acontece porque o particionamento pode ficar desbalanceado, fazendo com que o algoritmo realize mais chamadas recursivas.

---

## 15. Conclusão

O programa permite analisar na prática como diferentes algoritmos de ordenação se comportam em cenários variados.

A comparação mostra que a eficiência de um algoritmo depende não apenas do tamanho do conjunto de dados, mas também da forma como os dados estão organizados inicialmente.

O Bubble Sort é o menos eficiente para grandes volumes de dados. O Insertion Sort é vantajoso quando os dados já estão quase ordenados. O Quick Sort, em geral, apresenta melhor desempenho em dados aleatórios, mas pode ter desempenho ruim quando utiliza o último elemento como pivô em conjuntos já ordenados.
