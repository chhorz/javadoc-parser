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
package com.github.chhorz.javadoc;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.github.chhorz.javadoc.tags.Tag;

/**
 *
 * @author chhorz
 *
 */
public class JavaDoc {

	private String description;
	private List<Tag> tags;

	public JavaDoc(final String description, final List<Tag> tags) {
		this.description = description;
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public <T extends Tag> List<T> getTags(final Class<T> tagClass) {
		return tags.stream()
			.filter(tagClass::isInstance)
			.map(tagClass::cast)
			.collect(toList());
	}

	@Override
	public String toString() {
		return String.format("JavaDoc [description=%s, tags=%s]", description, tags);
	}

}
