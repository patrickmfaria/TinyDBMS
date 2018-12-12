package qprocessor;

public abstract class Grammar
{
    protected final int ERROR_STATE = -2;
    protected final int FINAL_STATE = -1;
    
    protected int field_id;
    protected int value_id;
    protected int rel_id;
    protected int order_id;
    protected int cond_id;

    protected int[][] TransitionTable;
    
    public ParseTree parsetree;

    public Grammar()
    {
        parsetree = new ParseTree();
        field_id = 0;
        value_id = 0;
        rel_id = 0;
        order_id = 0;
        cond_id = 0;

        CreateTransitionTable();
    }
    
    abstract void CreateTransitionTable();
    abstract int InsertParseTree(int state, String token);   
    abstract int ConvertToken(String token);
    
    int TransitionTable(int state, String token)
    {
        if(TransitionTable[state][ConvertToken(token)] == 0)
            return ERROR_STATE;
        else 
            return TransitionTable[state][ConvertToken(token)];
    }
    
    public boolean FinalOK(int state)
    {
        return true;
    }
    
    void print()
    {
        parsetree.print(parsetree.root);
    }

}
