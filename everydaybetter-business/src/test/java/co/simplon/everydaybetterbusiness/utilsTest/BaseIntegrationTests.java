package co.simplon.everydaybetterbusiness.utilsTest;

import com.jayway.jsonpath.JsonPath;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Transactional
public class BaseIntegrationTests extends BaseMvcTests {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");

    @PersistenceContext
    private EntityManager entityManager;

    protected final static LocalDate asLocalDate(String json, String path) {
        var candidate = (String) JsonPath.read(json, path);
        return null == candidate ? null
                : LocalDate.parse(candidate, DATE_FORMATTER);
    }

    protected final static Long asLong(String json, String path) {
        var candidate = (Integer) JsonPath.read(json, path);
        return null == candidate ? null : candidate.longValue();
    }

    protected final static Boolean asBoolean(String json, String path) {
        var candidate = (Boolean) JsonPath.read(json, path);
        return null == candidate ? null : candidate.booleanValue();
    }

    protected final static String asString(String json, String path) {
        return (String) JsonPath.read(json, path);
    }

    protected final static Year asYear(String json, String path) {
        var candidate = (Integer) JsonPath.read(json, path);
        return null == candidate ? null : Year.of(candidate);
    }
//
//    protected final <T> T findEntity(Class<T> type, String query,
//            Object... params) {
//        var jpql = jpql(query, params);
//        return findEntity(type, jpql);
//    }

    protected final <T> T findEntity(Class<T> type, String jpql) {
        return entityManager.createQuery(jpql, type).getSingleResult();
    }

    protected final <T> T findEntityWithParams(Class<T> type, String jpql, Object... params) {
        var query = entityManager.createQuery(jpql, type);
        for (int i = 0; i < params.length; i++) {
            query.setParameter("p" + (i + 1), params[i]);
        }
        return query.getSingleResult();
    }

    protected final <T> T findEntity(Class<T> type, String jpql, Object... params) {
        var q = entityManager.createQuery(jpql, type);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        return q.getSingleResult();
    }

    protected final <T> T findEntity(Class<T> type, Long id) {
        return entityManager.find(type, id);
    }

    protected final <T> List<T> findEntities(Class<T> type, String query, Object... params) {
        var jpql = jpql(query, params);
        return entityManager.createQuery(jpql, type).getResultList();
    }

    protected final static String jpql(String query, Object... params) {
        return String.format(query, params);
    }

    protected final <T> List<T> findEntitiesWithParams(Class<T> type, String jpql, Object... params) {
        var query = entityManager.createQuery(jpql, type);
        for (int i = 0; i < params.length; i++) {
            query.setParameter("p" + (i + 1), params[i]);
        }
        return query.getResultList();
    }

}
