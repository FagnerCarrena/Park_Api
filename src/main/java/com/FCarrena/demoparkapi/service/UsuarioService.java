package com.FCarrena.demoparkapi.service;

import com.FCarrena.demoparkapi.entity.Usuario;
import com.FCarrena.demoparkapi.exception.EntityNotFoundException;
import com.FCarrena.demoparkapi.exception.UsernameUniqueViolationExcption;
import com.FCarrena.demoparkapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//bean gerenciado pelo spring boot @Service//

@RequiredArgsConstructor
@Service
public class UsuarioService {

private  final UsuarioRepository usuarioRepository;
private  final PasswordEncoder passwordEnconder;



@Transactional
public Usuario salvar(Usuario usuario) {
    try {
          usuario.setPassword(passwordEnconder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    } catch (org.springframework.dao.DataIntegrityViolationException ex) {
        throw new UsernameUniqueViolationExcption(String.format("username {%s} ja cadastrado", usuario.getUsername()));
    }
}




@Transactional(readOnly = true)
public Usuario buscarPorId(Long id) {
    return usuarioRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(String.format("usuario id=%s não encontrado", id)
            )
            );
};

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {

    if(!novaSenha.equals(confirmaSenha)){
        throw new RuntimeException("Novasenha nao confere com confrima de senha");
    }

    Usuario user = buscarPorId(id);
    if(!passwordEnconder.matches(senhaAtual, user.getPassword())){
        throw new RuntimeException("Sua senha não confere");
    }

    user.setPassword(passwordEnconder.encode(novaSenha));
    return user;

    }
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos(){
    return usuarioRepository.findAll();

    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("usuario com '%s'não encontrado", username)
                )
        );
    }
    @Transactional(readOnly = true)
    public Usuario.Role buscarRolePorUsername(String username) {
        return usuarioRepository.findRoleByUsername(username);

    }
}
