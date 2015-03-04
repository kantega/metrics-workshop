# Metrics for Developers #

## About ##

Metrics workshop at [Booster](http://www.boosterconf.no/) 2015.

## Requirements ##

* Git 1.7.10+
* Apache Maven 3.x || Eclipse
* JVM 8.x
* A decent IDE, like IDEA, Eclipse

## Get it ##

    git clone https://github.com/kantega/metrics-workshop.git

or download the [ZIP](https://github.com/kantega/metrics-workshop/archive/master.zip).

## Usage ##

__Maven__

    mvn clean install
    cd webapp
    mvn jetty:run

__IntelliJ IDEA & Maven__

    Download and install Maven from http://maven.apache.org/download.html
    Download and install IDEA from https://www.jetbrains.com/idea/download/
    Open Project -> select pom.xml
    Click 'Maven Project' on Right menu bar -> metrics-workshop -> Plugins -> Right click jetty:run -> Run Maven Build
    If asked about "No Maven installation..", click 'configuration dialog', and set 'Maven home location' to installed Maven home dir
    Access application with browser on http://localhost:8080

__Stand-alone Eclipse__

    Download and install Maven from http://maven.apache.org/download.html
    Import -> Maven -> Existing Maven Projects
    Root Directory: Select metrics-workshop path -> Next -> Finish
    Under 'Project Explorer Window' Select project root, then go to Run Menu -> Run As -> Maven Build (first one)
    Write 'install jetty:run' in Goals -> Run
    Access application with browser on http://localhost:8080

__Eclipse & Maven__

    mvn -Declipse.workspace="path to your Eclipse Workspace" eclipse:configure-workspace
    mvn eclipse:eclipse
    Start Eclipse
    File > Import > Existing Projects into Workspace
    Root Directory: Select metrics-workshop path -> Finish
    Under Project Explorer Window Select project root, then go to Run Menu -> Run Configurations, double click Maven Build
    Under Base directory -> Browser Workspace -> Select metrics-workshop
    Write 'install jetty:run' in Goals -> Run
    Access application with browser on http://localhost:8080
