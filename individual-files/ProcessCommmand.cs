using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;
  
class ProcessCommand {
    public ProcessCommand(){
        Console.WriteLine("Enter OS command: ");
        string userCommand = Console.ReadLine();
        Console.WriteLine(userCommand);
        if (userCommand.Length != 0)
            try
            {
                Process.Start(userCommand);
            }
            catch (System.ComponentModel.Win32Exception)
            {
                Console.WriteLine("Error opening file");
            }
    }
    static void Main(string[] args){
        ProcessCommand PC = new ProcessCommand();
    }
}
