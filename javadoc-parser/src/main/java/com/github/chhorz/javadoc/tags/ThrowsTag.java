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
package com.github.chhorz.javadoc.tags;

/**
 * https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html#CHDCHAHD
 *
 * @author chhorz
 *
 */
public class ThrowsTag extends StructuredTag {

	private static final String TAG_NAME = "throws";

	private static final String CLASS_NAME = "className";
	private static final String DESCRIPTION = "description";

	public ThrowsTag() {
		super(TAG_NAME, CLASS_NAME, DESCRIPTION);
	}

	public String getClassName() {
		return getValues().get(CLASS_NAME);
	}

	public String getDescription() {
		return getValues().get(DESCRIPTION);
	}

}
