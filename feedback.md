# Feedback: Dictionary med Åben Adressering

Godt arbejde med din aflevering, Thomas! Du har fat i de grundlæggende principper i linear probing, herunder brugen af en `DELETED` sentinel. Der er dog et par kritiske logiske udfordringer, du bør kigge på.

## Fokusområder og observationer

### 1. Logikfejl i `put` ved `DELETED` markører
I din `put` metode stopper dit `while`-loop, så snart det møder en `DELETED` plads:
```java
while (table[index] != null && table[index] != DELETED) { ... }
```
**Hvorfor er det et problem?** 
Hvis du prøver at opdatere værdien for en nøgle, der ligger *efter* en `DELETED` markør i søgestien, vil din `put` metode stoppe ved markøren og indsætte nøglen som en ny indgang. Du ender altså med at have den samme nøgle to steder i din tabel, hvilket bryder Dictionary-kontrakten.

**Løsning:** Du skal fortsætte søgningen indtil du møder `null` for at være helt sikker på, om nøglen findes i tabellen. Hvis du vil være effektiv og genbruge `DELETED` pladsen, kan du gemme indekset på den første slettede plads, du møder, men du må ikke stoppe søgningen efter den eksisterende nøgle endnu.

### 2. Hash-funktionen og negative tal
Du bruger `Math.abs(key.hashCode()) % table.length`. 
**Vær opmærksom på:** I Java returnerer `Math.abs(Integer.MIN_VALUE)` faktisk et negativt tal! Dette vil kaste en `ArrayIndexOutOfBoundsException`.
**Bedre løsning:** En mere robust måde at håndtere det på er: `(key.hashCode() & 0x7fffffff) % table.length`.

### 3. Fuld tabel og uendelig løkke
Din kode tjekker ikke, om tabellen er 100% fuld. Da din stop-betingelse er `table[index] != null`, vil metoderne gå i en uendelig løkke, hvis arrayet bliver fyldt helt op med data og `DELETED` markører.

### 4. Dynamisk Resizing
Du har en fast kapacitet på 16. I en rigtig applikation ville man implementere en `rehash()` metode, der udvider tabellen, når den er f.eks. halvfuld. Dette ville også løse problemet med den uendelige løkke.

## Opsummering
*   **Datastruktur:** Korrekt brug af generisk array og `Entry` objekt.
*   **Linear Probing:** God forståelse af modulo-regning til cirkulær gennemgang.
*   **Sletning:** Rigtig tankegang med `DELETED` markøren for ikke at bryde søgestien.

Flot struktur i din kode og god overskuelig README!
