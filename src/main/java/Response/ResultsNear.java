package Response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class ResultsNear {
    List<CafeDetails> resultsNear;

    public ResultsNear(List<CafeDetails> cafeDetails) {
        this.resultsNear = cafeDetails;
    }

    public List<CafeDetails> getResultsNear(){
        return this.resultsNear;
    }

}
