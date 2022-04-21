package dsg.learning.gpstrajectoriessse.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "latitude", "longitude", "altitude"})
@Getter
@Setter
public class Trajectory {
    
    private String latitude;
    private String longitude;
    private String altitude;

    @JsonCreator
    public Trajectory(@JsonProperty(value = "latitude",required = true) String latitude, 
        @JsonProperty(value = "longitude",required = true) String longitude,
        @JsonProperty(value = "altitude",required = true) String altitude) {

        if (!StringUtils.hasText(latitude) || !StringUtils.hasText(longitude) || !StringUtils.hasText(altitude)) {
            throw new IllegalArgumentException("string cannot be null or empty");
        }

        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    @Override
    public boolean equals(Object trajectory) {
        Trajectory other = (Trajectory) trajectory;
        return this.latitude.equals(other.latitude) &&
            this.longitude.equals(other.longitude) &&
            this.altitude.equals(other.altitude);
    }
}
