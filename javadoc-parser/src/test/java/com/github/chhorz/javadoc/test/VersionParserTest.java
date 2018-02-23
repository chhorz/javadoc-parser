package com.github.chhorz.javadoc.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.chhorz.javadoc.tags.VersionTag;

/**
 * Test class to validate parsing of the {@code @version} tag.
 * 
 * @author chhorz
 * 
 */
@DisplayName("Tests for JavaDoc version tag")
class VersionParserTest extends AbstractParserTest {

	@Test
	void simpleTest() {
		javaDoc = basicPlainParser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(VersionTag.class))
			.hasSize(1)
			.extracting(VersionTag::getVersionText)
			.contains("1.2.3");
	}

	@Test
	void complexTest() {
		javaDoc = basicPlainParser.parse(complexJavaDoc);

		assertThat(javaDoc.getTags(VersionTag.class))
			.hasSize(1)
			.extracting(VersionTag::getVersionText)
			.contains("1.2.3-SNAPSHOT");
	}
}
