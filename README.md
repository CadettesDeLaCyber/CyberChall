# Sensibilisation Scolaire Les Cadettes de la Cyber - Application Web

## Description
Cette application web vise à sensibiliser les élèves à une thématique particulière (ex: cyber-sécurité, protection des données). Elle est développée en utilisant **Spring Boot**, **Thymeleaf**, et **GitHub** pour la gestion du code source.

## Technologies Utilisées
- **Spring Boot** (Back-end)
- **Thymeleaf** (Templates HTML)
- **Spring Data JPA** (Base de données relationnelle)
- **MySql** (Base de données en mémoire pour le développement)
- **GitHub** (Gestion du code source)

## Installation et Exécution
### Prérequis
- Java 17+
- Maven
- IDE Eclipse

### Cloner le projet
```bash
git clone https://github.com/CadettesDeLaCyber/CyberChall.git
cd CyberChall
```

### Lancer l'application
```bash
mvn spring-boot:run
```
L'application sera accessible sur [http://localhost:4040](http://localhost:4040).

## Structure du Projet
```
src/
│── main/java/com/tonprojet/
│   │── controllers/       # Gestion des requêtes HTTP
│   │── services/          # Logique métier
│   │── repositories/      # Accès à la base de données
│   │── models/            # Modèles de données (JPA)
│   └── CyberChall.java  # Point d'entrée
│
└── main/resources/templates/  # HTML (Thymeleaf)
    ├── index.html
    ├── accueil-temporaire.html
```

## Exemples de Fonctionnalités
- Affichage dynamique des modules (thématiques de sensibilisation à la cybersécurité)
- Quiz et challenges interactifs avec les élèves
- Cours
- Statistiques

## Contribuer
1. Forker le projet
2. Créer une branche: `git checkout -b feature-nouvelle-fonctionnalite`
3. Committer les changements: `git commit -m 'Ajout d'une nouvelle fonctionnalité'`
4. Pousser la branche: `git push origin feature-nouvelle-fonctionnalite`
5. Ouvrir une Pull Request

## Auteurs

- **Direction programme : Charlotte Wojcik et l'Amiral Coustillère**
- **Cheffe de projet : Mathilde Douillard**
- **Conception : Hanäe Lopez, Joseline Youego, Margaux Richard et Solène Lemonnier**
- **Développement : Aicha Mimouni**

## Licence
Ce projet est sous licence Les Cadettes de la Cyber - Pôle d'excellence Cyber - Rennes.

## Contact

contact@pole-excellence-cyber.org

