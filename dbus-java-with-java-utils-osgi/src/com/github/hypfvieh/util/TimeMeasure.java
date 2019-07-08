package com.github.hypfvieh.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utility class for time measurements.
 * Instances may be reset for reuse.
 */
public class TimeMeasure {

    public static interface ITimeMeasureFormat {
        public String format(long _durationInMillis);
    }

    /** Start time in milliseconds. */
    private volatile long startTm;

    /** Formatter used for {@link #toString()} */
    private final ITimeMeasureFormat tmf;

    /**
     * Create a new instance using _ts millis as
     * @param _formatter formatter to use for toString() call
     */
    public TimeMeasure(ITimeMeasureFormat _formatter) {
        tmf = _formatter;
        reset();
    }

    /**
     * Create a new instance, used a formatter converting everything &gt;= 5000 ms to seconds (X.Y -&gt; 6.1).
     */
    public TimeMeasure() {
        this(new ITimeMeasureFormat() {
            @Override
            public String format(long _durationInMillis) {
                return _durationInMillis >= 5000 ? ((long) ((_durationInMillis / 1000d) * 10) / 10d) + "s" : _durationInMillis + "ms";
            }
        });
    }

    /**
     * Resets the start time to current time in milliseconds.
     * @return the object
     */
    public final TimeMeasure reset() {
        this.startTm = System.currentTimeMillis();
        return this;
    }

    /**
     * Returns the start time in milliseconds.
     * @return start time in ms
     */
    public long getStartTime() {
        return startTm;
    }

    /**
     * Returns the elapsed time in milliseconds.
     * @return elapsed time in ms
     */
    public long getElapsed() {
        return System.currentTimeMillis() - startTm;
    }

    /**
     * Formats the elapsed time using the given dateFormatter.
     * If null is given, a new Formatter with format HH:mm:ss.SSS will be used.
     *
     * The timezone of the given dateFormatter will always be set to 'UTC' to avoid any timezone related offsets.
     *
     * @param _dateFormat date format
     * @return formatted string
     */
    public String getElapsedFormatted(DateFormat _dateFormat) {
        return getElapsedFormatted(_dateFormat, getElapsed());
    }

    /**
     * Same as above, used for proper unit testing.
     * @param _dateFormat
     * @param _elapsedTime
     * @return formatted string
     */
    String getElapsedFormatted(DateFormat _dateFormat, long _elapsedTime) {
        Date elapsedTime = new Date(_elapsedTime);

        DateFormat sdf = _dateFormat;
        if (_dateFormat == null) {
            sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        }
        sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // always use UTC so we get the correct time without timezone offset on it

        return sdf.format(elapsedTime);
    }

    /**
     * Change the start time (for unit testing only!).
     * @param _tm
     */
    void setStartTm(long _tm) {
        startTm = _tm;
    }

    public long getElapsedAndReset() {
        long elapsed = getElapsed();
        reset();
        return elapsed;
    }

    /**
     * Returns the elapsed time in milliseconds formatted as string.
     * @return elapsed time in ms
     */
    @Override
    public String toString() {
        if (tmf == null) {
            return String.valueOf(getElapsed());
        }
        return tmf.format(getElapsed());
    }

}
