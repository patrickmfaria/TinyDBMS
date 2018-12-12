package qprocessor;

import Disk.Disk;
import Disk.SchemaMgr;

public class LogicalPlan extends BaseExecution
{
    
    public ParseTree logicaltree;
    
    LogicalPlan(SchemaMgr schemaMgr, Disk disk) 
    {
        super(schemaMgr,disk);
    }

    boolean Generate() 
    {
        logicaltree = new ParseTree();
        Node tmp1_node;
        Node tmp2_node;
        
        // Insert Projection
        tmp1_node = logicaltree.InsertNode("", "<PROJECTION>", 0);
        
        // Get sel list and assign to projection
        tmp2_node = parsetree.SearchNode(this.parsetree.root, "<SelList>");
        tmp1_node.leafs = tmp2_node.leafs;
        
        // Insert Selection
        tmp1_node = new Node();
        tmp1_node.name = "<SELECTION>";
        tmp1_node.level = 0;
        
        Node tmp_selection = tmp1_node;  // hold pointer to selection

        tmp2_node = parsetree.SearchNode(this.parsetree.root, "<ConditionList>");
        tmp1_node.leafs = tmp2_node.leafs;
        
        // links projection with selection
        logicaltree.root.link = tmp1_node;
                
        // Insert Relations
        tmp2_node = parsetree.SearchNode(this.parsetree.root, "<FromList>");
        Node top_rel_node = null, future_link = null;
        int num_rels = 1;
        
        while(num_rels <= tmp2_node.leafs.size() )
        {
            // Open the product
            
            top_rel_node = new Node();
            top_rel_node.name = "<PRODUCT>";
            top_rel_node.level = 0;
            top_rel_node.link = null;
            
            if(future_link != null)
                top_rel_node.link = future_link;
            
            // open the relation
            tmp1_node = parsetree.SearchNode(this.parsetree.root, "<Rel>"+num_rels);            
            top_rel_node.leafs.add(tmp1_node);
            
            if( num_rels == 1 && tmp2_node.leafs.size() > 1)
            {
                num_rels++;
                tmp1_node = parsetree.SearchNode(this.parsetree.root, "<Rel>"+num_rels);
                top_rel_node.leafs.add(tmp1_node);
            }

            num_rels++;
            
            future_link = top_rel_node;
        }

        // links betwenn selection and relations
         tmp_selection.link = future_link;

        return true;
    }

}
