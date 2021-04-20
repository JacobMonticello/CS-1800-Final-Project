package jacobmonticellopoker;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CardSelector extends JFrame{
    private CardContainer cards;
    private JComboBox valueComboBox;
    private JComboBox suiteComboBox;    
    private String[] values = new String[]{"2","3","4","5","6","7","8",
                                           "9","T","J","Q","K","A"};
    private String[] suites = new String[]{"♠","♥","♦","♣"};
    private String value;
    private String suite;
    private InformationInterface info;
    private JLabel warningLabel;
    
    public CardSelector(CardContainer cards, InformationInterface info){
        super("♠♥ Card Selector ♦♣");
        this.info = info;
        this.cards = cards;
        this.value = cards.getValue();
        this.suite = cards.getSuite();
        
        setLayout(new FlowLayout());

        setSize(325, 105);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        valueComboBox = setTypeSelector();
        suiteComboBox = setSuiteSelector();
        
        add(valueComboBox);
        add(suiteComboBox);        
        add(setEnterButton());    
        add(setWarning());
    }
    
    private JComboBox setTypeSelector(){
        JComboBox typeSelector = new JComboBox(values);
        typeSelector.setBackground(Color.WHITE);
        typeSelector.setSelectedIndex(CardMaster.indexValue(cards.getValue()));
                
        typeSelector.addActionListener((ActionEvent e) -> {
            JComboBox temp = (JComboBox)e.getSource();
            value = (temp.getSelectedItem().toString());
        });
                
        return typeSelector;
    }
    
    private JComboBox setSuiteSelector(){
        JComboBox suiteSelector = new JComboBox(suites);
        suiteSelector.setBackground(Color.WHITE);
        suiteSelector.setSelectedIndex(CardMaster.indexSuite(cards.getSuite()));
                
        suiteSelector.addActionListener((ActionEvent e) -> {
            JComboBox temp = (JComboBox)e.getSource();
            suite = (temp.getSelectedItem().toString());
        });        
        
        return suiteSelector;
    }
    
    private JButton setEnterButton(){
        JButton button = new JButton("Enter Card");
        button.setBackground(Color.WHITE);
        
        button.addActionListener((ActionEvent e) -> {   
            Card oldCard = new Card(cards.getValue(),cards.getSuite());
            Card newCard = new Card((valueComboBox.getSelectedItem().toString()),(suiteComboBox.getSelectedItem().toString()));
            
            if(CardMaster.cardExists(newCard)){
                CardMaster.addCard(oldCard);
                CardMaster.removeCardRiver(oldCard);

                value = (valueComboBox.getSelectedItem().toString());
                suite = (suiteComboBox.getSelectedItem().toString());

                cards.setCard(value, suite);

                CardMaster.addCardRiver(newCard);
                CardMaster.removeCard(newCard);
                CardMaster.callAll();

                info.updateInformation();
                dispose();
            }else{                
                updateWarning("Card Already in Hand!");
            }
        });  
        
        return button;
    }   
    
    private JLabel setWarning(){
        warningLabel = new JLabel("");        
        warningLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        warningLabel.setForeground(Color.RED);
        warningLabel.setBackground(Color.WHITE);
        
        return warningLabel;
    }   
    
    private void updateWarning(String text){
        warningLabel.setText(text);
    }
    
    public String getValue(){
        return value;
    }
    
    public String getSuite(){
        return suite;
    }
}
