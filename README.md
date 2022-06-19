# siw-taggaecuci
 siw exam - tagga & cuci 

 Server Postgres:
 	db: taggaecuci (localhost)
 	port: 8080

 classi del dominio:
 	1. Maglietta
 	2. Materiale
 	3. Collezione
 	4. Accessorio
  5. Ordine
 	
 Associazioni:
 	Per ogni maglietta è di interesse un nome, descrizione,  i colori disponibili e la lista dei Materiali di cui è composta.
 	Per ogni materiale è di interesse il nome e la descrizione (con eventuali percentuali
 	Ogni collezione è composta da un nome, una stagione e una lista di magliette e accessori
 	Ogni accessorio ha un nome, una tipologia, descrizione e lista dei materiali
 	
 Form:
 	Contattaci: Form che permette al cliente di richiedere un ordine al sistema. gli amministratori potreanno visualizzare
 				tutte le richieste di tutti i clienti. Verranno mostrati i dati dell'utente con i prodotti richiesti
