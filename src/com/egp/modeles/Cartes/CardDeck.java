package com.egp.modeles.Cartes;

import com.egp.modeles.Events.Event;
import com.egp.modeles.Zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardDeck {

    private ArrayList<Card> cards;
    private final ArrayList<Card> trash;

    public CardDeck(ArrayList<Zone> list){
        this.cards = new ArrayList<>();
        this.trash = new ArrayList<>();

        for (Zone z : list){
            this.cards.add(new Card(z));
        }
        this.melanger();
    }

    public CardDeck(ArrayList<Event> keys, Event[] events, double dropKey){
        this.cards = new ArrayList<>();
        this.trash = new ArrayList<>();

        for (Event key : keys){
            this.cards.add(new Card(key));
        }

        for(int i = this.cards.size(); i<Math.round(keys.size()/dropKey); i++){
            int r = new Random().nextInt(events.length);
            this.cards.add(new Card(events[r]));
        }

        this.melanger();

    }

    public void melanger() { Collections.shuffle(this.cards); }

    public Card tirer(){
        Card c = this.cards.get(0);
        jeter(c);
        return c;
    }

    public void jeter(Card c){
        this.cards.remove(c);
        this.trash.add(c);
        if (this.cards.isEmpty()) {
            melangeTrash();
            placeTrash();
        }
    }

    public void melangeTrash() { Collections.shuffle(this.trash); }

    public void placeTrash() {
        this.trash.addAll(this.cards);
        this.cards = new ArrayList<>(this.trash);
        this.trash.clear();
    }

    public void retire(Card c) {
        if (!this.trash.remove(c))
            this.cards.remove(c);
    }

    public String toString(){
        StringBuilder msg = new StringBuilder("Paquet :\n");
        for (Card c : this.cards){
            msg.append(c.getObject().toString()).append("\n");
        }
        msg.append("\nDéfausse :\n");
        for (Card c : this.trash){
            msg.append(c.getObject().toString()).append("\n");
        }
        return msg.toString();
    }
}

