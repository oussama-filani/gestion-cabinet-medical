# 🏥 Système de Gestion de Consultations Médicales

## 🎯 Objectif
Ce projet implémente un **système de gestion des consultations médicales** permettant de gérer les patients et leurs consultations dans un cabinet médical. Le système est développé en Java, avec **JavaFX** pour l'interface graphique et **MySQL** pour le stockage des données.

Les principales fonctionnalités incluent :
- Gestion des patients
- Gestion des consultations
- Architecture en couches pour une meilleure organisation du code

---

## 🛠️ Fonctionnalités

### ✅ 1. Gestion des Patients
Le système permet de gérer les informations des patients avec les fonctionnalités suivantes :
- **Ajout** de nouveaux patients
- **Modification** des informations des patients existants
- **Suppression** de patients
- **Visualisation** de la liste des patients

### ✅ 2. Gestion des Consultations
Le système facilite la gestion des consultations médicales avec :
- **Planification** de nouvelles consultations
- **Association** d'une consultation à un patient
- **Modification** des détails d'une consultation
- **Suppression** de consultations
- **Visualisation** de l'historique des consultations

---

## 🏗️ Architecture du Système

Le système suit une **architecture en couches (MVC)**, offrant une séparation claire des responsabilités pour faciliter la gestion et l’évolution du projet.

### Couche Modèle (Entités)
- **Patient** : Stocke les informations personnelles des patients (nom, prénom, téléphone, etc.)
- **Consultation** : Contient les détails d’une consultation (date, description, médecin, etc.)

### Couche DAO (Data Access Object)
- **PatientDao** : Gère l’accès aux données des patients en base de données
- **ConsultationDao** : Gère l’accès aux données des consultations en base de données

### Couche Service
- **CabinetService** : Coordonne les opérations métier et l'accès aux DAO, en assurant la logique métier du système

### Couche Présentation
- **Vues** : Interfaces utilisateur (PatientView, ConsultationView, MainView)
- **Contrôleurs** : Gèrent les interactions avec l'utilisateur (PatientController, ConsultationController)

---

## 🖥️ Interface Utilisateur

### Onglet Patients
L’interface permet de gérer les informations des patients à travers :
- Un formulaire pour **ajouter un nouveau patient** (nom, prénom, téléphone)
- La possibilité de **modifier les informations** d’un patient existant
- Un bouton pour **supprimer un patient**
- Une liste affichant tous les patients pour faciliter la gestion

![Capture d'écran de l'application](./images/Capture1.PNG)

### Onglet Consultations
L’interface de gestion des consultations permet de :
- **Sélectionner une date** pour la consultation
- **Associer un patient** à la consultation à l’aide d’une liste déroulante
- **Ajouter une description détaillée** de la consultation
- **Enregistrer une nouvelle consultation** en cliquant sur "Ajouter"
- **Modifier les détails d’une consultation** existante
- **Supprimer une consultation**

![Capture d'écran de l'application](./images/Capture2.PNG)

---

## 🏁 Conclusion
Ce projet permet de gérer efficacement les consultations médicales à travers une architecture modulaire et une interface utilisateur intuitive. Il offre une solution complète pour gérer les **patients** et les **consultations** dans un cabinet médical, tout en étant flexible et évolutif grâce à l'utilisation de l'architecture en couches (MVC).
