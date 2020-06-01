# Ile Interdite

Afin de pouvoir compiler et lancer l'application dans votre IDE, il faut ajouter JavaFX a votre
classpath (les `.jar` son dans `lib`) et il faut ajouter les options suivantes à la VM:

```shell script
--module-path $ProjectFileDir$/lib/javafx-sdk-14.0-2.1/lib
--add-modules javafx.controls,javafx.media
```