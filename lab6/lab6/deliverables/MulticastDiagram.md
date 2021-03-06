# Multicasting Communication Diagram and Description

+ Gary Miller

Multicasting is an approach to establishing a client server connection, when there are multiple clients and their location is generally unknown. This differs from unicasting, because the server sends out a message to any client that is in range an listening, opposed unicasting is less realistic because it is a form of point to point communication and requires the location of each client of be known. Multicasting can is analogous to shouting in a room, whoever is can hear the message is capable of listening. This is done by establishing a multicast group, which takes the messages sent by the server and sends them out to anyone client that wants to listen. The clients are then capable of establishing a connection to the server through the multicast group, and effectively exchange messages between itself and the client. This concept is visually depicted in Figure I.

![Diagram of Multicasting Communication](/home/m/millerg2/CS441/cs441spring2016-millerg2/lab6/lab6/deliverables/MulticastingDiagram.pdf)
