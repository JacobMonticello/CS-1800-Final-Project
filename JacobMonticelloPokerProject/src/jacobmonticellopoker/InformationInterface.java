package jacobmonticellopoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationInterface extends JPanel{    
    private JLabel pair, twopair, threekind, fullhouse, fourkind, nothing;
    
    public InformationInterface(){
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);
                
        add(setLabel(), BorderLayout.PAGE_START);
        add(setInformation(), BorderLayout.LINE_START);
                
    }    
    
    private JPanel setLabel(){
        JPanel titleHolder = new JPanel();
        JLabel title = new JLabel("Statistics");        
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
      
        title.setBackground(Color.WHITE);
        
        titleHolder.add(title);
        
        return titleHolder;
    }
    
    private JPanel setInformation(){
        JPanel infoHolder = new JPanel();
        infoHolder.setLayout(new GridLayout(9,1));
        
        pair = new JLabel("Pair:                                   %" + (String.format("%4.3f", CardMaster.pair * 100)));
        twopair = new JLabel("Two Pair:                          %" + (String.format("%4.3f", CardMaster.twopair * 100)));   
        threekind = new JLabel("Three of a Kind                %" + (String.format("%4.3f", CardMaster.threekind * 100)));      
        fullhouse = new JLabel("Full House:                        %" + (String.format("%4.3f", CardMaster.fullhouse * 100)));
        fourkind = new JLabel("Four of a Kind:                  %" + (String.format("%4.3f", CardMaster.fourkind * 100)));
        nothing = new JLabel("Nothing:                             %" + (String.format("%4.3f", (1 - CardMaster.pair - CardMaster.twopair - CardMaster.threekind - CardMaster.fullhouse - CardMaster.fourkind) * 100)));
        
        infoHolder.add(pair);
        infoHolder.add(twopair);
        infoHolder.add(threekind);
        infoHolder.add(fullhouse);
        infoHolder.add(fourkind);
        infoHolder.add(nothing);
        
        return infoHolder;        
    }
       
    public void updateInformation(){
        pair.setText("Pair:                                   %" + (String.format("%4.3f", CardMaster.pair * 100)));
        twopair.setText("Two Pair:                          %" + (String.format("%4.3f", CardMaster.twopair * 100)));   
        threekind.setText("Three of a Kind                %" + (String.format("%4.3f", CardMaster.threekind * 100))); 
        fullhouse.setText("Full House:                        %" + (String.format("%4.3f", CardMaster.fullhouse * 100)));
        fourkind.setText("Four of a Kind:                  %" + (String.format("%4.3f", CardMaster.fourkind * 100)));
        nothing.setText("Nothing:                             %" + (String.format("%4.3f", (1 - CardMaster.pair - CardMaster.twopair - CardMaster.threekind - CardMaster.fullhouse - CardMaster.fourkind) * 100)));
        repaint();
    }
    
    public void reset(){
        updateInformation();
    }
}
