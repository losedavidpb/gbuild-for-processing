package gbuild.event;

/**
 * <p>
 * Definition of an Event
 * </p>
 * 
 * <p>
 * Processing includes features to manage I/O interactions.
 * Taking this into account, graphical components could offer
 * an easy way to define visual effects that are associated
 * to such I/O events. To get this, gBuild incorporates this
 * interface which can be used to implement I/O interaction.
 * </p>
 * 
 * <p>
 * This class was designed in order to provide to developers
 * the basic structure that an event must has. However, gBuild
 * has implemented concrete events associated to complex cases.
 * </p>
 * 
 * <p>
 * Relative to concrete events, it is recommended to not use them
 * unless you know what are its functionality and how you can
 * include them. In other words, developers shall define on the
 * great majority of the cases new graphical events.
 * </p>
 * 
 * <p>
 * Talking about the implementation, an event must check all the
 * necessary conditions that have to be true in order to begin
 * the execution. This means that developers are responsables to
 * define correctly such restrictions.
 * </p>
 * 
 * <p>
 * Relative to event's conditions, this interface was not designed
 * to include Processing functions such as mousePressed or keyReleased.
 * So, if an event needs to call one of these functions, developers
 * must call this event at such function.
 * </p>
 * 
 * @author David Parreño Barbuzano
 * @since  4.1.0
 */
public interface GEvent {
    /**
     * Execute an event based on a condition
     * 
     * <p>
     * It is important to notice that the execution
     * would not be truly called whether conditions
     * associated to this event are not true
     * </p>
     */
    public void execute();
}