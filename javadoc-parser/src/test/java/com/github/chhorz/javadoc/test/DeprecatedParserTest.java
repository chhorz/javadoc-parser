package com.github.chhorz.javadoc.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.chhorz.javadoc.tags.DeprecatedTag;

/**
 * Test class to validate parsing of the {@code @deprecated} tag.
 * 
 * @author chhorz
 * 
 */
@DisplayName("Tests for JavaDoc deprecated tag")
class DeprecatedParserTest extends AbstractParserTest {

	@Test
	void simpleTest() {
		javaDoc = basicPlainParser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(DeprecatedTag.class))
			.hasSize(1)
			.extracting(DeprecatedTag::getDeprecatedNote)
			.contains("use xyz instead");
	}

	@Test
	void complexTest() {
		javaDoc = basicPlainParser.parse(complexJavaDoc);

		assertThat(javaDoc.getTags(DeprecatedTag.class))
			.hasSize(1)
			.extracting(DeprecatedTag::getDeprecatedNote)
			.contains("since version 42, use xyz instead");
	}
}
