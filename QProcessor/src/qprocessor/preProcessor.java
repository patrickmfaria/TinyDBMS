package qprocessor;

import Disk.Disk;
import Disk.Schema;
import Disk.SchemaMgr;
import Disk.Table;
import java.util.ListIterator;
import java.util.Vector;

public class preProcessor extends BaseExecution
{
    
    public preProcessor(SchemaMgr schemaMgr, Disk disk)
    {
        super(schemaMgr,disk);
    }
    
    public boolean Run()
    {
        Vector<String> tableNames ;
        Vector<String> fieldNames ;
        ListIterator it_table = null;
        String tmp_table;
        
        error = 0;
        
        // Check if the tables exist
        // Verify from FROM Clause
        // Get the fields name
        tableNames = parsetree.GetListAtributes("<FromList>", "<Rel>", "<Atribute>");
        if(tableNames.isEmpty())
        {
            error = 905; // Internal error - From list
            return false;
        }

        // Check if table exists
        it_table = tableNames.listIterator();
        while (it_table.hasNext()) 
        {
            tmp_table = (String)it_table.next();
            if(schemaMgr.getSchema(tmp_table) == null )
            {
                error = 401; // Execution Error  - Table do not exist
                return false;
            }
        }
                
        ListIterator it_field;
        String tmp_field;
        
        int cont_nschema = 0;
        int cont_field_exist = 0;
        
        // check if all columns exists
        // Check columns from Select
        // Get the fields name
        fieldNames = parsetree.GetListAtributes("<SelList>", "<Field>", "<Atribute>");
        if(fieldNames.isEmpty())
        {
            error = 901; // Internal error - Fields list
            return false;
        }
        
        // if '*' get all fields of the relation
        // Assuming for now just on relation
        it_field = fieldNames.listIterator();        
        while (it_field.hasNext()) 
        {
            String tmp_type = (String)it_field.next();
            if(tmp_type.equalsIgnoreCase("*")) 
            {
                fieldNames.clear();
                // Assuming just on table in this case
                Schema schemaPtr = schemaMgr.getSchema(tableNames.firstElement());
                fieldNames = schemaPtr.getFields();
                break;
            }
        }
        
        it_field = fieldNames.listIterator();
        while (it_field.hasNext()) 
        {
            tmp_field = (String)it_field.next();
            // if literal or number discard
            if(tmp_field.charAt(0) != 39 && !Character.isDigit(tmp_field.charAt(0)))
            {
                Vector<String> tmp_tableNames; 
                // Check if the field has table name together
                // if don't have check for all FROM tables 
                // if has just check into that specific table
                if(tmp_field.contains("."))
                {
                    // Extract the table
                    tmp_tableNames = new Vector<String>();
                    tmp_table = tmp_field.substring(0, tmp_field.indexOf("."));
                    tmp_tableNames.add(tmp_table);
                    // take of the "."
                    tmp_field = tmp_field.substring(tmp_field.indexOf(".")+1, tmp_field.length());
                } else
                    tmp_tableNames = tableNames;
                
                it_table = tmp_tableNames.listIterator();
                Schema tmp_schema;
                cont_nschema = 0;
                cont_field_exist = tmp_tableNames.size();
                while (it_table.hasNext()) 
                {
                    tmp_table = (String)it_table.next();
                    tmp_schema = schemaMgr.getSchema(tmp_table);
                    if(tmp_schema == null)
                    {
                        error = 413; // Table in sel list field name doesn't exist
                        return false;                
                    }
                    if(!tmp_schema.isFromSchema(tmp_field))
                        cont_field_exist--;
                    else
                        cont_nschema++;
                }
                if(cont_field_exist<=0)  // found it in more than one schema
                {
                    error = 407; // Field don't exist field list
                    return false;
                }
                if(cont_nschema>1)
                {
                    error = 408; // Mut to specify table
                    return false;
                }
            }
        }
            
        cont_nschema = 0;
        // Check columns from Order by
        fieldNames = parsetree.GetListAtributes("<OrderbyList>", "<Order>", "<Atribute>");
        /*if(fieldNames.isEmpty())
        {
            error = 909; // Internal error - Fields list - Order list
            return false;
        }
        */
        it_field = fieldNames.listIterator();
        while (it_field.hasNext()) 
        {
            tmp_field = (String)it_field.next();
            // if literal or number discard
            if(tmp_field.charAt(0) != 39 && !Character.isDigit(tmp_field.charAt(0)))
            {
                Vector<String> tmp_tableNames; 
                // Check if the field has table name together
                // if don't have check for all FROM tables 
                // if has just check into that specific table
                if(tmp_field.contains("."))
                {
                    // Extract the table
                    tmp_tableNames = new Vector<String>();
                    tmp_table = tmp_field.substring(0, tmp_field.indexOf("."));
                    tmp_tableNames.add(tmp_table);
                    // take of the "."
                    tmp_field = tmp_field.substring(tmp_field.indexOf(".")+1, tmp_field.length());
                } else
                    tmp_tableNames = tableNames;
                
                it_table = tmp_tableNames.listIterator();
                Schema tmp_schema;
                cont_nschema = 0;
                cont_field_exist = tmp_tableNames.size();
                while (it_table.hasNext()) 
                {
                    tmp_table = (String)it_table.next();
                    tmp_schema = schemaMgr.getSchema(tmp_table);
                    if(tmp_schema == null)
                    {
                        error = 413; // Table in field name doesn't exist
                        return false;                
                    }
                    if(!tmp_schema.isFromSchema(tmp_field))
                        cont_field_exist--;
                    else
                        cont_nschema++;
                }
                if(cont_field_exist<=0)  // found it in more than one schema
                {
                    error = 407; // Field don't exist
                    return false;
                }
                if(cont_nschema>1)
                {
                    error = 408; // Mut to specify table
                    return false;
                }
            }
        }

        cont_nschema = 0;
        // Check columns from Where
        fieldNames = parsetree.GetListAtributes("<ConditionList>", "<Cond>", "<Operand>");
        /*if(fieldNames.isEmpty())
        {
            error = 910; // Internal error - Fields list - Condition list
            return false;
        }
        */
        it_field = fieldNames.listIterator();
        while (it_field.hasNext()) 
        {
            tmp_field = (String)it_field.next();
            // if literal or number discard
            if(tmp_field.charAt(0) != 39 && !Character.isDigit(tmp_field.charAt(0)))
            {
                Vector<String> tmp_tableNames; 
                // Check if the field has table name together
                // if don't have check for all FROM tables 
                // if has just check into that specific table
                if(tmp_field.contains("."))
                {
                    // Extract the table
                    tmp_tableNames = new Vector<String>();
                    tmp_table = tmp_field.substring(0, tmp_field.indexOf("."));
                    tmp_tableNames.add(tmp_table);
                    // take of the "."
                    tmp_field = tmp_field.substring(tmp_field.indexOf(".")+1, tmp_field.length());
                } else
                    tmp_tableNames = tableNames;
                
                it_table = tmp_tableNames.listIterator();
                Schema tmp_schema;
                cont_nschema = 0;
                cont_field_exist = tmp_tableNames.size();
                while (it_table.hasNext()) 
                {
                    tmp_table = (String)it_table.next();
                    tmp_schema = schemaMgr.getSchema(tmp_table);
                    if(tmp_schema == null)
                    {
                        error = 413; // Table in field name doesn't exist
                        return false;                
                    }
                    if(!tmp_schema.isFromSchema(tmp_field))
                        cont_field_exist--;
                    else
                        cont_nschema++;
                }
                if(cont_field_exist<=0)  // found it in more than one schema
                {
                    error = 407; // Field don't exist field list
                    return false;
                }
                if(cont_nschema>1)
                {
                    error = 408; // Mut to specify table
                    return false;
                }
            }
        }
        
        //
        //
        //
        // Check codition datatyes
        //  to perform this whan evaluating the expressions
        //
        //
        
        return true;
    }

}
