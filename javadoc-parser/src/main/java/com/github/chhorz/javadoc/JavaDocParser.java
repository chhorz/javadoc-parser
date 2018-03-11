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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.github.chhorz.javadoc.exception.DuplicateTagException;
import com.github.chhorz.javadoc.tags.Tag;

/**
 *
 * @author chhorz
 *
 */
public final class JavaDocParser {

	private List<Tag> tags = new ArrayList<>();
	private Map<String, String> replacements = new HashMap<>();

	public JavaDoc parse(final String javaDocString) {
		String description = "";
		List<Tag> tags = new ArrayList<>();

		if (javaDocString != null && !javaDocString.isEmpty()) {
			description = performReplacements(parseDescription(javaDocString));
			tags = parseTags(javaDocString);
		}

		return new JavaDoc(description, tags);
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

	private List<Tag> parseTags(final String javaDocString) {
		List<Tag> tagList = new ArrayList<>();

		String allTagNames = tags.stream()
				.map(tag -> tag.getTagName())
				.map(tag -> String.format("@%s", tag))
				// .map(tag -> Pattern.quote(tag))
				.collect(Collectors.joining("|", "(?=", "|$)"));

		for (Tag tag : tags) {

			StringBuilder sb = new StringBuilder();
			for (String segmentName : tag.getSegmentNames()) {
				sb.append("\\s+?(?<" + segmentName + ">.+?)");
			}
			String parts = sb.toString();

			Pattern pattern = Pattern.compile(".*@" + tag.getTagName() + parts + "\\s*" + allTagNames);
			Matcher matcher = pattern.matcher(javaDocString);
			// System.out.println(pattern);

			while (matcher.find()) {
				Tag currentTag;
				try {
					currentTag = tag.getClass().newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					currentTag = tag;
					e.printStackTrace();
				}

				for (String segmentName : tag.getSegmentNames()) {
					currentTag.putValue(segmentName, performReplacements(matcher.group(segmentName)));
					// System.out.println("segmentName=" + segmentName + ", found=" + tag.getValues().get(segmentName));
				}

				// System.out.println("Tag:" + currentTag);
				tagList.add(currentTag);
			}
		}

		// System.out.println(tagList);
		return tagList;
	}

	private String performReplacements(final String input) {
		String convertedString = input;
		for (Entry<String, String> replacement : replacements.entrySet()) {
			convertedString = convertedString.replaceAll(replacement.getKey(), replacement.getValue());
		}
		return convertedString.trim();
	}

	public void addReplacement(final String regex, final String replacement){
		Objects.requireNonNull(regex, "The given regex must not be null!");
		Objects.requireNonNull(replacement, "The given replacement must not be null!");

		if (regex.isEmpty()) {
			throw new IllegalArgumentException("The given regex must not be empty!");
		}

		if (regex != null && !regex.isEmpty() && replacement != null) {
			replacements.put(regex, replacement);
		}
	}

	public void addTag(final Tag tag) {
		Objects.requireNonNull(tag, "The given tag must not be null!");

		// check that each tag is only registered once
		if (tags.stream().anyMatch(t -> t.getClass().equals(tag.getClass()))){
			throw new DuplicateTagException(tag);
		}
		// add tag
		tags.add(tag);
	}

}
