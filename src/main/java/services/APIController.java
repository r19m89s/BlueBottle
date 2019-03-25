package services;

import Response.Cafes;
import Response.ResultsNear;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api")
public class APIController {
    DBUtils dbUtils = new DBUtils();

    @GetMapping("cafe_search/fetch.json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Cafes getCafes(@QueryParam("coordinates") boolean coordinates, @QueryParam("latitude") BigDecimal latitude,
                          @QueryParam("longitude") BigDecimal longitude,
                          @QueryParam("radius") Integer radius) {
        if (radius == null) {
            radius = 30;
        }
        return new Cafes(new ResultsNear(DBUtils.getCafes(latitude, longitude, radius)));
    }

}
