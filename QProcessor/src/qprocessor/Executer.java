package qprocessor;

import Disk.*;
import java.util.*;

class Executer extends BaseExecution
{
    StringBuffer output = new StringBuffer();
    int tmp_rel_control; // Control the name of temporary relations

    Executer(int i, int i0, int i1) 
    {
        super(i,i0,i1);
        tmp_rel_control = 0;
    }
        
    public void Run()
    {
        String cmd;
        
        error = 0;
        // Clear the output buffer
        output.delete(0, output.length());
        
        // Get the root node
        cmd = parsetree.root.name;
        
        // dispatch the command execution 
        if( cmd.equalsIgnoreCase("CT")) // Create Table
            Execute_CT();
        else if( cmd.equalsIgnoreCase("DT"))
            Execute_DT(); // Drop Table
        else if( cmd.equalsIgnoreCase("II"))
            Execute_II(); // Insert Into
        else if( cmd.equalsIgnoreCase("SFWO"))
            Execute_SFWO(); // Select
    }
    
    private void Execute_CT()
    {
        String table_name;
        Vector<String> fieldNames ;
        Vector<String> fieldTypes ;
        Schema schemaPtr;
        
        // Get the Table Name
        table_name = parsetree.GetAtributeValue("<TableName>", "<Atribute>");
        if(table_name.length() == 0)
        {
            error = 900; // Internal error - Table Name
            return;
        }
        
        
        // Get the fields name
        fieldNames = parsetree.GetListAtributes("<FieldList>", "<Field>", "<Atribute>");
        if(fieldNames.isEmpty())
        {
            error = 901; // Internal error - Fields list
            return;
        }
        
        // Get the types name
        fieldTypes = parsetree.GetListAtributes("<FieldList>", "<Field>", "<Type>");
        if(fieldNames.isEmpty())
        {
            error = 904; // Internal error - Create Table - Type Fields list
            return;
        }
        
        // Check if table already exists
        if(schemaMgr.getSchema(table_name) != null )
        {
            error = 400; // Execution Error  - Create Table - Table Already exists
            return;
        }
            
        // Create the table
        try {
            schemaPtr = new Schema(fieldNames, fieldTypes, disk); 
        } catch (Exception e)
        { 
            error = 950; // INternal Error - Disk Library - add schema
            return;
        }
        schemaMgr.addSchema(table_name, schemaPtr); 

        // Set output
        output.append("Table " + table_name + " was created sucessuful.\t");
    }

    private void Execute_DT()
    {
        String table_name;
        
        // Get the Table Name
        table_name = parsetree.GetAtributeValue("<TableName>", "<Atribute>");
        if(table_name.length() == 0)
        {
            error = 900; // Internal error - Table Name
            return;
        }
               
        // Check if table already exists
        if(schemaMgr.getSchema(table_name) == null )
        {
            error = 401; // Execution Error  - Table do not exist
            return;
        }
            
        // Drop the table
        try {
            schemaMgr.rmSchema(table_name);
        } catch (Exception e)
        { 
            error = 951; // INternal Error - Disk Library - Remove schema
            return;
        }

        // Set output
        output.append("Table " + table_name + " was droped sucessuful.\t");
    }
    
