/* CS 225 - Fundamentals of Computer Science
 * File Name: Lex_Luthor_Client.java
 * Java Programming
 * Chapter 10 Demonstration
 * Instructor: Dan Grissom
 * 
 * Description: This file contains the source code for Person.
 * Person inherits social security number.
 */

package windowBuilder.common;

public class Person extends SocialSecurityNumber
{
	private String firstName;
	private String lastName;
	private char gender;
	private int age;
	
	//////////////////////////////////////////////////////////////////////
	// Constructors
	public Person()
	{
		firstName = "Jim";
		lastName = "Doe";
		gender = 'M';
		age = 0;
	}
	
	public Person(String fName, String lName, char gen, int ageInYears)
	{
		firstName = fName;
		lastName = lName;
		gender = gen;
		age = ageInYears;
	}

	//////////////////////////////////////////////////////////////////////
	// Getters/Setters	
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String fName)
	{
		firstName = fName;
	}
	public String getLastName()
	{
		return lastName;
	}	
	public String getLastName(boolean isJr) // Overloaded method
	{
		if (isJr)
			return lastName + ", Jr.";
		else
			return getLastName();
	}
	public void setLastName(String lName)
	{
		lastName = lName;
	}
	public char getGender()
	{
		return gender;
	}
	public void setGender(char gen)
	{
		gender = gen;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int ageInYears)
	{
		age = ageInYears;
	}
	
	public String getDisplayName()
	{
		return firstName + "-" + lastName;
	}
	
	//////////////////////////////////////////////////////////////////////
	// toString() overridden method
	public String toString()
	{
		String ret = "I am a " + age + " year old ";
		if (gender == 'M' || gender == 'm')
			ret += "male ";
		else
			ret += "female ";
		ret += "and my name is " + firstName + " " + lastName + ".";
		
		ret += " Also, b/c I trust you, my SSN is " + getSsnString("password123") + ".";
		
		return ret;
	}

	
	
	
	
}
