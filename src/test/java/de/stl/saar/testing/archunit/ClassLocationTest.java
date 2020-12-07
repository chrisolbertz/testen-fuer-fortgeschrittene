package de.stl.saar.testing.archunit;

import org.junit.jupiter.api.Test; 

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import javax.persistence.Entity;

class ClassLocationTest {
	private static final String BASE_PACKAGE = "de.stl.saar.testing.archunit";
		
	@Test
	void enums_should_be_located_in_package_enums() {
		JavaClasses importedClasses = new ClassFileImporter().
				importPackages(BASE_PACKAGE);
		ArchRule archRule = classes().
				that().
				areEnums().
				should().
				resideInAPackage("..enums..");
		archRule.check(importedClasses);
	}

	@Test
	void interfaces_should_be_located_in_package_interfaces() {
		JavaClasses importedClasses = new ClassFileImporter().importPackages(BASE_PACKAGE);
		ArchRule archRule = classes().
				that().
				areInterfaces().
				should().
				resideInAPackage("..interfaces..");
		archRule.check(importedClasses);
	}
	
	@Test
	void classes_with_annotation_entity_should_be_located_in_package_model_hibernate() {
		JavaClasses importedClasses = new ClassFileImporter().
				importPackages(BASE_PACKAGE);
		ArchRule archRule = classes().
				that().
				areAnnotatedWith(Entity.class).
				should().
				resideInAPackage("..model.hibernate");
		archRule.check(importedClasses);
	}
}