package com.hieunh.restfulAPI.repository;

import com.hieunh.restfulAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);

//    JPQL
    @Query("Select u from User u where u.email = ?1")
    public User getUserInfoByEmail(String email);

//    Native SQL
    @Query(value = "Select * from user where full_name = :name", nativeQuery = true)
    public User getUserInfoByFullName(@Param("name") String fullName);

    @Modifying
    @Transactional
    @Query(value = "update user set full_name = ?1, avatar = ?2 where id = ?3", nativeQuery = true)
    public void updateProfile(String name, String avatar, int id);

    @Query(nativeQuery = true, name = "getUserInfo")
    User getUserInfo(int id);
}
