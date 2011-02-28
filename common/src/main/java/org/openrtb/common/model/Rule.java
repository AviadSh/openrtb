/*
 * Copyright (c) 2010, The OpenRTB Project All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the OpenRTB nor the names of its contributors may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.openrtb.common.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA. Rule
 *
 * @author jdrahos
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"operator", "type", "values"})
public class Rule {
	@JsonProperty
	private String operator;
	@JsonProperty
	private String type;
	@JsonProperty
	private List<Object> values;

	protected Rule(){
		values = new LinkedList<Object>();
	}

	public Rule(String operator, String type, Collection<Object> values){
		this();
		setOperator(operator);
		setType(type);
		setValues(values);
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		if(operator == null || operator.length() == 0)
			throw new IllegalArgumentException("operator passed to Rule#setOperator() must be non-null and at least one character long");

		this.operator = operator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(type == null || type.length() == 0)
			throw new IllegalArgumentException("type passed to Rule#setType() must be non-null and at least one character long");

		this.type = type;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(Collection<Object> values) {
		checkValues(values);
		this.values.clear();
		this.values.addAll(values);
	}

	private void checkValues(Collection<Object> values){
		if(values == null || values.size() == 0){
			throw new IllegalArgumentException("Values passed to Rule#setValues() must be non-null and contain at least one item");
		}

		for(Object val: values){
			if(!String.class.equals(val.getClass())
					&& !Integer.class.equals(val.getClass())){
				throw new IllegalArgumentException("Values passed to Rule#setValues() must be either of type String or Integer");
			}
		}
	}

	public void addValue(String value) {
		if(value == null || value.length() == 0) {
			throw new IllegalArgumentException("Value passed to Rule#addValue() must be non-null and at least one character long");
		}

		this.values.add(value);
	}

	public void addValue(Integer value) {
		if(value == null) {
			throw new IllegalArgumentException("Value passed to Rule#addValue() must be non-null");
		}

		this.values.add(value);
	}
}
