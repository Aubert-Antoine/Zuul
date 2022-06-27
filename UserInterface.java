import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.net.URL;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Antoine
 * @version 08/05/21
 * images et 1 bouton ok
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;

    private JButton aButtBACK;
    private JButton aButtQUIT;
    private JButton aButtHELP;
    private JButton aButtUP;
    private JButton aButtDOWN;
    private JButton aButtEAST;
    private JButton aButtWEST;
    private JButton aButtNORTH;
    private JButton aButtSOUTH;
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) isneeded.
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * @param pText du txt
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)
    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText nean
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)
    /**
     * Show an image file in the interface.
     * @param pImageName renseigne sur le path de l'image via son nom
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "Images/edit/" + pImageName+".png";    //mettre url et extention
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     * @param pOnOff nean
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "WWII airplane simulator" );
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();       //panel principal
        
        JPanel vPanel1 = new JPanel();        //droite general
        JPanel vPanel2 = new JPanel();        //gauche general
        
        JPanel vPanel3 = new JPanel();      //top de gauche
        JPanel vPanel4 = new JPanel();      //centre de gauche   
        JPanel vPanel5 = new JPanel();      //bas de gauche
        
        JPanel vPanel6 = new JPanel();      //top de droite
        JPanel vPanel7 = new JPanel();      //centre de droite  inclut autre panel 
        JPanel vPanel8 = new JPanel();      //bas de droite
        
        JPanel vPanel9 = new JPanel();      //top de droite de dedans panel7
        
        this.aImage = new JLabel();

        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add( vPanel1, BorderLayout.EAST );       //cmd droite go ... 
        vPanel.add( vPanel2, BorderLayout.WEST );       //cmd gauche essentiels

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
        // add some event listeners to some components
        this.aEntryField.addActionListener( this );

        // presentes de bases

        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();

        /*etapes creation buttons
        1 declaration avec txt
        faire les panel pourles placer
        leurs mettres des actions lisner pour real les actions dans actionPerformed
         */
        /*EXPLICATION FONCTIONNEMENT DE PANEL...
        Panel est une sorte de plan, decoupâble en 5
        on ^peut mettre des panel dans des panel pour rediviser et faire plusieurs emplacements
        le borderlayout --> cole le bouton au cotes

         */

        /*  CONSTRUCTION TYPE

        //permet la creation du bouton avec le txt
        this.aButtTEST = new JButton("TEST");      
        //permet de placer des plan ou inserer les boutons
        vPanel.add( this.aButtTEST,BorderLayout.EAST );
        // add some event listeners to some components
        this.aButtTEST.addActionListener( this );

        */  

                /*Partie de gauche*/
                
                //orga des panel avec border layout pour la liste
        
        vPanel2.setLayout( new BorderLayout() );       
        vPanel2.add(vPanel3,BorderLayout.NORTH); //top
        vPanel2.add(vPanel4,BorderLayout.CENTER); //centre
        vPanel2.add(vPanel5,BorderLayout.SOUTH); //bas
                
                //liste de panel sans border layout pour ne pas faire toucher les bouton de la liste
        this.aButtBACK = new JButton("BACK");
        vPanel3.add( this.aButtBACK );
        this.aButtBACK.addActionListener( this );
        
        this.aButtHELP = new JButton("HELP");
        vPanel4.add( this.aButtHELP );
        this.aButtHELP.addActionListener( this );
        
        this.aButtQUIT = new JButton("QUIT");
        vPanel5.add( this.aButtQUIT );
        this.aButtQUIT.addActionListener( this );
        
                /*Partie de droite*/   
                
                //orga des panel avec border layout pour la liste
        
        vPanel1.setLayout( new BorderLayout() );       
        vPanel1.add(vPanel6,BorderLayout.NORTH); //top
        vPanel1.add(vPanel7,BorderLayout.CENTER); //centre
        vPanel1.add(vPanel8,BorderLayout.SOUTH);  //bas
        
                //cadre de panel droit
        vPanel6.setLayout( new BorderLayout() ); 
        this.aButtUP = new JButton("UP");
        vPanel6.add( this.aButtUP );
        this.aButtUP.addActionListener( this );
        
        vPanel8.setLayout( new BorderLayout() ); 
        this.aButtDOWN = new JButton("DOWN");
        vPanel8.add( this.aButtDOWN );
        this.aButtDOWN.addActionListener( this );
        
                    //cadre interieur droit        
        vPanel7.setLayout( new BorderLayout() ); 
        
        this.aButtEAST = new JButton("EAST");
        vPanel7.add(this.aButtEAST,BorderLayout.EAST); //top
        this.aButtEAST.addActionListener( this );
        
        vPanel7.add(vPanel9,BorderLayout.CENTER); //centre
        
        this.aButtWEST = new JButton("WEST");
        vPanel7.add(aButtWEST,BorderLayout.WEST);  //bas
        this.aButtWEST.addActionListener( this );
        
                   //dans le panel de panel droit
        
        vPanel9.setLayout( new BorderLayout() ); 
       
        this.aButtNORTH = new JButton("NORTH");
        vPanel9.add( this.aButtNORTH, BorderLayout.NORTH );
        this.aButtNORTH.addActionListener( this );
        
        this.aButtSOUTH = new JButton("SOUTH");
        vPanel9.add( this.aButtSOUTH,  BorderLayout.SOUTH );
        this.aButtSOUTH.addActionListener( this );   
    } // createGUI()
    /**
     * Actionlistener interface for entry textfield.
     * @param pE un action event
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        if(pE.getSource()==this.aEntryField)this.processCommand(); // never suppress this line
        
                /*Partie gauche*/
        if(pE.getSource()==this.aButtBACK){this.aEngine.interpretCommand("back");}       // "" == remplacve la phrase tapper --> "go direction" ou "commandWord"
        if(pE.getSource()==this.aButtHELP){this.aEngine.interpretCommand("help");}
        if(pE.getSource()==this.aButtQUIT){this.aEngine.interpretCommand("quit");}
        
                /*Partie droite*/
        if(pE.getSource()==this.aButtUP){this.aEngine.interpretCommand("go up");}       // "" == remplacve la phrase tapper --> "go direction" ou "commandWord"
        if(pE.getSource()==this.aButtDOWN){this.aEngine.interpretCommand("go down");}
        
                /*Partie interieur (cote G et D) droit*/
        if(pE.getSource()==this.aButtEAST){this.aEngine.interpretCommand("go east");}
        if(pE.getSource()==this.aButtWEST){this.aEngine.interpretCommand("go west");}
        
                 /*Partie interieur dans le panel droit*/
        if(pE.getSource()==this.aButtNORTH){this.aEngine.interpretCommand("go north");}
        if(pE.getSource()==this.aButtSOUTH){this.aEngine.interpretCommand("go south");}
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );
        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
