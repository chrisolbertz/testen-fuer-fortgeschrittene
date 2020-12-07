package de.stl.saar.prog3.architecturetests;
import org.junit.jupiter.api.Test; 

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import javafx.fxml.FXML;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import javax.persistence.Entity;

class ClassLocationTest {

	@Test
	void enums_should_be_located_in_package_enums() {
		JavaClasses importedClasses = new ClassFileImporter().
				importPackages("de.stl.saar.prog3");
		
		ArchRule archRule = classes().that().
									  areEnums().
									  should().
									  resideInAPackage("..enums");
								
		
		archRule.check(importedClasses);
	}

	@Test
	void interfaces_should_be_located_in_package_interfaces() {
		JavaClasses importedClasses = new ClassFileImporter().importPackages("de.stl.saar.prog3");
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
				importPackages("de.stl.saar.prog3");
		ArchRule archRule = classes().
				that().
				areAnnotatedWith(Entity.class).
				should().
				resideInAPackage("..model.hibernate");
		archRule.check(importedClasses);
	}
	
	@Test
	void classes_with_method_annotated_with_FXML_should_be_located_in_package_controller() {
		JavaClasses importedClasses = new ClassFileImporter().
				importPackages("de.stl.saar.prog3");
		ArchRule archRule = methods().
				that().
				areAnnotatedWith(FXML.class).
				should().
				beDeclaredInClassesThat().
				resideInAPackage("..controllers");
		archRule.check(importedClasses);
	}
}
