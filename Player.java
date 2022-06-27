import java.util.Stack;


/**
 * La classe player est la classe type du joueur et des info qui lui sont liées
 *
 * @author Antoine
 * @version 08/05/21
 */
public class Player
{
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private String aNamePlayer;
    private double aWeight;
    private double aWeightMax;
    private ItemList aItemList;
    private Item aPetrol;
    private boolean aAllowToGo;
    
    /**
     * Constructeur d'objets de classe Player
     */
    public Player()
    {
        this.aPreviousRooms = new Stack<Room>();
        this.aCurrentRoom = null;
        this.aNamePlayer = javax.swing.JOptionPane.showInputDialog("I'm your ... What is your name soldier ?");
        /*https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html*/
        this.aItemList = new ItemList();
        this.aWeight = 0;
        this.aWeightMax = 32;
        this.aPetrol = new Item("Petrol" , "Is needed to flight. Be careful: no petrol = crash", 500);
        this.aAllowToGo = false;
    }//Player()

    /**
     * getter de la stack qui contient toutes les rooms
     * @return aPreviousRooms() qui est la liste Room stockée dans la stack
     */
    public Stack getPreviousRooms()
    {
        return this.aPreviousRooms;
    }//getPreviousRooms()
    /**
     * getter de la current room 
     * @return aCurrentRoom qui est le lieu courant
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    }//getCurrentRoom()
    /**
     * est le getter de ItemList
     * @return aItemList
     */
    public ItemList getItemList()
    {
        return this.aItemList;
    }//getItemList()
    /**
     * getter de aWeight
     * @return aWeight, soit le poids
     */
    public double getWeight(){return this.aWeight;}//getWeigth()
    /**
     * getter de aWeightmax
     * @return aWeight, soit le poids max
     */
    public double getWeightMax(){return this.aWeightMax;}//getWeigthMax()
    /**
     * getter de aPetrol
     * @return aPetrol, soit le poids
     */
    public double getPetrolWeight(){return this.aPetrol.getItemWeight();}//getPetrol()
    /**
     * permet de savoir si le player est autoriqser a atteindre une certaine room
     * @return aAllowToGo un boolean qui def l'etat v/f
     */
    public boolean getAllowToGo()
    {
        return this.aAllowToGo;
    }//getAllowToGo()

    /**
     * Modificateur qui prend en compte la 1ere piece pourle depart du joueur.
     * @param pRoomStart qui est la room de depart, le param est def dans Game Engine dans creatRooms qui set la 1ere room
     */
    public void setCurrentRoom(final Room pRoomStart)
    {
        this.aCurrentRoom = pRoomStart;
    }//setCurrentRoom(.)
    /**
     * set le poid
     * @param pWeight est le poid actuel
     */
    public void setWeight(final double pWeight)     //prendre en compte que le pWeight doit etre sous forme de += dans take et -= dans drop
    {
        this.aWeight = pWeight;
    }//setWeight(.)
    /**
     * set le poid
     * @param pWeightMax est le poid actuel
     */
    public void setWeightMax(final double pWeightMax)
    {
        this.aWeightMax = pWeightMax;
    }//setWeightMAx(.)
    /**
     * set le Petrol restant
     * @param pPetrol est le poid actuel du petrol
     */
    public void setPetrol(final double pPetrol)     //prendre en compte que le pWeight doit etre sous forme de += dans take et -= dans drop
    {
        this.aPetrol.setWeight(pPetrol);
    }//setWeight(.)
    /**
     * Permet de savoir si un player a le droit d'acceder a une room.
     * (traite le cas du mdp)
     * @param pBoolean set AllowTo...
     */
    public void setAllowToGo(final boolean pBoolean)
    {
        this.aAllowToGo = pBoolean;
    }//setAllowToGo(.)
    
    /**
     * take permet de prendre un item dans une piece et de le garder su soi
     * @param pNomItem est l'item a prendre
     */
    public void takeItem(final String pNomItem)
    {
        this.aItemList.addItemList(this.aCurrentRoom.getItemList().getItem(pNomItem));
        
        this.aCurrentRoom.removeItem(pNomItem);
    }//takeItem(.)
    /**
     * drop permet de lacher un item dans une piece.
     * @param pNomItem est le nom de l'item a lacher
     */
    public void dropItem(final String pNomItem)
    {
        this.aCurrentRoom.addItem(this.aItemList.getItem(pNomItem));
        this.aItemList.removeItemList(pNomItem);
        if(this.aItemList.getItem(pNomItem) != null){this.aWeight -= this.aItemList.getItem(pNomItem).getItemWeight();}
    }//takeItem(.)
    /**
     * movRoom de Player est une extention de goRoom de GameEngine
     * permet de definir la position du joueur--Il y a une dualité avec goRoom de gameEngine
     * @param pRoom est la prochaine piece du joueur
     */
    public void movRoom(final Room pRoom)
    {
        this.aPreviousRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = pRoom;
        /*ci dessus extrait de goRoom de GE*/
    }//movRoom(.)
    /**
     * fait un retour du player dans sa piece precedente
     */
    public void lastRoom()
    {
        this.aCurrentRoom = this.aPreviousRooms.pop();
    }//lastRoom()
}//Player
