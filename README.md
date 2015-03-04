# Metrics for Developers #

## About ##

Metrics workshop at [Booster](http://www.boosterconf.no/) 2015.

## Requirements ##

* Git 1.7.10+
* Apache Maven 3.1.x
* JVM 8.x

## Get it ##

    git clone https://github.com/kantega/metrics-workshop.git

or download the [ZIP](https://github.com/kantega/metrics-workshop/archive/master.zip).

## Usage ##

__Maven__

    mvn clean install
    cd webapp
    mvn jetty:run

__Maven with remote debug (Recommended)__

1. Run the following:

        mvn clean install
        cd webapp
        mvnDebug jetty:run

2. Attach a debugger to port 8000


__IntelliJ IDEA & Maven__

1. Download and install [Maven](https://maven.apache.org/download.html)
2. Download and install [IDEA](https://www.jetbrains.com/idea/download)
3. Start IDEA, Open Project -> select metrics-workshop/pom.xml
4. Click Build -> Make Project
5. Click 'Maven Project' on Right menu bar -> webapp -> Plugins -> Right click jetty:run -> Debug 'webapp' \[jetty:run\]
    * If asked about "No Maven installation..", click 'configuration dialog', and set 'Maven home location' to installed Maven home dir
6. Open up your favorite browser to [http://localhost:8080](http://localhost:8080)


__Eclipse (Luna required)__

1. Download and install [Eclipse IDE for Java EE Developers (Luna)](https://eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/lunar)
2. Import -> Maven -> Existing Maven Projects
    * Root Directory: Select metrics-workshop path -> Next -> Finish
3. Under 'Project Explorer Window' Select project webapp, then go to Run Menu -> Debug As -> Maven Build (first one)
    * Write 'install jetty:run' in Goals -> Debug
    * To modify settings afterwards go to Run Menu -> Debug Configuration
4. Open up your favorite browser to [http://localhost:8080](http://localhost:8080)
