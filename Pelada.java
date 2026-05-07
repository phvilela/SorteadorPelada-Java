import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class Pelada {
    private Jogador[]goleiros;
    private Jogador[]linha;
    private Time[] times;

    public Pelada (String edicao,int ntimes){
        goleiros = new Jogador[ntimes];
        linha = new Jogador[5*ntimes];
        try {
            File lista = new File("Listas/"+getpath(edicao));
            try (Scanner scanner = new Scanner(lista)){
                preencheLista(scanner);
            } catch (Exception e) {
                    System.err.println("\nArquivo nao pode ser lido: "+ e.getMessage());
            }
        }catch(Exception e){
            System.out.println("\nNao foi possivel iniciar o arquivo");
        }
    }

    public static Pelada criaPelada(String edicao){
        try{
            File a = new File("Listas/"+getpath(edicao));
            try(Scanner scanner = new Scanner(a)){
                int nlinhas=0;
                while(scanner.hasNextLine()){
                    scanner.nextLine();
                    nlinhas++;
                }
                if (nlinhas%6!=0){
                    throw new Exception("Insira uma lista com multiplos de 6 jogadores");
                }
                return new Pelada(edicao,nlinhas/6);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private void preencheLista(Scanner scanner){
        int ntimes = goleiros.length;
        for(int i=0;i<ntimes;i++){
            String[] aux = scanner.nextLine().split(" ");
            String nome = "";
            for (int j = 0; j < aux.length-1; j++) {
                    nome += aux[j];
                    if(j!=aux.length-2)
                    nome += " ";
                }
            goleiros[i] = new Jogador(nome,Double.parseDouble(aux[aux.length-1]));
        }
        for(int i=0; i<linha.length;i++){
            String[] aux = scanner.nextLine().split(" ");
            String nome = "";
            for (int j = 0; j < aux.length-1; j++) {
                nome += aux[j];
                if(j!=aux.length-2)
                nome += " ";
            }
            linha[i] = new Jogador(nome,Double.parseDouble(aux[aux.length-1]));
        }
    }

    public void sorteiaPelada(){
        ordenaLista();
        int nsorteios= 60,ntimes=goleiros.length;
        Random rand = new Random();
        Time[] auxf = new Time[ntimes];
        for (int cont = 0; cont < nsorteios ; cont++) {
            Time[] aux = new Time[ntimes];
            for (int i = 0; i < ntimes; i++) {
                aux[i]= new Time();
            }
            //Sorteia goleiros
            for (Jogador g : goleiros) {
                int p = rand.nextInt(goleiros.length);
                while (aux[p].getGoleiro()!=null) { 
                    p  = rand.nextInt(goleiros.length);
                }
                aux[p].setGoleiro(g);
            }
            //Sorteia 4 e 5 estrelas
            for(int j=0; j<2*ntimes ;j++){
                int p = rand.nextInt(ntimes);
                while (!aux[p].add45(linha[j])){
                    p = rand.nextInt(ntimes);
                }
            }
            //Sorteia 1 a 3 estrelas
            for(int j = 2*ntimes;j<5*ntimes;j++){
                int p = rand.nextInt(ntimes);
                while(!aux[p].add123(linha[j])){
                    p = rand.nextInt(ntimes);
                }
            }
            if(cont==0 | getVarTimes(aux)<getVarTimes(auxf)){
                auxf=aux;
            }
        }
        times = auxf;
    }

    public void ordenaLista(){
        int ntimes=goleiros.length;
        Jogador[] auxgl = new Jogador[ntimes];
        for(int i=0;i<ntimes;i++){
            int cont=0;
            for (Jogador g : goleiros) {
                if (g.getOverall() > goleiros[i].getOverall()){
                    cont++;
                }
            }
            for (int j=0;j<i;j++){
                if(goleiros[i].getOverall()==goleiros[j].getOverall())
                cont++;
            }
            auxgl[cont]=goleiros[i];
        }
        this.goleiros = auxgl;
        Jogador[] auxln = new Jogador[linha.length];
        for(int i=0;i<linha.length;i++){
            int cont=0;
            for (Jogador j : linha) {
                if (j.getOverall() > linha[i].getOverall()){
                    cont++;
                }
            }
            for (int j=0;j<i;j++){
                if(linha[i].getOverall()==linha[j].getOverall())
                cont++;
            }
            auxln[cont]=linha[i];
        }
        this.linha = auxln;
    }
    
    public void alteraEdicao(String edicao){
        try {
            File lista = new File("Listas/"+getpath(edicao));
            try (Scanner scanner = new Scanner(lista)){
                preencheLista(scanner);
                
            } catch (Exception e) {
                    System.err.println("\nArquivo nao pode ser lido: "+ e.getMessage());
            }
        }catch(Exception e){
            System.out.println("\nNao foi possivel iniciar o arquivo");
        }
    }

    public void confereNotas(String edicao){
        try {
            File lista = new File("Listas/"+getpath(edicao));
            try (Scanner scanner = new Scanner(lista)){
                preencheLista(scanner);
            } catch (Exception e) {
                    System.err.println("\nArquivo nao pode ser lido: "+ e.getMessage());
            }
        }catch(Exception e){
            System.out.println("\nNao foi possivel iniciar o arquivo");
        }
    }

    public boolean emitirLista(String edicao){
        String[] cores = {"Azul","Branco","Preto","Amarelo","Verde","Vermelho","Laranja","Marrom"};
        String[] emojis = {"🔵","⚪","⚫","🟡","🟢","🔴","🟠","🟤"};
        File arq = new File("Resultado/Resultado"+getpath(edicao));
        try {
            arq.createNewFile();
            try (FileWriter writer = new FileWriter(arq)) {
                writer.write("COPA SALAME™ "+edicao+"\n\n");
                int cont = 0;
                for( Time t: times){
                    System.out.print("\nTime "+cores[cont]);
                    t.printTime();
                    writer.write("Time "+cores[cont]+emojis[cont]+"\n"+t.getStringTime());
                    if(cont!=times.length-1)
                        writer.write("\n\n");
                    cont++;
                }
            }
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("\nLista emitida");
        return true;
    }

    public void printPelada(){
        System.out.println("\nGoleiros:");
        for(Jogador j:goleiros){
            System.out.print(j.getNome()+" ");
        }
        System.out.println("\nLinha:");
        int cont=0;
        for(Jogador j:linha){
            System.out.print(j.getNome()+" ");
            cont++;
            if(cont%5==0)
            System.out.println();
        }
    }
    
    public void printResultado(){
        int ntimes = goleiros.length;
        for(int i=0;i<ntimes;i++){
            System.out.printf("Time %-10d   ",i+1);
            }

        System.out.println("");
        for(Time t: times){
            System.out.printf("%-18s",t.getGoleiro().getNome());
        }
        
        for(int i = 0; i < 5; i++){
            System.out.println("");
            for(Time t:times){
                System.out.printf("%-18s",t.getJogador(i).getNome());
            }
        }
        System.out.println("");        
        for(Time t:times)
            System.out.printf("Media: %-9.2f  ",t.getMedia());
    }

    private double getVarTimes(Time[] times){
        double somavar=0;
        for (Time t : times) {
            if(t==null){
                return Double.MAX_VALUE;
            }
            somavar+=Math.pow(t.getMedia()-getMediaPelada(),2);
        }
        return somavar;
    }

    private double getMediaPelada(){
        double soma=0;
        for (Jogador g:goleiros){
            soma+=g.getOverall();
        }
        for(Jogador j:linha){
            soma+=j.getOverall();
        }
        return soma/6*goleiros.length;
    }

    private static String getpath(String edicao){
        return "Salame" + edicao + ".txt";
    }

    public Time[] getTimes() {
        return times;
    }
}
