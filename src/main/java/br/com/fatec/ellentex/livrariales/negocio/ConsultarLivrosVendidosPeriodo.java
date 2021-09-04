package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.LivroDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PeriodoConsulta;
import br.com.fatec.ellentex.livrariales.hibernate.LivroPeriodo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConsultarLivrosVendidosPeriodo implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PeriodoConsulta){
            System.out.println("Consultando livros vendidos por periodo ... ");
            PeriodoConsulta periodoConsulta = (PeriodoConsulta) entidade;
            LivroDAO livroDAO = new LivroDAO();
            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dtInicio = new Date();
            Date dtFim = new Date();
            Date dt;
            Map<String, List<LivroPeriodo>> mapLivrosRetornoDAO = new LinkedHashMap<>();
            Map<String, String> mapTitulos = new LinkedHashMap<>();
            Map<String, List<LivroPeriodo>> mapLivrosFinal = new LinkedHashMap<>();
            Map<String, List<LivroPeriodo>> mapTituloLivroDTO = new LinkedHashMap<>();
            Map<String,Map<String, LivroPeriodo>> mapTituloDataLivro = new LinkedHashMap<>();

            try {
                dtInicio = formatoData.parse(periodoConsulta.getDataInicio());
                dtFim = formatoData.parse(periodoConsulta.getDataFim());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<LivroPeriodo> livrosPeriodo = livroDAO.consultaMaisVendidoPorPeriodo(periodoConsulta.getDataInicio(), periodoConsulta.getDataFim());

            long data = (dtInicio.getTime() - dtFim.getTime())+ 3600000; // 1 hora para compensar horário de verão
            long dias = ((data / 86400000L) * - 1) + 2;

            dt = dtInicio;
            dateFormat.format(dt);

            for(int i = 0; i < dias; i++){
                String dataAtualFormat = formatoData.format(dt);
                List<LivroPeriodo> listLivro = new ArrayList<>();
                for(LivroPeriodo livro : livrosPeriodo){
                    String dataLivroFormat = formatoData.format(livro.getData());
                    if(dataLivroFormat.equals(dataAtualFormat)){
                        listLivro.add(livro);
                    }
                }
                mapLivrosRetornoDAO.put(dataAtualFormat, listLivro);
                dt.setDate(dt.getDate() + 1);
            }

            for(Map.Entry<String, List<LivroPeriodo>> key : mapLivrosRetornoDAO.entrySet()){
                //System.out.println(key.getKey());
            }


            //Separando títulos
            for(LivroPeriodo liv: livrosPeriodo){
                mapTitulos.put(liv.getTitulo(), liv.getTitulo());
            }

            //Mapa Titulo x Objetos do Livro do Titulo
            for(Map.Entry<String, String> keymapTitulo : mapTitulos.entrySet()){
                List<LivroPeriodo> listaLivros = new ArrayList<>();
                for(LivroPeriodo livroPeriodo : livrosPeriodo){
                    if(livroPeriodo.getTitulo().equals(keymapTitulo.getKey())){
                        listaLivros.add(livroPeriodo);
                    }
                }
                mapTituloLivroDTO.put(keymapTitulo.getKey(), listaLivros);
            }

            //A ideia é fazer um mapa de <titulo e <mapa de data e livro>>
            for(Map.Entry<String, List<LivroPeriodo>> key: mapTituloLivroDTO.entrySet()){
                Map<String, LivroPeriodo> mapaDataLivro = new LinkedHashMap<>();
                for(LivroPeriodo livroPeriodo : key.getValue()){
                    String dataLivroFormat = formatoData.format(livroPeriodo.getData());
                    mapaDataLivro.put(dataLivroFormat, livroPeriodo);
                }
                mapTituloDataLivro.put(key.getKey(), mapaDataLivro);
            }


            //FECHANDO O MAPA - MAP<DATA,LIST<LIVRODTO>>
            for(Map.Entry<String, List<LivroPeriodo>> keyData : mapLivrosRetornoDAO.entrySet()){
                //System.out.println("CHAVE DATA = " + keyData.getKey());
                List<LivroPeriodo> listaLivro = new ArrayList<>();

                for(Map.Entry<String, String> keyTitulo : mapTitulos.entrySet()){
                    if(mapTituloDataLivro.get(keyTitulo.getKey()).get(keyData.getKey()) != null){
                        listaLivro.add(mapTituloDataLivro.get(keyTitulo.getKey()).get(keyData.getKey()));
                    }
                    else {
                        LivroPeriodo livroPeriodo = new LivroPeriodo();
                        livroPeriodo.setTitulo(keyTitulo.getKey());
                        livroPeriodo.setQtde(0);
                        listaLivro.add(livroPeriodo);
                    }
                }
                mapLivrosFinal.put(keyData.getKey(), listaLivro);
            }

            System.out.println("MAPA FINAL = " + mapLivrosFinal.size());
            return mapLivrosFinal;
        }
        return null;
    }
}
