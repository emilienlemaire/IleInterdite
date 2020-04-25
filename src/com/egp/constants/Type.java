package com.egp.constants;

public enum Type {
    Normale {
        public String toString() {
            return "Normale";
        }
    },
    Spawn{
        public String toString() {
            return "Spawn";
        }
    },
    Heliport {
        public String toString() {
            return "Héliport";
        }
    },
    Air {
        public String toString() {
            return "Air";
        }
    },
    Terre {
        public String toString() {
            return "Terre";
        }
    },
    Eau{
        public String toString() {
            return "Eau";
        }
    },
    Feu{
        public String toString() {
            return "Feu";
        }
    }
}
