/**
 * Model pour cree un item; prend en compte nom description et poid
 *
 * @author (Antoine)
 * @version 08/05/21
 */
public class Item
{
    private String aItemNom;
    private String aItemDescription;
    private double aItemWeight;


    /**
     * Constructeur naturel d'objets de classe Item
     * @param pNom nom de l'item
     * @param pDescriptionItem description de l'item
     * @param pWeight poid de l'item
     */
    public Item(final String pNom, final String pDescriptionItem, final double pWeight)
    {
        this.aItemNom = pNom;
        this.aItemDescription = pDescriptionItem;
        this.aItemWeight = pWeight;
    }
   
    /**
     * getters du nom de l'item
     * @return aItemNom qui est le nom de l'item
     */
    public String getItemNom()
    {return this.aItemNom;}
    /**
     * getters du Description de l'item
     * @return aItemDescription qui est la description de l'item
     */
    public String getItemDescription()
    {return this.aItemDescription;}
    /**
     * getters du Weigt de l'item
     * @return aItemWeight qui est le poids de l'item
     */
    public double getItemWeight()
    {return this.aItemWeight;}
    /**
     * getter de la description de l'item
     * @return this.getItemNom() +" : " + this.getItemDescription() + " ( " + this.getItemWeight() + " Kg)."
     */
    public String getItemLongDescription()
    { return this.getItemNom() +" : " + this.getItemDescription() + " ( " + this.getItemWeight() + " Kg).";}
    
    /**
     * setter du weight
     * @param pWeight faire varier le poid
     */
    public void setWeight(final double pWeight)
    { this.aItemWeight = pWeight;}
}//Item
