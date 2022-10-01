package ljlex;

public enum TipoToken {
    PCDeclaracoes, Integer, Float, Return, 
    //Imprimir e ler
    Cin, Cout, ComentLinha, ComentBloco,
    //Inicio
    PCVoid, Main,
    //Tokens Aritmeticos
    OpAritMult, OpAritDiv, OpAritSoma, OpAritSub,
    //Comparações
    OpMenor, OpMenorIgual, OpMaiorIgual, OpMaior, OpIgual, Diferente, Increment, Decrement,
    //Parametros
    Delim, AbrePar, FechaPar, AbreChave, FechaChave, AbreColch, FechaColch,
    //Variaveis e tipo de variaveis
    Var, NumInt, NumReal, Cadeia, Fim, Literal,
    //Condições
    If, Else, While, Do, For
}
