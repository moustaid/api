package com.ositel.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"fileName",
"headerColumn",
"linesValue"
})
public class Output {

@JsonProperty("fileName")
private String fileName;
@JsonProperty("headerColumn")
private List<String> headerColumn = null;
@JsonProperty("linesValue")
private List<List<String>> linesValue = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("fileName")
public String getFileName() {
return fileName;
}

@JsonProperty("fileName")
public void setFileName(String fileName) {
this.fileName = fileName;
}

@JsonProperty("headerColumn")
public List<String> getHeaderColumn() {
return headerColumn;
}

@JsonProperty("headerColumn")
public void setHeaderColumn(List<String> headerColumn) {
this.headerColumn = headerColumn;
}

@JsonProperty("linesValue")
public List<List<String>> getLinesValue() {
return linesValue;
}

@JsonProperty("linesValue")
public void setLinesValue(List<List<String>> linesValue) {
this.linesValue = linesValue;
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
