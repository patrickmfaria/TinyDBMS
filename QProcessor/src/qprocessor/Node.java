package qprocessor;

import java.util.*;

public class Node 
{
    public String name;
    public int level;  // Used only to parse tree
    public List<Node> leafs;
    public Node link;  // Used only to logical plans
    
    public Node()
    {
        leafs = new LinkedList<Node>() {};
    }
}
