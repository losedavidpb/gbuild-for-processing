package gbuild.slider;

import gbuild.GComponent;
import gbuild.GPanel;
import gbuild.event.ClickAtGEvent;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * <p>
 * Component for sliders
 * </p>
 * 
 * @author David Parreño Barbuzano
 * @since 4.1.0
 */
public abstract class GSlider extends GComponent {
    protected GPanel sliderBar, progressBar;
    private PVector limits;
    private int value;
    
    /**
     * Create a new instance of a slider
     * 
     * @param manager Processing manager
     * @param parent component parent
     */
    public GSlider(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.sliderBar = new GPanel(manager);
        this.sliderBar.setStrokeOpaque(true);
        this.sliderBar.setOpaque(true);
        this.sliderBar.setColor(255, 255, 255);
        this.sliderBar.setStrokeColor(0, 0, 0);
        this.sliderBar.addEvent(new ClickAtGEvent(this.sliderBar));
        
        this.progressBar = new GPanel(manager, this.sliderBar);
        this.progressBar.setStrokeOpaque(false);
        this.progressBar.setOpaque(true);
        this.progressBar.setColor(188, 137, 60);
        
        this.limits = new PVector(0, 10);
        this.value = 0;
    }
    
    /**
     * Create a instance of a slider
     * 
     * @param manager Processing manager
     */
    public GSlider(PApplet manager) {
        this(manager, null);
    }
    
    /**
     * Update progress for this slider
     */
    public abstract void updateProgressBar();
    
    @Override
    public void pos(Integer ... coords) {
        super.pos(coords);
        this.sliderBar.pos(coords);
    }
    
    @Override
    public void pos(Float ... coords) {
        super.pos(coords);
        this.sliderBar.pos(coords);
    }
    
    @Override
    public void dim(Integer ... dimensions) {
        super.dim(dimensions);
        this.sliderBar.dim(dimensions);
    }
    
    /**
     * Get current value for slider
     *
     * @return value value for slider
     */
    public int value() {
        return this.value;
    }
    
    /**
     * Adjust current value for slider
     * 
     * <p>
     * Since sliders has a fixed range, passed value
     * must be greater or equal than minimum value,
     * and less or equal than maximum value
     * </p>
     * 
     * @param value value for slider
     */
    public void setValue(int value) {
        if (!(value >= this.limits.x && value <= this.limits.y)) {
            PApplet.println("error GSlider.setValue: value must be at range");
            System.exit(1);
        }
        
        this.value = value;
    }
    
    /**
     * Return the limits for current slider
     * 
     * @return limits for slider
     */
    public PVector limits() {
        return this.limits;
    }
    
    /**
     * Adjust limits for current slider
     * 
     * <p>
     * Since sliders has a fixed range, passed limits
     * must have two numbers where first number is less
     * than the second one.
     * </p>
     * 
     * @param minValue minimum value for slider
     * @param maxValue maximum value for slider
     */
    public void setLimits(int minValue, int maxValue) {
        if (minValue >= maxValue) {
            PApplet.println("error GSlider.setLimits: min value must be less than max value");
            System.exit(1);
        }
        
        this.limits = new PVector(minValue, maxValue);
    }
}