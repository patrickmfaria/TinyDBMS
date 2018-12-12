package qprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main 
{
   
    public static void main(String[] args) throws IOException 
    {
       Parser parser = new Parser();
       Executer executer = new Executer(100, 50, 10);
       
       String cmd = "";
       
       System.out.println("QProcessor V1.0");
       System.out.println("Michael Mburu\n");
       System.out.println("Enter a command or Quit to exit");
       
       String[] cmds = new String[12];
       cmds[0] = "CREATE TABLE a (c1 string, c2 int)";
       cmds[1] = "INSERT INTO a (c1, c2) VALUES ('info1', 1)";
       cmds[2] = "INSERT INTO a (c1, c2) VALUES ('info2', 2)";
       cmds[3] = "SELECT * FROM a";
       cmds[4] = "CREATE TABLE b (c3 int, c4 string)";
       cmds[5] = "INSERT INTO b (c3, c4) VALUES (5, 'info3')";
       cmds[6] = "INSERT INTO b (c3, c4) VALUES (6, 'info4')";
       cmds[7] = "SELECT * FROM b";
       cmds[8] = "SELECT a.c1, a.c2, b.c3, b.c4 FROM a,b";
       cmds[9] = "SELECT c1, c2, b.c3, b.c4 FROM a,b";
       cmds[10] = "SELECT c1, a.c2, b.c3, c4 FROM a,b";
       cmds[11] = "QUIT";
       
       while(true)
       {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(">");
            //cmd = br.readLine();
            //cmd = cmd.trim();
            for(int i = 0; i<cmds.length;i++)
            {
                cmd = cmds[i];
                System.out.print(cmds[i]+" \n");
                if(cmd.equalsIgnoreCase("Quit")) 
                {
                    break;
                }
                if(!cmd.equalsIgnoreCase(""))
                {
                    parser.SetCommand(cmd);
                    parser.Execute();

                    if(parser.GetLastError()==0)
                    {
                        executer.SetCommand(parser.GetParsedTree());
                        executer.Run();
                        if(executer.GetLastError()==0)
                        {
                           System.out.println(executer.output);
                        }else
                        {
                            System.err.println("ERROR: " + executer.GetLastError());
                        }
                    } else
                    {
                        System.err.println("ERROR: " + parser.GetLastError());
                    }
                }
            }
        System.exit(0);
    }
       
    }
}

