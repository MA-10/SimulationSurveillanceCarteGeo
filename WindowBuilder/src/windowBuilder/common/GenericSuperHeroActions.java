/* CS 225 - Fundamentals of Computer Science
 * File Name: Lex_Luthor_Client.java
 * Java Programming
 * Chapter 10 Demonstration
 * Instructor: Dan Grissom
 * 
 * Description: This file contains the source code for the
 * GenericSuperHeroActions interface. Any class that implements
 * this interface MUST implement all 4 methods.
 */

package windowBuilder.common;

public interface GenericSuperHeroActions
{
	//////////////////////////////////////////////////////////////////////
	// Static final instance variables
	public static final String [] attacks = {"Pow", "Wahm", "Wop", "Ching", "Foo", "Bam" };
	
	//////////////////////////////////////////////////////////////////////
	// Abstract methods
	public String fly();
	public String punch();
	public String defend();
	public String randomAttack();
}
