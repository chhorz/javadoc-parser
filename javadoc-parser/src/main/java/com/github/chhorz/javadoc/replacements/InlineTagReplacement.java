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
package com.github.chhorz.javadoc.replacements;

import static java.lang.String.format;

public abstract class InlineTagReplacement implements Replacement {

	private final String inlineTag;
	private final String prefix;
	private final String suffix;

	public InlineTagReplacement(final String inlineTag) {
		this(inlineTag, "", "");
	}

	public InlineTagReplacement(final String inlineTag, final String wrapperString) {
		this(inlineTag, wrapperString, wrapperString);
	}

	public InlineTagReplacement(final String inlineTag, final String prefix, final String suffix) {
		this.inlineTag = inlineTag;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	@Override
	public String perform(final String input) {
		String output = input;

		int tagStartIndex = output.indexOf("{@" + inlineTag);
		int tagEndIndex = tagStartIndex;
		while (tagStartIndex > 0) {
			int bracketCount = 0;
			for (int i = tagStartIndex; i < output.length(); i++) {
				char c = output.charAt(i);
				if (c == '{') {
					bracketCount++;
				}

				if (c == '}') {
					bracketCount--;
				}

				if (bracketCount == 0) {
					tagEndIndex = i;
					break;
				}
			}

			String completeTag = output.substring(tagStartIndex, tagEndIndex + 1);
			String tagReplacement = completeTag.substring(0, completeTag.length() - 1).replaceFirst("\\{@"+inlineTag+"\\s+:?", "");
			output = output.replace(completeTag, format("%s%s%s", prefix, tagReplacement, suffix));

			tagStartIndex = output.indexOf("{@" + inlineTag);
			tagEndIndex = tagStartIndex;
		}

		return output;
	}

}
