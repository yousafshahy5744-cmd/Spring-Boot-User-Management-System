package com.example.crud_application.repository;

import com.example.crud_application.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByEmail(String email);

        @Query("SELECT u FROM User u WHERE u.email = :email OR u.userCNIC = :userCNIC OR u.mobileNumber = :mobileNumber")
        List<User> alreadyExists(@Param("email") String email,
                                 @Param("userCNIC") String userCNIC,
                                 @Param("mobileNumber") String mobileNumber
        );

        @Transactional
        @Modifying
        @Query("Update User u set u.status = :newStatus, u.updatedAt = :now Where u.isActive = true")
        int updateStatusForActivesUsers(
                @Param("newStatus")User.AccountStatus newStatus,
                @Param("now") LocalDateTime now);

}
