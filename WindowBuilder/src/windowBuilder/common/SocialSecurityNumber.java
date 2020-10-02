/* CS 225 - Fundamentals of Computer Science
 * File Name: SocialSecurityNumber.java
 * Java Programming
 * Chapter 10 Demonstration
 * Instructor: Dan Grissom
 * 
 * Description: This file contains the source code for the
 * social security number. It is abstract b/c you would never
 * create a SSN without a person.
 */

package windowBuilder.common;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public abstract class SocialSecurityNumber
{
	private static long nextSSN = 1;
	private long ssn;
	
	//////////////////////////////////////////////////////////////////////
	// Constructor
	public SocialSecurityNumber()
	{
		ssn = nextSSN++;
	}
	
	//////////////////////////////////////////////////////////////////////
	// Member methods
	protected long getSSN(String password)
	{
		if (password.equals("password123"))
			return ssn;
		else
			return -1;
	}
	
	protected String getSsnString(String password)
	{
		//DecimalFormat df = new DecimalFormat("000000000");
		if (password.equals("password123"))
			return convertSsnToString(getSSN(password));
		else
			return "You do not have access to my SSN.";
	}
	
	private String convertSsnToString(long ssn)
	{
		String ssnStr = "";
		long divideBy = 100000000;
		while (divideBy > 0)
		{
			long digit = ssn / divideBy;
			ssnStr += (ssn / divideBy);
			ssn -= (digit * divideBy); // Get rid of digit in overall number
			divideBy /= 10; // Go to next digit
			
			if (divideBy == 100000 || divideBy == 1000)
				ssnStr += "-";
		}
		return ssnStr;
	}
}
