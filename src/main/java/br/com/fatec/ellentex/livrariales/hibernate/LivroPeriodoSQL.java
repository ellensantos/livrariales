package br.com.fatec.ellentex.livrariales.hibernate;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import javax.persistence.EntityManager;
import java.util.List;

public class LivroPeriodoSQL {
    public List<LivroPeriodo> getLivros(EntityManager entityManager, String dataInicio, String dataFim){
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        SQLQuery query = session.createSQLQuery(
                "select produto_id, titulo as titulo, date(data) as data, sum(qtde) as qtde " +
                "from itempedido" +
                "    join livro as livro on livro.id = produto_id" +
                "    join pedidocompra_itempedido pi on itempedido.id = pi.itens_id" +
                "    join pedido pe on pe.id = PedidoCompra_id" +
                "    where pe.status = 'ENTREGUE' and date(data) between '" + dataInicio + "' and  '" + dataFim + "'" +
                "group by produto_id, date(pe.data) order by date(pe.data)");

        query.addScalar("titulo");
        query.addScalar("data");
        query.addScalar("qtde", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(LivroPeriodo.class));
        List<LivroPeriodo> list;
        list = query.list();

        return list;
    }
}
