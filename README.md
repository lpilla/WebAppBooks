
# PillaWebAppBooks - Progetto di Esame

## Descrizione del Progetto
Il progetto PillaWebAppBooks è una web application sviluppata con Spring Boot e Thymeleaf per la gestione di libri. Gli utenti possono visualizzare, aggiungere, modificare ed eliminare libri dal proprio catalogo. Inoltre, è implementata una funzionalità di sincronizzazione che ottiene dati dai servizi Google Books API.

## Prerequisiti
- Java 11 o versione successiva
- Maven
- Un ambiente di sviluppo integrato (IDE) come IntelliJ o Eclipse
- Connessione Internet per la sincronizzazione con Google Books API

## Configurazione e Installazione
1. Clona il repository da GitHub.
   ```
   git clone https://github.com/lpilla/WebAppBooks.git
   ```
2. Importa il progetto nel tuo IDE.
3. Configura le impostazioni del database nel file `application.properties`.
4. Avvia l'applicazione eseguendo la classe `PillaWebAppBooksApplication`.

## Utilizzo
1. Accedi all'applicazione visitando [http://localhost:8080](http://localhost:8080).
2. Registrati o effettua l'accesso se sei già un utente.
3. Naviga tra le varie sezioni dell'applicazione:
   - **Libri**: Visualizza e gestisci i tuoi libri.
   - **Aggiungi Libro**: Aggiungi nuovi libri al tuo catalogo.
   - **Sincronizza**: Ottieni dati aggiornati da Google Books API.

## Endpoints

- **GET /books**: Visualizza i libri dell'utente autenticato.
- **GET /books/allbooks**: Visualizza tutti i libri nel sistema.
- **GET /books/add**: Pagina per aggiungere un nuovo libro.
- **POST /books/add**: Aggiungi un nuovo libro.
- **GET /books/delete/{id}**: Elimina un libro specifico.
- **GET /books/delete/all**: Elimina tutti i libri.
- **GET /books/edit/{id}**: Pagina per modificare un libro.
- **POST /books/edit**: Modifica un libro esistente.
- **GET /books/sincronizza**: Sincronizza con Google Books API.

## Gestione degli Utenti

Il progetto PillaWebAppBooks include un sistema di gestione degli utenti per autenticare gli utenti e mantenere le informazioni dell'utente.

### Login
L'utente può effettuare l'accesso utilizzando la pagina di login disponibile all'endpoint `/user/signin`. Se l'utente è già autenticato, viene reindirizzato alla pagina principale dei libri.

```java
@RequestMapping("/signin")
public String showLoginPage(HttpSession session) {
    // ...
}
```

### Logout
L'utente può effettuare il logout tramite l'endpoint `/user/logout`. Questo termina la sessione corrente.

```java
@RequestMapping("/logout")
public String logout(HttpSession session) {
    // ...
}
```

### Registrazione
Gli utenti possono registrarsi utilizzando la pagina di registrazione disponibile all'endpoint `/user/signup`. I dati vengono validati e, se corretti, l'utente viene registrato e automaticamente autenticato.

```java
@RequestMapping(value = "/signup")
public String signupPage(User user) {
    // ...
}

@PostMapping("/signup")
public String postSignup(@Valid User user, BindingResult bindingResult, Model model, HttpSession session){
    // ...
}
```

### Visualizzazione Utenti
Gli utenti possono visualizzare la lista completa degli utenti o dettagli specifici di un utente utilizzando gli endpoint `/user/allusers` e `/user/{id}`, rispettivamente.

```java
@RequestMapping("/allusers")
public String getAllUsers(Model model) {
    // ...
}

@RequestMapping("/{id}")
public String getUser(@PathVariable("id") Long id, Model model) {
    // ...
}
```

## Gestione dei Dati

Il progetto utilizza un sistema di persistenza dei dati mediante l'interfaccia `BookDao`, che estende `CrudRepository` di Spring Data. Di seguito sono elencati i principali metodi forniti da questa interfaccia:

### Ricerca per Titolo e Descrizione

Gli utenti possono cercare libri specifici utilizzando i metodi `findByTitle` e `findByDescription`. Questi metodi restituiscono il libro corrispondente al titolo o alla descrizione forniti.

```java
public Book findByTitle(String title);
public Book findByDescription(String description);
```

### Ricerca per ID

Il metodo `findById` consente agli utenti di recuperare un libro specifico fornendo l'ID corrispondente.

```java
public Optional<Book> findById(Long id);
```

### Ricerca per Utente

Il metodo `findByUser` restituisce un elenco di libri associati a un utente specifico.

```java
public Iterable<Book> findByUser(User user);
```

### Operazioni CRUD di Base

L'interfaccia `CrudRepository` fornisce le operazioni CRUD di base come `save`, `deleteById`, e `findAll`. Queste operazioni consentono agli utenti di gestire il proprio catalogo di libri.

```java
public interface BookDao extends CrudRepository<Book, Long> {
    // ...
}
```

## Gestione Utenti nel Database

La gestione degli utenti nel progetto PillaWebAppBooks è affidata all'interfaccia `UserDao`, che estende `CrudRepository` di Spring Data. Questa interfaccia fornisce un metodo personalizzato per l'operazione di login attraverso l'annotazione `@Query`.

### Operazione di Login

Il metodo `login` in `UserDao` consente agli utenti di effettuare l'accesso fornendo il loro nome utente e la password. Questa operazione di login è implementata attraverso una query personalizzata con l'uso di `@Query` e restituisce un oggetto `User` corrispondente alle credenziali fornite.

```java
@Query("select s from User s where username= :username and password = :password")
public User login(String username, String password);
```

### Operazioni CRUD di Base

L'interfaccia `CrudRepository` fornisce operazioni CRUD di base come `save`, `deleteById` e `findAll`. Queste operazioni consentono di gestire gli utenti nel database.

```java
public interface UserDao extends CrudRepository<User, Long> {
    // ...
}
```

## Dipendenze

Il progetto ha le seguenti dipendenze principali:

- [Spring Boot](https://spring.io/projects)

- [Thymeleaf](https://www.thymeleaf.org/)
- [Google Gson](https://github.com/google/gson)
- [H2 Database](https://www.h2database.com/)
- [MySQL Connector Java](https://dev.mysql.com/downloads/connector/j/)
- [JUnit Jupiter](https://junit.org/junit5/)
- [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson)

Assicurati di eseguire un aggiornamento delle dipendenze Maven nel tuo IDE o di utilizzare il comando Maven `mvn clean install` per applicare le modifiche.

## Licenza
Questo progetto è distribuito con la licenza [MIT License](LICENSE).
