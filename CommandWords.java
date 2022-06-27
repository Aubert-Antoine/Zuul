/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + A.Aubert 
 * @version 2008.03.30 + 2019.09.25 + 08/05/21
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private final String[] aValidCommands;

    /**
     * Constructor - initialise the command words.
     * cree un tableau de string avec "go", "help", "quit","look","eat","back" et pour les autres commande word
     */
    public CommandWords()
    {
        this.aValidCommands = new String[] {"go","back","help","look","eat","take","drop","items","use","test","quit", "alea"};  
    }//CommandWords()

    /**
     * return toutes les cmd valides 
     * est aussi apl dans Parser avec showCommands()
     * @return vStr return toutes les cmd valides 
     */
    public String getCommandList()
    {
        String vStr="";
        for( String vCommand : this.aValidCommands){
            vStr += vCommand + " ";
        }// for
        return vStr;  
    }//getCommandList()

    /**
     * Check whether a given String is a valid command word. 
     * @param pString est une string 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        for ( int i=0; i<this.aValidCommands.length; i++ ) {
            if ( this.aValidCommands[i].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands
        return false;
    } // isCommand(.)
}//CommandWords