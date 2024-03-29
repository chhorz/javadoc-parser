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

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.concat;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.github.chhorz.javadoc.replacements.Replacement;
import com.github.chhorz.javadoc.tags.BlockTag;
import com.github.chhorz.javadoc.tags.Tag;

/**
 *
 * @author chhorz
 *
 */
public final class JavaDocParser {

	private final List<BlockTag> tags = new ArrayList<>();
	private final List<Replacement> replacements = new ArrayList<>();

	public JavaDoc parse(final String javaDocString) {
		String summary = "";
		String description = "";
		List<BlockTag> parsedTags = new ArrayList<>();

		if (javaDocString != null && !javaDocString.isEmpty()) {
			final String rawDescription = parseDescription(javaDocString);

			summary = performReplacements(parseSummary(rawDescription));
			description = performReplacements(rawDescription);
			parsedTags = parseTags(javaDocString);
		}

		return new JavaDoc(summary, description, parsedTags);
	}

	private String parseSummary(final String description) {
		if (description.isEmpty()) {
			return "";
		} else if (description.contains("@summary")) {
			Pattern summaryPattern = Pattern.compile("\\{@summary ([^{}]+)}([\\s.,:;-])?");
			Matcher summaryMatcher = summaryPattern.matcher(description);

			if (summaryMatcher.find()) {
				return summaryMatcher.group(1);
			} else {
				return "";
			}

		} else {
			if (description.contains(".")) {
				return description.substring(0, description.indexOf('.') + 1);
			} else {
				return description;
			}
		}
	}

	private String parseDescription(final String javaDocString) {
		String[] lines = javaDocString.split("\n");

		StringBuilder stringBuilder = new StringBuilder();
		for (String line : lines) {
			if (line.trim().startsWith("@")) {
				break;
			}
			stringBuilder.append(line).append("\n");
		}
		return stringBuilder.toString().trim();
	}

	private List<BlockTag> parseTags(final String javaDocString) {
		List<BlockTag> tagList = new ArrayList<>();

		Stream<String> tagNamesStream = tags.stream()
				.map(Tag::getTagName)
				.map(tag -> String.format("@%s", tag));

		String allTagNames = concat(tagNamesStream, Stream.of("[^{]@\\S+"))
				.collect(joining("|", "(?=", "|$)"));

		for (BlockTag tag : tags) {
			Pattern pattern = Pattern.compile(tag.createPattern(allTagNames), Pattern.DOTALL);
			Matcher matcher = pattern.matcher(javaDocString);

			int start = 0;
			while (matcher.find(start)) {
				BlockTag currentTag;
				try {
					currentTag = tag.getClass().getDeclaredConstructor().newInstance();
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					currentTag = tag;
					e.printStackTrace();
				}

				for (BlockTag.Segment segment : tag.getSegments()) {
					String segmentText = matcher.group(segment.getSegmentName());
					if (segment.isRequired() || segmentText != null) {
						currentTag.putValue(segment.getSegmentName(), performReplacements(segmentText));
					}
				}

				tagList.add(currentTag);

				start = matcher.end();
			}
		}

		return tagList;
	}

	private String performReplacements(final String input) {
		String convertedString = input;
		for (Replacement replacement : replacements) {
			convertedString = replacement.perform(convertedString);
		}
		return convertedString.trim();
	}

	void addReplacement(final Replacement replacement){
		Objects.requireNonNull(replacement, "The given replacement must not be null!");
		replacements.add(replacement);
	}

	void addTag(final BlockTag tag) {
		Objects.requireNonNull(tag, "The given tag must not be null!");

		// check that each tag is only registered once
		if (tags.stream().noneMatch(t -> t.getClass().equals(tag.getClass()))){
			// add tag
			tags.add(tag);
		}
	}

}
