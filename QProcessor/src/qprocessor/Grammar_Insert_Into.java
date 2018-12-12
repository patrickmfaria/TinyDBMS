package qprocessor;

public class Grammar_Insert_Into extends Grammar
{
    void CreateTransitionTable()
    {
        TransitionTable = new int[10][9];   
        int cont =0;
        
        // 0
        TransitionTable[cont][0] = 1;
        TransitionTable[cont][1] = ERROR_STATE;
        TransitionTable[cont][2] = ERROR_STATE;
        TransitionTable[cont][3] = ERROR_STATE;
        TransitionTable[cont][4] = ERROR_STATE;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = ERROR_STATE;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;

        // 1
        TransitionTable[cont][0] = 2;
        TransitionTable[cont][1] = 2;
        TransitionTable[cont][2] = ERROR_STATE;
        TransitionTable[cont][3] = ERROR_STATE;
        TransitionTable[cont][4] = ERROR_STATE;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = 2;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;
        // 2
        TransitionTable[cont][0] = ERROR_STATE;
        TransitionTable[cont][1] = ERROR_STATE;
        TransitionTable[cont][2] = 3;
        TransitionTable[cont][3] = ERROR_STATE;
        TransitionTable[cont][4] = ERROR_STATE;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = ERROR_STATE;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;
        // 3
        TransitionTable[cont][0] = 4;
        TransitionTable[cont][1] = 4;
        TransitionTable[cont][2] = ERROR_STATE;
        TransitionTable[cont][3] = ERROR_STATE;
        TransitionTable[cont][4] = ERROR_STATE;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = 2;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;
        // 4
        TransitionTable[cont][0] = ERROR_STATE;
        TransitionTable[cont][1] = ERROR_STATE;
        TransitionTable[cont][2] = ERROR_STATE;
        TransitionTable[cont][3] = 5;
        TransitionTable[cont][4] = 6;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = ERROR_STATE;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;
        // 5
        TransitionTable[cont][0] = 4;
        TransitionTable[cont][1] = 4;
        TransitionTable[cont][2] = ERROR_STATE;
        TransitionTable[cont][3] = ERROR_STATE;
        TransitionTable[cont][4] = ERROR_STATE;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = ERROR_STATE;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;
        // 6
        TransitionTable[cont][0] = ERROR_STATE;
        TransitionTable[cont][1] = ERROR_STATE;
        TransitionTable[cont][2] = ERROR_STATE;
        TransitionTable[cont][3] = ERROR_STATE;
        TransitionTable[cont][4] = ERROR_STATE;
        TransitionTable[cont][5] = 7;
        TransitionTable[cont][6] = ERROR_STATE;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;
        // 7
        TransitionTable[cont][0] = ERROR_STATE;
        TransitionTable[cont][1] = ERROR_STATE;
        TransitionTable[cont][2] = 8;
        TransitionTable[cont][3] = ERROR_STATE;
        TransitionTable[cont][4] = ERROR_STATE;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = ERROR_STATE;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;
        // 8
        TransitionTable[cont][0] = ERROR_STATE;
        TransitionTable[cont][1] = ERROR_STATE;
        TransitionTable[cont][2] = ERROR_STATE;
        TransitionTable[cont][3] = ERROR_STATE;
        TransitionTable[cont][4] = ERROR_STATE;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = 9;
        TransitionTable[cont][7] = 9;
        TransitionTable[cont][8] = ERROR_STATE;
        
        cont++;
        // 9
        TransitionTable[cont][0] = ERROR_STATE;
        TransitionTable[cont][1] = ERROR_STATE;
        TransitionTable[cont][2] = ERROR_STATE;
        TransitionTable[cont][3] = 8;
        TransitionTable[cont][4] = FINAL_STATE;
        TransitionTable[cont][5] = ERROR_STATE;
        TransitionTable[cont][6] = ERROR_STATE;
        TransitionTable[cont][7] = ERROR_STATE;
        TransitionTable[cont][8] = ERROR_STATE;
        
    }

    int ConvertToken(String token)
    {
        if(token.compareToIgnoreCase("Into") == 0) return 0;
        if(token.compareToIgnoreCase("(") == 0) return 2;
        if(token.compareToIgnoreCase(",") == 0) return 3;
        if(token.compareToIgnoreCase(")") == 0) return 4;
        if(token.compareToIgnoreCase("Values") == 0) return 5;
        
        if( token.charAt(0) == 39) return 6; // Literal
        if( Character.isDigit(token.charAt(0))) return 7; // Number
        
        if(Character.isLetter(token.charAt(0))) return 1; // Valid Atribute
       
        return 8;
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
                parsetree.InsertNode("", "II", state); // Create table command
                parsetree.InsertNode("II", "<TableName>", state); 
                parsetree.InsertNode("II", "<FieldList>", state); 
                parsetree.InsertNode("II", "<ValueList>", state); 
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
            case 9:
                value_id++;
                parsetree.InsertNode("<ValueList>", "<Value>" + value_id, state); 
                parsetree.InsertNode("<Value>" + value_id, "<Value>" + value_id + "<Atribute>", state); 
                parsetree.InsertNode("<Value>" + value_id + "<Atribute>", token, state); 
                break;
            case -1:
                if(field_id != value_id)
                    return 302; // Semantinc error - Number fields differ number values
                field_id = 0;
                value_id = 0;
                break;
        }
        } catch(Exception e)
        { 
            return 300; // semantic error
        }
        return 0;
    }
    
}
