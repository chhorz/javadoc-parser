package com.github.chhorz.javadoc.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.chhorz.javadoc.tags.ExceptionTag;

/**
 * Test class to validate parsing of the {@code @exception} tag.
 * 
 * @author chhorz
 * 
 */
@DisplayName("Tests for JavaDoc exception tag")
class ExceptionParserTest extends AbstractParserTest {

	@Test
	void simpleTest() {
		javaDoc = basicPlainParser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(ExceptionTag.class))
			.hasSize(1)
			.extracting(ExceptionTag::getClassName, ExceptionTag::getDescription)
			.contains(tuple("RuntimeException", "may occur always"));
	}

	@Test
	void complexTest() {
		javaDoc = basicPlainParser.parse(complexJavaDoc);

		assertThat(javaDoc.getTags(ExceptionTag.class))
			.hasSize(1)
			.extracting(ExceptionTag::getClassName, ExceptionTag::getDescription)
			.contains(tuple("RuntimeException", "may occur always"));
	}
}
