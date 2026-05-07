

public class Time {
    private Jogador goleiro;
    private final Jogador[] linha;

    public Time() {
        goleiro = null;
        linha = new Jogador[5];
    }

    public void printTime(){
        System.out.println("\n"+goleiro.getNome());
        for(Jogador j:linha){
            System.out.println(j.getNome());
        }
    }

    public String getStringTime(){
        String retorno = goleiro.getNome()+"\n";
        int cont = 0;
        for(Jogador j:linha){
            retorno+=j.getNome();
            if(cont!=linha.length-1)
                retorno+="\n";
            cont++;
        }
        return retorno;
    }

    public boolean add45(Jogador j){
        if(linha[0]==null){
            linha[0]=j;
            return true;
        }else if(linha[1]==null){
            linha[1]=j;
            return true;
        }else{
            return false;
        }
    }

    public boolean add123(Jogador j){
        if(linha[2]==null){
            linha[2] = j;
            return true;
        }else if(linha[3]==null){
            linha[3]=j;
            return true;
        }else if(linha[4]==null){
            linha[4]=j;
            return true;
        }else{
            return false;
        }
    }

    public void limpatimes(){
        goleiro=null;
        for (int i = 0; i < 5; i++) {
            linha[i]=null;
        }
    }

    public double getVarTime(){
        double somavar= goleiro.getOverall()-getTeamOver();
        somavar*=somavar;
        for(Jogador j:linha){
            double aux = j.getOverall()-getTeamOver();
            somavar+=aux*aux;
        }
        return somavar/6;
    }

    public double getMedia(){
        double soma = goleiro.getOverall();
        for(Jogador j:linha){
            soma+=j.getOverall();
        }
        return soma/6;
    }

    public double getTeamOver(){
        double soma = goleiro.getOverall();
        for(Jogador j:linha){
            soma+=j.getOverall();
        }
        return soma/6;
    }

    public boolean setLinha(Jogador jogador){
        for (int i = 0; i < linha.length; i++) {
            if(linha[i]==null){
                this.linha[i]=jogador;
                return true;
            }
        }
        return false;
    }

    public Jogador getJogador(int pos){
        return linha[pos];
    }

    public void setGoleiro(Jogador goleiro) {
        this.goleiro = goleiro;
    }

    public Jogador getGoleiro() {
        return goleiro;
    }

}
