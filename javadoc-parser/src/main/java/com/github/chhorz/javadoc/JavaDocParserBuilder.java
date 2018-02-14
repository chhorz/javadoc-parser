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
package com.github.chhorz.javadoc;

import java.util.stream.Stream;

import com.github.chhorz.javadoc.tags.AuthorTag;
import com.github.chhorz.javadoc.tags.CategoryTag;
import com.github.chhorz.javadoc.tags.DeprecatedTag;
import com.github.chhorz.javadoc.tags.ExceptionTag;
import com.github.chhorz.javadoc.tags.ParamTag;
import com.github.chhorz.javadoc.tags.ReturnTag;
import com.github.chhorz.javadoc.tags.SeeTag;
import com.github.chhorz.javadoc.tags.SinceTag;
import com.github.chhorz.javadoc.tags.Tag;
import com.github.chhorz.javadoc.tags.ThrowsTag;
import com.github.chhorz.javadoc.tags.VersionTag;

/**
 *
 * @author chhorz
 *
 */
public class JavaDocParserBuilder {

	private JavaDocParser javaDocParser;

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

	public static JavaDocParserBuilder withBasicTags() {
		return new JavaDocParserBuilder();
	}

	public <T extends Tag> JavaDocParserBuilder withCustomTag(final T tag) {
		javaDocParser.addTag(tag);
		return this;
	}

	public JavaDocParserBuilder withOutputType(final OutputType outputType) {
        if (OutputType.ASCIIDOC.equals(outputType)) {
            // TODO
        } else if  (OutputType.MARKDOWN.equals(outputType)) {
            javaDocParser.addReplacement("", "");
            
        }
		return this;
	}
	
	public JavaDocParserBuilder withReplacement(final String regex, final String replacement) {
	    javaDocParser.addReplacement(regex, replacement);
	    return this;
	}

	public JavaDocParser build() {
		return javaDocParser;
	}

}
