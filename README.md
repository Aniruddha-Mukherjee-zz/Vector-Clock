# Vector-Clock
Vector Clock assignment for Distributed Computing Lab

javac Peer/*.

java Peer.Server 0

java Peer.Server 1

java Peer.Server 2

java Peer.Server 3


API(s) supported:

event               : Local Event. Clock++

Show                : Show the entire clock stored at that node

send x              : Send the vector clock to peer x. 
                      sender.clock++  as well as  receiver.clock++
