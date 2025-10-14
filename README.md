# 🛍️ Online Store with Admin Panel

## 📖 Descriere generală
Această aplicație este un **magazin online** care permite:
- gestionarea produselor și categoriilor printr-un **panou de administrare**;
- **înregistrarea și autentificarea utilizatorilor**;
- **plasarea comenzilor** de către clienți;
- afișarea **prognozei meteo** pentru orașul utilizatorului logat.

Aplicația este construită folosind:
- **Spring Boot** (backend)
- **JPA / Hibernate** (baza de date)
- **Spring Security** (autentificare și roluri)
- **Thymeleaf / Angular / React** (interfața grafică)

---

## ⚙️ Funcționalități principale

### 🔐 Login panel
- Autentificare utilizatori (Admin / User)
- Înregistrare utilizatori noi
- Logout

---

## 👑 Funcționalități ADMIN
1. **Adăugare categorii**
    - nume categorie
    - categorie părinte
2. **Vizualizare arbore categorii**
    - ierarhie „categorie – subcategorie”
    - căutare categorii
    - mutare prin drag & drop
3. **Adăugare produse**
    - nume, descriere, imagine (URL), disponibilitate, preț
    - tip produs (enum)
    - categorie (dropdown)
    - autor (dropdown)
4. **Listă produse**
    - vizualizare toate produsele
    - căutare
    - editare produse

---

## 🙋‍♂️ Funcționalități USER
1. **Înregistrare cont**
    - validare câmpuri (email, parolă, adresă etc.)
2. **Autentificare și Logout**
3. **Widget Meteo**
    - afișează vremea bazată pe orașul utilizatorului
4. **Listă de produse**
    - afișare tip listă / grid
    - paginare
    - sortare și căutare (Ajax)
    - adăugare produs în coș
5. **Coș de cumpărături**
    - vizualizare produse adăugate
    - plasare comandă
    - pagină „Mulțumim pentru comandă”
    - scădere automată stoc

---

## 🧱 Structura aplicației

Aplicația este împărțită în următoarele pachete:

com.meehigh.abcshop
┣ controller/ → conține clasele Controller (AdminController, UserController etc.)
┣ service/ → conține logica de business (UserService, ProductService etc.)
┣ repository/ → acces la baza de date (CategoryRepository, ProductRepository etc.)
┣ model/ → entitățile JPA (User, Product, Order etc.)
┣ dto/ → obiecte de transfer date (UserResponse, RegisterRequest etc.)
┗ security/ → configurări Spring Security

---

## 🧩 Entități principale (modele)

### 🏷️ Category
- `name`
- `parent` (categorie părinte)
- `children` (subcategorii)

### 👤 User
- `email` (login)
- `password` (criptat)
- `city`
- `address` (țară, oraș, stradă, cod poștal)
- `avatar`
- `role` (ADMIN / USER)
- `messageChannel` (poștă / email)

### 📦 Product
- `title`
- `description`
- `imageUrl`
- `category`
- `price`
- `productType` (enum)
- `author`

### 🧾 Order
- `user`
- `totalCost`
- `deliveryAddress`
- `orderDate`
- `orderLines`
- `status` (enum)

### 🧺 Cart
- produse temporare (nu este entitate salvată în DB)

---

## 🔐 Tehnologii folosite
- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA (Hibernate)**
- **MySQL / PostgreSQL**
- **Thymeleaf / Angular / React**
- **Bootstrap / Tailwind / CSS pentru stilizare**

---

## 🧠 Funcționalități extinse (opționale)
- Editarea profilului utilizatorului
- Vizualizarea comenzilor plasate
- Adăugarea autorilor din panoul admin

---

## 📌 Cerințe suplimentare
- Interfață estetică și funcțională
- Validare date înainte de salvare
- Parole criptate (BCrypt)
- Utilizare REST API dacă se folosește Angular / React

---

## 🧭 Pornirea aplicației

1. Clonează proiectul:
   ```bash
   git clone https://github.com/username/online-store.git

2. Deschide proiectul în IntelliJ IDEA.

3. Configurează baza de date în application.properties.

4. Rulează aplicația (Run Application).

5. Accesează:

Panou utilizator: http://localhost:8080

Panou admin: http://localhost:8080/admin

👨‍💻 Autori

Proiect realizat de [Mihai Călugăru, Steluța Dragu, Viorel Gheorghiu]
Cursanti la [Software Develoment Academy ]
📅 Anul 2025

