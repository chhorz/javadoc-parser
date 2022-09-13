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
package com.github.chhorz.javadoc.test.parser.tags;

import com.github.chhorz.javadoc.tags.PatternTag;

import java.util.Arrays;
import java.util.List;

/**
 * Custom tag to test extensibility of the javadoc parser.
 *
 * @author chhorz
 *
 */
public class CustomPatternTag extends PatternTag {

    private static final String TAG_NAME = "custom";

    private static final String NUMBER = "number";
    private static final String OTHER_VALUE = "otherValue";

    private String number;
    private String otherValue;

    public CustomPatternTag() {
        super(TAG_NAME, "\\s+?(?<number>\\d+?)\\s+?(?<otherValue>.+?)\\s*");
    }

    public Long getNumber(){
        return Long.parseLong(number);
    }

    public String getOtherValue(){
        return otherValue;
    }

    @Override
    public List<Segment> getSegments() {
        return Arrays.asList(new Segment(NUMBER), new Segment(OTHER_VALUE));
    }

    @Override
    public void putValue(String segmentName, String value) {
        switch (segmentName) {
            case NUMBER:
                this.number = value;
            case OTHER_VALUE:
                this.otherValue = value;
        }
    }
}
