package qprocessor;

public class Grammar_Create_Table extends Grammar
{
    void CreateTransitionTable()
    {
        TransitionTable = new int[6][7];   
        
        TransitionTable[0][0] = 1;
        TransitionTable[0][1] = ERROR_STATE;
        TransitionTable[0][2] = ERROR_STATE;
        TransitionTable[0][3] = ERROR_STATE;
        TransitionTable[0][4] = ERROR_STATE;
        TransitionTable[0][5] = ERROR_STATE;
        TransitionTable[0][6] = ERROR_STATE;

        TransitionTable[1][0] = 2;
        TransitionTable[1][1] = 2;
        TransitionTable[1][2] = ERROR_STATE;
        TransitionTable[1][3] = ERROR_STATE;
        TransitionTable[1][4] = ERROR_STATE;
        TransitionTable[1][5] = ERROR_STATE;
        TransitionTable[1][6] = ERROR_STATE;

        TransitionTable[2][0] = ERROR_STATE;
        TransitionTable[2][1] = ERROR_STATE;
        TransitionTable[2][2] = 3;
        TransitionTable[2][3] = ERROR_STATE;
        TransitionTable[2][4] = ERROR_STATE;
        TransitionTable[2][5] = ERROR_STATE;
        TransitionTable[2][6] = ERROR_STATE;

        TransitionTable[3][0] = 4;
        TransitionTable[3][1] = 4;
        TransitionTable[3][2] = ERROR_STATE;
        TransitionTable[3][3] = ERROR_STATE;
        TransitionTable[3][4] = ERROR_STATE;
        TransitionTable[3][5] = ERROR_STATE;
        TransitionTable[3][6] = ERROR_STATE;
        
        TransitionTable[4][0] = ERROR_STATE;
        TransitionTable[4][1] = ERROR_STATE;
        TransitionTable[4][2] = ERROR_STATE;
        TransitionTable[4][3] = 5;
        TransitionTable[4][4] = ERROR_STATE;
        TransitionTable[4][5] = ERROR_STATE;
        TransitionTable[4][6] = ERROR_STATE;
        
        TransitionTable[5][0] = ERROR_STATE;
        TransitionTable[5][1] = ERROR_STATE;
        TransitionTable[5][2] = ERROR_STATE;
        TransitionTable[5][3] = ERROR_STATE;
        TransitionTable[5][4] = 3;
        TransitionTable[5][5] = FINAL_STATE;
        TransitionTable[5][6] = ERROR_STATE;
    }

    int ConvertToken(String token)
    {
        if(token.compareToIgnoreCase("Table") == 0) return 0;
        if(token.compareToIgnoreCase("(") == 0) return 2;
        if(token.compareToIgnoreCase(",") == 0) return 4;
        if(token.compareToIgnoreCase(")") == 0) return 5;
        if(token.compareToIgnoreCase("int") == 0 || token.compareToIgnoreCase("String") == 0 ) return 3;
        if(Character.isLetter(token.charAt(0))) return 1; // Valid Atribute
       
        return 6;
    }

    int InsertParseTree(int state, String token) 
    {

        // Insert root if first call
        try
        {
        switch(state)
        {
            case 0:
                //parsetree.InsertNode("", "DDL", state); // DDL type statment
                break;
            case 1:
                parsetree.InsertNode("", "CT", state); // Create table command
                parsetree.InsertNode("CT", "<TableName>", state); 
                parsetree.InsertNode("CT", "<FieldList>", state); 
                break;
            case 2:
                parsetree.InsertNode("<TableName>", "<Atribute>", state); 
                parsetree.InsertNode("<Atribute>", token, state); 
                break;
            case 4:
                if(parsetree.SearchNodeLevel(parsetree.root, token, state))
                        return 301; // Semantic error - fields duplicated
                field_id++;
                parsetree.InsertNode("<FieldList>", "<Field>" + field_id, state); 
                parsetree.InsertNode("<Field>" + field_id , "<Field>" + field_id + "<Atribute>", state); 
                parsetree.InsertNode("<Field>" + field_id + "<Atribute>", token, state); 
                break;
            case 5:
                parsetree.InsertNode("<Field>" + field_id, "<Field>" + field_id + "<Type>", state); 
                parsetree.InsertNode("<Field>" + field_id + "<Type>", token, state); 
                break;
            case -1:
                field_id = 0;
                break;
        }
        } catch(Exception e)
        { 
            return 300; 
        }// semantic error
        return 0;
    }
    
}
