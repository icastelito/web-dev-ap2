package beans;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class LancamentoDAO {

    @Transactional
    public void salvar(Lancamento lancamento) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            entityManager.persist(lancamento);
        } finally {
            entityManager.close();
        }
    }

    @Transactional
    public void excluir(Integer lancamentoId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            Lancamento lancamento = entityManager.find(Lancamento.class, lancamentoId);
            if (lancamento != null) {
                entityManager.remove(lancamento);
            }
        } finally {
            entityManager.close();
        }
    }

    public List<Lancamento> listar() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Lancamento l";
            TypedQuery<Lancamento> query = entityManager.createQuery(jpql, Lancamento.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
