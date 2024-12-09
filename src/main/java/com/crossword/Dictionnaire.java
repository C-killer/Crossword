package com.crossword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {
    // stockage des mots
    private final List<String> mots = new ArrayList<>();

    /**
     * Ajoute un mot au Dictionnaire, en dernière position.
     * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
     */
    public void add(String mot) {
        mots.add(mot.toLowerCase());
    }

    public void setMots(List<String> newMots) {
        mots.clear();
        for (String mot : newMots) {
            mots.add(mot.toLowerCase());
        }
    }

    /**
     * Taille du dictionnaire, c'est-à-dire nombre de mots qu'il contient.
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

    public List<String> getMots() {
        return mots;
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
        this.setMots(cible);
        return cpt;
    }


    /**
     * modifiera le dictionnaire pour ne garder que les mots
     * dont la ième lettre est égale au caractère de l’argument c
     * @param c char à filtre
     * @param i index
     * @return le nombre de mots qui ont été supprimés du dictionnaire
     */
    public int filtreParLettre(char c, int i) {
        List<String> cible = new ArrayList<>();
        int cpt=0;
        for (String mot : mots) {
            if (i < mot.length() && mot.charAt(i) == c) {
                cible.add(mot);
            } else {
                cpt++;
            }
        }
        this.setMots(cible);
        return cpt;
    }


    public int filtreParEnsembleLettre(Set<Character> lettres, int i) {
        List<String> cible = new ArrayList<>();
        int cpt = 0;
        for (String mot : mots) {
            if (i < mot.length() && lettres.contains(mot.charAt(i))) {
                cible.add(mot);
            } else {
                cpt++;
            }
        }
        setMots(cible);
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


    public Set<Character> getLettres(int i) {
        Set<Character> lettres = new HashSet<>();
        for (String mot : mots) {
            if (i < mot.length()) {
                lettres.add(mot.charAt(i));
            }
        }
        return lettres;
    }

}