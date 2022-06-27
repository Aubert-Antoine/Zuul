import java.util.HashMap;
import java.util.Set;

/**
 * reduit le couplge entre Player et Room - toutes les methodes communes sont regroupées ici. Le reste est conservé.
 * stock les inventaires de chaques player.
 *
 * @author (Antoine)
 * @version 08/05/21
 */
public class ItemList
{
    private HashMap<String, Item> aInventaire;
    private static final String[] ITEMS = {"Map","Key","Converting-table","Tools","Petrol","Bomb", "Drugs", "Beamer", "Kit-beamer"};//rajouter ITEMS si besoin

    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList()
    {
        this.aInventaire = new HashMap<String, Item>();
    }//ItemList()

    /**
     * getItemString() contient hashmap pour afficher les differents item dispo dans chaques room dans un ord precis cf tab.
     * @return vItemStr qui est une string avec toutes les items
     */
    public String getItemString()
    {
        if(this.aInventaire == null){return "No item here.";}
        else{
            String vItemStr = "Items: ";
            Set<String> vItemKeys = this.aInventaire.keySet();
            for( String vTabItem : ITEMS ){
                for ( String vItemKey : vItemKeys ){
                    if(vItemKey.equals(vTabItem)){vItemStr += " " + vItemKey;}
                }//for each
            }//for each
            return vItemStr;
        }
    }//getItemString()
    /**
     * getItem renvoie le nom de l'item passé en param sinon null
     * utilise la hash map pour faire l association entre le param str et l'item Item.
     * @param pStrItem est une str
     * @return this.aMesItems.get(pStrItem) item associé a la Str de param 
     */
    public Item getItem(final String pStrItem)
    {
        return this.aInventaire.get(pStrItem);
    }//getItem(.)
    
    /**
     * addItemList permet d'ajouter un item a la hashmap d'item list.
     * utilise .put de la hashmap aInventaire.
     * @param pAddIList de type String; est la clef de la hashmap aIaInventaire
     */
    public void addItemList(final Item pAddIList)
    {
        if(pAddIList != null )this.aInventaire.put(pAddIList.getItemNom(), pAddIList);
    }//addItemList(.)
    /**
     * removeItemList permet de retirer un item a la hashmap d'item list.
     * utilise .remove de la hashmap aInventaire.
     * @param pRemoveIList de type String; est la clef de la hashmap aIaInventaire
     */
    public void removeItemList(final String pRemoveIList)
    {
        this.aInventaire.remove(pRemoveIList);
    }//addItemList(.)
    /**
     * hasItem test si le Player / room a l'item
     * @param pItem string le nom d un item don t on recherceh la presence
     * @return si l'item est possedé ou present selon l'atribut qui precede
     */
    public boolean hasItem(final String pItem)
    {
        return this.aInventaire.containsKey(pItem);
    }//hasItem(.)
}//ItemList
