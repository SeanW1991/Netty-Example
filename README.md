This project is an example of using Netty to create a TCP Client and Server in an Event driven manner for handling incoming and outgoing messages.

Incoming messages are decoded into Events and then handled by its corrosponding listeners.

Outgoing messages are translated from its corrosponding Event to a Netty ByteBuf.

This code was designed to expand on the example code provided by Netty. The client and server has been abstracted out
to allow for other supporting protocols within the Netty API to be supported easier. For example Websockets and UDT.





