package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
/*
 * Dmitry likes TopCoder Single Round Matches. Once, he registered for an SRM and was waiting for the coding phase to begin. To entertain himself while waiting, he decided to play the following game. 

He makes a pile of cards, and on each card, he writes the number of an SRM in which he has competed. No two cards contain the same number. He then takes turns until the pile is empty. Each turn consists of the following sequence of actions:
Dmitry chooses an arbitrary card from the pile. Let A be the number written on this card.
The card with number A is removed from the pile.
If the pile contains a card with number A-1, that card is removed from the pile.
If the pile contains a card with number A+1, that card is removed from the pile.
The game is finished when the pile becomes empty. It's fun to play the game, so Dmitry wants to spend as much time as possible playing it. 

You are given a int[] cards containing the numbers written on the cards in the pile before the start of the game. Return the largest possible number of turns in which Dmitry can finish the game.
 * 
 * 
 * Examples
0)	
    	
{498, 499}
Returns: 1
In the first turn, Dmitry can choose A = 498 or A = 499. In any of these cases both cards will be removed from the pile and the game will be finished.
1)	
    	
{491, 492, 495, 497, 498, 499}
Returns: 4
One out of many possible ways to spend 4 turns playing this game is to choose the following numbers in each turn: 497, 499, 495, 492.
2)	
    	
{100, 200, 300, 400}
Returns: 4
3)	
    	
{11, 12, 102, 13, 100, 101, 99, 9, 8, 1}
Returns: 6
Note that the elements of cards are not necessarily sorted in ascending order.
4)	
    	
{118, 321, 322, 119, 120, 320}
Returns: 4
5)	
    	
{10, 11, 12, 13, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9}
Returns: 7
*
*/


public class SRMCards {
	public static void main(String [] args){
		SRMCards srmCards = new SRMCards();
		int[] cards = {10, 11, 12, 13, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.print("Max Turns:"+srmCards.maxTurns(cards));
	}
	
	public int maxTurns(int [] cards){
		//Sort the cards
		 Arrays.sort(cards);
		
		 HashMap<Integer,Boolean> cardMap = new HashMap<Integer,Boolean>();
		 for(int i=0;i<cards.length;i++){
			 cardMap.put(cards[i],true);
		 }
		 int maxTurns = calculateMaxTurns(cardMap);
		 return maxTurns;
	}
	
	private int calculateMaxTurns(HashMap<Integer,Boolean>cardMap){
		//Pick the most efficient card
		int efficientCard = mostEfficientCard(cardMap);
		int maxTurns = 0;
		cardMap.put(efficientCard, false);
		if(cardMap.containsKey(efficientCard-1)){
			cardMap.put(efficientCard-1, false);
		}
		if(cardMap.containsKey(efficientCard +1)){
			cardMap.put(efficientCard+1, false);
		}
		boolean pileEmpty=false;
		for(boolean eligible : cardMap.values()){
			if(eligible == true){
				pileEmpty = false;
				break;
			}
				
			pileEmpty = true;
		}
		if(pileEmpty){
			return 1;
		}
		maxTurns = calculateMaxTurns(cardMap);
		return maxTurns + 1;
		
	}
	
	private int mostEfficientCard(HashMap<Integer,Boolean>cardMap){
		int efficientCard = 0;
		int minNeighbours = 3;
		for(Entry<Integer, Boolean> card : cardMap.entrySet()){
			int neighbours = 0;
			//Check if the card is still eligible
			if(card.getValue()){
				//CheckFor left neighbour
				if(cardMap.containsKey(card.getKey()-1)){
					neighbours++;
				}
				//Check for right neighbour
				if(cardMap.containsKey(card.getKey()+1)){
					neighbours++;
				}
				if(neighbours < minNeighbours){
					minNeighbours = neighbours;
					efficientCard = card.getKey();
				}
			}
		}
		return efficientCard;
	}
}
