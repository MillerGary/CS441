# FileSocketClient & FileSocketServer Technical Diagram and Desciption

+ Gary Miller

The FilesocketClient and FileSocketServer system interaction is set up such that the client side of the system is a given the path to a file as an argument. The client is also given a host name and port number to use in order to establish a connection with the server, this connection can be made either locally or remotely over the same local area network. After initializing a ClientSocket the FileSocketClient.java class identifies the file that is to be transmitted and reads it in byte by byte, this information is then transfered over the connection that was just established. The FileSocketClient.java class was updated so that it automatically determines the size of the file and allocates a buffer of that same size. The server then receives from the client the size of the file that is about to sent and sets its own buffer to that size, finally the server reads the file byte by byte and saves it to the specified directory. This is visually depicted in the technical diagram included below.


![Figure I](/home/m/millerg2/CS441/cs441spring2016-millerg2/lab2/Documents/FileSocketClientServerDiagram.pdf)
