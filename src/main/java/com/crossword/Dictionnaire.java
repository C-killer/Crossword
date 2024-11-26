package com.crossword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

    // stockage des mots
    private List<String> mots = new ArrayList<>();

    /**
     * Ajoute un mot au Dictionnaire, en dernière position.
     * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
     */
    public void add(String mot) {
        mots.add(mot.toLowerCase());
    }

    /**
     * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
     * @return la taille
     */
    public int size() {
        return mots.size();
    }

    /**
     * Accès au i-eme mot du dictionnaire.
     * @param i l'index du mot recherché, compris entre 0 et size-1.
     * @return le mot à cet index
     */
    public String get(int i) {
        return mots.get(i);
    }

    /**
     * Rend une copie de ce Dictionnaire.
     * @return une copie identique de ce Dictionnaire
     */
    public Dictionnaire copy () {
        Dictionnaire copy = new Dictionnaire();
        copy.mots.addAll(mots);
        return copy;
    }

    /**
     * Retire les mots qui ne font pas exactement "len" caractères de long.
     * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
     * @param len la longueur voulue
     * @return le nombre de mots supprimés
     */
    public int filtreLongueur(int len) {
        List<String> cible = new ArrayList<>();
        int cpt=0;
        for (String mot : mots) {
            if (mot.length() == len)
                cible.add(mot);
            else
                cpt++;
        }
        mots = cible;
        return cpt;
    }


    /**
     * Filters the list of words based on the specified length and character.
     * Only words that are at least 'x' characters long and have the 'x'-th character
     * equal to the specified 'c' are kept. All other words are removed.
     *
     * @param c the character that must be at position 'x' in the words to be kept
     * @param x the minimum length of the words to be kept
     * @return the number of words that were removed during the filtering process
     */
    public int filtreParLettre(char c, int x) {
        List<String> cible = new ArrayList<>();
        int cpt=0;
        for (String mot : mots) {
            if (mot.length() >= x && mot.charAt(x) == c)
                cible.add(mot);
            else
                cpt++;
        }
        mots = cible;
        return cpt;
    }


    public static Dictionnaire loadDictionnaire(String path) {
        Dictionnaire dico = new Dictionnaire();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {  // readLine()会自动去掉换行符\n
                if (!line.isEmpty()) {
                    dico.add(line); // 使用 trim() 去除多余空白
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading dictionary from file: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return dico;
    }


    @Override
    public String toString() {
        if (size() == 1) {
            return mots.getFirst();
        } else {
            return "Dico size =" + size();
        }
    }



}