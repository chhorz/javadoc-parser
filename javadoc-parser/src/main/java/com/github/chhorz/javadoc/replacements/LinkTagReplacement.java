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
package com.github.chhorz.javadoc.replacements;

/**
 * Documentation of javadoc tags: <a href="https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html#CHDJGIJB">Oracle</a>
 */
public class LinkTagReplacement extends InlineTagReplacement{

	private static final String TAG_NAME = "link";

	public LinkTagReplacement() {
		super(TAG_NAME);
	}

}
