/**
 * La class beamer herite de la class Item car les teleporteur sont des sortes d'items amelioré.
 *
 * @author (Antoine)
 * @version 08/05/21
 */
public class Beamer extends Item
{
    private boolean aUsable;
    private Room aRoomCharge;

    public Beamer(final String pNom, final String pDescriptionItem, final double pWeight, final boolean pUsable, final Room pRoomCharge)
    {
        super(pNom, pDescriptionItem, pWeight);
        this.aUsable = pUsable;
        this.aRoomCharge = pRoomCharge;
    }//Beamer()

    /**
     * getter de Usable
     * @return aUsable ctd l'etat v ou f de la charge du beamer 
     */
    public boolean getBeamerUsable()
    {
           return this.aUsable;
    }//getBeamerUsable()
    /**
     * getter de Charge
     * @return aRoomCharge ctd la Room ou le beamer a ete chargé et donc la ou il va nous ramener
     */
    public Room getRoomCharge()
    {
           return this.aRoomCharge;
    }//getRoomCharge()
    
            /*SETTER*/
    /**
     * setter du Usable
     * @param pUsable def l'etat de l'utilisation
     */
    public void setUsable(final boolean pUsable)
    {
        this.aUsable = pUsable;
    }//setUsable(.)
    /**
     * setter de Charge
     * @param pRoomCharge def la Room ou l'on peut l'utiliser
     */
    public void setRoomCharge(final Room pRoomCharge)
    {
        this.aRoomCharge = pRoomCharge;
    }//setRoomCharge(.)
}//Beamer
