BEGIN{
pktsent=0;
pktrcvd=0;
pktrtr=0;
}
{
	if($1=="s" && $4 == "RTR" && $7 == "tcp")
	{
    pktrtr++;
	}
	if($1=="s" && $4 == "AGT" && $7 == "tcp")
	{
    pktsent++;
	}
    if($1=="r" && $4 == "AGT" && $7 == "tcp")
	{
    pktrcvd++;
	}

}
END{
printf("the number of packets sent = %d\n",pktsent);
printf("the number of packets received = %d\n",pktrcvd);
printf("\nPacket Delivery Ratio = %f",pktrcvd/pktsent * 100);
printf("\nRouting Load = %f",pktrtr/pktrcvd);
}
