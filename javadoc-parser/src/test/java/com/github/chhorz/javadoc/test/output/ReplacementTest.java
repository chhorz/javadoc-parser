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
package com.github.chhorz.javadoc.test.output;

import com.github.chhorz.javadoc.JavaDoc;
import com.github.chhorz.javadoc.JavaDocParser;
import com.github.chhorz.javadoc.JavaDocParserBuilder;
import com.github.chhorz.javadoc.OutputType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for output string replacements")
class ReplacementTest {

	@Test
	void noReplacement() {
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
	void withReplacement() {
		// given
		String javaDocString = "Hello world!";
		JavaDocParser javaDocParser = JavaDocParserBuilder.withBasicTags()
				.withReplacement(javaDoc -> javaDoc.replaceAll("(world)", "*$0*"))
				.build();

		// when
		JavaDoc javaDoc = javaDocParser.parse(javaDocString);

		// then
		assertThat(javaDoc).isNotNull();
		assertThat(javaDoc.getDescription())
				.isNotNull()
				.isEqualTo("Hello *world*!");
	}

	@Test
	void snippetReplacement() {
		// given
		String javaDocString = "Snippet tag example.\n" +
							   "The following code shows how to use {@code Optional.isPresent}:\n" +
							   "{@snippet :\n" +
							   "if (v.isPresent()) {\n" +
							   "    System.out.println(\"v: \" + v.get());\n" +
							   "}\n" +
							   "}\n" +
							   "" +
							   "{@snippet :\n" +
							   "if (v.isPresent()) {\n" +
							   "    System.out.println(\"v: \" + v.get());\n" +
							   "} else if (v.isEmpty()) {\n" +
							   "    System.out.println(\"v: <null>\");\n" +
							   "}\n" +
							   "}\n" +
							   "This is it.";
		JavaDocParser javaDocParser = JavaDocParserBuilder.withBasicTags()
				.withOutputType(OutputType.HTML)
				.build();

		// when
		JavaDoc javaDoc = javaDocParser.parse(javaDocString);

		// then
		assertThat(javaDoc)
				.isNotNull()
				.hasFieldOrPropertyWithValue("summary", "Snippet tag example.")
				.hasFieldOrPropertyWithValue("description", "Snippet tag example.\n" +
															"The following code shows how to use <code>Optional.isPresent</code>:\n" +
															"<pre>\n" +
															"if (v.isPresent()) {\n" +
															"    System.out.println(\"v: \" + v.get());\n" +
															"}\n" +
															"</pre>\n" +
															"<pre>\n" +
															"if (v.isPresent()) {\n" +
															"    System.out.println(\"v: \" + v.get());\n" +
															"} else if (v.isEmpty()) {\n" +
															"    System.out.println(\"v: <null>\");\n" +
															"}\n" +
															"</pre>\n" +
															"This is it.");
	}

	@Test
	void customReplacement(){
		// given
		String javaDocString = "Snippet tag example.\n" +
							   "The following code shows how to use {@code Optional.isPresent}:\n" +
							   "{@snippet :\n" +
							   "if (v.isPresent()) {\n" +
							   "    System.out.println(\"v: \" + v.get());\n" +
							   "}\n" +
							   "}";
		JavaDocParser javaDocParser = JavaDocParserBuilder.withBasicTags()
				.withOutputType(OutputType.HTML)
				.withReplacement(input -> input.replaceAll("System\\.out\\.println", "logger.info"))
				.build();

		// when
		JavaDoc javaDoc = javaDocParser.parse(javaDocString);

		// then
		assertThat(javaDoc)
				.isNotNull()
				.hasFieldOrPropertyWithValue("summary", "Snippet tag example.")
				.hasFieldOrPropertyWithValue("description", "Snippet tag example.\n" +
															"The following code shows how to use <code>Optional.isPresent</code>:\n" +
															"<pre>\n" +
															"if (v.isPresent()) {\n" +
															"    logger.info(\"v: \" + v.get());\n" +
															"}\n" +
															"</pre>");
	}

}
