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
import com.github.chhorz.javadoc.exception.DuplicateTagException;
import com.github.chhorz.javadoc.tags.AuthorTag;
import com.github.chhorz.javadoc.test.tags.CustomPatternTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Test class to validate parsing of a custom pattern tag.
 *
 * @author chhorz
 *
 */
@DisplayName("Tests for custom pattern JavaDoc tag")
class CustomPatternTagParserTest extends AbstractParserTest {

    // @formatter:off
    final String simpleJavaDoc =
            "Test\n" +
            "@since 0.1\n" +
            "@author name\n" +
            "@custom 0815 this is the custom tag value\n" +
            "@param test value\n" +
            "@return the result";
    // @formatter:on

    private final JavaDocParser parser = JavaDocParserBuilder
            .withBasicTags()
            .withCustomTag(new CustomPatternTag())
            .withOutputType(OutputType.PLAIN)
            .build();

    JavaDoc javaDoc;

    @Test
    void simpleCategory() {
        javaDoc = parser.parse(simpleJavaDoc);

        assertThat(javaDoc.getTags(CustomPatternTag.class))
                .hasSize(1)
                .extracting(CustomPatternTag::getNumber, CustomPatternTag::getOtherValue)
                .contains(tuple(815L, "this is the custom tag value"));
    }

    @Test
    void duplicateTags(){
        assertThatThrownBy(() -> JavaDocParserBuilder.withBasicTags()
                .withCustomTag(new CustomPatternTag())
                .withCustomTag(new CustomPatternTag())
                .build())
                .isInstanceOf(DuplicateTagException.class)
                .hasMessage("The parser already contains a tag with name '@custom'.");
    }

    @Test
    void basicTagWithUnknown() {
        javaDoc = basicPlainParser.parse(simpleJavaDoc);

        assertThat(javaDoc.getTags(AuthorTag.class))
                .hasSize(1)
                .extracting(AuthorTag::getAuthorName)
                .contains("name");
    }
}
