package challenge;

public class Jogador {

    private String id;
    private String name;
    private String full_name;
    private String club;
    private String age;
    private String birth_date;
    private String nationality;
    private String eur_wage;
    private String eur_release_clause;

    public Jogador(String id, String name, String full_name, String club, String age, String birth_date, String nationality, String eur_wage, String eur_release_clause) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.club = club;
        this.age = age;
        this.birth_date = birth_date;
        this.nationality = nationality;
        this.eur_wage = eur_wage;
        this.eur_release_clause = eur_release_clause;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEur_wage() {
        return eur_wage;
    }

    public void setEur_wage(String eur_wage) {
        this.eur_wage = eur_wage;
    }

    public String getEur_release_clause() {
        return eur_release_clause;
    }

    public void setEur_release_clause(String eur_release_clause) {
        this.eur_release_clause = eur_release_clause;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Jogador{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", full_name='").append(full_name).append('\'');
        sb.append(", club='").append(club).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append(", birth_date='").append(birth_date).append('\'');
        sb.append(", nationality='").append(nationality).append('\'');
        sb.append(", eur_wage='").append(eur_wage).append('\'');
        sb.append(", eur_release_clause='").append(eur_release_clause).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
