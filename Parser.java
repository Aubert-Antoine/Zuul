import java.util.StringTokenizer;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau + Aubert Antoine
 * @version 2008.03.30 + 2013.09.15 + 08/05/21
 */
public class Parser 
{

    private CommandWords aCommandWords;  // holds all valid command words

    /**
     * Create a new Parser.
     */
    public Parser() 
    {
        this.aCommandWords = new CommandWords();
    } // Parser()

    /**
     * Get a new command from the user. The command is read by
     * parsing the 'inputLine'.
     * @param pInputLine V02.000-06.04.21
     * @return new Command( vWord1, vWord2 ) ou new Command( null, vWord2 )
     */
    public Command getCommand( final String pInputLine ) 
    {
        String vWord1;
        String vWord2;
        StringTokenizer tokenizer = new StringTokenizer( pInputLine );
        if ( tokenizer.hasMoreTokens() ){vWord1 = tokenizer.nextToken();}     // get first word
        else{vWord1 = null;}
        if ( tokenizer.hasMoreTokens() ){vWord2 = tokenizer.nextToken(); }     // get second word
        else{vWord2 = null;}
        // note: we just ignore the rest of the input line.
        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if ( this.aCommandWords.isCommand( vWord1 ) ){return new Command( vWord1, vWord2 );}
        else{return new Command( null, vWord2 );}
    } // getCommand(.)
    /**
     * Returns a String with valid command words.
     * @return this.aCommandWords.getCommandList() a String with valid command words
     */
    public String getCommandString() // was showCommands()
    {return this.aCommandWords.getCommandList();}//getCommandString()
}//Parser
