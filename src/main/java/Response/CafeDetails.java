package Response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

@JsonSerialize
public class CafeDetails {
    private String address;
    @JsonProperty("coming_soon")
    private boolean comingSoon;
    @JsonProperty("community_day_link")
    private boolean communityDayLink;
    private String distance;
    @JsonProperty("google_maps_link")
    private String googleMapsLink;
    @JsonProperty("first_slide")
    private boolean firstSlide;
    private int id;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String name;
    private String region;
    @JsonProperty("sanitized_address")
    private String sanitizedAddress;
    private String slug;
    private String type;
    private String url;
    private WeeklyHours hours;
    private String timezone;

    public CafeDetails(String address, boolean comingSoon, boolean communityDayLink, String distance, String googleMapsLink,
                       boolean firstSlide, int id, String imageUrl, BigDecimal latitude, BigDecimal longitude, String name,
                       String region, String sanitizedAddress, String slug, String type, String url, WeeklyHours hours, String timezone) {
        this.address = address;
        this.comingSoon = comingSoon;
        this.communityDayLink = communityDayLink;
        this.distance = distance;
        this.googleMapsLink = googleMapsLink;
        this.firstSlide = firstSlide;
        this.id = id;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.region = region;
        this.sanitizedAddress = sanitizedAddress;
        this.slug = slug;
        this.type = type;
        this.url = url;
        this.hours = hours;
        this.timezone = timezone;
    }
    public String getAddress() {
        return address;
    }

    public boolean isComingSoon() {
        return comingSoon;
    }

    public boolean isCommunityDayLink() {
        return communityDayLink;
    }

    public String getDistance() {
        return distance;
    }

    public String getGoogleMapsLink() {
        return googleMapsLink;
    }

    public boolean isFirstSlide() {
        return firstSlide;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getSanitizedAddress() {
        return sanitizedAddress;
    }

    public String getSlug() {
        return slug;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public WeeklyHours getHours() {
        return hours;
    }

    public String getTimezone() {
        return timezone;
    }
}