MATEI Alexandru-Petrut, 325CB           
				----------- TEMA 1 - Imbarcarea pasagerilor in avion -----------

Precizez ca nu am scris javadoc-ul in fisierele .java pe care le-am trimis pe vmchecker in arhiva. Le scrisesem in alte clase cu 
continut identic ca in acestea si cu aceeasi functionalitate pentru a le pastra pe acestea trimise cu rol de copii ale 
lor pentru orice eventualitate. Din greseala am sters fisierele .java unde am scris javadoc-ul, dar am avut o copie a javadoc-ului
generat de proiectul in care se aflau acestea. Sper ca e in regula, nu am mai stat sa rescriu tot javadoc-ul, considerand ca
daca el exista in arhiva inseamna ca e ok. Este cel aferent claselor din arhiva.

Pentru realizarea temei am creat 8 clase de baza: Pasager, Category, Single, Group, Family, Creator, PriorityQueueVec
, Test si o interfata Ticket implementata de alte 3 clase: Business, Economic si Premium.
 O sa precizez utilitatea fiecarei clase in rezolvarea temei:

- Clasa Pasager este o clasa publica care contine:
		- campurile pe care le atribui pasagerilor - id-ul, numele, varsta,tipul de bilet, tipul de prioritate 
		(prioritara sau neprioritara), precizarea daca au nevoi speciale, referinta la categoria din care 
		fac parte, acestea fiind private, alegere pe care am facut-o pentru ca datele pasagerilor sa
		ramana "confidentiale" ( sa nu fie accesate de oricine )

		- constructorul public Pasager care are ca parametri toate campurile clasei Pasager, pe care le 
		seteaza la declarare.

		- 3 metode getter fara parametri care intorc id-ul categoriei din care fac parte pasagerii, numele
		pasagerilor si referinta la categoria din care fac parte pasagerii, si anume metodele publice
		getCategoryId() si getName() care returneaza un string si getCategoryReference() care returneaza
		o instanta de tip Category ( una din entitatile singur, grup si familie ).

		- o metoda publica calculatePersonalCost() fara parametri care calculeaza prioritatea unui pasager
		pe care o intoarce ca o valoare de tip int prin intermediul variabilei cost initializate cu 0 la
		inceputul body-ului sau pe baza punctelor asociate fiecarui tip de persoana, bilet sau beneficiu, 
		specificate in cerinta problemei. Se foloseste in cadrul ei o referinta la metoda getTicketType() 
		implementata in interfata Ticket si care returneaza un string cu tipul de bilet, aceasta metoda
		fiind suprascrisa in cele 3 clase Business, Economic si Premium care implementeaza interfata.
		
		-o metoda publica fara parametri toString() care suprascrie metoda toString() din clasa Object si 
		care intoarce un String prin concatenarea id-ului, numelui, varstei si a tipului de bilet.

- Clasa Category este o clasa abstracta care arata conceptele de polimorfism si abstractizare, reprezentand in acelasi 
timp si clasa parinte a claselor Single, Group si Family. Ea contine:
		- un camp de tip String care retine id-ul aferent categoriei 
		- constructorul public Category de baza al clasei cu un parametru care este id-ul corespondent
		categoriei respective pe care il seteaza la declarare.
		- o metoda getter fara parametri getCategoryId() care intoarce id-ul pasagerului.
		- 5 metode abstracte care urmeaza a fi implementate/suprascrise in fiecare clasa care mosteneste clasa
		Category; ele au urmatoarele functionalitati: addPassenger - adauga pasageri in structura corespunzatoare
		tipului de categorie, removePassenger - sterge pasageri din structura respectiva, isEmpty - imi spune 
		daca structura respectiva e goala, getPassengers - intoarce un vector cu toti pasagerii din grupul
		respectiv si getCategoryCost - intoarce costul/nr de puncte de prioritate corespondent categoriei
		( 0 pentru Single, 5 pentru Group si 10 pentru Family )

