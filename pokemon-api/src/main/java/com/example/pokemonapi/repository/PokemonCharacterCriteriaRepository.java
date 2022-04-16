package com.example.pokemonapi.repository;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.model.PokemonCharacterPage;
import com.example.pokemonapi.model.PokemonCharacterSearchCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.pokemonapi.util.ApiConstants.ATTACK;
import static com.example.pokemonapi.util.ApiConstants.DEFENSE;
import static com.example.pokemonapi.util.ApiConstants.HIT_POINTS;

@Slf4j
@Repository
public class PokemonCharacterCriteriaRepository {

    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public PokemonCharacterCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<PokemonCharacters> findAllWithFilters(PokemonCharacterPage pokemonCharacterPage,
                                                      PokemonCharacterSearchCriteria employeeSearchCriteria){
        CriteriaQuery<PokemonCharacters> criteriaQuery = criteriaBuilder.createQuery(PokemonCharacters.class);
        Root<PokemonCharacters> employeeRoot = criteriaQuery.from(PokemonCharacters.class);
        Predicate predicate = getPredicate(employeeSearchCriteria, employeeRoot);
        criteriaQuery.where(predicate);
        setOrder(pokemonCharacterPage, criteriaQuery, employeeRoot);

        TypedQuery<PokemonCharacters> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pokemonCharacterPage.getPageNumber() * pokemonCharacterPage.getPageSize());
        typedQuery.setMaxResults(pokemonCharacterPage.getPageSize());

        Pageable pageable = getPageable(pokemonCharacterPage);

        long employeesCount = getEmployeesCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, employeesCount);
    }

    private Predicate getPredicate(PokemonCharacterSearchCriteria pokemonCharacterSearchCriteria,
                                   Root<PokemonCharacters> pokemonCharactersRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(pokemonCharacterSearchCriteria.getHp()) && pokemonCharacterSearchCriteria.getHp() != 0){
            predicates.add(
                    criteriaBuilder.equal(pokemonCharactersRoot.get(HIT_POINTS.toLowerCase()),
                            pokemonCharacterSearchCriteria.getHp())
            );
        }
        if(Objects.nonNull(pokemonCharacterSearchCriteria.getAttack()) && pokemonCharacterSearchCriteria.getAttack() != 0){
            predicates.add(
                    criteriaBuilder.equal(pokemonCharactersRoot.get(ATTACK.toLowerCase()),
                             pokemonCharacterSearchCriteria.getAttack())
            );
        }

        if(Objects.nonNull(pokemonCharacterSearchCriteria.getDefense()) && pokemonCharacterSearchCriteria.getDefense() != 0){
            predicates.add(
                    criteriaBuilder.equal(pokemonCharactersRoot.get(DEFENSE.toLowerCase()),
                            pokemonCharacterSearchCriteria.getDefense())
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(PokemonCharacterPage pokemonCharacterPage,
                          CriteriaQuery<PokemonCharacters> criteriaQuery,
                          Root<PokemonCharacters> pokemonCharactersRoot) {
        if(pokemonCharacterPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(pokemonCharactersRoot.get(pokemonCharacterPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(pokemonCharactersRoot.get(pokemonCharacterPage.getSortBy())));
        }
    }

    private Pageable getPageable(PokemonCharacterPage pokemonCharacterPage) {
        Sort sort = Sort.by(pokemonCharacterPage.getSortDirection(), pokemonCharacterPage.getSortBy());
        return PageRequest.of(pokemonCharacterPage.getPageNumber(),pokemonCharacterPage.getPageSize(), sort);
    }

    private long getEmployeesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<PokemonCharacters> countRoot = countQuery.from(PokemonCharacters.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
