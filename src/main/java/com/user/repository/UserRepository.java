package com.user.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.repository.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Serializable> {
	 @Query(value = "{'username' : ?0}")
	 User findByUsername(String username);
	

//    @Query(value = "{'userId' : ?0, 'status' : {$in: ?1}, 'createdAt':{'$gte':?2}}",
//            fields = "{'id':1, 'questionId':1}")
//    public List<NotesResponseVO> findByUserIdAndStatusAndTime(String userId);

}
