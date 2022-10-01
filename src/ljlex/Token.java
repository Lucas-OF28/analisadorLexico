package ljlex;


//Estrutura de dados do Token.
public class Token {
     public TipoToken nome;
    public String lexema;
    
    
    public Integer linha;
    
    public Token(TipoToken nome, String lexema, Integer linha){
      this.nome = nome;
      this.lexema = lexema;
      
      this.linha = linha;
}
    
//Construtor
    @Override
    public String toString() {
        return "<"+"Token "+nome+", Dado Analisado: "+lexema+ "> "+" Linha: "+linha+'\n'+'\n';
    }    
}

