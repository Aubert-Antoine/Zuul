import java.util.Random;

/**
 * La class 
 * @author Antoine
 * @version 08/05/21
 */
public class RoomRandomizer {
    private Room[] aTabRandomRoom;
    private Random aRandom;

    /**
     * constructeur naturel
     * @param pTabRoom est le tableau complet de toutes les Rooms
     */
    public RoomRandomizer(final Room[] pTabRoom)
    {
        this.aTabRandomRoom = pTabRoom;
        this.aRandom = new Random();
    }//GenerateRandom()

    /**
     * determine au hasard une Room
     * @param pNbMax est al borne positive max.
     * @return une Room aleatoirement, choisis dans le tab
     */
    public Room findRandomRoom(final int pNbMax)
    {
        int vNb = this.aRandom.nextInt(pNbMax);      //borne sup en param
        return this.aTabRandomRoom[vNb];
    }//findRandomRoom(.)
    /**
     * 
     */
    public Room determineRoom(final int pNbRoom)
    {
        return this.aTabRandomRoom[pNbRoom];
    }//determineRoom(.)
}