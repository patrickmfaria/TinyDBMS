package qprocessor;

public class Grammar_Select_From_Where_Orderby extends Grammar
{
    void CreateTransitionTable()
    {
        TransitionTable = new int[16][12];   

        TransitionTable[0][0] = 1;
        TransitionTable[0][3] = 6;
        TransitionTable[1][1] = 2;
        TransitionTable[1][2] = 3;
        TransitionTable[2][0] = 1;
        TransitionTable[3][0] = 4;
        TransitionTable[4][1] = 5;
        TransitionTable[4][4] = 7;
        TransitionTable[4][10] = 12;
        TransitionTable[5][0] = 4;
        TransitionTable[6][1] = 2;
        TransitionTable[6][2] = 3;
        TransitionTable[7][0] = 8;
        TransitionTable[7][5] = 8;
        TransitionTable[7][6] = 8;
        TransitionTable[8][7] = 9;
        TransitionTable[9][0] = 10;
        TransitionTable[9][5] = 10;
        TransitionTable[9][6] = 10;
        TransitionTable[10][7] = 9;
        TransitionTable[10][8] = 11;
        TransitionTable[10][9] = 12;
        TransitionTable[11][0] = 8;
        TransitionTable[12][10] = 13;
        TransitionTable[13][0] = 14;
        TransitionTable[14][1] = 15;
        TransitionTable[15][0] = 14;
    }

    int ConvertToken(String token)
    {
        if(token.compareToIgnoreCase(",") == 0) return 1;
        if(token.compareToIgnoreCase("From") == 0) return 2;
        if(token.compareToIgnoreCase("*") == 0) return 3;
        if(token.compareToIgnoreCase("Where") == 0) return 4;
        
        if( token.charAt(0) == 39) return 5; // Literal
        if( Character.isDigit(token.charAt(0))) return 6; // Number

        if(token.compareToIgnoreCase("=") == 0) return 7;
        if(token.compareToIgnoreCase("+") == 0) return 7;
        if(token.compareToIgnoreCase("*") == 0) return 8;
        if(token.compareToIgnoreCase("-") == 0) return 7;
        if(token.compareToIgnoreCase("/") == 0) return 7;
        if(token.compareToIgnoreCase("<") == 0) return 7;
        if(token.compareToIgnoreCase(">") == 0) return 7;

        if(token.compareToIgnoreCase("AND") == 0) return 8;
        if(token.compareToIgnoreCase("OR") == 0) return 8;
        
        if(token.compareToIgnoreCase("ORDER") == 0) return 9;
        if(token.compareToIgnoreCase("BY") == 0) return 10;

        if(Character.isLetter(token.charAt(0))) return 0; // Valid Atribute
       
        return 11;
    }

    int InsertParseTree(int state, String token) 
    {

        // Insert root if first call
        try
        {
        switch(state)
        {
            case 0:
                parsetree.InsertNode("", "SFWO", state); // Select command
                parsetree.InsertNode("SFWO", "<SelList>", state); 
                parsetree.InsertNode("SFWO", "<FromList>", state); 
                parsetree.InsertNode("SFWO", "<ConditionList>", state); 
                parsetree.InsertNode("SFWO", "<OrderbyList>", state); 
                break;
            case 1:
                field_id++;
                parsetree.InsertNode("<SelList>", "<Field>" + field_id, state); 
                parsetree.InsertNode("<Field>" + field_id , "<Field>" + field_id + "<Atribute>", state); 
                parsetree.InsertNode("<Field>" + field_id + "<Atribute>", token, state); 
                break;
            case 4:
                if(parsetree.SearchNodeLevel(parsetree.root, token, state))
                        return 303; // Semantic error - Table duplicated
//                field_id++;
                rel_id++;
                parsetree.InsertNode("<FromList>", "<Rel>" + rel_id, state); 
                parsetree.InsertNode("<Rel>" + rel_id , "<Rel>" + rel_id + "<Atribute>", state); 
                parsetree.InsertNode("<Rel>" + rel_id + "<Atribute>", token, state); 
                break;
            case 6:
                field_id++;
                parsetree.InsertNode("<SelList>", "<Field>" + field_id, state); 
                parsetree.InsertNode("<Field>" + field_id , "<Field>" + field_id + "<Atribute>", state); 
                parsetree.InsertNode("<Field>" + field_id + "<Atribute>", token, state); 
                break;
            case 8:
                cond_id++;
                parsetree.InsertNode("<ConditionList>", "<Cond>" + cond_id, state); 
                parsetree.InsertNode("<Cond>" + cond_id , "<Cond>" + cond_id + "<Operand>", state); 
                parsetree.InsertNode("<Cond>" + cond_id + "<Operand>", token, state); 
                break;
            case 9:
                parsetree.InsertNode("<Cond>" + cond_id , "<Cond>" + cond_id + "<Operator>", state); 
                parsetree.InsertNode("<Cond>" + cond_id + "<Operator>", token, state); 
                break;
            case 10:
                parsetree.InsertNode("<Cond>" + cond_id , "<Cond>" + cond_id + "<ROperand>", state); 
                parsetree.InsertNode("<Cond>" + cond_id + "<ROperand>", token, state); 
                break;
            case 11:
                parsetree.InsertNode("<ConditionList>", "<Bool>" + cond_id, state); 
                parsetree.InsertNode("<Bool>" + cond_id , "<Bool>" + cond_id + "<BOperator>", state); 
                parsetree.InsertNode("<Bool>" + cond_id + "<BOperator>", token, state); 
                break;
            case 14:
                order_id++;
                parsetree.InsertNode("<OrderbyList>", "<Order>" + order_id, state); 
                parsetree.InsertNode("<Order>" + order_id , "<Order>" + order_id + "<Atribute>", state); 
                parsetree.InsertNode("<Order>" + order_id + "<Atribute>", token, state); 
                break;
        }
        } catch(Exception e)
        { 
            return 300; // semantic error
        }
        return 0;
    }
    
    @Override
    // Don't allow senteces finished with FROM, WHERE, AND, OR ORDER BY
    public boolean FinalOK(int state)
    {
        if(state == 7 || state == 9 || state == 11 || state == 12) return false;
        else return true;
    }
}
