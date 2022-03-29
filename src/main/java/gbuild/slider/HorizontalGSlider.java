package gbuild.slider;

import gbuild.GComponent;
import gbuild.event.ClickAtGEvent;
import processing.core.PApplet;

import static processing.core.PApplet.abs;

/**
 * <p>
 * Component for horizontal sliders
 * </p>
 * 
 * @author David Parreño Barbuzano
 * @since 4.1.0
 */
public class HorizontalGSlider extends GSlider {

    /**
     * Create a new instance of a horizontal slider
     * 
     * @param manager Processing manager
     * @param parent component parent
     */
    public HorizontalGSlider(PApplet manager, GComponent parent) {
        super(manager, parent);
    }
    
    /**
     * Create a new instance of a horizontal slider
     * 
     * @param manager Processing manager
     */
    public HorizontalGSlider(PApplet manager) {
        this(manager, null);
    }
    
    @Override
    public void dim(Integer ... dimensions) {
        super.dim(dimensions);
        super.progressBar.dim(null, (int)super.sliderBar.dim().y);
    }
    
    @Override
    public void setValue(int value) {
        super.setValue(value);
        this.progressBar.dim((int)PApplet.map(
            this.value(), this.limits().x, this.limits().y,
            0, super.sliderBar.dim().x
        ));
    }
    
    @Override
    public void setLimits(int minValue, int maxValue) {
        super.setLimits(minValue, maxValue);
        super.setValue(maxValue - minValue);
        this.dim(maxValue - minValue);
        super.sliderBar.dim(maxValue - minValue);
    }
    
    @Override
    public void draw() {
        if (isVisible()) {
            this.listenEvents();
            this.sliderBar.draw();
            this.progressBar.draw();
            this.updateProgressBar();
        }
    }

    @Override
    public void updateProgressBar() {
        ClickAtGEvent event = (ClickAtGEvent)super.sliderBar.getEvent(0);
                
        if (event.isClicked()) {
            this.progressBar.dim((int)abs(this.progressBar.pos().x - manager().mouseX));
            
            if (manager().mouseX <= this.sliderBar.pos().x)
                this.progressBar.dim(0);
            
            if (this.progressBar.dim().x > this.sliderBar.dim().x)
                this.progressBar.dim((int)this.sliderBar.dim().x);
            
            this.setValue((int)PApplet.map(
                this.progressBar.dim().x, 0, this.sliderBar.dim().x,
                this.limits().x, this.limits().y
            ));
        }
    }
}