# Deklin

A 667 (remake) rsps

#Table of contents

1. [ Run the server ](#runserver)
2. [ Run the client ](#runclient)
3. [ Useful Information ](#usefulinformation)
4. [ Server information ](#usefulinformationserver)
    
    4.1 [ Dialogue information ](#usefulinformationserverdialogue)
    
6. [ Client information ](#usefulinformationclient)

    5.1 [ Mouse events ](#mouseclickeventsclient)

<a name="runserver"></a>
## Run the Server

You need the cache: https://mega.nz/#!3NNlVZ4Z!SGIl2l1-GtDkBhIDFkxGvx1prwKEq6wnsaaHmmBIpiY

Unpack zip in data folder to get structure /data/cache/*unpacked cache files*

Run com.rs.Launcher
Args: true/false (debug mode)

<a name="runclient"></a>
## Run the client

Run Loader class

<a name="usefulinformation"></a>
## Useful information

<a name="usefulinformationserver"></a>
### Server

<a name="usefulinformationserverdialogue"></a>
#### Dialogues

##### Creating dialogue

##### Calling dialogue
A dialogue can be created by using  

    Dialogue testdialogue = new SimpleMessage();

Where "SimpleMessage" is a class extending Dialogue, check it for reference.     

Then, to run the dialogue type the following: 

    player.getDialogueManager().startDialogue(testdialogue);
    
As a developer you can also run the "startdialogue" command to see this in action

##### Binding dialogue to npc
Server\src\com\rs\game\actionHandling\handlers\NPCHandler.java

###### Quest Implementation


###### Other
    
<a name="usefulinformationclient"></a>
### Client

<a name="mouseclickeventsclient"></a>
#### Mouse events

Mouse events are handled in following classes
- Class93_Sub1.java
- Class93_Sub2.java

#### Client zoom
aShort3455 in Class268.java, 320 default zoom