import java.io.*;
import java.util.*;

/** Classe pour crée l'échiquier et gérer la pluspart des opérations sur celui-çi,
 * cette classe implément Serializable afin de pouvoir enregistrer l'échiquier dans un fichier et pouvoir reprendre une partie à partir du fichier.  */
class NewEchiquier implements Serializable {

  /** Un tableau de tableaux de String  pour l'afffichage de notre échiquier.  */
  private String[][] echiquier = new String[16][16];
  /** Une liste de Lipides pour le joueur1. */
  private ArrayList<Lipides> lipidesJ1 = new ArrayList<Lipides>(30);
  /** Une liste de Lipides pour le joueur2. */
  private ArrayList<Lipides> lipidesJ2 = new ArrayList<Lipides>(30);
  /** Une liste d'Anticorps pour le joueur1. */
  private ArrayList<Anticorps> anticorpsJ1 = new ArrayList<Anticorps>(8);
  /** Une liste d'Anticorps pour le joueur2. */
  private ArrayList<Anticorps> anticorpsJ2 = new ArrayList<Anticorps>(8);
  /** Une liste d'Antigenes. */
  private ArrayList<Antigene> antigenes = new ArrayList<Antigene>(40);
  /** Une variable pour manipuler un Lipide. */
  private Lipides itemLipide = null;
  /** Une variable pour manipuler un Anticorps. */
  private Anticorps itemAnticorps = null;
  /** Une variable pour manipuler un Antigene. */
  private Antigene itemAntigenes = null;
  /** Un booleen de retour pour savoir lorsque l'action que l'on souhaite entreprendre a été effectuée.*/
  private boolean trouve = false;
  /** Un compteur afin de savoir si c'est le tour du Joueur1 ou bien celui du Joueur2. */
  private int tour = 0;
  /** Un compteur de points pour le Joueur1. */
  private int pointsJ1 = 0;
  /** Un compteur de points pour le Joueur2. */
  private int pointsJ2 = 0;

  /** Retourne le compteur tour. */
  public int getTour() {
    return this.tour;
  }

