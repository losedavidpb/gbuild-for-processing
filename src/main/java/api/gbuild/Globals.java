package api.gbuild;

/**
 * <p>
 * Public constants and variables for gBuild components
 * </p>
 * 
 * <p>
 * gBuild includes lots of default values for the attributes of
 * UI components, as well as variables that are necessary to
 * implements events on Processing. This class constains all
 * constants and variables that uses gBuild and the developer
 * at Processing language evironment.
 * </p>
 * 
 * <p>
 * Some of the variables and constants of gBuild can be customized,
 * but you shouldn't apply any modifications unless you know what
 * are the consecuencies of your changes
 * </p>
 * 
 * <p>
 * Talking about the use of this class, all variables and constants
 * are static and public, and it is not possible to create a new
 * instance of a Globals class, so object attributes or methods
 * are not allowed.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class Globals {
    private Globals() { }
    
    /**
     * Default value for text size
     */
    public static final int TEXT_SIZE = 11;
    
    /**
     * Space between each option of a menu
     */
    public static final int MENU_SPACE = 50;
            
    /**
     * Specifies if a new dialog was created and it's
     * visible for user. This variables offers a way
     * to manage modal option for dialogs
     */
    public static boolean newDialog = false;
}