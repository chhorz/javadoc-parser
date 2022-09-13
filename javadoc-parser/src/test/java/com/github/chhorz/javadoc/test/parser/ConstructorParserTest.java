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

import com.github.chhorz.javadoc.tags.ConstructorTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for KDoc constructor tag")
class ConstructorParserTest extends AbstractParserTest {

	@Test
	void simpleTest() {
		javaDoc = kotlinParser.parse(simpleKDoc);

		assertThat(javaDoc.getTags(ConstructorTag.class))
				.hasSize(1)
				.extracting(ConstructorTag::getDescription)
				.contains("Creates an empty group.");
	}

}
