package ljlex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeitorDeArquivosTexto {
    private final static int  TAMANHO_BUFFER = 20;
    int[] bufferDeLeitura;
    int ponteiro;
    int bufferAtual;
    int inicioLexema;
    private String lexema;
    private int cont;
    
    private void inicializarBuffer(){
        bufferAtual = 2;
        inicioLexema =0;
        lexema = "";
        bufferDeLeitura = new int[TAMANHO_BUFFER * 2];
        ponteiro = 0;
        recarregarBuffer1(); 
        
    }
    
     private int lerCaractereDoBuffer(){
         int ret = bufferDeLeitura[ponteiro];
         //System.out.println(this);
         incrementarPonteiro();
         return ret;
     }
         
    private void incrementarPonteiro(){
        ponteiro++;
        if(ponteiro == TAMANHO_BUFFER ){ // metade do buffer, recarrega o buffer 2
            recarregarBuffer2();
        }else if(ponteiro == TAMANHO_BUFFER * 2){
            recarregarBuffer1(); // chega no final do segundo buffer e reinicia o ponteiro
            ponteiro = 0;
        }
    }
    
     // Buffer da esquerda, verifica os caracteres a esquerda.
    private void recarregarBuffer1(){//Ler o arquivo de entra bite por bite e colocar no array
        if(bufferAtual == 2){
            bufferAtual = 1;
        
        for(int i=0; i<TAMANHO_BUFFER; i++){
            try {
                bufferDeLeitura[i] = is.read();          
                if(bufferDeLeitura[i] == -1){
                    break;
            }
            } catch (IOException ex) {
                Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       }
    }
     // Buffer da direita, verifica os caracteres a direita.
     private void recarregarBuffer2(){
         if(bufferAtual == 1){
             bufferAtual = 2;
        for(int i=TAMANHO_BUFFER; i<TAMANHO_BUFFER * 2; i++){
            try {
                bufferDeLeitura[i] = is.read();          
                if(bufferDeLeitura[i] == -1){
                    break;
            }
            } catch (IOException ex) {
                Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       }
    }
    
      InputStream is;
       public LeitorDeArquivosTexto(String arquivo){
        try {
            is= new FileInputStream(new File(arquivo)); //passa o caminho do arquivo de entrada
            inicializarBuffer();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public int lerProximoCaractere(){ 
        int c = lerCaractereDoBuffer();
        lexema+= (char)c;
        return c;
   }
    public void retroceder(){
        ponteiro--; // retorna o ponteiro para a posicao anterior
        lexema = lexema.substring(0, lexema.length()-1);
        if(ponteiro < 0){
            ponteiro = TAMANHO_BUFFER * 2 - 1;
        }
    }
    
    public void zerar(){ // retorna o ponteiro ao inicio
        ponteiro = inicioLexema;
        lexema = "";
    }
    public void confirmar(){
       // System.out.print(lexema);
        inicioLexema = ponteiro; // retorna o ponteiro ao inicio
        lexema = "";
    }
    
    public String getLexema(){
          return lexema; 
    }
    
    /*@Override
    public String toString(){
        String ret = "Buffer:[";
        for(int i : bufferDeLeitura){
            char c = (char)i;
            if(Character.isWhitespace(c)){
                ret += ' ';
            }else{
                ret += (char) i;
            }
        }
        ret +="]\n";
        ret += "       ";
        for(int i = 0; i< TAMANHO_BUFFER * 2; i++){
            if(i == inicioLexema && i == ponteiro){
            ret += "%";
            }else if (i==inicioLexema){
                ret += "^";
            }else if (i==ponteiro){
                ret += "*";
            }else {
                ret += " ";
            }
        }
        return ret;
       
    }*/
}
