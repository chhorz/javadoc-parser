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
package com.github.chhorz.javadoc.test.tags;

import com.github.chhorz.javadoc.exception.InvalidCapturingGroupName;
import com.github.chhorz.javadoc.tags.BlockTag;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TagTest {

	@Nested
	class SegmentTests {

		@Test
		void testInvalidSegment() {
			// given
			String segmentName = "aB42Cxyz";

			// when - then
			assertThat(new BlockTag.Segment(segmentName))
					.isNotNull();
		}

		@Test
		void segmentWithoutName() {
			// given
			String segmentName = null;

			// when - then
			assertThatThrownBy(() -> new BlockTag.Segment(segmentName))
					.isInstanceOf(NullPointerException.class)
					.hasMessage("The segment name must not be null");
		}

		@Test
		void segmentWithInvalidCharacter() {
			// given
			String segmentName = "a-b";

			// when - then
			assertThatThrownBy(() -> new BlockTag.Segment(segmentName))
					.isInstanceOf(InvalidCapturingGroupName.class)
					.hasMessage("The group name 'a-b' is not valid. Please refer to the java capturing group documentation.");
		}

	}

}