    private void Execute_II()
    {
        String table_name;
        Vector<String> fieldNames ;
        Vector<String> values ;
        LinkedList<Tuple> tuples = new LinkedList<Tuple>();
        Table tablePtr;
        Schema schemaPtr;
        
        // Get the Table Name
        table_name = parsetree.GetAtributeValue("<TableName>", "<Atribute>");
        if(table_name.length() == 0)
        {
            error = 900; // Internal error  - Table Name
            return;
        }
        
        // Get the fields name
        fieldNames = parsetree.GetListAtributes("<FieldList>", "<Field>", "<Atribute>");
        if(fieldNames.isEmpty())
        {
            error = 901; // Internal error - Fields list
            return;
        }
        
        // Get the values
        values = parsetree.GetListAtributes("<ValueList>", "<Value>", "<Atribute>");
        if(fieldNames.isEmpty())
        {
            error = 902; // Internal error - Create Table - Type Fields list
            return;
        }
        
        // Check if table exists
        if(schemaMgr.getSchema(table_name) == null )
        {
            error = 401; // Execution Error  - Table do not exist
            return;
        }
            
        // Insert into the table
        try {
            schemaPtr = schemaMgr.getSchema(table_name);
        } catch (Exception ex)
        { 
            error = 951; // INternal Error - Disk Library - Get schema
            return;
        }
        
        Tuple tuple;
        // set up a tuple 
        try {   
            tuple = new Tuple(schemaPtr); 
        } catch (Exception ex)
        { 
            error = 952; // Internal Error - Disk Library - New Tuple from Schema
            return;
        }
        
        ListIterator it_field, it_value; 
        String tmp_value, tmp_field;
        
        it_field = fieldNames.listIterator();
        it_value = values.listIterator();
        while (it_field.hasNext()) // If here they have the same number of elements
        {
            try {
                tmp_value = (String)it_value.next();
                tmp_field = (String)it_field.next();
                // Check if  number of literal
                if(tmp_value.charAt(0) == 39) 
                {
                    if(!schemaPtr.getType(tmp_field).equalsIgnoreCase("string"))
                    {
                        error = 403; // Execution Error - Types Incompatible
                        return;        
                    }
                    tmp_value = tmp_value.replace((char)39,' '); // take off the quotes
                    tmp_value = tmp_value.trim();
                    tuple.setString(schemaPtr.getPos(tmp_field), tmp_value);
                } else if ( Character.isDigit(tmp_value.charAt(0)))
                {
                    if(!schemaPtr.getType(tmp_field).equalsIgnoreCase("int"))
                    {
                        error = 403; // Execution Error - Types Incompatible
                        return;        
                    }
                    tuple.setInt(schemaPtr.getPos(tmp_field), Integer.parseInt(tmp_value));
                }
            } catch (Exception e)
            { 
                error = 953; // Internal Error - Disk Library - Tuple Set String / Schema GetPos
                return;
            }
        }
    
        tablePtr = schemaMgr.getTable(table_name);
        tuples.add(tuple); 
        tablePtr.tableWrite(1, tuples);

        // Set output
        output.append("1 line inserted into table " + table_name + " sucessuful.\t");
    }
    
    private void Execute_SFWO()
    {
        preProcessor preproc = new preProcessor(this.schemaMgr, this.disk);
        
        // Execute the pre-processing
        preproc.SetCommand(this.parsetree);
        if(!preproc.Run())
        {
            error = preproc.GetLastError();
            return;
        }
        
        // now we have a valid parse tree
        LogicalPlan logicplan = new LogicalPlan(this.schemaMgr, this.disk);
        logicplan.SetCommand(this.parsetree);
        if(!logicplan.Generate())
        {
            error = logicplan.GetLastError();
            return;
        }
        //logicplan.logicaltree.printbyLink(logicplan.logicaltree.root);
        /*
        // the we can execute the logical query plan
        /Optmizer optmizer = new Optmizer();
        optmizer.SetSource(this.parsetree);
        optmizer.SetCommand(logicplan.logicaltree);
        if(!optmizer.Optmize())
        {
            error = optmizer.GetLastError();
            return;
        }
        */
        
        // Get the relations
        Vector<String> rels;
        rels = logicplan.logicaltree.GetListAtributesbyLink("<PRODUCT>", "<Rel>", "<Atribute>" );
        
        String final_relation;
                
        // Execute the relation, assuming just two relations at maximum
        if( rels.size() >= 2)
        {
            String rel1, rel2;
            rel1 = rels.firstElement();
            rel2 = rels.lastElement();
            // get the first relation
            // get the second relation
            final_relation = Product(rel1, rel2);
        }
        else  final_relation = rels.firstElement();

       //Get the fields
        Vector<String> fields;
        fields = logicplan.logicaltree.GetListAtributesbyLink("<PROJECTION>", "<Field>", "<Atribute>" );
        
        // execution th projection
        Projection(fields, final_relation);
        
    }
        
