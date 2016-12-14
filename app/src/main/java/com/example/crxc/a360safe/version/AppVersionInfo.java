package com.example.crxc.a360safe.version;
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
        "versionname",
        "versioncode",
        "des",
        "url"
})
public class AppVersionInfo {

    @JsonProperty("versionname")
    private String versionname;
    @JsonProperty("versioncode")
    private Integer versioncode;
    @JsonProperty("des")
    private String des;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The versionname
     */
    @JsonProperty("versionname")
    public String getVersionname() {
        return versionname;
    }

    /**
     *
     * @param versionname
     * The versionname
     */
    @JsonProperty("versionname")
    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    /**
     *
     * @return
     * The versioncode
     */
    @JsonProperty("versioncode")
    public Integer getVersioncode() {
        return versioncode;
    }

    /**
     *
     * @param versioncode
     * The versioncode
     */
    @JsonProperty("versioncode")
    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode;
    }

    /**
     *
     * @return
     * The des
     */
    @JsonProperty("des")
    public String getDes() {
        return des;
    }

    /**
     *
     * @param des
     * The des
     */
    @JsonProperty("des")
    public void setDes(String des) {
        this.des = des;
    }

    /**
     *
     * @return
     * The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
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