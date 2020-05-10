package challenge;

import java.util.Date;

public class ScriptsBuilder {
    private Integer id;
    private Integer episode;
    private String episodeName;
    private String segment;
    private String type;
    private String actor;
    private String character;
    private String detail;
    private Date recordDate;
    private String series;
    private Date transmissionDate;

    public ScriptsBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ScriptsBuilder withEpisode(Integer episode) {
        this.episode = episode;
        return this;
    }

    public ScriptsBuilder withEpisodeName(String episodeName) {
        this.episodeName = episodeName;
        return this;
    }

    public ScriptsBuilder withSegment(String segment) {
        this.segment = segment;
        return this;
    }

    public ScriptsBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ScriptsBuilder withActor(String actor) {
        this.actor = actor;
        return this;
    }

    public ScriptsBuilder withCharacter(String character) {
        this.character = character;
        return this;
    }

    public ScriptsBuilder withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public ScriptsBuilder withRecordDate(Date recordDate) {
        this.recordDate = recordDate;
        return this;
    }

    public ScriptsBuilder withSeries(String series) {
        this.series = series;
        return this;
    }

    public ScriptsBuilder withTransmissionDate(Date transmissionDate) {
        this.transmissionDate = transmissionDate;
        return this;
    }

    public Scripts createScripts() {
        return new Scripts(id, episode, episodeName, segment, type, actor, character, detail, recordDate, series, transmissionDate);
    }
}