package qprocessor;

import java.util.*;

public class ParseTree
{
    Node root;
    
    Node SearchNode(Node node, String target)
    {
        ListIterator it; 
        Node tmp;
        
        if(node.name.compareToIgnoreCase(target)==0) return node;
        
        it = node.leafs.listIterator();
        while (it.hasNext()) 
        {
            tmp = SearchNode((Node)it.next(), target);
            if ( tmp != null) return tmp;
        }
        return null;
    }

    boolean SearchNodeLevel(Node node, String target, int level)
    {
        ListIterator it; 

        if(node.name.compareToIgnoreCase(target)==0 && node.level == level) return true;
        
        it = node.leafs.listIterator();
        while (it.hasNext()) 
            if( SearchNodeLevel((Node)it.next(), target, level))
                return true;
        return false;
    }

    Node InsertNode(String target, String newnode, int newtype)
    {
        Node parent_node;
        Node new_node;
        
        // Insert root
        if (target.length() == 0 )
        {
            new_node = new Node();
            new_node.name = newnode;
            new_node.level = newtype;
            new_node.link = null;
            root = new_node;
            return new_node;  
        }
        
        // sub nodes
        parent_node = SearchNode(root,target);
        if(parent_node == null)
        {
            throw new UnsupportedOperationException("Node not found!");
        }

        new_node = new Node();
        new_node.name = newnode;
        new_node.level = newtype;
        new_node.link = null;
        parent_node.leafs.add(new_node);
        return new_node;  
    }
    
    public String GetAtributeValue(String syntatic_categorie, String token)
    {
        Node node, tmp;
        
        // Search
        node = SearchNode(root, syntatic_categorie);
        if (node == null) return "";
                

        // Get the list node
        ListIterator it; 

        // Get the value
        it = node.leafs.listIterator();
        while (it.hasNext())
        {
            tmp = (Node)it.next(); // Atribute level
            if(tmp.name.compareToIgnoreCase(token)==0) 
            {
                tmp = tmp.leafs.get(0); // Always atribut must have one element
                return tmp.name;
            }
        }
        return "";       
    }
    
    public Vector<String> GetListAtributes(String syntatic_categorie, String syntatic_subcategorie, String token)
    {
        Vector<String> result_vector;
        Node node, tmp, sub_categorie;
        
        result_vector = new Vector<String>();
        // Search
        node = SearchNode(root, syntatic_categorie);
        if (node == null) return result_vector;

        // Get the list node
        ListIterator it1; 

        // Get the value
        it1 = node.leafs.listIterator();
        while (it1.hasNext())
        {
            sub_categorie = (Node)it1.next(); // syntatic subcategorie level
            if(sub_categorie.name.startsWith(syntatic_subcategorie)) 
            {
                ListIterator it2; 
                it2 = sub_categorie.leafs.listIterator();
                while (it2.hasNext())
                {   
                    tmp = (Node)it2.next(); 
                    if( tmp.name.contains(token)) 
                        result_vector.add(tmp.leafs.get(0).name);
                }
            }
        }
        return result_vector;       
    }

    public Vector<String> GetListAtributesbyLink(String syntatic_categorie, String syntatic_subcategorie, String token)
    {
        Vector<String> result_vector;
        Node node, tmp, sub_categorie;
        
        result_vector = new Vector<String>();
        // Search
        node = SearchNodebyLink(root, syntatic_categorie);
        if (node == null) return result_vector;

        // Get the list node
        ListIterator it1; 

        // Get the value
        it1 = node.leafs.listIterator();
        while (it1.hasNext())
        {
            sub_categorie = (Node)it1.next(); // syntatic subcategorie level
            if(sub_categorie.name.startsWith(syntatic_subcategorie)) 
            {
                ListIterator it2; 
                it2 = sub_categorie.leafs.listIterator();
                while (it2.hasNext())
                {   
                    tmp = (Node)it2.next(); 
                    if( tmp.name.contains(token)) 
                        result_vector.add(tmp.leafs.get(0).name);
                }
            }
        }
        return result_vector;       
    }

    void print(Node node)
    {
        ListIterator it; 
        System.out.println(node.name);
        it = node.leafs.listIterator();
        while (it.hasNext()) 
            print((Node)it.next());
    }
            
    void printbyLink(Node node)
    {
        ListIterator it; 
        System.out.println(node.name);
        it = node.leafs.listIterator();
        while (it.hasNext()) 
            printbyLink((Node)it.next());
        if(node.link!=null) printbyLink(node.link);
    }


    Node SearchNodebyLink(Node node, String target)
    {
        ListIterator it; 
        Node tmp;
        
        if(node.name.compareToIgnoreCase(target)==0) return node;
        
        it = node.leafs.listIterator();
        while (it.hasNext()) 
        {
            tmp = SearchNodebyLink((Node)it.next(), target);
            if ( tmp != null) return tmp;
        }
        if(node.link!=null) 
        {
            tmp = SearchNodebyLink(node.link, target);
            if ( tmp != null) return tmp;
        }
        return null;
    }

}
