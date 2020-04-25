package com.egp.constants;

public enum Etat {
    Normale {
        public String toString() {
            return "Normale";
        }
    },
    Innondee {
        public String toString() {
            return "Inondée";
        }
    },
    Submergee {
        public String toString() {
            return "Submergée";
        }
    }
}
