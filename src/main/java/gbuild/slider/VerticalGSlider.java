package gbuild.slider;

import gbuild.GComponent;
import gbuild.event.ClickAtGEvent;
import processing.core.PApplet;

import static processing.core.PApplet.abs;

/**
 * <p>
 * Component for vertical sliders
 * </p>
 * 
 * @author David Parreño Barbuzano
 * @since 4.1.0
 */
public class VerticalGSlider extends GSlider {

    /**
     * Create a new instance of a vertical slider
     * 
     * @param manager Processing manager
     * @param parent component parent
     */
    public VerticalGSlider(PApplet manager, GComponent parent) {
        super(manager, parent);
    }
    
    /**
     * Create a new instance of a horizontal slider
     * 
     * @param manager Processing manager
     */
    public VerticalGSlider(PApplet manager) {
        this(manager, null);
    }
    
    @Override
    public void setValue(int value) {
        super.setValue(value);
        this.progressBar.dim(null, (int)PApplet.map(
            this.value(), this.limits().x, this.limits().y,
            0, super.sliderBar.dim().y
        ));
    }
    
    @Override
    public void dim(Integer ... dimensions) {
        super.dim(dimensions);
        super.progressBar.dim((int)super.sliderBar.dim().x);
    }
    
    @Override
    public void setLimits(int minValue, int maxValue) {
        super.setLimits(minValue, maxValue);
        this.dim(null, maxValue - minValue);
        super.sliderBar.dim(null, maxValue - minValue);
        super.setValue(maxValue - minValue);
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
            this.progressBar.dim(null, (int)abs(this.progressBar.pos().y - manager().mouseY));
            PApplet.println(manager().mouseY);
            
            if (manager().mouseY <= this.sliderBar.pos().y)
                this.progressBar.dim(null, 0);
            
            if (this.progressBar.dim().y > this.sliderBar.dim().y)
                this.progressBar.dim(null, (int)this.sliderBar.dim().y);
            
            this.setValue((int)PApplet.map(
                this.progressBar.dim().y, 0, this.sliderBar.dim().y,
                this.limits().x, this.limits().y
            ));
        }
    }
}