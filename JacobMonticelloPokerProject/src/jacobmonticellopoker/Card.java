package jacobmonticellopoker;

public class Card {
    private String value;
    private String suite;
    
    public Card(String val, String sui){
        value = val;
        suite = sui;
    }
    
    public void setValue(String val){
        value = val;
    }
    
    public void setSuite(String sui){
        suite = sui;
    }
    
    public String getValue(){
        return value;
    }
    
    public String getSuite(){
        return suite;
    }
}
