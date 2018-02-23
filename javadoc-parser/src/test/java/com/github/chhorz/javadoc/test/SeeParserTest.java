package com.github.chhorz.javadoc.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.chhorz.javadoc.tags.SeeTag;

/**
 * Test class to validate parsing of the {@code @see} tag.
 * 
 * @author chhorz
 * 
 */
@DisplayName("Tests for JavaDoc see tag")
class SeeParserTest extends AbstractParserTest {

	@Test
	void simpleTest() {
		javaDoc = basicPlainParser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(SeeTag.class))
			.hasSize(1)
			.extracting(SeeTag::getReference)
			.contains("documentation in section abc");
	}

	@Test
	void complexTest() {
		javaDoc = basicPlainParser.parse(complexJavaDoc);

		assertThat(javaDoc.getTags(SeeTag.class))
			.hasSize(1)
			.extracting(SeeTag::getReference)
			.contains("documentation in section abc at link.org");
	}
}