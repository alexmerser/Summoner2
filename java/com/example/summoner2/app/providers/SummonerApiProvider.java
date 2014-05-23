package com.example.summoner2.app.providers;

/**
 * Created by alex on 30.04.14.
 */
public class SummonerApiProvider extends ApiProvider {

    private String api_version;
    private String summoner_base_url;
    private String summoner_by_name_url;
    private String summoner_by_id_url;
    private String summoner_masteries;
    private String summoner_name_by_id_url;
    private String summoner_runes_url;
    private ApiProvider base_api;


    public SummonerApiProvider(String region){

        this.base_api = new ApiProvider(region);
        this.setApi_version("v1.4");
        this.setSummoner_base_url(base_api.getApi()+base_api.getRealm()+this.getApi_version()+"/summoner/");
        this.setSummoner_by_name_url(this.getSummoner_base_url()+"by-name/");
        this.setSummoner_masteries("/masteries");
        this.setSummoner_name_by_id_url("/name");
        this.setSummoner_runes_url("/runes");

    }

    public String getSummoner_by_name_url(String name) {
        return summoner_by_name_url+name+base_api.getApi_key();
    }

    public void setSummoner_by_name_url(String summoner_by_name_url) {
        this.summoner_by_name_url = summoner_by_name_url;
    }

    public String getSummoner_by_id_url() {
        return summoner_by_id_url;
    }

    public void setSummoner_by_id_url(String summoner_by_id_url) {
        this.summoner_by_id_url = summoner_by_id_url;
    }

    public String getSummoner_masteries() {
        return summoner_masteries;
    }

    public void setSummoner_masteries(String summoner_masteries) {
        this.summoner_masteries = summoner_masteries;
    }

    public String getSummoner_name_by_id_url() {
        return summoner_name_by_id_url;
    }

    public void setSummoner_name_by_id_url(String summoner_name_by_id_url) {
        this.summoner_name_by_id_url = summoner_name_by_id_url;
    }

    public String getSummoner_runes_url() {
        return summoner_runes_url;
    }

    public void setSummoner_runes_url(String summoner_runes_url) {
        this.summoner_runes_url = summoner_runes_url;
    }


    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getSummoner_base_url() {
        return summoner_base_url;
    }

    public void setSummoner_base_url(String summoner_base_url) {
        this.summoner_base_url = summoner_base_url;
    }

    public ApiProvider getBase_api() {
        return base_api;
    }
}
