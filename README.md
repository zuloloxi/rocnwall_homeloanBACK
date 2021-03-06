# Getting Started
Projet de simulation de crédit immobilier

**Procédure d'installation :**
Le projet a été développé sous IntelliJ, et utilise le framework SpringBoot.

Downloader la version V2.0 dans un répertoire local.

La classe de lancement de l'application se trouve dans le répertoire **configuration**, 
dans le package **HomeloancalculatorApplication**.
Elle a pour nom : **HomeloancalculatorApplication**.

Une fois que l'application est lancée, il est possible de visualiser l'ensemble des API REST exposées en allant sur l'URL suivante:
**http://localhost:8080/swagger-ui.html**

L'application est livrée avec deux configurations :
- dans le profil 'local', la base utilisée est la base h2
- dans le profil 'dev', la base utilisée est Postgres.

Postgres doit être installé au préalable.

Le choix du profil se fait dans le fichier **application.properties**.
#Set active spring profile
spring.profiles.active=local

Si le profil 'dev' est utilisé, il faudra configurer l'accès à Postgres dans le fichier **application-dev.properties**.
#configure database connection
spring.datasource.url= jdbc:postgresql://localhost:5432/homeloan
spring.datasource.username=postgres
spring.datasource.password=admin
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect