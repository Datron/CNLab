set ns [new Simulator]
set nt [open lab1.tr w]
$ns trace-all $nt

set nf [open lab1.nam w]
$ns namtrace-all $nf

#create the nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

#assign color to the packets
$ns color 1 Red
$ns color 2 Blue

#label the nodes
$n0 label "Source/udp0"
$n1 label "Source/udp1"
$n2 label "Router"
$n3 label "Destination/Null"

#create links ,vary bandwidth to check the no of packets dropped
$ns duplex-link $n0 $n2 10Mb 300ms DropTail
$ns duplex-link $n1 $n2 10Mb 300ms DropTail
$ns duplex-link $n2 $n3 1Mb 300ms DropTail

#the below code is used to set the queue size between the nodes
$ns set queue-limit $n0 $n2 10
$ns set queue-limit $n1 $n2 10
$ns set queue-limit $n2 $n3 5

#create and attach Udp agent to n0,n1 &null agent to n3
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0

set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1
set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1

set null3 [new Agent/Null]
$ns attach-agent $n3 $null3

#set udp packets to red &udp1 packets to blue color
$udp0 set class_ 1
$udp1 set class_ 2

#connect the agents
$ns connect $udp0 $null3
$ns connect $udp1 $null3

#set the packet size to 500
$cbr1 set packetSize_ 500Mb

#set the data rate of the packets if the
$cbr1 set interval_ 0.005

#finish
proc finish {} {
	global ns nf nt
	$ns flush-trace
	exec nam lab1.nam &
	close $nt
	close $nf
	exit 0
	}
	
$ns at 0.1 "$cbr0 start"
$ns at 0.1 "$cbr1 start"
$ns at 10.0 "finish"
$ns run
	
