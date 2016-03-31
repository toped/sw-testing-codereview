#include <stdio.h>
#include <string.h>

char *sequence = "class department college office field library";
void unique( const char *list, char *buf )
{
  char name[100];
  int k;
  buf[0] = 0;
  for(;;)
    {
      while( *list == ' ' ) list++;
      k = sscanf( list, "%s", name );
      if( k != 1 ) break;
      if( !strstr( buf, name ) )
        { strcat( buf, " " );
          strcat( buf, name ); }
      list += strlen(name);
    }
}

int main()
{
  char types[1000];
  unique( types, sequence );
  printf( "Facilities used: %s\n", types );
  return 0;
}
