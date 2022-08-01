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
package com.github.chhorz.javadoc;

import java.util.stream.Stream;

import com.github.chhorz.javadoc.replacements.*;
import com.github.chhorz.javadoc.tags.*;

import static java.lang.String.format;

/**
 * Fluent builder to create a {@link JavaDocParser} instance.
 *
 * @author chhorz
 *
 */
public class JavaDocParserBuilder {

	private final JavaDocParser javaDocParser;

	private JavaDocParserBuilder() {
		this.javaDocParser = new JavaDocParser();

		Stream.of(new AuthorTag(),
						new CategoryTag(),
						new DeprecatedTag(),
						new ExceptionTag(),
						new ParamTag(),
						new ReturnTag(),
						new SeeTag(),
						new SinceTag(),
						new ThrowsTag(),
						new VersionTag())
				.forEach(javaDocParser::addTag);
	}

	/**
	 * Create a new builder instance with common JavaDoc tags.
	 *
	 * @return a new builder instance
	 */
	public static JavaDocParserBuilder withBasicTags() {
		return new JavaDocParserBuilder();
	}

	/**
	 * Adds a custom JavaDoc tag to the parser instance.
	 *
	 * @param tag a new instance of the custom tag
	 * @param <T> type representation of the custom tag
	 * @return the updated builder instance
	 */
	public <T extends Tag> JavaDocParserBuilder withCustomTag(final T tag) {
		javaDocParser.addTag(tag);
		return this;
	}

	/**
	 * Adds string replacements based on regular expressions to convert JavaDoc format into the given output format.
	 *
	 * @param outputType the requested output type
	 * @return the fluent builder instance
	 */
	public JavaDocParserBuilder withOutputType(final OutputType outputType) {
		javaDocParser.addReplacement(new SummaryTagReplacement());
		if (OutputType.ASCIIDOC.equals(outputType) || OutputType.MARKDOWN.equals(outputType)) {
			javaDocParser.addReplacement(new CodeTagReplacement("`"));
			javaDocParser.addReplacement(new SnippetTagReplacement("```"));
			javaDocParser.addReplacement(new LinkTagReplacement());
			javaDocParser.addReplacement(new LinkPlainTagReplacement());
			javaDocParser.addReplacement(new LiteralTagReplacement("_"));
			javaDocParser.addReplacement(new ValueTagReplacement("`"));
		} else if (OutputType.HTML.equals(outputType)) {
			javaDocParser.addReplacement(new CodeTagReplacement("<code>", "</code>"));
			javaDocParser.addReplacement(new SnippetTagReplacement("<pre>", "</pre>"));
			javaDocParser.addReplacement(new LinkTagReplacement());
			javaDocParser.addReplacement(new LinkPlainTagReplacement());
			javaDocParser.addReplacement(new LiteralTagReplacement("<i>", "</i>"));
			javaDocParser.addReplacement(new ValueTagReplacement("<pre>", "</pre>"));
		}
		return this;
	}

	/**
	 * Adds custom replacements for the output content.
	 *
	 * @see #withOutputType(OutputType)
	 *
	 * @param replacement the replacement function
	 * @return the fluent builder instance
	 */
	public JavaDocParserBuilder withReplacement(final Replacement replacement) {
		javaDocParser.addReplacement(replacement);
		return this;
	}

	/**
	 * Create the {@link JavaDocParser} instance.
	 *
	 * @return a new parser instance
	 */
	public JavaDocParser build() {
		return javaDocParser;
	}

}
