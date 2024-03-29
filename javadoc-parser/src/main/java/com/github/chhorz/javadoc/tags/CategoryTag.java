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
 * This tag is not an official javadoc tag but was proposed <a href="https://www.oracle.com/java/technologies/javase/proposed-javadoc-tags.html">here</a>
 *
 * @author chhorz
 */
public class CategoryTag extends StructuredTag {

	private static final String TAG_NAME = "category";

	private static final String CATEGORY_NAME = "categoryName";

	public CategoryTag() {
		super(TAG_NAME, new Segment(CATEGORY_NAME));
	}

	public String getCategoryName() {
		return getValues().get(CATEGORY_NAME);
	}

}
