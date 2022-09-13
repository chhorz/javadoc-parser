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
 * Oracle documentation <a href="https://docs.oracle.com/en/java/javase/18/docs/specs/javadoc/doc-comment-spec.html#serialfield">@serialField</a> tag
 *
 * @author chhorz
 */
public class SerialFieldTag extends StructuredTag {

	private static final String TAG_NAME = "serialField";

	private static final String FIELD_NAME = "fieldName";
	private static final String FIELD_TYPE = "fieldType";
	private static final String FIELD_DESCRIPTION = "fieldDescription";

	public SerialFieldTag() {
		super(TAG_NAME, new Segment(FIELD_NAME), new Segment(FIELD_TYPE), new Segment(FIELD_DESCRIPTION, false));
	}

	public String getFieldName() {
		return getValues().get(FIELD_NAME);
	}

	public String getFieldType() {
		return getValues().get(FIELD_TYPE);
	}

	public String getFieldDescription() {
		return getValues().get(FIELD_DESCRIPTION);
	}

}
