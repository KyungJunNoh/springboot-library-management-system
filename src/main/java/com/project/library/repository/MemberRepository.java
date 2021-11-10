package com.project.library.repository;

import com.project.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findById(String id);
    boolean existsByIdAndCallNum(String id, String callNum);
}