    private boolean Projection(Vector<String> fieldNames, String rel)
    {
        LinkedList<Tuple> tuples = new LinkedList<Tuple>();
        Schema schemaPtr;
        Table tablePtr;
        ListIterator it_tuples_rel;
        int num;
        
        schemaPtr = schemaMgr.getSchema(rel);
        tablePtr = schemaMgr.getTable(rel);
        try
        {
            num = tablePtr.tableRead(tuples, 1);
        } catch( Exception ex)
        {
            error = 805; // Execution error - Empty table
            return false;
        }
                
        // if '*' get all fields of the relation
        // Assuming for now just on relation
        ListIterator it_field;
        it_field = fieldNames.listIterator();        
        while (it_field.hasNext()) 
        {
            String tmp_type = (String)it_field.next();
            if(tmp_type.equalsIgnoreCase("*")) 
            {
                fieldNames.clear();
                fieldNames = schemaPtr.getFields();
                break;
            }
        }
                
        it_tuples_rel = tuples.listIterator();
        
        // Header
        it_field = fieldNames.listIterator();        
        while (it_field.hasNext()) 
        {
            String tmp_type = (String)it_field.next();
            System.out.format("%-10s\t", tmp_type);
            //output.append(tmp_type + "\t");
        }
        System.out.format("\n");
        it_field = fieldNames.listIterator();        
        while (it_field.hasNext()) 
        {
            String tmp_type = (String)it_field.next();
            System.out.format("%-10s\t", "---------------");
            //output.append(tmp_type + "\t");
        }
        //output.append("\n");
        System.out.format("\n");
        
        while (it_tuples_rel.hasNext()) 
        {
            Tuple tmp_tuple_rel = (Tuple)it_tuples_rel.next();
            int j;
        
            // Show just selected columns
            String tmp_field;
        
            it_field = fieldNames.listIterator();        
            while (it_field.hasNext()) 
            {
                String tmp_type = (String)it_field.next();
                // If is not in the schema and it has "."
                if(!schemaPtr.isFromSchema(tmp_type) && tmp_type.contains("."))
                {
                    // take out the point
                    tmp_type = tmp_type.substring(tmp_type.indexOf(".")+1);
                }
                if(schemaPtr.getType(tmp_type).equalsIgnoreCase("string"))
                {
                    System.out.format("%-10s\t", tmp_tuple_rel.getString(schemaPtr.getPos(tmp_type)));
//                    output.append(tmp_tuple_rel.getString(schemaPtr.getPos(tmp_type)) + "\t");
                } 
                else
                {
                    System.out.format("%-10s\t", tmp_tuple_rel.getInt(schemaPtr.getPos(tmp_type)));
//                    output.append(tmp_tuple_rel.getInt(schemaPtr.getPos(tmp_type)) + "\t");
                }
            }            
            System.out.format("\n");
            //output.append("\n");
        }
        output.append(num + " line(s) selected\n");
        return true;
    }
    
