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
package com.github.chhorz.javadoc.exception;

import static java.lang.String.format;

/**
 * This exception will be thrown if the group name is not valid (<a href="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#groupname">Documentation</a>)
 *
 * @author chhorz
 */
public class InvalidCapturingGroupName extends RuntimeException {

	private static final long serialVersionUID = 4020993298788475547L;

	public InvalidCapturingGroupName(String groupName) {
		super(format("The group name '%s' is not valid. Please refer to the java capturing group documentation.", groupName));
	}

}
