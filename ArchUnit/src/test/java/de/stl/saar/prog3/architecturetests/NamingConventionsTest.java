package de.stl.saar.prog3.architecturetests;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class NamingConventionsTest {

	/**
	 * Die Namen der Interfaces im Paket service.interfaces sollen mit Service enden. Ob tatsaechlich
	 * nur Interfaces in diesem Paket liegen, kann dann mit einem separaten Testfall getestet werden.
	 */
	@Test
	void service_interfaces_should_have_suffix_Service() {
		JavaClasses importedClasses = new ClassFileImporter().importPackages("de.stl.saar.prog3");
		ArchRule archRule = classes().
				that().
				resideInAPackage("..service.interfaces").
				should().
				haveSimpleNameEndingWith("Service");
		archRule.check(importedClasses);
	}

	/**
	 * Die Namen der Klassen im Paket service.implementations sollen mit ServiceImpl enden. Ob tatsaechlich
	 * nur Klassen und keine Interfaces in diesem Paket liegen, kann dann mit einem separaten Testfall getestet werden.
	 */
	@Test
	void service_implementations_should_have_suffix_ServiceImpl() {
		JavaClasses importedClasses = new ClassFileImporter().importPackages("de.stl.saar.prog3");
		ArchRule archRule = classes().
				that().
				resideInAPackage("..service.implementations..").
				should().
				haveSimpleNameEndingWith("ServiceImpl");
		archRule.check(importedClasses);
	}
	
	/**
	 * Die Namen der Klassen im Paket view.fx.controllers sollen mit Controller enden.
	 */
	@Test
	void controllers_should_have_suffix_Controller() {
		JavaClasses importedClasses = new ClassFileImporter().importPackages("de.stl.saar.prog3");
		ArchRule archRule = classes().
				that().
				resideInAPackage("..view.fx.controllers..").
				should().
				haveSimpleNameEndingWith("Controller");
		archRule.check(importedClasses);
	}
}