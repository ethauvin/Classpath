/*
 * @(#)Classpath.java
 *
 * Copyright (C) 2001 by Erik C. Thauvin (erik@thauvin.net)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * A copy of the GNU General Public License can be obtained by
 * visting the Free Software Foundation Web site at:
 *
 *		<http://www.gnu.org/copyleft/gpl.html>
 *
 * or by writting to:
 *
 *		Free Software Foundation, Inc.
 *		59 Temple Place - Suite 330
 *		Boston, MA 02111-1307, USA.
 *
 * $Id$
 */
import java.io.*;
import java.util.StringTokenizer;

/**
 * The Classpath class demonstrates how to check for the presence of a given
 * library in the system class path.
 *
 * @author Erik C. Thauvin
 * @created September 7, 2001
 */
public class Classpath extends java.lang.Object
{

	/**
	 * Returns true if the given library is included the system class path, and
	 * actually exists on disk. Returns false otherwise.
	 *
	 * @param libName The name of the library
	 * @return true or false.
	 */
	public static boolean isLibInClassPath(String libName)
	{
		// Get the class path
		String classPath =
				System.getProperty("java.class.path", ".").toLowerCase();

		// Get the library name (e.g.: "\tools.jar")
		String lib = (File.separator + libName.toLowerCase());

		// Tokenize the class path using the path separator
		StringTokenizer st =
				new StringTokenizer(classPath, File.pathSeparator);

		String libPath;
		File filePath;

		// Loop thru the class path entries
		while (st.hasMoreTokens())
		{
			// Get the current class path entry
			libPath = st.nextToken();

			// Does it contain our library?
			if (libPath.endsWith(lib))
			{
				filePath = new File(libPath);

				// Does the file actually exists?
				if (filePath.exists())
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * The main program for the Classpath class.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args)
	{
		if (args.length == 1)
		{
			System.out.print("The \"" + args[0] + "\" library was ");

			if (!Classpath.isLibInClassPath(args[0]))
			{
				System.out.print("NOT ");
			}

			System.out.println("found in the system class path.");
		}
		else
		{
			// e.g.: java -cp .;C:\jdk1.3\lib\tools.jar Classpath tools.jar
			System.err.println("Usage: java Classpath <library name>");
		}
	}
}

