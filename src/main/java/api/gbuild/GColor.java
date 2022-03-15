package api.gbuild;

/**
 * Implementation for Procesing colors
 * 
 * @author David Parreño Barbuzano
 */
public final class GColor {
    private float[] colorValue;
    
    /**
     * Create a new instance of a color
     * 
     * @param component red, green, and blue component
     */
    public GColor(Float ... component) {
        this.setColor(component);
    }
    
    /**
     * Create a new instance of a color
     * 
     * @param component red, green, and blue component
     */
    public GColor(Integer ... component) {
        this.setColor(component);
    }
    
    /**
     * Get the value for color
     * 
     * @return value for color
     */
    public float[] color() {
        return this.colorValue;
    }
    
    /**
     * Set the value for color
     * 
     * @param component red, green, and blue component
     */
    public void setColor(Float ... component) {
        if (component.length == 0) return;
        
        if (component.length >= 1 && component.length <= 3) {
            this.colorValue = new float[3];
            for (int i = 0; i < colorValue.length; i++) colorValue[i] = 0;
            
            for (int i = 0; i < component.length; i++) {
                if (component[i] != null)
                    colorValue[i] = component[i];
            }
        }
    }
    
    /**
     * Set the value for color
     * 
     * @param component red, green, and blue component
     */
    public void setColor(Integer ... component) {
        if (component.length == 0) return;
        
        if (component.length >= 1 && component.length <= 3) {
            this.colorValue = new float[3];
            for (int i = 0; i < colorValue.length; i++) colorValue[i] = 0;
        
            for (int i = 0; i < component.length; i++) {
                if (component[i] != null)
                    colorValue[i] = component[i];
            }
        }
    }
}
