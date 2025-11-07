# 🧨 Démineur en Java

Bienvenue dans ce projet de **Démineur** développé en Java, jouable entièrement dans la console. Le jeu reprend les mécaniques classiques du démineur avec une grille, des mines cachées, et la posibilité de poser des drapeaux pour marquer les cases suspectes.

---

### 🎮 Fonctionnalités
- Génération des mines alétoirement
- Révélation des cases avec une affichage du nombre de mines adjacentes
- Découverte en cascade des cases vides
- Système de marquage de cases suspectes
- Interface console avec validation des entrées
- Détection de victoire ou de défaite

---

### 🛠️ Structure du code
- **main()** : la boucle de jeu principale
- **gameGrid()** : la création de la grille
- **addMines()** : placement aléatoire des mines
- **displayGrid()** :affichage formaté de la grille
- **inputValidation()** : vérfification des entrées de l'utilisateur
- **serialDiscovery()** : découvert en cascade (de manière récursive) des cases vide
- **minesCounting()**: comptage des mines adjacentes
- **recalculateEmptyCells()** : calcul des cases restantes à découvrir

---

### 🧾 Règles du jeu
- Le joueur choisit une case à révéler ou à marquer d’un drapeau
- Si une mine est révélée, la partie est perdue (Game over)
- Si toutes les cases vides sont révélées alors la partie est gagnée
- Les drapeaux peuvent être posés ou retirés à volonté, mais ils ne permettent pas de révéler une case

---

### 🧑‍💻 Lancer le jeu
1. Compiler le fichier:

           java fr/ex/demineur/Main.java
2. Exécuter le jeu:

           java fr.ex.demineur.Main

--- 
### 📌 Symboles utilisés

- Case non révélée : -
- Drapeau posé : F
- Mine : X
- Nombre de mines adjacentes : 1-8
   
