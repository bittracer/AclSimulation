# AclSimulation

## Scenario 1: 

Prevent traffic from 172.16.4.13 going to 172.16.3.2 and traffic from 172.16.5.2 going to 172.16.3.4

#### Acl.txt

access-list 1 deny 172.16.4.13 0.0.0.0 
access-list 1 permit 172.16.5.0 0.0.0.255
access-list 1 deny any

interface EO
ip access-group 1 out

#### packet.txt

172.16.4.13 172.16.3.2
172.16.5.2 172.16.3.4


## Scenario 2: 

Prevent telnet access from 192.168.3.1 reaching to 192.168.1.1 and access from 192.168.4.0 reaching to 192.168.3.1

#### Acl.txt

access-list 101 deny tcp 192.168.3.1 0.0.0.0 192.168.1.1 0.0.0.0 23
access-list 101 permit tcp 192.168.4.0 0.0.0.255 192.168.3.0 0.0.0.0 23
access-list 101 permit any any

interface E1
ip access-group 101 in

#### packet.txt

192.168.3.1 192.168.1.1 50000 23
192.168.4.0 192.168.3.1 51000 23
