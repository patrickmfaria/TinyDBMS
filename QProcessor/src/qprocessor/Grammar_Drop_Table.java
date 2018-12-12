package qprocessor;

public class Grammar_Drop_Table extends Grammar
{
    void CreateTransitionTable()
    {
        TransitionTable = new int[3][3];   
        
        TransitionTable[0][0] = 1;
        TransitionTable[0][1] = ERROR_STATE;
        TransitionTable[0][2] = ERROR_STATE;

        TransitionTable[1][0] = 2;
        TransitionTable[1][1] = 2;
        TransitionTable[1][2] = ERROR_STATE;

        TransitionTable[2][0] = FINAL_STATE;
        TransitionTable[2][1] = FINAL_STATE;
        TransitionTable[2][2] = ERROR_STATE;
    }

    int ConvertToken(String token)
    {
        if(token.compareToIgnoreCase("Table") == 0) return 0;
        if(Character.isLetter(token.charAt(0))) return 1; // Valid Atribute
       
        return 2;
    }

    int InsertParseTree(int state, String token) 
    {

        // Insert root if first call
        try
        {
        switch(state)
        {
            case 0:
                break;
            case 1:
                parsetree.InsertNode("", "DT", state); // Create table command
                parsetree.InsertNode("DT", "<TableName>", state); 
                break;
            case 2:
                parsetree.InsertNode("<TableName>", "<Atribute>", state); 
                parsetree.InsertNode("<Atribute>", token, state); 
                break;
            case -1:
                break;
        }
        } catch(Exception e)
        { 
            return 300; // semantic error
        }
        return 0;
    }
    
}
