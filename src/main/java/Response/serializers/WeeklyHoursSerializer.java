package Response.serializers;

import Response.WeeklyHours;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class WeeklyHoursSerializer extends JsonSerializer<WeeklyHours> {

    @Override
    public void serialize(WeeklyHours weeklyHours, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(DateTimeFormatter.ISO_LOCAL_DATE.format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(weeklyHours.getMonday().getClosed())));
        jsonGenerator.writeObject(weeklyHours.getMonday());
        jsonGenerator.writeFieldName(DateTimeFormatter.ISO_LOCAL_DATE.format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(weeklyHours.getTuesday().getClosed())));
        jsonGenerator.writeObject(weeklyHours.getTuesday());
        jsonGenerator.writeFieldName(DateTimeFormatter.ISO_LOCAL_DATE.format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(weeklyHours.getWednesday().getClosed())));
        jsonGenerator.writeObject(weeklyHours.getWednesday());
        jsonGenerator.writeFieldName(DateTimeFormatter.ISO_LOCAL_DATE.format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(weeklyHours.getThursday().getClosed())));
        jsonGenerator.writeObject(weeklyHours.getThursday());
        jsonGenerator.writeFieldName(DateTimeFormatter.ISO_LOCAL_DATE.format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(weeklyHours.getFriday().getClosed())));
        jsonGenerator.writeObject(weeklyHours.getFriday());
        jsonGenerator.writeFieldName(DateTimeFormatter.ISO_LOCAL_DATE.format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(weeklyHours.getSaturday().getClosed())));
        jsonGenerator.writeObject(weeklyHours.getSaturday());
        jsonGenerator.writeFieldName(DateTimeFormatter.ISO_LOCAL_DATE.format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(weeklyHours.getSunday().getClosed())));
        jsonGenerator.writeObject(weeklyHours.getSunday());
        jsonGenerator.writeEndObject();
    }
}
