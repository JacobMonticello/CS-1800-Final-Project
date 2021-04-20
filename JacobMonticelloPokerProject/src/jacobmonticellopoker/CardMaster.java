package jacobmonticellopoker;

import java.util.ArrayList;

public class CardMaster {
    public static ArrayList<Card> validCards = new ArrayList<>();
    public static ArrayList<Card> currentCards = new ArrayList<>();
    
    public static double pair, twopair, threekind, fullhouse, fourkind;
    
    public static void setCards(){
        validCards = new ArrayList<>();
        currentCards = new ArrayList<>();
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                Card tempCard = new Card(intToValue(j),intToSuite(i));
                validCards.add(tempCard);
            }
        }
        
        pair = 0.42257;
        twopair = 0.04754;
        threekind = 0.02113;
        fullhouse = 0.00144;
        fourkind = 0.00024;
    }
        
    public static void removeCard(Card card){
        String tempValue = card.getValue();
        String tempSuite = card.getSuite();
        
        for(int i = 0; i < validCards.size(); i++){
            Card currentCard = validCards.get(i);
            String currentValue = currentCard.getValue();
            String currentSuite = currentCard.getSuite();
            
            if(currentValue.equals(tempValue) && currentSuite.equals(tempSuite)){
                validCards.remove(i);
            }
        }
    }
    
    public static void addCard(Card card){
        if(!cardExists(card)){
            validCards.add(card);
        }
    }   
    
    
    public static boolean cardExists(Card card){
        String tempValue = card.getValue();
        String tempSuite = card.getSuite();
        
        boolean exists = false;
        for(int i = 0; i < validCards.size(); i++){
            Card currentCard = validCards.get(i);
            String currentValue = currentCard.getValue();
            String currentSuite = currentCard.getSuite();
            if(currentValue.equals(tempValue) && currentSuite.equals(tempSuite)){
                exists = true;
            }
        }
        
        return exists;
    }
    
    public static void removeCardRiver(Card card){
        String tempValue = card.getValue();
        String tempSuite = card.getSuite();
        
        for(int i = 0; i < currentCards.size(); i++){
            Card currentCard = currentCards.get(i);
            String currentValue = currentCard.getValue();
            String currentSuite = currentCard.getSuite();
            
            if(currentValue.equals(tempValue) && currentSuite.equals(tempSuite)){
                currentCards.remove(i);
            }
        }
    }
    
    public static void addCardRiver(Card card){
        if(!cardExistsRiver(card)){
            currentCards.add(card);
        }
    }
    
    public static boolean cardExistsRiver(Card card){
        String tempValue = card.getValue();
        String tempSuite = card.getSuite();
        
        boolean exists = false;
        
        for(int i = 0; i < currentCards.size(); i++){
            Card currentCard = currentCards.get(i);
            String currentValue = currentCard.getValue();
            String currentSuite = currentCard.getSuite();
            
            if(currentValue.equals(tempValue) && currentSuite.equals(tempSuite)){
                exists = true;
            }
        }
        
        return exists;
    }
    
    public static void callAll(){
        pair();
        twoPair();
        ToK();
        fullHouse();
        FoK();
            
    }
    
    public static void pair(){  
        pair = 0; 
        switch(currentCards.size()){
            case 0:
                pair = 0.42257;
            case 3:
                if(isNothing()){
                    pair = (360 + 10*C(4,2))/C(49,2);
                }else if(isPair()){
                    pair = 1 - ((132 + 11*C(4,2))/C(49,2) + (88+C(3,2))/C(49,2) + C(3,2)/C(49,2) + C(2,2)/C(49,2));
                }         
                break;
            case 4:
                if(isNothing()){
                    pair = 0.25;
                }else if(isPair()){
                    pair = 1 - 1.0/6.0;
                }                  
                break;
            case 5:
                pair = 0;
                if(isPair()){
                    pair = 1;
                }             
                break;
        }
    }
    
    public static void twoPair(){
        twopair = 0;
        switch(currentCards.size()){
            case 0:
                pair = 0.04754;
            case 3:
                if(isNothing()){
                    twopair = (C(3,2)*3)/C(49,2);
                }else if(isPair()){
                    twopair = (132 + 11*C(4,2))/C(49,2);
                }              
                break;
            case 4:
                if(isPair()){
                    twopair = 0.125;
                }else if(isTwoPair()){
                    twopair = 1 - 1.0/12.0;
                }                  
                break;
            case 5:
                twopair = 0;
                if(isTwoPair()){
                    twopair = 1;
                }             
                break;
        }
    }
    
    public static void ToK(){   
        threekind = 0;   
        switch(currentCards.size()){
            case 0:
                threekind = 0.02113;
            case 3:
                if(isNothing()){
                    threekind = (3*C(3,2))/C(49,2);
                }else if(isPair()){
                    threekind = (88 + C(3,2))/C(49,2);
                }else if(isToK()){
                    threekind = 1 - (12*C(4,2) + 48)/C(49,2);
                }               
                break;
            case 4:
                if(isPair()){
                    threekind = 1.0/24.0;
                }else if(isToK()){
                    threekind = 1 - 1.0/12.0;
                }                  
                break;
            case 5:
                threekind = 0;
                if(isToK()){
                    threekind = 1;
                }             
                break;
        }
    }
    
    public static void fullHouse(){   
        fullhouse = 0;
        switch(currentCards.size()){
            case 0:
                fullhouse = 0.00144;
            case 3:
                if(isPair()){
                    fullhouse = C(3,2)/C(49,2);
                }else if(isToK()){
                    fullhouse = 12*C(4,2)/C(49,2);
                }               
                break;
            case 4:
                if(isTwoPair()){
                    fullhouse = 1.0/12.0;
                }else if(isToK()){
                    fullhouse = 1.0/16.0;
                }                  
                break;
            case 5:
                fullhouse = 0;
                if(isFullHouse()){
                    fullhouse = 1;
                }             
                break;
                
        }
    }
    
    public static void FoK(){    
        fourkind = 0;
        switch(currentCards.size()){
            case 0:
                fourkind = 0.00024;
            case 3:
                if(isPair()){
                    fourkind = C(2,2)/C(49,2);
                }else if(isToK()){
                    fourkind = 48.0/C(49,2);
                }               
                break;
            case 4:
                if(isToK()){
                    fourkind = 1.0/48.0;
                }                  
                break;
            case 5:
                fourkind = 0;
                if(isFoK()){
                    fourkind = 1;
                }             
                break;
        }
    }
    
    public static boolean isNothing(){
        return !(isPair() || isTwoPair() || isToK() || isFullHouse() || isFoK());
    }
    
    public static boolean isPair(){
        if(isToK() || isTwoPair() || isFullHouse()){
            return false;
        }
        
        int[] cardVals = new int[13];
                
        for(int i = 0; i < currentCards.size(); i++){
            cardVals[indexValue(currentCards.get(i).getValue())]++;
        }
            
        for(int i = 0; i < cardVals.length; i++){
            if(cardVals[i] == 2){
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean isTwoPair(){
        int[] cardVals = new int[13];
        
        
        for(int i = 0; i < currentCards.size(); i++){
            cardVals[indexValue(currentCards.get(i).getValue())]++;
        }
              
        int counter = 0;
        
        for(int i = 0; i < cardVals.length; i++){
            if(cardVals[i] == 2){
                counter++;        
                if(counter == 2){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean isToK(){
        if(isFullHouse()){
            return false;
        }
        
        int[] cardVals = new int[13];
        
        
        for(int i = 0; i < currentCards.size(); i++){
            cardVals[indexValue(currentCards.get(i).getValue())]++;
        }
                
        boolean returnVal = false;
        
        for(int i = 0; i < cardVals.length; i++){
            if(cardVals[i] == 2){
                return false;
            }
            if(cardVals[i] == 3){
                returnVal = true;
            }
        }
        
        return returnVal;
    }
    
    public static boolean isFoK(){
        int[] cardVals = new int[13];
        
        
        for(int i = 0; i < currentCards.size(); i++){
            cardVals[indexValue(currentCards.get(i).getValue())]++;
        }
                
        for(int i = 0; i < cardVals.length; i++){
            if(cardVals[i] == 4){
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean isFullHouse(){          
        int[] cardVals = new int[13];
        
        for(int i = 0; i < currentCards.size(); i++){
            cardVals[indexValue(currentCards.get(i).getValue())]++;
        }
                
        int counter1 = 0;
        int counter2 = 0;
        
        for(int i = 0; i < cardVals.length; i++){
            if(cardVals[i] == 2){
                counter1++;
            }
            if(cardVals[i] == 3){
                counter2++;
            }
        }
        
        return (counter1 == 1 && counter2 == 1);
    }
    
    public static double  C(int n1, int n2){        
        int total = 1;
        int divider = 1;
        
        for(int i = 0; i < n2; i++){
            total *= n1 - i;  
            divider *= n2 - i;
        }
        
        
        return total/divider;
    }
    
    public static int indexValue(String val){
        switch(val){
            case "2":
                return 0;
            case "3":
                return 1;
            case "4":
                return 2;
            case "5":
                return 3;
            case "6":
                return 4;
            case "7":
                return 5;
            case "8":
                return 6;
            case "9":
                return 7;
            case "T":
                return 8;
            case "J":
                return 9;
            case "Q":
                return 10;
            case "K":
                return 11;
            case "A":
                return 12;
            default:
                return 0;
        }
    }
    
    public static String intToValue(int val){
        switch(val){
            case 0:
                return "2";
            case 1:
                return "3";
            case 2:
                return "4";
            case 3:
                return "5";
            case 4:
                return "6";
            case 5:
                return "7";
            case 6:
                return "8";
            case 7:
                return "9";
            case 8:
                return "T";
            case 9:
                return "J";
            case 10:
                return "Q";
            case 11:
                return "K";
            case 12:
                return "A";
            default:
                return "2";
        }
    }
        
    public static int indexSuite(String sui){
        switch(sui){
            case "♠":
                return 0;
            case "♥":
                return 1;
            case "♦":
                return 2;
            case "♣":
                return 3;
            default:
                return 0;
        }
    }
    
    public static String intToSuite(int sui){
        switch(sui){
            case 0:
                return "♠";
            case 1:
                return "♥";
            case 2:
                return "♦";
            case 3:
                return "♣";
            default:
                return "♠";
        }
    }
}