  /** Remplit l'échiquier avec l'affichage d'une case vide. */
  public void creerEchiquier() {
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        this.echiquier[i][j] = Color.CYAN + "{__}" + Color.RESET;
      }
    }
  }

  /** Renvoie l'échiquier. */
  public String[][] renvoyerEchiquier() {
    return this.echiquier;
  }

  /** Affiche l'échiquier.  */
  public void afficherEchiquier() {
    for (int row = 0; row < this.echiquier.length; row++) {
      for (int col = 0; col < this.echiquier[row].length; col++) {
        if (col == 0 && row == 0) { //Coin superieur gauche
          System.out.print("   ");
        } else if (row == 0) { //Premiere ligne
          if (col < 10 & col != 9) {
            System.out.print("  " + (col) + " ");
          } else if (col == 9) {
            System.out.print("  " + (col) + "  ");
          } else if (col == 10) {
            System.out.print("" + (col));
          } else {
            System.out.print("  " + (col));
          }
        } else if (col == 0) { //Premiere colonne
          if (row < 10) {
            System.out.print("  " + (row));
          } else if (row == 9) {
            System.out.print("   " + (row) + "   ");
          } else {
            System.out.print(" " + (row));
          }
        } else { //Tous les autres cas
          System.out.print("" + this.echiquier[row - 1][col - 1]);
        }
      }
      System.out.println();
    }
  }

  /** On place les pièces sur l'échiquier dans leur positionnement de départ. */
  public void placementDepartEchiquier() {
    for (int i = 0; i < this.echiquier.length; i++) {
      for (int j = 0; j < this.echiquier[i].length - 1; j++) {
        if (j % 2 == 0) {
          if (i == 0 || i == 4) {
            itemLipide = new Lipides(i, j);
            itemLipide.setJoueur("J1");
            lipidesJ1.add(itemLipide);
            this.echiquier[i][j] = itemLipide.renvoyerForme();
          }

          if (i == 14 || i == 10) {
            itemLipide = new Lipides(i, j);
            itemLipide.setJoueur("J2");
            lipidesJ2.add(itemLipide);
            this.echiquier[i][j] = itemLipide.renvoyerForme();
          }
        }
        if (j % 2 == 1) {
          if (i == 1 || i == 3) {
            itemLipide = new Lipides(i, j);
            itemLipide.setJoueur("J1");
            lipidesJ1.add(itemLipide);
            this.echiquier[i][j] = itemLipide.renvoyerForme();
          }

          if (i == 13 || i == 11) {
            itemLipide = new Lipides(i, j);
            itemLipide.setJoueur("J2");
            lipidesJ2.add(itemLipide);
            this.echiquier[i][j] = itemLipide.renvoyerForme();
          }
        }
        if (j % 8 == 0 & (i == 2 || i == 12)) {
          String couleur = "rouge";
          itemAnticorps = new Anticorps(couleur, i, j);
          if (i == 2) {
            anticorpsJ1.add(itemAnticorps);
            itemAnticorps.setJoueur("J1");
          } else {
            anticorpsJ2.add(itemAnticorps);
            itemAnticorps.setJoueur("J2");
          }
          this.echiquier[i][j] = itemAnticorps.renvoyerForme();
        }

        if (j % 8 == 2 & (i == 2 || i == 12)) {
          String couleur = "bleu";
          itemAnticorps = new Anticorps(couleur, i, j);
          if (i == 2) {
            anticorpsJ1.add(itemAnticorps);
            itemAnticorps.setJoueur("J1");
          } else {
            anticorpsJ2.add(itemAnticorps);
            itemAnticorps.setJoueur("J2");
          }
          this.echiquier[i][j] = itemAnticorps.renvoyerForme();
        }

        if (j % 8 == 4 & (i == 2 || i == 12)) {
          String couleur = "jaune";
          itemAnticorps = new Anticorps(couleur, i, j);
          if (i == 2) {
            anticorpsJ1.add(itemAnticorps);
            itemAnticorps.setJoueur("J1");
          } else {
            anticorpsJ2.add(itemAnticorps);
            itemAnticorps.setJoueur("J2");
          }
          this.echiquier[i][j] = itemAnticorps.renvoyerForme();
        }

        if (j % 8 == 6 & (i == 2 || i == 12)) {
          String couleur = "vert";
          itemAnticorps = new Anticorps(couleur, i, j);
          if (i == 2) {
            anticorpsJ1.add(itemAnticorps);
            itemAnticorps.setJoueur("J1");
          } else {
            anticorpsJ2.add(itemAnticorps);
            itemAnticorps.setJoueur("J2");
          }
          this.echiquier[i][j] = itemAnticorps.renvoyerForme();
        }
      }
    }
  }

  /** On recherche un Anticorps à une certaine position et si on le trouve on le retourne. */
  public Anticorps trouverAC(int x, int y, ArrayList<Anticorps> List) {
    for (Anticorps object : List) {
      if (object.getX() == x && object.getY() == y) {
        return object;
      }
    }

    return null;
  }

  /** On recherche un Antigene à une certaine position et si on le trouve on le retourne. */
  public Antigene trouverAG(int x, int y, ArrayList<Antigene> List) {
    for (Antigene object : List) {
      if (object.getX() == x && object.getY() == y) {
        return object;
      }
    }

    return null;
  }

  /** On recherche un Lipide à une certaine position et si on le trouve on le retourne. */
  public Lipides trouverLI(int x, int y, ArrayList<Lipides> List) {
    for (Lipides object : List) {
      if (object.getX() == x && object.getY() == y) {
        return object;
      }
    }

    return null;
  }

  /** Fonction pour éliminer un Antigene. */
  public void eliminer(int x, int y, ArrayList<? extends Pions> List) {
    for (
      Iterator<? extends Pions> iterator = List.iterator();
      iterator.hasNext();
    ) {
      Pions value = iterator.next();
      if (value.getX() == x && value.getY() == y) {
        iterator.remove();
      }
    }
  }

  /** Fonction pour capturer un Anticorps. */
  public void capturer(int x, int y, String Couleur, ArrayList<Antigene> List) {
    for (Iterator<Antigene> iterator = List.iterator(); iterator.hasNext();) {
      Antigene value = iterator.next();
      if (
        value.getX() == x &&
        value.getY() == y &&
        value.getCouleur().equals(Couleur)
      ) {
        iterator.remove();
        if (tour % 2 == 0) {
          pointsJ1++;
        } else {
          pointsJ2++;
        }
      }
    }
  }

  /** Fonction pour comparer les couleurs de l'Anticorps avec celle de l'Antigene. */
  public boolean comparerCouleur(Anticorps anticorps, Antigene antigene) {
    if (anticorps.getCouleur().equals(antigene.getCouleur())) {
      return true;
    }
    return false;
  }

  /** Fonction pour changer la case de l'affichage d'un Anticorps. */
  public void changerCaseAC(int row, int col, Anticorps anticorps) {
    anticorps.setX(row);
    anticorps.setY(col);
    this.echiquier[row][col] = anticorps.renvoyerForme();
  }

  /** Fonction pour changer la case de l'affichage d'un Lipide. */
  public void changerCaseLI(int row, int col, Lipides lipides) {
    lipides.setX(row);
    lipides.setY(col);
    this.echiquier[row][col] = lipides.renvoyerForme();
  }

  /** Fonction pour initialiser l'affichage de départ des Antigenes. */
  public void departAntigene() {
    for (int i = 0; i < 40; i++) {
      if (i < 10) {
        itemAntigenes = new Antigene("rouge");
        antigenes.add(itemAntigenes);
        int x = itemAntigenes.getX();
        int y = itemAntigenes.getY();
        this.echiquier[x][y] = itemAntigenes.renvoyerForme();
      } else if (i < 20) {
        itemAntigenes = new Antigene("bleu");
        antigenes.add(itemAntigenes);
        int x = itemAntigenes.getX();
        int y = itemAntigenes.getY();
        this.echiquier[x][y] = itemAntigenes.renvoyerForme();
      } else if (i < 30) {
        itemAntigenes = new Antigene("vert");
        antigenes.add(itemAntigenes);
        int x = itemAntigenes.getX();
        int y = itemAntigenes.getY();
        this.echiquier[x][y] = itemAntigenes.renvoyerForme();
      } else {
        itemAntigenes = new Antigene("jaune");
        antigenes.add(itemAntigenes);
        int x = itemAntigenes.getX();
        int y = itemAntigenes.getY();
        this.echiquier[x][y] = itemAntigenes.renvoyerForme();
      }
    }
  }

  /** Fonction pour renvoyer le nombre d'Antigenes restant dans la liste d'Antigenes. */
  public int nombreAG() {
    return antigenes.size();
  }

  /** Fonction pour afficher le vainqueur et le nombre de points de chaque joueurs à la fin de la partie. */
  public void fin() {
    if (pointsJ1 > pointsJ2) {
      System.out.println(
        " \n\n Victoire du joueur 1 avec : " +
        String.valueOf(pointsJ1) +
        " points \n Le joueur 2 a : " +
        String.valueOf(pointsJ2) +
        " points \n\n"
      );
    }

    if (pointsJ1 == pointsJ2) {
      System.out.println(
        " \n\n Egalite le joueur 1 a : " +
        String.valueOf(pointsJ1) +
        " points \n Le joueur 2 a : " +
        String.valueOf(pointsJ2) +
        " points\n\n"
      );
    } else {
      System.out.println(
        " \n\n Victoire du joueur 2 avec : " +
        String.valueOf(pointsJ2) +
        " points \n Le joueur 1 a : " +
        String.valueOf(pointsJ1) +
        " points \n\n"
      );
    }
  }

  /** Fonction qui renvoie le nombre d'Anticorps du joueur1 au cours de la partie. */
  public int nombreACJ1() {
    return anticorpsJ1.size();
  }

  /** Fonction qui renvoie le nombre d'Anticorps du joueur2 au cours de la partie. */
  public int nombreACJ2() {
    return anticorpsJ2.size();
  }

  /** Fonction pour compter le nombre d'Antigenes qui se trouve sur la même case,
   * ainsi que pour afficher le nombre sur la case.
   */
  void compteurAG() {
    int compteur = 0;
    for (int row = 0; row < this.echiquier.length - 1; row++) {
      for (int col = 0; col < this.echiquier.length - 1; col++) {
        for (Antigene antigene : antigenes) {
          if (antigene.getX() == row && antigene.getY() == col) {
            compteur++;
          }
        }
        if (compteur > 1) {
          this.echiquier[row][col] =
            Color.BLUE_BOLD +
            "{" +
            Integer.toString(compteur) +
            "_}" +
            Color.RESET;
        }
        compteur = 0;
      }
    }
  }

  /** Fonction qui permet au joueur de choisir une case contenant plusieurs Antigenes afin de connaître son contenu. */
  void contenuSurcharge() {
    System.out.println("Quelle case : ");
    System.out.println("ligne : ");
    int ligne = saisieEntier() - 1;
    System.out.println("colonne : ");
    int colonne = saisieEntier() - 1;

    int nombreRouge = 0;
    int nombreBleu = 0;
    int nombreJaune = 0;
    int nombreVert = 0;
    String msg = "\n";

    for (Antigene antigene : antigenes) {
	System.out.println(antigene.renvoyerForme()); 

      if (antigene.getX() == ligne && antigene.getY() == colonne) {
        switch (antigene.getCouleur()) {
          case "rouge":
            nombreRouge++;
            break;
          case "bleu":
            nombreBleu++;
            break;
          case "jaune":
            nombreJaune++;
            break;
          case "vert":
            nombreVert++;
            break;
          default:
            System.out.println("System out Error 403");
        }
      }
    }

    if (nombreRouge > 0) {
      msg +=
        " Il y'a " + Integer.toString(nombreRouge) + " antigenes rouges \n ";
    }

    if (nombreBleu > 0) {
      msg += "Il y'a " + Integer.toString(nombreBleu) + " antigenes bleues \n ";
    }

    if (nombreVert > 0) {
      msg += "Il y'a " + Integer.toString(nombreVert) + " antigenes verts \n ";
    }

    if (nombreJaune > 0) {
      msg +=
        "Il y'a " + Integer.toString(nombreJaune) + " antigenes jaunes \n ";
    }

    System.out.println(msg);
  }

  /** Fonction pour afficher différentes options possibles pour le joueur,
   * ainsi que pour lui permettre de choisir entre celles-ci.  */
  int messageVoir() {
    System.out.println("Voulez-vous voir le contenu d'une case ?");
    System.out.println(
      "(1) Oui \t (2) Non \n(3) Enregistrer la partie et retourner au menu \t(4) Retourner au menu  "
    );
    int choix = saisieEntier();
    if (choix == 1) {
      contenuSurcharge();
    } else if (choix == 3) {
      this.sauver();
      return 3;
    } else if (choix == 4) {
      return 4;
    }
    return 0;
  }

  /** Message qui affiche quel joueur est entrain de jouer, ainsi que ses points et le nombre d'Antigenes restants au cours de la partie. */
  void messageTour() {
    if (this.tour % 2 == 0) {
      System.out.println(
        " Joueur 1 \t points : " +
        String.valueOf(pointsJ1) +
        " \t Antigenes : " +
        String.valueOf(antigenes.size()) +
        "\n"
      );
    } else {
      System.out.println(
        "Joueur 2 \t points : " +
        String.valueOf(pointsJ2) +
        " \t Antigenes : " +
        String.valueOf(antigenes.size()) +
        "\n"
      );
    }
  }

  /** Message qui permet au joueur de choisir la case où se trouve la piece qu'il veut déplacer ou bien où veut-il la déplacer
   * en fonction d'un message qu'il entre dans les paramètres de la fonction.
   * @param choix un String pour spécifier si le joueur choisit une pièce à déplacer ou une destination.
   */
  int[] messageDeplacement(String choix) {
    int[] XY = new int[2];
    int row;
    int col;

    if (choix.equals("deplacement")) {
      System.out.println("Quel pièce voulez-vous déplacer : ");
    } else {
      System.out.println("Où voulez-vous la déplacer : ");
    }
    System.out.println("ligne : ");
    row = saisieEntier();
    System.out.println("colonne : ");
    col = saisieEntier();
    //System.out.println("");

    XY[0] = row;
    XY[1] = col;
    return XY;
  }

  /** Fonction pour permettre a l'utilisateur de choisir s'il veut déplacer un Anticorps ou un Lipide. */
  void choixCoup() {
    loop:while (true) {
      afficherEchiquier();
      System.out.println("Quelle piece voulez-vous deplacer :  ");
      System.out.println("(1)Lipide\t(2)Anticorps");
      int choix = saisieEntier();

      switch (choix) {
        case 1:
          coupLipides();
          break loop;
        case 2:
          coupAnticorps();
          break loop;
        default:
          System.out.println("Choisissez la bonne option ");
      }
    }
  }

  /** Fonction pour le déplacement des Anticorps, prenant en compte leur possibilités de capture et d'élimination. */
  void coupAnticorps() {
    boolean reussi = false;
    ArrayList<Anticorps> mesAnticorps;
    int oldRow;
    int oldCol;
    int row;
    int col;
    int[] coord;

    if (this.tour % 2 == 0) {
      mesAnticorps = this.anticorpsJ1;
    } else {
      mesAnticorps = this.anticorpsJ2;
    }

    // Boucle qui ne s'arrête que si et seulement si le joueur sélectionne un de ces Anticorps.
    while (true) {
      coord = messageDeplacement("deplacement");
      oldRow = coord[0] - 1;
      oldCol = coord[1] - 1;

      itemAnticorps = trouverAC(oldRow, oldCol, mesAnticorps);
      if (itemAnticorps != null) {
        break;
      }
      if (itemAnticorps == null) {
        System.out.println("Choisissez un de vos anticorps ! ");
      }
    }

    // Boucle qui ne s'arrête que si et seulement si le joueur effectue un coup correcte avec son Anticorps.
    while (!reussi) {
      coord = messageDeplacement("location");
      this.echiquier[oldRow][oldCol] = Color.CYAN + "{__}" + Color.RESET;
      row = coord[0] - 1;

      col = coord[1] - 1;

      // Le cas où le joueur veut aller vers une case supérieur à la capacité de déplacement d'un Anticorps.
      if (
        Math.abs(itemAnticorps.getX() - row) > itemAnticorps.getPas() ||
        Math.abs(itemAnticorps.getY() - col) > itemAnticorps.getPas()
      ) {
        System.out.println("Vous ne pouvez pas effectuer ce coup!");
        continue;
      }

      // Le modulo 15 nous permet d'implémenter la grille infinie.
      row = (row + 15) % 15;
      col = (col + 15) % 15;

      //Le cas où le joueur1 tombe sur une case déjà occupée par un de ces Anticorps ou un de ces Lipides.
      if (
        tour % 2 == 0 &&
        (
          this.echiquier[row][col].contains("{A1}") ||
          this.echiquier[row][col].contains("{L1}")
        )
      ) {
        System.out.println("Case deja occupee!");
        changerCaseAC(oldRow, oldCol, itemAnticorps);
        continue;
      } else if ( //Le cas où le joueur2 tombe sur une case déjà occupée par un de ces Anticorps ou un de ces Lipides.
        tour % 2 == 1 &&
        (
          this.echiquier[row][col].contains("{A2}") ||
          this.echiquier[row][col].contains("{L2}")
        )
      ) {
        System.out.println("Case deja occupee!");
        changerCaseAC(oldRow, oldCol, itemAnticorps);
        continue;
      } else if ( // Le cas où le joueur tombe sur une case avec un Antigene.
        this.echiquier[row][col].contains("{oV}") ||
        this.echiquier[row][col].contains("{oR}") ||
        this.echiquier[row][col].contains("{oJ}") ||
        this.echiquier[row][col].contains("{oB}")
      ) {
        itemAntigenes = trouverAG(row, col, antigenes);
        trouve = comparerCouleur(itemAnticorps, itemAntigenes);

        if (
          trouve == true && itemAnticorps.getLimite() > 0
        ) {/*  L'Antigene  est de la même couleur que lui, 
        il peut donc le capturer s'il n'en a pas déjà capturé 5 et le joueur gagne un point. */
          System.out.println("Miam !");
          this.echiquier[row][col] = itemAnticorps.renvoyerForme();
          capturer(row, col, itemAnticorps.getCouleur(), antigenes);
          changerCaseAC(row, col, itemAnticorps);
          itemAnticorps.limiteDown();
          reussi = true;
        } else { // Sinon il l'élimine simplement et le joueur ne gagne pas de points.
          System.out.println("Eliminé!");
          eliminer(row, col, antigenes);
          changerCaseAC(row, col, itemAnticorps);
          reussi = true;
        }
      } else if (
        this.echiquier[row][col].contains(Color.BLUE_BOLD)
      ) {/* Dans le cas où la case est surchargée,
        nous avons décidé de ne pas donner la possibilité à l'Anticorps de manger les Antigènes se trouvant dedans. */
        System.out.println(
          "Les anticorps ne peuvent pas manger dans la surcharge !"
        );
        reussi = true;
      } else if ( // Le cas où le joueur1 tombe sur un Anticorps ou un Lipide du joueur2, il l'élimine.
        tour % 2 == 0 &&
        (
          this.echiquier[row][col].contains("{A2}") ||
          this.echiquier[row][col].contains("{L2}")
        )
      ) {
        System.out.println("Eliminé!");
        if (this.echiquier[row][col].contains("{A2}")) {
          eliminer(row, col, anticorpsJ2);
        } else {
          eliminer(row, col, lipidesJ2);
        }
        changerCaseAC(row, col, itemAnticorps);
        reussi = true;
      } else if (
        tour % 2 == 1 &&
        (
          this.echiquier[row][col].contains("{A1}") ||
          this.echiquier[row][col].contains("{L1}")
        )
      ) { // Le cas où le joueur2 tombe sur un Anticorps ou un Lipide du joueur1, il l'élimine.
        System.out.println("Eliminé!");
        if (this.echiquier[row][col].contains("{A1}")) {
          eliminer(row, col, anticorpsJ2);
        } else {
          eliminer(row, col, lipidesJ2);
        }
        changerCaseAC(row, col, itemAnticorps);
        reussi = true;
      } else { // Le seul cas restant, une case vide, l'Anticorps va simplement dessus.
        changerCaseAC(row, col, itemAnticorps);
        reussi = true;
      }
    }

    /*  A la fin d'un coup, on affiche le message pour le joueur, son nombre de points, le nombre d'Antigenes restant.
    On affiche ensuite l'échiquier et on passe au tour suivant. */
    messageTour();
    afficherEchiquier();
    tour++;
  }

  /** Fonction pour déplacer un Lipides. */
  void coupLipides() {
    boolean reussi = false;
    ArrayList<Lipides> mesLipides;
    int row;
    int col;
    int[] coord;

    // En fonction du tour, c'est le joueur1 ou le joueur2 qui déplace ses lipides
    if (this.tour % 2 == 0) {
      mesLipides = lipidesJ1;
    } else {
      mesLipides = lipidesJ2;
    }

    // Boucle qui ne s'arrête que si et seulement si le joueur a sélectionné un de ses Lipides.
    while (true) {
      coord = messageDeplacement("deplacement");
      row = coord[0] - 1;
      col = coord[1] - 1;
      itemLipide = trouverLI(row, col, mesLipides);
      if (itemLipide == null) {
        System.out.println("Choisissez un de vos Lipides ! ");
      } else {
        break;
      }
    }

    // Boucle qui ne s'arrête que si et seulement si le joueur a déplacé correctement son Lipides.
    while (!reussi) {
      coord = messageDeplacement("location");
      this.echiquier[itemLipide.getX()][itemLipide.getY()] =
        Color.CYAN + "{__}" + Color.RESET;

      row = coord[0] - 1;
      col = coord[1] - 1;

      if ( // Le cas où le joueur veut se déplacer vers une case qui dépasse la capacité de déplacement du Lipides.
        Math.abs(itemLipide.getX() - row) > itemLipide.getPas() ||
        Math.abs(itemLipide.getY() - col) > itemLipide.getPas()
      ) {
        System.out.println("Vous ne pouvez pas effectuer ce coup!");
        changerCaseLI(itemLipide.getX(), itemLipide.getY(), itemLipide);
        continue;
      }

      // Le modulo 15 nous permet d'implémenter la grille infinie.
      row = (row + 15) % 15;
      col = (col + 15) % 15;

      // Si la case est occuppée, le Lipides ne peut pas s'y déplacer.
      if (!this.echiquier[row][col].contains("{__}")) {
        System.out.println("Case deja occupee!");
        changerCaseLI(itemLipide.getX(), itemLipide.getY(), itemLipide);
        continue;
      } else { // La case est libre, le Lipides peut y aller.
        changerCaseLI(row, col, itemLipide);
        reussi = true;
      }
    }
    messageTour();
    afficherEchiquier();
    tour++;
  }

  /**  Fonction pour déplacer tous les Antigènes. */
  void coupAntigene() {
    for (Antigene antigene : antigenes) {
      antigene.placement(echiquier);
    }
  }

  /** Fonction de saisie d'une chaine de caractère. */
  public String saisieChaine() {
    try {
      BufferedReader buff = new BufferedReader(
        new InputStreamReader(System.in)
      );
      String chain = buff.readLine();
      return chain;
    } catch (IOException e) {
      System.out.println("Impossible de travailler " + e);
      return null;
    }
  }

  /** Fonction de saisie d'un entier. */
  public int saisieEntier() {
    try {
      BufferedReader buff = new BufferedReader(
        new InputStreamReader(System.in)
      );
      String chain = buff.readLine();
      int num = Integer.parseInt(chain);
      return num;
    } catch (IOException e) {
      System.out.println("Impossible de travailler " + e);
      return -1;
    }
  }

  /** Fonction pour enregistrer l'objet NewEchiquier afin de pouvoir reprendre une partie à partir d'un fichier. */
  public void sauver() {
    try {
      System.out.println(" Nom du fichier : ");
      String nom = saisieChaine();
      FileOutputStream ostream = new FileOutputStream(nom);
      ObjectOutputStream p = new ObjectOutputStream(ostream);
      p.writeObject(this);
      p.flush();
      p.close();
    } catch (IOException e) {
      System.out.println(" Erreur " + e);
    }

    System.out.println("Partie Sauvegardée ");
  }
}
