package dev.lugami.candy.base;

public interface CancellableCPSHandler extends CandyCPSHandler {

    /**
     * isCancelled - Returns true if a CandyCPSHandler's onClick event was cancelled.
     * @return boolean
     */
    boolean isCancelled();

    /**
     * setCancelled - Sets the 'cancelled' value for every CandyCPSHandler to 'value' (the parameter)
     * @param value The boolean for cancelling an event or not
     */
    void setCancelled(boolean value);

    void reset();

}
