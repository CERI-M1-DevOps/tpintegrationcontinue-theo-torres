package liste;

public class ListeSimple {
    private long size;
    Noeud tete;
    
    /**
    * Retourne le nombre d'elements de la liste
    * @return nombre d'elements actuellement contenus dans la liste
    */
    public long getSize() {
        return size;
    }

    /**
    * Ajoute un nouvel element au  début de la liste
    * @param element la valeur a ajouter
    */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }
    
    /**
    * Modifier le premier element trouvé par sa nouvelle valeur
    * Ne fait rien si l'element n'est pas trouve
    * @param element ce que je cherche
    * @param nouvelleValeur ce que je mets à la place
    */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
    * Change tout les memes elements par la nouvelle valeur
    * @param element ce que je cherche
    * @param nouvelleValeur ce que je mets à la place
    */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

     /**
     * Affiche la liste en mode texte.
     * @return la liste sous forme de String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
    * Supprime juste la premiere fois ou on trouve l’element dans la liste
    * @param element la valeur a enlever
    */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
    * Supprime toutes les fois ou l'element est trouve
    * @param element la valeur a supprimer
    */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }
    
    /**
    * Version recursive pour supprimer toutes les occurrences
    * @param element la valeur a supprimer
    * @param tete la tete de la sous-liste en cours
    * @return la nouvelle tete apres suppression
    */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
    * Renvoie l'avant-dernier element de la liste
    * Si la liste a 0 ou 1 element, ca ne veut rien dire -> on renvoie null
    * @return le noeud juste avant le dernier, ou null si pas assez d'elements
    */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }
    
    /**
    * Inverse l'ordre de la liste
    * Pas de nouvelle liste creee
    */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
    * Donne le noeud place juste avant r
    * @param r le noeud dont on veut recuperer le precedent
    * @return le precedent de r
    */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }
    
    /**
    * Echange les positions de r1 et r2 dans la liste
    * Gere aussi le cas ou l'un des deux est la tete; si r1 == r2, on ne fait rien
    * @param r1 premier noeud a echanger
    * @param r2 second noeud a echanger
    */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}