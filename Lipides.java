/** Classe pour créer les lipides.  */
class Lipides extends Pions {
  
  /** Affichage du Lipide. */
  private String forme = Color.PURPLE + "{L1}" + Color.RESET;
  /** Nombre de cases maximales de déplacement du Lipide. */
  private final int pas = 1;

  /** Constructeur d'un Lipide à partir du constructeur de Pions. */
  Lipides(int x, int y) {
    super(x, y);
  }

  /** Retourne un affichage différent pour le Lipide en fonction du joueur. */
  public String renvoyerForme() {
    if (this.getJoueur().equals("J1")) {
      return this.forme;
    } else {
      return Color.PURPLE_BOLD + "{L2}" + Color.RESET;
    }
  }

  /** Retourne le déplacement maximal du Lipide */
  public int getPas() {
    return this.pas;
  }
}
