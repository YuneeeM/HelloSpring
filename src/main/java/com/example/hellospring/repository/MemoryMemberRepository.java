package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private  static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //optional로 감싸면 null 이라도 반환가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member ->member.getName().equals(name))
                .findAny();
        //루프를 돌리면서 filter로 같은 값이 있으면 반환함 , 끝까지 찾아서 없으면 nill
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store에 있는 멤버들를 반환해줌
    }

    public void clearStore(){
        store.clear();
    }
}
