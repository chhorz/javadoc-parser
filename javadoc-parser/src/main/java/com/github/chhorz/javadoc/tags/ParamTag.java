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
 * Oracle documentation <a href="https://docs.oracle.com/en/java/javase/18/docs/specs/javadoc/doc-comment-spec.html#param">@param</a> tag
 * Kotlin documentation <a href="https://kotlinlang.org/docs/kotlin-doc.html#param-name">@param</a> tag
 *
 * @author chhorz
 */
public class ParamTag extends StructuredTag {

	private static final String TAG_NAME = "param";

	private static final String PARAMETER_NAME = "parameterName";
	private static final String DESCRIPTION = "description";

	public ParamTag() {
		super(TAG_NAME, new Segment(PARAMETER_NAME), new Segment(DESCRIPTION, false));
	}

	public String getParamName() {
		return getValues().get(PARAMETER_NAME);
	}

	public String getParamDescription() {
		return getValues().get(DESCRIPTION);
	}

}
