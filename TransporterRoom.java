/**
 * La class transporter determine au hasard une room dans laquel le player peut se teleporter
 * Elle extends de Room
 * @author (Antoine)
 * @version 08/05/21
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer aRoomRandomizer;
    private boolean aBooleanAllow;

    /**
     * Constructeur naturel de TransporterRoom;
     * @param pDescription est la description de la piece
     * @param pImage est limage attribué a la piece
     * @param pRooms est le tableau de Room definit dans creatRoom de GE.
     */
    public TransporterRoom(final String pDescription, final String pImage, final Room[] pRooms)     //rajout d'un boolean pour savoir il y a deja eut 1 teleportation ?
    {
        super(pDescription, pImage);
        this.aRoomRandomizer = new RoomRandomizer(pRooms);
        this.aBooleanAllow = true;
    }//TransporterRoom(...)

    /**
     * Getter de aBooleanAllow
     * @return aBooleanAllow un boolean
     */
    public boolean getRoomRandomizerBool()
    {
        return this.aBooleanAllow;
    }

    /**
     * Permet de savoir si on peut se teleporter
     * @param pBoolean est l'etat de boolean
     */
    public void setBooleanAllow(final boolean pBoolean)
    {
        this.aBooleanAllow = pBoolean;
    }//setBooleanAllow(.)

    /**
     * return a random room, independent of th e Direction.
     * @param pDirection 
     * @return findRandomRoom()
     */
    @Override public Room getExit(final String pDirection)
    {
        if(this.aBooleanAllow){
            System.out.println("teleportation");
            this.setBooleanAllow(false);
            System.out.println(aBooleanAllow);
            return this.aRoomRandomizer.findRandomRoom(4);
        }else{
            System.out.println("pas de teleportation");
            System.out.println("on doit aller en A50");
            return this.aRoomRandomizer.determineRoom(4);
        }
    }//getExit(.)
}//TransporterRoom