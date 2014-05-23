package com.example.summoner2.app.providers;

/**
 * Created by alex on 30.04.14.
 */
public class LeagueApiProvider extends ApiProvider {
    private String api_version;
    private ApiProvider base_api;
    private String league_base_url;
    private String league_by_summoner_id;

    /* /api/lol/{region}/v2.3/league/by-summoner/{summonerId}/entry */

    public LeagueApiProvider(int summoner_id, String region){

        ApiProvider b_api = new ApiProvider(region);
        this.setBase_api(b_api);
        this.setApi_version("v2.3");
        this.setLeague_base_url("/league");
        this.setLeague_by_summoner_id(this.base_api.getApi()+this.base_api.getRealm()+this.getApi_version()+this.getLeague_base_url()+"/by-summoner/"+summoner_id+"/entry");

    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getLeague_base_url() {
        return league_base_url;
    }

    public void setLeague_base_url(String league_base_url) {
        this.league_base_url = league_base_url;
    }

    public String getLeague_by_summoner_id() {

        return String.format("%s%s", league_by_summoner_id, getBase_api().getApi_key());
    }

    public void setLeague_by_summoner_id(String league_by_summoner_id) {
        this.league_by_summoner_id = league_by_summoner_id;
    }

    public ApiProvider getBase_api() {
        return base_api;
    }

    public void setBase_api(ApiProvider base_api) {
        this.base_api = base_api;
    }
}
