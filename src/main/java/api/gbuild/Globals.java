package api.gbuild;

import processing.core.PConstants;

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
     * Default vlaue for text color
     */
    public static final GColor TEXT_COLOR = new GColor(0, 0, 0);
    
    /**
     * Default value for text mode
     */
    public static final int TEXT_MODE = PConstants.MODEL;
    
    /**
     * Default value for text align
     */
    public static final int TEXT_ALIGN = PConstants.LEFT;
    
    /**
     * Defaulkt value for panel color
     */
    public static final GColor PANEL_COLOR = new GColor(255, 255, 255);
    
    /**
     * Default value for panel stroke color
     */
    public static final GColor PANEL_STROKE = new GColor(255, 255, 255);
    
    /**
     * Default with for a panel
     */
    public static final int PANEL_DIM_X = 50;
    
    /**
     * Default height for a panel
     */
    public static final int PANEL_DIM_Y = 50;
    
    /**
     * Defalt color for background button
     */
    public static final GColor BUTTON_BACKGROUND = new GColor(255, 255, 255);
            
    /**
     * Default color for raw content of a button
     */
    public static final GColor BUTTON_RAW = new GColor(0, 0, 0);
    
    /**
     * Default color for hover content of a button
     */
    public static final GColor BUTTON_HOVER = new GColor(255, 255, 255);
    
    /**
     * Default color for stroke color of a button
     */
    public static final GColor BUTTON_STROKE = new GColor(255, 255, 255);
    
    /**
     * Space between each option of a menu
     */
    public static final int MENU_SPACE = 50;
    
    /**
     * Space between text stored at a dialog with text
     */
    public static final int SPACE_GDIALOG_TEXT = 20;
            
    /**
     * Specifies if a new dialog was created and it's
     * visible for user. This variables offers a way
     * to manage modal option for dialogs
     */
    public static boolean newDialog = false;
}