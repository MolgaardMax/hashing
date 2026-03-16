Dictionary med Åben Adressering

Denne opgave implementerer interfacet Dictionary<K, V> ved hjælp af en hash-tabel med åben adressering.
Elementerne gemmes direkte i et array, og kollisioner håndteres ved hjælp af linear probing.
- Hash funktion
Indekset beregnes med:
Math.abs(key.hashCode()) % table.length
Dette sikrer, at indekset altid er positivt.
- Kollisioner
Hvis en plads allerede er optaget, søges der efter næste ledige plads i tabellen ved hjælp af:
(index + 1) % table.length
- Sletning
Ved sletning bruges en speciel DELETED markør i stedet for null, så søgninger stadig fungerer korrekt.

Implementerede metoder

- put(K key, V value)
- get(K key)
- remove(K key)
- size()
- isEmpty()

Thomas Mølgaard
