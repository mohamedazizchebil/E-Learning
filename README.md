# E-Learning Platform 📚

Une plateforme web de gestion de formations en Java EE. Elle permet aux utilisateurs de s'inscrire, de se connecter, de consulter leurs formations, et aux administrateurs de gérer les formateurs, les formations et les inscriptions.

## 🚀 Fonctionnalités

### 👨‍🎓 Utilisateurs (Apprenants)
- Inscription / Connexion
- Consultation des formations disponibles
- Inscription à une formation
- Visualisation de ses inscriptions


### 👨‍💼 Administrateur
- Gestion des formateurs
- Gestion des formations

## 🧱 Technologies utilisées

- **Java EE (Servlets, JSP)**
- **JPA / Hibernate**
- **MySQL**
- **Apache Tomcat**
- **Maven**

## 🗂️ Structure du projet

src/
├── main/
│ ├── java/
│ │ ├── entities/ # Entités JPA (User, Formation, Formateur)
│ │ ├── dao/ # Accès aux données (DAO)
│ │ └── web/ # Servlets (Login, Register, Admin, etc.)
│ ├── resources/
│ │ └── META-INF/persistence.xml
│ └── webapp/
│ ├── WEB-INF/web.xml # Fichier de configuration
│ └── *.jsp # Pages JSP (login.jsp, admin.jsp, etc.)



## ⚙️ Installation

1. Cloner le projet :
   ```bash
   git clone https://github.com/mohamedazizchebil/E-Learning.git
