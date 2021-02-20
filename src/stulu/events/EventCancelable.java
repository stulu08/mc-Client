package stulu.events;

public class EventCancelable extends Event{
    private boolean cancelled = false;
    private boolean isCancelled(){
        return cancelled;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }
}
