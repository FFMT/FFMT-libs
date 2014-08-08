FFMT libs
============

##FRENCH
###Comment installer
####Version universel pour les utilisateurs :
* Téchargez la version universel [ici](http://ci.mcnanotech.fr/job/FFMT-libs/)
* Placez l'archive dans votre dossier .minecraft/mods/

####Source pour les moddeurs :
Dans le fichier build.gradle, ajoutez ceci :
```groovy
repositories {
    maven {
        name = "mff"
        url = "http://files.minecraftforgefrance.fr/maven/"
    }
}

dependencies {
   compile 'fr.minecraftforgefrance:FFMT-libs:${version}-mc${mcversion}:dev'
}
```
La liste des versions disponibles est [ici](http://files.minecraftforgefrance.fr/maven/fr/minecraftforgefrance/FFMT-libs/)

##ENGLISH
###How to install
####Universal for users :
* Download the universal version [here](http://ci.mcnanotech.fr/job/FFMT-libs/)
* Place the file in the folder .minecraft/mods/


####Source for modder :

In the file build.gradle, add this :
```groovy
repositories {
    maven {
        name = "mff"
        url = "http://files.minecraftforgefrance.fr/maven/"
    }
}

dependencies {
   compile 'fr.minecraftforgefrance:FFMT-libs:${version}-mc${mcversion}:dev'
}
```
The list of available versions ​​is [here](http://files.minecraftforgefrance.fr/maven/fr/minecraftforgefrance/FFMT-libs/)
