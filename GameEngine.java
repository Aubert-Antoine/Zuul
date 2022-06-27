/*extentions pour lecture ficher text*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * classe Game qui est le moteur du jeu
 * @author Aubert Antoine
 * @version 08/05/21
 */

public class GameEngine
{
    private Parser aParser;
    private UserInterface aGui;
    private Room[] aTabRoom;
    private Player aPlayer;


    /**
     * contructeur par defaut qui apl la methode "creatroom"
     */
    public GameEngine()
    {
        this.aPlayer = new Player();
        this.createRooms();
        this.aParser = new Parser();
    }//Game()
    /**
     * modificateur de aGui
     * @param pUserInterface est un userinterface
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }//setGui(.)

    /**
     * procedure qui permet de cree une room lorsquel est apl par le constructeur.
     * initialise et asssocie une description a chaques room.
     * hashmap pour avoir accès aux room depuis n'importe quelle méthode ou classe.
     * positionne les sorties 
     *  prb A20 2 down... Par rapport au plan... Voir doc pour exp
     *  prb teleportation
     *  declare et associe les items
     */

    private void createRooms()
    {
        //initialisation
        Room vAirport = new Room("on the tarmac at the airport, next to your plane.", "imgAirp");
        Room vA10 = new Room("in A10","imgA10");
        Room vA11 = new Room("in A11","imgA11");
        Room vA12 = new Room("in vA12","imgA12");
        Room vA20 = new Room("in A20","imgA20");
        Room vA30 = new Room("in A30","imgA30");
        Room vA31 = new Room("in A31","imgA31");
        Room vCrash = new Room("Crash... It's the end"+"\n"+"tap 'quit'","imgCrash");
        Room vA40 = new Room("in A40","imgA40");
        Room vA50 = new Room("in A50","imgA50");
        Room vEnd = new Room("End...well done","imgAEnd");
        Room vCockpit = new Room("in the cockpit","imgCockpit");
        Room vReserve = new Room("in the reserve","imgReserve");
        Room vSoute = new Room("in the soute","imgSoute");
        Room vMagicalplace = new Room("in a magical place","imgMagicalplace");

        this.aTabRoom = new Room[]{vA30, vA20, vA31, vA40, vA50, vCrash, vAirport, vA10, vA11, vA12, vEnd, vCockpit, vReserve, vSoute, vMagicalplace};
        TransporterRoom vA41 = new TransporterRoom("in A41","imgA41", this.aTabRoom);

        //position des sorties
        vAirport.setExit("up", vA10);
        vA10.setExit("east", vA11);
        vA10.setExit("up", vA20);
        vA12.setExit("up", vA20);
        vA20.setExit("down", vA30);
        vA20.setExit("west", vA40);
        vA20.setExit("cockpit", vCockpit);
        vA30.setExit("down", vA31);
        vA30.setExit("up", vA20);
        vA30.setExit("cockpit", vCockpit);
        vA31.setExit("futur", vA20);
        vA31.setExit("down", vCrash);
        vA40.setExit("cockpit", vCockpit);
        vA40.setExit("south", vA41);
        vA40.setExit("up", vA30);
        vA40.setExit("east", vA20);
        vA41.setExit("down", vA50);
        vA50.setExit("cockpit", vCockpit);
        vA50.setExit("down", vEnd );
        vEnd.setExit("aeroport", vAirport);
        /*lieux avion*/
        vCockpit.setExit("A-40", vA40);
        vCockpit.setExit("A-50", vA50);
        vCockpit.setExit("reserve", vReserve);
        vCockpit.setExit("soute", vSoute);
        vCockpit.setExit("magical-place",vMagicalplace);
        vReserve.setExit("cockpit", vCockpit);
        vReserve.setExit("soute", vSoute);
        vSoute.setExit("cockpit", vCockpit);
        vSoute.setExit("reserve", vReserve);
        vMagicalplace.setExit("exit", vSoute);
        //declare items
        Item vKey = new Item("Key", "Is required for turn on the aircraft"  , 0.1 );
        Item vConverting_table = new Item("Converting-table" ,"allows to converting cipher \"1337 SP34K\"; " , 2 );
        Item vMap = new Item( "Map","Is needed to goes to A40" , 0.5 );
        Item vTools = new Item("Tools" ,"can help to repare something" , 8.7 );
        Item vPetrol = new Item("Petrol" , "Is needed to flight. Be careful: no petrol = crash", 40); //40 a changer dans limit petrol !!
        Item vDrugs = new Item("Drugs", "can increased your weight max", 0.3);
        Item vBomb = new Item("Bomb" ,"It's the final item to use, to complet your mission", 80 );
        Item vKitbeamer = new Item("Kit-beamer" ,"Is needed to charge the beamer", 3.4 );
        Beamer vBeamer = new Beamer("Beamer", "is something weird that allows you to move",12 , false, null );
        //associe l'item aux lieux
        vAirport.addItem(vKey);
        vA11.addItem(vConverting_table);
        vA30.addItem(vMap);
        vA40.addItem(vTools);
        vA40.addItem(vPetrol);
        vMagicalplace.addItem(vDrugs);
        vA50.addItem(vBomb);
        vA20.addItem(vKitbeamer);
        vMagicalplace.addItem(vBeamer);


        this.aPlayer.setCurrentRoom(vAirport);
    }//creatRooms()

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     * @param pCommandLine est un est un eString
     */
    public void interpretCommand( final String pCommandLine ) 
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );
        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "help" ) )
            this.printHelp(vCommand);
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom( vCommand );
        else if ( vCommandWord.equals( "look" ) )
            this.look( vCommand );
        else if ( vCommandWord.equals( "eat" ) )
            this.eat(vCommand);
        else if ( vCommandWord.equals( "back" ) )
            this.back(vCommand);
        else if ( vCommandWord.equals( "test" ) )
            this.test(vCommand);
        else if ( vCommandWord.equals( "quit" ) )
            this.quit(vCommand);
        else if ( vCommandWord.equals( "take" ) )
            this.take(vCommand);
        else if ( vCommandWord.equals( "drop" ) )
            this.drop(vCommand);
        else if ( vCommandWord.equals( "items" ) )
            this.printInventaire(vCommand);
        else if ( vCommandWord.equals( "use" ) )
            this.use(vCommand);
    }//interpretCommand(.)

    /**
     * tableau contenant toutes les pieces
     * @return return this.aTabRoom[pTRoom]
     */
    public Room[] getTabRoom()
    {
        return this.aTabRoom;
    }//getTabRoom(.)

    /**
     * procedure est apl au debut du jeu et affiche un msg des le debut
     */
    private void printWelcome()
    {
        this.aGui.print( "\n" );
        this.aGui.println("Welcome to 'WWII airplane simulator'!");
        this.aGui.println("It is my first game!.");
        this.aGui.println(" ");
        this.aGui.println("made by: Aubert Antoine");
        this.aGui.println(" ");
        this.aGui.println(" ");
        this.aGui.println("You are on board of Enola Gay (b-29),around the end of ww2.");
        this.aGui.println(" ");
        this.aGui.println("Type 'help' if you need help.");
        printLocationInfo();
    }//printWelcome()
    /**
     * printInventaire affiche les items dont dispose le player et le poid auquel il est.
     * est appelé si input = "items"
     * @param pCommand sert a savoir si il y a un second mot
     */
    private void printInventaire(final Command pCommand)
    {
        if(pCommand.hasSecondWord()){this.aGui.println("La cmd item n'a pas de second mot !");}
        else{
            this.aGui.println("Your " + this.aPlayer.getItemList().getItemString());
            this.aGui.println("Your total weight are: "+ this.aPlayer.getWeight() + " / " + this.aPlayer.getWeightMax() );
            this.aGui.println(" ");
        }
    }//inventaire(.)
    /**
     * procedure printHelp qui s execute ssi "help" est tape
     * adapter les phrases a jeu
     * @param pCommand sert a savoir si il y a un second mot
     */
    private void printHelp(final Command pCommand)
    {
        if(pCommand.hasSecondWord()){this.aGui.println("La cmd help n'a pas de second mot !");}
        else{
            this.aGui.println("You must complete your mission, captain.");
            this.aGui.println("You fly in the air.");
            this.aGui.println("Your command words are: "+ this.aParser.getCommandString() );
            this.aGui.println(" ");
        }
    }//printWelcome()
    /**
     * (modif pour interface graph)
     * printLocationInfo()
     * affiche info situation du lieu courant
     * affiche image de la piece
     */
    private void printLocationInfo()
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        if ( this.aPlayer.getCurrentRoom().getImageName() != null ) this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
        this.aGui.println(" ");
    }//printLocationInfo()

    /**
     * procedure donne les info quand on passe dans un autre lieu
     * @param pCommand recup la direction souhaitee 
     */
    private void goRoom(final Command pCommand)
    {
        if ( ! pCommand.hasSecondWord() ) {
            this.aGui.println( "Go where?" );
            return;
        }
        String vDirection = pCommand.getSecondWord();
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit( vDirection );
        if ( vNextRoom == null ){this.aGui.println( "There is no door!" );}
        else if(vNextRoom.equals(this.aPlayer.getCurrentRoom())){this.aGui.println("Unknown direction");}  
        else {
            this.aPlayer.movRoom(vNextRoom);
            this.printLocationInfo();
            this.limitPetrol(pCommand);
        }
    }//goRoom(.)
    /**
     * back est une commande quipermet de revenir dan la piece precedante.
     * verifie si la stack ne est pas vide et fait reculer sauf si on est au pt de depart. Donne aussi la locationinfo
     * @param pBack est u param de type commande 
     */
    private void back(final Command pBack)
    {
        if(!pBack.hasSecondWord())
        {
            if(this.aPlayer.getPreviousRooms().empty())this.aGui.println( "You are at the start !" );
            else{
                if(this.aPlayer.getCurrentRoom().isExit((Room)this.aPlayer.getPreviousRooms().peek())){
                    this.aPlayer.lastRoom();
                    limitPetrol(pBack);
                }else{this.aGui.println( "You can't go back" );}
            }//else
            this.printLocationInfo();       //affiche toujours les infos, meme au debut !
        }//retient 1 ancienne room 
        else{this.aGui.println( "Back doesn't have a second word..." );}
    }//back(.)
    /**
     * look permet de soit donner la description de la piece soit de l itrem si il est connue sinon "unknown item"
     * @param pLook est une command 
     */
    private void look(final Command pLook)
    {
        if(!pLook.hasSecondWord()){this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());}
        else
        {
            Item vItem = this.aPlayer.getCurrentRoom().getItem(pLook.getSecondWord());      //l'item
            if(vItem != null){this.aGui.println(vItem.getItemLongDescription());}
            else{this.aGui.println("Unknown Item");}
            this.aGui.println(" ");
        }
    }//look(.)
    /**
     * take permet de prendre un item a une dualité avec takeitem de Player
     * @param pCommand est la commande saisie
     */
    private void take(final Command pCommand)
    {
        if(pCommand.hasSecondWord()){
            if(this.aPlayer.getCurrentRoom().getItemList().hasItem(pCommand.getSecondWord()))/*si l'item est present dans la currentRoom*/
            {
                double vCurrentWeight = this.aPlayer.getWeight() + this.aPlayer.getCurrentRoom().getItem(pCommand.getSecondWord()).getItemWeight();
                if( vCurrentWeight <= this.aPlayer.getWeightMax()){
                    this.aPlayer.takeItem(pCommand.getSecondWord()); /*appel takeItem qui ajoute a la liste de l'inventaire par joueur*/
                    this.aGui.println( ""+pCommand.getSecondWord() + " has been taken successfully !");
                    this.aPlayer.setWeight(vCurrentWeight);
                }else{this.aGui.println( "The weight is too heavy, drop some items to take it !" );}
            }else{this.aGui.println( "this item is not here !" );}
        }else{this.aGui.println( "take has a second word... Take what ?" );}
    }//take(.)
    /**
     * drop permet de lacher un item; a une dualité avec dropitem de Player
     * @param pCommand est la commande saisie
     */
    private void drop(final Command pCommand)
    {
        if(pCommand.hasSecondWord()){
            if(pCommand.getSecondWord().equals("Key")){this.aGui.println( "Key can't be droped in flight" );}
            if(this.aPlayer.getItemList().hasItem(pCommand.getSecondWord()))/*si l'item est present dans la currentRoom*/{
                double vCurrentWeight = this.aPlayer.getWeight() - this.aPlayer.getItemList().getItem(pCommand.getSecondWord()).getItemWeight();
                this.aPlayer.setWeight(vCurrentWeight);
                this.aPlayer.dropItem(pCommand.getSecondWord());
                this.aGui.println( ""+pCommand.getSecondWord() + " has been dropped successfully !");
            }else{this.aGui.println( "this item is not owned !" );}
        }else{this.aGui.println( "drop has a second word... Drop what ?" );}
    }//take(.)  
    /**
     * eat est une commande qui permet de manger des drogues lol donc d augmenter le poid max du joueur par 2. 
     * @param pCommand est ici "eat Drugs"
     */
    private void eat(final Command pCommand)
    {
        if(pCommand.hasSecondWord()){
            if(this.aPlayer.getItemList().hasItem("Drugs")){
                double vPoidMax2 = this.aPlayer.getWeightMax()*2;
                this.aPlayer.setWeightMax(vPoidMax2);
                this.aGui.println( "Your weight max is increased !" );
                this.aPlayer.getItemList().removeItemList(pCommand.getSecondWord());
            }else{this.aGui.println( "Eat what ? You need to have on you the item to eat it" );}
        }else{this.aGui.println( "Eat has a second word..." );}
    }//eat()
    /**
     * use permet d'utiliser un objet 
     * @param pCommand est l'item avec lequel interagir
     */
    private void use(final Command pCommand)
    {
        if(pCommand.hasSecondWord()){
            if(this.aPlayer.getItemList().hasItem(pCommand.getSecondWord())){
                if(pCommand.getSecondWord().equals("Petrol")){
                    limitPetrol(pCommand);
                    this.aPlayer.getItemList().removeItemList(pCommand.getSecondWord());
                }else if(pCommand.getSecondWord().equals("Converting-table")){
                    if(this.aPlayer.getCurrentRoom().getDescription().equals("in A11")){
                        this.aGui.println("Capitain send this code to radio:");
                        this.aGui.println("zuul");
                        String vOutPut = javax.swing.JOptionPane.showInputDialog("Enter the code");
                        if(vOutPut.equals("zuul")){
                            this.aGui.println( "Code accepted " );
                            this.aPlayer.setAllowToGo(true);
                            this.openRoomExit(this.aTabRoom[8]);
                        }else{
                            this.aPlayer.setAllowToGo(false);
                            this.aGui.println( "Code refused" );
                        }
                    }else{this.aGui.println( "You are not in the good Room" );}
                }else if(pCommand.getSecondWord().equals("Tools")){
                    this.aGui.println( "This fonction comming soon." );
                }else if(pCommand.getSecondWord().equals("Key")){
                    String vAirport = "on the tarmac at the airport, next to your plane.";
                    if(this.aPlayer.getCurrentRoom().getDescription().equals(vAirport)){this.aGui.println( "You turn on there motors" );}
                    else{this.aGui.println( "You can't turn off your motors when you flight" );}
                }else if(pCommand.getSecondWord().equals("Kit-beamer")){
                    if(this.aPlayer.getItemList().hasItem("Beamer")){
                        Beamer vBeamer = (Beamer)this.aPlayer.getItemList().getItem("Beamer");
                        vBeamer.setRoomCharge(this.aPlayer.getCurrentRoom());
                        vBeamer.setUsable(true);
                        this.aPlayer.getItemList().removeItemList("Kit-beamer");
                        this.aGui.println("The item Kit-beamer is remove");
                        this.aGui.println("The Beamer is now charged !");
                        this.aGui.println("If you enter the command 'use Beamer' you will be teleported to the room where you loaded it");
                    }else{this.aGui.println( "For use the Kit-beamer and charge the Beamer you need to carry it" );}
                }else if(pCommand.getSecondWord().equals("Beamer")){
                    Beamer vBeamer = (Beamer)this.aPlayer.getItemList().getItem("Beamer");
                    if(vBeamer.getBeamerUsable()){     /*test si il est chargé*/
                        this.aPlayer.movRoom(vBeamer.getRoomCharge());
                        this.printLocationInfo();
                        vBeamer.setUsable(false);
                    }else{this.aGui.println("For use the Beamer you need to charge it with the Kit-beamer (take both and use  'Kit-beamer')");}
                }
                //}else if(pCommand.getSecondWord().equals("")){
                //}else if(pCommand.getSecondWord().equals("")){
                else{this.aGui.println( "The command use can't be applied to this thing" );}
            }else{this.aGui.println( "This item is not owned !" );}
        }else{this.aGui.println( "Use has a second word..." );}
    }//use(.)
    /**
     * affiche le petrol restant et si il n'y en a plus quitte la partie
     * @param pCommand peut etre "go" - "back" ou "eat Drugs"
     */
    private void limitPetrol(final Command pCommand)
    {
        //on prend le poid actuel
        double vPetrol = this.aPlayer.getPetrolWeight();
        //selon a commande on attibut  un nouveau poid
        if("go".equals(pCommand.getCommandWord()) || "back".equals(pCommand.getCommandWord())){vPetrol -= 20;}
        if("use".equals(pCommand.getCommandWord()) && "Petrol".equals(pCommand.getSecondWord())) {vPetrol += 40;}
        this.aPlayer.setPetrol(vPetrol);
        //on test si le nv poid est positif ctd si il y a encorte du petrol
        if(this.aPlayer.getPetrolWeight() > 0 )
        {this.aGui.println( "Your petrol is " + this.aPlayer.getPetrolWeight() + " / 500" );
        }else{
            this.aGui.println( "End of game, you lose because you don't have petrol anymore" );
            Command vCommand = new Command("quit","");
            quit(vCommand);
        }
    }//limitPetrol(.)
    /**
     * Permet de mofifier les sortie d'une Room
     * Par example si le player ne peut se deplacer dans cette direction qu'a une certaine condition
     * @param pRoom designe la Room a Room a laquel on veut redefinir les sorties.
     */
    private void openRoomExit(final Room pRoom)
    {
        if(this.aPlayer.getAllowToGo()){
            if(this.aPlayer.getCurrentRoom().getDescription().equals("in A11")){
                pRoom.setExit("east", this.aTabRoom[9]);
                this.aPlayer.setCurrentRoom(this.aPlayer.getCurrentRoom());
                this.aGui.println( "You are allow to go: east" );
                this.aPlayer.setAllowToGo(false);
            }
        }
    }//openRoomExit(.)
    /**
     * test est une commande qui represente un nom de fichier et qui execute tt les cmd presente dans le fichier .txt
     * "test need a second word ! for test you need to enter the name of the file .txt"
     * le dossier avec les .txt est \"zuul_vX.X\test\..."
     * @param pTest est de la forme "1er cmd et 2eme cmd" renseigne sur la methode a tester 
     */
    private void test(final Command pTest)
    {
        if(!pTest.hasSecondWord())
        {this.aGui.println("Test needs a second word ! For test you need to enter the name of the file .txt");}
        else
        {
            Scanner vSc;
            try { // pour "essayer" les instructions suivantes
                String vTestPath = "test/" + pTest.getSecondWord() + ".txt";
                vSc = new Scanner( new File( vTestPath ) );
                while ( vSc.hasNextLine() ) {
                    String vLigne = vSc.nextLine();
                    // traitement de la ligne lue
                    this.interpretCommand(vLigne);
                } // while
            } // try
            catch ( final FileNotFoundException pFNFE ) {
                this.aGui.println("The file name is unknown. Take a look to the directory.");
                this.aGui.println("The the location file is \"zuul_vX.X\test\".");
            } // catch
        }//else
    }//test()


    /**
     * methode quit qui s execute ssi "quit" est tape
     * permet de dire qu'on veut quitter le jeu
     * @param pSdMot test si il y a un secondmot et retoune un boolean
     */
    private void quit(final Command pSdMot)             /*si return boolrean on peut interdir les autre dethodes de montionner*/
    {
        if(pSdMot.hasSecondWord()){this.aGui.println("Quit doesn't have a second word !");
        }else{this.endGame();} 
    }//quit(.)
    /**
     * Permet dequitter le jeu
     */
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }//endGame()
} // Game
