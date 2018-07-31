/**
 *
 *    Copyright 2018 the original author or authors.
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

import com.github.chhorz.javadoc.tags.Tag;

/**
 * A exception that is thrown if more than one tags of one type should be registered within one parser.
 * 
 * @author chhorz
 * 
 */
public class DuplicateTagException extends RuntimeException {
	
	public static final long serialVersionUID = 1l;
	
	public DuplicateTagException(final Tag tag){
		super(String.format("The parser contains a tag '@%s'.", tag.getTagName()));
	}
	
}
