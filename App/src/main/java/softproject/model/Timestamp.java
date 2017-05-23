package softproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Created by Pontus on 2017-05-17.
 */
public class Timestamp implements Serializable{
    ZonedDateTime estimated;
    ZonedDateTime target;
    ZonedDateTime actual;
    ZonedDateTime recommended;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getEstimated() {
        return estimated;
    }

    public void setEstimated(ZonedDateTime estimated) {
        this.estimated = estimated;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getTarget() {
        return target;
    }

    public void setTarget(ZonedDateTime target) {
        this.target = target;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getActual() {
        return actual;
    }

    public void setActual(ZonedDateTime actual) {
        this.actual = actual;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getRecommended() {
        return recommended;
    }

    public void setRecommended(ZonedDateTime recommended) {
        this.recommended = recommended;
    }
}
