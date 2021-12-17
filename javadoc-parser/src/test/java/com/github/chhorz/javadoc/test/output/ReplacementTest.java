/**
 *
 *    Copyright 2018-2021 the original author or authors.
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
package com.github.chhorz.javadoc.test.output;

import com.github.chhorz.javadoc.JavaDoc;
import com.github.chhorz.javadoc.JavaDocParser;
import com.github.chhorz.javadoc.JavaDocParserBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for output string replacements")
class ReplacementTest {

	@Test
	void noReplacement(){
		// given
		String javaDocString = "Hello world!";
		JavaDocParser javaDocParser = JavaDocParserBuilder.withBasicTags()
				.build();

		// when
		JavaDoc javaDoc = javaDocParser.parse(javaDocString);

		// then
		assertThat(javaDoc).isNotNull();
		assertThat(javaDoc.getDescription())
				.isNotNull()
				.isEqualTo("Hello world!");
	}

	@Test
	void withReplacement(){
		// given
		String javaDocString = "Hello world!";
		JavaDocParser javaDocParser = JavaDocParserBuilder.withBasicTags()
				.withReplacement("(world)", "*$0*")
				.build();

		// when
		JavaDoc javaDoc = javaDocParser.parse(javaDocString);

		// then
		assertThat(javaDoc).isNotNull();
		assertThat(javaDoc.getDescription())
				.isNotNull()
				.isEqualTo("Hello *world*!");
	}

}
