/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.vitro.webapp.search.documentBuilding;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cornell.mannlib.vitro.webapp.beans.IndividualImpl;
import edu.cornell.mannlib.vitro.webapp.beans.VClass;

public class ExcludeBasedOnTypeTest {

	@Test
	public void testCheckForExclusion() {
		
		ExcludeBasedOnType ebot = new ExcludeBasedOnType();
		ebot.addTypeToExclude("http://xmlns.com/foaf/0.1/Person");
		
		IndividualImpl ind = new IndividualImpl();
		ind.setURI("http://example.com/n2343");
		VClass personClass = new VClass("http://xmlns.com/foaf/0.1/Person");		
		ind.setVClass(personClass);
		
		String excludeResult = ebot.checkForExclusion(ind);
		assertNotNull( excludeResult );		
	}

	@Test
	public void testCheckForExclusion2() {
		
		ExcludeBasedOnType ebot = new ExcludeBasedOnType();
		ebot.addTypeToExclude("http://example.com/KillerRobot");
		
		IndividualImpl ind = new IndividualImpl();
		ind.setURI("http://example.com/n2343");
		VClass personClass = new VClass("http://xmlns.com/foaf/0.1/Agent");		
		ind.setVClass(personClass);
		
		List<VClass> vClassList = new ArrayList<VClass>();
		vClassList.add( new VClass("http://example.com/Robot"));
		vClassList.add( new VClass("http://example.com/KillerRobot"));
		vClassList.add( new VClass("http://example.com/Droid"));
		ind.setVClasses(vClassList, true);
		
		String excludeResult = ebot.checkForExclusion(ind);
		assertNotNull( excludeResult );		
	}
	
	@Test
	public void testCheckForNonExclusion() {
		
		ExcludeBasedOnType ebot = new ExcludeBasedOnType();
		ebot.addTypeToExclude("http://xmlns.com/foaf/0.1/Person");
		
		IndividualImpl ind = new IndividualImpl();
		ind.setURI("http://example.com/n2343");
		VClass personClass = new VClass("http://xmlns.com/foaf/0.1/Robot");		
		ind.setVClass(personClass);
		
		String excludeResult = ebot.checkForExclusion(ind);
		assertNull( excludeResult );		
	}
	
	@Test
	public void testCheckForNonExclusion2() {		
		ExcludeBasedOnType ebot = new ExcludeBasedOnType();
		ebot.addTypeToExclude("http://xmlns.com/foaf/0.1/Person");
		
		IndividualImpl ind = new IndividualImpl();
		ind.setURI("http://example.com/n2343");
		VClass personClass = new VClass("http://xmlns.com/foaf/0.1/Agent");		
		ind.setVClass(personClass);
		
		List<VClass> vClassList = new ArrayList<VClass>();
		vClassList.add( new VClass("http://example.com/Robot"));
		vClassList.add( new VClass("http://example.com/KillerRobot"));
		vClassList.add( new VClass("http://example.com/Droid"));
		ind.setVClasses(vClassList, true);
		
		String excludeResult = ebot.checkForExclusion(ind);
		assertNull( excludeResult );		
	}
}
