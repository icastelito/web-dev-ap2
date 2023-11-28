package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class LancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Lancamento lancamento = new Lancamento();
    private LancamentoDAO lancamentoDAO = new LancamentoDAO();

    public void salvar() {
        lancamentoDAO.salvar(lancamento);
        lancamento = new Lancamento();
    }

    public void excluir(Lancamento lancamento) {
        lancamentoDAO.excluir(lancamento.getId());
    }

    public List<Lancamento> getLancamentos() {
        return lancamentoDAO.listar();
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public void resumo() {
        List<Lancamento> lista = getLancamentos();

        double totalDespesas = 0;
        double totalReceitas = 0;

        for (Lancamento lancamento : lista) {
            if ("Despesa".equals(lancamento.getTipo())) {
                totalDespesas += lancamento.getValor();
            } else if ("Receita".equals(lancamento.getTipo())) {
                totalReceitas += lancamento.getValor();
            }
        }

        double saldo = totalReceitas - totalDespesas;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Resumo", "Receita: " + totalReceitas + " || Despesas: " + totalDespesas + " || Saldo: " + saldo));
    }
}
