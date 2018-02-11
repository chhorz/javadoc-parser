/**
 *
 *    Copyright 2018 the original author or authors.
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
package com.github.chhorz.javadoc.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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
		javaDoc = parser.parse(simpleJavaDoc);

		assertThat(javaDoc.getTags(ParamTag.class))
			.hasSize(1)
			.extracting(ParamTag::getParamName, ParamTag::getParamDescription)
			.contains(tuple("test", "value"));
	}

	@Test
	void complexTest() {
		javaDoc = parser.parse(complexJavaDoc);

		assertThat(javaDoc.getTags(ParamTag.class))
			.hasSize(1)
			.extracting(ParamTag::getParamName, ParamTag::getParamDescription)
			.contains(tuple("test", "Lorem ipsum"));
	}
}
