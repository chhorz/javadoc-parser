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

import com.github.chhorz.javadoc.exception.InvalidCapturingGroupName;

import java.util.List;
import java.util.Objects;

/**
 * Oracle documentation <a href="https://docs.oracle.com/en/java/javase/18/docs/specs/javadoc/doc-comment-spec.html#block-tags">block tags</a>
 *
 * @author chhorz
 */
public interface BlockTag extends Tag {

	/**
	 * @return all names of segments that can be read
	 */
	List<Segment> getSegments();

	/**
	 * @param segmentName the segment name for which the value should be saved
	 * @param value       the value that was parsed for the segment
	 */
	void putValue(final String segmentName, final String value);

	/**
	 * @param allTagNames the list of all patterns will be calculated on the fly and describes the end of a single tag
	 *
	 * @return the complete pattern string for this tag
	 */
	String createPattern(final String allTagNames);

	/**
	 * Representation for a javadoc segment of a given tag.
	 */
	class Segment {

		public static final String SEGMENT_NAME_PATTERN = "[a-zA-Z]\\w*";

		private final String segmentName;
		private final boolean required;

		public Segment(String name) {
			this(name, true);
		}

		public Segment(String segmentName, boolean required) {
			Objects.requireNonNull(segmentName, "The segment name must not be null");

			this.segmentName = segmentName;
			this.required = required;

			if (!segmentName.matches(SEGMENT_NAME_PATTERN)) {
				throw new InvalidCapturingGroupName(segmentName);
			}
		}

		public String getSegmentName() {
			return segmentName;
		}

		public boolean isRequired() {
			return required;
		}

		@Override
		public String toString() {
			return String.format("Segment [name=%s, required=%s]", segmentName, required);
		}
	}

}