    private String Product(String rel1, String rel2)
    {
        Vector<String> conc_fieldNames ;  // final field names
        Vector<String> conc_fieldTypes ;  // final types
        Schema schemaPtr;
        Schema tmp_schema1, tmp_schema2;
        LinkedList<Tuple> tuples = new LinkedList<Tuple>();
        Table tablePtr;

        conc_fieldNames = new Vector<String>();
        conc_fieldTypes = new Vector<String>();

        // read the schema relation 1
        tmp_schema1 = schemaMgr.getSchema(rel1);
        if(tmp_schema1 == null)
        {
            error = 813; // internal error - Table not exist
            return "";                
        }

        // read the schema relation 1
        tmp_schema2 = schemaMgr.getSchema(rel2);
        if(tmp_schema2 == null)
        {
            error = 814; // internal error - Table not exist
            return "";                
        }
        
        // Conctation of atributes
        Vector<String> fieldNames1 = tmp_schema1.getFields(); // get fields schema 1
        Vector<String> fieldNames2 = tmp_schema2.getFields(); // get fields schema 2
        Vector<String> types1 = tmp_schema1.getTypes();  // get types schema 1
        Vector<String> types2 = tmp_schema2.getTypes();  // get types schema 2
        
        
        // Genetate just on schema with all atribs from schema 1 and schema 2
        ListIterator it_field1, it_field2;
        ListIterator it_type1, it_type2;
        
        String tmp_field, tmp_type;
        
        it_field1 = fieldNames1.listIterator();  // pointer to schema 1
        it_type1 = types1.listIterator();
        
        // Including attribs rel 1 (schema 1)
        while (it_field1.hasNext()) 
        {
            tmp_field = (String)it_field1.next(); // get field
            tmp_type = (String)it_type1.next();  // get type
            if(tmp_schema2.isFromSchema(tmp_field))  // add name of table if already exists
                tmp_field = rel1 + "." + tmp_field;
            conc_fieldNames.add(tmp_field);
            conc_fieldTypes.add(tmp_type);
        }

        it_field2 = fieldNames2.listIterator(); // pointer to schema 2
        it_type2 = types2.listIterator();

        // Including attribs rel 2
        while (it_field2.hasNext()) 
        {
            tmp_field = (String)it_field2.next();
            tmp_type = (String)it_type2.next();
            if(tmp_schema1.isFromSchema(tmp_field)) // add name of table is already exists in other schema
                tmp_field = rel2 + "." + tmp_field;
            conc_fieldNames.add(tmp_field);
            conc_fieldTypes.add(tmp_type);
        }

        // Create the new schema, the complete one
        try {
            schemaPtr = new Schema(conc_fieldNames, conc_fieldTypes, disk); 
        } catch (Exception e)
        { 
            error = 950; // INternal Error - Disk Library - add schema
            return "";
        }
        
        tmp_rel_control++; // counter of temporary schemas
        String new_tmp_rel = "tmp_rel" + tmp_rel_control;
        schemaMgr.addSchema(new_tmp_rel, schemaPtr); 
        
        tablePtr = schemaMgr.getTable("tmp_rel" + tmp_rel_control);
        // Execute the Product cartesian
        
        // Get the pointer to both relations
        Table tablePtr1 = schemaMgr.getTable(rel1);
        Table tablePtr2 = schemaMgr.getTable(rel2);

        // Create vector to hold the tuples from the relations
        LinkedList<Tuple> tuples1 = new LinkedList<Tuple>();
        LinkedList<Tuple> tuples2 = new LinkedList<Tuple>();

        // Read all tuples from each relation
        tablePtr1.tableRead(tuples1, 1);
        tablePtr2.tableRead(tuples2, 1);
        
        // Read each tuple of relation one and cross with all rows of schema 2
        ListIterator it_tuples_rel1;
        ListIterator it_tuples_rel2;
        
        it_tuples_rel1 = tuples1.listIterator();
        
        while (it_tuples_rel1.hasNext()) 
        {
            // Get the tuple of relation 1
            Tuple tmp_tuple_rel1 = (Tuple)it_tuples_rel1.next();

            // Cros with al rows of relarion 2
            it_tuples_rel2 = tuples2.listIterator();
            while (it_tuples_rel2.hasNext()) 
            {
                int cti = 0, cts = 0;  // global counter to the final rel
            
                // New tuple with all attributes            
                Tuple new_tuple = new Tuple(schemaPtr); // schemaptr has all atribs from both relations
        
                // transfer all string fields from rel 1 to the final rel
                int cs = 0;
                while (cs < tmp_tuple_rel1.getSizeString()) 
                {
                    String tmp = tmp_tuple_rel1.getString(cs);
                    new_tuple.setString(cts, tmp);
                    cs++; cts++;
                }

                // transfer all int fields from rel 1 to the final rel
                int ci = 0;
                while (ci < tmp_tuple_rel1.getSizeInt()) 
                {
                    int tmp = tmp_tuple_rel1.getInt(ci);
                    new_tuple.setInt(cti, tmp);
                    ci++; cti++;
                }
                
                // 
                // 
                //
                // do the same with the fields in the rel 2
                Tuple tmp_tuple_rel2 = (Tuple)it_tuples_rel2.next();

                // transfer all string fields from rel 2 to the final rel
                cs = 0;
                while (cs < tmp_tuple_rel2.getSizeString()) 
                {
                    String tmp = tmp_tuple_rel2.getString(cs);
                    new_tuple.setString(cts, tmp);
                    cs++; cts++;
                }

                ci = 0;
                while (ci < tmp_tuple_rel2.getSizeInt()) 
                {
                    int tmp = tmp_tuple_rel2.getInt(ci);
                    new_tuple.setInt(cti, tmp);
                    ci++; cti++;
                }
                // add tuple and write to the table
                tuples.add(new_tuple); 
            }
        }
        tablePtr.tableWrite(1, tuples);

        return ("tmp_rel" + tmp_rel_control);
    }
    
    
}
