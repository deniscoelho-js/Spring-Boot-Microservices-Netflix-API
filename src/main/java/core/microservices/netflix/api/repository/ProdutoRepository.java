package core.microservices.netflix.api.repository;

import core.microservices.netflix.api.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    public List<Produto> obterTodos(){
        return produtos;
    }

    public Optional<Produto> obterPorId(Integer id){
        return produtos.stream().filter(produto -> produto.getId() == id).findFirst();
    }

    public Produto adicionarProduto(Produto produto){
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    public void deletarProduto(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }
}
