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
import static org.assertj.core.api.Assertions.tuple;

import com.github.chhorz.javadoc.JavaDoc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.chhorz.javadoc.tags.ParamTag;

/**
 * Test class to validate parsing of the {@code @param} tag.
 *
 * @author chhorz
 *
 */
@DisplayName("Tests for JavaDoc param tag")
class ParamParserTest extends AbstractParserTest {

	@Test
	void simpleTest() {
		javaDoc = basicPlainParser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(ParamTag.class))
			.hasSize(1)
			.extracting(ParamTag::getParamName, ParamTag::getParamDescription)
			.contains(tuple("test", "value"));
	}
	@Test
	void optionalSegmentTest() {
		javaDoc = basicPlainParser.parse(optionalSegmentsJavaDoc);

		assertThat(javaDoc.getTags(ParamTag.class))
				.hasSize(1)
				.extracting(ParamTag::getParamName, ParamTag::getParamDescription)
				.contains(tuple("test", ""));
	}

	@Test
	void complexTest() {
		javaDoc = basicPlainParser.parse(complexJavaDoc);

		assertThat(javaDoc.getTags(ParamTag.class))
			.hasSize(1)
			.extracting(ParamTag::getParamName, ParamTag::getParamDescription)
				.contains(tuple("test",
						"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt\n"
								+ "           ut labore et dolore magna aliqua."));
	}

	@Test
	void kDocTest(){
		JavaDoc kDoc = kotlinParser.parse(simpleKDoc);

		assertThat(kDoc.getTags(ParamTag.class))
				.hasSize(1)
				.extracting(ParamTag::getParamName, ParamTag::getParamDescription)
				.contains(tuple("T", "the type of a member in this group."));
	}
}
