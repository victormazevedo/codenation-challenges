package challenge;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SCRIPTS")
public class Quote {

    @Id
    private Integer id;
    private String actor;
    private String detail;

    public Quote(Integer id, String actor, String detail) {
        this.id = id;
        this.actor = actor;
        this.detail = detail;
    }

    public Quote() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActor() {
        return this.actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getQuote() {
        return this.detail;
    }

    public void setQuote(String quote) {
        this.detail = quote;
    }

}