- Clasa Single este o clasa publica care mosteneste clasa Category. O categorie de pasageri de tip Single se diferentiaza
fata de celelalte 2 tipuri ( Group si Family ), in sensul ca astfel de structura poate contine un singur pasager
astfel ca la apelul de add si remove doar se va seta variabila passenger la o referinta catre un pasager (in cazul add) sau
la null (in cazul remove), astfel ca este necesara verificarea isEmpty care verifica, deci, aceasta referinta, mai precis
daca aceasta este null sau nu. Metodele add si remove care sunt folosite, deci, in acest context apartin clasei 
Java.util.Vector, pe care am folosit-o in cadrul tuturor claselor in care am avut nevoie de crearea unui vector de pasageri,
inclusiv in clasa in care am implementat coada de prioritati. Aceasta clasa contine deci:
		- un camp privat passenger de tip Pasager 
		- un constructor cu parametru care este id-ul pasagerului, acesta facand apel la superclasa prin apelul cu
		super(categoryId).
		- metodele suprascrise din clasa parinte despre care am vorbit la clasa Category; de asemenea, am ales ca
		apelul metodei getPassengers sa intoarca un vector cu un singur element pentru a pastra generalitatea
		de care aceasta clasa beneficiaza in restul codului( cele 2 clase Group si Family intorc evident un vector 
		cu 2 sau mai multi pasageri, deci am dorit ca cele 3 clase sa fie usor interschimbabile ).
		- metoda toString() care, de asemenea, suprascrie metoda din clasa Object si care intoarce string-ul aferent
		structurii de tip Single prin intermediul lui passenger 

- Clasa Group este o clasa publica care mosteneste clasa Category. O categorie de pasageri de tip Group va stoca pasageri 
intr-un vector pe care il intoarce la apelul getPassengers() si in care se vor adauga pasageri / din care se vor sterge 
pasageri la apelul metodei addPassenger / removePassenger. Restul metodelor au fost descrise anterior. Ea contine, deci:
		- un camp privat de tip vector de pasageri, folosind clasa Java.util.Vector
		- un constructor asemanator celui din Single care de aceasta data trebuie sa si instantieze vectorul de 
		pasageri
		- metodele suprascrise din clasa parinte despre care am vorbit: addPassenger foloseste metoda add pentru
		a adauga in vector pasagerul p, iar metoda removePassenger foloseste metoda remove pentru a sterge din vector
		pasagerul p; de asemenea, si metoda isEmpty() foloseste metoda predefinita isEmpty() din java.util.Vector
		pentru a verifica daca entitatea e goala, iar metodele getCategoryCost() si toString() sunt folosite in
		acelasi scop ca in clasa Single.

- Clasa Family este o clasa publica care mosteneste clasa Category. O categorie de pasageri de tip Family va stoca pasageri 
intr-un vector pe care il intoarce la apelul getPassengers() si in care se vor adauga pasageri / din care se vor sterge
pasageri la apelul metodei addPassenger / removePassenger. Restul metodelor au fost descrise anterior in cadrul claselor Group
si Single. Are acelasi continut ca si clasa Group, cu modificari la nivel de continut al metodei getCategoryCost().

- Interfata Ticket a fost creata cu scopul de a exemplifica principiul de polimorfism. Cele 3 clase - Business, Economic si
Premium implementeaza aceasta interfata si fiecare contine metoda getTicketType() care intoarce tipul de bilet corespunzator
ca string : "Business" pentru clasa Business etc. Aceasta metoda e suprascrisa din interfata Ticket, fiind si singura metoda
continuta de aceasta.

