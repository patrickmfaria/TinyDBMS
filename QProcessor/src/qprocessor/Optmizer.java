package qprocessor;

import Disk.Schema;
import java.util.ListIterator;
import java.util.Vector;

public class Optmizer extends BaseExecution
{
    protected ParseTree sourcetree;

    public void SetSource(ParseTree parsetree)
    {
        sourcetree = parsetree;
    }
    
    public boolean Optmize() 
    {
        Node sel_node;
        Node oper_cond;
        ParseTree optmizedtree;
        ListIterator it_cond = null;
        Vector<String> condition;
        int cont_opers;
        String xoper;

        optmizedtree = parsetree; // Just to easy programming

        Vector<String> tableNames ;
        Vector<String> fromtableNames ;
        fromtableNames = sourcetree.GetListAtributes("<FromList>", "<Rel>", "<Atribute>");

        // Get all conditions
        sel_node = optmizedtree.SearchNode(optmizedtree.root, "<SELECTION>"); 
        
        // scan all conditions
        it_cond = sel_node.leafs.listIterator();
        while (it_cond.hasNext()) 
        {
            // get the condition operators
            oper_cond  = (Node)it_cond.next();
            condition = optmizedtree.GetListAtributes("<SELECTION>", oper_cond.name , "Oper");
            // scan all operators and operands and verify if all belongs the same table
            cont_opers = 0;
            Vector<String> tmp_tableNames = new Vector<String>();
            ListIterator it_table = fromtableNames.listIterator();

            while (cont_opers < condition.size())
            {
                xoper = condition.get(cont_opers);
                // if has table name explicited
                if(xoper.contains("."))
                {
                    String tmp_table = xoper.substring(0, xoper.indexOf("."));
                    tmp_tableNames.add(tmp_table);
                }
                else
                {
                    // Find the field into all tables list in FROM clause
                    it_table = fromtableNames.listIterator();
                    Schema tmp_schema;
                    while (it_table.hasNext()) 
                    {
                        String tmp_table = (String)it_table.next();
                        tmp_schema = schemaMgr.getSchema(tmp_table);
                        if(tmp_schema.isFromSchema(xoper))
                            tmp_tableNames.add(tmp_table);
                    }
                    
                }
                // Verify if there are diferents tables
                boolean dif_tables = false;
                it_table = tmp_tableNames.listIterator();
                // Ate least one table id went thought prepprocessor
                String tmp_table = (String)it_table.next();
                while(it_table.hasNext()) 
                {
                    String tmp2_table = (String)it_table.next();
                    if(!tmp_table.equalsIgnoreCase(tmp2_table) )
                    {
                        dif_tables = true;
                        break;
                    }
                }
                
                if(!dif_tables)
                {
                    
                }
                else
                {
                    
                }
            }
        
            
            // Check consistency
            // If left side and righ side are number or lit
        // if condition comparing with numbers or literals ( regular conditions)
        
            // find the the field
            // get the relation of the field
            // Push down the condition
        
        // if condition is comparation between relations
        
            // get relation 1
            // get relation 2
        
            // find the product with both relations
        
            // push down
        }

        return true;
    } 
}

