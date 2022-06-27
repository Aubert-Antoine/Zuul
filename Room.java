import java.util.HashMap; 
import java.util.Set;
/**
 * classe Room qui represente un lieux du jeux
 * @author Aubert Antoine
 * @version 08/05/21
 */

public class Room 
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName; 
    private Item aItem;
    private ItemList aItemList;
    private static final String[] DIRECTIONS = {"north", "south", "east", "west", "up", "down", "cockpit", "reserve" ,"soute", "magical-place", "futur", "A-40", "A-50", "exit"};

    /**
     * Constructeur naturel qui cree un lieux a partir de la chaine "description"
     * @param pDescription une string qui donne une description simple d'un lieux
     * @param pImage donne une valeur de type String a aImage
     */
    public Room(final String pDescription, final String pImage )
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImage;
        this.aItemList = new ItemList();
    }//Room(.)

    /**
     * getter de la description du lieu
     * @return aDescription ctd description du lieu
     */
    public String getDescription() { return this.aDescription; }
    /**
     * Renvoie la piece ateinte si pas de prb, sinon return null
     * @param pDirection est une str avec la direction 
     * @return aExits.get(pDirection)
     */
    public Room getExit(final String pDirection)
    {
        for( String vTabDirection : DIRECTIONS ){
            if(pDirection.equals(vTabDirection)){return this.aExits.get(pDirection);}
        }return this;
    }//getExit(.)
    /**
     * La methode getExitString(), renvoit une str vExitStr qui contient "Exits: " + les diff direction en fonction de leur existance
     * Utilise une hashmap et une for-each pour la parcourir
     * @return vExitStr qui est une string avec toutes les sorties
     */
    public String getExitString()
    {
        String vExitStr = "Exits: ";
        Set<String> vExitKeys = this.aExits.keySet();
        for( String vTabDirection : DIRECTIONS ){
            for ( String vExitKey : vExitKeys ){
                if(vTabDirection.equals(vExitKey)){vExitStr += " " + vExitKey;}
            }//for each
        }
        return vExitStr;
    }//getExitString()
    /**
     * revoit une description detaillé par lieux.
     * @return '"you are " + aDescription + ".\n" + getExitString();'
     */
    public String getLongDescription()
    {
        return "You are " + this.aDescription + ".\n" + this.aItemList.getItemString() + "\n" + this.getExitString();
    }//getLongDescription()
    /**
     * Return a string describing the room's image name
     * @return this.aImageName a string describing the room's image name
     */
    public String getImageName()
    {
        return this.aImageName;
    }//getImageName()
    /**
     * est le getter de aItemList
     * @return aItemList, un attribut de type ItemList
     */
    public ItemList getItemList()
    {
        return this.aItemList;
    }//getItemList()
    /**
     * getItem renvoie le nom de l'item passé en parametre sinon null
     * utilise la hash map pour faire l association entre le param str et l'item Item.
     * @param pStrItem est une str
     * @return this.aItems.get(pStrItem) item associé a la Str de param 
     */
    public Item getItem(final String pStrItem)
    {
        return this.aItemList.getItem(pStrItem);
    }//getItem(.)

    /**
     * Def une sortie pour cette piece 
     * Chaques direction, soit conduit a une autre piece, soit est null
     * @param pDirection est la direction 
     * @param pNeighbor est la piece voisine
     */ 
    public void setExit(final String pDirection, final Room pNeighbor )
    {
        this.aExits.put(pDirection, pNeighbor);
    }//setExits(..)
    /**
     * Permet de set un item pour le lieu
     * @param pItem est un item qui donera sa  valeur a aItem
     */
    public void setItem(final Item pItem)
    {
        this.aItem = pItem;
    }//setItem(.)
    
    /**
     * isExit() permet de savoir sqi il y a une sortie ou pas. permet de cree une trap door
     * @param pRoomExit permet de séavoir si il y a uin eexit a la room indiqué
     * @return true ou false en fonction de si il exiqste une sortie
     */
    public boolean isExit(final Room pRoomExit)
    {
        return this.aExits.containsValue(pRoomExit);
    }//isExit(.)
    /**
     * addItem ajoute les items de ma piece avac une hashmap
     * dualité avec addItem de ItemList (apl mais ne gere plus la liste)
     * //Appel addItem() de ItemList (pas d'apl recurcif ici !!)
     * @param pAddI de type item sera l'item ajouté
     */
    public void addItem(final Item pAddI)         
    {
        if( pAddI != null) this.aItemList.addItemList(pAddI);     /*test si null pourquoi bug sinon ???*/
    }//addItem(.)
    /**
     * removeItem permet d'enlever un item d'une piece via .remove
     * @param pRemoveItem est le parametre qui va etre supppr
     */
    public void removeItem(final String pRemoveItem)
    {
        this.aItemList.removeItemList(pRemoveItem);
    }//removeItem(.)
} // Room
