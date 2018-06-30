package com.novamaday.novalib.api.bukkit.exp;

import org.bukkit.entity.Player;

import java.util.UUID;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ExpTimerData {
    private final UUID id;

    private final int originalLevel;
    private final float originalExp;

    private final int lengthInSeconds;

    private int currentTimeInSeconds;

    private ExpTimerUnit unit;

    /**
     * Creates a new ExpTimerData object for the player.
     *
     * @param p                The player to create it for.
     * @param _lengthInSeconds The length of times in seconds for the timer.
     */
    public ExpTimerData(Player p, int _lengthInSeconds) {
        id = p.getUniqueId();
        originalLevel = p.getLevel();
        originalExp = p.getExp();
        lengthInSeconds = _lengthInSeconds;

        currentTimeInSeconds = 0;

        if (lengthInSeconds > 60)
            unit = ExpTimerUnit.MINUTES;
        else
            unit = ExpTimerUnit.SECONDS;
    }

    //Getters

    /**
     * Gets the UUID of the player the data belongs to.
     *
     * @return The UUID of the player the data belongs to.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the player's original EXP level
     *
     * @return the player's original EXP level.
     */
    public int getOriginalLevel() {
        return originalLevel;
    }

    /**
     * Gets the player's original EXP.
     *
     * @return the player's original EXP.
     */
    public float getOriginalExp() {
        return originalExp;
    }

    /**
     * Gets the length in seconds for the timer.
     *
     * @return The length in seconds for the timer.
     */
    public int getLengthInSeconds() {
        return lengthInSeconds;
    }

    /**
     * Gets the current time in seconds for the timer.
     *
     * @return the current time in seconds for the timer.
     */
    public int getCurrentTimeInSeconds() {
        return currentTimeInSeconds;
    }

    /**
     * Gets the Time Unit for the timer.
     *
     * @return the Time Unit for the timer.
     */
    public ExpTimerUnit getUnit() {
        return unit;
    }

    //Setters

    /**
     * Sets the current time in seconds.
     *
     * @param _currentTime The current time in seconds.
     */
    public void setCurrentTimeInSeconds(int _currentTime) {
        currentTimeInSeconds = _currentTime;
    }

    /**
     * Sets the ExpTimerUnit to use.
     *
     * @param _unit The ExpTimerUnit to use.
     */
    public void setUnit(ExpTimerUnit _unit) {
        unit = _unit;
    }
}