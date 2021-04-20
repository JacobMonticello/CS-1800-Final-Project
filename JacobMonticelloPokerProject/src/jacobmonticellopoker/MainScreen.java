package jacobmonticellopoker;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainScreen extends JFrame {
    private CardInterface cardInterface;
    private InformationInterface informationInterface;
    private JButton clearButton;
    
    public MainScreen(){
        super("♠♥ Poker Calculator ♦♣");
        
        setSize(725, 285);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);
        
        CardMaster.setCards();
        
        initInformation();
        initCard();  
        initClearButton();
        
        add(cardInterface);
        add(informationInterface);
        add(clearButton);
    }
    
    private InformationInterface initInformation(){
        informationInterface = new InformationInterface();
        return informationInterface;
    }  
    
    private CardInterface initCard(){
        cardInterface = new CardInterface(informationInterface);
        return cardInterface;
    }
    
    private JButton initClearButton(){
        clearButton = new JButton("Clear");
        clearButton.setBackground(Color.WHITE);
        
        clearButton.addActionListener((ActionEvent e) -> {   
            CardMaster.setCards();  
            informationInterface.reset();
            cardInterface.reset();
        });  
        
        return clearButton;
    }
    
}
