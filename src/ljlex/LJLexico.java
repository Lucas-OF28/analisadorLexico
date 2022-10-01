package ljlex;

public class LJLexico {
    LeitorDeArquivosTexto ldat;
    int linha = 1;
    public LJLexico (String arquivo){
        ldat = new LeitorDeArquivosTexto(arquivo);
}
    public Token proximoToken(){ // vai fazer a leitura do arquivo de entrada txt
        Token proximo = null; // até reconhecer um padrao e irá retornar o token que reconheceu ou erro
        espacos();
        ldat.confirmar();
        proximo = fim();
        
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }     
        proximo = palavrasChaves();
        if(proximo == null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        } 
        proximo = literal();
        if(proximo == null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        } 
        proximo = variavel();
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }   
        proximo = numeros();
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }        
        proximo = operadorAritmetico();
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }        
        proximo = operadorRelacional();
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }        
        proximo = delimitador();
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }           
        proximo = parenteses();
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }        
         proximo = chaves();
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }        
        proximo = cadeia();
        if(proximo ==null){
            ldat.zerar();
        }else{
            ldat.confirmar();
            return proximo;
        }        
             System.err.println("erro lexico");  
             System.err.println(ldat.toString());
             return null;
    }
    private Token operadorAritmetico(){ //Leitor dos operadores Aritmeticos
        int caractereLido = ldat.lerProximoCaractere();
        char c =(char)caractereLido;
        if(c == '*'){
            return new Token(TipoToken.OpAritMult, ldat.getLexema(),linha);
        }else if(c == '/'){
            c = (char) ldat.lerProximoCaractere();
                    if(c=='/'){
                        c = (char) ldat.lerProximoCaractere();
                            if(c=='>'){
                                return new Token(TipoToken.ComentBloco, ldat.getLexema(),linha);
                            }else{
                                return new Token(TipoToken.ComentLinha, ldat.getLexema(),linha);
                            }
                    }else{
                         return new Token(TipoToken.OpAritDiv, ldat.getLexema(),linha);
                    }
        }else if(c == '+'){
               c = (char) ldat.lerProximoCaractere();
               if(c=='+'){
                   return new Token(TipoToken.Increment, ldat.getLexema(),linha);
               }else{
                   ldat.retroceder();
                   return new Token(TipoToken.OpAritSoma, ldat.getLexema(),linha);
               }
        }else if(c == '-'){
            c = (char) ldat.lerProximoCaractere();
            if(c == '-'){
                  return new Token(TipoToken.Decrement, ldat.getLexema(),linha);
            }else{
                ldat.retroceder();
                return new Token(TipoToken.OpAritSub, ldat.getLexema(),linha);
            }
        }else{
            return null;
        }
    }
    private Token parenteses(){ // Leitor dos parenteses
        int caractereLido = ldat.lerProximoCaractere();
        char c =(char)caractereLido;
        if(c == '('){
            return new Token(TipoToken.AbrePar, ldat.getLexema(),linha);
        }else if (c == ')'){
            return new Token(TipoToken.FechaPar, ldat.getLexema(),linha);
        }else{
            return null;
        }
    } 
    private Token chaves(){ // Leitor das chaves
        int caractereLido = ldat.lerProximoCaractere();
        char c =(char)caractereLido;
        if(c == '{'){
            return new Token(TipoToken.AbreChave, ldat.getLexema(),linha);
        }else if (c == '}'){
            return new Token(TipoToken.FechaChave, ldat.getLexema(),linha);
        }else{
            return null;
        }
    } 
      private Token colchete(){ // Leitor das chaves
        int caractereLido = ldat.lerProximoCaractere();
        char c =(char)caractereLido;
        if(c == '['){
            return new Token(TipoToken.AbreColch, ldat.getLexema(),linha);
        }else if (c == ']'){
            return new Token(TipoToken.FechaColch, ldat.getLexema(),linha);
        }else{
            return null;
        }
    } 
    private Token delimitador(){
        int caractereLido = ldat.lerProximoCaractere();
        char c =(char)caractereLido;
        if(c == ':'){
            return new Token(TipoToken.Delim, ldat.getLexema(),linha);
        }else{
            return null;
        }
    } 
    private Token literal(){
        int caractereLido = ldat.lerProximoCaractere();
        char c =(char)caractereLido;
        if(c == '"'){
            return new Token(TipoToken.Literal, ldat.getLexema(),linha);
        }else{
            return null;
        }
    } 
    private Token operadorRelacional(){
        int caractereLido = ldat.lerProximoCaractere();
        char c =(char)caractereLido;
        if(c == '<'){
               c = (char) ldat.lerProximoCaractere();
               if(c=='='){
                   return new Token(TipoToken.OpMenorIgual, ldat.getLexema(),linha);
               }else{
                   ldat.retroceder();
                   return new Token(TipoToken.OpMenor, ldat.getLexema(),linha);
               }
        }else if(c=='='){
            c = (char) ldat.lerProximoCaractere();
            if(c == '='){
                return new Token(TipoToken.Diferente, ldat.getLexema(),linha);
            }else{
                ldat.retroceder();
                return new Token(TipoToken.OpIgual, ldat.getLexema(),linha);
            }   
        }else if(c== '>'){
            c = (char) ldat.lerProximoCaractere();
          if(c == '='){
                return new Token(TipoToken.OpMaiorIgual, ldat.getLexema(),linha);
            }else{
                ldat.retroceder();
                return new Token(TipoToken.OpMaior, ldat.getLexema(),linha);
            }   
        }else{
            return null;
        }     
  }
    private Token numeros(){
        int estado = 1;
        while(true){
            char c = (char) ldat.lerProximoCaractere();
            if(estado == 1){
                if(Character.isDigit(c)){
                    estado = 2;
                }else{
                    return null;
                }
            }else if (estado == 2){
                if(c == '.'){
                    c = (char) ldat.lerProximoCaractere();
                    if(Character.isDigit(c)){
                        estado = 3;
                    }else{
                        return null;
                    }
                }else if(!Character.isDigit(c)){
                    ldat.retroceder();
                    return new Token(TipoToken.NumInt, ldat.getLexema(),linha);
                }
            }else if(estado == 3){
                if(!Character.isDigit(c)){
                    ldat.retroceder();
                    return new Token(TipoToken.NumReal, ldat.getLexema(),linha);
                }
            }
        }
    }
    private Token variavel(){
        int estado = 1;
        while(true){
            char c = (char) ldat.lerProximoCaractere();
            if(estado == 1){
                if(Character.isLetter(c)){
                    estado = 2;
                }else{
                    return null;
                }
            }else if(estado == 2){
                if(!Character.isLetterOrDigit(c)){
                    ldat.retroceder();
                    return new Token(TipoToken.Var, ldat.getLexema(),linha);
                }
          }
        }
    }
    private Token cadeia(){
        int estado = 1;
        while(true){
            char c = (char) ldat.lerProximoCaractere();
            if(estado == 1){
                if(c =='\''){
                    estado = 2;
                }else{
                    return null;
                }
            }else if(estado == 2){
                if (c=='\n'){
                    linha+=1;
                    return null;
                }
                if(c=='\''){
                    return new Token(TipoToken.Cadeia, ldat.getLexema(),linha);
                }else if(estado == 3){
                    if(c=='\n'){
                        linha+=1;
                        return null;
                    }else{
                        estado = 2;
                    }
                }
            }
        }
    }
    private void espacos(){
        int estado = 1;
        
        while(true){
            char c=(char)ldat.lerProximoCaractere();
            if(c == '\n'){      
                linha++;        
            }    
            if(estado == 1){
                if(Character.isWhitespace(c) || c==' '){
                    estado = 2;
                }else if(c == '%'){
                    estado = 3;
                }else{
                    ldat.retroceder();
                    return;
                }
            }else if(estado == 2){
                if(c == '%'){
                    estado = 3;
                }else if(!(Character.isWhitespace(c) || c == ' ')){
                    ldat.retroceder();
                    return;
                }
            }else if(estado == 3){
                if(c == '\n'){
                    return;
            }
        }
    }
}
    private Token palavrasChaves(){
        while(true){
            char c = (char)ldat.lerProximoCaractere();
            if (!Character.isLetter(c)){
                    ldat.retroceder();
                  String lexema = ldat.getLexema();
                  if(lexema.equals("DECLARACOES")){
                      return new Token(TipoToken.PCDeclaracoes, lexema,linha);
                  }else if (lexema.equals("Integer")){
                      return new Token(TipoToken.Integer, lexema, linha);
                  }else if (lexema.equals("Float")){
                      return new Token(TipoToken.Float, lexema, linha);
                  }else if (lexema.equals("Return")){
                      return new Token(TipoToken.Return, lexema, linha);
                  }else if (lexema.equals("Cin")){
                      return new Token(TipoToken.Cin, lexema, linha);
                  }else if (lexema.equals("Cout")){
                      return new Token(TipoToken.Cout, lexema, linha);
                  }else if (lexema.equals("If")){
                      return new Token(TipoToken.If, lexema, linha);
                  }else if (lexema.equals("Else")){
                      return new Token(TipoToken.Else, lexema, linha);
                  }else if (lexema.equals("For")){
                      return new Token(TipoToken.For, lexema, linha);
                  }else if (lexema.equals("While")){
                      return new Token(TipoToken.While, lexema, linha);
                  }else if (lexema.equals("Void")){
                      return new Token(TipoToken.PCVoid, lexema,linha);
                  }else if (lexema.equals("Main")){
                      return new Token(TipoToken.Main, lexema, linha);
                  }else if (lexema.equals("Do")){
                      return new Token(TipoToken.Do, lexema, linha);
                  }else{
                      return null;
                  }
                
            }
        }
    }
    private Token fim(){
        int caracterLido = ldat.lerProximoCaractere();        
        if (caracterLido == -1){
            return new Token(TipoToken.Fim,"Fim",linha );
        }
        return null;
    }
}