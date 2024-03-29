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
package com.github.chhorz.javadoc.tags;

/**
 * A PatternTag can be used to define a custom {@link java.util.regex.Pattern}. For this free kind of tag, more methods have to be implemented.
 *
 * @author chhorz
 */
public abstract class PatternTag implements BlockTag {

	private final String tagName;
	private final String pattern;

	public PatternTag(final String tagName, final String pattern) {
		this.tagName = tagName;
		this.pattern = pattern;
	}

	@Override
	public String getTagName() {
		return tagName;
	}

	@Override
	public String createPattern(final String allTagNames) {
		return "@" + tagName + pattern + "\\s*" + allTagNames;
	}

	@Override
	public String toString() {
		return String.format("Tag [tagName=%s, pattern=%s]", tagName, pattern);
	}

}
