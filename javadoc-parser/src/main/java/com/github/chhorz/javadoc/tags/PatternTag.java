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
package com.github.chhorz.javadoc.tags;

/**
 *
 * @author chhorz
 *
 */
public abstract class PatternTag extends Tag {

	private String name;
	private String pattern;

	public PatternTag(final String name, final String pattern) {
		this.name = name;
		this.pattern = pattern;
	}

	public String getTagName() {
		return name;
	}

	// public List<String> getSegmentNames() {
	// return segmentNames;
	// }
	//
	// public Map<String, String> getValues() {
	// return values;
	// }
	//
	// public void putValue(final String segmentName, final String segmentValue) {
	// if (values.containsKey(segmentName)) {
	// values.put(segmentName, segmentValue != null ? segmentValue : "");
	// }
	// }

	@Override
	public String createPattern(final String allTagNames) {
		return "@" + name + pattern + "\\s*" + allTagNames;
	}

	@Override
	public String toString() {
		return String.format("Tag [name=%s, pattern=%s]", name, pattern);
	}

}
