/** Classe pour créer les Anticorps. */
class Anticorps extends Pions {

  /** Couleur d'un anticorps. */
  private String couleur;
  /** Nombre de cases maximales de déplacement d'un anticorps. */
  private final int pas = 1;
  /** Nombre limite d'Antigenes que l'Anticorps peut capturer.  */
  private int limite = 5;

  /** Constructeur d'un Anticorps à partir du constructeur de pions, puis on lui attribue une couleur. */
  public Anticorps(String couleur, int x, int y) {
    super(x, y);
    this.couleur = couleur;
  }

  /** Retourne la limite de capture de l'Anticorps. */
  public int getLimite(){
    return this.limite;
  }

  /** Diminue la limite de capture de l'Antigene de 1. */
  public void limiteDown(){
    this.limite--;
  }

  /** Renvoie l'Affichage de l'Anticorps en fonction du joueur et en fonction de la couleur. */
  public String renvoyerForme() {
    if (this.getJoueur() == "J1") {
      if (this.couleur == "rouge") {
        return Color.RED + "{A1}" + Color.RESET;
      } else if (this.couleur == "bleu") {
        return Color.BLUE + "{A1}" + Color.RESET;
      } else if (this.couleur == "jaune") {
        return Color.YELLOW + "{A1}" + Color.RESET;
      } else {
        return Color.GREEN + "{A1}" + Color.RESET;
      }
    } else {
      if (this.couleur == "rouge") {
        return Color.RED + "{A2}" + Color.RESET;
      } else if (this.couleur == "bleu") {
        return Color.BLUE + "{A2}" + Color.RESET;
      } else if (this.couleur == "jaune") {
        return Color.YELLOW + "{A2}" + Color.RESET;
      } else {
        return Color.GREEN + "{A2}" + Color.RESET;
      }
    }
  }

  /** Renvoie la couleur de l'Anticorps.*/
  public String getCouleur() {
    return this.couleur;
  }

  /** Renvoie le déplacement maximal de l'Anticorps. */
  public int getPas() {
    return this.pas;
  }
}
