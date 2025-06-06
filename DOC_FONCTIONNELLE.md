# 📄 Documentation Fonctionnelle de l’Application Web **CyberChall**

## ✅ Sommaire

1. [Introduction](#1-🎯-introduction)  
2. [Objectifs de l’Application](#2-🎯-objectifs-de-lapplication)  
3. [Fonctionnalités Principales](#3-🧩-fonctionnalités-principales)  
   - [3.1 Modules de Sensibilisation](#31-📚-modules-de-sensibilisation)  
   - [3.2 Gestion des Sessions](#32-🧭-gestion-des-sessions)  
     - [3.2.1 Sessions Temporaires via QR Code](#321-sessions-temporaires-via-qr-code)  
   - [3.3 Résultats et Suivi de Progression](#33-📊-résultats-et-suivi-de-progression)  
   - [3.4 Gestion des Utilisateurs](#34-👥-gestion-des-utilisateurs)  
   - [3.5 Authentification et Sécurité](#35-🔐-authentification-et-sécurité)  
   - [3.6 Interface Utilisateur (UI)](#36-🎨-interface-utilisateur-ui)  
4. [Parcours Utilisateur](#4-👣-parcours-utilisateur)  
5. [Technologies Utilisées](#5-🛠-technologies-utilisées)  
6. [Évolutions Possibles](#6-🚀-évolutions-possibles)  
7. [Conclusion](#7-🧩-conclusion)

---

## 1. 🎯 Introduction

**CyberChall** est une application web pédagogique développée pour initier les collégiens et lycéens aux bonnes pratiques en cybersécurité.  
Elle combine des contenus théoriques, des quiz et des challenges interactifs.

Développée avec **Spring Boot** et **Thymeleaf**, elle permet de suivre la progression des utilisateurs et de gérer les sessions pédagogiques.

---

## 2. 🎯 Objectifs de l’Application

- Sensibiliser les élèves aux enjeux de la cybersécurité.  
- Évaluer leurs connaissances via des modules interactifs.  
- Suivre leur progression grâce à des outils de reporting simples.  
- Fournir aux enseignants une interface de gestion intuitive.  

---

## 3. 🧩 Fonctionnalités Principales

### 3.1 📚 Modules de Sensibilisation

Chaque module comprend :  
- ✅ Du contenu explicatif sur les risques numériques *(V1)*  
- ❓ Un quiz à choix multiples *(V1)*  
- 🔐 Un challenge ou mini-simulation *(V2)*  

**Exemples de modules :**
- Protection des données  
- Réseaux sociaux  
- Sécurité des appareils  
- Cyberattaques courantes  

---

### 3.2 🧭 Gestion des Sessions

- Création de sessions (date, thématiques sélectionnées, QrCode)  
- Liste des sessions disponibles dans un dashboard  
- Participation à une session existante  
- Durée de vie d'une session : 1 mois  

#### 🔧 Diagramme UML - Architecture des Entités

```mermaid
classDiagram
    class Admin {
        - Long id
        - String username
        - String password
        + void createSession()
        + List~Session~ viewSessions()
        + void deleteSession()
    }

    class Session {
        - Long id
        - String token
        - Date startDate
        - Int durationInDays
        - Long admin_id
        + void addSubModules()
        + List~SousModule~ getSousModules()
    }

    class Module {
        - Long id
        - String name
        + List~SousModule~ getSousModules()
    }

    class SousModule {
        - Long id
        - String title
        - String type
        - Long session_id
        - Long module_id
        - Long course_id
        - Long challenge_id
        + void addQuiz()
        + void addChallenge()
        + Cours getCours()
        + Challenge getChallenge()
        + Quizz getQuizz()
    }

    class Quizz {
        - Long id
        - String questions
        - Long sousmodule_id
    }

    class Challenge {
        - Long id
        - String description
        - Long sousmodule_id
    }

    class Cours {
        - Long id
        - String content
        - Long sousmodule_id
    }

    Admin "1" --> "many" Session : crée
    Session "1" --> "2..4" SousModule : contient
    SousModule --> Module : appartient à
    SousModule --> Quizz : contient
    SousModule --> Challenge : contient
    SousModule --> Cours : lié à

    ---

## 4. 👣 Parcours Utilisateur

### Élève :
1. Arrive sur `/` → page de connexion.
2. Se connecte → redirection vers `accueil_admin`.
3. Accède aux modules, répond aux quiz/challenges.
4. Visualise ses résultats.

### Administrateur :
1. Connexion comme un utilisateur classique.
2. Accès à des vues supplémentaires :
   - Création de session
   - Liste des sessions actives
   - Vue sur les performances des utilisateurs

---

## 5. 🛠 Technologies Utilisées

| Composant        | Technologie        |
|------------------|--------------------|
| Backend          | Spring Boot (Java) |
| Frontend         | Thymeleaf, HTML/CSS |
| Authentification | Cookie + Session    |
| Build            | Maven              |
| Base de données  | (À implémenter, version actuelle utilise une mémoire temporaire) |

---

## 6. 🚀 Évolutions Possibles

- Système d’inscription avec hachage des mots de passe
- Export des résultats (CSV, PDF)
- Interface d’administration dédiée
- Niveaux de difficulté par module
- Gamification (badges, niveaux, timer)

---

## 7. 🧩 Conclusion

**CyberChall** est un outil éducatif moderne, conçu pour éveiller les jeunes aux enjeux de la cybersécurité.  
Avec son interface simple et ses contenus ludiques, elle favorise une prise de conscience numérique tout en rendant l'apprentissage attractif.

---

> 🛠 *Projet open-source maintenu dans un but pédagogique. Pour toute suggestion, merci de proposer une issue ou un pull request.*

