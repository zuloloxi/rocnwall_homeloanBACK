
**Projet prêt immobilier (« MortgageProject »)**

Type de projet (« projectType »)
Achat (« Purchase ») 
Construction (« Construction »)
Rachat de crédit (« Debt consolidation »)
Nombre d’emprunteurs (« numberOfBorrowers »)
Seul (« One »)
A deux (« Two »)
Par emprunteur :
Revenus nets mensuels avant impôt en € (« netIncome »)
Date de naissance JJMMAA (« dateOfBirth »)
Charges mensuelles du foyer en € (« householdCharges »)



Simulation du prêt (« HomeloanSimulator»)
Mode de calcul (« calculationMode ») : 
Capital cible (« capitalTarget »)
Echéance cible («paymentTarget »)
Apport personnel en € (« personalDeposit »)
Capital en € (« loanAmount »)
Taux annuel en % (« loanInterestRate »)
Constante fixée à la tarification moyenne en vigueur = 0,85%
Durée en années (« loanDuration »)
Echéance en € (« loanPayment »)
Coût du crédit en € (« loanCost »)
Intérêts (« interestCost »)
Assurance (« insuranceCost »)
Frais de dossier (« applicationFee »)
Garantie Crédit Logement (« loanGuaranty »)
TAEG (« effectiveInterestRate »)
Impact assurance (« insuranceImpact »)
Impact frais (« feesImpact »)


**Formule de calcul du capital cible**
per: périodicité (durée en mois d'une période) = 1 (on ne gère ici que les crédits mensuels)
ta : taux annuel
e : échéance
dur : durée en années
d : nombre d'échéances = dur * 12 // per
t : taux périodique = ta * per / 12  
td = (1+t)^d
capital = (e * (td - 1)) / (t * td)

**Formule de calcul de l’échéance cible**
per: périodicité (durée en mois d'une période) = 1 (on ne gère ici que les crédits mensuels)
ta : taux annuel
c : capital
dur : durée en années
d : nombre d'échéances = dur * 12 // per
t : taux périodique = ta * per / 12  
td = (1+t)^d
Si t = 0, alors échéance = c / d
Sinon échéance = c * t * td / (td - 1)

**Algorithme de calcul du TAEG (Taux Annuel Effectif Global)**
Méthode « tauxActuariel: v »
« Calcul du taux actuariel périodique
le receveur (d) est la collection des durées de période
v : collection des versements correspondants
les capitaux sont signés négativement
variables locales : c sv sd vi di t epsilon f dvf tf ti
TAEG = taux actuariel périodique * 12 / per (ici, per : périodicité mensuelle = 1)
Code ci-dessous, en syntaxe Smalltalk, à transposer en Java… »  
     v size ~~ self size
        ifTrue: [ self error: 'Versements et durées incompatibles'].
     heuristique pour la valeur initiale du taux"
     c := 0. sv := 0.  sd := 0.
     1 to: self size do: [ :i |
          vi := v at: i.
          di := self at: i.
          sd := sd + di.
          vi negative ifTrue: [ c := c - (vi*di)].
          sv := sv + (vi*di)].
      t := 1 + (sv / ( c / 2 * sd)).

  " équation actuarielle dont on cherche le 0
        (v1 t * (d1+ ... + dn))  +  ((v2 - v1) t * (d2+ .... + dn)) +....+ ((vn - vn-1) t * dn) - vn
     f est la fonction
     dvf sa dérivée"

   "itère tant qu'epsilon est suffisamment petit"
      epsilon := 1.0e-8.
      [epsilon <= 1.0e-6] whileTrue: [
   "approximation, méthode de la tangente"
         20 timesRepeat: [
            f := v last negated.
            dvf := 0.
            tf := 1.0.  "facteur  t * ( dn +...+di )"
            sd := 0. "facteur (dn + ... + di)"
            self size to: 1 by: -1 do: [ :i |
                vi := v at: i.
                i > 1 ifTrue: [vi := vi -  (v at: i - 1)].
                tf := tf * (t raisedTo: (self at: i)).
                sd := sd + (self at: i).
                f := vi * tf + f.
                dvf := sd * vi * tf / t + dvf].
            (epsilon >= f abs or: [epsilon >= (dvf/f) abs])
                  ifTrue: [^t - 1].
            t := t - (f/dvf)].
        epsilon := epsilon * 10].
        self error: 'Taux actuariel non calculable'
