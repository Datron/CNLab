set ns [new Simulator]
set trf [open lab4.tr w]
$ns trace-all $trf

set topo [new Topography]

$topo load_flatgrid 1000 1000

set naf [open lab4.nam w]

$ns namtrace-all-wireless $naf 1000 1000

$ns node-config -adhocRouting DSDV \
       -llType LL \
    -macType Mac/802_11 \
    -ifqType Queue/DropTail \
    -ifqLen 20 \
    -phyType Phy/WirelessPhy \
    -channelType Channel/WirelessChannel \
    -propType Propagation/TwoRayGround \
    -antType Antenna/OmniAntenna \
    -topoInstance $topo \
    -agentTrace ON \
    -routerTrace ON
create-god 3
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$n0 label "tcp0"
$n1 label "sink1/tcp1"
$n2 label "tcp1"
$n3 label "sink2/tcp2"

$n0 set X_ 70
$n0 set Y_ 70
$n0 set Z_ 0
$n1 set X_ 120
$n1 set Y_ 120
$n1 set Z_ 0
$n0 set X_ 500
$n0 set Y_ 500
$n0 set Z_ 0

$ns at 0.3 "$n0 setdest 70 70 10"
$ns at 0.3 "$n0 setdest 120 120 20"
$ns at 0.3 "$n0 setdest 500 500 30"

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

set sink1 [new Agent/TCPSink]
$ns attach-agent $n1 $sink1
$ns connect $tcp0 $sink1

set tcp1 [new Agent/TCP]
$ns attach-agent $n2 $tcp1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1

set sink2 [new Agent/TCPSink]
$ns attach-agent $n3 $sink2
$ns connect $tcp1 $sink2

$ns at 5 "$ftp0 start"
$ns at 10 "$ftp1 start"

$ns at 120 "$n2 setdest 600 600 20"

proc finish { } {
global ns naf trf
$ns flush-trace
exec nam lab4.nam &
close $trf
close $naf
exit 0
}

$ns at 400 "finish"
$ns run
