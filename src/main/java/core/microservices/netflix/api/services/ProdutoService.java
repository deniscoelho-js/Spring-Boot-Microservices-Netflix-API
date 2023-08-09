package core.microservices.netflix.api.services;

import core.microservices.netflix.api.model.Produto;
import core.microservices.netflix.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> obterTodos(){
        return produtoRepository.obterTodos();
    }

    public Optional<Produto> obterPorId(Integer id){

        if(id == null){
            throw new InputMismatchException("Id é nulo");
        }
        return produtoRepository.obterPorId(id);
    }

    public Produto adicionarProduto(Produto produto){
        return produtoRepository.adicionarProduto(produto);
    }

    public void deletarProduto(Integer id){
        produtoRepository.deletarProduto(id);
    }

    public Produto atualizarProduto(Integer id, Produto produto){
        produto.setId(id);
        return produtoRepository.atualizarProduto(produto);
    }
}
