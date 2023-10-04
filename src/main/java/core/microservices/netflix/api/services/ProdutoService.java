package core.microservices.netflix.api.services;

import core.microservices.netflix.api.exception.ResourceNotFoundException;
import core.microservices.netflix.api.model.Produto;
import core.microservices.netflix.api.repository.ProdutoRepository;
import core.microservices.netflix.api.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> obterTodos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class)).collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> obterPorId(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(id == null){
            throw new ResourceNotFoundException("Produto com id: " + id + "não encontrado");
        }
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }

    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDto){
        produtoDto.setId(null);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());
        return produtoDto;
    }

    public void deletarProduto(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o produto com o Id: " + id);
        }

        produtoRepository.deleteById(id);
    }

    public ProdutoDTO atualizarProduto(Integer id, ProdutoDTO produtoDto){
        produtoDto.setId(id);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);
        produtoRepository.save(produto);
        return produtoDto;
    }
}
