package jacobmonticellopoker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;

public class CardContainer extends JButton implements ActionListener{
    private String value;
    private String suite;
    private InformationInterface info;
    private boolean initialized;
    
    public CardContainer(InformationInterface info){
        super("--");
        this.info = info;
        value = "-";
        suite = "-";
        initialized = false;
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(80, 130));
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if(initialized){
            CardSelector select = new CardSelector(this, info);
            select.setVisible(true);        
        }
    }
    
    public void setCard(String val, String sui){
        value = val;
        suite = sui;
        
        setText(value+suite);
    }
    
    public void initialize(){
        initialized = true;
        
        Card randomCard = (CardMaster.validCards).get(new Random().nextInt((CardMaster.validCards).size()));
        setCard(randomCard.getValue(), randomCard.getSuite());
        
        CardMaster.addCardRiver(randomCard);
        CardMaster.removeCard(randomCard);
    }
    
    public void reset(){
        initialized = false;
        setCard("-","-");
    }
    
    public String getValue(){
        return value;
    }
    
    public String getSuite(){
        return suite;
    }
    
}
