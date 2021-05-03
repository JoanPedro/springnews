package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reviews.main.domain.Cidade;
import reviews.main.domain.Cliente;
import reviews.main.domain.Endereco;
import reviews.main.domain.enums.TipoCliente;
import reviews.main.dto.ClienteDTO;
import reviews.main.dto.ClienteNewDto;
import reviews.main.repositories.ClienteRepository;
import reviews.main.repositories.EnderecoRepository;
import reviews.main.services.exceptions.DataIntegrityException;
import reviews.main.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repository;

  @Autowired
  private EnderecoRepository enderecoRepository;

  public Cliente find(Integer id) {
    Optional<Cliente> result = this.repository.findById(id);
    return result.orElseThrow(
        () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getSimpleName())
    );
  }

  public Cliente insert(Cliente obj) {
    // Ensure new obj by id. Case id not null, its perform an update!
    obj.setId(null);
    Cliente result = this.repository.save(obj);
    this.enderecoRepository.saveAll(obj.getEnderecos());
    return result;
  }

  public void update(Cliente obj) {
    Cliente newObj = this.find(obj.getId());
    this.updateData(newObj, obj);
    this.repository.save(newObj);
  }

  public void delete(Integer id) {
    this.find(id);
    try {
      this.repository.deleteById(id);
    } catch (DataIntegrityViolationException exception) {
      throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas!");
    }
  }

  public List<Cliente> findAll() {
    return this.repository.findAll();
  }

  public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return repository.findAll(pageRequest);
  }

  public Cliente fromDTO(ClienteDTO objDTO) {
    return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
  }

  @Transactional
  public Cliente fromDTO(ClienteNewDto objDTO) {
    Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
    Cidade cidade = new Cidade(objDTO.getCidadeId(), null, null);
    Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);

    // Relational
    cliente.getEnderecos().add(endereco);
    cliente.getTelefones().add(objDTO.getTelefone1());

    if (!Objects.isNull(objDTO.getTelefone2()))
      cliente.getTelefones().add(objDTO.getTelefone2());

    if (!Objects.isNull(objDTO.getTelefone3()))
      cliente.getTelefones().add(objDTO.getTelefone3());

    return cliente;
  }

  private void updateData(Cliente newObj, Cliente obj) {
    newObj.setNome(obj.getNome());
    newObj.setEmail(obj.getEmail());
  }
}