- Clasa PriorityQueueVec este o clasa publica care implementeaza coada de prioritati folosind un heap reprezentat sub forma unui
vector, deci folosesc din nou clasa java.util.Vector. Am folosit, de asemenea, clasa java.util.Collections pentru a folosi metoda 
statica swap pe care o contine, aceasta schimband elementele la pozitiile specificate in lista specificata, care in cazul meu este
queue, un vector care are acces la 3 campuri private: prioritatea, categoria si id-ul pasagerilor. Metoda Collections.swap 
interschimba efectiv 2 elemente ca in C, folosita evident pentru eficienta. Clasa contine, deci:
		- o alta clasa interna - QueueEntry - care este o clasa privata ce contine: 
				- campurile prioritate, categorie si id-ul categoriei ale pasagerilor
				- un constructor cu 2 parametri, la apelul caruia se creeaza o copie goala a categoriei
				inserate in vector, dupa care se seteaza prioritatea
				- o metoda addPassenger care adauga pasagerul in categoria aferenta nodului (initial goala)
				si care odata cu aceasta adaugare, creste prioritatea cu nr de puncte asociat pasagerului
				- metoda toString() suprascrisa din clasa Object care intoarce id-ul categoriei
		Am implementat aceasta clasa interna cu scopul de a modela o entitate ce reprezinta un nod din heap. Am ales sa
		fie interna pentru ca e folosita doar in contextul cozii. Se retine astfel intr-un nod al heap-ului prioritatea
		nodului si un element de tip Category si categoryID-ul acestuia pentur acces mai usor.
		- campul privat queue - vector prin care este reprezentat heap-ul 
		- campul privat size - dimensiunea cozii 
		- un constructor fara parametri care initializeaza vectorul queue, setand dimensiunea cozii la 0
		- metoda insertCat cu un parametru care functioneaza astfel: inserarea unei categorii seteaza mai intai nodul
		la prioritatea categoriei de pasageri, la care adauga prioritatea fiecarui pasager in parte odata cu adaugarea
		fiecarui pasager, dupa care se realizeaza o operatie de heapify_up, care urca nodul nou inserat la pozitia lui
		corespunzatoare.
		- metoda insert care este implementata conform cerintei, cu 2 parametri - Pasager p si int priority : este metoda
		de inserare a unui pasager si cauta categoria pasagerului in vector; daca aceasta exista, il introduce la 
		pozitia corespunzatoare; in caz contrar, creeaza o noua intrare, in coada de prioritati, aferenta categoriei
		pasagerului la ultima pozitie disponibila si adauga pasagerul la nodul nou creat iar apoi realizeaza operatia		
		de heapify_up.
		- metoda heapify_up cu un parametru de tip int care este o metoda recursiva ce ajuta la mutarea unui nod la
		pozitia sa corespunzatoare in sus, deci daca nodul este nou adaugat si se afla pe randul cel mai departat de
		radacina, va fi dus cat mai sus posibil in arbore, in cazul in care prioritatea lui este mai mare decat a 
		parintelui. 
		- metoda heapify_down cu un parametru de tip int care este o metoda recursiva ce ajuta la mutarea unui nod la
		pozitia corespunzatoare in jos, deci daca nodul se afla intr-o pozitie din partea superioara a arborelui(cat mai
		aproape de root), dar prioritatea lui este mai mica decat a unuia dintre copii, acesta va fi mutat in jos prin
		interschimbari cu copiii acestuia. 
		- metoda delete ceruta cu un parametru de tip Pasager, care permite stergerea unui pasager din queue astfel:
		se cauta categoria pasagerului in queue si daca este gasit se merge la pasul urmator iar daca nu este gasit 
		executia se termina; pasul urmator este ca in nodul corespunzator categoriei pasagerului este cautata instanta
		pasagerului p, iar daca este exista, el este sters din structura aferenta categoriei; daca categoria ramane 
		goala, aceasta este stearsa din coada, daca nu, se face heapify_down pentru ca prioritatea nodului a scazut.
		- metoda delete doar ca sterge un nod intreg (o categorie) prin mutarea ei la finalul cozii, iar apoi scaderea
		cozii cu un element, dupa care urmeaza un heapify_down pentru pozitionarea corecta a nodului ce se afla inainte
		la sfarsitul cozii, asemanator cu situatia in care se facea embark
		- metoda embark ceruta fara parametri care reprezinta deci un pop(): elementul cu prioritatea cea mai mare
		este scos din coada, element ce se afla in root(pozitia 0), iar pentru extragerea lui, se va interschimba 
		nodul root cu nodul de la finalul cozii, dupa care se va micsora dimensiunea cozii cu 1, iar apoi elementul
		care a fost pus in root va fi mutat la pozitia corespunzatoare folosind un heapify_down.
		- metoda recursiva preorder - parcurgerea recursiva in preordine a heap-ului
		- metoda list ceruta fara parametri care intoarce afisarea elementelor din heap in preordine folosind metoda 
		preorder descrisa mai sus

