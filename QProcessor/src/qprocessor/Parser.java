package qprocessor;

public class Parser extends Base
{
    private final int INITIAL_STATE = 0;
    private final int ERROR_STATE = -2;
    private final int FINAL_STATE = -1;
    
    protected final int NUM_COMMANDS = 5;
    protected String[] commands = new String[5];

    private String command;
    private int state;
    
    Scanner scanner = new Scanner();
    Grammar grammar;

    public Parser()
    {
        commands[0] = "INSERT";
        commands[1] = "CREATE";
        commands[2] = "DROP";
        commands[3] = "SELECT";
        commands[4] = "DELETE";
    }
        
    public void SetCommand(String cmd)
    {
        command = cmd;
        error = 0;
        scanner.SetCommand(cmd.trim());        
    }

    public boolean isCommand(String cmd)
    {
        for(int i = 0;i < NUM_COMMANDS;i++)
            if( cmd.compareToIgnoreCase(commands[i]) == 0) return true;
        return false;
    }
    
    ParseTree GetParsedTree()
    {
        return grammar.parsetree;
    }

    public int Execute ()
    {
        String token = "";
        String cmd = "";

        error = 0;
        
        // Get the first toke and define what command
        cmd = scanner.GetNextToken();
        if( scanner.GetLastError() != 0)
        {
            error = scanner.GetLastError();
            return error;
        }
        
        if(!isCommand(cmd))
        {
            error = 200;  // Command Invalid
            return(error);
        }
       
        // Instiate the correspodent grammar
        ////////////////
        if( cmd.compareToIgnoreCase(commands[0]) == 0) // Insert
              grammar = new Grammar_Insert_Into();

        if( cmd.compareToIgnoreCase(commands[1]) == 0) // Create
              grammar = new Grammar_Create_Table();
        
        if( cmd.compareToIgnoreCase(commands[2]) == 0) // Drop
              grammar = new Grammar_Drop_Table();

        if( cmd.compareToIgnoreCase(commands[3]) == 0) // Select
              grammar = new Grammar_Select_From_Where_Orderby();

        state = INITIAL_STATE;    
        grammar.InsertParseTree(state, cmd);
        
        while ( (state != ERROR_STATE && state != FINAL_STATE ) && !scanner.EOT() )
        {
            token = scanner.GetNextToken();
            if( scanner.GetLastError() != 0)
            {
                error = scanner.GetLastError();
                return error;
            }
            
            state = grammar.TransitionTable(state, token);
            if(state != ERROR_STATE) 
            {
                error = grammar.InsertParseTree(state, token);
                if(error>0) return error;
            }
            
        }
        
        //grammar.print();
        if(!grammar.FinalOK(state))
            error = 202;
       
        
        if(state == ERROR_STATE) error = 201; // Syntax error
        return error;
    }
}
