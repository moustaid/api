package com.ositel.pojo;


	
	import java.util.HashMap;
	import java.util.Map;
	import com.fasterxml.jackson.annotation.JsonAnyGetter;
	import com.fasterxml.jackson.annotation.JsonAnySetter;
	import com.fasterxml.jackson.annotation.JsonIgnore;
	import com.fasterxml.jackson.annotation.JsonInclude;
	import com.fasterxml.jackson.annotation.JsonProperty;
	import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"input JSON",
"output"
})
public class RootResponse {

@JsonProperty("input JSON")
private InputJSON inputJSON;
@JsonProperty("output")
private Output output;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("input JSON")
public InputJSON getInputJSON() {
return inputJSON;
}

@JsonProperty("input JSON")
public void setInputJSON(InputJSON inputJSON) {
this.inputJSON = inputJSON;
}

@JsonProperty("output")
public Output getOutput() {
return output;
}

@JsonProperty("output")
public void setOutput(Output output) {
this.output = output;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}


}
