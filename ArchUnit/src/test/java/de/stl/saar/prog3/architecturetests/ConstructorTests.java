package de.stl.saar.prog3.architecturetests;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

class ConstructorTests {
	/**
	 * Werkzeugklassen, die nur statische Methoden enthalten, liegen in dem Paket utils (dies kann in einem
	 * anderen Test geprueft werden). Hier wird geprueft, ob die Klassen in dem Paket utils einen privaten
	 * Konstruktor haben, denn eine Objekterzeugung soll nicht moeglich sein.
	 */
	@Test
	void utils_classes_should_only_have_private_constructors() {
		JavaClasses importedClasses = new ClassFileImporter().
				importPackages("de.stl.saar.prog3");
		
		ArchRule archRule = constructors().that().
										   areDeclaredInClassesThat().
										   resideInAnyPackage("..utils..").
										   should().
										   bePrivate();
		
		archRule.check(importedClasses);
		
	}

	/**
	 * Prueft, ob die Klassen im Paket model.hibernate einen oeffentlichen Konstruktor haben. 
	 */
	@Test
	void hibernate_entity_classes_should_only_have_public_constructors() {
		JavaClasses importedClasses = new ClassFileImporter().importPackages("de.stl.saar.prog3");
		
		ArchRule archRule = constructors().
				that().
				areDeclaredInClassesThat().
				resideInAPackage("..model.hibernate..").
				should().
				bePublic();
				
		archRule.check(importedClasses);
	}
}