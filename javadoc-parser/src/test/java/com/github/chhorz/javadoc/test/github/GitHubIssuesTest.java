package com.github.chhorz.javadoc.test.github;

import com.github.chhorz.javadoc.JavaDoc;
import com.github.chhorz.javadoc.JavaDocParser;
import com.github.chhorz.javadoc.JavaDocParserBuilder;
import com.github.chhorz.javadoc.OutputType;
import com.github.chhorz.javadoc.tags.ParamTag;
import com.github.chhorz.javadoc.tags.ReturnTag;
import com.github.chhorz.javadoc.tags.SinceTag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class GitHubIssuesTest {

	@Test
	@GitHubIssue("#29")
	void tagRemovalAtStartOfDescription(){
		// given
		String javaDocString = "{@summary This is a summary text.} This is a description with <b>bold</b> and {@code some code}\n" +
				"\n" +
				"@since 1.0\n" +
				"@param foo The foo param\n" +
				"@param bar The {@code bar} param\n" +
				"@return The {@value return} value";
		JavaDocParser javaDocParser = JavaDocParserBuilder.withAllKnownTags()
				.withOutputType(OutputType.MARKDOWN)
				.build();

		// when
		JavaDoc javaDoc = javaDocParser.parse(javaDocString);

		// then
		assertThat(javaDoc)
				.isNotNull()
				.hasFieldOrPropertyWithValue("summary", "This is a summary text.")
				.hasFieldOrPropertyWithValue("description", "This is a summary text. This is a description with <b>bold</b> and `some code`");

		assertThat(javaDoc.getTags(SinceTag.class))
				.hasSize(1)
				.extracting(SinceTag::getSinceText)
				.containsExactly("1.0");

		assertThat(javaDoc.getTags(ParamTag.class))
				.hasSize(2)
				.extracting(ParamTag::getParamName, ParamTag::getParamDescription)
				.containsExactly(tuple("foo", "The foo param"),
						tuple("bar", "The `bar` param"));

		assertThat(javaDoc.getTags(ReturnTag.class))
				.hasSize(1)
				.extracting(ReturnTag::getDescription)
				.containsExactly("The `return` value");
	}

}
