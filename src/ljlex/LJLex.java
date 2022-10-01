package ljlex;

public class LJLex {
//Inicia a aplicaçao
    //Comando para rodar Shift+F11 e colar o link no cmd ex: java -jar C:\Users\lab101a local onde esta o txt C:\Users\lab101a\programa.txt
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LJLexico lex = new LJLexico(args[0]);
        Token t = null;
        
        while((t=lex.proximoToken()).nome !=TipoToken.Fim){
            System.out.print(t);
    }
    }
    
}
