package qprocessor;

public class Scanner  extends Base
{
    
    private int[][] TransitionTable = new int[5][7];
    
    private final int INITIAL_STATE = 0;
    private final int ERROR_STATE = -2;
    private final int FINAL_STATE = -1;
    
    private String command;
    private int pos;
    private int state;
    
    public Scanner()
    {
        command  = "";
        pos = 0;
        state = INITIAL_STATE;
        error = 0;
        
        TransitionTable[0][0] = ERROR_STATE;
        TransitionTable[0][1] = 1;
        TransitionTable[0][2] = 2;
        TransitionTable[0][3] = FINAL_STATE;
        TransitionTable[0][4] = 3;
        TransitionTable[0][5] = 4;
        TransitionTable[0][6] = ERROR_STATE;

        TransitionTable[1][0] = FINAL_STATE;
        TransitionTable[1][1] = 1;
        TransitionTable[1][2] = ERROR_STATE;
        TransitionTable[1][3] = FINAL_STATE;
        TransitionTable[1][4] = 2;
        TransitionTable[1][5] = ERROR_STATE;
        TransitionTable[1][6] = ERROR_STATE;

        TransitionTable[2][0] = FINAL_STATE;
        TransitionTable[2][1] = 1;
        TransitionTable[2][2] = 2;
        TransitionTable[2][3] = FINAL_STATE;
        TransitionTable[2][4] = 2;
        TransitionTable[2][5] = ERROR_STATE;
        TransitionTable[2][6] = ERROR_STATE;

        TransitionTable[3][0] = ERROR_STATE;
        TransitionTable[3][1] = ERROR_STATE;
        TransitionTable[3][2] = 3;
        TransitionTable[3][3] = FINAL_STATE;
        TransitionTable[3][4] = ERROR_STATE;
        TransitionTable[3][5] = ERROR_STATE;
        TransitionTable[3][6] = ERROR_STATE;
        
        TransitionTable[4][0] = 4;
        TransitionTable[4][1] = 4;
        TransitionTable[4][2] = 4;
        TransitionTable[4][3] = 4;
        TransitionTable[4][4] = 4;
        TransitionTable[4][5] = FINAL_STATE;
        TransitionTable[4][6] = ERROR_STATE;

    }
    
    public void SetCommand(String cmd)
    {
        command = cmd.trim();
        pos = 0;
        state = INITIAL_STATE;
        error = 0;
    }
    
    private boolean isOperator(char ic)
    {
        if(ic == '*' || ic == '-' || ic == '+' || ic == '=' || ic == '/' || ic == '(' || ic == ')' || ic == ',' || ic =='>' || ic == '<') 
            return true;
        else
            return false;
    }
    
    private int Convert_Char(char ic)
    {
        if(Character.isSpaceChar(ic)) return 0;
        if(Character.isDigit(ic)) return 1;
        if(Character.isLetter(ic)) return 2;
        if(ic == '_') return 2;
        if(isOperator(ic)) return 3;
        if(ic == '.') return 4;
        if(ic == 39) return 5; // simple quote
        return 6;
    }
    
    // End of tokend
    public boolean EOT()
    {
        if(pos>=command.length()) return true;
        else return false;
    }
    
    public String GetNextToken()
    {
        char cur_char;
        int start =0;
                
        error = 0;
        // Trailling the spaces
        while (command.charAt(pos) == ' ' && pos < command.length()) pos++;
               
        start = pos;
        state = INITIAL_STATE;    
        while ( ( state != ERROR_STATE && state != FINAL_STATE ) && pos < command.length() )
        {   
            cur_char = command.charAt(pos);
            if(isOperator(cur_char) && state == INITIAL_STATE ) 
            {
                pos++;
                break;
            }
            
            state = TransitionTable[state][Convert_Char(cur_char)];
            if(state == FINAL_STATE && isOperator(cur_char) ) break;
            pos++;
        }
        
        if ( state == INITIAL_STATE || pos >= command.length() || state == FINAL_STATE  )
            return command.substring(start, pos).trim();
        else 
        {
            error = 100;  // Error lex
            return "@ERROR: Lex Error";
        }
    }
}
