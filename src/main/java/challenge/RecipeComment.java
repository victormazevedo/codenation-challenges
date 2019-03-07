package challenge;

import org.springframework.data.annotation.Id;

/**
 * Classe para mapear o coment�rio da receita no MongoDB
 *
 */
public class RecipeComment {

    @Id
    private String id;
    private String comment;

    public RecipeComment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecipeComment{");
        sb.append("id='").append(id).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
