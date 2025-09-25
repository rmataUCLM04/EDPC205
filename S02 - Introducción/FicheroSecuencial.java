package practicas_25_26;

/*********************************************************************
*
* Class Name:
* Author/s name:
* Release/Creation date:
* Class version:
* Class description: A brief description of what the class does
*
**********************************************************************
*/

import java.io.*;
import java.util.*;
public class FicheroSecuencial <T extends LineaProcesable> {
	
	private File file;
	private Scanner scan;
	private String separator;
	
	/*********************************************************************
	*
	* Method name:
	*
	* Name of the original author (if the module author is different
	* than the author of the file):
	*
	* Description of the Method: A description of what the method does.
	*
	* Calling arguments: A list of the calling arguments, their types, and
	* brief explanations of what they do.
	*
	* Return value: it type, and a brief explanation of what it does.
	*
	* Required Files: A list of required files needed by the method,
	* indicating if the method expects files to be already opened (only if
	* files are used)
	*
	* List of Checked Exceptions and an indication of when each exception
	* is thrown.
	*
	*********************************************************************/
	
	public FicheroSecuencial(File file, Scanner scan, String separator) {
		this.file = file;
		this.scan = scan;
		this.separator = separator;
	}
	
	

	/*********************************************************************
	*
	* Method name:
	*
	* Name of the original author (if the module author is different
	* than the author of the file):
	*
	* Description of the Method: A description of what the method does.
	*
	* Calling arguments: A list of the calling arguments, their types, and
	* brief explanations of what they do.
	*
	* Return value: it type, and a brief explanation of what it does.
	*
	* Required Files: A list of required files needed by the method,
	* indicating if the method expects files to be already opened (only if
	* files are used)
	*
	* List of Checked Exceptions and an indication of when each exception
	* is thrown.
	*
	*********************************************************************/
	
	public void read(T objeto) {
		String[] data = scan.nextLine().split(separator);
		objeto.leerDatos(data);
	}

	
	/*********************************************************************
	*
	* Method name:
	*
	* Name of the original author (if the module author is different
	* than the author of the file):
	*
	* Description of the Method: A description of what the method does.
	*
	* Calling arguments: A list of the calling arguments, their types, and
	* brief explanations of what they do.
	*
	* Return value: it type, and a brief explanation of what it does.
	*
	* Required Files: A list of required files needed by the method,
	* indicating if the method expects files to be already opened (only if
	* files are used)
	*
	* List of Checked Exceptions and an indication of when each exception
	* is thrown.
	*
	*********************************************************************/
	
	public void skip() {
		scan.nextLine();
	}
	
	/*********************************************************************
	*
	* Method name:
	*
	* Name of the original author (if the module author is different
	* than the author of the file):
	*
	* Description of the Method: A description of what the method does.
	*
	* Calling arguments: A list of the calling arguments, their types, and
	* brief explanations of what they do.
	*
	* Return value: it type, and a brief explanation of what it does.
	*
	* Required Files: A list of required files needed by the method,
	* indicating if the method expects files to be already opened (only if
	* files are used)
	*
	* List of Checked Exceptions and an indication of when each exception
	* is thrown.
	*
	*********************************************************************/
	
	public void close() {
		scan.close();
	}
	
	/*********************************************************************
	*
	* Method name:
	*
	* Name of the original author (if the module author is different
	* than the author of the file):
	*
	* Description of the Method: A description of what the method does.
	*
	* Calling arguments: A list of the calling arguments, their types, and
	* brief explanations of what they do.
	*
	* Return value: it type, and a brief explanation of what it does.
	*
	* Required Files: A list of required files needed by the method,
	* indicating if the method expects files to be already opened (only if
	* files are used)
	*
	* List of Checked Exceptions and an indication of when each exception
	* is thrown.
	*
	*********************************************************************/
	
	public boolean isEndOfFile() {
		return !scan.hasNextLine();
	}
}
