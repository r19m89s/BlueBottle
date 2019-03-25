package Response;

import Response.serializers.WeeklyHoursSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = WeeklyHoursSerializer.class)
public class WeeklyHours {
    private OpenHours monday;
    private OpenHours tuesday;
    private OpenHours wednesday;
    private OpenHours thursday;
    private OpenHours friday;
    private OpenHours saturday;
    private OpenHours sunday;

    public WeeklyHours() {
        this.monday = new OpenHours();
        this.tuesday = new OpenHours();
        this.wednesday = new OpenHours();
        this.thursday = new OpenHours();
        this.friday = new OpenHours();
        this.saturday = new OpenHours();
        this.sunday = new OpenHours();
    }

    public OpenHours getMonday() {
        return this.monday;
    }

    public OpenHours getTuesday() {
        return this.tuesday;
    }

    public OpenHours getWednesday() {
        return this.wednesday;
    }

    public OpenHours getThursday() {
        return this.thursday;
    }

    public OpenHours getFriday() {
        return this.friday;
    }

    public OpenHours getSaturday() {
        return this.saturday;
    }

    public OpenHours getSunday() {
        return this.sunday;
    }
}
