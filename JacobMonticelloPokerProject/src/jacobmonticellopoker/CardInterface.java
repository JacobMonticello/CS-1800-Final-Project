package jacobmonticellopoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardInterface extends JPanel{
    private CardContainer[] currentCards = new CardContainer[5];
    private InformationInterface info;
    private int stage;
    
    public CardInterface(InformationInterface info){
        this.info = info;
        stage = 0;
        
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);
                
        add(setLabel(), BorderLayout.PAGE_START);
        add(setCurrentCards(), BorderLayout.CENTER);
        add(setButtons(), BorderLayout.PAGE_END);
        
    }
    
    public JPanel setLabel(){
        JPanel titleHolder = new JPanel();
        JLabel title = new JLabel("Cards");        
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
      
        title.setBackground(Color.WHITE);
        
        titleHolder.add(title);
        
        return titleHolder;
    }
    
    public JPanel setCurrentCards(){
        JPanel currCards = new JPanel();
        currCards.setLayout(new FlowLayout());
        
        for(int i = 0; i < 5; i++){
            currentCards[i] = new CardContainer(info);
        }
        
        currCards.add(currentCards[0]);
        currCards.add(currentCards[1]); 
        currCards.add(currentCards[2]); 
        currCards.add(currentCards[3]); 
        currCards.add(currentCards[4]); 
        
        return currCards;
    }
    
    private JPanel setButtons(){
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        
        
        buttons.add(flopButton());
        buttons.add(turnButton()); 
        buttons.add(riverButton()); 
        
        return buttons;
    }
    
    private JButton flopButton(){
        JButton button = new JButton("Flop");
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(260, 35));
        
        button.addActionListener((ActionEvent e) -> {     
            if(stage == 0){
                createCards();
            }
        });  
        
        return button;
    }
    
    private JButton turnButton(){
        JButton button = new JButton("Turn");
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(75, 35));
        
        button.addActionListener((ActionEvent e) -> {   
            if(stage == 1){
                createCards();
            }
        });  
        
        return button;
    }
    
    private JButton riverButton(){
        JButton button = new JButton("River");
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(75, 35));
        
        button.addActionListener((ActionEvent e) -> {
            if(stage == 2){
                createCards();
            }
        });  
        
        return button;
    }
    
    //stage:
    // 0 = flop
    // 1 = turn
    // 2 = river
    private void createCards(){
        if(stage == 0){
            createCard(currentCards[0]);
            createCard(currentCards[1]);
            createCard(currentCards[2]);
        }else if(stage == 1){
            createCard(currentCards[3]);            
        }else if(stage == 2){
            createCard(currentCards[4]);            
        }        

        stage++;
        CardMaster.callAll();
        info.updateInformation();
    }
    
    private void createCard(CardContainer card){
        card.initialize();
    }    
    
    public void reset(){
        stage = 0;
        
        for(int i = 0; i < 5; i++){
            currentCards[i].reset();
        }
    }
}
