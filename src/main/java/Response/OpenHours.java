package Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenHours {
    private String open;
    private String closed;

    public String getOpen(){
        return this.open;
    }
    public void setOpen(String open){
        this.open = open;
    }
    public String getClosed(){
        return this.closed;
    }
    public void setClosed(String closed){
        this.closed = closed;
    }
    public OpenHours(){
        this.open = "";
        this.closed = "";
    }
    public OpenHours(String open, String closed){
        this.open = open;
        this.closed = closed;
    }
}