- Clasa Creator este o clasa publica folosita la crearea de entitati de tip Pasager, Ticket si Category, pe baza string-urilor
aferente citite din fisierul de input, si folosita, de asemenea, pentru crearea de copii ale unei categorii. Ea contine:
		- metoda statica createPassenger care creeaza o entitate de tip Pasager pe baza informatiilor primite din 
		fisierul de input si a categoriei in care se afla, intorcand entitatea nou creata; folosesc aici clasa
		StringTokenizer care retine aceste informatii cu ajutorul metodelor sale predefinite - countTokens si nextToken
		- metoda statica createBoolean care e folosita in createPassenger si care creeaza o valoarea booleana pe baza
		unui string(converteste string in boolean)
		- metoda statica createTicket care creeaza o entitate Ticket, care desi putea fi un simplu string, am ales sa
		fie facute pe baza unei interfete pentru a demonstra principiile de polimorfism si abstractizare
		- metoda statica createCategory care creeaza categoria din care un pasager face parte pe baza informatiilor pe
		care le primeste de la input (f - familie, g - grup , s - singur) si adauga pasagerul respectiv in acea
		categorie pe care o returneaza.
		- metoda statica createEmptyCategoryInstance care creeaza o copie goala(fara pasageri) a unei instante Category
		pe baza id-ului categoriei, cu rol de nod in coada; si pe aceasta metoda o returneaza.

- Clasa Test este clasa care contine metoda main folosita pentru testare si implementare generala. Ea contine metoda statica
main care implementeaza celelalte clase create, citeste din fisierul de input si afiseaza in fisierul de output; de asemenea,
ea arunca o exceptie checked pentru a fi prinsa ca sa nu perturbe fluxul normal al instructiunilor programului. Aici declar
variabilele de care o sa am nevoie, initializez fisierele, de exemplu in number_of_passengers o sa retin numar de pasageri
gasit pe prima linie a fisierului de input. Folosesc HashMap pentru a retine toti pasagerii intr-un map, clasa File pentru 
crearea fisierelor de input/output, clasele BufferedReader si BufferedWriter pentru a citi/scrie din/in fisiere si clasa
StringTokenizer cu aceeasi functionalitate descrisa in clasa Creator. Declar priority-queue-ul implementat printr-un vector heap
. Toate parsarile sunt puse intr-un try-catch pentru a evita erorile de tipul fisier lipsa/citire neregulamentara. Parsarea
pasagerilor incepe de cand se citesc liniile fisierului. Pentru fiecare pasager, salvez datele in map, in functie de id-ul 
categoriei acestuia. Daca aceasta categorie nu exista, inca o creez si o adaug in map, dupa care noile intrari ale categoriei
respective vor fi puse in aceasta instanta creata. Pentru detalii legate de crearea pasagerilor si a categoriilor se vor
verifica clasele Creator si Category in care toate functionalitatile sunt pe larg explicate. In metoda urmeaza parsarea
instructiunilor. Se va parsa categoria, dar si numele persoanei din categoria respectiva, daca exista. De asemenea, se va
sterge persoana sau categoria la apelul metodei delete. Inchid fisierele dupa toate citirile si parsarile necesare. In blocul
catch se afiseaza si un mesaj de eroare daca parsarea fisierului a esuat.
