import java.io.*;

/**Une classe générale de pions avec des attributs et des fonctions que partagent les Lipides, Anticorps et Antigenes
 * Cette classe implémente Serializable car cela nous permet de sauvagarder les différentes pièces d'une partie avec la sauvegarde objet.
 */
class Pions implements Serializable {

  /** Coordonnée en abscisse. */
  private int x;
  /** Coordonnée en ordonnée. */
  private int y;
  /** Informe sur le joueur auquel appartient le pion.  */
  private String joueur;
  /** Couleur du pion. */
  private String couleur;

  /** Constructeur principal qui permet d'initialiser les coordonnées.
   * @param x abscisse
   * @param y ordonnées
   */
  public Pions(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** Retourne l'information sur le joueur. */
  public String getJoueur() {
    return this.joueur;
  }

  /** Définis le joueur auquel appartient le pions. */
  public void setJoueur(String joueur) {
    this.joueur = joueur;
  }

  /** Renvoie la coordonnées en abscisse. */
  public int getX() {
    return this.x;
  }

  /** Renvoie la coordonnées en oordonnée. */
  public int getY() {
    return this.y;
  }

  /** Redéfinis la coordonnées en abscisse.  */
  public void setX(int x) {
    this.x = x;
  }

  /** Redéfinis la coordonnées en oordonnée. */
  public void setY(int y) {
    this.y = y;
  }

  /** Renvoie la Couleur du pion. */
  public String getCouleur() {
    return this.couleur;
  }
	
  /** Redéfinis la Couleur du pion. */
  public void setCouleur(String couleur) {
     this.couleur = couleur;
  }
}
