CREATE DATABASE gestioncontrat CHARACTER SET utf8;
USE gestioncontrat;


#------------------------------------------------------------
# Table: typecontrat
#------------------------------------------------------------

CREATE TABLE typecontrat(
  id      Int  Auto_increment  NOT NULL ,
  libelle Varchar (255)
  ,CONSTRAINT typecontrat_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: date
#------------------------------------------------------------

CREATE TABLE date(
  date Date NOT NULL
  ,CONSTRAINT date_PK PRIMARY KEY (date)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: utilisateur
#------------------------------------------------------------

CREATE TABLE utilisateur(
  pseudo          Varchar (255) NOT NULL ,
  motdepasse      Varchar (255) ,
  nom             Varchar (255) ,
  prenom          Varchar (255) ,
  telephone       Int ,
  email           Varchar (255) ,
  datedenaissance Date ,
  role            Varchar (255) ,
  numerorue       Varchar (255) ,
  cp              Int ,
  ville           Varchar (255) ,
  pays            Varchar (255)
  ,CONSTRAINT utilisateur_PK PRIMARY KEY (pseudo)

)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: entreprise
#------------------------------------------------------------

CREATE TABLE entreprise(
  id            Int  Auto_increment  NOT NULL ,
  nom           Varchar (255) ,
  siret         Int ,
  qualification Varchar (255) ,
  telephone     Int ,
  email         Varchar (255) ,
  numerorue     Varchar (255) ,
  cp            Int ,
  ville         Varchar (255) ,
  pays          Varchar (255) ,
  representant  Varchar (100)
  ,CONSTRAINT entreprise_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: contrat
#------------------------------------------------------------

CREATE TABLE contrat(
  numero                  Int  Auto_increment  NOT NULL ,
  libelle                 Varchar (255) ,
  description             Varchar (255) ,
  id                      Int ,
  date                    Date ,
  date_finir              Date ,
  date_approuver          Date ,
  id_entreprise           Int ,
  pseudo                  Varchar (255) ,
  id_entreprise_concerner Int
  ,CONSTRAINT contrat_PK PRIMARY KEY (numero)

  ,CONSTRAINT contrat_typecontrat_FK FOREIGN KEY (id) REFERENCES typecontrat(id)
  ,CONSTRAINT contrat_date0_FK FOREIGN KEY (date) REFERENCES date(date)
  ,CONSTRAINT contrat_date1_FK FOREIGN KEY (date_finir) REFERENCES date(date)
  ,CONSTRAINT contrat_date2_FK FOREIGN KEY (date_approuver) REFERENCES date(date)
  ,CONSTRAINT contrat_entreprise3_FK FOREIGN KEY (id_entreprise) REFERENCES entreprise(id)
  ,CONSTRAINT contrat_utilisateur4_FK FOREIGN KEY (pseudo) REFERENCES utilisateur(pseudo)
  ,CONSTRAINT contrat_entreprise5_FK FOREIGN KEY (id_entreprise_concerner) REFERENCES entreprise(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: avenant
#------------------------------------------------------------

CREATE TABLE avenant(
  id          Int  Auto_increment  NOT NULL ,
  modalites   Varchar (255) ,
  dateavenant Date ,
  numero      Int
  ,CONSTRAINT avenant_PK PRIMARY KEY (id)

  ,CONSTRAINT avenant_contrat_FK FOREIGN KEY (numero) REFERENCES contrat(numero)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: facture
#------------------------------------------------------------

CREATE TABLE facture(
  numero        Int  Auto_increment  NOT NULL ,
  libelle       Varchar (255) ,
  description   Varchar (255) ,
  pseudo        Varchar (255) ,
  date          Date ,
  id            Int ,
  id_entreprise Int
  ,CONSTRAINT facture_PK PRIMARY KEY (numero)

  ,CONSTRAINT facture_utilisateur_FK FOREIGN KEY (pseudo) REFERENCES utilisateur(pseudo)
  ,CONSTRAINT facture_date0_FK FOREIGN KEY (date) REFERENCES date(date)
  ,CONSTRAINT facture_entreprise1_FK FOREIGN KEY (id) REFERENCES entreprise(id)
  ,CONSTRAINT facture_entreprise2_FK FOREIGN KEY (id_entreprise) REFERENCES entreprise(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: devis
#------------------------------------------------------------

CREATE TABLE devis(
  numero        Int  Auto_increment  NOT NULL ,
  libelle       Varchar (255) ,
  description   Varchar (255) ,
  id            Int ,
  pseudo        Varchar (255) ,
  date          Date ,
  id_entreprise Int
  ,CONSTRAINT devis_PK PRIMARY KEY (numero)

  ,CONSTRAINT devis_entreprise_FK FOREIGN KEY (id) REFERENCES entreprise(id)
  ,CONSTRAINT devis_utilisateur0_FK FOREIGN KEY (pseudo) REFERENCES utilisateur(pseudo)
  ,CONSTRAINT devis_date1_FK FOREIGN KEY (date) REFERENCES date(date)
  ,CONSTRAINT devis_entreprise2_FK FOREIGN KEY (id_entreprise) REFERENCES entreprise(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: produit
#------------------------------------------------------------

CREATE TABLE produit(
  id           Int  Auto_increment  NOT NULL ,
  nom          Varchar (100) ,
  quantite     Int ,
  devise       Varchar (100) ,
  prixunitaire DECIMAL (15,3) ,
  tva          Decimal ,
  description  Varchar (255)
  ,CONSTRAINT produit_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: composer
#------------------------------------------------------------

CREATE TABLE composer(
  id     Int NOT NULL ,
  numero Int NOT NULL
  ,CONSTRAINT composer_PK PRIMARY KEY (id,numero)

  ,CONSTRAINT composer_produit_FK FOREIGN KEY (id) REFERENCES produit(id)
  ,CONSTRAINT composer_devis0_FK FOREIGN KEY (numero) REFERENCES devis(numero)
)ENGINE=InnoDB;

SELECT * FROM mysql.user;
create user 'kodjovi'@'localhost' IDENTIFIED by 'lkyhafpa';
grant all PRIVILEGES on *.* to 'kodjovi'@'localhost';

#------------------------------------------------------------
# Table: facturer
#------------------------------------------------------------

CREATE TABLE facturer(
  id     Int NOT NULL ,
  numero Int NOT NULL
  ,CONSTRAINT facturer_PK PRIMARY KEY (id,numero)

  ,CONSTRAINT facturer_produit_FK FOREIGN KEY (id) REFERENCES produit(id)
  ,CONSTRAINT facturer_facture0_FK FOREIGN KEY (numero) REFERENCES facture(numero)
)ENGINE=InnoDB;



