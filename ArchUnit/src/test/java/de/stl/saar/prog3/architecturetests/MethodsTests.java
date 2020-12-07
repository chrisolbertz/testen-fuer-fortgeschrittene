package de.stl.saar.prog3.architecturetests;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

class MethodsTests {

	@Test
	void dao_methods_beginning_with_insert_should_return_void() {
		JavaClasses importedClasses = new ClassFileImporter().importPackages("de.stl.saar.prog3");
		
		ArchRule archRule = methods().that().
									  areDeclaredInClassesThat().
									  resideInAnyPackage("..dao..interfaces..").
									  and().
									  haveNameMatching("insert[A-Z][a-z]*").
									  should().
									  notHaveRawParameterTypes("void");
		
		archRule.check(importedClasses);
	}

	@Test
	void classes_in_utils_package_should_only_contain_static_methods() {
		JavaClasses importedClasses = new ClassFileImporter().importPackages("de.stl.saar.prog3");
		
		ArchRule archRule = methods().that().
									  areDeclaredInClassesThat().
									  resideInAPackage("..utils..").
									  should().
									  beStatic();
		
		archRule.check(importedClasses);
	}
}
