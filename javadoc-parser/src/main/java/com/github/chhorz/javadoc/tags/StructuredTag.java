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

import java.util.*;

/**
 *
 * @author chhorz
 *
 */
public abstract class StructuredTag extends Tag {

	private final String name;
	private final List<Segment> segments;
	private final TreeMap<String, String> values = new TreeMap<>();

	public StructuredTag(final String name, final Segment... segments) {
		this.name = name;
		this.segments = Arrays.asList(segments);
		getSegments().stream()
				.map(Segment::getName)
				.forEach(segmentName -> values.put(segmentName, ""));
	}

	public String getTagName() {
		return name;
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void putValue(final String segmentName, final String segmentValue) {
		if (values.containsKey(segmentName)) {
			values.put(segmentName, segmentValue != null ? segmentValue : "");
		}
	}

	public String createPattern(final String allTagNames) {
		StringBuilder sb = new StringBuilder();
		getSegments().stream()
				.map(segment -> segment.isRequired()
						? String.format("\\s+?(?<%s>.+?)", segment.getName())
						: String.format("\\s+?(?<%s>.+?)??", segment.getName()))
				.forEach(sb::append);
		return "@" + name + sb + "\\s*" + allTagNames;
	}

	@Override
	public String toString() {
		return String.format("Tag [name=%s, segments=%s]", name, segments);
	}

}
