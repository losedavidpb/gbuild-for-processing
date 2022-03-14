package api.gbuild;

/**
 * Public constants for gBuild
 * 
 * @author David
 */
public class Globals {
    // DO NOT MODIFY ANY OF THESE VALUES UNLESS YOU
    // KNOW EXACTLY WHAT THEY MEAN AND WHAT ARE THE
    // CONSECUENCIES THAT EXISTS WHEN THEY'RE CHANGED
    
    private Globals() { }
    
    /**
     * Path for the folder which contains all the data that
     * gBuild uses when media will be managed
     * 
     * All files should be stored inside this file path
     */
    public static final String DATA_PATH = "data\\";
    
    /**
     * Path for the folder which contains all the images
     * used when gBuild is been executed
     */
    public static final String IMAGE_PATH = DATA_PATH + "images\\";
    
    /**
     * Path for the folder which contains all the sounds
     * used when gBuild is been executed
     */
    public static final String SOUND_PATH = DATA_PATH + "sounds\\";
    
    /**
     * Path for the folder which contains all the images
     * associated to graphical buttons
     */
    public static final String BUTTON_PATH = IMAGE_PATH + "buttons\\";
    
    /**
     * Path for the the default image of a close button
     * which is been hovering by the user
     */
    public static final String CLOSE_ACTIVE_BUTTON = BUTTON_PATH + "close_active_button.jpg";
    
    /**
     * Path for the the default image of a close button
     * which is not been hovering by the user
     */
    public static final String CLOSE_NO_ACTIVE_BUTTON = BUTTON_PATH + "close_no_active_button.jpg";
}