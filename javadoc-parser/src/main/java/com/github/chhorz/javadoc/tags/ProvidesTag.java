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
 * Oracle documentation <a href="https://docs.oracle.com/en/java/javase/18/docs/specs/javadoc/doc-comment-spec.html#provides">@provides</a> tag
 *
 * @author chhorz
 */
public class ProvidesTag extends StructuredTag {

	private static final String TAG_NAME = "provides";

	private static final String SERVICE_TYPE = "serviceType";
	private static final String DESCRIPTION = "description";

	public ProvidesTag() {
		super(TAG_NAME, new Segment(SERVICE_TYPE), new Segment(DESCRIPTION, false));
	}

	public String getServiceType() {
		return getValues().get(SERVICE_TYPE);
	}

	public String getDescription() {
		return getValues().get(DESCRIPTION);
	}

}
