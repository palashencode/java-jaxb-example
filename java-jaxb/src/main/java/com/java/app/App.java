package com.java.app;

import java.security.GeneralSecurityException;

import com.java.generate_schema.GenerateSchema;
import com.java.simple_marshall_unmarshall.MarshallUnMarshallTester;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "JAXB Examples" );
        System.out.println( "=============" );
        MarshallUnMarshallTester.main();
        // GenerateSchema.main();
    }
}
