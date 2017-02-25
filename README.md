# AclSimulation

##Scenario 1.

Prevent all traffic from 192.168.2.0 from going to 192.168.1.0 

#### Acl.txt file

access-list 1 deny 192.168.2.0 0.0.0.0 

access-list 1 permit any

interface EO

ip access-group 1 out

#### packet.txt file

192.168.2.0 192.168.1.0

## Scenario 2.

Prevent all traffic from 192.168.3.1 from going to 192.168.2.1 

#### Acl.txt file

access-list 1 deny 192.168.3.1 0.0.0.0 

access-list 1 permit any

interface EO

ip access-group 1 out

#### packet.txt file

192.168.3.1 192.168.2.1


## Scenario 3.

Prevent FTP access from 2.1 to 3.1

#### Acl.txt file

access-list 101 deny TCP 192.168.2.1 0.0.0.0 192.168.3.1 0.0.0.0 20

access-list 101 deny TCP 192.168.2.1 0.0.0.0 192.168.3.1 0.0.0.0 21

access-list 101 permit ip any any 

interface E1

ip access-group 101 in

#### packet.txt file

192.168.2.1 192.168.3.1 53000 20

192.168.2.1 192.168.3.1 54000 21


## Scenario 4.

Prevent Telnet and FTP access from 3.1 to 1.1 

#### Acl.txt file

access-list 102 deny TCP 192.168.3.1 0.0.0.0 192.168.1.1 0.0.0.0 23

access-list 102 deny TCP 192.168.3.1 0.0.0.0 192.168.1.1 0.0.0.0 20

access-list 102 deny TCP 192.168.3.1 0.0.0.0 192.168.1.1 0.0.0.0 21

access-list 102 permit ip any any 

interface E2

ip access-group 102 in

#### packet.txt file

192.168.3.1 192.168.1.1 55000 23

192.168.3.1 192.168.1.1 56000 20

192.168.3.1 192.168.1.1 57000 21


## Scenario 5:

Prevent traffic from 172.16.4.13 going to 172.16.3.2 and traffic from 172.16.5.2 going to 172.16.3.4

#### Acl.txt file

access-list 1 deny 172.16.4.13 0.0.0.0 

access-list 1 permit 172.16.5.0 0.0.0.255

access-list 1 deny any

interface EO

ip access-group 1 out

#### packet.txt file

172.16.4.13 172.16.3.2

172.16.5.2 172.16.3.4


## Scenario 6:

Prevent telnet access from 192.168.3.1 reaching to 192.168.1.1 and access from 192.168.4.0 reaching to 192.168.3.1

#### Acl.txt file

access-list 101 deny tcp 192.168.3.1 0.0.0.0 192.168.1.1 0.0.0.0 23

access-list 101 permit tcp 192.168.4.0 0.0.0.255 192.168.3.0 0.0.0.0 23

access-list 101 permit ip any any

interface E1

ip access-group 101 in

## packet.txt file

192.168.3.1 192.168.1.1 50000 23

192.168.4.0 192.168.3.1 51000 23
