BEGIN{c=0;x=0;}
{
	if($1=="r")
	{c++;
	printf("%s\t %s\n",$5,$11);
	}
	if($1=="d")
	{x++;
	printf("%s\t %s\n",$5,$11);
	}

}
END{
printf("the number of packets dropped = %d\n",x);
printf("the number of packets received = %d\n",c);
printf("the thorughput is = %f\n",(c/(c+x))*100);
}
