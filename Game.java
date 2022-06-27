/**
 * classe Game qui est le moteur du jeu
 * @author Aubert Antoine
 * @version 08/05/21
 */
public class Game

{
    private GameEngine aEngine;
    private UserInterface aGui;

    /**
     * contructeur par defaut qui apl la methode "creatroom"
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }//Game()

    public static void main(String[] args) {
    
    }
} // Game
