package com.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.dto.MemberDto;

//@Repository // JpaRepository<entity객체, key타입> - find, save, delete (CRUD 작업에 필요한 기본 메소드가 만들어져 있음)
// findAll, findById(), save(), delete(), deleteById, count()
public interface MemberRepository extends JpaRepository<MemberDto, String> {

	// 로그인 확인 - 1개 select일때 타입 : Optional 
	Optional<MemberDto> findByIdAndPw(String id, String pw);

	// 로그인 확인 - JPA 지정하는 이름방식을 따르지 않을 경우
//	@Query(value = "select * from memberdto where id = ? and pw = ?", nativeQuery = true)
//	Optional<MemberDto> selectLogin(String id, String pw);	
//	@Query("select m from MemberDto m where m.id = :id and m.pw = :pw")
//	Optional<MemberDto> selectLogin(@Param("id") String id, @Param("pw") String pw);

}
