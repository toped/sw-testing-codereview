import java.io.IOException;
import java.util.logging.Logger;

public class ControlFlow
{
    public ControlFlow()
    {
        byte inputBuffer[] = new byte[ 128 ];
        try
            {
                // Read data from the standard input
                int byteCount = System.in.read( inputBuffer );
                
                // Check whether data has been read or not
                if( byteCount <= 0 )
                    {
                        return;
                    }
                
                int i = 1;
                switch ( i )
                    {
                    case 1:
                        // Turn data into a String
                        tring s = new String( inputBuffer );
                        s = s.substring( 0, byteCount-2 
                                                
                                         if( ( s.equals( "admin" ) ) == true )
                                             {
                                                 authorize( s );
                                             }
                        default:
                                         break;
                                         }
                    }
                catch ( IOException e )
                    {
                        final Logger logger = Logger.getAnonymousLogger();
                        String exception = "Exception " + e;
                        logger.warning( exception );
                    }
            }
        
    
    }
