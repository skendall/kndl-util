package org.kndl.util.classloader;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Loads a class from any selection of remote jars.
 *
 *
 */
public class KndlClassLoader extends ClassLoader {

    private static final Logger     logger = Logger.getLogger(KndlClassLoader.class);

    private List<URLClassLoader> jarLocations;

    private List<Class> loadedClasses;
    
    public KndlClassLoader() {
        this.jarLocations = new LinkedList<URLClassLoader>();
        this.loadedClasses = new ArrayList<Class>();
    }

    public void addLocation(URL url) {
        URLClassLoader ucl = new URLClassLoader(new URL[] {url},this.getClass().getClassLoader());
        jarLocations.add(ucl);
    }

    public Class load(String className) {
        Class c = null;
        for(URLClassLoader loader : jarLocations) {
        	for(URL url : loader.getURLs())
        		System.out.println("Loading " + url);
            try {
                c = Class.forName(className,true,loader);
            } catch (ClassNotFoundException e) {
                logger.error(e);
            }
        }
        return c;
    }
    
	public List<Class> loadAll() {
		loadedClasses.clear();
		
		for (URLClassLoader loader : jarLocations) {
			for (URL url : loader.getURLs()) {
				logger.info("Loading: " + url);
				try {
					JarInputStream stream = new JarInputStream(url.openStream());
					JarEntry entry = stream.getNextJarEntry();

					while (entry != null) {
						String name = entry.getName();
						if (name.endsWith(".class")) {
							int i = name.lastIndexOf("/");

							try {
								Class c = Class.forName(name.substring(0, name.length() - 6).replace("/", "."), 
										false,
										loader);
								loadedClasses.add(c);
								logger.info("Loaded " + c.getName());
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								logger.error(e.getMessage());
							}
						}
						entry = stream.getNextJarEntry();
					}

					stream.close();
				} catch (IOException ioe) {
					logger.error(ioe);
				}
			}
		}

		return loadedClasses;
	}
	
	public List<Class> getClasses() {
		return loadedClasses;
	}

    public static void main(String args[]) {
        KndlClassLoader ncl = new KndlClassLoader();
        try {
            ncl.addLocation(new URL("http://pants.spacerobots.org/test.jar"));
            ncl.addLocation(new URL("http://repo1.maven.org/maven2/io/undertow/undertow-core/1.0.0.Beta12/undertow-core-1.0.0.Beta12.jar"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        List<Class> classes = ncl.loadAll();
        for(Class c : classes) {
        	System.out.println("Class: " + c.getName());
        }
    }
}
