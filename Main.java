import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("SALAME S.A. Todos os direitos reservados: @copasalame\n\nSorteio da COPA SALAME(TM)\n");
        try (Scanner scan = new Scanner(System.in)) {
            Pelada pelada;
            String edicao = "";
            do{
                System.out.println("Informe a edição a ser sorteada:");
                edicao = scan.nextLine();
                if(edicao.equals("0")){
                    System.out.println("\nEncerrando aplicação\nSALAME S.A.agradece");
                    scan.nextLine();
                    return;
                }
                System.out.println("");
                pelada = Pelada.criaPelada(edicao);
                if(pelada==null){
                    System.out.println("\nTente novamente ou digite 0 para sair");
                }
            }while(pelada==null);
            pelada.ordenaLista();
            int escolha = 1;
            while (escolha!=0) {
                //pelada.printPelada();
                System.out.println("\nOque deseja fazer com esses jogadores?\n 1- Sortear times\n 2- Emitir lista de times\n 3- Verificar notas\n 4- Alterar a edição\n 0- Sair\n\nSelecione sua escolha: ");
                escolha = scan.nextInt();
                switch (escolha) {
                    case 1 -> {
                        int escolha1 = 1;
                        while(escolha1!=0){
                            pelada.sorteiaPelada();
                            pelada.printResultado();
                            System.out.println("\n\nDeseja Sortear novamente?: 1- Sim 0- Não");
                            escolha1 = scan.nextInt();
                            System.out.println("");
                        }
                    }
                    case 2->{
                        while(!pelada.emitirLista(edicao)){
                            System.out.println("\nDeseja tentar novamente? 1- Sim 0- Não");
                            int a = scan.nextInt();
                            if(a == 0)
                                break;
                        }
                    }
                    case 3->{
                        pelada.confereNotas(edicao);
                        pelada.printPelada();
                        pelada.ordenaLista();
                        System.out.println("\nLista atualizada");
                    }
                    case 4->{
                        System.out.println("Insira a nova ediçao (Ediçao atual: "+edicao+")");
                        scan.nextLine();
                        edicao = scan.nextLine();
                        try {
                            pelada = Pelada.criaPelada(edicao);
                        } catch (Exception e) {
                        }
                    }
                    case 0->{
                    }
                    default -> System.out.println("\nSelecione uma opção válida");
                }
            }
            System.out.println("\nEncerrando aplicação\nSALAME S.A.agradece");
            scan.nextLine();
        }
    }
}