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
package com.github.chhorz.javadoc.test.tags;

import com.github.chhorz.javadoc.tags.StructuredTag;

/**
 * Custom tag to test extensibility of the javadoc parser.
 * 
 * @author chhorz
 *
 */
public class CustomTag extends StructuredTag {

	private static final String TAG_NAME = "custom";

	private static final String TAG_VALUE = "value";

	public CustomTag() {
		super(TAG_NAME, TAG_VALUE);
	}

	public String getValue() {
		return getValues().get(TAG_VALUE);
	}

}
