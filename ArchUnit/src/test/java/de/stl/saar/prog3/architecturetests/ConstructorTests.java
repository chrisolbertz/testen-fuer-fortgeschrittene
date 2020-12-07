package de.stl.saar.prog3.architecturetests;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

class ConstructorTests {

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
