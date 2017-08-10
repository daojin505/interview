package interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedEnvelope {

	public static void main(String[] args) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < 5; ++i) {
			Card card = new Card();
			card.size = i * 2;
			card.id = i;
			cards.add(card);
		}

		List<Envelop> evelops = new ArrayList<>();

		for (int i = 0; i < 10; ++i) {
			Envelop env = new Envelop();
			env.size = i;
			env.id = i;
			evelops.add(env);
		}

		HashMap<Card, List<Envelop>> results = putInCard(cards, evelops);

		for (Map.Entry<Card, List<Envelop>> entry : results.entrySet()) {
			System.out.println("[");
			System.out.println("card:" + entry.getKey().size);
			for (Envelop env : entry.getValue()) {
				System.out.println("envelop:" + env.size);
			}
			System.out.println("]");
		}
	}

	static class Card implements Comparable<Card> {
		int id;
		int size;

		@Override
		public int compareTo(Card o) {
			// TODO Auto-generated method stub
			return size - o.size;
		}
	}

	static class Envelop implements Comparable<Envelop> {
		int id;
		int size;

		@Override
		public int compareTo(Envelop o) {
			// TODO Auto-generated method stub
			return size - o.size;
		}
	}

	public static HashMap<Card, List<Envelop>> putInCard(List<Card> cards, List<Envelop> envelops) {

		Collections.sort(cards);

		Collections.sort(envelops);
		
		HashMap<Card, List<Envelop>> cardWithEnvelops = new HashMap<Card, List<Envelop>>();
		
		for (int i = 0; i < cards.size(); ++i) {

			Card card = cards.get(i);

			Card nextCard = null;

			if (i + 1 < cards.size()) {
				nextCard = cards.get(i + 1);
			}

			List<Envelop> cardEnvelops = new ArrayList<Envelop>();
			
			cardWithEnvelops.put(card, cardEnvelops);
			
			Envelop lastEnvelop = null;
			
			while (envelops.size() > 0) {
				
				Envelop envelop = envelops.get(0);
				
				if ((lastEnvelop != null && envelop.size == lastEnvelop.size) || envelop.size == card.size) {
					
					for (int indexCard = i - 1; indexCard >= 0; --indexCard) {
						
						List<Envelop> preEnvelops = cardWithEnvelops.get(cards.get(indexCard));
						
						if (preEnvelops.size() == 0) {
							
							preEnvelops.add(envelop);
						
						} else {
							
							Envelop maxEnv = preEnvelops.get(preEnvelops.size() - 1);
							
							if (maxEnv.size < envelop.size) {
								
								preEnvelops.add(envelop);
							}
						}
					}
					
					envelops.remove(envelop);
					
					continue;
				}
				
				if (envelop.size > card.size && (nextCard == null || envelop.size <= nextCard.size)) {
					
					cardEnvelops.add(envelop);
					
					lastEnvelop = envelop;
					
					envelops.remove(envelop);
				
				} else {
					break;
				}
			}
		}
		return cardWithEnvelops;
	}
}