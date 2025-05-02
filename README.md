# 🧵 Paralelní programování
Školní projekt z 3 ročníku SPŠE Pardubice předmětu Programování (PG).

## 🗂️ Obsah
- [O projektu](#-o-projektu)
- [Cil projektu](#-cil-projektu)
- [Zadání](#-zadani)
- [Nutné požadavky pro přijetí projektu](#nutne-pozadavky-pro-prijeti-projektu)
- [Technologie](#-technologie)


### 📖 O projektu
Tento projekt je simulator restauračního zasedacího systému v Programování zadaný Liborem Barelem. Je to 3 finální projekt třetího ročníku cílen na naučení se Paralelního programování. GUI bylo zadáno pouze jako výpis v terminálu. Sepsaná teorie o paralením programování je napsána v LibreOffice. Dokumentace k řešení problému je ve figmě.

### ✨ Cil projektu
Cilem bylo naucit se:
- Naučit se základy paralelního programování

### ❗ Zadáni
*"Systémy hromadné obsluhy řeší příchody zákazníků, jejich řazení do fronty a obsluhu na
přepážce. Při tom sledují různé ukazatele a tyto modely tak pomáhají při rozhodování o
konfiguraci front a přepážek pro dosažení optimálního vytížení zdrojů."*

Nasimuluj část systému obsluhy ve veřejné jídelně. V tomto případě se obsloužením
zákazníka chápe obsazení místa u stolu na snědení jídla.

V základu bude takový systém v konfiguraci jedna fronta, více míst obsluhy. Příchody
budou v konstantním intervalu (každé dvě sekundy se objeví nový zákazník), obsloužení
se bude skládat ze tří částí – snědení polévky, snědení hlavního jídla a snědení dezertu.
Snědení polévky bude trvat náhodnou dobu mezi třemi a pěti sekundami, snědení
hlavního jídla bude trvat náhodnou dobu mezi 7 a 20 sekundami. Snědení dezertu bude
trvat náhodnou dobu mezi jednou a třemi sekundami.

Všichni příchozí budou mít hlavní jídlo, 60% zákazníků si vezme i dezert a pouze 40% si dá
i polévku.

Pokud bude v takové jídelně 30 míst, je to akorát, nebo málo, nebo víc, než je potřeba.

Pro výstupy postačí konzole (textové rozhraní), kam se budou logovat příchody a odchody
zákazníků. Po zastavení simulace se vypíše i počet obsloužených zákazníků.

Pro odpověď na tuto otázku je potřeba, aby aplikace sledovala i zvlášť čas strávený ve
frontě a čas obsluhy a procento vytížení jednotlivých míst (součet časů obsluhování všech
zákazníků na daném místě / celkový čas simulace).

<b>Součástí bude i písemná část, kde bude</b>
- zpracovaná teorie o paralelním programování,
- ukázky aplikace teorie na aplikaci,
- popis a zdůvodnění řešení.

#### Varianta 1a
Aplikaci uprav tak, že časy mezi příchody budou generovány podle exponenciálního
rozdělení pravděpodobnosti a časy obsluhy budou generovány podle normálního
rozdělení pravděpodobnosti.
#### Varianta 1b
Aplikaci uprav tak, že v pevných časech přijde počet zákazníků vygenerovaný podle
Poissonova rozdělení pravděpodobnosti a časy obsluhy budou generovány podle
normálního rozdělení pravděpodobnosti.
#### Varianta 2
(může navazovat na V2)
Porovnej statistiky, když se změní konfigurace na více front, více míst obsluhy, kdy každé
místo obsluhy (nebo skupina míst) má svou frontu a příchozí zákazník se řadí do té
aktuálně nejkratší.

### Nutné požadavky pro přijetí projektu
- Přiměřený rozsah a kvalita zpracování
- Autorská práce
- Korektní práce se zdroji informací
- Porozumění zpracovaného tématu

### 🛠️ Technologie
- Java 21
- JetBrains IntelliJ IDEA