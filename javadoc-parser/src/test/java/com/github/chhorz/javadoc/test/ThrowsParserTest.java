package com.github.chhorz.javadoc.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.chhorz.javadoc.tags.ThrowsTag;

/**
 * Test class to validate parsing of the {@code @throws} tag.
 * 
 * @author chhorz
 * 
 */
@DisplayName("Tests for JavaDoc throws tag")
class ThrowsParserTest extends AbstractParserTest {

	@Test
	void simpleTest() {
		javaDoc = basicPlainParser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(ThrowsTag.class))
			.hasSize(1)
			.extracting(ThrowsTag::getClassName, ThrowsTag::getDescription)
			.contains(tuple("NullpointerException", "when something is null"));
	}

	@Test
	void complexTest() {
		javaDoc = basicPlainParser.parse(complexJavaDoc);

		assertThat(javaDoc.getTags(ThrowsTag.class))
			.hasSize(1)
			.extracting(ThrowsTag::getClassName, ThrowsTag::getDescription)
			.contains(tuple("NullpointerException", "when something is null"));
	}
}
