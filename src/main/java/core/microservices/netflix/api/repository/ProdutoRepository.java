package core.microservices.netflix.api.repository;

import core.microservices.netflix.api.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

    public Produto atualizarProduto(Produto produto){
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if(produtoEncontrado.isEmpty()){
            throw new InputMismatchException("Produto não encontrado");
        }

        deletarProduto(produto.getId());
        produtos.add(produto);
        return produto;

    }
}
