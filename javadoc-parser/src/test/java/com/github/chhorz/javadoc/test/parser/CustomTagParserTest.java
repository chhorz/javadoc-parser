/**
 *
 *    Copyright 2018-2022 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.github.chhorz.javadoc.test.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.chhorz.javadoc.JavaDoc;
import com.github.chhorz.javadoc.JavaDocParser;
import com.github.chhorz.javadoc.JavaDocParserBuilder;
import com.github.chhorz.javadoc.OutputType;
import com.github.chhorz.javadoc.tags.AuthorTag;
import com.github.chhorz.javadoc.test.parser.tags.CustomTag;

/**
 * Test class to validate parsing of a custom tag.
 *
 * @author chhorz
 *
 */
@DisplayName("Tests for custom Javadoc tag")
class CustomTagParserTest extends AbstractParserTest {

	// @formatter:off
	final String simpleJavaDoc =
			"Test\n" +
			"@since 0.1\n" +
			"@author name\n" +
			"@custom this is the custom tag value\n" +
			"@param test value\n" +
			"@return the result";
	// @formatter:on

	private final JavaDocParser parser = JavaDocParserBuilder
		.withStandardJavadocTags()
		.withTag(new CustomTag())
		.withOutputType(OutputType.PLAIN)
		.build();

	JavaDoc javaDoc;

	@Test
	void simpleCategory() {
		javaDoc = parser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(CustomTag.class))
			.hasSize(1)
			.extracting(CustomTag::getValue)
			.contains("this is the custom tag value");
	}

	@Test
	void basicTagWithUnknown() {
		javaDoc = basicPlainParser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(AuthorTag.class))
			.hasSize(1)
			.extracting(AuthorTag::getNameText)
			.contains("name");
	}

}
