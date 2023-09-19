import java.util.*;

/** Classe pour crée les Antigenes. */
class Antigene extends Pions {


  /** Une liste des différentes directions vers lesquelles l'Antigene peut se déplacer. 
   * DG : Diagonale Gauche et DD : Diagonale Droite.
  */
  private ArrayList<String> direction = new ArrayList<String>(
    Arrays.asList(
      "gauche",
      "droite",
      "haut",
      "bas",
      "DG_haut",
      "DG_bas",
      "DD_haut",
      "DD_bas"
    )
  );

  /** Fonction qui renvoie l'affichage de l'Antigene sur l'échiquier en fonction de sa couleur. */
  public String renvoyerForme() {
    if (this.getCouleur() == "rouge") {
      return Color.RED + "{oR}" + Color.RESET;
    } else if (this.getCouleur() == "bleu") {
      return Color.BLUE + "{oB}" + Color.RESET;
    } else if (this.getCouleur() == "jaune") {
      return Color.YELLOW + "{oJ}" + Color.RESET;
    } else if (this.getCouleur() == "vert") {
      return Color.GREEN + "{oV}" + Color.RESET;
    }
    return null;
  }

  /** Constructeur d'un Antigene en fonction d'une couleur, on associe ensuite des coordonnées aléatoires comprises entre 6 et 10 en x et entre 1 et 15 en y sur le plateau. */
  Antigene(String couleur) {
    super(
      5 + (int) (Math.random() * ((4) + 1)),
      (int) (Math.random() * ((14) + 1))
    );
    this.setCouleur(couleur);
  }

  /** Fonction qui permet un nouveau placement aléatoire de l'Antigene sur le plateau. */
  public void placement(String[][] plateau) {
    // On récupère les nouvelles coordonnées aléatoires de l'Antigene, ainsi que la direction.
    int[] XY = deplacement();
    int x = XY[0];
    int y = XY[1];
    int indexAleat = XY[2];
    int pasAleat = XY[3];
    String directionAleat = this.direction.get(indexAleat);
    boolean test;

    /*Si l'Antigene se déplace de deux cases, on vérifie à l'aide d'un booléen si la premiere case sur laquelle
             il va avancer n'est pas occupée. */
    if (pasAleat == 2) {
      test = verification(directionAleat, plateau, x, y);

      // Si tout est bon on change le contenu de la case, on met l'antigene dessus.
      if (test) {
        changerContenuCase(plateau, x, y);
      }
    }
    // Sinon on vérifie juste le contenu de la case où il va aller.
    else {
      test = verifierContenuCase(plateau, x, y);

      if (test) {
        changerContenuCase(plateau, x, y);
      }
    }
  }

  /** Fonction qui place l'affichage de l'antigene dans une case de l'éxhiquier si la case en question n'est pas déjà occupée par une   *autre pièce qu'un Antigene, cette fonction place aussi un affichage vide sur la case où se trouvait précedemment l'Antigene.
   *@param plateau l'échiquier
   *@param x nouvel abscisse de l'Antigene
   *@param y nouvel ordonnée de l'Antigene*/
  public void changerContenuCase(String[][] plateau, int x, int y) {
    if (
      plateau[x][y].contains("{A1}") ||
      plateau[x][y].contains("{A2}") ||
      plateau[x][y].contains("{L1}") ||
      plateau[x][y].contains("{L2}")
    ) {} else {
      plateau[this.getX()][this.getY()] = Color.CYAN + "{__}" + Color.RESET;
      plateau[x][y] = this.renvoyerForme();
      this.setX(x);
      this.setY(y);
    }
  }

  /* Fonction qui verifie si une case est occupée et retourne false si c'est le cas, sinon elle retourne true. */
  public boolean verifierContenuCase(String[][] plateau, int x, int y) {
    boolean test = true;

    if (
      plateau[x][y].contains("{A1}") ||
      plateau[x][y].contains("{A2}") ||
      plateau[x][y].contains("{L1}") ||
      plateau[x][y].contains("{L2}")
    ) {
      test = false;
    } else {
      test = true;
    }
    return test;
  }

