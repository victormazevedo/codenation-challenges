package challenge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Scripts {

    @Id
    private Integer id;
    private Integer episode;
    @Column(name = "episode_name")
    private String episodeName;
    private String segment;
    private String type;
    private String actor;
    private String character;
    private String detail;
    @Column(name = "record_date")
    private Date recordDate;
    private String series;
    @Column(name = "transmission_date")
    private Date transmissionDate;

    Scripts(Integer id, Integer episode, String episodeName, String segment, String type, String actor, String character, String detail, Date recordDate, String series, Date transmissionDate) {
        this.id = id;
        this.episode = episode;
        this.episodeName = episodeName;
        this.segment = segment;
        this.type = type;
        this.actor = actor;
        this.character = character;
        this.detail = detail;
        this.recordDate = recordDate;
        this.series = series;
        this.transmissionDate = transmissionDate;
    }

    public Scripts() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getEpisode() {
        return episode;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public String getSegment() {
        return segment;
    }

    public String getType() {
        return type;
    }

    public String getActor() {
        return actor;
    }

    public String getCharacter() {
        return character;
    }

    public String getDetail() {
        return detail;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public String getSeries() {
        return series;
    }

    public Date getTransmissionDate() {
        return transmissionDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Scripts{");
        sb.append("id=").append(id);
        sb.append(", episode=").append(episode);
        sb.append(", episodeName='").append(episodeName).append('\'');
        sb.append(", segment='").append(segment).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", actor='").append(actor).append('\'');
        sb.append(", character='").append(character).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", recordDate=").append(recordDate);
        sb.append(", series='").append(series).append('\'');
        sb.append(", transmissionDate=").append(transmissionDate);
        sb.append('}');
        return sb.toString();
    }
}
