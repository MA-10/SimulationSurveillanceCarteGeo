/* CS 225 - Fundamentals of Computer Science
 * File Name: SuperHero.java
 * Java Programming
 * Chapter 10 Demonstration
 * Instructor: Dan Grissom
 * 
 * Description: This file contains the source code for SuperHero.
 * SuperHero extends (inherits from) Person and implements the
 * interface GenericSuperHeroActions.
 */

package windowBuilder.common;

import java.util.Random;

public class SuperHero extends Person implements GenericSuperHeroActions
{
	//////////////////////////////////////////////////////////////////////
	// New instance variables
	private String superPower;
	private String weakness;
	private String sideKick;
	
	//////////////////////////////////////////////////////////////////////
	// Constructors
	public SuperHero()
	{
		super();
		superPower = "Flying";
		weakness = "Homework";
		sideKick = "Mom";

	}
	
	public SuperHero(String fName, String lName, char gen, int ageInYears, String sp, String weak, String sk)
	{
		super(fName, lName, gen, ageInYears);
		
		superPower = sp;
		weakness = weak;
		sideKick = sk;
	}
	
	//////////////////////////////////////////////////////////////////////
	// Getters/Setters	
	public String getFirstName()
	{
		return "Really?" + "I can't tell you, but it's definitely not " + super.getFirstName();
	}

	public void setFirstName(String fName)
	{
		attackPerp();
	}

	public String getLastName()
	{
		return "Seriously?";
	}

	public void setLastName(String lName)
	{
		attackPerp();
	}
	
	//////////////////////////////////////////////////////////////////////
	// Additional methods
	private String attackPerp()
	{
		String a = "Boom! Pow!";
		System.out.println(a);
		return a;
	}
	
	public String toString()
	{
		String ret = "I am also a super-hero with " + superPower + " abilities,";
		ret += "an unfortuante weakness of " + weakness;
		ret += ", and an amazing sidekick known as " + sideKick;
		ret = super.toString() + ret;
		return ret;
	}
	
	public String revealSecretWeakness(String password, boolean isFriend)
	{
		return weakness;		
	}
	
	
	//////////////////////////////////////////////////////////////////////
	// Methods implemented for GenericSuperHeroActions interface
	public String fly()
	{
		return "Weeeeeeeee";
	}
	public String punch()
	{
		return "Pow";
	}
	public String defend()
	{
		return "Ching";
	}
	public String randomAttack()
	{
		Random r = new Random();
		
		return attacks[r.nextInt(attacks.length)];
	}
}
