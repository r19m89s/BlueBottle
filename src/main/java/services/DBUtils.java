package services;

import Response.CafeDetails;
import Response.OpenHours;
import Response.WeeklyHours;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DBUtils {
    private static Connection con;
    private static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static String dbAddress = "jdbc:mysql://localhost:3306/";
    private static String dbName = "CAFEINFO";
    private static String userName = "root";
    private static String passWord = "";
    private static PreparedStatement statement;

    public DBUtils() {
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, passWord);
        } catch (SQLException e) {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void createDatabase() {
        try {
            con = DriverManager.getConnection(dbAddress + String.format("?user=%s&password=%s", userName, passWord));
            Statement s = con.createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void createTableCafeDetails() {
        String tableCreation = "CREATE TABLE IF NOT EXISTS CAFEINFO.cafe_detail ("
                + "id INT(64) NOT NULL AUTO_INCREMENT,"
                + "address VARCHAR(255) NOT NULL,"
                + "coming_soon BOOLEAN,"
                + "community_day_link BOOLEAN,"
                + "google_map_link VARCHAR(255),"
                + "first_slide BOOLEAN,"
                + "image_url VARCHAR(255),"
                + "latitude DECIMAL(18,6),"
                + "longitude DECIMAL(18,6),"
                + "name VARCHAR(255),"
                + "region VARCHAR(255),"
                + "sanitized_address VARCHAR(255),"
                + "slug VARCHAR(255),"
                + "type VARCHAR(255),"
                + "url VARCHAR(255),"
                + "timezone VARCHAR(255),"
                + "PRIMARY KEY (id));";
        try {
            statement = con.prepareStatement(tableCreation);
            //The next line has the issue
            statement.executeUpdate();
            System.out.println("Table Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void insertCafeHours(int id, List<OpenHours> openHoursList) {
        String insertHours =
                String.format("INSERT INTO CAFEINFO.cafe_hours ( `id`, `day`, `open`, `closed`)"
                                      +
                                      " VALUES (%d, 'monday', '%s', '%s'), (%d, 'tuesday', '%s', '%s'), (%d, 'wednesday', '%s', '%s'), "
                                      + "(%d, 'thursday', '%s', '%s'),  (%d, 'friday', '%s', '%s'),"
                                      + " (%d, 'saturday', '%s', '%s'), (%d, 'sunday', '%s', '%s');",
                              id, openHoursList.get(0).getOpen(), openHoursList.get(0).getClosed(),
                              id, openHoursList.get(1).getOpen(), openHoursList.get(1).getClosed(),
                              id, openHoursList.get(2).getOpen(), openHoursList.get(2).getClosed(),
                              id, openHoursList.get(3).getOpen(), openHoursList.get(3).getClosed(),
                              id, openHoursList.get(4).getOpen(), openHoursList.get(4).getClosed(),
                              id, openHoursList.get(5).getOpen(), openHoursList.get(5).getClosed(),
                              id, openHoursList.get(6).getOpen(), openHoursList.get(6).getClosed());
        try {
            statement = con.prepareStatement(insertHours, Statement.RETURN_GENERATED_KEYS);
            //The next line has the issue
            statement.executeUpdate();
            System.out.println("Table Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void populateCafeDetails() {
        String insertBrooklynIntoTable =
                "INSERT INTO CAFEINFO.cafe_detail (`address`, `coming_soon`, `community_day_link`, `google_map_link`,"
                        +
                        " `first_slide`, `image_url`, `latitude`, `longitude`, `name`, `region`, `sanitized_address`, `slug`, `type`, `url`, `timezone`)"
                        + " SELECT '203 7th Avenue<br/>Brooklyn, NY 11215', false, false,"
                        +
                        "'http://maps.google.com/maps?daddr=203%207th%20Avenue%2C%20Brooklyn%2C%20NY%2011215%20US', true, "
                        +
                        "'https://blue-bottle-cms.global.ssl.fastly.net/hbhhv9rz9/image/upload/s--hhUcfcMc--/c_thumb,h_224,q_jpegmini,w_335/v1478284322/jjggt5buupuainkf8zgp.jpg',"
                        +
                        "40.670643, -73.978191, 'Park Slope', 'new_york', '203 7th Avenue<br/>Brooklyn, NY 11215', 'park-slope',"
                        + "'cafe', 'https://bluebottlecoffee.com/cafes/park-slope','America/New_York'"
                        +
                        " WHERE NOT EXISTS (SELECT 1 FROM CAFEINFO.cafe_detail WHERE address like '203 7th Avenue%') LIMIT 1;";
        try {
            statement = con.prepareStatement(insertBrooklynIntoTable, Statement.RETURN_GENERATED_KEYS);
            int updateResult = statement.executeUpdate();
            if (updateResult == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                insertCafeHours(generatedKeys.getInt(1),
                                Arrays.asList(new OpenHours("07:00:00-04:00", "18:00:00-04:00"),
                                              new OpenHours("07:00:00-04:00", "18:00:00-04:00"),
                                              new OpenHours("07:00:00-04:00", "18:00:00-04:00"),
                                              new OpenHours("07:00:00-04:00", "18:00:00-04:00"),
                                              new OpenHours("07:00:00-04:00", "18:00:00-04:00"),
                                              new OpenHours("07:30:00-04:00", "18:30:00-04:00"),
                                              new OpenHours("07:30:00-04:00", "17:00:00-04:00")));
            }
            System.out.println("Inserted brooklyn into table;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String insertOaklandIntoTable =
                "INSERT INTO CAFEINFO.cafe_detail (`address`, `coming_soon`, `community_day_link`, `google_map_link`, `first_slide`, "
                        +
                        "`image_url`, `latitude`, `longitude`, `name`, `region`, `sanitized_address`, `slug`, `type`, "
                        + "`url`, `timezone`) SELECT '480 9th Street<br/>Oakland, CA 94607', false, false, "
                        +
                        "'http://maps.google.com/maps?daddr=480%209th%20Street%2C%20Oakland%2C%20CA%2094607%20US',true, "
                        +
                        "'https://blue-bottle-cms.global.ssl.fastly.net/hbhhv9rz9/image/upload/s--dN9bOlNC--/c_thumb,h_224,q_jpegmini,w_335/v1544645070/ty6k22cncdxxtmti4ksa.jpg',"
                        +
                        "37.801247, -122.273948, 'Old Oakland', 'bay_area', '480 9th Street<br/>Oakland, CA 94607', 'old-oakland',"
                        + "'cafe', 'https://bluebottlecoffee.com/cafes/old-oakland', 'America/Los_Angeles' "
                        +
                        "WHERE NOT EXISTS (SELECT 1 FROM CAFEINFO.cafe_detail WHERE address like '480 9th Street%') LIMIT 1;\n";
        try {
            statement = con.prepareStatement(insertOaklandIntoTable, Statement.RETURN_GENERATED_KEYS);
            //The next line has the issue
            int updateResult = statement.executeUpdate();
            if (updateResult == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                insertCafeHours(generatedKeys.getInt(1),
                                Arrays.asList(new OpenHours("06:30:00-07:00", "17:30:00-07:00"),
                                              new OpenHours("06:30:00-07:00", "17:30:00-07:00"),
                                              new OpenHours("06:30:00-07:00", "17:30:00-07:00"),
                                              new OpenHours("06:30:00-07:00", "17:30:00-07:00"),
                                              new OpenHours("06:30:00-07:00", "17:30:00-07:00"),
                                              new OpenHours("07:00:00-07:00", "17:30:00-07:00"),
                                              new OpenHours("07:00:00-07:00", "17:00:00-07:00")));
            }
            System.out.println("Table Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected static void createTableCafeHours() {
        String tableCreation = "CREATE TABLE IF NOT EXISTS CAFEINFO.cafe_hours ("
                + "id INT(64) NOT NULL,"
                + "day VARCHAR(255) NOT NULL,"
                + "open VARCHAR(255) NOT NULL,"
                + "closed VARCHAR(255) NOT NULL);";
        try {
            statement = con.prepareStatement(tableCreation);
            //The next line has the issue
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static List<CafeDetails> getCafes(BigDecimal latitude, BigDecimal longitude, Integer radius) {
        List<CafeDetails> cafes = new ArrayList<>();
        String getCafesWithinDistance = String.format("SELECT subq.distance, cafe_detail.*\n" +
                                                              "FROM  \n" +
                                                              "(SELECT 6371 * 2 * ASIN(SQRT(\n" +
                                                              "            POWER(SIN((latitude - abs(%s)) * pi()/180 / 2),\n" +
                                                              "            2) + COS(latitude * pi()/180 ) * COS(abs(%s) *\n" +
                                                              "            pi()/180) * POWER(SIN((longitude - %s) *\n" +
                                                              "            pi()/180 / 2), 2) )) as distance, id\n" +
                                                              "            FROM CAFEINFO.cafe_detail) as subq\n" +
                                                              "            INNER JOIN CAFEINFO.cafe_detail on cafe_detail.id = subq.id\n" +
                                                              "            WHERE subq.distance < %d;",
                                                      latitude, latitude, longitude, radius);
        try {
            statement = con.prepareStatement(getCafesWithinDistance);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CafeDetails cafeDetails = new CafeDetails(rs.getString(3), rs.getBoolean(4),
                                                          rs.getBoolean(5),
                                                          rs.getDouble(1) * 0.671 + " miles",
                                                          rs.getString(6), rs.getBoolean(7),
                                                          rs.getInt(2), rs.getString(8),
                                                          rs.getBigDecimal(9),
                                                          rs.getBigDecimal(10), rs.getString(11),
                                                          rs.getString(12), rs.getString(14),
                                                          rs.getString(14), rs.getString(15),
                                                          rs.getString(16),
                                                          getWeeklyHours(rs.getInt(2), rs.getString(17)),
                                                          rs.getString(17));
                cafes.add(cafeDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cafes;
    }

    private static WeeklyHours getWeeklyHours(Integer id, String timezone) {
        String getCafeHours = String.format("SELECT * FROM CAFEINFO.cafe_hours WHERE id = %d", id);
        WeeklyHours weeklyHours = new WeeklyHours();
        try {
            statement = con.prepareStatement(getCafeHours);
            ResultSet rs = statement.executeQuery();
            List<OpenHours> openHours = Arrays.asList(weeklyHours.getMonday(), weeklyHours.getTuesday(),
                                                      weeklyHours.getWednesday(), weeklyHours.getThursday(),
                                                      weeklyHours.getFriday(), weeklyHours.getSaturday(),
                                                      weeklyHours.getSunday());
            int listIndex = 0;
            LocalDate curDate = LocalDate.from(
                    Calendar.getInstance().getTime().toInstant().atZone(TimeZone.getTimeZone(timezone).toZoneId()));
            if (!curDate.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                curDate = curDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
            }
            while (rs.next()) {
                LocalTime openTime = LocalTime.parse(rs.getString(3), DateTimeFormatter.ofPattern("HH:mm:ss"));
                LocalTime closedTime = LocalTime.parse(rs.getString(4), DateTimeFormatter.ofPattern("HH:mm:ss"));
                openHours.get(listIndex).setClosed(
                        LocalDateTime.of(curDate, closedTime).atZone(TimeZone.getTimeZone(timezone).toZoneId()).format(
                                DateTimeFormatter.ISO_ZONED_DATE_TIME));
                openHours.get(listIndex).setOpen(
                        LocalDateTime.of(curDate, openTime).atZone(TimeZone.getTimeZone(timezone).toZoneId()).format(
                                DateTimeFormatter.ISO_ZONED_DATE_TIME));
                curDate = curDate.plus(1, ChronoUnit.DAYS);
                listIndex += 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weeklyHours;
    }
}
