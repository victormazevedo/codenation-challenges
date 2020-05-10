package challenge;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeCommentRepository extends MongoRepository<RecipeComment, ObjectId> {
}
