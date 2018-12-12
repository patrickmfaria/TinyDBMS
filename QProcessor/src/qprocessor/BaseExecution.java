package qprocessor;

import Disk.Disk;
import Disk.SchemaMgr;

public class BaseExecution extends Base
{
    protected ParseTree parsetree;

    protected SchemaMgr schemaMgr;
    protected Disk disk;
    
    public BaseExecution()
    {
        error = 0;            
    }
    public BaseExecution(int arg0, int arg1, int arg2)
    {
        disk = new Disk(arg0, arg1, arg2);
        schemaMgr = new SchemaMgr(disk);
        error = 0;    
    }

    public BaseExecution(SchemaMgr schemaMgr, Disk disk)
    {
        this.schemaMgr = schemaMgr;
        this.disk = disk;
        error = 0;    
    }
    
    public void SetCommand(ParseTree parsetree)
    {
        this.parsetree = parsetree;
    }
}
