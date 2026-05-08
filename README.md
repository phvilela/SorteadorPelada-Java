# Sorteador de Times para FUT6

<h3>O Sorteador foi desenvolvido para facilitar o sorteio de times em um campeonato que um grupo de amigos disputava com o nome de Copa Salame, por isso os modelos de entrada especificos a serem vistos no tópico Como Funciona</h3>

## Requisitos:
- JVM instalada na máquina:
`sudo apt install openjdk-17-jdk`
ou baixar o instalador diretamente do site oficial do Java e instalar

## Como funciona

O programa recebe uma entrada da string da edição a ser sorteada, e lê do arquivo [Lista/Salame\<edicao\>.txt](Listas/SalameExemplo.txt) que deve conter 6 linhas para cada time do campeoanto, e deve seguir o seguinte padrão para cada linha:
`Nome do Jogador NOTA_DO_JOGADOR`

<p> O programa considera que os primeiros NUMERO_DE_TIMES jogadores são os goleiros, e os separa dos demais para o sorteio </p>

  Ao iniciar é exibido um menu com as opções de sorteio:
  -  1- Sortear times
  -  2- Emitir lista de times
  -  3- Verificar notas
  -  4- Alterar a edição
  -  0- Sair

A opção emitir lista de times seleciona o resultado do ultimo sorteio é associa cores a cada time para padronizar a vestimenta no campeonato. Em seguida escreve o resultado do sorteio em um arquivo na pasta [Resultado](Resultado) com o nome `ResultadoSalame/<edicao/>.txt`

## Como executar

1. Executar diretamente do arquivo jar:
 ```bash
java -jar Sorteio.jar
```

2. Compilar e executar o arquivo Main.class:

 ```bash
javac *.java
java Main
```
