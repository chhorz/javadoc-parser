/**
 *
 *    Copyright 2018-2020 the original author or authors.
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

/**
 * Test class to validate parsing of the javadoc description.
 *
 * @author chhorz
 *
 */
@DisplayName("Tests for JavaDoc summary")
class SummaryParserTest extends AbstractParserTest {

	@Test
	void nullTest() {
		javaDoc = basicPlainParser.parse(nullJavaDoc);

		assertThat(javaDoc)
			.hasFieldOrPropertyWithValue("summary", "");
	}

	@Test
	void emptyTest() {
		javaDoc = basicPlainParser.parse(emptyJavaDoc);

		assertThat(javaDoc)
			.hasFieldOrPropertyWithValue("summary", "");
	}

	@Test
	void simpleTest() {
		javaDoc = basicPlainParser.parse(simpleJavaDoc);

		assertThat(javaDoc)
			.hasFieldOrPropertyWithValue("summary", "Test");
	}

	@Test
	void complexTest() {
		javaDoc = basicPlainParser.parse(complexJavaDoc);

		assertThat(javaDoc)
			.hasFieldOrPropertyWithValue("summary",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt\n" +
					"ut labore et dolore magna aliqua.");
	}

	@Test
	void inlinedTagsTest() {
		javaDoc = basicAsciidocParser.parse(javaDocWithInlineTags);

		assertThat(javaDoc)
			.hasFieldOrPropertyWithValue("summary",
					"Lorem ipsum dolor `sit` amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt\n" +
					"ut labore et dolore magna _aliqua_.");
	}

	@Test
	void asciidocTest() {
		javaDoc = basicAsciidocParser.parse(javaDocWithInlineTagsAndSummary);

		assertThat(javaDoc)
			.hasFieldOrPropertyWithValue("summary",
					"Ut enim ad minim veniam, quis nostrud exercitation ullamco\n" +
					"laboris nisi ut aliquip ex ea commodo consequat.");
	}
}
