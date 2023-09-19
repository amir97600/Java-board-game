/** Programme du Jeu de l'immunité
 * @author Amirdine Kassim et Karl Baltazart
 * @version 1.0
 */

import java.io.*;


/** Classe pour éxécuter le jeu. */
class Main {

  static String touche = null;
  static int choix = 0;
  /** On initialise un objet de type NewEchiquier. */
  static NewEchiquier echiquier = new NewEchiquier();

  public static void main(String[] args) {
    /** On crée l'échiquier, on y place les pions et on met en place le systeme de surcharge pour les antigenes. */
    echiquier.creerEchiquier();
    echiquier.placementDepartEchiquier();
    echiquier.departAntigene();
    echiquier.compteurAG();

    /** Boucle qui affiche un message de bienvenue et s'arrête lorsque l'utiliisateur appuie sur la touche Entrée. */
    while (touche == null) {
      init();
      touche = saisieChaine();
    }

    /** Boucle qui affiche le menu principal et demande à l'utilisateur de choisir ce qu'il souhaite faire. */
    loop:while (true) {
      menu();
      choix = saisieEntier();
      switch (choix) {
        case 1:
          nouvellePartie();
          break;
        case 2:
          chargerPartie();
          break;
        case 3:
          quitterPartie();
          break loop;
        default:
          System.out.println("Choisissez une option correcte !");
      }
    }
  }

  /** Fonction qui permet d'initialiser une nouvelle partie qui consiste en une boucle qui alterne entre le joueur 1 et le joueur 2, lors d'un tour le joueur peut choisir de quitter la partie à tout moment ou bien de sauvegarder et de quitter la partie. Les antigenes ne se déplacent qu'après que les deux joueurs aient effectué un déplacement. La partie se termine lorsqu'il n'y a plus d'antigènes à capturer ou bien lorsqu'un des deux joueurs n'a plus d'anticorps. */
  public static void nouvellePartie() {
    int quitter = 0;
    int tourtour = 0;

    while (echiquier.nombreAG() > 0) {
      if (echiquier.nombreACJ1() == 0 || echiquier.nombreACJ2() == 0) {
        break;
      }
      echiquier.messageTour();
      echiquier.afficherEchiquier();
      quitter = echiquier.messageVoir();
      if (quitter == 3 || quitter == 4) {
        break;
      }
      echiquier.choixCoup();
      tourtour++;

      if (tourtour > 0 && tourtour % 2 == 0) {
        echiquier.coupAntigene();
        echiquier.compteurAG();
      }
    }

    echiquier.fin();
  }

  /** Fonction qui permet de reprendre une partie à partir d'un fichier sauvegardé, elle récupère un objet NewEchiquier enregistré et l'utilise ensuite comme la fonction nouvellePartie(). */
  public static void chargerPartie() {
    int quitter;

    NewEchiquier echiquier = restaurerEchiquier();
    while (echiquier.nombreAG() > 0) {
      if (echiquier.nombreACJ1() == 0 || echiquier.nombreACJ2() == 0) {
        break;
      }
      echiquier.messageTour();
      echiquier.afficherEchiquier();
      quitter = echiquier.messageVoir();
      if (quitter == 3 || quitter == 4) {
        break;
      }
      echiquier.choixCoup();
      echiquier.coupAntigene();
      echiquier.compteurAG();
    }

    echiquier.fin();
  }

  /** Fonction qui affiche un message d'aurevoir lorsque l'on quitte le jeu. */
  public static void quitterPartie() {
    System.out.println(" Ciao! ");
  }

  /** Fonction qui affiche un message d'initialisation du jeu. */
  public static void init() {
    System.out.println(
      "Bienvenue dans le jeu de l'Immunite, appuyer sur une touche pour commencer"
    );
  }

  /** Fonction qui affiche le menu principal du jeu. */
  public static void menu() {
    System.out.println("Choisissez ce que vous voulez faire : \n");
    System.out.println("1\tPour commencer une nouvelle partie");
    System.out.println("2\tPour continuer une partie à partir d'un fichier");
    System.out.println("3\tPour quitter le jeu");
  }

  /** Fonction de saisie d'une chaine de caractère. */
  public static String saisieChaine() {
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
  public static int saisieEntier() {
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

  /** Fonction qui récupère un fichier d'un objet enregistré et qui le retourne. */
  public static NewEchiquier restaurerEchiquier() {
    try {
      System.out.println("Nom du fichier à lire : ");
      String nom = saisieChaine();
      FileInputStream istream = new FileInputStream(nom);
      ObjectInputStream p = new ObjectInputStream(istream);
      NewEchiquier echiquier = (NewEchiquier) p.readObject();
      p.close();
      return echiquier;
    } catch (IOException e) {
      System.out.println(" Erreur " + e);
    } catch (ClassNotFoundException c) {
      System.out.println("Erreur de chargement ");
    }
    return null;
  }
}