  /** Fonction qui tire un pas aléatoire : le nombres de cases desquelles avance l'Antigene, ainsi qu'une direction.
   * La fonction retourne un tableau d'entiers composé des deux nouvelles coordonnées de l'antigène. */
  public int[] deplacement() {
    int indexAleat = (int) (
      Math.random() * (((this.direction.size()) - 1) + 1)
    );
    String directionAleat = this.direction.get(indexAleat);
    int pasAleat = 1 + (int) (Math.random() * ((2 - 1) + 1));

    int x;
    int y;
    int[] XY = new int[4];

    if (directionAleat == "haut") {
      y = (this.getY() - pasAleat);
      XY[1] = y;
      XY[0] = this.getX();
    }
    if (directionAleat == "bas") {
      y = (this.getY() + pasAleat);
      XY[1] = y;
      XY[0] = this.getX();
    }
    if (directionAleat == "gauche") {
      x = (this.getX() - pasAleat);
      XY[1] = this.getY();
      XY[0] = x;
    }
    if (directionAleat == "droite") {
      x = (this.getX() + pasAleat);
      XY[1] = this.getY();
      XY[0] = x;
    }
    if (directionAleat == "DG_haut") {
      x = (this.getX() - pasAleat);
      y = (this.getY() - pasAleat);
      XY[1] = y;
      XY[0] = x;
    }
    if (directionAleat == "DD_haut") {
      x = (this.getX() + pasAleat);
      y = (this.getY() - pasAleat);
      XY[1] = y;
      XY[0] = x;
    }
    if (directionAleat == "DG_bas") {
      x = (this.getX() - pasAleat);
      y = (this.getY() + pasAleat);
      XY[1] = y;
      XY[0] = x;
    }
    if (directionAleat == "DD_bas") {
      x = (this.getX() + pasAleat);
      y = (this.getY() - pasAleat);
      XY[1] = y;
      XY[0] = x;
    }

    if (XY[0] <= 0) {
      XY[0] = 14;
    }
    if (XY[1] <= 0) {
      XY[1] = 14;
    }
    if (XY[0] >= 15) {
      XY[0] = 0;
    }
    if (XY[1] >= 15) {
      XY[1] = 0;
    }

    XY[2] = indexAleat;
    XY[3] = pasAleat;

    return XY;
  }

  /** Fonction qui vérifie le contenu de la case avant celle qui correspond à la destination de l'Antigène, en effet,lorsque celui-çi
   * se déplace de deux cases il faut vérifier qu'il n'emjambe pas une case où se trouverait un Anticorps ou un Lipide */
  public boolean verification(
    String directionAleat,
    String[][] plateau,
    int x,
    int y
  ) {
    boolean test = false;

    if (directionAleat == "haut") {
      y = (y + 1 + 15) % 15;

      test = verifierContenuCase(plateau, x, y);
    }

    if (directionAleat == "bas") {
      y = (y - 1 + 15) % 15;

      test = verifierContenuCase(plateau, x, y);
    }

    if (directionAleat == "gauche") {
      x = (x + 1 + 15) % 15;

      test = verifierContenuCase(plateau, x, y);
    }

    if (directionAleat == "droite") {
      x = (x - 1 + 15) % 15;

      test = verifierContenuCase(plateau, x, y);
    }

    if (directionAleat == "DG_haut") {
      x = (x + 1 + 15) % 15;
      y = (y + 1 + 15) % 15;
      test = verifierContenuCase(plateau, x, y);
    }

    if (directionAleat == "DD_haut") {
      x = (x - 1 + 15) % 15;
      y = (y + 1 + 15) % 15;
      test = verifierContenuCase(plateau, x, y);
    }

    if (directionAleat == "DG_bas") {
      x = (x + 1 + 15) % 15;
      y = (y - 1 + 15) % 15;
      test = verifierContenuCase(plateau, x, y);
    }

    if (directionAleat == "DD_bas") {
      x = (x - 1 + 15) % 15;
      y = (y + 1 + 15) % 15;
      test = verifierContenuCase(plateau, x, y);
    }

    return test;
  }
}
