/**
 * classe Command qui detecte une action clavier et effectue une action jeux
 * @author Aubert Antoine
 * @version 08/05/21
 */
public class Command 
{
    private String aCommandWord;        //commande 1er mot
    private String aSecondWord;         //commande 2eme mot

    /**
     * Constructeur naturel qui attribue les valeurs de aCommandWord et de aSecondWorld
     * @param pCommandWord es le 1er mot de cmd
     * @param pSecondWord est le second mot
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }//Command(..)

    /**
     * getter de CommandWord()
     * @return this.aCommandWord
     */
    public String getCommandWord() {return this.aCommandWord;}   
    /**
     * getter de secondWord()
     * @return this.aSecondWord
     */
    public String getSecondWord() {return this.aSecondWord;}

    /** 
     * fonction booleenne qui atteste la presence d un 2eme mot (SecondWord)
     * @return true/false en fonction de si SecondWord == null
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    }//hasSecondWord()
    /** 
     * fonction booleenne qui atteste la presence d un 1eme mot 
     * @return true/false en fonction de si aCommandWord == null
     */
    public boolean isUnknown()
    {
        if(this.aCommandWord == null){
            return true;
        }else{
            return false;
        }
    }//isUnknown()
} // Command
