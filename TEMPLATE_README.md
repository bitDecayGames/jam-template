# Jump-Template

This template project is for use when trying to set up a new project with Jump.  It starts you out with several basic and generic pieces:

1. A few start up screens like:
  * Splash screen
  * Menu screen
  * Game screen
2. An entity-component system with generic components
3. A few helper classes that seem to be needed in most games we build

It does not contain very much actual room code.  Most of that is left up to you to define and implement.  This is just a starting point.

## Install

You will need to install both the ```jump``` and the ```animagic``` projects as dependencies.  The way you do this is by cloning those repositories into an adjacent folder to the jump-template.  You will need to have git setup with the correct ssh keys and you will need to have maven installed and accessible through command line.  I'll show you how to do it with jump, and then you just need to repeat the steps with animagic.

Jump:
```
git@github.com:bitDecayGames/Jump.git
```

Animagic:
```
git@github.com:Kenoshen/animagic.git
```

First, clone the repository into an adjacent directory:
```bash
# starting from this project's root directory
cd ..
git clone git@github.com:bitDecayGames/Jump.git
```

Next, move into that repositories root directory:
```
cd Jump
```

Use the maven install command to compile, build, and install the project into your local maven repository.  It will then become accessible through this project's pom.xml:
```
mvn install
```

Now repeat for each of the required dependencies that are not already in the maven central repository (custom built stuff).

## Run

Running is as easy as right clicking the Launcher class and selecting "Run".

## Building/Packaging

When you are ready to package it up into a deliverable, just use this maven command:
```
mvn package
```

That command will create a zip file in at ```target/{{projectName}}-{{projectVersion}}``` that you can then use to distribute.


> Note: as of this moment, I haven't found a way to distribute the jar such that a jdk does not need to be installed.  When running the jar from a computer without a jdk, the jar throws an error and notifies the user that in order to run the executable, they must install a jdk.  Not very user-friendly...

