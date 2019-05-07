This is a bulldozer simulation in Java implemented using Command Pattern. 


To generate jar use the following command:

    mvn clean install assembly:single 

this will create a jar file inside *target* directory:

Simulation.jar



This program starts by receiving input from a file. To achieve that, follow the steps:

1. Navigate to *target*  directory inside the project:

``````
>cd sitesimulator\target
``````

2. Run the command to execute jar and pass a test file as an argument from src/test/resources directory.

   
    java -jar Simulation.jar ../src/test/resources/map

    
_____________

