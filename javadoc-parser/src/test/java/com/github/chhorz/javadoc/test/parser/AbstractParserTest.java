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

import com.github.chhorz.javadoc.JavaDoc;
import com.github.chhorz.javadoc.JavaDocParser;
import com.github.chhorz.javadoc.JavaDocParserBuilder;
import com.github.chhorz.javadoc.OutputType;

abstract class AbstractParserTest {

	JavaDocParser basicPlainParser = JavaDocParserBuilder
			.withBasicTags()
			.withOutputType(OutputType.PLAIN)
			.build();

	JavaDocParser basicAsciidocParser = JavaDocParserBuilder
			.withBasicTags()
			.withOutputType(OutputType.ASCIIDOC)
			.build();

	JavaDoc javaDoc;

	// @formatter:off
	final String nullJavaDoc = null;

	final String emptyJavaDoc = "";

	final String simpleJavaDoc =
			"Test\n" +
			"@since 0.1\n" +
			"@author name\n" +
			"@category some-category\n" +
			"@deprecated use xyz instead\n" +
			"@version 1.2.3\n" +
			"@see documentation in section abc\n" +
			"@throws NullPointerException when something is null\n" +
			"@exception RuntimeException may occur always\n" +
			"@param test value\n" +
			"@return the result";

	final String optionalSegmentsJavaDoc =
			"Test\n" +
			"@deprecated\n" +
			"@throws NullPointerException\n" +
			"@exception RuntimeException\n" +
			"@param test\n";

	final String complexJavaDoc =
			"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt\n" +
			"ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco\n" +
			"laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in.\n" +
			"\n" +
			"Voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat\n" +
			"non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n" +
			"\n" +
			"@param test \n" +
			"           Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt\n" +
			"           ut labore et dolore magna aliqua.\n" +
			"@since 0.1\n" +
			"@category some-category\n" +
			"@category another-category\n" +
			"@deprecated since version 42, use xyz instead\n" +
			"@version 1.2.3-SNAPSHOT\n" +
			"@see documentation in section abc at link.org\n" +
			"@throws NullPointerException when something is null\n" +
			"@exception RuntimeException may occur always\n" +
			"@author name\n" +
			"@return the result";

	final String javaDocWithInlineTags =
			"Lorem ipsum dolor {@code sit} amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt\n" +
			"ut labore et dolore magna {@literal aliqua}. Ut enim ad minim veniam, quis nostrud exercitation ullamco\n" +
			"laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in.\n" +
			"\n" +
			"Voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat\n" +
			"non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n" +
			"\n" +
			"@throws NullPointerException when something is {@code null}\n" +
			"@since 0.1\n" +
			"@author name";

	final String javaDocWithInlineTagsAndSummary =
			"Lorem ipsum dolor {@code sit} amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt\n" +
			"ut labore et dolore magna {@literal aliqua}. {@summary Ut enim ad minim veniam, quis nostrud exercitation ullamco\n" +
			"laboris nisi ut aliquip ex ea commodo consequat.} Duis aute irure dolor in reprehenderit in.\n" +
			"\n" +
			"Voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat\n" +
			"non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n" +
			"\n" +
			"@throws NullPointerException when something is {@code null}\n" +
			"@since 0.1\n" +
			"@author name";
	// @formatter:on

}
