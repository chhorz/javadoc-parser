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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.chhorz.javadoc.replacements.*;
import com.github.chhorz.javadoc.tags.*;

/**
 * Fluent builder to create a {@link JavaDocParser} instance.
 *
 * @author chhorz
 *
 */
public class JavaDocParserBuilder {

	private final JavaDocParser javaDocParser;

	public static final List<BlockTag> STANDARD_JAVADOC_TAGS = Arrays.asList(new AuthorTag(),
			new DeprecatedTag(),
			new ExceptionTag(),
			new HiddenTag(),
			new ParamTag(),
			new ProvidesTag(),
			new ReturnTag(),
			new SeeTag(),
			new SerialDataTag(),
			new SerialFieldTag(),
			new SerialTag(),
			new SinceTag(),
			new ThrowsTag(),
			new UsesTag(),
			new VersionTag());

	public static final List<BlockTag> STANDARD_KDOC_TAGS = Arrays.asList(new ParamTag(),
			new ReturnTag(),
			new ConstructorTag(),
			new ReceiverTag(),
			new PropertyTag(),
			new ThrowsTag(),
			new ExceptionTag(),
			new SampleTag(),
			new SeeTag(),
			new AuthorTag(),
			new SinceTag(),
			new SuppressTag(),
			new DeprecatedKotlinTag());

	private JavaDocParserBuilder(BlockTag... tags) {
		this.javaDocParser = new JavaDocParser();
		Arrays.asList(tags).forEach(javaDocParser::addTag);
	}

	/**
	 * Create a new builder instance without any javadoc tags.
	 *
	 * @return a new builder instance
	 */
	public static JavaDocParserBuilder withoutTags(){
		return new JavaDocParserBuilder();
	}

	/**
	 * Adds all tags from the official <a href="https://docs.oracle.com/en/java/javase/18/docs/specs/javadoc/doc-comment-spec.html">Oracle documentation</a>.
	 *
	 * @return a new builder instance
	 */
	public static JavaDocParserBuilder withStandardJavadocTags(){
		return new JavaDocParserBuilder(STANDARD_JAVADOC_TAGS.toArray(new BlockTag[]{}));
	}

	/**
	 * Adds all tags from the official <a href="https://kotlinlang.org/docs/kotlin-doc.html">Kotlin documentation</a>.
	 *
	 * @return a new builder instance
	 */
	public static JavaDocParserBuilder withStandardKDocTags(){
		return new JavaDocParserBuilder(STANDARD_KDOC_TAGS.toArray(new BlockTag[]{}));
	}

	/**
	 * Creates a new builder instance with all known block tags of {@link #STANDARD_JAVADOC_TAGS} and {@link #STANDARD_KDOC_TAGS}.
	 *
	 * @return a new builder instance
	 */
	public static JavaDocParserBuilder withAllKnownTags(){
		Set<BlockTag> tags = new HashSet<>();
		tags.addAll(STANDARD_JAVADOC_TAGS);
		tags.addAll(STANDARD_KDOC_TAGS);
		return new JavaDocParserBuilder(tags.toArray(new BlockTag[]{}));
	}

	/**
	 * Adds a javadoc tag to the parser instance.
	 *
	 * @param tag a new instance of the custom tag
	 * @param <T> type representation of the custom tag
	 * @return the updated builder instance
	 */
	public <T extends BlockTag> JavaDocParserBuilder withTag(final T tag) {
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
		javaDocParser.addReplacement(new InlineTagReplacement(new SummaryTag()));
		if (OutputType.ASCIIDOC.equals(outputType) || OutputType.MARKDOWN.equals(outputType)) {
			javaDocParser.addReplacement(new InlineTagReplacement(new CodeTag(), "`"));
			javaDocParser.addReplacement(new InlineTagReplacement(new SnippetTag(), "```"));
			javaDocParser.addReplacement(new InlineTagReplacement(new LinkTag()));
			javaDocParser.addReplacement(new InlineTagReplacement(new LinkPlainTag()));
			javaDocParser.addReplacement(new InlineTagReplacement(new LiteralTag(), "_"));
			javaDocParser.addReplacement(new InlineTagReplacement(new ValueTag(), "`"));
		} else if (OutputType.HTML.equals(outputType)) {
			javaDocParser.addReplacement(new InlineTagReplacement(new CodeTag(), "<code>", "</code>"));
			javaDocParser.addReplacement(new InlineTagReplacement(new SnippetTag(), "<pre>", "</pre>"));
			javaDocParser.addReplacement(new InlineTagReplacement(new LinkTag()));
			javaDocParser.addReplacement(new InlineTagReplacement(new LinkPlainTag()));
			javaDocParser.addReplacement(new InlineTagReplacement(new LiteralTag(), "<i>", "</i>"));
			javaDocParser.addReplacement(new InlineTagReplacement(new ValueTag(), "<pre>", "</pre>"));
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
